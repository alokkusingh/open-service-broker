# Open Service Broker
Open Service Broker using Spring Cloud OSB.

Deployed as Kubernetes Service Broker.

## Docker Build
```shell
mvn clean package
```
```shell
docker build -t alokkusingh/home-osb:latest -t alokkusingh/home-osb:1.0.0 --build-arg JAR_FILE=target/open-service-broker-0.0.1-SNAPSHOT.jar .
```
```shell
docker push alokkusingh/home-osb:latest
```
```shell
mvn clean package
docker build -t alokkusingh/home-osb:latest -t alokkusingh/home-osb:1.0.0 --build-arg JAR_FILE=target/open-service-broker-0.0.1-SNAPSHOT.jar .
docker push alokkusingh/home-osb:latest
```
---
## Kubernetes Deployment
### Create Namespaces for OSB and Tests
```shell
kubectl apply -f k8s/deploy/namespace.yaml
```
### Create Java Spring OSB Service - business logic
```shell
kubectl apply --validate=true --dry-run=client -f k8s/deploy/home-osb-service.yaml 
```
```shell
kubectl apply -f k8s/deploy/home-osb-service.yaml
```
### Create Service Broker
Create secret for service broker
```shell
kubectl create -f k8s/deploy/home-osb-broker-secret.yaml
```
Create service broker
```shell
kubectl create -f k8s/deploy/home-osb-broker.yaml
```
Note: as of now Microk8s doesn't support servicecatalog

### View Service Offerings
Show the list of brokered service offerings advertised by the service broker:
```shell
kubectl get clusterserviceclasses -o=custom-columns=NAME:.metadata.name,EXTERNAL\ NAME:.spec.externalName
```
Show the details of the brokered service offering:
```shell
kubectl get clusterserviceclasses b92c0ca7-c162-4029-b567-0d92978c0a97 -o=yaml
```
#### View Service Plans
Show the list of brokered service plans advertised by the service broker:
```shell
kubectl get clusterserviceplans -o=custom-columns=NAME:.metadata.name,EXTERNAL\ NAME:.spec.externalName
```
Show the details of the brokered service plan:
```shell
kubectl get clusterserviceplans fd81196c-a414-43e5-bd81-1dbb082a3c55 -o yaml
```
### Use the service broker
#### Create a service instance
Create an instance of a brokered service from the sample service broker:
```shell
kubectl create -f k8s/deploy/service-instance.yml
```
Show the details of the created service instance:
```shell
kubectl describe serviceinstance mail-instance -n test-stack
```
#### Create a service binding
Create a service binding for the service instance:
```shell
kubectl create -f k8s/deploy/service-binding.yml
```
Show the details of the created service binding:
```shell
kubectl describe servicebinding mail-binding -n test-stack
```
Service bindings are exposed via Kubernetes secret objects. 
Show the details of the secret containing the binding credentials:
```shell
kubectl get secret mail-binding -n test-stack -o yaml
```


## Delete the stack
```shell
kubectl delete -f k8s/deploy/namespace.yaml
```
