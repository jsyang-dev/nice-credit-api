package link.credit.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

@Component
@Slf4j
public class SocketSender {

  public String send(String text, String host, int port) {

    StringBuilder receivedText = new StringBuilder();
    log.info("[Send] \"" + text + "\"");

    try (Socket socket = new Socket(host, port)) {
      log.debug("socket connected: " + host + ":" + port);
      socket.setSoTimeout(30000);

      PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(socket.getInputStream(), "EUC-KR"));

      writer.println(text);
      receivedText = new StringBuilder(reader.readLine());
      log.info("[Receive] \"" + receivedText + "\"");

    } catch (UnknownHostException e) {
      log.error("Server not found: " + e.toString());
    } catch (IOException e) {
      log.error("I/O Exception: " + e.toString());
    }

    return receivedText.toString();
  }
}
