FROM isuper/java-oracle:jdk_8
VOLUME /tmp
ADD bootdemo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
