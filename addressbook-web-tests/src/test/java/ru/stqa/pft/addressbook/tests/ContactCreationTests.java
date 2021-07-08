package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.generators.ContactDataAdapter;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsXML() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[]{(c)}).collect(Collectors.toList()).iterator();
    }
  }


  @DataProvider
  public Iterator<Object[]> validContactsXJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }

      GsonBuilder builder = new GsonBuilder();
      builder.registerTypeAdapter(ContactData.class, new ContactDataAdapter());
      builder.setPrettyPrinting();
      Gson gson = builder.create();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsXJson")
  public void testContactCreation(ContactData contact) throws Exception {
    app.goTo().HomePage();
    Contacts before = app.contact().all();  // получение старого множества контактов before
    app.goTo().AddingNewContact();
    File photo = new File("src/test/resources/stru.png");
    app.contact().create(contact.withPhoto(photo));
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();  // получение нового множества контактов after
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
