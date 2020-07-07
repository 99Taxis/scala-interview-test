PROBLEMA
========
Dado o projeto em scala, utilizando [PlayFramework](https://www.playframework.com/), fazer as devidas correções e melhorias no código.

Resultado esperado
------------------
* Expor o endpoint abaixo responsável por gerar uma corrida com sua devida taxa.

```
method: POST
url: /rides
body: {
    "category": String,
    "driver": String,
    "passenger": String,
    "amount": Decimal
}
```

* Exemplo de chamada

Request
```
curl --location --request POST 'localhost:9005/rides' \
--header 'Content-Type: application/json' \
--data-raw '{
    "category": "pop",
    "driver": "fulano",
    "passenger": "ciclano",
    "amount": 30.9
}'
```

Response   
```
status: 201 Created
body: {
    "category": "pop",
    "driver": "fulano",
    "passenger": "ciclano",
    "amount": 30.9,
    "fee": 3.09
}
```
