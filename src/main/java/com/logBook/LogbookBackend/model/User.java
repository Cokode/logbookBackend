package com.logBook.LogbookBackend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
  private Long ID;
  private String userName;
  private String FirstName;
  private String MiddleName;
  private String LastName;
  private String email;
  private String phoneNumber;
  private String password;
  private LocalDate dateOfBirth;
  private List<Log> logs;

  public User() {
  }

  public User(String userName,
              String firstName,
              String middleName,
              String lastName,
              String email,
              String phoneNumber,
              String password,
              LocalDate dateOfBirth) {
    this.userName = userName;
    FirstName = firstName;
    MiddleName = middleName;
    LastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.dateOfBirth = dateOfBirth;
    this.logs = new ArrayList<>();
  }

  public Long getID() {
    return ID;
  }

  public void setID(Long ID) {
    this.ID = ID;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return FirstName;
  }

  public void setFirstName(String firstName) {
    FirstName = firstName;
  }

  public String getMiddleName() {
    return MiddleName;
  }

  public void setMiddleName(String middleName) {
    MiddleName = middleName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Log getLog() {
    return (Log) logs;
  }
}
