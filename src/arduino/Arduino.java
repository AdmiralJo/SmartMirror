#include <Ethernet.h>
#include <SPI.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
byte ip[] = { 192,168,178,155};
byte server[] = { 192,168,178,42};
byte sn[] = { 255, 255, 255, 0 };
byte gw[] = { 192, 168, 178, 1};

EthernetClient client;

int PIN = 13, PORT = 1234;

void setup()
{
  pinMode(PIN, OUTPUT);
  Ethernet.begin(mac, ip, sn, gw);
  Serial.begin(9600);
  Serial.println("connecting...");


  if (client.connect(server, PORT)) {
    Serial.println("connected");
  } else {
    Serial.println("connection failed");
  }
}

void loop()
{
  if (client.available()) {
    char c = client.read();

    if(c == '1') {
      digitalWrite(PIN, HIGH);
      client.println('1');
      Serial.println("switched on relais");
    } else if(c == '0') {
      digitalWrite(PIN, LOW);
      client.println('0');
      Serial.println("switched off relais");
    } else{
      Serial.println("invalid request");
    }
  }

  if (!client.connected()) {
    
    Serial.println("connection failed. reconnecting...");

    delay(1000);
    
    if (client.connect(server, PORT)) {
      Serial.println("connected");
    } else {
     Serial.println("connection failed");
    }
  }
}
