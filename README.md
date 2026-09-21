# Guesthouse Customer Service
One of two services in the guesthouse booking system.
This service owns everything about customers, the other service guesthouse-booking-service owns rooms and bookings

## This service does
- Stores all customer data in its own database
- Exposes REST API for CRUD
- Before deleting customer, asks booking-service whether any active bookings exists on customer, if booking is active, delete is refused.

The booking service has no customer table. A booking only stores a customer id, and the booking service asks customer-service whether that customer exists.

The services only talk over REST and never read or write in each others databases. 

## REST API 

Runs on Port 8080

- GET /api/customers
- GET /api/customers/{id}
- POST /api/customers
- PUT /api/customers/{id}
- DELETE /api/customers/{id}

Body for POST/PUT
```json 
{"name": "Eric"} 
```

System runs from booking-service where you
```docker compose up --build``` in booking-service repo

repos need to be same level in folder

