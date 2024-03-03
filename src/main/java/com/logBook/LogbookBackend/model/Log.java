package com.logBook.LogbookBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
public class Log {
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
}
