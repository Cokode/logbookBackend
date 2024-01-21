package com.logBook.LogbookBackend.model;

import jakarta.persistence.*;

@Entity
@Table
public class Log implements LogInterface {
  @Id
  @SequenceGenerator(
          name = "student_sequence",
          sequenceName = "student_sequence",
          allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;
  private double amount;
  private LogType logType;
  private String date;
  private String time;

  public Log( double amount, LogType logType, String date, String time) {
    this.amount = amount;
    this.logType = logType;
    this.date = date;
    this.time = time;
  }
  public Log() {
    // default constructor
  }

  // implementation of interfaces starts here.
  @Override
  public boolean validateLog(Log log) {
    return log != null;
  }

  @Override
  public boolean logHasValue(Log log) {
    return log.amount >= 1.0 && log
            .date != null && log
            .logType != null && log
            .time != null;
  }

  public Long getID() {
    return ID;
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
}
