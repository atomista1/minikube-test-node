IMAGE=$DOCKER_REGISTRY/minikube-test-node:$1
docker build -t $IMAGE -f docker/Dockerfile .
echo $DOCKER_PASSWORD | docker login $DOCKER_REGISTRY -u $DOCKER_USER --password-stdin
docker push $IMAGE

