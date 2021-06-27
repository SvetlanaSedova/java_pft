package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

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
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().all();  // получение старого множества контактов before
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();  // получение нового множества контактов after
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);  // удаляем из первого списка контакт, который был удален
    Assert.assertEquals(before, after);           // сверка списков

  }
}
