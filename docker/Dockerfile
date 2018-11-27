FROM gradle:5.0.0-jdk8-alpine

LABEL version="1.0"
LABEL description="This runs the entire bap microservices"
LABEL maintainer="jacktan165@gmail.com"

WORKDIR /app

USER root
RUN chown -R gradle /app
USER gradle

COPY . /app

RUN gradle build