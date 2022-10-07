FROM openjdk:11-jre-slim
LABEL maintainer="Pavel Medvedev, Ltd"

COPY build/libs/traderHelper.jar $APP_JAR_FILE

ENTRYPOINT ["java -jar $APP_JAR_FILE"]