# Java 17 버전의 OpenJDK Alpine 이미지를 사용
FROM openjdk:17-jdk-alpine AS builder

# Gradle 실행에 필요한 패키지 설치
RUN apk add --no-cache dos2unix

# 프로젝트의 주요 파일 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

#yml 파일 변경 docker안에서는 이클립스에서 사용하는거랑 다르기 때문에 분리
RUN rm src/main/resources/bootstrap.yaml
RUN mv src/main/resources/bootstrap-docker.yaml src/main/resources/bootstrap.yaml

# gradlew 실행 권한 부여 및 줄바꿈 문제 해결
RUN chmod +x ./gradlew
RUN dos2unix ./gradlew

# Gradle을 사용하여 JAR 파일 빌드
RUN ./gradlew bootJar

# 최종 실행 환경을 위한 Java 17 베이스 이미지 사용
FROM openjdk:17-jdk-alpine

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 실행 환경으로 복사
COPY --from=builder build/libs/*.jar /app/app.jar

# 컨테이너에서 사용할 포트 노출
EXPOSE 8080

# Java 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
