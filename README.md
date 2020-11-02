Fortune Teller
==============

Simple Spring Boot "microservices" app consist out of 3 microservices:

- fortune-eureka: discovery server, allows other services to find eachother
- fortune-ui: frontend web app which displays a random fortune message to the user
- fortune-service: backend service that contains a pool of message and can provide 
                   a random message from its pool upon request.

Purpose of these apps is to demonstrate running these kinds of apps from STS Boot Dash 
inside docker containers.

Importing into STS
=================

Clone the repo.

Use the 'Import Existing Maven Projects' wizard in STS. Point it at the root of this
repo. 

Running as Docker Containers (STS)
==================================

 - Create a 'Docker Run Target' by clicking the '+' button in the Boot Dash.
 - Simply drag and drop the apps onto the target

Everything then happens automatically.

Running as Docker Containers with Docker Compose CLI
====================================================

For reference, a `docker-compose.yml` file is provided in the root of this repo.
The docker compose file assumes you have already created the images.
If you already tried running the apps in docker from Boot Dash the images
will exist. Otherwise, you need to run `./mvnw spring-boot:build-image` on each
project. For example in bash you can do like so:

```
for f in fortune-*
do
   cd $f
   ./mvnw -Dmaven.test.skip=true spring-boot:build-image
   cd ..
done
```

Once images have been created you can run them like so:

```
$ docker-compose up
```

Running as Local Apps
=====================

The apps have been configure to run out of the box in STS docker target. 
For running locally we have to assign each app a unique port since
multiple apps running on localhost can not all bind the same port
(this isn't an issue in docker because every container runs on separate ip
address).

To make this port reconfigurations a little easier the various 
`application.yml` files contain a section for the `local` profile. 
You must enable this profile explicitly when you run the apps on localhost.

In the Boot Dash you can do this by selecting a local app node
and clicking on the pencil icon. Then type 'local' into the
`Profiles` input box.

Or you can run the apps on the CLI using a command like `./mvnw -Dspring.profiles.active=local spring-boot:run`. 