# 💳 Digital Banking Backend (Spring Boot)

A production-style backend system simulating core banking operations such as fund transfers, transaction history, and idempotent APIs. Designed with clean architecture, transactional consistency, and real-world backend practices.

---

## 🚀 Features

* 💰 **Fund Transfer API** with transactional consistency (`@Transactional`)
* 🔁 **Idempotency Support** using unique `referenceId` to prevent duplicate transactions
* 📜 **Transaction History API** for audit and tracking
* ✅ **DTO Validation** using Jakarta Validation
* ⚠️ **Global Exception Handling** with structured error responses
* 📦 **Standard API Response Wrapper** for consistent responses
* 📊 **Swagger/OpenAPI Documentation** for easy API testing
* 🧾 **Logging** for traceability of financial operations

---

## 🏗️ Tech Stack

* **Java 8+**
* **Spring Boot**
* **Spring Data JPA**
* **MySQL**
* **Hibernate**
* **Swagger (SpringDoc OpenAPI)**

---

## 📁 Project Structure

```
com.bank.app
│
├── controller        # REST Controllers
├── service           # Business Logic
├── repository        # JPA Repositories
├── entity            # Database Entities
├── dto               # Request & Response Models
├── exception         # Custom Exceptions & Handler
└── config            # Configurations (if any)
```

---

## 🔧 Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/your-username/banking-backend.git
cd banking-backend
```

---

### 2. Configure Database (MySQL)

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bankdb
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3. Run the Application

```bash
mvn spring-boot:run
```

App runs on:

```
http://localhost:8080
```

---

## 📌 API Endpoints

### 🔁 Transfer Money

**POST** `/api/transfer`

#### Request Body:

```json
{
  "fromAccount": "ACC1",
  "toAccount": "ACC2",
  "amount": 1000,
  "referenceId": "txn-123"
}
```

#### Response:

```json
{
  "status": "SUCCESS",
  "message": "Transfer completed",
  "data": {
    "transactionId": 1
  }
}
```

---

### 📜 Get Transaction History

**GET** `/api/transactions/{accountNumber}`

#### Response:

```json
{
  "status": "SUCCESS",
  "message": "Transaction history fetched",
  "data": [
    {
      "fromAccount": "ACC1",
      "toAccount": "ACC2",
      "amount": 1000,
      "timestamp": "2026-04-12T22:00"
    }
  ]
}
```

---

## 🔍 Swagger API Docs

Access interactive API documentation:

```
http://localhost:8080/swagger-ui.html
```

---

## 🧠 Key Concepts Implemented

### 🔁 Idempotency

* Ensures duplicate requests (same `referenceId`) are processed only once
* Prevents double debit in case of retries or network issues

---

### 💾 Transaction Management

* Uses `@Transactional` to ensure atomicity
* Either all DB operations succeed or fail together

---

### 📦 Standard API Response

* Unified structure for all APIs:

```json
{
  "status": "SUCCESS / FAILED",
  "message": "Description",
  "data": {}
}
```

---

## 🚀 Future Improvements

* 🔐 Add authentication & authorization (JWT)
* ⚡ Add optimistic locking for concurrency handling
* 📊 Add pagination for transaction history
* ☁️ Deploy on AWS (EC2 + RDS)

---

## 👨‍💻 Author

**Durgesh Kulkarni**
Backend Engineer | Java | Spring Boot

---

## ⭐ Why this project?

This project demonstrates **real-world backend design principles** used in banking systems, focusing on:

* Data consistency
* Safe transaction handling
* Clean API design
* Production-ready practices

---
