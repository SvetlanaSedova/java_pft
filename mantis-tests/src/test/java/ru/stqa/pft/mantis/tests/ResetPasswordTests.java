package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }


  @Test
  public void testResetPassword() throws IOException {

    User user = app.db().getUsersList().iterator().next();
    String newpassword = "pass" + System.currentTimeMillis();

    app.resetPass().loginUI(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.resetPass().resetPassword(user.getUsername());

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);

    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());

    app.resetPass().changePasswordByLink(confirmationLink, newpassword);

    HttpSession session = app.newSession();
    assertTrue(session.login(user.getUsername(), newpassword));
    assertTrue(session.isLoggedAs(user.getUsername()));
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
