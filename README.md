# minikube-test-node
simple http listener in node

```
docker build -t slimslender/minikube-test-node -f docker/Dockerfile .
```

Note that there are two mandatory endpoints for our deployment to kube

* `/health` must return a status 200 to indicate that it is ready to receive requests
* `/info` must return a status 200 to indicate readiness

Both of these endpoints are defined in server.js along with a / endpoint for the hello world

## Build and Push Images

New versions of this service can be pushed to a Docker registry by setting 3 environment variables:

1.  DOCKER_REGISTRY
2.  DOCKER_USER
3.  DOCKER_PASSWORD

and then running `build.sh` with a single argument (the tag of the container to build)

```
DOCKER_REGISTRY= DOCKER_USER= DOCKER_PASSWORD= ./build.sh 0.11.1
```
