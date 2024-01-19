package com.logBook.LogbookBackend.controller;


import com.logBook.LogbookBackend.model.Log;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
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
  public ArrayList<Log> getIt() {
    System.out.println("Hey it is working");
    return businessLogic.getLogHistory();
  }

  @ResponseStatus
  @PostMapping("/log-record-sell")
  public void postSaleLog(@RequestBody Log log) {
    if(businessLogic.addSellLog(log)) {
      System.out.println("succeeded");// code goes here Todo
    }
    // code goes here
    System.out.println("did not succeed");

  }

  @PostMapping("/log-record-buy")
  public void postBuyLog(@RequestBody Log log) {
    if (businessLogic.addBuyLog(log)) {
      // code goes here Todo
      System.out.println("succeeded");
    }
    // code goes here Todo
    System.out.println("did not succeed");
  }

}
