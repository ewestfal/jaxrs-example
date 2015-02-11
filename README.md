A simple example of using Apache CXF and JAX-RS for REST-ful web services. Just a place to hack around, used for some training and discussion on the Kuali Rice team.

# Running the Project

You can launch the application from the command line (if you have Maven installed) or from an IDE.

To launch using maven, run the following:

```
mvn spring-boot:run
```

To run from an IDE, load the project into your IDE and set up a run configuration for the ```com.westbrain.sandbox.jaxrs.Application``` main class.

# Using the API

We will use the command line tool ```curl``` to exercise the API.

# Get group 1

```
curl -D hdr.txt http://localhost:8080/api/groups/1
cat hdr.txt
```

# Pretty print group 1

```
curl -D hdr.txt -s http://localhost:8080/api/groups/1 | jq "."
```

# Get a group that doesn't exist

```
curl -D hdr.txt http://localhost:8080/api/groups/500
cat hdr.txt
```

# Get all groups

```
curl -D hdr.txt -s http://localhost:8080/api/groups | jq "."
```

# Create a new group

```
curl -D hdr.txt -s -H "Content-Type: application/json" -d '{ "name":"KualiRiceTeam", "description":"The gang of awesome Rice developers!" }' http://localhost:8080/api/groups | jq "."
cat hdr.txt
```

# Update a group

```
curl -D hdr.txt -s http://localhost:8080/api/groups/11 | jq "."
curl -D hdr.txt -X PUT -H "Content-Type: application/json" -d '{ "id":11, "name":"Eleven!", "description":"This is the new group eleven" }' http://localhost:8080/api/groups/11
cat hdr.txt
curl -D hdr.txt -s http://localhost:8080/api/groups/11 | jq "."
```

# Delete a group

```
curl -D hdr.txt -s -X DELETE http://localhost:8080/api/groups/11
cat hdr.txt
curl -D hdr.txt http://localhost:8080/api/groups/11
cat hdr.txt
```