kubectl apply -f family-cluster-role.yaml

kubectl create clusterrolebinding service-reader-pod --clusterrole=service-reader --serviceaccount=default:default

kubectl apply -f family-app.yaml
kubectl apply -f family-member-app.yaml
kubectl apply -f family-db.yaml


read -p "Zakończono."
