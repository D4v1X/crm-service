In Progress
-----------
### Description
---------------
The REST API contained in this repo is a Play Framework Java application. 
Play uses Akka-Http as an embedded server so there's no need to setup an external one.

### Requirements
----------
SBT
Java 8
Postgres

### Postgres database
--------------------------------

### Building and running
---------------------------------
From the root directory of the project, 
you can compile the project:

```$ sbt compile```

Generate JOOQ objects:

```sbt genJooqModel ``` 

Run the project:

```$ sbt run```

The dist task builds a binary version of your application that you can deploy to a server without any dependency on SBT, 
the only thing the server needs is a Java installation.

```$ sbt dist```

### Application design
------------------

### Application Directory Structure
----------------------------------
- **app/actions:** contains the action acts as decorator for the action method call.
- **app/annotations:** contains the custom annotations.
- **app/controllers:** contains the different controllers serving the responses to the requests
- **app/enums:**
- **app/exceptions:** contains a few exceptions used throughout the application
- **app/filters:** 
- **app/jooq:** contains the Jooq objects created from the initial SQL script and that map objects in the database
- **app/model:** contains the application business model
- **app/repositories:** contains the repositories used to access the database
- **app/services:** contains the services implementing the business logic
- **app/utils:** 
  - contains some utility classes to help implementing the business logic
  - contains general error handling
  - contains the dependency injection config, which is Guice, has to work
- **logs:** contains the application log
- **conf:** contains the configuration files for Play Framework and Jooq. One very important file in this directory is "routes".
- **conf/database:** contains the SQL script with which to initialize the database.
- **project:** contains configuration files for Play Framework
- **sql:** contains the SQL script with which to initialize the database
- **test:** contains the unit tests

### Security
--------
- [x] Authentication  
- [x] Authorisation 
- [x] SQL injection
- [ ] XSS prevention, Info: https://stackoverflow.com/questions/27561226/how-to-prevent-xss-in-play-framework-2
- [ ] OAuth2, Info: http://www.securesocial.ws/guide/getting-started.html 

### Continuous Deployment 
--------------------
- [ ] AWS, Info: https://www.playframework.com/documentation/2.6.x/Deploying-Boxfuse
- [ ] Heroku, Info: https://www.playframework.com/documentation/2.6.x/ProductionHeroku

### Development Environments.
---------------------------------
- [ ] Docker, Info: https://www.scala-sbt.org/sbt-native-packager/formats/docker.html

### Examples of GET requests (assuming localhost and default port 9000)
-------------------------------------------------------------------

Test:

```curl -X GET \ http://localhost:9000/ \ ```

Login:

```
curl -X POST \
  http://localhost:9000/api/v1/users \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"name": "superadmin",
	"email": "superadmin@gmail.com",
	"password" : "superadmin",
	"role": "ADMIN"
}'
```

```
curl -X GET \
  http://localhost:9000/api/v1/users \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
```
