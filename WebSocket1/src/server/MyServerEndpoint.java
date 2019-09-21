package server;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
@ServerEndpoint(value = "/server1")
public class MyServerEndpoint {
  @OnOpen
  public void handleOpen() {
    System.out.println("Client is now connected!");
  }
  @OnMessage
  public String handleMessage(String message) {
    System.out.println("Receive from client: " + message);
    String responseMessage = "Echo " + message;
    System.out.println("Send to client: " + responseMessage);
    return responseMessage;
  }
  @OnClose
  public void handleClose() {
    System.out.println("Client is now disconnected!");
  }
  @OnError
  public void handleError(Throwable t) {
    t.printStackTrace();
  }
}