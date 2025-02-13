FROM eclipse-temurin:23-noble AS compiler

ARG COMPILE_DIR=/code_folder
WORKDIR ${COMPILE_DIR}

COPY mvnw .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true

FROM eclipse-temurin:23-jre-noble

ARG DEPLOY_DIR=/app
WORKDIR ${DEPLOY_DIR}

COPY --from=compiler /code_folder/target/movies-0.0.1-SNAPSHOT.jar movies.jar
COPY --from=compiler /code_folder/src/main/resources/static/movies_post_2010.zip .

ENV PORT=4000
EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar movies.jar