FROM java:8
VOLUME /tmp
ADD ./target/service-auth.jar /app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app.jar"]