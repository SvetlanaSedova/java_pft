package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size()==0) {        // проверяем, есть ли контакты для изменения . если нет, то создае
      app.goTo().AddingNewContact();
      app.contact().create(new ContactData().withFirstName("First_name_test").withMiddleName("Middle_name_test")
              .withLastName("Last_name_test").withNick("nick").withAddress("address_test")
              .withPhoneHome("123456789").withPhoneMobile("987654321").withEmail("test@test.test"));
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();  // получение старого списка контактов before
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstName("First_name_test1").withMiddleName("Middle_name_test1")
            .withLastName("Last_name_test1").withNick("nick1").withAddress("address_test1")
            .withPhoneHome("1234567891").withPhoneMobile("9876543211").withEmail("test1@test.test");
    app.contact().modify(modifiedContact,contact);
    Set<ContactData> after = app.contact().all();     // получение нового списка  контактов after
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);

  }
}
