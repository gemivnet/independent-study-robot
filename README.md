## Independent Study Robot

This is a project I worked on during my senior year of high school. The goal of the project was to make an arduino based robot which would map the room it was in and send the data to a webpage which would then be rendered into a map.

#### Equipment

* Sparkfun RedBoard
* Parallax Robot Shield Kit
* Sparkfun MMA8452
* ESP8266 ESP-01
* LV MaxSonar EZ2
* HiTEC HS-485HB Servo

#### Theory of Operation

The Robot drives around the room following the outermost wall. It goes around the room in a clockwise pattern. The data is then sent over WiFi to a webserver. A Java program then interprets the data and makes a map of the room.