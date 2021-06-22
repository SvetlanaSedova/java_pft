package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nick;
  private final String address;
  private final String phoneHome;
  private final String phoneMobile;
  private final String email;

  public ContactData(int id, String firstName, String middleName, String lastName, String nick, String address, String phoneHome, String phoneMobile, String email) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nick = nick;
    this.address = address;
    this.phoneHome = phoneHome;
    this.phoneMobile = phoneMobile;
    this.email = email;
  }

  public ContactData(String firstName, String middleName, String lastName, String nick, String address, String phoneHome, String phoneMobile, String email) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nick = nick;
    this.address = address;
    this.phoneHome = phoneHome;
    this.phoneMobile = phoneMobile;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNick() {
    return nick;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneHome() {
    return phoneHome;
  }

  public String getPhoneMobile() {
    return phoneMobile;
  }

  public String getEmail() {
    return email;
  }

  public int getId() { return id;  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

}
