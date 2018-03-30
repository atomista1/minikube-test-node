IMAGE=$DOCKER_REGISTRY/minikube-test-node:$1
docker build -t $IMAGE -f docker/Dockerfile .
echo $IMAGE > image.txt
