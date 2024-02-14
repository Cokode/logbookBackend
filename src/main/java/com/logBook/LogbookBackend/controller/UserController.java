package com.logBook.LogbookBackend.controller;

import com.logBook.LogbookBackend.BusinessLogic.UserService;
import com.logBook.LogbookBackend.model.LogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping()
public class UserController {

  private final UserService userService;


  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/createUser")
  public LogUser createUser(@RequestBody LogUser logUser) {
   return  userService.createUser(logUser);
  }

}