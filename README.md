# Car Selling REST API
This is a Car Selling REST API built using Spring Boot, Spring JPA, Spring Security, and Spring Boot Validation with Hibernate Validator. The API allows users to perform CRUD operations on car listings, manage users, handle authentication and authorization, and generate invoices when a car is purchased. The application uses MySQL (phpMyAdmin) as the database.
## Features
> * User Management: Register new users, login, and authentication using Spring Security.
> * Car Listings: Add, update, delete, and retrieve car listings.
> * Invoice Management: Automatically generate an invoice when a user purchases a car. Admins can retrieve invoices by their ID.
> * Authentication & Authorization: Secure the API using JWT tokens.
> * CRUD Operations: Perform Create, Read, Update, and Delete operations on cars.
> * Role-Based Access Control: Different access levels for Admin, Seller and Buyer.
> * Input Validation: Validate request payloads using Spring Boot Validation with Hibernate Validator annotations.
## Technologies Used
> * Spring Boot: For building the REST API.
> * Spring JPA (Hibernate): For database interaction and ORM.
> * Spring Security: For securing the API and managing user roles and authentication.
> * Spring Boot Validation: For input validation using annotations.
> * JWT (JSON Web Tokens): For secure authentication and stateless sessions.
> * MySQL (phpMyAdmin): For managing the database.
> * Maven: For project management and dependencies.
> * GitHub: For version control.
> * UML: For Design and Modeling.
## Prerequisites
> * Java 17 (or higher)
> * Maven (for building the project)
> * MySQL Database (with phpMyAdmin)
> * Git (for version control)
## Getting Started
### Clone the repository
```bash
git clone https://github.com/H-Ryuk/Sales-Cars-App.git
cd your-repo
```
### Set up MySQL database
##### 1. Update the application.properties file with your database configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/car_sales_db?createDatabaseIfNotExist=true
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```
### Build and Run the Application
##### 1. Build the project using Maven:
```bash
mvn clean install
```
##### 2. Run the Spring Boot application:
````bash
mvn spring-boot:run
````
### Access the API
The API will be available at ``http://localhost:8080/api/v1/register``.
### Postman collection
You can use the Postman for testing the API.
## API Endpoints
> Public Endpoints 
> * ``POST /api/v1/users/register:`` Register a new user.
> * ``POST /api/v1/users/login:`` Authentication and retrieve a JWT token.
### Secured Endpoints (require JWT)
> Cars
> * ``GET /api/v1/cars:`` Retrieve all car listings.
> * ``POST /api/v1/cars:`` Create a new car listing (Admin/Seller only).
> * ``GET /api/v1/cars/{mark}:`` Retrieve a specific car by Mark.
> * ``PUT /api/v1/cars/{carId}:`` Update a car listing by ID (Admin/Seller only).
> * ``DELETE /api/v1/cars/{carId}:`` Delete a car listing by ID (Admin/Seller only).

### Validation
This project uses Spring Boot Validation with Hibernate Validator to ensure that incoming data is correctly formatted and meets the defined constraints.

### Security 
> * JWT Authentication: Users need to register and login to get a JWT token.
> * Role-Based Access Control: Admin users can create, update, and delete car listings, while regular users can only view the listings.

## Class Diagram
![Alt text](src/main/resources/static/images/CarsSalesClassDiagram.drawio.png)
## Tools
> * Lombok: User to reduce boilerplate code. 
> * Spring DevTools: for hot reloading during development. 
> * BCrypt: For password encryption.
## Contribution
Feel free to submit pull requests and contribute to this project.Please ensure your code follows the project's coding standards.

    