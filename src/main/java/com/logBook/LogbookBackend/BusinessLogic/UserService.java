package com.logBook.LogbookBackend.BusinessLogic;

import com.logBook.LogbookBackend.model.*;
import com.logBook.LogbookBackend.respository.LogRepository;
import com.logBook.LogbookBackend.respository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
  private final UserRepository userRepository;
  private final LogRepository logRepository;

  @Autowired
  public UserService(UserRepository userRepository, LogRepository logRepository) {
    this.userRepository = userRepository;
    this.logRepository = logRepository;
  }

  public LogUser createUser (@NotNull LogUser newLogUser) throws Exception {
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

    System.out.println(logUser + " logUser after collecting information.");

    return logUser;
  }

  public boolean verifyPayLoadInformation(LogUser newLogUser) throws Exception{
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

  public LogUser addLog(Log log) {
    LogUser logUser = userRepository.findUserByEmail("rolandi@gmail.com");
    List<Log> logs = logUser.getLog();
    logUser.setLogs(null);
    log.setLogUser(logUser);

    if (logs == null) {
      logs = new ArrayList<>();
    }

    logs.add(log);
    logUser.setLogs(logs);

    userRepository.save(logUser);
    System.out.println(userRepository.findUserByEmail("rolandi@gmail.com").getLog().size());
    return userRepository.findUserByEmail("rolandi@gmail.com");
  }

  public boolean updateUserInformation(LogUpdateBody logUpdateBody) {
    assert logUpdateBody != null && !logUpdateBody.type().isEmpty();

    if(verifyUserName(logUpdateBody.userName())) {
      return false;
    }

    String value = logUpdateBody.data();
    String userName = logUpdateBody.userName();
    String type = logUpdateBody.type();

    LogUser logUser = userRepository.findUserByUserName(userName);

      switch (type) {
      case "email" -> logUser.setEmail(value);

      case "username" -> logUser.setUserName(value);

      case "firstName" ->  logUser.setFirstName(value);

      case "middleName" -> logUser.setMiddleName(value);

      case "lastName" -> logUser.setLastName(value);

      case "password" -> logUser.setPassword(value);

      case "phoneNumber" -> logUser.setPhoneNumber(value);

      case "dateOfBirth" -> logUser.setDateOfBirth(value);

        default -> {
          return false;
        }
      }

    try {
      userRepository.save(logUser);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }

//  @PostConstruct
//  public void addLog () {
//    Log log = new Log(45.3, LogType.BUY, "784939", "8393");
//    System.out.println(log);
//    userRepository.findUserById(1L).getLog().add(log);
//  }
}
