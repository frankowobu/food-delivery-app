## Food Delivery App
A robust and scalable backend application for a food delivery service, built using Spring Boot. The app supports user registration, authentication, and role-based access for managing meal orders and administrative tasks. With integrated CI/CD pipelines and semantic versioning, this project is designed for efficient development and deployment.

## Key Features

- **User Management**: Signup, login, and authentication with JWT.
- **Meal Management**: CRUD operations for meals by admins.
- **Order Management**: Users can place orders, and admins can approve them.
- **Feedback System**: Collect user feedback for quality improvement.
- **Role-Based Access**: Ensures secure access for users and admins.
- **CI/CD Pipeline**: 
  - Automated builds, Docker image creation, and push to Docker Hub on merging to the main branch.
  - Configured using GitLab CI/CD for streamlined deployment processes.
- **Semantic Versioning**: 
  - Automatic version incrementing based on Conventional Commits (`feat`, `fix`, `Breaking Change`).
## Tech Stack

- **Backend:** Spring Boot, Java
- **Database:** MySQL
- **Security:** Spring Security with JWT
- **Build Tool:** Maven
- **CICD:** Gitlab

## Getting Started

1. Clone the repository: git clone https://github.com/frankowobu/food-delivery-app.git
2. cd food-delivery-app
3. mvn clean install
4. mvn spring-boot:run

## Endpoints
OPEN YOUR POSTMAN TO TEST THE API  
git clone https://github.com/frankowobu/food-delivery-postman.git  
This contains all the API requests in this project.

## Contribution
Contributions are welcome! Feel free to fork the repository and submit a pull request.

