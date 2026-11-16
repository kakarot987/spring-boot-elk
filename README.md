# Spring Boot + ELK — local observability demo

Build and run from a clean clone with `docker compose up --build -d`.
This multi-stage image builds the Spring Boot JAR using the Gradle wrapper,
and the logging encoder dependency is pinned. The application emits console logs
even when Logstash is unavailable; `LOGSTASH_HOST` defaults to `localhost`
for local application runs and is `logstash` in Docker Compose.

## Verify actual ingestion

Run `bash scripts/verify-ingestion.sh`. It calls `/log?level=error` and
polls Elasticsearch for the resulting JSON event. Visit
http://127.0.0.1:5601 and create a data view for `springboot-logs-*`
to inspect events in Kibana.

## Security and limits

**Development only:** Elasticsearch authentication is disabled. Elasticsearch,
Kibana and the sample application bind their published ports to localhost.
Do not expose these services publicly or reuse this configuration in production.
Logstash uses internal Docker networking. The example runs pinned Elasticsearch,
Logstash and Kibana 8.12.1 images; update and evaluate security advisories separately.
The integration check requires an active local Compose stack and has not yet been
executed in this review. CI tests the Gradle build and Docker image, not a live ELK deployment.

Stop with `docker compose down`.
