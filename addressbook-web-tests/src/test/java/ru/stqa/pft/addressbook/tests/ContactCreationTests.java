package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.goTo().HomePage();
    Contacts before = app.contact().all();  // получение старого множества контактов before
    app.goTo().AddingNewContact();
    File photo = new File("src/test/resources/stru.png");
    ContactData contact = new ContactData().withFirstName("First_name_test").withMiddleName("Middle_name_test")
            .withLastName("Last_name_test").withNick("nick").withAddress("address_test").withPhoneHome("123456789")
               .withPhoneMobile("987654321").withPhoneWork("1111111")
                  .withEmail("test@test.test").withEmailSecond("qwert@qw.qw").withEmailThird("aaaa@aa.aa").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();  // получение нового множества контактов after
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }
}
