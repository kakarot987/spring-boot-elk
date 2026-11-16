#!/usr/bin/env bash
set -euo pipefail
# Run after: docker compose up -d --build
curl --fail-with-body -sS "http://127.0.0.1:8080/log?level=error"
echo
for attempt in $(seq 1 40); do
  result="$(curl -fsS -H 'Content-Type: application/json' \
    -d '{"query":{"match_phrase":{"message":"This is an ERROR log"}},"size":1}' \
    "http://127.0.0.1:9200/springboot-logs-*/_search" || true)"
  if echo "$result" | grep -q '"This is an ERROR log"'; then
    echo "Success: ERROR event indexed in Elasticsearch"
    exit 0
  fi
  sleep 3
done
echo "ERROR: Log event not indexed in 120 seconds; inspect compose logs." >&2
exit 1
