## Getting started

### Introduction

  Sample query :
  START_LATITUDE=12.94523 START_LONGITUDE=77.61896 DEST_LATITUDE=12.95944 DEST_LONGITUDE=77.66085
  
  
### Installation

  Cloning the repository.
  ```bash
  git clone https://github.com/viswavanapalli/pathPlotter.git
  ```
### Building with maven

  ```bash
  mvn clean install -DskipTests
  ```  
### Running locally
  
  ```bash
  java -cp {PATH_TO pathPlotter-1.0-SNAPSHOT.jar} PathPlotterApplication
  
  Sample Queries are execute
  It will be followed by a Command Line prompt which feeds in INPUT_QUERIES and execute them. 
  Until "exit" is entered.
  ```
### References
  ```
  https://developers.google.com/maps/documentation/directions/client-library
  https://mkyong.com/java/how-to-send-http-request-getpost-in-java/
  ```
### Math for understanding
  ```
 -> TO Find Radians From latitude and longitude
    latitude  in radians :- latitudeInDegrees * pi / 180
    longitude in radians :- longitudeInDegrees* pi / 180


 -> TO GET X,Y,Z Coordinates from Latitude, Longitude
    R -> radius of earch
    p-> latitude in radians
    q-> longitude in radians
    Then,
    x-> R*Cos(p)*Sin(q)
    y-> R*Sin(p)
    z-> R*Cos(p)*Cos(q)

 -> displacement between two points in coordinate system
    sqrt( (diffInX)*(diffInX) + (diffInY)*(diffInY) + (diffInZ)*(diffInZ) )

 -> Find angle projected by line at center by
    displacement = 2*R*Sin(0/2)

 -> Length of arc projecting an angle 0 onto center,
    length of arc = R*0

 -> Approximate infinitesimal latitude and longitude grid as a rectangle while incrementing
  ```
