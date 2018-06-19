# socket_comunication
How to use it
==============================

PC-PC
==============================
- Use same port number in EchoServer.java and EchoClient.java
- Run EchoServer.java
- Run EchoClient.java
- Input messages to EchoClient.java

Android-PC
==============================
- Here android app acts as server socket and  java app acts as client
- Change port number in EchoClient.java to 8000
- Run below command for port forwarding
```
adb forward tcp:8000 tcp:9000
```
- Now launch android app
- Run java client app
- Send messages from client to server
