FROM ubuntu:16.04

MAINTAINER rohanpandhare <faltoo51188@gmail.com>

#Update_Linux
RUN apt-get update

#Install common properties software
RUN apt-get install -y software-properties-common

#Install_Java8_Oracle
RUN \
    echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
    add-apt-repository -y ppa:webupd8team/java && \
    apt-get update && \
    apt-get install -y oracle-java8-installer && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf /var/cache/oracle-jdk8-installer

#CREATING_GLOBAL_VARIABLE
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV SCALA_VERSION 2.11.7
ENV SBT_VERSION 1.1.5

#Install_Wget_Command
RUN apt-get install wget

#Install_Scala_Using_Debian_Package_Manager
RUN mkdir /home/scala && cd /home/scala && \
    wget www.scala-lang.org/files/archive/scala-$SCALA_VERSION.deb && \
    dpkg -i scala-$SCALA_VERSION.deb && \
    rm scala-$SCALA_VERSION.deb && \
    apt-get update && apt-get install scala

#Install_SBT
RUN mkdir /home/sbt && cd /home/sbt && \
    wget https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
    dpkg -i sbt-$SBT_VERSION.deb && \
    rm sbt-$SBT_VERSION.deb && \
    apt-get update && \
    apt-get install sbt

#Exposing_Port_To_Outside_World
EXPOSE 9000

#Install_Git_and_Clone_Project_Repo
RUN apt-get install git-core -y && \
    mkdir /home/app && cd /home/app && \
    git clone https://github.com/rohanpandharemv/Play-Scala-Sample-Application.git && \
    cd /home/app/Play-Scala-Sample-Application && \
    sbt compile && sbt package
