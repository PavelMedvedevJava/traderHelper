FROM openjdk:11.0.7-jdk
LABEL maintainer="Pavel Medvedev, Ltd"

RUN apt-get clean && apt-get update -y \
    && apt-get install -y unzip

RUN apt-get install \
        git \
        mc \
        -y --fix-missing

WORKDIR /gradle
RUN curl -L https://services.gradle.org/distributions/gradle-6.8.1-bin.zip -o gradle-6.8.1-bin.zip
RUN unzip gradle-6.8.1-bin.zip
ENV GRADLE_HOME=/gradle/gradle-6.8.1
ENV PATH=$PATH:$GRADLE_HOME/bin
RUN gradle --version

RUN mkdir -p /app

ENTRYPOINT git -C /app clone https://github.com/PavelMedvedevJava/traderHelper.git

RUN cd ~ /app/traderHelper

RUN gradle build

ENV APP_JAR_FILE /app/traderHelper/build/libs/traderHelper-0.0.1-SNAPSHOT-plain.jar

ENTRYPOINT ["/bin/sh","-c","java -jar $APP_JAR_FILE"]