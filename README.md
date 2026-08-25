# TODO
* Create a class-diagram
* Create a database-diagram

## ClassDiagram:
``` mermaid
classDiagram
    class Classroom{
    +String name
    +int capacity
    +boolean Accessible 
    
    }
    
    class Customer {
        <<abstact>>
        +parseCustomer() void
        +saveCustomer() void
        +updateCustomer() void
        }
    class Individual{
        String name
        CustomerTypes type
      
    }
    
    class Company {
        String name
        CustomerTypes type
    }
    class Booking{
        customer Customer
        classroom Classroom
        booked_from DateTime
        booked_to   DateTime
        }
        class StorageDAO{
        
        }
    class CustomerTypes{
    <<enumeration>>
    COMPANY
    INDIVDUAL
    }
 Customer <|-- Individual
 Customer <|-- Company
 Customer --> CustomerTypes : type
 Booking <|-- Customer
 Booking <|-- Classroom
```

Database Schema:
```mermaid
erDiagram
customers {
INT id PK
VARCHAR_45 name
ENUM type
}

    classroom {
        INT id PK
        VARCHAR_45 name
        TEXT capacity
        TEXT accessibility
    }

    bookings {
        INT id PK
        INT booked_by FK
        INT booked_classroom FK
        DATETIME book_start
        DATETIME book_end
    }

    customers ||--o{ bookings : "booked_by"
    classroom ||--o{ bookings : "booked_classroom"