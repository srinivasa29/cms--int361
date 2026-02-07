 SmartContact CRM – Contact Management System

A full-stack Spring Boot + MySQL CRM application with secure authentication and contact management features.

 Overview

SmartContact CRM is a modern, secure, and user-friendly contact management system built using Spring Boot, Spring Security, Thymeleaf, and MySQL.
It allows users to register, log in, store contacts, upload images, and manage them easily from a mobile-friendly dashboard.

This project also supports CI/CD with Jenkins, Docker-based deployment, and GitHub integration.

AI / Generative AI (Future Enhancement Scope)

SmartContact CRM is currently a rule-based full stack application.
However, the system architecture is designed to support future integration of Generative AI and LLM-based features.

Planned AI-driven enhancements include:

AI-based contact note summarization
Automatically generate short summaries from long contact descriptions using LLMs.

Smart contact categorization
Use NLP techniques to auto-tag contacts (e.g., work, personal, client, vendor) based on stored text data.

Intelligent search & recommendations
Enhance search using semantic matching instead of exact keywords, inspired by LLM behavior.

AI-assisted insights
Generate insights such as frequently contacted users or suggested follow-ups based on interaction patterns.

These enhancements will be implemented using LLM APIs or open-source NLP models in future versions, while keeping the existing Spring Boot backend intact

 Features
 User Authentication

User Registration & Login

Encrypted passwords using BCrypt

Role-based access control

Session management & logout support

 Contact Management

Add, Update, Delete contacts

Upload profile pictures

Store contact details (email, phone, work, description)

Search and sort contacts

Pagination support

 User Dashboard

Personalized dashboard for each user

View total contacts

Update profile & settings

 Cloud / Local Storage

Store images locally or configure cloud storage (AWS S3, Cloudinary)

 Admin Features (Optional)

View all users

Delete users

Manage global data

 Tech Stack
Layer	Technology
Backend	Spring Boot (3.x)
Security	Spring Security + BCrypt
View	Thymeleaf, HTML5, CSS3, Bootstrap 5
Database	MySQL 8.x
ORM	Hibernate / Spring Data JPA
Build Tool	Maven
CI/CD	Jenkins
Containerization	Docker, Docker Compose
Version Control	Git + GitHub
 Project Architecture
SmartContactCRM/
│
├── src/main/java/com/smartcontact/
│   ├── controller/       → MVC Controllers
│   ├── entity/           → JPA Entities (User, Contact)
│   ├── repository/       → JPA Repositories
│   ├── config/           → Security & App Config
│   └── service/          → Service Layer
│
├── src/main/resources/
│   ├── templates/        → Thymeleaf Views
│   ├── static/           → CSS, JS, Images
│   └── application.properties
│
└── pom.xml               → Maven configuration

 Local Setup Instructions
1️ Clone Repository
git clone https://github.com/your_username/SmartContactCRM.git
cd SmartContactCRM

2️ Configure MySQL Database

Create database:

CREATE DATABASE smart_contact;


Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/smart_contact
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3️ Run Application

Using Maven Wrapper:

./mvnw spring-boot:run


Or using Maven:

mvn spring-boot:run


Application is available at:

http://localhost:8080

 Docker Setup
1️ Build Docker Image
docker build -t smartcontact-app .

2️ Run Container
docker run -p 8080:8080 smartcontact-app

3️ Docker Compose
docker-compose up -d

CI/CD Pipeline (Jenkins)

This project includes a complete CI/CD pipeline:

✔ Git Checkout
✔ Maven Build (Skip Tests)
✔ Docker Build & Tag
✔ Docker Compose Deploy
✔ Webhook Trigger Support

Pipeline uses:

Jenkinsfile

 Screenshots (Optional)

You can add:

Login Page

Register Page

Dashboard

Add Contact

Profile Page

 Folder Structure (Short View)
SmartContactCRM/
├── src/
├── target/
├── Dockerfile
├── docker-compose.yml
├── Jenkinsfile
└── README.md

 Contributing

Fork the project

Create your feature branch

Commit changes

Push & Open a Pull Request

 License

This project is licensed under the MIT License.

 Contact

Mannepula Srinivasa
 Email: srinivasamannepula7@gmail.com

 GitHub: https://github.com/srinivasa29

