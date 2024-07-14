# Quiz Application
## Overview
This is a simple quiz application built using Spring Boot. The application allows users to create quizzes by selecting a category and specifying the number of questions. Users can also retrieve questions for a specific quiz.

## Features
- Create quizzes by specifying category, number of questions, and quiz title.
- Retrieve questions for a specific quiz.
- Integration with PostgreSQL for data persistence.
- RESTful APIs for creating and fetching quizzes.
  
## Technologies Used
- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok
- Maven

## Getting Started
### Clone the Repository
```
git clone https://github.com/yourusername/quiz-app.git
cd quiz-app
```

## Setup PostgreSQL Database
### 1. Create a PostgreSQL database:

```
CREATE DATABASE quizapp;
Update the application.properties file with your PostgreSQL credentials:
properties
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/quizapp
spring.datasource.username=yourusername
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

## Build and Run the Application

```
mvn clean install
mvn spring-boot:run
```
## API Endpoints
### Create a Quiz

-URL: /quiz/create
-Method: POST
-Params:
    -category: The category of the quiz (e.g., Java)
    -numQ: The number of questions
    -title: The title of the quiz
-Response:
    -201 Created if the quiz is successfully created
    -500 Internal Server Error if there is an error

Example:
```
curl -X POST "http://localhost:8080/quiz/create?category=Java&numQ=5&title=JQuiz"
```

### Get Quiz Questions
-URL: /quiz/get/{id}
-Method: GET
-Params:
    -id: The ID of the quiz
-Response:
    -200 OK with the list of questions
    -404 Not Found if the quiz does not exist

Example:
```
curl -X GET "http://localhost:8080/quiz/get/1"
```

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/example/mindmingle/
│   │       ├── controller/
│   │       │   ├── QuizController.java
│   │       ├── dao/
│   │       │   ├── QuestionDao.java
│   │       │   ├── QuizDao.java
│   │       ├── model/
│   │       │   ├── Question.java
│   │       │   ├── QuestionWrapper.java
│   │       │   ├── Quiz.java
│   │       ├── service/
│   │       │   ├── QuizService.java
│   │       │   ├── QuestionService.java
│   └── resources/
│       ├── application.properties
│       └── data.sql
└── test/
    ├── java/
    └── resources/
```




