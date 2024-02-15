FROM openjdk:17-oracle

WORKDIR /

COPY target/interview-platform-*.jar /app.jar

ENTRYPOINT exec java $JAVA_OPTS \
    -Dfile.encoding=UTF-8 \
    -jar /app.jar
