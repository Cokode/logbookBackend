package com.logBook.LogbookBackend.controller;

import com.logBook.LogbookBackend.model.Log;
import com.logBook.LogbookBackend.model.LogType;
import com.logBook.LogbookBackend.respository.LogRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BusinessLogic {
  private final LogRepository logRepository;

  @Autowired
  public BusinessLogic (LogRepository logRepository)  {
    this.logRepository = logRepository;
  }

  @PostConstruct
  public void add() {
    var scop = new Log( 50.4, LogType.BUY, "49909");
    var scop1 = new Log( 50.4, LogType.SALE, "49909");
    logRepository.save(scop);
    logRepository.save(scop1);
  }

  public ArrayList<Log> getLogHistory() {
    return (ArrayList<Log>) logRepository.findAll();
  }

  public boolean addBuyLog(Log log) {
    if(log.validateLog(log) && log.logHasValue(log)){
     // var newLog = new Log(log.getAmount(), LogType.BUY, "839383");
      logRepository.save(log);
      return true;
    }
    return false;
  }

  public boolean addSellLog(Log log) {
    if(log.validateLog(log) && log.logHasValue(log)){
      var newLog = new Log(log.getAmount(), LogType.SALE, "839383");
      logRepository.save(newLog);
      return true;
    }
    return false;
  }
}
