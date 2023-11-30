FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
RUN  ls -l /tmp
RUN  ls -l /home
# RUN  ./mvnw install
COPY --from=build target/*.jar app.jar
RUN  pwd
RUN  ls -l
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
