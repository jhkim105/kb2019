## 사용한 오픈소스

### Backend
* SpringBoot - WEB Framework
* JPA - ORM
* ActiveMQ - 검색어 카운트 저장
* H2 - Memory DB
* auth0 - JWT Token
* Swagger - API Documentation/

### Frontend
* Nextjs - React Framework
* Ant UI - UI Library
 
  
## 실행방법
### STEP 1 - 소스 내려받기 

```
git clone https://github.com/jhkim105/kb2019.git
cd kb2019
```  
    
### STEP 2 - 실행하기

빌드 없이 바로 시작하기
* Maven이 설치되어 있는 경우 mvn을 사용
* Maven이 설치되어 있지 않은 경우 소스에 포함되어 있는 명령어 사용
  * Windows - mvnw.cmd
  * Mac/Linux - mvnw 
```
./mvnw spring-boot:run
```
빌드 후 jar 파일 실행하기
```
./mvnw package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### STEP 3- 접속하기
* App: [http://localhost:8080/](http://localhost:8080/)
* Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* h2-console:[http://localhost:8080/h2-console](http://localhost:8080/h2-console) (JDBC URL:jdbc:h2:mem:demo)
