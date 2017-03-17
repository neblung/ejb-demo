#!/usr/bin/env bash

CONTAINER=$(basename `pwd`)

set -e
./gradlew build
containerid=$(docker run -d --name=$CONTAINER -p 8080:8080 jboss/wildfly:10.1.0.Final)
echo started container $containerid
for war in $(find . -name '*.war'); do
    docker cp $war $containerid:/opt/jboss/wildfly/standalone/deployments
done

echo $CONTAINER up and running
echo "direct your browser to:"
echo "   http://localhost:8080/webapp1"
echo "   http://localhost:8080/webapp2"
echo "   http://localhost:8080/view"
echo "view logs with: \$> docker logs -f $CONTAINER"
read -p "press <ENTER> to kill container" eatCR
docker rm -f $CONTAINER

