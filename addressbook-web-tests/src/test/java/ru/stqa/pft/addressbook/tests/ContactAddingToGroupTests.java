package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddingToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().isEmpty()) {    // проверяем, есть ли группа для добавления контакта. если нет, то создаем
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
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
  public void testAddingContactToGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups(); // получение списка контактов и групп
    ContactData addingContact = contacts.iterator().next();   //выбор контакта
    GroupData groupForAdding = groups.iterator().next();  //выбор группы

    if (app.contact().isContactInGroup(addingContact, groupForAdding)) {
      app.contact().removeContactFromGroup(addingContact, groupForAdding);
      app.goTo().HomePage();
      app.contact().filterContactsByGroupName("[all]");
    }
    app.goTo().HomePage();
    app.contact().addContactToGroup(addingContact, groupForAdding);
    contacts = app.db().contacts();
    ContactData addedContact = app.contact().GetContactFromSetById(contacts, addingContact.getId());
    MatcherAssert.assertThat("contact not added to the group",
            addedContact.getGroups().contains(groupForAdding));
  }
}
