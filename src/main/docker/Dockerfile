FROM java:8
VOLUME /tmp
ADD caching-stratagies-0.0.1-SNAPSHOT.jar caching-stratagies.jar
RUN bash -c 'touch /caching-stratagies.jar'
EXPOSE 9303
ENTRYPOINT exec java $JAVA_OPTS -jar /caching-stratagies.jar

# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/caching-stratagies.jar"]