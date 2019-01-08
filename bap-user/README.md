# BAP-User

This is home to all users information.

## How to run

* Install Docker Compose.
* In this current directory, run `docker-compose up --build` or `docker-compose up -d --build` to run in detached mode. 
This will start the user service, postgresql database and pgadmin.
* To access the APIs, go to `localhost:8080` etc.

## How to shut down

* run `docker-compose down`.
* run `docker-compose down -v` to remove the database too.

## How to run pgadmin

* Go to `localhost:5050`.
* To login, go to `docker-compose.yml` and find `PGADMIN_DEFAULT_EMAIL` and `PGADMIN_DEFAULT_PASSWORD`, this is your email and password.
Change it if you want to, it's only for you locally.
* Create a new server.
* Run `docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' bap_user_db_1` to get the IP Address of the database.
* Put in that IP Address and Port`5432`.
* For the database username and password, find `POSTGRES_USER` and `POSTGRES_PASSWORD` in `docker-compose.yml`. Change it if you want to, it's for you only.