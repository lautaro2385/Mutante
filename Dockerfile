FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-DAWS_DYNAMODB_ENDPOINT=http://localstack:5669","-DAWS_ACCESSKEY=id","-DAWS_SECRETKEY=key","-jar","/app.jar"]