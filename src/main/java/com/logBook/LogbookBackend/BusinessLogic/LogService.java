package com.logBook.LogbookBackend.BusinessLogic;

import com.logBook.LogbookBackend.model.Log;
import com.logBook.LogbookBackend.model.LogType;
import com.logBook.LogbookBackend.model.LogUser;
import com.logBook.LogbookBackend.respository.LogRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LogService {
  private final LogRepository logRepository;

  @Autowired
  public LogService (LogRepository logRepository) {
    this.logRepository = logRepository;
  }

  public void addLogToUser (@NotNull @RequestBody Log newLog) {
//    LogUser logUser = logRepository.findLogByUser(userUsername);

  }

  public List<Log> getLogs(String email, String password) {
    return logRepository.findLogByLogUserEmailAndLogUserPassword(email, password);
  }

  public List<Log> getLogs(long id) {
    return logRepository.findLogByLogUserId(id);
  }

  public List<Log> getAllof() {
    return logRepository.findAll();
  }

//  @PostConstruct
//  public void addLog () {
//    Log log = new Log(45.3, LogType.BUY, "784939", "8393");
//    System.out.println(log);
//    logRepository.save(log);
//  }

}
