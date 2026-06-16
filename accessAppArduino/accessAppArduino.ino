#include "WiFiS3.h"
#include "arduino_secrets.h" 

char ssid[] = SECRET_SSID;        
char pass[] = SECRET_PASS;    
int keyIndex = 0;                 

String IPAddress = "enter your IPAddress"
String kodWejscia = "12345";
String stalyKod = "12345";
const String resetCode = "0";
String pinBuffer = "";
bool pinReady = false;
unsigned long pinStartTime = 0;
bool isEnteringPin = false;
const unsigned long pinTimeout = 30000;

const byte ROWS = 4;
const byte COLS = 3;

byte rowPins[ROWS] = {2, 3, 4, 5}; 
byte colPins[COLS] = {6, 7, 8};    

char keyMap[ROWS][COLS] = {
  {'1','2','3'},
  {'4','5','6'},
  {'7','8','9'},
  {'*','0','#'}
};


const char* serverHost = IPAddress;
const uint16_t serverPort = 8080;      

const int motionSensorPin = 9;
const int kontaktronPin = 0;
const int reedSwitchPin = 12;
int led =  LED_BUILTIN;
int status = WL_IDLE_STATUS;
WiFiServer server(80);

// Przechowywanie poprzedniego stanu
int ostatniStanRuchu = LOW;
int ostatniStanDrzwi = LOW;
// Deklaracja obiektu WiFiClient
WiFiClient client; 

void setup() {
  Serial.begin(115200);      
  pinMode(led, OUTPUT);   
  digitalWrite(led, LOW);   
  pinMode(motionSensorPin, INPUT);  
  pinMode(kontaktronPin, INPUT_PULLUP);
  pinMode(reedSwitchPin, OUTPUT);
  digitalWrite(reedSwitchPin, HIGH);
  setupKeypad();

  if (WiFi.status() == WL_NO_MODULE) {
    Serial.println("Communication with WiFi module failed!");
    // don't continue
    while (true);
  }

  while (status != WL_CONNECTED) {
    Serial.print("Attempting to connect to Network named: ");
    Serial.println(ssid);                   
    status = WiFi.begin(ssid, pass);
    delay(10000);
  }
  server.begin();                           
  printWifiStatus(); 
                    
}

void loop() {

  changeState();
  handlePinEntry();
  sendDataPir();
  sendDataKontaktron();
  delay(500);
}

void setupKeypad() {
  for (int c = 0; c < COLS; c++) {
    pinMode(colPins[c], INPUT_PULLUP);
  }

  for (int r = 0; r < ROWS; r++) {
    pinMode(rowPins[r], OUTPUT);
    digitalWrite(rowPins[r], HIGH);
  }
}

String getPin(unsigned long timeoutMs) {
  String pin = "";
  
  unsigned long startTime = millis();

  while (millis() - startTime < timeoutMs) {
    char key = readKey();
    if (key != '\0') {
      startTime = millis(); // resetuj licznik czasu przy każdym naciśnięciu

      Serial.print("Pressed: ");
      Serial.print(key);

      if (key >= '0' && key <= '9') {
        if (pin.length() < 10) {
          pin += key;
          Serial.print("*");
        }
      } else if (key == '*') {
        if (pin.length() > 0) {
          pin.remove(pin.length() - 1);
          Serial.print("\nLast digit removed: ");
          for (int i = 0; i < pin.length(); i++) Serial.print("*");
        }
      } else if (key == '#') {
      
        return pin;
      }
    }
  }

  return ""; // zwróć pusty PIN
}

void handlePinEntry() {
  String pin = getPin(10000);

  if (pin == "") {
    return;
  } 

  if (kodWejscia == resetCode) {
    Serial.println("Default code has been set");
    kodWejscia = stalyKod;
    digitalWrite(LED_BUILTIN, LOW);
  }

  if (pin == kodWejscia) {
    Serial.println("The code is correct, you can enter.");
    digitalWrite(LED_BUILTIN, HIGH);
    digitalWrite(reedSwitchPin, LOW);
    delay(5000); // jeśli konieczne
    digitalWrite(LED_BUILTIN, LOW);
    digitalWrite(reedSwitchPin, HIGH);
  } else {
    Serial.println("The code is wrong, please try again");
    digitalWrite(LED_BUILTIN, LOW);
  }
}

char readKey() {
  for (int r = 0; r < ROWS; r++) {
    // ustaw wszystkie rzędy na HIGH
    for (int i = 0; i < ROWS; i++) {
      digitalWrite(rowPins[i], HIGH);
    }

    // ustaw tylko bieżący rząd na LOW
    digitalWrite(rowPins[r], LOW);

    for (int c = 0; c < COLS; c++) {
      if (digitalRead(colPins[c]) == LOW) {
        delay(50); // krótki debounce
        if (digitalRead(colPins[c]) == LOW) { // sprawdź jeszcze raz
          char key = keyMap[r][c];

          // czekaj aż przycisk zostanie puszczony
          while (digitalRead(colPins[c]) == LOW);
          delay(50); // dodatkowy debounce

          return key;
        }
      }
    }
  }
  return '\0'; // nic nie wciśnięto
}

void printWifiStatus() {
  
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());

  IPAddress ip = WiFi.localIP();
  Serial.print("IP Address: ");
  Serial.println(ip);

  long rssi = WiFi.RSSI();
  Serial.print("signal strength (RSSI):");
  Serial.print(rssi);
  Serial.println(" dBm");
}

void changeState(){
  WiFiClient client = server.available();   
  if (client) {                             
    Serial.println("new client");           
    String currentLine = ""; 
    String firstLine = "";               
    while (client.connected()) {            
      if (client.available()) {             
        char c = client.read();             
        Serial.write(c);                    
        if (c == '\n') {  

          if (firstLine == "") {
            firstLine = currentLine;  // zapisz pierwszą linię (z URL-em)
          }
          
          if (currentLine.length() == 0) {
            
            Serial.println("\n== Pełne żądanie ==");
            Serial.println(firstLine);

            int index = firstLine.indexOf("value=");
            if (index != -1) {
              String kod = firstLine.substring(index + 6);
              kod.trim();  
              int wartosc = kod.toInt();
              kodWejscia = String(wartosc);
              Serial.println("RECEIVED CODE: " + String(wartosc));
            }

            client.println("HTTP/1.1 200 OK");
            client.println("Content-type:text/html");
            client.println();
            break;
          } else {    
            currentLine = "";
          }
        } else if (c != '\r') {  
          currentLine += c;      
        }
      }  
    }
    client.stop();
    Serial.println("client disconnected");
    Serial.println("Access Code: " + currentLine);
  }
}

void sendDataPir(){
    
  int stanRuchu = digitalRead(motionSensorPin);
  Serial.print("Motion sensor status: ");
  Serial.println(stanRuchu == HIGH ? "Motion detected" : "Motion undetected");

  if (stanRuchu != ostatniStanRuchu) {
    ostatniStanRuchu = stanRuchu;

    Serial.println("Attempt to connect to the server...");
    if (client.connect(serverHost, serverPort)) {
      Serial.println("Connected to server!");
      String id = "1";
      String serverEndpoint = "/pir/" + id;

      String wykrycieRuchu = (stanRuchu == HIGH) ? "true" : "false";
      String jsonObiekt = "{\"motion\": \""  + wykrycieRuchu + "\"}";
      String httpRequest = String("GET ") + serverEndpoint + " HTTP/1.1\r\n" +
                           "Host: " + serverHost + "\r\n" +
                           "Content-Type: application/json\r\n" +
                           "Content-Length: " + String(jsonObiekt.length()) + "\r\n" +
                           "Connection: close\r\n\r\n" +
                           jsonObiekt;

      Serial.println("Sending a task:");
      Serial.println(httpRequest);

      client.print(httpRequest);

      Serial.println("Waiting for server response...");
      while (client.connected() || client.available()) {
        if (client.available()) {
          String response = client.readStringUntil('\n');
          Serial.println("Server response:");
          Serial.println(response);
        }
      }

      client.stop();
      Serial.println("Connection to server closed.");
    } else {
      Serial.println("Failed to connect to server.");
    }
  }
}

void sendDataKontaktron(){
    
  int stanDrzwi = digitalRead(kontaktronPin);
  Serial.print("The door state: ");
  Serial.println(stanDrzwi == HIGH ? "The door was opened" : "The door was closed");

  if (stanDrzwi != ostatniStanDrzwi) {
    ostatniStanDrzwi = stanDrzwi;

    Serial.println("Attemtping to connect to the server...");
    if (client.connect(serverHost, serverPort)) {
      Serial.println("Connected to server!");
      String kontraktonId = "1";
      String serverEndpoint2 = "/kontrakton/" + kontraktonId;

      String wykrycieDrzwi = (stanDrzwi == HIGH) ? "true" : "false";
      String jsonObiekt2 = "{\"doorState\": \""  + wykrycieDrzwi + "\"}";
      String httpRequest2 = String("GET ") + serverEndpoint2 + " HTTP/1.1\r\n" +
                           "Host: " + serverHost + "\r\n" +
                           "Content-Type: application/json\r\n" +
                           "Content-Length: " + String(jsonObiekt2.length()) + "\r\n" +
                           "Connection: close\r\n\r\n" +
                           jsonObiekt2;

      Serial.println("Sending a request:");
      Serial.println(httpRequest2);

      client.print(httpRequest2);

      Serial.println("Waiting for server response...");
      while (client.connected() || client.available()) {
        if (client.available()) {
          String response2 = client.readStringUntil('\n');
          Serial.println("Server response:");
          Serial.println(response2);
        }
      }

      client.stop();
      Serial.println("Connection to server closed.");
    } else {
      Serial.println("Failed to connect to server..");
    }
  }
}


