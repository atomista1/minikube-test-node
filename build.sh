IMAGE=$DOCKER_REGISTRY/minikube-test-node:$1
docker build -t $IMAGE -f docker/Dockerfile .
docker login $DOCKER_REGISTRY -u $DOCKER_USER -p $DOCKER_PASSWORD
docker push $IMAGE
echo $IMAGE > image.txt

