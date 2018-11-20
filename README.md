# CVRPTW - Capacitated Vehicle Routing Problem with Time Windows

It is a micro-service to route orders minimizing overall distance. This problem is well known as Vehicle Routing Problem,
more specifically the Capacitated Vehicle Routing Problem with Time Windows with multiple Depots
, please find a reference for it at (https://en.wikipedia.org/wiki/Vehicle_routing_problem).

This micro-service provides the following endpoints with these attributes:
- Restaurants: create, update, get all and get by id
`{"id": 1, "lat" : "0.0", "lon" : "0.0"}`
- Clients: create, update, get all and get by id
`{"id": 1, "lat" : "0.0", "lon" : "0.0"}`
- Orders: Create, update, get all, get by id and query by restaurant with optional filtering by delivery time.
The pickup time is the moment when the dish is ready and the delivery time is the due time to deliver the dish.
```json
{
    "id": 1,
    "restaurantId": 1,
    "clientId": 1,
    "pickup": "2018-06-05T13:37:00Z",
    "delivery": "2018-06-05T13:54:00Z"
}
```
_* This example ilustrates the problem. Please, use the right types for the attributes._

This micro-service also provides an endpoint which returns the solution proposed following the example below.
```json
{
	"routes": [
		{
			"orders": [1, 2, 5]
		}
		{
			"orders": [3]
		}
	]
}
```
_* Same consideration. The ids are the orders that are picked and delivered in one route by a driver._

## This case considers that:

- It have limitless drivers.
	-> but it is better to minimize the number of drivers.
- A worker can do at most 3 deliveries of the same restaurant at the same time (i.e. in the same route)
- A worker can only do deliveries of a single restaurant at a time (i.e. in the same route)
- It should try to avoid delivering an order after the delivery time
	-The process will prefer routes with less late delivery time
	-The process only allows a late delivery time up to a limit (pre-defined as 20 minutes)
- It CAN'T pick up an order before the pickup time
- The driver is on the restaurant at the pickup time (only for the first pickup)
- Consider that the driver goes 0.1 units in line each 5 minute.
- Each order must be assigned to route only once
- It elaborates the algorithm to solve the Vehicle Routing Problem (do not use libraries for that)

## Pre-defined endpoints:

Create client: POST `http://localhost:8080/api/v1/client`  
Update client: PUT `http://localhost:8080/api/v1/client`  
Get all client: GET `http://localhost:8080/api/v1/client`  
Get one client: GET `http://localhost:8080/api/v1/client/{id}`  

Create restaurant: POST `http://localhost:8080/api/v1/restaurant`  
Update restaurant: PUT `http://localhost:8080/api/v1/restaurant`  
Get all restaurant: GET `http://localhost:8080/api/v1/restaurant`  
Get one restaurant: GET `http://localhost:8080/api/v1/restaurant/{id}`  

Create order: POST `http://localhost:8080/api/v1/order`  
Update order: PUT `http://localhost:8080/api/v1/order`  
Get all order: GET `http://localhost:8080/api/v1/order`  
Get one order: GET `http://localhost:8080/api/v1/order/{id}`  
Search order: GET `http://localhost:8080/api/v1/order?restaurantId={id}&deliveryAfter={date}&deliveryUntil={date}`  
_* For the search order the deliveryAfter and deliveryUntil are optional
and you can use any combination between the three params._  

Get routes: GET `http://localhost:8080/api/v1/routes`  

## Testing info

Run the app:  
```
mvn spring-boot:run  
```

Execute unit tests:  
```
mvn test  
````

Create project to a IDE (i.e. Eclipse):  
```
mvn eclipse:eclipse
```
