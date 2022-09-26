kubectl delete clusterrolebinding service-reader-pod

kubectl delete -f family-app.yaml
kubectl delete -f family-member-app.yaml
kubectl delete -f family-db.yaml

read -p "Zakończono"
