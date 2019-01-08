# BAP

This is an multi build project for BAP; which is a chat server where
multiple users can join at anytime, similar to Discord. The difference is
that this server will notify everyone that a user dota game has finished if
they give permission to do so.

## How is this project structured?

It is seperated into three projects, `bap-chat` which deals with chats.
`bap-dota` which deals with dota, and `bap-user` which deals with users.
Pretty self explanatory. It is build using `gradle` and `docker`.

## How do I run this project?

* Install Docker Compose.
* In the parent directory, run `docker-compose up --build` or 
`docker-compose up -d --build` to run in detached mode. 
This will start all the services, databases and pgadmin.

## How to shut down

* run `docker-compose down`.
* run `docker-compose down -v` to remove the database too.

## How to run pgadmin

* Go to `localhost:5050`.
* To login, go to `docker-compose.yml` and find `PGADMIN_DEFAULT_EMAIL` 
and `PGADMIN_DEFAULT_PASSWORD`, this is your email and password. 
Change it if you want to, it's only for you locally.
* Create a new server.
* Run `docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' SERVICE`
to get the IP Address of the database. Replace `SERVICE` with the docker
container service name. Go to the specific directory for more information.
* Put in that IP Address and Port`5432`.
* For the database username and password, find `POSTGRES_USER` and 
`POSTGRES_PASSWORD` in `docker-compose.yml`. 
Change it if you want to, it's for you only.