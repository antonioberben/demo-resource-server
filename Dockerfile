FROM maven:3.3.9-jdk-8

ARG RS_SWAGGER_HOST_AND_PORT
ARG RS_SWAGGER_SCHEME

ENV RS_SWAGGER_HOST_AND_PORT=$RS_SWAGGER_HOST_AND_PORT
ENV RS_SWAGGER_SCHEME=$RS_SWAGGER_SCHEME

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY . /usr/src/app

COPY maven/settings.xml /root/.m2/settings.xml

RUN mvn package install -Pswagger-spec,documentation # <---- To get from begginig the swagger.json we need to, first, package (the swagger plugin can read it) And then install

EXPOSE 9010
EXPOSE 9011


CMD java -jar target/jp-rs.jar server config.yml
