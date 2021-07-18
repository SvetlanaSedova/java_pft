package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
    if (contactData.getEmailSecond() != null) {
      type(By.name("email2"), contactData.getEmailSecond());
    }
    if (contactData.getEmailThird() != null) {
      type(By.name("email3"), contactData.getEmailThird());
    }
    if (contactData.getPhoto() != null) {
      attach(By.name("photo"), contactData.getPhoto());
    }
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

  public void addContactToGroup(ContactData contact, GroupData group) {
    selectById(contact.getId());
    Select select = new Select(wd.findElement(By.name("to_group")));
    select.selectByValue(Integer.toString(group.getId()));
    wd.findElement(By.name("add")).click();
  }

  public boolean isContactInGroup(ContactData contact, GroupData group) {
    Groups groups = contact.getGroups();
    boolean isItInGroup = false;
    for (GroupData groupOfContact : groups) {
      if(groupOfContact.getId() == group.getId()){
        isItInGroup = true;
      }
    }
    return isItInGroup;
  }

  public void removeContactFromGroup(ContactData contact, GroupData group) {
//    wd.findElement(By.name("group")).click();
    filterContactsByGroupName(group.getName());
    wd.findElement(By.id(Integer.toString(contact.getId()))).click();
    wd.findElement(By.name("remove")).click();

  }

  public void filterContactsByGroupName(String groupName) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
  }

  public ContactData GetContactFromSetById(Contacts contacts, int id) {
    ContactData addedContact = new ContactData();
    for (ContactData contact : contacts) {
      if (contact.getId() == id) {
        addedContact = contact;
      }
    }
    return addedContact;
  }

  public ContactData getContactWithoutAnyGroup(Contacts contacts, Groups groups) {
    ContactData contact = null;
    for (ContactData contactFromSet : contacts) {                    //ищем контакт, который не находится хотя бы в одной группе
      if (contactFromSet.getGroups().size() != groups.size()) {
        contact = contactFromSet;
        break;
      }
    }
    return contact;
  }


  public GroupData getGroupNotContainsContact(Groups groups, ContactData contact) {
    GroupData group = null;
    for (GroupData groupFromSet : groups) {
      if (!contact.getGroups().contains(groupFromSet)) {
        group = groupFromSet;
        break;
      }
    }
    return group;
  }

  public ContactData getContactWithGroup(Contacts contacts) {
    ContactData contactWithGroup = null;
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() != 0) {
        contactWithGroup = contact;
        break;
      }
    }
    return contactWithGroup;
  }

  public GroupData getGroupFromContact(Groups groups, ContactData contact) {
    GroupData groupFromContact = null;
    for (GroupData group : groups) { // ищем группу в контакте
      if (contact.getGroups().contains(group)) {
        groupFromContact = group;
        break;
      }
    }
    return groupFromContact;
  }

}
