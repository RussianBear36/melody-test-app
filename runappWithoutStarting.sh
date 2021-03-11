gradle buildWar
gradle moveWars
cd docker-image
docker build -t javamelody ./javamelody #build image for javaMelody WAR-collect server
docker build -t cuba-sample-sales-corewar ./jetty/corewar
docker build -t cuba-sample-sales-webwar ./jetty/webwar
docker build -t jetty ./jetty