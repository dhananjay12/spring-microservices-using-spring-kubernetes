# Spring Microservice K8s [![Build Status](https://travis-ci.org/dhananjay12/spring-microservices-using-spring-kubernetes.svg?branch=master)](https://travis-ci.org/dhananjay12/spring-microservices-using-spring-kubernetes)

This sample application is to test spring apps service discovery with [spring-cloud-kubernetes](https://github.com/spring-cloud/spring-cloud-kubernetes) in K8 env. For other envs you can use Eureka(which is actually the default).

## Build and Dockerize

mvn clean install -Ddocker

## Build and Dockerize but Skip Docker Push

mvn clean install -Ddocker -Ddocker.skip.push

## Endpoints

#### Gateway
Standard actuator endpoints

#### User service
* `/user/public-address`

and standard actuator endpoints

#### Contant Us service
* `/contact-us/address`
* `/contact-us/hello`

and standard actuator endpoints

Spring Cloud Kubernetes requires access to the Kubernetes API in order to be able to retrieve a list of addresses for 
pods running for a single service. If you use Kubernetes, you should just execute the following command:

```
kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default
```

This is only done for testing. In production, it can be something like - 
https://cloud.spring.io/spring-cloud-kubernetes/reference/html/#service-account

## Properties required for K8
The properties required to switch from Eureka to K8s are:
```
        - name: EUREKA_CLIENT_ENABLED
          value: "false"
        - name: SPRING_CLOUD_KUBERNETES_ENABLED
          value: "true"
        - name: SPRING_CLOUD_KUBERNETES_RELOAD_ENABLED
          value: "true"
        - name: SPRING_AUTOCONFIGURE_EXCLUDE
          value: "org.springframework.cloud.netflix.eureka.loadbalancer.LoadBalancerEurekaAutoConfiguration"
```

## Copy data.csv to the volume created

### Google Cloud
Login to your google account using gcloud and then set the project and region.
Then set the cluster using following command:

```
gcloud container clusters get-credentials spring-k8s
```

Then copy the file using following command 

```
kubectl cp data.csv <namespace>/<pod-name>:/app/data
```
where /app/data is the mount path of the container.


NOTE:  If you refer the official Google Documentation, you will notice that in ReadWriteMany access type, PersistentVolumes that are backed by Compute Engine persistent disks are not supported. There are alternative solutions for this like Google Filestore or Network File System(NFS).
