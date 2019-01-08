FROM postgres:11

WORKDIR /user
COPY . /user
COPY setup.sql /docker-entrypoint-initdb.d/10-init.sql