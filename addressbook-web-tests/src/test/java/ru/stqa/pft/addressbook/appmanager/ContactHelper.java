package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends  HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnHomePage() {
   click(By.linkText("home"));
  }

  public void submitAddingContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillAddingContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstName());
    type(By.name("middlename"),contactData.getMiddleName());
    type(By.name("lastname"),contactData.getLastName());
    type(By.name("nickname"),contactData.getNick());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getPhoneHome());
    type(By.name("mobile"),contactData.getPhoneMobile());
    type(By.name("email"),contactData.getEmail());
  }

  public void selectContact() {
    click(By.xpath("//input[@type='checkbox']"));
  }

  public void editContact() {
//    click(By.xpath("//td//img[@title='Edit']"));
    click(By.xpath("//img[@title='Edit']"));
  }

  public void submitContactModification() {
    click(By.xpath("//input[@value='Update']"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@title='Edit']"));
  }

  public void createContact(ContactData contactData) {
    fillAddingContactForm(contactData);
    submitAddingContact();
    returnHomePage();
  }
}
