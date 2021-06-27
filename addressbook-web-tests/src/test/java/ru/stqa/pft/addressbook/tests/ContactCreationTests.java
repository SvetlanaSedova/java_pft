package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.goTo().HomePage();
    Set<ContactData> before = app.contact().all();  // получение старого множества контактов before
    app.goTo().AddingNewContact();
    ContactData contact = new ContactData().withFirstName("First_name_test").withMiddleName("Middle_name_test")
            .withLastName("Last_name_test").withNick("nick").withAddress("address_test").withPhoneHome("123456789")
               .withPhoneMobile("987654321").withEmail("test@test.test");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();  // получение нового множества контактов after
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
