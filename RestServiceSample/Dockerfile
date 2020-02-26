FROM adoptopenjdk/openjdk11:x86_64-ubuntu-jdk-11.0.3_7

MAINTAINER ericssonxiao <ericssonxiao@gmail.com>

RUN mkdir -p /user/share/RestServiceSample

RUN mkdir -p /user/share/RestServiceSample/bin

ADD /target/rest.war /user/share/RestServiceSample/bin/rest.war

WORKDIR /user/share/RestServiceSample

ENTRYPOINT ["/opt/java/openjdk/bin/java", "-jar", "bin/rest.war"]