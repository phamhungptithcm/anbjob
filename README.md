
This is a simple project I created to apply to NAB company with a modest time that I personally arrange to do this project is 15 hours, it is only a small part that the developer has to do in the actual tasks in the future. Anyway it also shows how:
 * How do I manage and break large tasks into subtasks....
 * I organize the code (Running flow, how to handle calls of functions...) is clear or not so that it can be easily managed as well as for the maintenance person to follow easily.

1. ERD
![image](https://user-images.githubusercontent.com/32831453/119690582-038d2e80-be74-11eb-9912-e4171f8bcceb.png)

2. Break task and note status for them
![image](https://user-images.githubusercontent.com/32831453/119691189-9201b000-be74-11eb-9e39-2628f94d49bc.png)

3. Prerequisites
Before you continue, ensure you meet the following requirements:
  * You have installed:
    + Java 8
    + Latest version of maven
    + Postgres 12.7
    + DBever 21.0.5 (setup with username/password is postgres/postgres)
  * You need to run script sql: 
    + Open DBever and create a new database with name nabjob
    + Restore with file script in project that you cloned
  * You are using eclipse to run app or run app with cmd:
    + cd project
    + mvn clean install
    + java -jar <file.jar in target folder>
  * You can go to this link http://localhost:8090/swagger-ui.html#/ to access and test all API in app
  
4. Screen shot all API

![image](https://user-images.githubusercontent.com/32831453/119854696-7dd4b600-bf3b-11eb-9f20-985b57abb85f.png)
