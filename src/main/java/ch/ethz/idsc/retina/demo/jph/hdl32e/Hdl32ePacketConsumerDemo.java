// code by jph
package ch.ethz.idsc.retina.demo.jph.hdl32e;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import ch.ethz.idsc.retina.dev.hdl32e.Hdl32ePacketProvider;
import ch.ethz.idsc.retina.dev.hdl32e.Hdl32eRayBlockListener;
import ch.ethz.idsc.retina.dev.hdl32e.data.Hdl32ePanorama;
import ch.ethz.idsc.retina.dev.hdl32e.data.Hdl32ePanoramaCollector;
import ch.ethz.idsc.retina.dev.hdl32e.data.Hdl32ePanoramaListener;
import ch.ethz.idsc.retina.util.io.PcapParse;

enum Hdl32ePacketConsumerDemo {
  ;
  public static void main(String[] args) throws Exception {
    @SuppressWarnings("unused")
    Hdl32eRayBlockListener hdl32ePositionListener = new Hdl32eRayBlockListener() {
      @Override
      public void digest(FloatBuffer fb, ByteBuffer bb) {
        System.out.println("here");
      }
    };
    Hdl32ePanoramaListener hdl32ePanoramaListener = new Hdl32ePanoramaListener() {
      @Override
      public void panorama(Hdl32ePanorama hdl32ePanorama) {
        // System.out.println("here");
      }

      @Override
      public void close() {
        // ---
      }
    };
    Hdl32ePanoramaCollector hdl32ePanoramaCollector = new Hdl32ePanoramaCollector();
    hdl32ePanoramaCollector.addListener(hdl32ePanoramaListener);
    Hdl32ePacketProvider packetConsumer = new Hdl32ePacketProvider();
    packetConsumer.hdl32eFiringDecoder.addListener(hdl32ePanoramaCollector);
    PcapParse.of(Pcap.TUNNEL.file, packetConsumer);
  }
}
