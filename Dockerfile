FROM openjdk:17
WORKDIR /app
COPY target/football-standing-0.0.1-SNAPSHOT.jar /app/football-standing.jar
ARG PROFILE=dev
ENV SPRING_PROFILES_ACTIVE=${PROFILE}
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/football-standing.jar","--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]