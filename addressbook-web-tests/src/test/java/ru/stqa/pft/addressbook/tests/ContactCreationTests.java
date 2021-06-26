package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().returnHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();  // получение старого списка  контактов before
    app.getNavigatioHelper().goToAddingNewContact();
    ContactData contact = new ContactData("First_name_test", "Middle_name_test",
            "Last_name_test", "nick", "address_test", "123456789", "987654321", "test@test.test");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();  // получение нового списка  контактов after
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1,c2) ->Integer.compare(c1.getId(),c2.getId());   // сортировка списков по id группы
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
