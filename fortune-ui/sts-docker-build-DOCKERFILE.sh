#!/bin/bash

#
# This file is included to show how you can setup a more customised build
# using a Dockerfile and a custom build command.

# If a `sts-docker-build.sh` file is found at root of your project, then
# it takes priority over STS default method to build docker image.
#
# If you want to fall back to the default (using `./mvn spring-boot:build-image`)
# then you can simply delete this file, or rename it (e.g. DISABLED-sts-docker-build.sh).

docker build -t fortune-ui .