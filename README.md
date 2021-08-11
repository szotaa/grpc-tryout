# grpc-tryout

small project to tryout grpc with spring boot,
two modules that talk to each other via grpc

## app-api-gateway

entrypoint to the whole application,
exposes two http endpoints:

```POST /{number}``` - adds a number to the in memory repository

```GET /avg``` - calculates average from all numbers in the repository

see class ```NumberController``` and ```application.properties```

## app-in-memory-persistence
holds state in memory, answers to grpc requests coming from app-api-gateway

see class ```NumberService``` and ```application.properties```

## app-proto-module

shared between all modules, contains .proto files
see file ```service.proto```