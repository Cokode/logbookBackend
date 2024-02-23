package com.logBook.LogbookBackend.BusinessLogic;

import com.logBook.LogbookBackend.model.*;
import com.logBook.LogbookBackend.respository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
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

  public Optional<List<Log>> addLog(Log log) {
    userRepository.findUserByEmail("fuckyou@yahoo.com").addToLogs(log);
    List<Log> logs = (userRepository.findUserByEmail("fuckyou@yahoo.com").getLog());
    System.out.println(logs.size());
    return Optional.of(userRepository.findUserByEmail("fuckyou@yahoo.com").getLog());
  }

  public boolean updateUserInformation(LogUpdateBody logUpdateBody, String type) {
    assert logUpdateBody != null && !type.isEmpty();

    String value = logUpdateBody.data();
    String userName = logUpdateBody.UserName();

    return switch (type) {
      case "email" -> executeUpdate(1, value, userName);

      case "username" -> executeUpdate(2,value, userName);

      case "firstName" -> executeUpdate(3, value, userName);

      case "middleName" -> executeUpdate(4,value, userName);

      case "lastName" -> executeUpdate(5, value, userName);

      case "password" -> executeUpdate(6,value, userName);

      case "phoneNumber" -> executeUpdate(7,value, userName);

      case "dateOfBirth" -> executeUpdate(8, value, userName);
    };
  }

  public boolean executeUpdate(int index, String value, String userName) {

    if(userRepository.findUserByUserName(userName) == null) {
      return false;
    }

    switch (index) {
      case 1 -> {
        userRepository.findUserByUserName(userName).setEmail(value);
      }
      case 2 -> {
        userRepository.findUserByUserName(userName).setUserName(value);
      }
      case 3 -> {
        userRepository.findUserByUserName(userName).setFirstName(value);
      }

      case 4 -> {
        userRepository.findUserByUserName(userName).setMiddleName(value);
      }
      case 5 -> {
        userRepository.findUserByUserName(userName).setLastName(value);
      }
      case 6 -> {
        userRepository.findUserByUserName(userName).setPassword(value);
      }
      case 7 -> {
        userRepository.findUserByUserName(userName).setPhoneNumber(value);
      }
      case 8 -> {
        userRepository.findUserByUserName(userName).setDateOfBirth(value);
      }
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
