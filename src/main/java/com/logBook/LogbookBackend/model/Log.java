package com.logBook.LogbookBackend.model;

import jakarta.persistence.*;

@Entity
public class Log implements LogInterface {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;
  private double amount;
  private LogType logType;
  private String date;
  private String time;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Log(double amount, LogType logType, String date, String time, User user) {
    this.amount = amount;
    this.logType = logType;
    this.date = date;
    this.time = time;
    this.user = user;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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
