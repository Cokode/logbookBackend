package com.logBook.LogbookBackend.BusinessLogic;

import com.logBook.LogbookBackend.model.Log;
import com.logBook.LogbookBackend.respository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
  private final LogRepository logRepository;

  @Autowired
  public LogService (LogRepository logRepository) {
    this.logRepository = logRepository;
  }



}
