package com.logBook.LogbookBackend.controller;


import com.logBook.LogbookBackend.model.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping()
public class controller {
  private final BusinessLogic businessLogic;

  public controller(BusinessLogic businessLogic) {
    this.businessLogic = businessLogic;
  }

  @GetMapping("/get")
  public String getIt() {
    System.out.println("Hey it is working");
    businessLogic.add();
    return "Hello";
  }

  @GetMapping("/get2")
  public ArrayList<Log> getIt2() {
    System.out.println("Hey it is working");
    return businessLogic.get();
  }

}
