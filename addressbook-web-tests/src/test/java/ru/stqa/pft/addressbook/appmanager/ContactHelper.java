package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

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
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNick());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhoneHome());
    type(By.name("mobile"), contactData.getPhoneMobile());
    type(By.name("email"), contactData.getEmail());
  }

  public void select(int index) {
    wd.findElements(By.xpath("//td/input[@type='checkbox']")).get(index).click();
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[@value='Update']"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contactData) {
    fillAddingContactForm(contactData);
    submitAddingContact();
    returnHomePage();
  }

  public void modify(int index, ContactData contact) {
    editContact(index);
    fillAddingContactForm(contact);
    submitContactModification();
    returnHomePage();
  }

  public void delete(int index) {
    select(index);
    deleteSelectedContact();
    returnHomePage();
  }


  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@title='Edit']"));
  }


  public int getContactCount() {
    return wd.findElements(By.xpath("//td/input[@type='checkbox']")).size();
  }


  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name=\"entry\"]")); // список строк с контактами
    for (WebElement element : elements) {
      String lastName = element.findElement(By.xpath("./td[2]")).getText();
      String firstName = element.findElement(By.xpath("./td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
      contacts.add(contact);
    }
    return contacts;
  }
}
