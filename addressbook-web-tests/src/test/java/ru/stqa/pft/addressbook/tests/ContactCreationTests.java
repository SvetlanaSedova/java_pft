package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    app.getNavigatioHelper().goToAddingNewContact();
    app.getContactHelper().fillAddingContactForm(new ContactData("First_name_test", "Middle_name_test", "Last_name_test", "nick", "address_test", "123456789", "987654321", "test@test.test"));
    app.getContactHelper().submitAddingContact();
    app.getContactHelper().returnHomePage();
  }

}
