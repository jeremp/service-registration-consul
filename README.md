# service-registration-consul

There are 2 Spring boot project.

- *store* : the "customer" services, exposes and index.html page that keep requesting the order service
- *order* : a service that expose a /health and a /hello endpoints.

## Setup a consul

Do this with docker : 

`docker pull consul`

`docker run -d -p 8500:8500 --name=reg-consul -e CONSUL_BIND_INTERFACE=eth0 consul`

You'll be able to see services register themself on http://localhost:8500

## Start some Order services

`./orders/run.sh 8081 Foo`

`./orders/run.sh 8082 Bar`

WARNING : don't use *8080* as it's Store service's default port.

## Start a Store service

`./store/run.sh`

browse to http://localhost:8080 ... watch the screen, kill some Order services