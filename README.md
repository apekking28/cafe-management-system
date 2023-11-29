# Cafe Management System

## Project Description

The **Cafe Management System** is a comprehensive solution for cafe owners to efficiently manage their business. It includes features such as authentication, product and category management, PDF generation for transactions, and the ability to send emails to administrators.

## Features

- **Authentication:** Secure login and signup for users and administrators.
- **Product Management:** CRUD operations for managing cafe products.
- **Category Management:** CRUD operations for organizing products into categories.
- **PDF Generation:** Automatic PDF receipts for transactions.
- **Email Notifications:** Send emails to administrators for important updates.

## Technologies Used

- **Backend:** [Spring boot](https://spring.io/projects/spring-boot)
- **Database:** [MySQL](https://www.mysql.com/)
- **PDF Generation:** [iTextPDF](https://itextpdf.com/), [Apache PDFBox](https://pdfbox.apache.org/)
- **Authentication:** [JWT](https://jwt.io)
- **Email Sending:** [Spring Boot Mail Starter](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.email)

## Dependency Management
  - Spring Boot Starter Data JPA
      Description: Spring Boot starter for working with relational databases using Java Persistence API (JPA).
  - Spring Boot Starter JDBC
      Description: Spring Boot starter for working with JDBC (Java Database Connectivity).
  - Spring Boot Starter Web
      Description: Spring Boot starter for building web applications.
  - MySQL Connector
      Description: MySQL JDBC driver for connecting to MySQL databases.
  - Project Lombok
      Description: Library to reduce boilerplate code in Java classes.
  - Spring Boot Starter Test
      Description: Spring Boot starter for testing.
  - iText PDF
      Description: Library for creating and manipulating PDF documents.
  - Apache PDFBox
      Description: Java library for working with PDF documents.
  - Guava
      Description: Google's utility library for Java, providing generic utilities for various purposes.
  - Gson
      Description: Google's JSON library for Java.
  - Spring Boot Starter Security
      Description: Spring Boot starter for securing applications using Spring Security.
  - JJWT (Java JWT)
      Description: Library for JSON Web Tokens (JWT) in Java.
  - Spring Boot Starter Mail
      Description: Spring Boot starter for sending email.

## Getting Started

### Prerequisites

- JDK 1.8
- Maven
- A running relational database compatible with JDBC (e.g., MySQL, PostgreSQL, Oracle)
- Intelej IDEA (Recommended)

### Installation

1. Clone the repository
  ```sh
   git clone git@github.com:apekking28/cafe-management-system.git
  ```
2. Install Maven dependencies
  ```sh
  mvn install
  ```
3. Edit the `application.properties` file with your MySQL credentials or your database
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/mydb
  spring.datasource.username=root
  spring.datasource.password=yourpassword
  ```
4. Run the Spring Boot application
  ```sh
  mvn spring-boot:run
  ```
5. Open your browser and visit `http://localhost:8080`

## Authors

- Ilham Firmansyah - Initial work - [apekking28](https://github.com/apekking28)

## Support

If you encounter any issues or require further assistance, you can create an issue in the repository. Make sure to describe the issue and include any relevant details like error messages or screenshots to help understand the problem better.
