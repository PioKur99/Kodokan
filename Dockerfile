FROM openjdk:11-jdk as build
WORKDIR /workspace/app

COPY server/mvnw .
COPY server/.mvn .mvn
COPY server/pom.xml .
COPY server/src src
RUN chmod +x server/mvnw
RUN ./mvnw clean install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:11-jdk
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","pl.kodokan.fcp.server.ServerApplication"]