package ru.stqa.pft.rest;

import org.testng.annotations.Test;

import java.io.IOException;

public class SkipTests extends TestBase {

  @Test
  public void test1() throws IOException {
    skipIfNotFixed(2);
    System.out.println("test1 executed");
  }

  @Test
  public void test2() throws IOException {
    skipIfNotFixed(15);
    System.out.println("test2 executed");
  }

  @Test
  public void test3() throws IOException {
    skipIfNotFixed(100);
    System.out.println("test3 executed");
  }
}
