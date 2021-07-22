package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class User {
  @Id
  public int id;
  public String username;
  public String email;

  public User(String username, String email) {
    this.id = id;
    this.username = username;
    this.email = email;
  }

  public User() {  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }
}
