package com.example;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Vehicle extends Rectangle {
  String name;
  Color color;
  private static final Random random = new Random();

  public Vehicle(String name, Color color, int y) {
    this.name = name;
    this.color = color;
    this.setWidth(80);
    this.setHeight(30);
    this.setFill(color);
    this.setX(20);
    this.setY(y);
  }

  public void move() {
    double x = this.getX() + random.nextInt(10);
    this.setX(x);
  }

  public void moveToStart() {
    this.setX(20);
  }
}
