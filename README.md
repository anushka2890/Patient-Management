# 🏥 Patient Management System

A simple Java Swing application to manage patient records in a hospital or clinic setting, using JDBC for database operations.

## 📌 Features

- Add, update, and delete patient records  
- Search for patients by name or ID  
- View all patient details in a structured table  
- User-friendly Java Swing interface

## 🛠 Tech Stack

- Java  
- Java Swing  
- JDBC  
- MySQL  

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/anushka2890/Patient-Management.git
cd Patient-Management
```

### 2. Set Up the Database
Create a MySQL database named patientdb

Create a table patients with the following schema:

sql
```
Copy
Edit
CREATE TABLE patients (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  age INT,
  gender VARCHAR(10),
  contact VARCHAR(15),
  diagnosis VARCHAR(255)
);
```
### 3. Update Database Credentials
In your Java file where the JDBC connection is set up, update the following:

Database URL, username, and password

Example:

java
Copy
Edit
```
String url = "jdbc:mysql://localhost:3306/patientdb";
String username = "your_mysql_username";
String password = "your_mysql_password";
```
4. Run the Application
Open the project in an IDE like IntelliJ or Eclipse

Locate and run the Java file containing the main() method

The Swing-based GUI should launch
