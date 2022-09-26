# Projekt

Projekt działa z wykorzystaniem kontenerów dockerowych oraz Kubernetes'a.

## Technologie i biblioteki

- Java 17
- Spring Framework 5
    - Spring Boot
    - Spring Data JPA
    - Spring Web MVC
    - Spring Cloud (OpenFeign, Resilence4j, Kubernetes)
- JUnit 5
- Maven
- MySQL, H2
- Flyway
- Lombok
- MapStruct

# Budowa i uruchomienie projektu

Projekt zawiera gotowe skrypty pozwalające na zbudowanie i uruchomienie zarówno obrazów dockerowych jak
i całego projektu w środowisku Kubernetes.

## Wymagania

* Java 17+
* Docker
* Maven
* Kubernetes CLI
* Minikube

## Budowa projektu

<sub>Wszystkie wymienione polecenia dostępne są w postaci skryptów podanych w nawiasach.</sub>
<br>
<br>
Sklonowanie projektu:

```
git clone https://github.com/Sekarre/family-project.git
```

Przed zbudowaniem projektu należy najpierw uruchomić Minikube poprzez użycie komendy:

```
minikube start
```

Następnie w głównym folderze projektu należy wykonać polecenia (oba w tej samej konsoli) pozwalające na zbudowanie projektu przy pomocy
narzędzia
Maven
(skrypt: **build-project.sh**):
<br/>
<br/>
<sub>Pozwala na dodanie obrazów dockerowych do lokalnego rejestru Minikube:</sub>

```
eval $(minikube docker-env)
```

<sub>Budowanie projektu oraz wszystkich wymaganych obrazów dockerowych:</sub>

```
mvn clean verify
```

## Uruchomienie projektu

Wszystkie kolejne polecenia należy wykonywać z folderu **_kubernetes_** znajdującym się w głównym katalogu projektu.

Stworzenie zasobów Kubernetes
(skrypt:  **init-kubernetes-default.sh**):
<br/>

<sub>Stworzenie i nadanie nowej roli dla klastra:</sub>

```
kubectl apply -f family-cluster-role.yaml
```

```
kubectl create clusterrolebinding service-reader-pod --clusterrole=service-reader --serviceaccount=default:default
```

<sub>Tworzenie zasobów Kubernetes dla komponentów aplikacji:</sub>

```
kubectl apply -f family-app.yaml
```

```
kubectl apply -f family-member-app.yaml
```

```
kubectl apply -f family-db.yaml
```

<br>
Serwisy family-app oraz family-member-app najprawdopodobniej będą wymagały ponownego uruchomienia przez Kubernetes z
powodu braku połączenia z bazą danych. Należy poczekać 2-3 minuty aż aplikacje nawiążą kontakt z bazą.

### Sprawdzenie poprawności działania

W zależności od systemu operacyjnego, aplikacja może działać na różnych portach, które wystawiane są
przez Minikube, aby uzyskać adres URL można wydać następujące polecenie (w przypadku systemu
Windows, po wydaniu polecenia należy zostawić otwarte okno konsoli):

```
minikube service family-app --url
```

Aplikacja wystawia dwa punkty końcowe, domyślnym portem wystawianym przez aplikacje jest 8080 ale może on się
różnic w zależności od systemu operacyjnego.

1. Usługa utworzenie rodziny (POST): ```http://localhost:{port}/api/v1/family```
   * **port** - port zwrócony przez wymienione wcześniej polecenie minikube
2. Usługa pobrania informacji o rodzinie (GET): ```http://localhost:{port}/api/v1/family/{id}```
   * **port** - port zwrócony przez wymienione wcześniej polecenie minikube
   * **id** - id zwrócone przez usługę tworzenia rodziny

Przykładowe wywołania usług znajdują się w folderze _**postman-samples**_.

Aplikacja udostępnia również interfejs Swaggera:

```http://localhost:{port}/swagger-ui/index.html#/```

### Czyszczenie

Aby usunąć niepotrzebne zasoby można wydać następujące polecenia (skrypt:  **clean-kubernetes-default.sh**)::

<sub>Usunięcie roli</sub>

```
kubectl delete clusterrolebinding service-reader-pod
```

<sub>Usunięcie zasobów projektu w Kubernetes</sub>

```
kubectl delete -f family-app.yaml
```

```
kubectl delete -f family-member-app.yaml
```

```
kubectl delete -f family-db.yaml
```