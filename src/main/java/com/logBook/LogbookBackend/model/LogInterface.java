package com.logBook.LogbookBackend.model;

public interface LogInterface {
  boolean validateLog(Log log);

  boolean logHasValue(Log log);
}
