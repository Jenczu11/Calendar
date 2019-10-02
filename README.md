[![Build Status](https://travis-ci.org/Jenczu11/Calendar.svg?branch=master)](https://travis-ci.org/Jenczu11/Calendar)
# Calendar @ PROKOM.TUL2019

Calendar app for 2019 Component Programming classes at Lodz University of Technology.
# Requirements

 - `maven` 	tested and build on version 3.6.0
 - `java` 	tested on versions 11.0 and above

# To package
To build and mostly package use this command other `maven`  commands should also work

    mvn package
## How to run
Ensure the above command runned without problems
The `mvn package`  command should generate target jar in `./target` with depedencies called:

> Calendar-**version**-jar-with-dependencies

To start application with graphical interface simply type:

    java -cp Calendar-**version**-jar-with-dependencies gui.GUI

To start application in command line interface type:
    
    java -cp Calendar-**version**-jar-with-dependencies program -cli

