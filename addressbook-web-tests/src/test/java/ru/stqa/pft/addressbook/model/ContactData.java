package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nick;
  private final String address;
  private final String phoneHome;
  private final String phoneMobile;
  private final String email;

  public ContactData(String firstName, String middleName, String lastName, String nick, String address, String phoneHome, String phoneMobile, String email) {
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
}
