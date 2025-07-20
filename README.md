**Base URL**
http://localhost:8080

**Endpoints:**

**User Registration**

**Endpoint**: POST /register

**Description**: Registers a new customer

**Request Body**: {
  "name": "admin",
  "email": "admin@gmail.com",
  "password": "admin@123",
  "phoneNumber": 9282829,
  "role": "ROLE_ADMIN"
}


**Response:**

<img width="1240" height="612" alt="image" src="https://github.com/user-attachments/assets/6eb22734-e22b-4f25-9710-68f67f68e4e4" />

**Login**

**Endpoint**: POST /login

**Description**: Authenticates a user and returns a success message or token.

**Request Body**:{
  "email": "jack@gmail.com",
  "password": "jack@123"
}


**Response**: 

A jwt token is generated.

<img width="629" height="337" alt="image" src="https://github.com/user-attachments/assets/88f69a6d-1736-4c75-bd1f-d6ad2479e3e5" />


**Get Profile**

**Endpoint**: GET /profile

**Description**: Retrieves the profile details of the authenticated user.

**Authentication**: Bearer Auth

**Response**:

<img width="631" height="348" alt="image" src="https://github.com/user-attachments/assets/091f95e5-6218-49fd-8084-b636e681a124" />

<img width="629" height="357" alt="image" src="https://github.com/user-attachments/assets/1dcdc2ac-d109-476b-9d34-bf6339d45fe7" />


**Get Customer Data**

**Endpoint**: GET /customer/data

**Description**: Retrieves data specific to a customer (accessible to users with ROLE_USER).

**Authentication**: Bearer Auth

**Response**:

<img width="632" height="329" alt="image" src="https://github.com/user-attachments/assets/52466ce0-5013-4964-9efc-f4002c8ef21d" />



**Get Admin Data**

**Endpoint**: GET /admin/data

**Description**: Retrieves admin-specific data

**Authentication**: Bearer Auth

**Response**:

<img width="637" height="318" alt="image" src="https://github.com/user-attachments/assets/eca9a609-c378-4dde-a597-94111c8fbcd4" />


**Get All Customers (Admin Only)**

**Endpoint**: GET /admin/customers

**Description**: Retrieves a list of all registered customers (accessible to users with ROLE_ADMIN).

**Authentication**: Bearer Auth

**Response:**

<img width="631" height="374" alt="image" src="https://github.com/user-attachments/assets/5b3d6c05-3b33-47f4-a5e7-ff16d1595a37" />
