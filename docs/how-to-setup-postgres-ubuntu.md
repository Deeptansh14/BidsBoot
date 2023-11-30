
## install and configure postgres db on ubuntu

refer website `https://www.postgresql.org/`

can also refer `https://www.hostinger.com/tutorials/how-to-install-postgresql-on-ubuntu`


However, Install simply with:

```sh
sudo apt update
sudo apt install postgresql
```

or follow a method given on the reference websites to add the latest postgres official repo and latest postgres.

To check it is _working_:

```sh
sudo systemctl status postgresql
sudo -u postgres psql
SELECT version();
# CTRL-D to exit psql
sudo pg_isready
```

change password, connect and check:

```sh
sudo -u postgres psql
ALTER USER postgres PASSWORD 'aarush';
# ALTER ROLE
# to see the query buffer:
\p
# CTRL-D

sudo systemctl restart postgresql

sudo su - postgres
postgres@hostname:~$ psql
postgres=# \conninfo 
You are connected to database "postgres" as user "postgres" via socket in "/var/run/postgresql" at port "5432".
# CTRL-D

createuser --interactive
# give javabid and superuser yes
psql
\du
# postgres=# \du
#                              List of roles
#  Role name |                         Attributes                         
# -----------+------------------------------------------------------------
#  javabid   | Superuser, Create role, Create DB
#  postgres  | Superuser, Create role, Create DB, Replication, Bypass RLS

# CTRL-D
# CTRL-D

```

create database, create linux user and connect with it

```sh
sudo -u postgres createdb javabid
sudo adduser javabid # and give same password and full name as Javabid Project

sudo -u javabid psql
# psql (16.1 (Ubuntu 16.1-1.pgdg23.10+1))
# Type "help" for help.

javabid=# \conninfo 
# You are connected to database "javabid" as user "javabid" via socket in "/var/run/postgresql" at port "5432".
# javabid=# 

```

### Configure the PostgreSQL Server

On terminal, with the usual linux user

```sh
[edit using vscode]
code /etc/postgresql/16/main/postgresql.conf

# find line, uncomment and change it to:
listen_addresses = '*'	

# then, [edit using nano]
sudo nano /etc/postgresql/16/main/pg_hba.conf

# Add a new policy at the bottom of the file using this syntax:
# connection database user ip_address encryption
# enable all TCP/IP connections,
# from all users to every PostgreSQL database.
# These users can use any IP address with an MD5-encrypted password

host all all 0.0.0.0/0 md5

# save and exit

# # replication privilege.
# local   replication     all                                     peer
# host    replication     all             127.0.0.1/32            scram-sha-256
# host    replication     all             ::1/128                 scram-sha-256
# host    all             all             0.0.0.0/0               md5

```
