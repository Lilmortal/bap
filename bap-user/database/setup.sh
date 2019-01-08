#!/bin/bash
set -e

apt-get update && \
      apt-get -y install sudo

pg_createcluster 11 main --start

/etc/init.d/postgresql start
sudo -u postgres psql -f setup.sql
/etc/init.d/postgresql stop