package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {
  @Test
  public void testResetPassword() throws MessagingException, IOException {
    String username = "user1626945367017";
    String mailPassword = "password";
    String newpassword = "pass" + System.currentTimeMillis();

    int mailboxSize = app.james().getMailboxSize(username, mailPassword);// Чистим ящие, если он не пуст
    if (mailboxSize != 0) {
      app.james().drainEmail(username, mailPassword);
    }

    app.resetPass().loginUI(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.resetPass().resetPassword(username);


    List<MailMessage> mailMessages = app.james().waitForMail(username, mailPassword, 60000);
    System.out.println(mailMessages);
    String confirmationLink = findConfirmationLink(mailMessages, username+"@localhost");
    app.resetPass().changePasswordByLink(confirmationLink, newpassword);

    HttpSession session = app.newSession();
    assertTrue(session.login(username,newpassword));
    assertTrue(session.isLoggedAs(username));
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}
