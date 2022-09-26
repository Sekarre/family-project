eval $(minikube docker-env)

mvn clean verify

read -p "Press enter to continue"