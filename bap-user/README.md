# BAP-User

This is home to all users information.

## How to run bap-user services

* Follow the instructions in README.md in the parent directory.
* User APIs can be found in `localhost:8080`.

## How to run pgadmin specifically for bap-user

* Follow the README.md instructions in the parent directory on how to run pgadmin.
* Run `docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' bap_user_db_1` to get the IP Address of the database.
* Put in that IP Address and Port`5432`.
* For the database username and password, find `POSTGRES_USER` and `POSTGRES_PASSWORD` in `docker-compose.yml` in the parent directory. 
Change it if you want to, it's for you only.

## TODO - Run kubenetes

If you have `minikube` installed, you can run `./deploy.sh` in `/deployment` folder.
`-v` tests if Postgresql database is up by creating a user and check that it returns `HTTP 200`.
`-b` opens up the healthcheck API.

However at the moment it is not working, my images does not seem to be updated...