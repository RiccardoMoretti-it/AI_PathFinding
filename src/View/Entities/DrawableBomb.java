package Entities;

import Model.Environment;
import Model.Entities.Bomb;
import processing.core.PApplet;

public class DrawableBomb extends Bomb {

     public DrawableBomb(float[] position, Environment environment) {
          super(position, environment);
     }

     @Override
     public void draw() {
          PApplet draw=environment.canvas;
          float radius=environment.field.getSquareSize()/2;
          draw.fill(0);
          draw.ellipse(position[0], position[1], radius, radius);
          
          draw.fill(138, 121, 10);
          draw.strokeWeight(4);
          draw.line(position[0], position[1], position[0]+radius,position[1]+radius);
     }
     
}
