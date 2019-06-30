kubectl apply -f k8s
kubectl set image deployments/contactus contactus=dhananjay12/fungible/contact-us-service:$SHA
kubectl set image deployments/user user=dhananjay12/fungible/user-service:$SHA
kubectl set image deployments/zuul zuul=dhananjay12/fungible/zuul-server:$SHA