#include <Servo.h>

Servo left, right, camera;
const int leftPin = 12, rightPin = 10, cameraPin = 11, cameraSensorPin = 0, averages = 1, separation = 10;

void setup() {

  camera.attach(cameraPin);
  Serial.begin(9600);
  
}

void loop() {

  // Stage 1 - Check if there is a wall to the right

  camera.write(0);
  delay(600);
 
  while (getDistance() > separation) {
    Serial.println(getDistance());
    move(0, 300);
    move(2, 100);
  }

  camera.write(90);
  delay(600);

  if (getDistance() > separation) {

    move(0, 1000);
    
  } else {

while (getDistance() < separation) {
  move(3, 200);
}
    
  }
  


}

int getDistance() {
  int distance = 0;
  for (int i = 0; i < averages; i++) {
   distance += analogRead(cameraSensorPin);
  }
  distance /= 2 * averages;
  return distance;
  
}

// Move Function - 0, Forward; 1, Backwards; 2, Left; 3, Right

void move(int direction, int time) {

  left.attach(leftPin);
  right.attach(rightPin);

    switch (direction) {
      case 0:
          left.writeMicroseconds(1400);
          right.writeMicroseconds(1600);
        break;
      case 1:
          left.writeMicroseconds(1600);
          right.writeMicroseconds(1400);
        break;
      case 2:
          left.writeMicroseconds(1400);
          right.writeMicroseconds(1400);
        break;
      case 3:
          left.writeMicroseconds(1600);
          right.writeMicroseconds(1600);
        break;
    }

  delay(time);

  left.detach();
  right.detach();
  
}

