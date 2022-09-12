FROM maven:3.8.6-jdk-11-slim AS build
# config maven mirrors to fix build slowly in China. Just remove it if no need.
COPY settings.xml /usr/share/maven/conf/settings.xml
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -DskipTests -f /home/app/pom.xml clean package

FROM xiaoqi97/selenium-102:1.0
COPY --from=build /home/app/target/docker-selenium-demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]