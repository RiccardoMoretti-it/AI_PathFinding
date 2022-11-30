package Entities.Characters;

import Model.Environment;
import Model.Entities.Characters.Robot;
import processing.core.PApplet;
import processing.core.PVector;

public class DrawableRobot extends Robot{

     public DrawableRobot(float[] position, Environment environment) {
          super(position, environment);
     }

     @Override
     public void draw() {
          PApplet draw=environment.canvas;
          float[] absolutePos=position.clone();
          absolutePos[0]+=environment.field.getDrawingPosition()[0];
          absolutePos[1]+=environment.field.getDrawingPosition()[1];
          draw.fill(255,0,0);
          draw.ellipse(absolutePos[0], absolutePos[1], environment.field.getSquareSize(), environment.field.getSquareSize());
     }
     
}
