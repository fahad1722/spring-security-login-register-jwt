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

<img width="1240" height="612" alt="image" src="https://github.com/user-attachments/assets/af9a1ec6-0bf7-4293-b32b-5bc50e439b94" />

**Login**

**Endpoint**: POST /login

**Description**: Authenticates a user and returns a success message or token.

**Request Body**:{
  "email": "jack@gmail.com",
  "password": "jack@123"
}


**Response**: 

A jwt token is generated.

<img width="620" height="339" alt="image" src="https://github.com/user-attachments/assets/b3a6c6e4-7bbf-4c32-a1f4-1c388b3305b9" />


**Get Profile**

**Endpoint**: GET /profile

**Description**: Retrieves the profile details of the authenticated user.

**Authentication**: Bearer Auth

**Response**:

<img width="634" height="356" alt="image" src="https://github.com/user-attachments/assets/200366ca-03c5-460e-8329-7145a39582d2" />


<img width="632" height="347" alt="image" src="https://github.com/user-attachments/assets/58cdba60-92d4-4f7e-9d82-3c490a0594fb" />


**Get Customer Data**

**Endpoint**: GET /customer/data

**Description**: Retrieves data specific to a customer (accessible to users with ROLE_USER).

**Authentication**: Bearer Auth

**Response**:

<img width="634" height="347" alt="image" src="https://github.com/user-attachments/assets/b955cac3-d944-42e7-9da3-e3a8bf8d8790" />



**Get Admin Data**

**Endpoint**: GET /admin/data

**Description**: Retrieves admin-specific data

**Authentication**: Bearer Auth

**Response**:

<img width="632" height="314" alt="image" src="https://github.com/user-attachments/assets/1aaffffd-1f84-4b3e-a8cd-dbf5485f4cef" />


**Get All Customers (Admin Only)**

**Endpoint**: GET /admin/customers

**Description**: Retrieves a list of all registered customers (accessible to users with ROLE_ADMIN).

**Authentication**: Bearer Auth

**Response:**

<img width="635" height="365" alt="image" src="https://github.com/user-attachments/assets/a4b65d8e-f9f8-46de-80c6-5b993e98cab5" />
