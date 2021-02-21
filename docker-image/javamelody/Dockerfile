
### Dockerfile

FROM openjdk:8

COPY . /usr/src/javamelody

CMD java -server -Dapp.home=/usr/src/javamelody/home -Xmx128m -jar /usr/src/javamelody/collector_server.war --httpPort=1337