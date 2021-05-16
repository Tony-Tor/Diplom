# Diploma project for TopJava "Voting for a restaurant"
"Vote for a restaurant" is a Rest application that allows you to store information about restaurants and their menus. Users can vote for the restaurant they like and view the vote statistics.
## API
Crud commands for the application  
Login: admin  
Password: password  
Base64: YWRtaW46cGFzc3dvcmQ=  

+ User Role

| Description | Сommand |
|----------------|---------|
|Getting all the dishes| curl --location --request GET 'http://localhost:8080/rest/meal/all' --header 'Content-Type: application/json' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ=' |
|Get all the Restaurant| curl --location --request GET 'http://localhost:8080/rest/restaurant/all' --header 'Content-Type: application/json' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ='|
|Voting for a restaurant|curl --location --request POST 'http://localhost:8080/rest/restaurant/vote/1' --header 'Content-Type: application/json' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ='|
|Get statistics|curl --location --request GET 'http://localhost:8080/rest/vote/statistics' --header 'Content-Type: application/json' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ='|
+ Admin Role

| Description | Сommand |
|----------------|---------|
|Create a dish|curl --location --request POST 'http://localhost:8080/rest/meal' --header 'Content-Type: application/json' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ=' --data-raw '{"name": "bread", "description": "tasty crusty bread", "price": 200}'|
|Update Dish|curl --location --request PUT 'http://localhost:8080/rest/meal/5' --header 'Content-Type: application/json' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ=' --data-raw '{"name": "bread", "description": "delicious crusty bread", "price": 200}'|
|Delete Dish|curl --location --request DELETE 'http://localhost:8080/rest/meal/5' --header 'Content-Type: application/json' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ='|
|Delete a restaurant menu|curl --location --request DELETE 'http://localhost:8080/rest/restaurant/1/menu' --header 'Content-Type: application/json' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ='|
|Assign a dish to a restaurant|curl --location --request POST 'http://localhost:8080/rest/restaurant/1/menu/5' --header 'Content-Type: application/json' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ='|
|Delete voiting|curl --location --request DELETE 'http://localhost:8080/rest/vote' --header 'Content-Type: application/json' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ='|
