# CustomerRelationshipManagement
   This application provide that create and management customer and its accounts.
# Technologies
  * JPA
  * H2
  * Web
  * Swagger2
  * Java 8
# URLs
  * DB : http://localhost:8088/crm/h2-console
  * Swagger UI : http://localhost:8088/crm/swagger-ui.html
# Endpoints
  * /customers HTTP Get # Get all customers
  * /customers HTTP Post # Create a new customer
  * /customers/{customerId} HTTP Get # Get a customer
  * /customers/{customerId} HTTP Delete # Delete a customer
  * /customers/{customerId} HTTP Put # Update an existing customer
  * /customers/{customerId}/accounts HTTP Post #Create an account for a customer
  * /customers/{customerId}/accounts HTTP Get #Get accounts from a customer
  * /customers/{customerId}/accounts/{accountId} HTTP Delete # Delete an account from a customer
  * /customers/{customerId}/accounts/{accountId} HTTP Put # Update an account from a customer
# Test
  * Postman script provided in the project source  
