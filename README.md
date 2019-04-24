# Conference Management System
### **_The student project created for JAVA courses_**


#### TASK
Conference system. The **moderator** creates  **conferences** and fill them with **lectures**. 
**Moderator** could approve, offer and change **lecture** title, also he could edit **conference's** place, date and time.
It is necessary to consider the possibility of viewing past/ongoing conferences.
Each **speaker** has rating, on which depends bonuses. **Speaker** could offer own lecture.  
Must be organized statistics of registered people and how many actually came to the conference. 
Must be implemented participants notifying of upcoming events.

#### REQUIREMENTS FOR IMPLEMENTATION OF THE PROJECT
1. On the basis of the entities create their classes.
2. Classes and methods must have proper functional names and must be properly structured by package.
3. Information must be stored in a database (MySQL recommended), for access use JDBC API with connection pool.
4. The application must support working with Cyrillic (be multilingual), including storing information in the database.
5. The code must be documented.
6. The application must be covered by unit tests.
7. When developing business logic, use sessions and filters, and events in the system should be handle using Log4j.
8. The application must implement Pagination, Transaction if required.
9. Using servlets and JSPs to implement the functionality proposed in the formulation of a specific problem.
10. Use JSTL library in JSP pages.
11. The application must respond correctly to all sorts of errors and exceptions. (User should never see stack trace on front-end side).
12. The application must have an authorization and authentication systems.

####Instructions
Before building be sure that project structure is following:

-out

-src

--main

---java (marked as Sources Root)

---resources (marked as Resources Root)

--test

---java (marked as Test Sources Root)

---resources (marked as Test Resources Root)

-target

-web (marked as WEB Sources Root)

To build the project execute the command: mvn package

To deploy project you must have servlet container (Apach Tomcat is preferred)