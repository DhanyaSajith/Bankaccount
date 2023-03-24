## BankAccount Backend API
This is a backend API to be used for opening a new “current account” of already existing customers.  The API is built using Java and Spring Boot.
Requirements
Java 8 , Maven ,Spring Boot
Getting Started
Clone the repository:
git clone https://github.com/DhanyaSajith/Bankaccount

Data Models
The API uses the following data models:
User
•	id (long)
•	firstName (string)
•	lastName (string)
•	balance(double)

CurrentAccount
•	id (long)
•	userId (long)
•	balance(double)

Transaction
•	id (long)
•	accountId(double)
•	amount(double)

Repository
•	UserRepository
•	CurrentAccountRepository
•	TransactionRepository

Service
•	UserService
•	CurrentAccountService
•	TransactionService

Controller
•	UserController
•	CurrentAccountService

Note:Here Controllers uses the UserService, CurrentAccountService, and TransactionService classes to handle the business logic and the @RestController and @RequestMapping annotations to define the API endpoints. The GET and POST methods handle GET and POST requests use the ResponseEntity class to return responses.
Unit testing has been done for services and controller and testing was successful.
API Endpoints
The API endpoints can be accessed at
http://localhost:8080/api/users
http://localhost:8080/api/accounts
GET /id: Retrieves User Information
POST /userId/open: Creates a new current account and retrieve the existing account for the specified user.
GET /userId/accounts: Retrieves account information and transactions.
Application is dockerized,but its not functional due to some configuration issues.
