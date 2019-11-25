## 사용한 오픈소스

### Backend
* SpringBoot - WEB Framework
* JPA - ORM
* ActiveMQ - 검색어 카운트 저장
* H2 - Memory DB
* auth0 - JWT Token
* Hystrix - Kakao API FailOver To Naver API
* Ehcache3
* Swagger - API Documentation

### Frontend
* Nextjs - React Framework
* Ant UI - UI Library
 
## 실행방법(jar)
### 실행 파일 다운로드  
[다운로드](https://github.com/jhkim105/kb2019/raw/master/jar/demo.jar)


### 실행하기
  
```
java -jar demo.jar
```  
  
## 실행방법(소스 실행하기)
### STEP 1 - 소스 내려받기   
```
git clone https://github.com/jhkim105/kb2019.git
cd kb2019
```  
    
### STEP 2 - 실행하기
* Maven이 설치되어 있는 경우 mvn을 사용
* Maven이 설치되어 있지 않은 경우 소스에 포함되어 있는 명령어 사용
  * Windows - mvnw.cmd
  * Mac/Linux - mvnw 
```
./mvnw spring-boot:run
```

## 접속하기
* App: [http://localhost:8080/](http://localhost:8080/)
* Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* h2-console:[http://localhost:8080/h2-console](http://localhost:8080/h2-console) (JDBC URL:jdbc:h2:mem:demo)

## 참고사항
* Cache 적용한 부분 - 도서 검색, 인기키워드
* 인키 키워드에 Cache(5초)를 적용해서, 카운트 숫자가 안맞는 것처럼 보일 수 있음.(5초 뒤에는 제대로 보임)
 