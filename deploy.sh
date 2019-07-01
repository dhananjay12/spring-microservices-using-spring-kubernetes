kubectl apply -f k8s
kubectl set image deployments/contactus contactus=dhananjay12/fungible-contact-us-service:$SHA
kubectl set image deployments/user user=dhananjay12/fungible-user-service:$SHA
kubectl set image deployments/gateway gateway=dhananjay12/fungible-gateway:$SHA