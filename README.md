
# UserService
The `UserService` is a microservice of the Agripulse project.

## Overview

`UserService` is a core microservice of the Agripulse project, responsible for managing user-related operations including authentication,
user registration, password management, and role-based access control.
This service ensures secure access and management of user data,
integrating with other microservices to provide a cohesive user experience within the Agripulse ecosystem.

## Features

- **User Signup**: Allows new users to register by providing their details.
- **User Login**: Authenticates users and provides JWT tokens for secure communication.
- **User Logout**: Invalidates the JWT token to log out users.
- **Password Management**: Supports password updates and secure hashing.
- **CRUD Operations**: Provides full Create, Read, Update, and Delete operations for user management.
- **Role-Based Access Control**: Manages user roles and permissions to enforce access control.

## Technologies Used

- **Java**: The primary programming language used for developing the service.
- **Spring Boot**: Framework for building and running the microservice.
- **Spring Security**: Provides authentication and authorization features.
- **Spring Data JPA**: Handles data persistence with MySQL.
- **MySQL**: Database for storing user information and roles.
- **JUnit**: Framework for writing unit tests.
- **Mockito**: Library for creating mock objects in tests.
- **Maven**: Build automation tool used for project management.
- **IntelliJ IDEA**: Integrated Development Environment (IDE) used for development.

## Installation and Setup

### Prerequisites

- **Java 17 or later**: Ensure Java is installed and configured.
- **Maven**: Install Maven for building and running the application.
- **MySQL**: Install and set up a MySQL database.

### Steps to Set Up

1. **Clone the Repository:**
    ```sh
    git clone https://github.com/d2j1/agripulse-user-service.git
    cd agripulse-user-service
    ```

2. **Configure Database:**

    Edit the `src/main/resources/application.properties` file to configure your MySQL database connection.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/agripulse_db
    spring.datasource.username=[your_username]
    spring.datasource.password=[your_username]
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    ```
    Make sure to add your database username and password in the above properties.
   
3. **Build and Run the Application:**
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```
4. Use Postman or a similar tool to interact with the services.

   
## API Endpoints

### User Endpoints

- **Get All Users**
  - `GET /users/`
  - Retrieves a list of all registered users.

- **Get User by ID**
  - `GET /users/{id}`
  - Retrieves details of a specific user by their ID.

- **Signup**
  - `POST /users/signup`
  - Registers a new user with the provided details.

- **Update User**
  - `PUT /users/{id}`
  - Updates the details of an existing user.

- **Update Password**
  - `PUT /users/updatedPassword`
  - Updates the password for a user.

- **Delete User**
  - `DELETE /users/{id}`
  - Deletes a user from the system.

## Integration with Other Services

`UserService` integrates with the `ContentService` to manage posts and comments related to users.
It ensures that only authorized users can perform certain operations based on their roles.

## Contributing
We'd like to hear from you about your contributions to improving Agripulse. Please fork the repositories and submit pull requests with detailed explanations of your changes.

## Contact
Please open an issue in the respective repositories or contact me at [dhananjayjadhav2151@gmail.com] for any questions or suggestions.

Thank You!
