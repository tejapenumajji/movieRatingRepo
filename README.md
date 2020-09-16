# Spring Boot Movie Rating Project

This application is packaged as a war which has Tomcat 8 embedded.

Used H2 in-memory data base to store the data.

### Rest services end points for movie rating.

```
http://localhost:8090/api/rest/customer
http://localhost:8090/api/rest/customer?page=0&size=10
http://localhost:8090/api/rest/movie
http://localhost:8090/api/rest/movie?page=0&size=10
http://localhost:8090/api/rest/customer/{customerId}/movie/{movieId}/rate/{rating}
http://localhost:8090/api/rest/customerRating/all
http://localhost:8090/api/rest/highestRatedMovie
http://localhost:8090/api/rest/highestRating/customer
```

### Create customer

```
POST http://localhost:8090/api/rest/customer
Accept: application/json
Content-Type: application/json

{
 "fname" : "Teja",
 "lname" : "rao"
}

RESPONSE: HTTP 201 (Created)
```

### Retrieve a paginated list of customers

```
GET http://localhost:8090/api/rest/customer?page=0&size=10

Response: HTTP 200
Content: paginated list 
```

### Create movie

```
POST http://localhost:8090/api/rest/movie
Accept: application/json
Content-Type: application/json

{
"name" : "avatar"
}

RESPONSE: HTTP 201 (Created)
```

### Retrieve a paginated list of movies

```
GET http://localhost:8090/api/rest/movie?page=0&size=10

Response: HTTP 200
Content: paginated list 
```

### Customer gives rating to the movie

```
POST http://localhost:8090/api/rest/customer/{customerId}/movie/{movieId}/rate/{rating}
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 201 (Created)
```

### Retrieve a paginated list of all customer ratings

```
GET http://localhost:8090/api/rest/customerRating/all

Response: HTTP 200
Content: paginated list 
```

### Retrieve the highest rated movie on average

```
GET http://localhost:8090/api/rest/highestRatedMovie

Response: HTTP 200
Content: highest rated movie string 
eg: avatar - 4.333333333333333
```

### Retrieve highest rated customer object

```
GET http://localhost:8090/api/rest/highestRating/customer

Response: HTTP 200
Content: Customer object
eg:
{
    "id": 1,
    "fname": "Mahesh",
    "lname": null,
    "customerAverageRating": 4,
    "averageRating": 3.6666666666666665
} 
```



