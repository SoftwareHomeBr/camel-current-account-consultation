ARG BUILD_ARGS=""
ARG APP_PATH="/app"

########################
# Maven & Dependencias #
########################
FROM artifactory.santanderbr.corp/docker-base/javase-11:2.0.1.RELEASE as dependencies

ARG USER_HOME_DIR="/root"
ARG SHA=707b1f6e390a65bde4af4cdaf2a24d45fc19a6ded00fff02e91626e3e42ceaff
ARG BASE_URL=http://artifactory.santanderbr.corp/artifactory/raw-downloads

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

ARG APP_PATH
WORKDIR $APP_PATH

USER root
COPY . $APP_PATH/
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-3.5.2-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha256sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn \
  && chown -R java:java /usr/share/maven \
  && chown -R java:java /app

#///////////////////////////
# Start Copia do workspace /
#///////////////////////////
USER java
COPY .ci/files/settings.xml $MAVEN_HOME/conf
RUN mvn -B -Dmaven.test.skip=true clean dependency:go-offline dependency:resolve \
  && rm -rf target/

#################
# BUILD/PACKAGE #
#################
FROM dependencies as build

ARG BUILD_ARGS
ARG APP_PATH
WORKDIR $APP_PATH
# Copia do projeto foi realizada no Stage DEPENDENCIES
RUN mvn -DskipTests package

####################
# TESTES UNITARIOS #
####################
FROM dependencies as unit_tests

ARG APP_PATH
WORKDIR $APP_PATH
# Copia do projeto foi realizada no Stage DEPENDENCIES
RUN mvn compile test

###############
## APLICACAO ##
###############
FROM artifactory.santanderbr.corp/docker-base/javase-11:2.0.1.RELEASE
ARG APP_PATH
ARG NAME
ARG VERSION
LABEL VENDOR=Santander
LABEL NAME=$NAME
LABEL VERSION=$VERSION
LABEL MAINTAINER="developer@santander.com.br"


USER root
# Mude para sua extensao .jar ou .war
ENV APP *.jar
ENV APP_HOME $APP_PATH

WORKDIR $APP_PATH
# Copia os artefatos gerados na Stage BUILD
COPY .ci/ ./.ci
COPY --from=build $APP_PATH/target/$APP $APP_PATH/entrypoint.sh ./
RUN chown -R java:java .
USER java

#############
#   PAAS    #
#############

COPY Dockerfile $IMAGE_SCRIPTS_HOME/Dockerfile

ENTRYPOINT [ "/bin/sh", "entrypoint.sh" ]
