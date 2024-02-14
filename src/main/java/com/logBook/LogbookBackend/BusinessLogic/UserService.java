package com.logBook.LogbookBackend.BusinessLogic;

import com.logBook.LogbookBackend.model.User;
import com.logBook.LogbookBackend.respository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean createUser (@NotNull User newUser) {
    User user;
    if (userRepository.findUserByUserName(
            newUser.getUserName()) == null
      && userRepository.findUserByEmail(newUser.getEmail()) == null) {

      user = newUser;
      userRepository.save(user);
      return true;
    }
    return false;
  }

  public boolean updateUserName (@NotBlank String newUserName, @NotBlank String email) {
    User user = userRepository.findUserByEmail(email);
    user.setUserName(newUserName);

    return true;
  }

  public boolean updateUserPhoneNumber (@NotBlank String newPhoneNumber, @NotBlank String email) {
    User user = userRepository.findUserByEmail(email);
    user.setPhoneNumber(newPhoneNumber);

    return true;
  }

}
