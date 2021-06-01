package ru.stqa.pft.sandbox;

public class NewClass {
  public static void main(String[] args){
    Point p1=new Point(2.3,15.0);
    Point p2=new Point(9.0,2.3);


    System.out.println("Результат работы функции в запускаемом классе "+ distance(p1, p2));

    System.out.println("Результат работы функции в методе класса " + p1.distanceToPoint(p2));

  }
  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y,2));
  }

}
