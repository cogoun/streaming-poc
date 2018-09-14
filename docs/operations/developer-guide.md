
### Useful commands

#### Start the platform in a detached  mode

    $ docker-compose up -d

#### Stop the platform

    $ docker-compose stop

#### List all log files from a service

    $ docker-compose logs -f SERVICE_NAME

- The -f performs as a tail.
- Omitting SERVICE_NAME will retrieve all logs from all containers.

#### Start a shell inside a container
First perform a ps:

    $ docker-compose ps

Then open a windows CMD and enter the id of one container that you want to open a shell to:

    P:\> docker exec -i -t 22afb55b1795  /bin/sh

I have see this work only through a windows CMD and not the git-bash application :/ .

#### Rebuild the platform with no cache

    $ docker-compose build --no-cache

#### Force recreation of containers even if the image has not changed

    $ docker-compose up -d --force-recreate

#### Monitor memory usage

    $ docker stats