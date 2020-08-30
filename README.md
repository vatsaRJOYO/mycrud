# mycrud

My first attempt at crud app using Spring boot,  Postgres database , and Redis cache

## commands for startup

### Postgres setup

1. Start postgresql cli `$ psql`  
1. create pgsql role with username `dummy` and password:  `create user dummy with password 'passwordoyo';`  
1. create database named `samplea`:  `create database samplea;`  
1. grant privilages to newly created role:  `grant all privilages on database samplea to dummy;`

### Redis intialization

Redis is used for caching request-response.  Default timeout is set at `15000` milliseconds.

Redis for this application is run on port `12302`.  
For Redis server initialisation:  `redis-server --port 12302`  
For Redis cli use:  `redis-cli -p 12302`  

### Run maven project (dev)

To start application: `mvn spring-boot:run`
The application starts on port:  `12300`  

## Testing via postman

Test collection that can be easily imported by postman is available [here](src/main/resources/static/sampleA.postman_collection.json)  
For instructions on importing and exporting in postman, visit [here](https://kb.datamotion.com/?ht_kb=postman-instructions-for-exporting-and-importing)  
