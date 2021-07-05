package ru.stqa.pft.addressbook.generators;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.IOException;

public class ContactDataAdapter extends TypeAdapter <ContactData> {
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
    writer.name("photo");
    writer.value(contact.getPhoto().getPath());
    writer.endObject();
  }

  @Override
  public ContactData read(JsonReader in) throws IOException {
    return null;
  }
}
