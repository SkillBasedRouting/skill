# Skill Service

holds all skills and shapes.
can hold users and groups too or acts as a layer to ldap or any other user federation

## Test & Build

run 
```bash
mvn clean package
docker build -t skillservice:beta .
```

or simply type
```bash
sh build.sh
```

## Run

run
```bash
docker run -d --rm -p "8080:8080" -v routing_skillservice:/persistence skillservice:beta
```

or simple type
```bash
sh run.sh
```

## Access

run
```bash
curl -G http://localhost:8080/api/v1/users
curl -G http://localhost:8080/api/v1/groups
curl -G http://localhost:8080/api/v1/skills
```

the response should look like this:
```json
[]
```

## Enable Security

1. make sure to have a running keycloak instance in the docker network which is accessable under the dns name 'keycloak'
2. export keycloak.json and replace it with the existing one under /resources/webapp/WEB-INF
3. uncomment security section in /resources/project-defaults.yml

