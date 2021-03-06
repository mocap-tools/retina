// code by jph
package ch.ethz.idsc.retina.dev.davis.data;

import java.nio.ByteBuffer;

/** notifies that block of aps columns is completed */
public interface DavisApsBlockListener {
  void apsBlock(int length, ByteBuffer byteBuffer);
}
