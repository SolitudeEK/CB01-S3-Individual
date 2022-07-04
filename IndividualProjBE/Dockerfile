FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=IndividualProjBE/build/libs/IndividualProject-2.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]