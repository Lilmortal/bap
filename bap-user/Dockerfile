FROM gradle:4.9.0-jdk8-alpine

LABEL version="1.0"
LABEL description="This runs the entire bap microservices"
LABEL maintainer="jacktan165@gmail.com"

WORKDIR /src

USER root
RUN chown -R gradle /src

COPY build.gradle /src
COPY settings.gradle /src
COPY ./bap-user /src/bap-user

# USER gradle

ENTRYPOINT ["gradle", ":bap-user:run"]