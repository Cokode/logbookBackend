package com.logBook.LogbookBackend.controller;

import com.logBook.LogbookBackend.respository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BusinessLogic {

  private final LogRepository logRepository;

  @Autowired
  public BusinessLogic (LogRepository logRepository)  {
    this.logRepository = logRepository;
  }

  public void add() {
    //logRepository.save(new Log(4L, 50.4, LogType.BUY, "49909"));
  }

}
