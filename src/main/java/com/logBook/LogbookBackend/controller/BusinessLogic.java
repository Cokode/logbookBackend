package com.logBook.LogbookBackend.controller;

import com.logBook.LogbookBackend.model.Log;
import com.logBook.LogbookBackend.model.LogInterface;
import com.logBook.LogbookBackend.model.LogType;
import com.logBook.LogbookBackend.respository.LogRepository;
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

  public void add() {
    var scop = new Log  ( 50.4, LogType.BUY, "49909");
    var scop1 = new Log  ( 50.4, LogType.SALE, "49909");
    logRepository.save(scop);
    logRepository.save(scop1);
  }

  public ArrayList<Log> get() {
    ArrayList<Log> logg = (ArrayList<Log>) logRepository.findAll();
    return logg;
  }

}
