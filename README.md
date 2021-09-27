# customer-rewards
## Rewards
### create new transaction
### POST /v1/transaction
### request
```
{
    "customerId": "042028f2-2b93-4031-8c7a-acf40b5a32c8",
    "value": 120.0
}
```
### response
status: 200
```
{
    "id": "843ccabb-c2c6-4551-94df-700616cc340e",
    "state": "CREATED"
}
```
status: 404
```
{
    "timestamp": "2021-09-27T16:56:12.388+00:00",
    "status": 404,
    "error": "Not Found",
    "trace": "com.infogain.demo.exception.ResourceNotFoundException: Customer not found....",
    "message": "Customer not found.",
    "path": "/v1/transaction"
}
```
---
### get rewards points for customer
### GET /v1/customer/042028f2-2b93-4031-8c7a-acf40b5a32c8/reward
### response
status: 200
```
{
    "transactions": [
        {
            "transactionDTO": {
                "value": 189.96,
                "date": "2021-05-10T21:20:13.000+00:00",
                "customerId": "042028f2-2b93-4031-8c7a-acf40b5a32c8"
            },
            "rewardPoints": 330
        },
        {
            "transactionDTO": {
                "value": 150.38,
                "date": "2021-04-27T17:47:52.000+00:00",
                "customerId": "042028f2-2b93-4031-8c7a-acf40b5a32c8"
            },
            "rewardPoints": 200
        },
        {
            "transactionDTO": {
                "value": 193.56,
                "date": "2021-02-28T13:24:49.000+00:00",
                "customerId": "042028f2-2b93-4031-8c7a-acf40b5a32c8"
            },
            "rewardPoints": 338
        },
        {
            "transactionDTO": {
                "value": 141.38,
                "date": "2021-05-31T13:59:25.000+00:00",
                "customerId": "042028f2-2b93-4031-8c7a-acf40b5a32c8"
            },
            "rewardPoints": 182
        },
        {
            "transactionDTO": {
                "value": 120.0,
                "date": "2021-09-18T03:19:15.144+00:00",
                "customerId": "042028f2-2b93-4031-8c7a-acf40b5a32c8"
            },
            "rewardPoints": 90
        }
    ],
    "totalRewardPoints": 1140
}
```
status 404
```
{
    "timestamp": "2021-09-27T16:54:57.014+00:00",
    "status": 404,
    "error": "Not Found",
    "trace": "com.infogain.demo.exception.ResourceNotFoundException: Customer not found....",
    "message": "Customer not found.",
    "path": "/v1/customer/042028f2-2b93-4031-8c7a-acf40b5a32c8/reward"
}
```
---
---
## CRUD
#### get all customers
### ```GET /v1/customer``` 
### response
status: 200
```
[
    {
        "id": "2f919434-ed72-434a-bc57-30341a18ee66",
        "personalId": "2484833",
        "name": "Eula Tidbury",
        "transactions": [
            {
                "id": "0513caab-1562-48b4-bf97-f1a311225bc3",
                "value": 99.15,
                "date": "2021-08-11T22:39:48.000+00:00"
            },
            {
                "id": "5dd20c74-693a-41e4-9911-940f8dfc890e",
                "value": 192.56,
                "date": "2021-02-23T10:21:33.000+00:00"
            },
            {
                "id": "a9cf2dd0-772d-4f12-ab70-3cee4e47fdcf",
                "value": 102.5,
                "date": "2020-11-25T13:20:08.000+00:00"
            },
            {
                "id": "9c196e55-b13a-4316-928d-e8665617ce00",
                "value": 130.26,
                "date": "2021-06-24T10:59:08.000+00:00"
            },
            {
                "id": "3cae99a7-60a7-4484-bdec-375cbc3f0678",
                "value": 80.39,
                "date": "2021-08-20T02:50:37.000+00:00"
            },
            {
                "id": "52b8f92f-4713-4974-b2fe-2f3bab6a986b",
                "value": 73.66,
                "date": "2020-08-26T23:00:49.000+00:00"
            },
            {
                "id": "b2bb3fc5-171f-4a36-aeb8-ffe64169d090",
                "value": 31.16,
                "date": "2021-07-29T11:36:30.000+00:00"
            },
            {
                "id": "83e908ab-fa66-4fea-a233-a0c13c5bc001",
                "value": 195.81,
                "date": "2021-08-03T03:12:37.000+00:00"
            },
            {
                "id": "5bcc2ee7-35ae-417b-8f0c-cef2ef06b23f",
                "value": 27.7,
                "date": "2021-08-13T04:10:34.000+00:00"
            },
            {
                "id": "8ec71614-7910-424e-b69f-09dac175a9a8",
                "value": 12.26,
                "date": "2021-01-03T19:27:26.000+00:00"
            }
        ]
    },
    {
        "id": "98134c74-bee2-4626-aa52-043ed92dde8c",
        "personalId": "4055549",
        "name": "Lydie Mein",
        "transactions": [
            {
                "id": "b7e96e48-f389-47aa-aba4-6140fc51715c",
                "value": 107.24,
                "date": "2020-11-05T11:57:51.000+00:00"
            },
            {
                "id": "31fd67f9-5850-48b9-af6c-5bdaade1e4f5",
                "value": 92.01,
                "date": "2021-06-24T13:52:09.000+00:00"
            },
            {
                "id": "4c0b2cb3-43db-47c6-9acb-f0327c710192",
                "value": 166.94,
                "date": "2020-08-27T12:08:10.000+00:00"
            },
            {
                "id": "7a8cde99-e373-40e3-b98b-ca719e14f14c",
                "value": 182.94,
                "date": "2020-12-22T03:01:19.000+00:00"
            },
            {
                "id": "02007271-66c4-4c42-bbf2-999d58c3d394",
                "value": 63.48,
                "date": "2021-05-29T17:25:38.000+00:00"
            },
            {
                "id": "aa402d10-bf1e-4164-8a27-6bce34e242ef",
                "value": 92.2,
                "date": "2020-11-20T22:37:13.000+00:00"
            }
        ]
    }
]
```
---
#### get single customer
### ```GET /v1/customer/2f919434-ed72-434a-bc57-30341a18ee66``` 
### responses
status: 200
```
{
    "id": "2f919434-ed72-434a-bc57-30341a18ee66",
    "personalId": "2484833",
    "name": "Eula Tidbury",
    "transactions": [
        {
            "id": "0513caab-1562-48b4-bf97-f1a311225bc3",
            "value": 99.15,
            "date": "2021-08-11T22:39:48.000+00:00"
        },
        {
            "id": "5dd20c74-693a-41e4-9911-940f8dfc890e",
            "value": 192.56,
            "date": "2021-02-23T10:21:33.000+00:00"
        },
        {
            "id": "a9cf2dd0-772d-4f12-ab70-3cee4e47fdcf",
            "value": 102.5,
            "date": "2020-11-25T13:20:08.000+00:00"
        },
        {
            "id": "9c196e55-b13a-4316-928d-e8665617ce00",
            "value": 130.26,
            "date": "2021-06-24T10:59:08.000+00:00"
        },
        {
            "id": "3cae99a7-60a7-4484-bdec-375cbc3f0678",
            "value": 80.39,
            "date": "2021-08-20T02:50:37.000+00:00"
        },
        {
            "id": "52b8f92f-4713-4974-b2fe-2f3bab6a986b",
            "value": 73.66,
            "date": "2020-08-26T23:00:49.000+00:00"
        },
        {
            "id": "b2bb3fc5-171f-4a36-aeb8-ffe64169d090",
            "value": 31.16,
            "date": "2021-07-29T11:36:30.000+00:00"
        },
        {
            "id": "83e908ab-fa66-4fea-a233-a0c13c5bc001",
            "value": 195.81,
            "date": "2021-08-03T03:12:37.000+00:00"
        },
        {
            "id": "5bcc2ee7-35ae-417b-8f0c-cef2ef06b23f",
            "value": 27.7,
            "date": "2021-08-13T04:10:34.000+00:00"
        },
        {
            "id": "8ec71614-7910-424e-b69f-09dac175a9a8",
            "value": 12.26,
            "date": "2021-01-03T19:27:26.000+00:00"
        }
    ]
}
```

status: 404
```
{
    "timestamp": "2021-09-27T16:04:10.261+00:00",
    "status": 404,
    "error": "Not Found",
    "trace": "com.infogain.demo.exception.ResourceNotFoundException: Customer not found..,
    "message": "Customer not found.",
    "path": "/v1/customer/2f919434-ed72-434a-bc57-30341a18ee66"
}
```
---
#### create customer
### ```POST /v1/customer``` 
### request
```
{
    "name": "Albert Ryan",
    "personalId": "10"
}
```
### response
status: 200
```
{
    "id": "55234540-2220-4082-a786-f673d8fe22e9",
    "state": "CREATED"
}
```
---
#### update customer
### ```PUT /v1/customer/55234540-2220-4082-a786-f673d8fe22e9``` 
### request
```
{        
    "personalId": "10",
    "name": "Ryan Albert"
}
```
### responses
status: 200
```
{
    "id": "55234540-2220-4082-a786-f673d8fe22e9",
    "state": "UPDATED"
}
```
status: 404
```
{
    "timestamp": "2021-09-27T16:49:54.846+00:00",
    "status": 404,
    "error": "Not Found",
    "trace": "com.infogain.demo.exception.ResourceNotFoundException: Customer not found....",
    "message": "Customer not found.",
    "path": "/v1/customer/55234540-2220-4082-a786-f673d8fe22e9"
}
```
---
#### get all transactions
### ```GET /v1/transaction``` 
### response
status: 200
```
[
    {
        "id": "0513caab-1562-48b4-bf97-f1a311225bc3",
        "value": 99.15,
        "date": "2021-08-11T22:39:48.000+00:00"
    },
    {
        "id": "b7e96e48-f389-47aa-aba4-6140fc51715c",
        "value": 107.24,
        "date": "2020-11-05T11:57:51.000+00:00"
    },
    {
        "id": "5dd20c74-693a-41e4-9911-940f8dfc890e",
        "value": 192.56,
        "date": "2021-02-23T10:21:33.000+00:00"
    },
    {
        "id": "143068e3-06f2-4dd1-a4f9-0f225c48a803",
        "value": 24.06,
        "date": "2020-10-29T23:57:10.000+00:00"
    }
]
```
---
#### get single transaction
### ```GET /v1/transaction/0513caab-1562-48b4-bf97-f1a311225bc3``` 
### response
status: 200
```
{
    "id": "0513caab-1562-48b4-bf97-f1a311225bc3",
    "value": 99.15,
    "date": "2021-08-11T22:39:48.000+00:00"
}
```
```
{
    "timestamp": "2021-09-27T16:51:31.114+00:00",
    "status": 404,
    "error": "Not Found",
    "trace": "com.infogain.demo.exception.ResourceNotFoundException: Transaction not found....",
    "message": "Transaction not found.",
    "path": "/v1/transaction/0513caab-1562-48b4-bf97-f1a311225bc3"
}
```
---
#### UPDATE transaction
### ```put /v1/transaction/0513caab-1562-48b4-bf97-f1a311225bc3``` 
### request
```
{   
    "value": 100.15 
}
```
### response
status: 200
```
{
    "id": "0513caab-1562-48b4-bf97-f1a311225bc3",
    "state": "UPDATED"
}
```
status: 404
```
{
    "timestamp": "2021-09-27T16:52:46.883+00:00",
    "status": 404,
    "error": "Not Found",
    "trace": "com.infogain.demo.exception.ResourceNotFoundException: Transaction not found....",
    "message": "Transaction not found.",
    "path": "/v1/transaction/acee9a2a-2ec1-40ea-b946-d0d0239ee813"
}
```
---
