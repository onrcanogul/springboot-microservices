#Start with a base image containing Java runtime
FROM eclipse-temurin:23-jdk

#Information around who maintains the image
MAINTAINER bankapp.com

#Add the applications's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SHAPSHOT.jar

#Execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SHAPSHOT.jar"]