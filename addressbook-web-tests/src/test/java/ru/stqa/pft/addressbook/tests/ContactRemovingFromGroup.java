package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.jetbrains.annotations.Nullable;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemovingFromGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().isEmpty()) {    // проверяем, есть ли группа для добавления контакта. если нет, то создаем
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
      app.goTo().HomePage();
    }

    if (app.db().contacts().isEmpty()) {   // проверяем, есть ли контакты для добавления в группу. если нет, то создае
      app.goTo().AddingNewContact();
      app.contact().create(new ContactData().withFirstName("First_name_test").withMiddleName("Middle_name_test")
              .withLastName("Last_name_test").withNick("nick").withAddress("address_test")
              .withPhoneHome("123456789").withPhoneMobile("987654321").withPhoneWork("1111111")
              .withEmail("test@test.test").withEmailSecond("qwert@qw.qw").withEmailThird("aaaa@aa.aa"));
    }
  }


  @Test
  public void testRemovingContactFromGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups(); // получение списка контактов и групп

    ContactData removedContact = app.contact().getContactWithGroup(contacts);  // ищем любой контакт с группой

    if (removedContact == null) {  //если ни один контакт не включен ни в одну группу
      app.contact().addContactToGroup(contacts.iterator().next(), groups.iterator().next()); //добавляем
      removedContact = app.contact().getContactWithGroup(app.db().contacts()); // и перезапрашиваем контакт
    }

    GroupData groupForRemoving = app.contact().getGroupFromContact(groups, removedContact);  //получаемгруппу для удаления


    app.goTo().HomePage();
    app.contact().removeContactFromGroup(removedContact, groupForRemoving);
    contacts = app.db().contacts();
    ContactData remContact = app.contact().GetContactFromSetById(contacts, removedContact.getId());
    MatcherAssert.assertThat("contact not removed from the group", !remContact.getGroups().contains(groupForRemoving));
  }


}

