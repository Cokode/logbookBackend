package com.logBook.LogbookBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {
  @Id
  @SequenceGenerator(
          name = "student_sequence",
          sequenceName = "student_sequence",
          allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;
  private String userName;
  @NotBlank
  private String FirstName;
  private String MiddleName;
  @NotBlank
  private String LastName;
  @Email @NotBlank
  private String email;
  @NumberFormat @NotBlank
  private String phoneNumber;

  @NotBlank
  private String password;

  @NotBlank
  private LocalDate dateOfBirth;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
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
    this.logs = new ArrayList<>(1000);
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

  public List<Log> getLog() {
    return logs;
  }
}
