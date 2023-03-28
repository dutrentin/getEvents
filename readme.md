Prova de Conceito WPlex
=================

Aplicação desenvolvida conforme documentação enviada
-------------------
**Configurações de aplicação:**
- As configurações de aplicação estão descritas no arquivo application.properties que pode ser encontrado no path src.main.resources
- Na arquivo application.properties é possível definiar a quantidade limite de distância para que a busca retorna os eventos. O valor atual default está como 50, conforme solicitado em especificação.

**Codificação:**
- Na classe de teste EventReadServiceImplTest foram desenvolvidos alguns testes simples utilizando Spring Test.
- Como disponibilização futura para integração, foi criada a classe EventController com método get passando os parâmetros da latitude e longitude e limite de distância.

**Exemplo de chamada REST**:
http://localhost:8080/events-api/events/list?latitude=-23.70041&longitude=-46.53713&limitDistance=50)

- A classe EventReadServiceImpl é responsável pela leitura da planilha que está contida na pasta resources da aplicação (resouces/eventlog.csv).
- No retorno do console está sendo apresentado os seguintes valores: Código do dispositivo, distância do evento até o ponto informado, data/hora do evento em formato ISO8601 e o payload do evento.

**Rodando a aplicação: **
- Ao subir a aplicação será apresentada a mensagem "digite:" no console para que seja inserido o comando ./csv-search --location -23.70041,-46.53713
- O sistema irá retornar ordenadamente





