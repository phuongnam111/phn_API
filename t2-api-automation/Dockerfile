FROM maven:3.9-eclipse-temurin-11 AS build

# Set working directory
WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean test -B

# Final stage - lightweight runtime (optional, for running tests only)
FROM maven:3.9-eclipse-temurin-11

WORKDIR /app

# Copy built artifacts from build stage
COPY --from=build /app/target ./target
COPY --from=build /app/pom.xml .
COPY --from=build /app/src ./src

# Default command to run tests
CMD ["mvn", "test", "-B"]

