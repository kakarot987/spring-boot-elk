# Spring Boot + ELK Stack Example

This project demonstrates how to integrate a Spring Boot application with the ELK Stack (Elasticsearch, Logstash, Kibana) for centralized logging and visualization.

## 🧱 Stack

- Spring Boot (REST API + Logging)
- Logback + Logstash Encoder (JSON Logging)
- Logstash (log ingestion)
- Elasticsearch (log storage + indexing)
- Kibana (log search + visualization)
- Docker Compose (for local setup)

---

## 🚀 How to Run

### 1. Build the Spring Boot Application

```bash
./gradlew clean build
```

### 2. Start the ELK Stack + App with Docker

```bash
docker-compose up --build
```

This will spin up:
- Elasticsearch at `http://localhost:9200`
- Logstash listening on port `5000`
- Kibana at `http://localhost:5601`
- Spring Boot app at `http://localhost:8080`

---

## 🧪 Test Logging

Use a browser or curl to generate logs:

```bash
curl http://localhost:8080/log?level=info
curl http://localhost:8080/log?level=warn
curl http://localhost:8080/log?level=error
```

---

## 📊 View Logs in Kibana

1. Open `http://localhost:5601`
2. Go to **Management > Stack Management > Index Patterns**
3. Create a new pattern: `springboot-logs-*`
4. Choose `@timestamp` as the time field
5. Go to **Discover** to explore logs!

---

## 📦 Docker Components

- `Dockerfile`: Builds the Spring Boot app image
- `docker-compose.yml`: Spins up all services
- `logstash.conf`: Logstash pipeline config

---

## ✅ Notes

- Logs are sent to Logstash via TCP on port `5000` in JSON format.
- Logstash parses them and sends to Elasticsearch.
- Kibana visualizes the logs from the ES index.

---

Enjoy! 🚀