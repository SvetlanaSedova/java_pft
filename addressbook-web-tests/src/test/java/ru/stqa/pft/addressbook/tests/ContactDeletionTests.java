package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

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

  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();  // получение старого списка  контактов before
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();  // получение нового списка  контактов after
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);  // удаляем из первого списка контакт , который был удален
    Assert.assertEquals(before, after);           // сверка списков

  }
}
