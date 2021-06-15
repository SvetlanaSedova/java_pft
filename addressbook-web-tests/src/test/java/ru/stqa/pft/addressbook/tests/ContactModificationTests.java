package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getContactHelper().returnHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigatioHelper().goToAddingNewContact();
      app.getContactHelper().createContact(new ContactData("First_name_test", "Middle_name_test", "Last_name_test", "nick", "address_test", "123456789", "987654321", "test@test.test"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillAddingContactForm(new ContactData("First_name_test1", "Middle_name_test1", "Last_name_test1", "nick1", "address_test1", "1234567891", "9876543211", "test1@test.test"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnHomePage();

  }
}
