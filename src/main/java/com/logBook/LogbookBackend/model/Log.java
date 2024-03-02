package com.logBook.LogbookBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
public class Log implements LogInterface {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private double amount;
  private LogType logType;
  private String date;
  private String time;

  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn(name = "logUser_id")
  @JsonIgnore
  private LogUser logUser;

  public Log(double amount, LogType logType, String date, String time) {
    this.amount = amount;
    this.logType = logType;
    this.date = date;
    this.time = time;
  }

  public Log() {
    // default constructor
  }

  public double getAmount() {
    return amount;
  }

  public LogType getLogType() {
    return logType;
  }

  public String getDate() {
    return date;
  }

  public String getTime() {
    return time;
  }

  public LogUser getLogUser() {
    return logUser;
  }

  public void setLogUser(LogUser logUser) {
    this.logUser = logUser;
  }

  // implementation of interfaces starts here.
  @Override
  public boolean validateLog(Log log) {
    return false;
  }

  @Override
  public boolean logHasValue(Log log) {
    return false;
  }

}
