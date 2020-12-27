FROM alpine/git as git
WORKDIR /repo
ADD https://api.github.com/repos/tronxi/chat-server/git/refs/heads/master version.json
RUN git clone https://github.com/tronxi/chat-server.git
RUN cd chat-server && git checkout master

FROM maven as builder
COPY --from="git" /repo/chat-server .
RUN mvn package spring-boot:repackage

FROM openjdk:11.0.8-slim-buster
COPY --from="builder" /target/chat-0.0.1-SNAPSHOT.jar .
CMD java -jar -Dspring.profiles.active=${profile} chat-0.0.1-SNAPSHOT.jar --chat-db=${chat_db} --spring.rabbitmq.password=${rabbit_pass} --secretToken=${secret_token} --spring.datasource.password=${db_pass}
