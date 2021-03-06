// code by jph
package ch.ethz.idsc.retina.dev.hdl32e;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.LinkedList;
import java.util.List;

import ch.ethz.idsc.retina.core.StartAndStoppable;
import ch.ethz.idsc.retina.util.GlobalAssert;
import ch.ethz.idsc.retina.util.io.ByteArrayConsumer;

/** the lidar device Velodyne HDL32E publishes firing data on port 2368
 * 
 * the class listens to the data and distributes the data to listeners
 * 
 * example use is for further distribution via LCM protocol */
public class Hdl32eRayDatagramClient implements StartAndStoppable {
  public static final int LENGTH = 1206;
  // ---
  private final int port;
  private final List<ByteArrayConsumer> listeners = new LinkedList<>();
  private boolean isLaunched;

  public Hdl32eRayDatagramClient(int port) {
    this.port = port;
  }

  public void addListener(ByteArrayConsumer pcapPacketConsumer) {
    listeners.add(pcapPacketConsumer);
  }

  @Override
  public void start() {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        isLaunched = true;
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
          byte[] bytes = new byte[LENGTH];
          DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
          while (isLaunched) {
            datagramSocket.receive(datagramPacket);
            GlobalAssert.that(datagramPacket.getLength() == LENGTH);
            listeners.forEach(listener -> listener.accept(bytes, datagramPacket.getLength()));
          }
          datagramSocket.close();
          System.out.println("socket closed.");
        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }
    };
    Thread thread = new Thread(runnable);
    thread.start();
  }

  @Override
  public void stop() {
    isLaunched = false;
  }
}
