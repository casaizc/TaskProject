# Project Tasks

This is a Spring Boot project using Java 17, Tomcat as the application server, H2 as the in-memory database, Thymeleaf for the view template, and JPA for data persistence.

## Requirements

- Java 17 installed
- Maven installed
- TomCat server installed
- Port 8082 free

## Setting

To configure the project, first clone the repository to your local machine: git clone https://github.com/casaizc/TaskProject.git

or go to the following link and download the .zip project and unzip it on your computer: https://github.com/casaizc/TaskProject

Then, navigate to the project directory: .../taskProject

## Construction and Execution

--It is mandatory to have the environment variables configured correctly, especially the Java and Maven variables, otherwise the following command will not work.

To build and run the project, use the following command:  mvn spring-boot:run  

This command will build the project and run it on the Tomcat server. --server port = 8082

## To-Do interface

In the following path you will find the interface to manage the to-do list: http://localhost:8082/api/list

## API Routes

- `/api/tasks`: Gets all tasks.
- `/api/tasks/{id}`: Gets a task by its ID.
- `/api/tasks`: Create a new task.
- `/api/tasks/{id}`: Updates an existing task by its ID.
- `/api/tasks/{id}`: Delete an existing task by its ID.

## Evidence

To run the tests, use the following command: mvn test

##Swagger

In the following path you will find the API documentation: http://localhost:8082/swagger-ui/index.html#/

##H2 DB

In the following path you will find the interface to access the embedded database: http://localhost:8082/h2-ui

-user: carlos

-password: saiz

-JDBC URL: jdbc:h2:mem:taskdb

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

## Contact

If you have any questions or suggestions, feel free to open an issue in this repository.
