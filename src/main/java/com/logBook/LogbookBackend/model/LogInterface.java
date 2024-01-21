package com.logBook.LogbookBackend.model;

public interface LogInterface {
  public boolean validateLog(Log log);

  boolean logHasValue(Log log);
}
