# Nexos CrediBanco

## Base de datos

* Postgresql
* Cloud SQL Studio

## GCP
https://nexoscredibanco.uc.r.appspot.com

## Swagger
https://nexoscredibanco.uc.r.appspot.com/swagger-ui/index.html

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/GenerateCardNumberS.PNG?raw=true)

## API
### 1. Generar número de tarjeta
#### Tipo de método: GET
#### Recurso: /card/{productId}/number
#### https://nexoscredibanco.uc.r.appspot.com/card/1/number

##### Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/GenerateCardNumberS.PNG?raw=true)

##### Error
![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/GenerateCardNumberF.PNG?raw=true)

### 2. Activar tarjeta
#### Tipo de método: POST
#### Recurso: /card/enroll
#### https://nexoscredibanco.uc.r.appspot.com/card/enroll

{
 "cardId": "5541457622216407"
}

##### Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/ActivarS.PNG?raw=true)

##### Error
![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/ActivarF.PNG?raw=true)

### 3. Bloquear tarjeta
#### Tipo de método: DELETE
#### Recurso: /card/{cardId}
#### https://nexoscredibanco.uc.r.appspot.com/card/5

##### Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/BloquearS.PNG?raw=true)

##### Error
![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/BloquearF.PNG?raw=true)

### 4. Recargar saldo
#### Tipo de método: POST
#### Recurso: /card/balance
#### https://nexoscredibanco.uc.r.appspot.com/card/balance

{
 "cardId": "5953609808554225",
 "balance": "10000"
}

##### Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/AdicionarSaldoS.PNG?raw=true)

##### Error
![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/AdicionarSaldoF.PNG?raw=true)

### 5. Consulta de saldo
#### Tipo de método: GET
#### Recurso: /card/balance/{cardId}
#### https://nexoscredibanco.uc.r.appspot.com/card/balance/6

##### Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/BalanceS.PNG?raw=true)

##### Error
![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/BalanceF.PNG?raw=true)

### 6. Transacción de compra
#### Tipo de método: POST
#### Recurso: /transaction/purchase
#### https://nexoscredibanco.uc.r.appspot.com/transaction/purchase

{
    "cardId": 5953609808554225,
    "price": 10000.0
}

##### Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/PurchaseS.PNG?raw=true)

##### Error
![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/PurchaseF.PNG?raw=true)

##### Error
![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/PurchaseF2.PNG?raw=true)

### 7. Consultar transacción
#### Tipo de método: GET
#### Recurso: /transaction/{transactionId}
#### https://nexoscredibanco.uc.r.appspot.com/transaction/4

##### Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/TransactionS.PNG?raw=true)

##### Error
![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/TransactionF.PNG?raw=true)

### 8. Anular transacción
#### Tipo de método: POST
#### Recurso: /transaction/anulation
#### https://nexoscredibanco.uc.r.appspot.com/transaction/annulation

{
 "cardId": 5953609808554225,
 "transactionId": 4
}

##### Salida Exitosa

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/AnnulationS.PNG?raw=true)

##### Error
![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/AnnulationF.PNG?raw=true)

##### Error
![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/AnnulationF2.PNG?raw=true)

## Test
### CardControllerTest

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/CardControllerTest.PNG?raw=true)

### TransactionControllerTest

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/TransactionControllerTest.PNG?raw=true)

### CardServiceTest

![Exitoso](https://github.com/javf1016/Images/blob/main/Nexos/CardServiceTest.PNG?raw=true)
