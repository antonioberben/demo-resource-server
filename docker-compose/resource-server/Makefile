# Environment Varibles
CONTAINER = resource_server_mongo_db

.PHONY: up

prep :
	mkdir -p \
		data

pull :
	docker-compose pull

up : prep
	docker-compose up -d

stop :
	docker-compose stop

down : stop
	docker-compose rm -f

shell :
	docker exec -ti $(CONTAINER) /bin/bash

tail :
	docker-compose logs -f --tail 200
