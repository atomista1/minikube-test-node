# minikube

## minikube setup

```
brew cask install minikube
```

I like to start from scratch each time:

```
minikube delete
minikube start
minikube addons enable ingress
minikube version
```

but note that this would completely delete the current kube cluster.  Good practice perhaps but only once the scripts for rebuilding the cluster are in a good state.

I am using `minikube version: v.25.0`.

## k8vent

```
kubectl apply -f https://raw.githubusercontent.com/atomist/k8vent/master/kube/namespace.yaml
kubectl apply -f https://raw.githubusercontent.com/atomist/k8vent/master/kube/rbac.yaml
curl -sL https://raw.githubusercontent.com/atomist/k8vent/master/kube/deployment-rbac.yaml \
  | sed -e 's/T0REPLACE/ANBD24ZEC/g' -e 's/ENV_REPLACE/production/g' | kubectl apply -f -
```

## k8-automation

```
kubectl apply -f https://raw.githubusercontent.com/atomist/k8-automation/master/assets/kube/namespace.yaml
kubectl apply -f https://raw.githubusercontent.com/atomist/k8-automation/master/assets/kube/rbac.yaml
kubectl apply -f https://raw.githubusercontent.com/atomista1/minikube-test-node/master/minikube/k8-automation-rbac.yaml
kubectl --namespace=k8-automation delete secret automation
kubectl --namespace=k8-automation create secret generic automation --from-literal=config='{"teamIds":["ANBD24ZEC"],"token":"xxxxxxxxxx","custom":{"hostUrl":"https://192.168.99.100","imagePullSecret":"artifactory","namespace":"splunk"}}'
```

## Docker Image builder

```
kubectl apply -f https://raw.githubusercontent.com/atomista1/minikube-test-node/master/minikube/namespace.yaml

# replace all fields here!!!!
kubectl --namespace=splunk create secret generic registry \
        --from-literal=registry=sforzando-dockerv2-local.jfrog.io \
        --from-literal=username=jim \
        --from-literal=password=XXXXXXX \
        --from-literal=email=jim@atomist.com \

# replace token again!!!! XXXXXXXX is the Atomist token, and YYYYYY is the GitHub token
kubectl --namespace=splunk create secret generic atomist \
        --from-literal=team=ANBD24ZEC \
        --from-literal=token=XXXXXXXXX \
        --from-literal=github=YYYYYYYY

kubectl delete -f https://raw.githubusercontent.com/atomista1/minikube-test-node/master/minikube/builder.yaml
kubectl apply -f https://raw.githubusercontent.com/atomista1/minikube-test-node/master/minikube/builder.yaml
```

## tailing logs

Tail logs for both the k8-automation and the micro-srv-automation to watch Atomist react to changes in `splunk/appcms`.

```
kubectl --namespace=k8-automation logs -f k8-automation-6d5db5b98d-s676x
kubectl --namespace=splunk logs -f micro-srv-automation-5846446b76-jbdwh
```
