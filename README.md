# poc-bank-api-java

`Project creation date: 2017`

Application that simulates a bank api, allowing the registration of customers, and the operations of deposits, withdrawals, transfers, balance inquiries and account statements.

## Business rules

- The account balance can't be negative;
- It is not possible to make a withdrawal or transfer when the account balance is insufficient;
- Accounts involved in any operation must be valid;
- The customer can only have one account (validate by CPF for example);
- The account Id for future transactions must be included in the creation request response;
- An extract should return all account movements (transfers, deposits and withdrawals);
- It is not possible to make a transfer to yourself (the source account cannot be the same as the destination account);

## Run the API locally

### Premisses

- Java JDK
- Maven
- Docker
- Docker-Compose
- PostgreSQL

After installing the dependencies through the terminal in the project's root folder, execute the following command:

```bash
mvn clean install
```

Then type the instruction below to allocate the container with the database:

```bash
docker-compose up
```

You should now be able to start the application in the IDE. It will be possible to test the application at: ```localhost:8080/```

## UML Diagram

![UML](docs/UML-bank-api.png)

### Services

#### "/client"

Service | Http Method | Address | Parameters
------------ | ------------  | ------------- | -------------
New Client Account | POST |localhost:8080/client/create | {"name": "Guillaume Falourd", "cpf": "111.111.111-11"}
Get Client Account | GET |localhost:8080/client/{accountId} |
Update Cliente Account | PUT | localhost:8080/client/update/{accountId} | {"name": "Guillaume Falourd", "cpf": "111.111.111-11"}

#### "/operation"

Service | Http Method | Address | Parameters
------------ | ------------ | ------------- | -------------
Balance | GET | localhost:8080/operation/balance/{accountId} |
Deposit | POST | localhost:8080/operation/deposit | {"accountId": 1,"value": 500}
Cashout | POST | localhost:8080/operation/cashout | {"accountId": 2,"value": 140}
Transfer | POST | localhost:8080/operation/transfer | {"depositAccountid": 1, "recipientAccountid": 2, "value": 50.00}
Extract | GET | localhost:8080/operation/accountStatement/{accountId} |
