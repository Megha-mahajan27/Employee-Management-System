# Employee-Management-System

Developed a user-friendly Employee Management System using Java, Spring Boot, and MySQL to centralize employee records and streamline operations such as employee registration, updating, searching, and deletion.

**Features:**
- Employee registration and management
- CRUD operations (Create, Read, Update, Delete)
- RESTful API development
- MySQL database integration
- Input validation
- JWT-based authentication
- Spring Security integration
- Role-based access control

**Technologies Used:** Java, Spring Boot, Spring Security, JWT, MySQL, Hibernate, JPA, REST APIs.
Employee Management System (EMS)

Project Overview

The Employee Management System (EMS) is a Java-based web application designed to manage employee information and provide secure authentication.

This project includes Google OAuth 2.0 Authentication to allow users to log in using their Google accounts.

Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Maven
- MySQL
- Google OAuth 2.0
- JWT (JSON Web Token)
- Git & GitHub

Features

1. Google OAuth 2.0 Authentication

Integrated Google OAuth 2.0 login using Spring Security.

Key functionalities:

- Login using a Google account.
- Google authentication through OAuth 2.0.
- Retrieve user profile information.
- Access user email and profile details using Google scopes.
- Handle successful Google authentication.
- Integrate authenticated users with the EMS application.

2. Security Implementation

- Spring Security configuration.
- JWT service for token-related functionality.
- OAuth 2.0 authentication handling.
- Custom OAuth2 user service.
- Google OAuth2 success handler.

Project Structure

src/main/java/com/jsp/ems/
│
├── Configuration/
│   └── SecurityConfig.java
│
├── controller/
│   └── OAuth2Controller.java
│
├── entity/
│   └── User.java
│
├── Repository/
│   └── UserRepository.java
│
├── security/
│   ├── JwtService.java
│   └── GoogleOAuth2SuccessHandler.java
│
├── service/
│   └── CustomOAuth2UserService.java
│
└── util/

Google OAuth 2.0 Configuration

Google OAuth 2.0 is configured using Google Cloud Console.

Configuration Steps

1. Create a project in Google Cloud Console.
2. Configure the OAuth consent screen.
3. Create OAuth 2.0 Client ID credentials.
4. Configure the authorized redirect URI:

http://localhost:8080/login/oauth2/code/google

5. Configure the Client ID and Client Secret using environment variables.

application.properties

spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CLIENT_ID}

spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}

spring.security.oauth2.client.registration.google.scope=profile,email

Important: Never upload actual Client IDs or Client Secrets to a public GitHub repository.

Environment Variables

Configure these environment variables before running the application:

GOOGLE_CLIENT_ID=your-google-client-id
GOOGLE_CLIENT_SECRET=your-google-client-secret

How to Run the Project

1. Clone the repository:

git clone https://github.com/Megha-mahajan27/Employee-Management-System.git

2. Open the project in Eclipse or another Java IDE.

3. Configure the MySQL database connection.

4. Configure Google OAuth credentials as environment variables.

5. Update Maven dependencies.

6. Run the Spring Boot application.

7. Open the application in your browser.

GitHub Repository

"Employee Management System – GitHub" (https://github.com/Megha-mahajan27/Employee-Management-System)

Author

Megha Mahajan

Java Developer | Spring Boot | Spring Security | Google OAuth 2.0

---

This project demonstrates employee management application development with Google OAuth 2.0 authentication and Spring Security integration.
