package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.db().contacts().isEmpty()) {        // проверяем, есть ли контакты для изменения . если нет, то создае
      app.goTo().AddingNewContact();
      app.contact().create(new ContactData().withFirstName("First_name_test").withMiddleName("Middle_name_test")
              .withLastName("Last_name_test").withNick("nick").withAddress("address_test")
              .withPhoneHome("123456789").withPhoneMobile("987654321").withPhoneWork("1111111").withEmail("test@test.test"));
    }
  }

  @Test
  public void testContactPhones(){
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork())
        .stream().filter((s)->!s.equals(""))
            .map(ContactPhoneTest::cleaned)
                    .collect(Collectors.joining("\n"));
  }

  static String cleaned (String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
