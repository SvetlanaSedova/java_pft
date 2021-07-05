package ru.stqa.pft.addressbook.generators;

import java.io.File;

public class ContactDataGenertor {
  public static void main(String[] args) {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);
  }
}
