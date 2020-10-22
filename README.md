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

Running as Docker Containers
============================

 - Create a 'Docker Run Target' by clicking the '+' button in the Boot Dash.
 - Simply drag and drop the apps onto the target

Everything then happens automatically.

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