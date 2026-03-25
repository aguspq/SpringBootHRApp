# SpringBootHRApp

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.x-brightgreen?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15%2B-blue?style=for-the-badge&logo=postgresql)

**SpringBootHRApp** is a high-performance RESTful API designed for Enterprise Human Resources management. It provides a robust backend to orchestrate the complex relationships between **Departments**, **Employees**, and **Projects**.

---

## 🚀 Key Features

* **Employee Lifecycle Management:** Comprehensive CRUD operations including complex project assignments and department filtering.
* **Organizational Hierarchy:** Managed **One-to-Many** relationships between Departments and Employees.
* **Project Orchestration:** Implementation of **Many-to-Many** bidirectional relationships (Employees ↔ Projects) with JPA.
* **Professional Architecture:**
    * **DTO Pattern:** Strict separation between Data Entities and API Presentation layer.
    * **Global Exception Handling:** Centralized `@RestControllerAdvice` ensuring consistent JSON error responses.
    * **Server-side Validation:** Robust data integrity via `Jakarta Validation`.
* **Interactive API Documentation:** Full **OpenAPI 3 / Swagger UI** integration for real-time endpoint testing.

## 🛠️ Tech Stack

* **Backend:** Java 17+, Spring Boot 3 (Data JPA, Web, Validation).
* **Build Tool:** Maven.
* **Database:** PostgreSQL / MySQL compatible.
* **Documentation:** SpringDoc OpenAPI (Swagger UI).

## ⚙️ Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/SpringBootHRApp.git](https://github.com/your-username/SpringBootHRApp.git)
    ```
2.  **Database Configuration:** Update the `src/main/resources/application.properties` file with your database credentials.
3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

## 📖 API Documentation

Once the server is running, explore and test the endpoints via **Swagger UI** at:
`http://localhost:8080/swagger-ui/index.html`

---
*Developed as a high-quality portfolio piece focusing on Clean Code and SOLID principles.*
