package Model.Entities.Characters;
import java.util.Optional;

import Entities.DrawableBomb;
import Model.Environment;
import Model.Entities.Entity;

public abstract class Character extends Entity{
     int currentDirection;
     boolean isMoving;

     public abstract void advance();

     public Character (float []position,Environment environment){
          super (position,environment);
     }

     public void takeStep(){
          switch(currentDirection){
               case Environment.LEFT:
               case Environment.RIGHT:
                    position[0]+=speed * (currentDirection == Environment.LEFT ? -1 : 1) * environment.field.getSquareSize();
               break;
               case Environment.UP:
               case Environment.DONW:
                    position[1]+=speed * (currentDirection == Environment.UP ? -1 : 1) * environment.field.getSquareSize();
               break;
          }
     }
     
     public boolean placeBomb(){
          environment.add( Optional.of(new DrawableBomb(position,environment)));
          return true;
     }
}
