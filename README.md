In Progress [7 days (29h of work.)]
---------------

### Description
---------------
The REST API contained in this repo is a Play Framework Java application.
Play uses Akka-Http as an embedded server so there's no need to setup an external one.

### Requirements
---------------

- SBT
- Java 8
- Postgres

### Postgres Database
---------------

```jdbc:postgresql://localhost:5432/crm_db```

the user and password ```crm_user/crm_pass```

In order to setup this database, follow these steps:

1. Open a console and go to the project base directory
2. Execute as a Postgres administrator user (e.g. postgres):

```psql -U postgres -f conf/database/crm_db/db_init.sql```

### Building and Running
---------------------------------

From the root directory of the project: 

- Compile the project: ```$ sbt compile```
- Run the project: ```$ sbt run```
- The dist task builds a binary version of your application that you can deploy to a server without any dependency on SBT, 
the only thing the server needs is a Java installation. ```$ sbt dist```

### Application Design
---------------------

For the exercise, I decided to use Play Framework because of its async-by-default nature. Embracing a paradigm like this can make an application better scalable and maintainable.

The libraries I'm using are more or less well known except for "Jooq". Jooq is a database-mapping library written in Java that implements the active record pattern. It offers a very good SQL Java DSL with which to interact with the database in a safe way to prevent the typical errors that arise from executing stringly SQL queries from Java. It's well equipped with a lot of helper methods to do almost everything you can when querying a database.

### Application Directory Structure
----------------------------------

- **app/actions:** contains actions. An action is essentially a (Request[A] => Result) function that handles a request and generates a result.
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
- **conf/evolutions:** contains the SQL evolutions script to track the database schema changes.
- **project:** contains configuration files for Play Framework
- **test:** contains the unit tests

### Security
---------------

- [x] Authentication  
- [x] Authorisation 
- [x] SQL injection, Info: https://www.jooq.org/doc/3.9/manual/sql-building/bind-values/sql-injection/
- [ ] XSS prevention, Info: https://stackoverflow.com/questions/27561226/how-to-prevent-xss-in-play-framework-2
- [ ] OAuth2, Info: http://www.securesocial.ws/guide/getting-started.html 
- [x] [Play 2.6 Security Analysis](https://nvisium.com/resources/blog/2017/10/04/play-2-6-security-analysis.html)

### Continuous Deployment 
---------------

- [x] Heroku, app url: https://tranquil-taiga-57523.herokuapp.com/

### Development Environments.
---------------

- [ ] Docker, Info: https://www.scala-sbt.org/sbt-native-packager/formats/docker.html

### Examples (assuming localhost and default port 9000)
---------------

Test: ```curl -X GET \ http://localhost:9000/ \ ```

##### Authentication:

1. Login:

```
POST /api/v1/authentications HTTP/1.1
Host: localhost:9000
Content-Type: application/json
```
- Body:

```json
{
    "email": "superadmin@gmail.com",
    "password": "superadmin"
}
```
- Result:

```
{
    "uuid": "42800f3f-718e-4daf-a7b7-40c7e547b5d6",
    "token": "lqp88ahbv66c1cu0d31pbjurh8"
}
```
2. Logout:



##### Customer: (User, Admin)

1. Get full customer information

```
GET /api/v1/customers/4 HTTP/1.1
Host: localhost:9000
Content-Type: application/json
X-USER-UUID: 42800f3f-718e-4daf-a7b7-40c7e547b5d6
X-USER-TOKEN: lqp88ahbv66c1cu0d31pbjurh8

```

- Result

```
{
    "id": 4,
    "name": "Abra",
    "surname": "<script>",
    "photo": "data:image/png;base64,iVBORw........kAAAAASUVORK5CYII=",
    "createdByUser": "user",
    "created": "2018-04-08T15:14:24.761535",
    "modifiedByUser": "admin",
    "modified": "2018-04-08T19:23:12.551626"
}
```


##### User: (Admin)

1. List users.

```
GET /api/v1/users HTTP/1.1
Host: localhost:9000
Content-Type: application/json
X-USER-UUID: 42800f3f-718e-4daf-a7b7-40c7e547b5d6
X-USER-TOKEN: lqp88ahbv66c1cu0d31pbjurh8
```

- Result

```
[
    {
        "id": 4,
        "name": "change",
        "email": "change@gmail.com",
        "role": "ADMIN"
    },
    {
        "id": 2,
        "name": "user",
        "email": "user@gmail.com",
        "role": "USER"
    },
    {
        "id": 1,
        "name": "superadmin",
        "email": "superadmin@gmail.com",
        "role": "ADMIN"
    }
]
```
