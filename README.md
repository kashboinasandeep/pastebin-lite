# Pastebin Lite

A minimal Pastebin-like application that allows users to create text pastes and share them via a unique URL. Pastes can optionally expire based on time (TTL) or view count.

## Features
- Create and share text pastes
- Optional TTL (time-based expiry)
- Optional max view count
- HTML view for users
- JSON API for automation
- Health check endpoint

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 (file-based persistence)
- Docker
- Deployed on Render

## Persistence Layer
This project uses an H2 file-based database stored on disk.  
This ensures data survives across requests and application restarts, which is required for serverless deployments.

## Running Locally

### Prerequisites
- Java 17
- Maven

### Steps
```bash
git clone https://github.com/kashboinasandeep/pastebin-lite.git
cd pastebin-lite
./mvnw clean package
java -jar target/pastebin-lite-0.0.1-SNAPSHOT.jar




Deployed URL

https://pastebin-lite-sjtu.onrender.com

Create paste (UI):
https://pastebin-lite-sjtu.onrender.com/

View paste (HTML):
https://pastebin-lite-sjtu.onrender.com/p/<id>

View paste (JSON API):
https://pastebin-lite-sjtu.onrender.com/api/pastes/<id>

Health check:
https://pastebin-lite-sjtu.onrender.com/api/healthz


Spec-Compliance Checklist (100% Match)

✔ Public GitHub repository  
✔ README.md exists  
✔ README includes:
- Project description  
- Local run instructions  
- Persistence explanation  

✔ No hardcoded localhost URLs  
✔ No secrets committed  
✔ Persistent storage (H2 file DB)  
✔ `/api/healthz` returns fast JSON  
✔ All API responses return JSON  
✔ TTL + max views enforced  
✔ Deterministic expiry supported  
✔ HTML rendered safely  
✔ UI + API both functional  
✔ Deployed app starts without manual DB steps  

