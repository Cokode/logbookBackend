package com.logBook.LogbookBackend.BusinessLogic;

import com.logBook.LogbookBackend.model.*;
import com.logBook.LogbookBackend.respository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;

  }

  public LogUser createUser (@NotNull LogUser newLogUser) {
   if (verifyUserDoesNotExist(newLogUser)) return null;
   LogUser logUser = collectPayLoadInformation(newLogUser);

   if (logUser != null) {
     boolean bol = verifyPayLoadInformation(logUser);
     if(bol) userRepository.save(logUser);
     return logUser;
   }

   return null;
  }

  public boolean verifyUserDoesNotExist (LogUser logUser) {
    return userRepository.findAll().contains(logUser);
  }

  public LogUser collectPayLoadInformation(@NotNull LogUser newLogUser) {

    if (!(verifyEmail(newLogUser.getEmail())
      && verifyUserName(newLogUser.getUserName())
      && verifyPhoneNumber(newLogUser.getPhoneNumber()))) {
      return null;
    }

    LogUser logUser = new LogUser();

    logUser.setUserName(newLogUser.getUserName());
    logUser.setFirstName(newLogUser.getFirstName());
    logUser.setMiddleName(newLogUser.getMiddleName());
    logUser.setLastName(newLogUser.getLastName());
    logUser.setEmail(newLogUser.getEmail());
    logUser.setPhoneNumber(newLogUser.getPhoneNumber());
    logUser.setPassword(newLogUser.getPassword());
    logUser.setDateOfBirth(newLogUser.getDateOfBirth());

    return logUser;
  }

  public boolean verifyPayLoadInformation(LogUser newLogUser) throws NullPointerException{
    return newLogUser.getUserName() != null &&
            newLogUser.getFirstName() != null &&
            newLogUser.getLastName() != null &&
            newLogUser.getEmail() != null &&
            newLogUser.getPassword() != null &&
            newLogUser.getDateOfBirth() != null &&
            newLogUser.getPhoneNumber() != null;
  }

  public LogUser getUser(LoginDetails loginDetails) {
    LogUser logUser = null;

    if (verifyUserLogin(loginDetails)) {
      logUser = userRepository.findUserByEmail(loginDetails.email()) != null ?
        userRepository.findUserByEmail(loginDetails.email()) :
          userRepository.findUserByPhoneNumber(loginDetails.email());
    }
    return logUser;
  }

  public boolean verifyEmail (String email) {
    return userRepository.findUserByEmail(email) == null;
  }

  public boolean verifyUserName (String username) {
    return userRepository.findUserByUserName(username) == null;
  }

  public boolean verifyPhoneNumber (String phoneNumber) {
    return userRepository.findUserByPhoneNumber(phoneNumber) == null;
  }

  public boolean verifyUserLogin(LoginDetails loginDetails) {
    String email = loginDetails.email();
    String password = loginDetails.password();

    LogUser logUser = userRepository.findUserByEmail(email);
    LogUser logUser1 = userRepository.findUserByPhoneNumber(email);

    logUser = (logUser == null ? logUser1 : logUser);

    if (logUser != null) {
      return logUser.getPassword().equals(password)
              && ( (logUser.getEmail().equals(email)
              || logUser.getPhoneNumber().equals(email)) );
    }
    return false;
  }

  public List<LogUser> getAll() {
    return userRepository.findAll();
  }

  public LogUser addLog(Log log, String email) {
    System.out.println(email + " this is the email");
    LogUser logUser = userRepository.findUserByEmail(email);
    List<Log> logs = logUser.getLogs();
    logUser.setLogs(null);
    log.setLogUser(logUser);

    if (logs == null) {
      logs = new ArrayList<>();
    }

    logs.add(log);
    logUser.setLogs(logs);

    userRepository.save(logUser);
    System.out.println(userRepository.findUserByEmail(email).getLogs().size());
    return userRepository.findUserByEmail(email);
  }

  public LogUser updateUserInformation(LogUser logUser) {
    assert logUser != null && !logUser.getEmail().isEmpty();

    if(verifyUserName(logUser.getUserName())) {
      return null;
    }

    String userName = logUser.getUserName();

    LogUser getLogUser = userRepository.findUserByUserName(userName);

    getLogUser.setEmail(logUser.getEmail());
    getLogUser.setUserName(logUser.getUserName());
    getLogUser.setFirstName(logUser.getFirstName());
    getLogUser.setMiddleName(logUser.getMiddleName());
    getLogUser.setLastName(logUser.getLastName());
    getLogUser.setPassword(logUser.getPassword());
    getLogUser.setPhoneNumber(logUser.getPhoneNumber());
    getLogUser.setDateOfBirth(logUser.getDateOfBirth());

    try {
      userRepository.save(getLogUser);
      return getLogUser;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

//  @PostConstruct
//  public void addLog () {
//    Log log = new Log(45.3, LogType.BUY, "784939", "8393");
//    System.out.println(log);
//    userRepository.findUserById(1L).getLog().add(log);
//  }
}
