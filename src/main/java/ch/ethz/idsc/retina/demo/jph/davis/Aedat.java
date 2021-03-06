// code by jph
package ch.ethz.idsc.retina.demo.jph.davis;

import java.io.File;

enum Aedat {
  LOG_01(new File("/tmp", "DAVIS240C-2017-08-03T16-55-01+0200-02460045-0.aedat")), //
  LOG_02(new File("/tmp", "DAVIS240C-2017-08-03T18-16-55+0200-02460045-0.aedat")), //
  LOG_03(new File("/tmp", "DAVIS240C-2017-08-04T10-13-29+0200-02460045-0.aedat")), //
  LOG_04(new File("/tmp", "DAVIS240C-2017-08-06T15-55-52+0200-02460045-0.aedat")), //
  ;
  public final File file;

  private Aedat(File file) {
    this.file = file;
  }
}
