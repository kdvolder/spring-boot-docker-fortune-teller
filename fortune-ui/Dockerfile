#
# To use this in STS, see 'sts-docker-build-DOCKERFILE.sh'
# Rename that file to 'sts-docker-build.sh' to 'enable' it (STS only
# looks for a build file with that name, so by naming it differently,
# we can 'disable' the file and still keep it around for reference
# purposes.

FROM openjdk:11 as builder
CMD mkdir /source
COPY . /source
WORKDIR /source
RUN ls -la
RUN ./mvnw clean package
FROM openjdk:11
COPY --from=builder /source/target/*.jar /app.jar

# Note: 
#
# For STS boot dash to work well with your custom-built image, you need to
# pass along any `$JAVA_OPTS` that STS may be setting to the jvm.

ENTRYPOINT java $JAVA_OPTS -jar /app.jar