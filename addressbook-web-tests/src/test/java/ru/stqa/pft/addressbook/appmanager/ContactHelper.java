package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    type(By.name("work"), contactData.getPhoneWork());
    type(By.name("email"), contactData.getEmail());
    if(contactData.getEmailSecond() != null){
    type(By.name("email2"), contactData.getEmailSecond());}
    if(contactData.getEmailThird() != null){
    type(By.name("email3"), contactData.getEmailThird());}
    attach(By.name("photo"), contactData.getPhoto());
  }

  private void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }


  private void initModificationContactByID(int id) {
    wd.findElement(By.xpath("//input[@value='" + id + "'] //..//..//img[@title='Edit']")).click();
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
    contactCache = null;
    returnHomePage();
  }

  public void modify(ContactData modifiedContact, ContactData contact) {
    initModificationContactByID(modifiedContact.getId());
    fillAddingContactForm(contact);
    submitContactModification();
    contactCache = null;
    returnHomePage();
  }


  public void delete(ContactData contact) {
    selectById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    returnHomePage();
  }


  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@title='Edit']"));
  }


  public int count() {
    return wd.findElements(By.xpath("//td/input[@type='checkbox']")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name=\"entry\"]")); // список строк с контактами
    for (WebElement element : elements) {
      String lastName = element.findElement(By.xpath("./td[2]")).getText();
      String firstName = element.findElement(By.xpath("./td[3]")).getText();
      String allPhones = element.findElement(By.xpath("./td[6]")).getText();
      String allEmails = element.findElement(By.xpath("./td[5]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withAllEmails(allEmails);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initModificationContactByID(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname)
            .withLastName(lastname).withPhoneHome(home).withPhoneWork(work).withPhoneMobile(mobile)
            .withEmail(email).withEmailSecond(email2).withEmailThird(email3);
  }
}
