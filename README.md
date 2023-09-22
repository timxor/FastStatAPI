# FastStatAPI
FastStatAPI


## building and compiling the app


```
unzip FastStatAPI.zip -d FastStatAPI

spring init -a FastStatAPI -l java --build maven FastStatAPI

cd FastStatAPI

mvn clean package
```


## running the app

```
ls -lb target/*.jar
java -jar target/FastStatAPI-0.0.1-SNAPSHOT.jar
```



## java, springboot and maven version

Confirm you have java and maven installed.


#### Install and set default java version to 17:

```
sdk list java
sdk install java 17.0.8-graalce
sdk use java 17.0.8-graalce 
sdk default 17.0.8-graalce
```

confirm java 17 is the current version:
```
java -version
```


#### install springboot

```
sdk install springboot
```


#### install maven

```
brew install maven
```

confirm maven is installed:

```
mvn -v
```

