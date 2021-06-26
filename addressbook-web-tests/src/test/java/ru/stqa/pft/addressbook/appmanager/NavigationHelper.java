package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void GroupPage() {
    click(By.linkText("groups"));
  }

  public void AddingNewContact() {
    click(By.linkText("add new"));
  }

  public void HomePage() {
    click(By.linkText("home"));
  }
}
