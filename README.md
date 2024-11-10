User API - AWS Lambda con DynamoDB
Questo progetto implementa una semplice API per gestire l'entità User su AWS Lambda, utilizzando DynamoDB come database e il linguaggio Java con Spring Boot per la logica dell'applicazione.

Descrizione
La User API è progettata per gestire operazioni CRUD (creazione, lettura, aggiornamento, eliminazione) su un'entità User, salvata in una tabella DynamoDB. La funzione Lambda è integrata con API Gateway per esporre le API come endpoint HTTP. L'applicazione utilizza la email come chiave primaria per l'entità User, e include i campi password, nome, e cognome.

Struttura del Progetto
src/main/java/it/test/demo
model/User: Definisce l'entità User con i campi email, password, nome, e cognome.
repository/UserRepository: Interfaccia per interagire con DynamoDB utilizzando l'SDK AWS.
service/UserService: Contiene la logica di business per le operazioni sugli utenti.
config/DynamoDbConfig: Configura il client DynamoDB e l'integrazione con il repository.
lambda/UserLambdaHandler: Gestisce le richieste Lambda per operazioni CRUD su User.
Prerequisiti
Java: Versione 21 o compatibile con AWS Lambda (verificare la versione supportata).
AWS Account: Per creare e configurare le risorse AWS (Lambda, DynamoDB).
AWS CLI: Configurato con un profilo avente permessi per Lambda e DynamoDB.
Maven: Per la gestione delle dipendenze e la creazione del pacchetto del progetto.
Configurazione
1. Configurazione DynamoDB
Assicurarsi che esista una tabella DynamoDB chiamata user con email come chiave primaria. La tabella verrà popolata automaticamente tramite le operazioni CRUD.

2. Variabili d'Ambiente AWS
Assicurarsi di avere configurate le variabili d'ambiente necessarie:

AWS_ACCESS_KEY_ID: Chiave di accesso AWS.
AWS_SECRET_ACCESS_KEY: Chiave segreta di accesso AWS.
AWS_REGION: Regione in cui si trovano le risorse (es. us-east-1).
3. Compilazione del Progetto
Nella directory principale del progetto, eseguire:

bash
Copia codice
mvn clean install
Questo comando genererà il file .jar da caricare su Lambda.

Distribuzione su AWS Lambda
Creazione del ruolo IAM: Creare un ruolo con permessi:

AWSLambdaBasicExecutionRole: Per consentire alla funzione Lambda di scrivere log su CloudWatch.
AmazonDynamoDBFullAccess: Per consentire alla funzione Lambda di accedere a DynamoDB.
Caricamento del file .jar su Lambda: Caricare il file .jar generato su AWS Lambda come pacchetto di distribuzione.

Configurazione Lambda:

Impostare il ruolo IAM creato come ruolo di esecuzione della funzione Lambda.
Configurare l'handler per puntare a it.test.demo.lambda.UserLambdaHandler::handleRequest.
Test della Funzione Lambda: Utilizzare AWS Lambda Console o Postman per testare gli endpoint.

Esempi di Utilizzo
Creazione di un Utente:

css
Copia codice
POST /user
Body: { "email": "email@example.com", "password": "password", "nome": "Nome", "cognome": "Cognome" }
Lettura di un Utente:

sql
Copia codice
GET /user/{email}
Aggiornamento di un Utente:

css
Copia codice
PUT /user
Body: { "email": "email@example.com", "password": "newpassword", "nome": "NuovoNome", "cognome": "NuovoCognome" }
Eliminazione di un Utente:

sql
Copia codice
DELETE /user/{email}
Test e Debugging
Utilizzare AWS CloudWatch per il monitoraggio dei log della funzione Lambda, utile per il debugging.
