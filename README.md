# Cities API

Este projeto teve como desafio desenvolver uma API Rest para consultar as cidades brasileiras com dados comparativos. Ainda, foi criado um serviço para o calculo da distância entre duas cidades.

A API foi desenvolvida com Spring Boot utilizando o Postgres como banco de dados, através do Docker, e também conta com a configuração para o deploy na plataforma Heroku.





## DataBase

### Postgres

* [Postgres Docker Hub](https://hub.docker.com/_/postgres)

```shell script
docker run --name cities-db -d -p 5432:5432 -e POSTGRES_USER=user_dio -e POSTGRES_PASSWORD=dio -e POSTGRES_DB=cities postgres
```

### Populate

* [data](https://github.com/chinnonsantos/sql-paises-estados-cidades/tree/master/PostgreSQL)

```shell script
cd ~/workspace/sql-paises-estados-cidades/PostgreSQL

Linux:
docker run -it --rm --net=host -v $PWD:/tmp postgres /bin/bash

Windows:
docker run -it --rm --net=host -v "%cd%":/tmp postgres /bin/bash

psql -h localhost -U user_dio cities -f /tmp/pais.sql
psql -h localhost -U user_dio cities -f /tmp/estado.sql
psql -h localhost -U user_dio cities -f /tmp/cidade.sql

psql -h localhost -U user_dio cities


Postegres Extensions:

CREATE EXTENSION cube; 
CREATE EXTENSION earthdistance;
```



### Access

```shell script
docker exec -it cities-db /bin/bash

psql -U user_dio cities
```



### Calculate distances between two cities

Point in miles
```roomsql
select ((select lat_lon from cidade where id = 4929) <@> (select lat_lon from cidade where id=5254)) as distance;

Postman:
http://localhost:8080/distances/by-points?from=4929&to=5254
```

Cube in meters
```roomsql
select earth_distance(
    ll_to_earth(-21.95840072631836,-47.98820114135742), 
    ll_to_earth(-22.01740074157715,-47.88600158691406)
) as distance;

Postman:
http://localhost:8080/distances/by-cube?from=4929&to=5254
```



### Add Postgres to Heroku

```
heroku addons:create heroku-postgresql
```



### Populate

```
Linux:
docker run -it --rm --net=host -v $PWD:/tmp postgres /bin/bash

Windows:
docker run -it --rm --net=host -v "%cd%":/tmp postgres /bin/bash


psql -h <Host name> -U <User Name> <Database Name> -f /tmp/pais.sql
psql -h <Host name> -U <User Name> <Database Name> -f /tmp/estado.sql
psql -h <Host name> -U <User Name> <Database Name> -f /tmp/cidade.sql

psql -h <Host name> -U <User Name> <Database Name>

Postegres Extensions:

CREATE EXTENSION cube; 
CREATE EXTENSION earthdistance;
```

