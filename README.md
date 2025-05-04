# Splitify - Backend (Java Spring Boot)
[https://splitify-java-backend.onrender.com](https://splitify-java-backend.onrender.com)

Splitify is a smart expense-splitting backend API built using Spring Boot. It allows users to create groups, split expenses with friends, track who owes whom, and settle transactions. JWT-based authentication and Spring Security are used to secure endpoints.

---

## 📚 Features

- ✅ User Signup/Login with JWT Authentication
- ✅ Secure endpoints with Spring Security
- ✅ Create and manage expenses
- ✅ Split expenses between participants
- ✅ Track balances: who owes whom
- ✅ Friend management
- ✅ Group support (optional)
- ✅ MongoDB with DBRef or manual references for users & expenses
- ✅ LLD-compliant modular structure

---

## 📌 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (JJWT 0.11.x)
- MongoDB
- Maven
- Lombok

---

## 🏗️ Project Structure

- **/src/main/java**: Contains the Java source files for the application.
- **/src/main/resources**: Contains the configuration files like `application.properties`.
- **/src/test**: Contains unit and integration tests.
- **Dockerfile**: Defines the Docker image to run the application in a containerized environment.
- **pom.xml**: The Maven project descriptor file that manages project dependencies and build lifecycle.
  
---

## 🚀 How to Run

### **Locally:**
1. Clone the repository.
2. Install the dependencies:
    ```bash
    mvn install
    ```
3. Run the application:
    ```bash
    mvn spring-boot:run
    ```
4. Open `http://localhost:9090` to access the API.

### **With Docker:**
1. Build the Docker image:
    ```bash
    docker build -t splitify-backend .
    ```
2. Run the container:
    ```bash
    docker run -p 9090:9090 splitify-backend
    ```
3. Open `http://localhost:9090` to access the API.

---

## 🔑 Authentication

This application uses **JWT (JSON Web Tokens)** for user authentication. When you log in or sign up, you'll receive a token to include in the `Authorization` header for accessing protected endpoints.

Example:
```bash
Authorization:
