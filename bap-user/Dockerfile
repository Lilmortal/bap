FROM gradle:4.9.0-jdk8-alpine

LABEL version="1.0"
LABEL description="This runs the entire bap microservices"
LABEL maintainer="jacktan165@gmail.com"

ENV GRADLE_USER_HOME /tmp

WORKDIR /src

COPY build.gradle /src
COPY settings.gradle /src
COPY ./bap-user /src/bap-user

USER root
RUN chown -R gradle /src
USER gradle

ENTRYPOINT ["gradle", ":bap-user:run"]