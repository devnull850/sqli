#!/bin/bash

DB=res/db/user.db
SQL=res/db/create.sql

if [ -f "$DB" ]; then
	rm "$DB"
fi

sqlite3 "$DB" < "$SQL"

java -cp build/classes:lib/sqlite-jdbc-3.36.0.3.jar sqli.TestServer
