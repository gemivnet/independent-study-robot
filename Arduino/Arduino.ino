
#include <Servo.h>

const int cameraPin = 0;
long distance1;
long d = 11;

// Define Servos
Servo leftServo, rightServo, cameraServo;

// Define WiFi
boolean initialized = false;

void setup()
{

  Serial.begin(9600);
  cameraServo.attach(11);
  cameraServo.write(90);

  // Init Wifi Module
  //Serial.begin (115200);
  //sendCommand ( "AT" );
  //sendCommand ( "AT+RST" );
  //delay (4000);
  //sendCommand ( "AT+CWLAP" );
  //sendCommand ( "AT+CWJAP=\"Thing\",\"abcdefgf\"" );
  //sendCommand ( "AT+CIPMUX=0" );
  //sendCommand ( "AT+CIFSR" );
  //sendCommand ( "AT+CIPMUX=1" );
  //sendCommand ( "AT+CIPSERVER=1,80" );
  //initialized = true;
  //delay(2000);
                                                                                                                                                                                                                                            
}

int i = 0;

int oldDistance;

void loop() {

      cameraServo.write(0);

      delay(750);

      getDistance();
      Serial.print("(s1," + String(distance1, DEC) + ")");

      while (distance1 > d) {
        Serial.print("(s1," + String(distance1, DEC) + ")");
        getDistance();
        move(0, 100, 100);
        move(2, 30, 100);
      }

      if (distance1 > oldDistance) {
         Serial.print("(c1)");
        move(2, 100, 100);
        move(0, 100, 100);
        move(3, 100, 100);
      }
      if (distance1 < 6) {
        Serial.print("(c2)");
        move(3, 100, 100);
        move(0, 100, 100);
        move(2, 100, 100);
      }

      oldDistance = distance1;
      
      Serial.print("(s2)");
      move(0, 1000, 100);
      
      cameraServo.write(90);
      delay(750);

      getDistance();
       Serial.print("(s3," + String(distance1, DEC) + ")");
      while (distance1 < d) {
        getDistance();
        move(3, 50, 100);
        Serial.print("(s3," + String(distance1, DEC) + ")");
      }


// Display WebServer
 char ch;
  if (initialized) 
  {     
    if (Serial.available() )
    {
       Serial.print (ch);
    } 
 
    if (Serial.available() )
    {
      ch = Serial.read();

      if (ch == 71) 
        webserver();
    }   
  } 

}



int averages = 50;

void getDistance() {

  int total = 0;

  for (int i = 0; i < averages; i++) {
    total += analogRead(cameraPin);
  }

  total /= averages;
  total /= 2;
  distance1 = total;
  
}

/**
 * Direction
 * 0 - Forward
 * 1 - Backwards
 * 2 - Left
 * 3 - Right
 * 
 * Time in Millisecconds
 * Speed 0 - 100%
 */

void move(int direction, int time, int speed) {

  leftServo.attach(10);
  rightServo.attach(12);
 
  switch (direction) {
    case 0:
             Serial.print("(m0," + String(time) + ")");
        leftServo.writeMicroseconds(1500 + speed);
        rightServo.writeMicroseconds(1500 - speed);
        break;
    case 1:
        Serial.print("(m1," + String(time) + ")");
        leftServo.writeMicroseconds(1500 - speed);
        rightServo.writeMicroseconds(1500 + speed);
        break;
    case 2:
        Serial.print("(m2," + String(time) + ")");
        leftServo.writeMicroseconds(1500 - speed);
        rightServo.writeMicroseconds(1500 - speed);
        break;
     case 3:
         Serial.print("(m3," + String(time) + ")");
        leftServo.writeMicroseconds(1500 + speed);
        rightServo.writeMicroseconds(1500 + speed);
        break;
  }
  
  delay(time);
  
  leftServo.detach();
  rightServo.detach();
  
}


//WiFi Module Functions
void httpOutput (char * msg) {
  int len = strlen (msg);
  Serial.print ( "AT+CIPSEND=0,");
  Serial.println ( len+2 );
  delay (50);
  Serial.println ( msg );
  Serial.println ();
  delay (50);

}

void webserver() {
  char ch;

  httpOutput ( "lol" );
  delay (100);
  Serial.println ( "AT+CIPCLOSE=0");
  while (Serial.available() ) // Flush the input buffer
  {
    ch = Serial.read();

  }
}


void sendCommand ( char * cmd ) {
  Serial.println ( cmd );
  getResponse();  
}

void getResponse () {
  unsigned long startTime;
  char ch;
  char lastCh;
  
  startTime = millis();
  lastCh = ' ';
  while (millis() < (startTime + 3000))
     if (Serial.available() )
     {
       ch = Serial.read();
       if ((lastCh == 'O') && (ch == 'K'))
          break;
       lastCh = ch;
     }
}
