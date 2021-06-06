package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    goToAddingNewContact();
    fillAddingContactForm(new ContactData("First_name_test", "Middle_name_test", "Last_name_test", "nick", "address_test", "123456789", "987654321", "test@test.test"));
    submitAddingContact();
    returnHomePage();
  }

}
