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

### Logging

Console based logging and file logging are enabled using lombok - log4j2  
The log files can be found in `logs` directory.  

### Staging using docker-compose

Three services are defined, one each for the application, redis cache and postgres database.
docker-compose can be used to build and run all the services.

For the purpose of staging, a new Maven profile `staging` is created and different set of properties are used during staging.

```bash
mvn clean package -P staging -Dmaven.test.skip=true  
# optional for cleanup  $docker-compose rm --all # cleanup Ignore  
docker-compose build --no-cache
docker-compose up --remove-orphans --force-recreate  
```

To shut-down `docker-compose down`  

The above commands should run the three services.

```text
   Container         Repository          Tag         Image Id       Size  
--------------------------------------------------------------------------
mycrud-app        mycrud_mycrud-app   latest       8c029f2d0ee1   153.7 MB
mycrud-postgres   postgres            9.6-alpine   a42c67bd1f54   36.67 MB
mycrud-redis      redis               alpine       2673ae15c02e   32.17 MB
```
