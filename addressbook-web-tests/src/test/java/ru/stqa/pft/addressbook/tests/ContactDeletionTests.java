package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{
  @Test

  public void testContactDeletion(){
    app.getContactHelper().returnHomePage();
    if (! app.getContactHelper().isThereAContact()){   // проверяем, есть ли контакты для удаления. если нет, то создаем
      app.getNavigatioHelper().goToAddingNewContact();
      app.getContactHelper().createContact(new ContactData("First_name_test", "Middle_name_test", "Last_name_test", "nick",
              "address_test", "123456789", "987654321", "test@test.test"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();  // получение старого списка  контактов before
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().returnHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();  // получение нового списка  контактов after
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(before.size() - 1);  // удаляем из первого списка контакт , который был удален
    Assert.assertEquals(before, after);           // сверка списков

  }

}
