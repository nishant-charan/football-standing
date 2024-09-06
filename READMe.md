# Football-Standing-Service

## Steps to run.

### 1. Extract Project from Zip
    `football-standing.zip`

### 2. Go to project directory
    `cd football-standing`

### 3.Build this Project using below command
    `mvnw clean install`

### 4. To ignore test cases while build use command
    `mvnw clean install -Dmaven.test.skip=true`

### 5. Run Docker build to build a docker image of this project
    `docker build -f Dockerfile -t football-standing .`

### 6. Use docker run to run this project on 8080 port (make sure no other app is running on the 8080 port)
    `docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=dev" football-standing`

**Note/Assumptions:**
1. This application needs `DATA_SOURCE_API_KEY` which is set in application-dev.properties for `dev` profile.
2. On Production `DATA_SOURCE_API_KEY` would be handled by another secret manager service like AWS Secrets Manager.
3. This application has 2 profiles `dev` and `prod`.
4. To access the api-docs => `http://localhost:8080/api-docs`
5. To access the swagger-ui => `http://localhost:8080/swagger-ui/index.html`