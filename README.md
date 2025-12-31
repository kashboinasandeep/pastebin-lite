# Pastebin Lite

Pastebin Lite is a lightweight Pastebin-like web application that allows users to create text pastes and share a unique URL to view them. Pastes can optionally expire based on time (TTL) or a maximum view-count limit, after which they become unavailable.

This project is evaluated using automated tests.

---

## Features

- Create text pastes with arbitrary content
- Receive a shareable URL for each paste
- View pastes via REST API or HTML page
- Optional constraints:
  - Time-based expiry (TTL)
  - View-count limit
- Deterministic expiry testing support
- Persistent database storage
- Simple UI for creating and viewing pastes

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Lombok
- HTML / JavaScript

---

## 🗄 Persistence Layer

**MySQL** is used as the persistence layer.

**Why MySQL?**
- Data persists across requests and restarts
- Works reliably in automated and concurrent test environments
- No in-memory-only storage is used

---

## ▶️ How to Run the Code Locally

### Prerequisites

- Java 17 or later
- Maven
- MySQL Server

---

git clone https://github.com/kashboinasandeep/pastebin-lite.git
cd pastebin-lite

spring.datasource.url=jdbc:mysql://localhost:3306/paste
spring.datasource.username=root
spring.datasource.password=root

**Postman:**

GET:   http://localhost:8080/api/healthz

POST:  http://localhost:8080/api/pastes
{
  "content": "Hello world",
  "ttl_seconds": 60,
  "max_views": 3
}

GET:   http://localhost:8080/p/{id}






