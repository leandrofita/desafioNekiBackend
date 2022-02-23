# Os dados estão sendo automaticamentes setados em um banco H2

# Os objetos salvos no banco podem ser encontrados no arquivo: DesafioNekiApplication.java 

#Endpoints:

### `login`
URL: http://localhost:8081/api/login
## Corpo da Requisição: 
{
    "login" : "cli1@gmail.com",
    "password" : "12345"
}
### `GET client`
URL: localhost:8081/clients/id
Headers:
Content-Type: application/json
Authorization: Bearer token

### `POST client`
URL: URL: localhost:8081/clients/
## Corpo da Requisição
Headers:
Content-Type: application/json
Authorization: Bearer token
{
    "login" : "teste@teste.com",
    "password" : "12345"
}

### `GET skill`
URL: http://localhost:8081/skills (todas)
URL: http://localhost:8081/skills/id (por id)
## Corpo da Requisição
Headers:
Content-Type: application/json
Authorization: Bearer token
{skillId}

### `GET client-skill`
### `DELETE client-skill`
URL: http://localhost:8081/client-skills/id (por id)
## Corpo da Requisição
Headers:
Content-Type: application/json
Authorization: Bearer token
{clientSkillId}

### `POST client-skill`
URL: http://localhost:8081/client-skills/
## Corpo da Requisição
Headers:
Content-Type: application/json
Authorization: Bearer token
{
    "idClient": 1,
    "idSkill": 3,
    "knowledgeLevel": 5
}

### `PUT client-skill`
URL: http://localhost:8081/client-skills/id
## Corpo da Requisição
Headers:
Content-Type: application/json
Authorization: Bearer token
{
    "knowledgeLevel": 5
}

#O método PUT só permite editar o atributo knowledgeLevel, por isso somente este campo é necessário no corpo da requisição.
