# Quarkus demo
This is an demo of two microservices using Quarkus. Its mainly an repo where I can try out differnt stuff, experiment and not something that is made for production.

# What do we demo?
Its based around the problem that you need to calculate how many hours that you need to run your engine heater at an given temperature.

The demo is mainly based in the engine-heater microservice and tries to use so many features as possible to show the capabilites of Quarkus.

# Contract testing
I have added some contract testing to the micro services using PACT. The engine-heater service is an consumer and the temperature service is an provider. The test is in its first iteration, so I havn't got it to work due too some configuration, but the style of the tests is correct.

## What you need
For this to run you have to use:
- Some sort of Docker enviroment or switch from Postgres DB to H2
- Optional: Prometheus and Grafana too show dashes.
