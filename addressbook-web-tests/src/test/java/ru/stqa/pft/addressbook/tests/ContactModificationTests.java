package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (!app.contact().isThereAContact()) {        // проверяем, есть ли контакты для изменения . если нет, то создае
      app.goTo().AddingNewContact();
      app.contact().create(new ContactData().withFirstName("First_name_test").withMiddleName("Middle_name_test")
              .withLastName("Last_name_test").withNick("nick").withAddress("address_test")
              .withPhoneHome("123456789").withPhoneMobile("987654321").withEmail("test@test.test"));
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();  // получение старого списка контактов before
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId())
            .withFirstName("First_name_test1").withMiddleName("Middle_name_test1")
            .withLastName("Last_name_test1").withNick("nick1").withAddress("address_test1")
            .withPhoneHome("1234567891").withPhoneMobile("9876543211").withEmail("test1@test.test");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();     // получение нового списка  контактов after
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());   // сортировка списков по id группы
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
