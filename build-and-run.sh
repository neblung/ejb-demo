#!/usr/bin/env bash

set -e
./gradlew build
container=$(docker run -d --name=ejb-demo -p 8080:8080 jboss/wildfly:10.1.0.Final)
echo started container $container
for i in webapp1 webapp2; do
    docker cp $i/build/libs/$i.war $container:/opt/jboss/wildfly/standalone/deployments
done

echo webapps up and running
echo 'curl localhost:8080/webapp1/rest/hello'
echo 'curl localhost:8080/webapp2/rest/hello'
echo 'view logs with: $> docker logs ejb-demo'
read -p "press <ENTER> to kill container" eatCR
docker kill ejb-demo && docker rm ejb-demo

