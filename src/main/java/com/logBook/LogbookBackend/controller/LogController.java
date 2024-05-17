package com.logBook.LogbookBackend.controller;

import com.logBook.LogbookBackend.BusinessLogic.LogService;
import com.logBook.LogbookBackend.model.Log;
import com.logBook.LogbookBackend.model.LoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping()
public class LogController {
  private final LogService logService;

  @Autowired
  public LogController( LogService logService) {
    this.logService = logService;
  }

  @PostMapping("/userLog")
  public List<Log> getUserLog(@RequestBody LoginDetails loginDetails) {
    return logService.getLogs(loginDetails.email(), loginDetails.password());
  }

  @GetMapping("/user-Logs")
  public List<Log> getUserLogs(@RequestParam long id) {
    return logService.getLogs(id);
  }

  @GetMapping("/allof")
  public List<Log> allof() {
    return logService.getAllof();
  }

}
