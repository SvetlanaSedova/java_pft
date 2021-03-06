package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("groups"));
    click(By.xpath("//form[@action='/addressbook/group.php']"));
  }

  public void submitGroupCeation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGrouCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  private void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }


  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(GroupData groupData) {
    initGrouCreation();
    fillGroupForm(groupData);
    submitGroupCeation();
    groupCache=null;
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache=null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectById(group.getId());
    deleteSelectedGroups();
    groupCache=null;
    returnToGroupPage();
  }


  public int count() {
    return wd.findElements(By.name("selected[]")).size();

  }

  private Groups groupCache = null;

  public Groups all() {
    if(groupCache!=null){
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(name);
      groupCache.add(group);
    }
    return new Groups(groupCache);
  }


}
