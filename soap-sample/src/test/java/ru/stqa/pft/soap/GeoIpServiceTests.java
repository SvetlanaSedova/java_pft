package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {
  @Test
  public void testValidIp() {
    String response = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("109.184.57.255");
    assertEquals(response.split(">")[2].split("<")[0],"RU");
  }

  @Test(enabled = false)
  public void testInvalidIp() {
    String response = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("qqqqqq");
    System.out.println(response);
    assertTrue(!response.contains("RU"));
  }
}
