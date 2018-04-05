# playground

## k8vent

```
kubectl apply -f https://raw.githubusercontent.com/atomista1/minikube-test-node/master/playground/k8vent/rbac.yaml
curl -sL https://raw.githubusercontent.com/atomista1/minikube-test-node/master/playground/k8vent/deployment-rbac.yaml \
  | sed -e 's/T0REPLACE/ANBD24ZEC/g' -e 's/ENV_REPLACE/playground/g' | kubectl apply -f -
```

## k8-automation

```
kubectl apply -f https://raw.githubusercontent.com/atomista1/minikube-test-node/master/playground/k8-automation/rbac.yaml
kubectl --namespace=default delete secret automation
kubectl --namespace=default create secret generic automation --from-literal=config='{"teamIds":["ANBD24ZEC"],"token":"xxxxxxxxxx","custom":{"hostUrl":"https://PLAYGROUND_INGRESS_HOST_OR_IP","imagePullSecret":"artifactory","namespace":"default"}}'
kubectl apply -f https://raw.githubusercontent.com/atomista1/minikube-test-node/master/playground/k8-automation/deployment-rbac.yaml
```
