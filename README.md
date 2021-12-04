# VOTING SESSION

### Endpoints
1. GET - http://localhost:8080/v1/pautas - Retorna lista de pautas do MongoDB na nuvem. HTTP Status: 200 - OK.
2. GET - http://localhost:8080/v1/pautas/{id} - Retorna pauta de acordo com o id informado. HTTP Status: 200 - OK.
3. POST - http://localhost:8080/v1/pautas - Salva pauta no MongoDB e retorna pauta salva. HTTP Status: 201 - Created.
4. POST - http://localhost:8080/v1/sessoes/{idPauta} - Salva sessao no MongoDB mandando o id da pauta como path variable, retornando sessão salva. HTTP Status: 201 - Created.
5. GET - http://localhost:8080/v1/sessoes/{id} - Retorna sessao de acordo com id informado. HTTP Status: 200 - OK.
6. PATCH - http://localhost:8080/v1/sessoes/{id} - Finaliza sessão setando flag de sessaoAberta com false. Retorna String informando que sessão id informado foi encerrada. HTTP Status: 200 - OK.
7. GET - http://localhost:8080/v1/sessoes/resultado/{id} - Retorna resultado da sessao informada pelo id. HTTP Status: 200 - OK.
8. POST - http://localhost:8080/v1/votos/{idSessao} - Salva voto na sessao informada pelo path variable, e também salva no MongoDB, retornando voto salvo. HTTP Status: 201 - Created.

### Banco de Dados
Foi utilizado o banco de dados não relacional MongoDB direto na nuvem através do serviço MongoDb Atlas (https://www.mongodb.com/), banco pode ser conectado pelo link no aplication properties.

### Exceptions customizadas:
As seguintes exceptions customizadas foram implementadas:
- PautaNotFoundException - HTTP Status: 404 - Not found.
- SessaoNotFoundException - HTTP Status: 404 - Not found.
- CpfInvalidoException - HTTP Status: 400 - Bad request.
- IntegrationException - HTTP Status: 400 - Bad request.
- CpfNaoHabilitadoParaVotarException - HTTP Status: 400 - Bad request.
- SessaoEncerradaException - HTTP Status: 400 - Bad request.

### Testes Unitários
Foram implementados testes unitários.

### Exemplos de Request:
Pauta:
``` json
{
    "pauta":"Pauta Votação Estacionamento."
}
```
Sessão:
``` json
{
    "duracao": 3
}
```
Voto:
``` json
{
"cpf":"74568706084",
"voto": "NAO"
}
```
### Arquitetura
Projeto foi desenvolvido utilizando o Design Pattern Facade e Mapper.

### Swagger UI
http://localhost:8080/swagger-ui.html
