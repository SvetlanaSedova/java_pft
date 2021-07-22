package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.testng.Assert;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

public class ResetPassHelper extends HelperBase {

  public ResetPassHelper(ApplicationManager app) {
    super(app);
  }

  public void loginUI(String login, String password) {
    type(By.name("username"), login);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void resetPassword(String username) {
    goToManagingUser(username);
    click(By.cssSelector("input[value = 'Reset Password']"));
    System.out.println("it's ok");
  }

  public void goToManagingUser(String username) {
    click(By.xpath("//span[contains(text(),'Manage')]"));
    click(By.xpath("//a[.= 'Manage Users']"));
    type(By.cssSelector("input#search"), username);
    click(By.cssSelector("input[value='Apply Filter']"));
    click(By.xpath(String.format("//a[contains(text(),'%s')]", username)));
  }

  public void changePasswordByLink(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("button[type='submit']"));
  }

}
