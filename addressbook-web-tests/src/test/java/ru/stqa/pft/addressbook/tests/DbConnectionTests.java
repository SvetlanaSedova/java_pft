package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTests {
  @Test
  public void testDbConnection() {
    Connection conn = null;
    try {
      conn =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" +
                      "user=root&password=");
      Statement st = conn.createStatement();
      ResultSet res = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
      Groups groups = new Groups();
      while (res.next()) {
        groups.add(new GroupData().withId(res.getInt("group_id")).withName(res.getString("group_name"))
                .withHeader(res.getString("group_header")).withFooter(res.getString("group_footer")));
      }
      res.close();
      st.close();
      conn.close();

      System.out.println(groups); // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
