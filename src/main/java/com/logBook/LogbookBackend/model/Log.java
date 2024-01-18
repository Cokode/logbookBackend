package com.logBook.LogbookBackend.model;

import jakarta.persistence.*;

  @Entity
  @Table
  public record Log(@Id @GeneratedValue(
          strategy = GenerationType.IDENTITY) Long id,
                    double amount, LogType logType, String date) implements LogInterface{



  }
