package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{
  @Test

  public void testContactDeletion(){
    app.getContactHelper().returnHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigatioHelper().goToAddingNewContact();
      app.getContactHelper().createContact(new ContactData("First_name_test", "Middle_name_test", "Last_name_test", "nick", "address_test", "123456789", "987654321", "test@test.test"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().returnHomePage();
  }

}
