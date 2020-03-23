package com.xuqm.base.common.xlog.printer.file.clean;

import com.xuqm.base.common.xlog.printer.file.clean.CleanStrategy;

import java.io.File;

/**
 * Never Limit the file life.
 */
public class NeverCleanStrategy implements CleanStrategy {

  @Override
  public boolean shouldClean(File file) {
    return false;
  }
}
