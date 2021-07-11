package ru.stqa.pft.addressbook.generators;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.IOException;

public class ContactDataAdapter extends TypeAdapter<ContactData> {
  @Override
  public void write(JsonWriter writer, ContactData contact) throws IOException {

    writer.beginObject();
    writer.name("firstName");
    writer.value(contact.getFirstName());
    writer.name("middleName");
    writer.value(contact.getMiddleName());
    writer.name("lastName");
    writer.value(contact.getLastName());
    writer.name("nick");
    writer.value(contact.getNick());
    writer.name("address");
    writer.value(contact.getAddress());
    writer.name("phoneHome");
    writer.value(contact.getPhoneHome());
    writer.name("phoneMobile");
    writer.value(contact.getPhoneMobile());
    writer.name("phoneWork");
    writer.value(contact.getPhoneWork());
    writer.name("email");
    writer.value(contact.getEmail());
    writer.name("email2");
    writer.value(contact.getEmailSecond());
    writer.name("email3");
    writer.value(contact.getEmailThird());
    writer.name("photo");
    writer.value(contact.getPhoto().getPath());
    writer.endObject();
  }

  @Override
  public ContactData read(JsonReader reader) throws IOException {
    ContactData contact = new ContactData();
    reader.beginObject();
    String fieldname = null;
    while (reader.hasNext()) {
      JsonToken token = reader.peek();

      if (token.equals(JsonToken.NAME)) {
        //get the current token
        fieldname = reader.nextName();
      }

      if ("firstName".equals(fieldname)) {
        token = reader.peek();
        contact.withFirstName(reader.nextString());
      }

      if ("middleName".equals(fieldname)) {
        token = reader.peek();
        contact.withMiddleName(reader.nextString());
      }
      if ("lastName".equals(fieldname)) {
        token = reader.peek();
        contact.withLastName(reader.nextString());
      }
      if ("nick".equals(fieldname)) {
        token = reader.peek();
        contact.withNick(reader.nextString());
      }
      if ("address".equals(fieldname)) {
        token = reader.peek();
        contact.withAddress(reader.nextString());
      }
      if ("phoneHome".equals(fieldname)) {
        token = reader.peek();
        contact.withPhoneHome(reader.nextString());
      }
      if ("phoneMobile".equals(fieldname)) {
        token = reader.peek();
        contact.withPhoneMobile(reader.nextString());
      }
      if ("phoneWork".equals(fieldname)) {
        token = reader.peek();
        contact.withPhoneWork(reader.nextString());
      }
      if ("email".equals(fieldname)) {
        token = reader.peek();
        contact.withEmail(reader.nextString());
      }
      if ("email2".equals(fieldname)) {
        token = reader.peek();
        contact.withEmailSecond(reader.nextString());
      }
      if ("email3".equals(fieldname)) {
        token = reader.peek();
        contact.withEmailThird(reader.nextString());
      }
      if ("photo".equals(fieldname)) {
        token = reader.peek();
        contact.withPhoto(new File(reader.nextString()));
      }
    }
    reader.endObject();
    return contact;
  }
}
