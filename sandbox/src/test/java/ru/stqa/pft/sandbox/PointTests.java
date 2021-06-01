package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void TestDistancePositive1() {
    Point p1 = new Point(19, 15);
    Point p2 = new Point(16, 11);
    Assert.assertEquals(p1.distanceToPoint(p2), 5.0);

  }

  @Test
  public void TestDistancePositive2() {
    Point p1 = new Point(129, 15.5);
    Point p2 = new Point(16, -411);
    Assert.assertEquals(p1.distanceToPoint(p2), 441.2156502210682);

  }

  @Test
  public void TestDistanceSamePoints() {
    Point p1 = new Point(9, 15);
    Assert.assertEquals(p1.distanceToPoint(p1), 0.0);

  }

  @Test
  public void TestDistanceMinusPoints() {
    Point p1 = new Point(2, 1.5);
    Point p2 = new Point(-2, -1.5);
    Assert.assertEquals(p1.distanceToPoint(p2), 5.0);

  }

  @Test
  public void TestDistanceWithNull() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(4, 3);
    double correctResult = Math.sqrt(Math.pow(p2.x, 2) + Math.pow(p2.y, 2));// сумма квадратов координат ненулевой точки
    Assert.assertEquals(p1.distanceToPoint(p2), correctResult);

  }
}
