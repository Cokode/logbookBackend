package com.logBook.LogbookBackend.BusinessLogic;

import com.logBook.LogbookBackend.model.LogUser;
import com.logBook.LogbookBackend.respository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public LogUser createUser (@NotNull LogUser newLogUser) {
    LogUser logUser;
    if (userRepository.findUserByUserName(
            newLogUser.getUserName()) == null
      && userRepository.findUserByEmail(newLogUser.getEmail()) == null) {

      logUser = newLogUser;
      userRepository.save(logUser);
      return logUser;
    }
    return null;
  }

  public boolean updateUserName (@NotBlank String newUserName, @NotBlank String email) {
    LogUser logUser = userRepository.findUserByEmail(email);
    logUser.setUserName(newUserName);

    return true;
  }

  public boolean updateUserPhoneNumber (@NotBlank String newPhoneNumber, @NotBlank String email) {
    LogUser logUser = userRepository.findUserByEmail(email);
    logUser.setPhoneNumber(newPhoneNumber);

    return true;
  }

}
