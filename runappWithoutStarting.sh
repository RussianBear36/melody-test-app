gradle buildWar
gradle moveWars
cd docker-image
docker build -t javamelody ./javamelody #build image for javaMelody WAR-collect server
docker build -t cuba-sample-sales-corewar ./tomcat/corewar
docker build -t cuba-sample-sales-webwar ./tomcat/webwar
docker build -t tomcat ./tomcat