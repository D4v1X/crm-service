In Progress
----------------------
From the root directory of the project, 
you can compile the project:

```$ sbt compile```

Run the project:

```$ sbt run```

Package the project:

```$ sbt package```

--------------
Logs:

```/logs/application.log```

Test:

```curl -X GET \ http://localhost:9000/ \ ```
     
Generate JOOQ objects:

```sbt genJooqModel ``` 