#shopping-card-cuscatlan-service
>Is responsible for keeping a record of the products selected by the customer, the quantity of each one, its price and the total purchase.We need to have the database for doing a shopping car.

#Next I will place the CURLS to use to be able to occupy the service:

```
>STEP 1: we can list all the products available from the inventory:

```curl --location --request GET 'https://fakestoreapi.com/products/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjI0Mzg3MiwiaWF0IjoxNjg2MjQwMjcyfQ.16skFr_q6Gz6J4CHaL1bbIcVoB8x1gk77OWcqAo5V5oqQoKkTRf_GeOnzRoudLubh47HhoXywPWJV4Xoj7TtvQ'
```
 
>STEP 2: we can list products by ID the available from the inventory:

```curl --location --request GET 'https://fakestoreapi.com/products/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjI0Mzg3MiwiaWF0IjoxNjg2MjQwMjcyfQ.16skFr_q6Gz6J4CHaL1bbIcVoB8x1gk77OWcqAo5V5oqQoKkTRf_GeOnzRoudLubh47HhoXywPWJV4Xoj7TtvQ'
```
 


#Products operation:
>STEP 3: we can save all products consuming api store:

``` curl --location --request POST 'localhost:2024/products/5' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxOTk3OCwiaWF0IjoxNjg2MjAxOTc4fQ.C1YHmBUq3-hTP4kAFByAOO56Vhj5RaB4DQLoFPc_3cUHGeHtMIytK4wsrndR04L9ydzZ4KXrri7aak3KSaLeLA'
```



>STEP 4: we can see all products consuming api store:

```curl --location --request GET 'localhost:2024/products' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxOTk3OCwiaWF0IjoxNjg2MjAxOTc4fQ.C1YHmBUq3-hTP4kAFByAOO56Vhj5RaB4DQLoFPc_3cUHGeHtMIytK4wsrndR04L9ydzZ4KXrri7aak3KSaLeLA'
```

>STEP 5: we can see quantity products consuming api store:

```curl --location --request GET 'localhost:2024/products/quantity' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxOTk3OCwiaWF0IjoxNjg2MjAxOTc4fQ.C1YHmBUq3-hTP4kAFByAOO56Vhj5RaB4DQLoFPc_3cUHGeHtMIytK4wsrndR04L9ydzZ4KXrri7aak3KSaLeLA'
```


>STEP 6: we can delete  products consuming api store:

```curl --location --request DELETE 'localhost:2024/products/delete/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxNjIxMCwiaWF0IjoxNjg2MTk4MjEwfQ.wQosMNibsJq-Nl_MtS27jRZ4CSC-2k81l5qkcwvfIuXK9jXJf-7tWFFZk6--HxuDCewK09tOKbo4_f4UwWGEqA'
```


#CLIENTS:  we can consult the list of clients, in this case they are users of the system

>STEP 7: we can see information of client use online token:

```curl --location --request GET 'localhost:2024/client/extract-one-information' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxMzc1MiwiaWF0IjoxNjg2MjEwMTUyfQ.sBJ7a_kFk_-CKieS51q7YAD3cWK3MgKIUOc9rT1CEQ8eZ7tMCU0GvFi53SrKNTbwmO6PgGHQkJiuA3YuO-G3Jg'
```


>STEP 8: we can see all the list  client :

```curl --location --request GET 'localhost:2024/client/list' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxMzc1MiwiaWF0IjoxNjg2MjEwMTUyfQ.sBJ7a_kFk_-CKieS51q7YAD3cWK3MgKIUOc9rT1CEQ8eZ7tMCU0GvFi53SrKNTbwmO6PgGHQkJiuA3YuO-G3Jg'
```




#DETAILS ORDER:  we can consult the list of details order, in this system

>STEP 9: we can see information of order details use online token:

```curl --location --request GET 'localhost:2024/details' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxOTk3OCwiaWF0IjoxNjg2MjAxOTc4fQ.C1YHmBUq3-hTP4kAFByAOO56Vhj5RaB4DQLoFPc_3cUHGeHtMIytK4wsrndR04L9ydzZ4KXrri7aak3KSaLeLA'
```


>STEP 10: delete details order:

```curl --location --request DELETE 'localhost:2024/details/remove/3' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxOTk3OCwiaWF0IjoxNjg2MjAxOTc4fQ.C1YHmBUq3-hTP4kAFByAOO56Vhj5RaB4DQLoFPc_3cUHGeHtMIytK4wsrndR04L9ydzZ4KXrri7aak3KSaLeLA'
```



#PAYMENTS:  we can consult the list of details order, in this system

>STEP 11: we can pay order in transaction:

```curl --location --request POST 'localhost:2024/payments/generate' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxNjIxMCwiaWF0IjoxNjg2MTk4MjEwfQ.wQosMNibsJq-Nl_MtS27jRZ4CSC-2k81l5qkcwvfIuXK9jXJf-7tWFFZk6--HxuDCewK09tOKbo4_f4UwWGEqA' \
--header 'Content-Type: application/json' \
--data-raw '{
    "type": "my order",
    "idOrder": 1,
    "amountPaid": 132.25
    
}'
```


>STEP 12: also, get information of the details order but extract import information : amount the order

```curl --location --request GET 'localhost:2024/payments/getAmountPayForOrder/6' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```






#ORDER:  we can generate order, in this system

>STEP 13: new order in transaction:

```curl --location --request POST 'localhost:2024/orders/generate' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxOTk3OCwiaWF0IjoxNjg2MjAxOTc4fQ.C1YHmBUq3-hTP4kAFByAOO56Vhj5RaB4DQLoFPc_3cUHGeHtMIytK4wsrndR04L9ydzZ4KXrri7aak3KSaLeLA'
```


>STEP 14: also, get information of the  order but extract import information : use token

```curl --location --request GET 'localhost:2024/orders/orderbytoken' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxNjIxMCwiaWF0IjoxNjg2MTk4MjEwfQ.wQosMNibsJq-Nl_MtS27jRZ4CSC-2k81l5qkcwvfIuXK9jXJf-7tWFFZk6--HxuDCewK09tOKbo4_f4UwWGEqA'
```



>STEP 15: also, get status information of the  order but extract import information : use token

```curl --location --request GET 'localhost:2024/orders/orderstatus/2' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4NjIxNjIxMCwiaWF0IjoxNjg2MTk4MjEwfQ.wQosMNibsJq-Nl_MtS27jRZ4CSC-2k81l5qkcwvfIuXK9jXJf-7tWFFZk6--HxuDCewK09tOKbo4_f4UwWGEqA'
```



>STEP 16: also,  remove information of the  order but extract import information : use token

```curl --location --request DELETE 'localhost:7001/shopping/remove/5' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjIiLCJleHAiOjE2NjgyMTE5MDksImlhdCI6MTY2ODE5MzkwOX0.HW39Ivrn-gAzeK9tE3Fsw3ljqNq6duh-Y-48HBr0CT0y9dqWk5ZEzobio6l6PwCls26xBOn6h9f5WbaUowaZsA'
```
