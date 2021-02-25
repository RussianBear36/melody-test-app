# javamelody-tests for cluster #

### Run: ###
  * Gradle: 
    * buildUberJar
    * moveJar
  * Docker:
    * cd docker-container
    * sudo docker build -t javamelody ./javamelody #build image for javaMelody WAR-collect server
    * sudo docker build -t cuba-sample-sales-core ./core
    * sudo docker build -t cuba-sample-sales-web ./web
  * Start pipeline:
    * cd - # if you not in project root dir
    * docker-compose up
    
* Endpoints: 
  * localhost/app # CUBA cluster
  * localhost:1337 # JavaMelody collect server
  
 
 
