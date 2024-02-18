package com.logBook.LogbookBackend.BusinessLogic;

import com.logBook.LogbookBackend.model.Log;
import com.logBook.LogbookBackend.model.LogType;
import com.logBook.LogbookBackend.model.LogUser;
import com.logBook.LogbookBackend.model.LoginDetails;
import com.logBook.LogbookBackend.respository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public boolean updateUserName (@NotBlank String newUserName, @NotBlank String email) {
    userRepository.findUserByEmail(email).setUserName(newUserName);
    return true;
  }

  public boolean updateUserPhoneNumber (@NotBlank String newPhoneNumber, @NotBlank String email) {
    userRepository.findUserByEmail(email).setPhoneNumber(newPhoneNumber);
    return true;
  }

  public boolean verifyUserLogin(LoginDetails loginDetails) {
    String email = loginDetails.email();
    String password = loginDetails.password();

    LogUser logUser = userRepository.findUserByEmail(email);
    LogUser logUser1 = userRepository.findUserByPhoneNumber(email);

    logUser = logUser == null ? logUser1 : logUser;

    if (logUser != null) {
      return logUser.getPassword().equals(password)
              && (logUser.getEmail().equals(email)
              ||  logUser.getPhoneNumber().equals(email));
    }
    return false;
  }

  public List<LogUser> getAll() {
    return userRepository.findAll();
  }
}
