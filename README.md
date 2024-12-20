# School-assignment Server programing

We will create a food delivery system which has the intention to make it easier for a client to find his or her own favorit restaurant with all information related to the clients adress, bankaccount and previous purchased dishes to make the service more proficent.

*Information about running the program (here)*

**Team members:  Madasa-cell (Adam), elisan0107 (Elias), Joakim, Fredrik**

<span style="color:red">**Step 1:**</span> **Basic planing:**
We will use spring boot and four diffrent microservices to connect to a separate database.
The image below demonstrates an early phase in our plan.
Our intention is to create a front end that recives input from a client in the form av restaurant choice, food choice.
When a new customer creates an account on our application the program will persist that information in the relevant tables using H2 (addera något mer om verktyg?).

The UML-diagram demonstrates the workflow of the code. Information enters the backend and is handled first in the high orchestrator "Order Service" which has the responsibility to distribut or retrive information related to a specific order - example: date of purchase, name of customer, restaurant of purchase, cost of purchase (Det ska komma en bättre illustration på hur OrderService hämtar och lagrar information(!)).

So "Order Service" is high up in the orchestration level and the rest of the microservices (vi kommer behöva göra om uml-diagramet så det inte står service längre om vi ska ha denna förklarings modellen) is lower which enable us to use a loose coupling and make changes to for example a specific customer and his or her payment information without affecting any other information related to that specific customer (sant/falskt?).

To the right of the UML-diagram is a description of the central tables used in our application (här måste vi fundera på vilka mer tabeller vi behöver (än så länge är det bara en representation av 1 databas, men vi ska som sagt ha många (med flera tabeller i varje!)).
![Plan](FoodDeliveryServerprograming(1).jpg)

<span style="color:red">**Step 2:**</span> **Creating databases and connecting them to our microservices:**
![Database Service Communication](database_service_communication.jpg)

Frontend **->** Fleetman (application manager) **POST**

Fleetman **->** OrderService

OrderService **->** OrderDb: **POST**

OrderService **<-** OrderDb: **GET** information about Order (kanske för att skriva ut ett kvitto?)

OrderService **<-** RestaurantService: **GET** information about Restaurant

OrderService **->** CustomerService: **GET 

<span style="color:red">**Step 3:**</span> **Connecting the microservices using a broker**

<span style="color:red">**Step 4:**</span> **Creating a front-end framework to interact with the backend**

# **Project Instructions**

## **Introduction**  
This project must be completed in **groups** and focuses on backend development.

### **Requirements**  
- [x] Use **Spring Boot** for the backend.  
- [x] Use **JPA** for persistence (database connection).  
- [x] Use **SQL** as the main database.  
- [ ] (Optional) Use **NoSQL** for additional features.  
- [ ] Create **more than one table** in your database.  
- [ ] Fetch data from all related tables for a single object or a list of objects.  
- [ ] Implement a **microservices architecture**.  
- [ ] If splitting microservices between team members:  
  - [ ] Ensure each microservice has **more than one table**.  
- [ ] Use **REST Web Services** to:  
    - [ ] Create data  
    - [ ] Update data  
    - [ ] Delete data  
    - [ ] Retrieve data  
- [ ] Choose any **frontend technology**.  
- [ ] Ensure the project is large enough for the group so that:  
    - [ ] Each member gets specific tasks.

---

## **Group Submission**  
After completing the project, the group must:  
- [ ] Submit the project repository link on **GitHub**.  
- [ ] Include a **README file** explaining all steps to run the program.  
- [ ] Conduct a **group presentation** to the class.  
- [ ] Ensure **all group members actively participate** in the presentation.

---

## **Individual Submission**  
Each group member must submit a **contribution report**.

### **Contribution Report Requirements**  
- [ ] State if you are aiming for **G** (Pass) or **VG** (Distinction).  
- [ ] Provide a **high-level overview** of your work in the project.  
    - No detailed logbook is required.  
- [ ] Include **specific links to commits** or relevant code that illustrate your contributions.  
- [ ] Limit the report to a maximum of **2 A4 pages**, excluding code.  
- [ ] Submit by **December 19, 2024**.  

---

## **Grading Criteria**  

### **Pass (G):**  
- [ ] Fulfill all technical requirements.  
- [ ] Complete the group presentation with active participation from all members.  

### **Distinction (VG):**  
In addition to meeting all requirements for Pass (G), fulfill **3 of the following**:  
- [ ] Implement and document **error handling**.  
- [ ] Use **ActiveMQ** (or similar) to send and receive data between microservices.  
- [ ] Write **unit tests** for critical classes.  
- [ ] Create a project that is **robust, polished, and well-architected** with significant effort reflected.  

**Note:** VG is graded **individually**, so some members can aim for VG while others aim for G.

---

## **Checklist for Submission**  

### Group Submission  
- [ ] GitHub repository link  
- [ ] README file  
- [ ] Group presentation with active participation  

### Individual Submission  
- [ ] Contribution report (max 2 pages)  
- [ ] Links to commits/relevant code  
- [ ] State whether aiming for G or VG  
- [ ] Submit by December 19, 2024  



