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
      app.contact().create(new ContactData("First_name_test", "Middle_name_test", "Last_name_test", "nick",
              "address_test", "123456789", "987654321", "test@test.test"));
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();  // получение старого списка контактов before
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "First_name_test1", "Middle_name_test1",
            "Last_name_test1", "nick1", "address_test1",
            "1234567891", "9876543211", "test1@test.test");
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
