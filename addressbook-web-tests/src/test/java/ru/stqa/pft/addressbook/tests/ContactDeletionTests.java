package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.db().contacts().size()==0) {        // проверяем, есть ли контакты для изменения . если нет, то создае
      app.goTo().AddingNewContact();
      app.contact().create(new ContactData().withFirstName("First_name_test").withMiddleName("Middle_name_test")
              .withLastName("Last_name_test").withNick("nick").withAddress("address_test")
              .withPhoneHome("123456789").withPhoneMobile("987654321").withPhoneWork("1111111")
              .withEmail("test@test.test").withEmailSecond("qwert@qw.qw").withEmailThird("aaaa@aa.aa"));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();  // получение старого множества контактов before
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();  // получение нового множества контактов after
    assertThat(after.size(),equalTo( before.size() - 1));
  }
}
