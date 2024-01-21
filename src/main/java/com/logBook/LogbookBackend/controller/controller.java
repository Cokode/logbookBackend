package com.logBook.LogbookBackend.controller;


import com.logBook.LogbookBackend.model.Log;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:5500/")
@RestController
@RequestMapping()
public class controller {
  private final BusinessLogic businessLogic;

  public controller(BusinessLogic businessLogic) {
    this.businessLogic = businessLogic;
  }

  @GetMapping("/get")
  public ArrayList<Log> getIt() {
    return businessLogic.getLogHistory();
  }

  @ResponseStatus
  @PostMapping("/log-record-sell")
  public void postSaleLog(@RequestBody Log log) {
    if(businessLogic.addSellLog(log)) {
      System.out.println("sell record succeeded");
      return;// code goes here Todo
    }
    // code goes here
    System.out.println("sell record did not succeed");

  }

  @ResponseStatus
  @PostMapping("/log-record-buy")
  public void postBuyLog(@RequestBody Log log) {
    if (businessLogic.addBuyLog(log)) {
      // code goes here Todo
      System.out.println("buy record succeeded");
      return;
    }
    // code goes here Todo
    System.out.println("buy record did not succeed");
  }
}
