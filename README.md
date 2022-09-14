# docker-selenium-demo

Run selenium jobs with chrome and chromedriver in docker inspired by [Ahamedabdulrahman](https://medium.com/@ahamedabdulrahman/dockerize-selenium-java-project-and-run-selenium-scripts-within-docker-container-c2603d1bac3f)

## Usage

1. build base image with chrome and chromedriver

```shell
docker build -t xiaoqi97/selenium-102:1.0 -f ./DockerfileBase .
```

or just pull the image:

```shell
docker pull xiaoqi97/selenium-102:1.0
```

2. config the webdriver path

> see [selenium-install_drivers](https://www.selenium.dev/documentation/webdriver/getting_started/install_drivers/#3-hard-coded-location)

```java
System.setProperty("webdriver.chrome.driver", "/app/bin/chromedriver_linux64_102.0.5005.27");
```

3. build your application image

```Dockerfile
FROM xiaoqi97/selenium-102:1.0
COPY <PATH-TO-BUILD-jar> /usr/local/lib/demo.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]
```

Remember using maven to build your jar before building the image,
or just copy the [Dockerfile](./Dockerfile)

## Example

```shell
# build example app
docker-compose up --build -d

# test example
# echo some string like "中国探月未来已来"
curl http://localhost:8080/page
```

## Issue

Add arguments if your app hangs referring to [Headless chrome is not working in the docker](https://github.com/SeleniumHQ/docker-selenium/issues/520)

```java
  options.addArgument("no-sandbox")
```
