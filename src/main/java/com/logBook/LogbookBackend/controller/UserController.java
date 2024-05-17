package com.logBook.LogbookBackend.controller;

import com.logBook.LogbookBackend.BusinessLogic.UserService;
import com.logBook.LogbookBackend.model.Log;
import com.logBook.LogbookBackend.model.LogUpdateBody;
import com.logBook.LogbookBackend.model.LogUser;
import com.logBook.LogbookBackend.model.LoginDetails;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    if (userService.createUser(logUser) == null) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(400));
    }
    throw new ResponseStatusException(HttpStatusCode.valueOf(201));
  }

  @GetMapping("/verifyUserName")
  public ResponseEntity<HttpStatus> verifyUserName (@RequestParam("userName") String userName) {
    if(!userService.verifyUserName(userName)) {
      System.out.println("user found");
     return ResponseEntity.badRequest().build();
    }

    System.out.println("user not found. I was here ERROR!");
    return ResponseEntity.ok().build();
  }

  // TODO ignore case in email also, remove password
      // field before sending object to client side.
  @PostMapping("/verifyLogin")
  public Optional<LogUser> verifyLogin (@RequestBody @NotNull LoginDetails loginInfo) {
    return Optional.ofNullable(userService.getUser(loginInfo));
  }

  @GetMapping("/getAll")
  public List<LogUser> allOf() {
    return userService.getAll();
  }

  @PostMapping("/addLog")
  public LogUser addLog(@NotNull @RequestBody Log log, @RequestParam String email) {
    return userService.addLog(log, email);
  }

  @PutMapping("/updateInfo")
  public LogUser updateUserInformation (@RequestBody @NotNull LogUser logUser) {
    return userService.updateUserInformation(logUser);
  }

}