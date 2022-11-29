package Model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import Model.Entities.Bomb;
import Model.Entities.Entity;
import Model.Entities.Powerup;
import Model.Entities.Characters.Robot;

public class Environment {
     
     public static final int LEFT = -1;
     public static final int RIGHT = -2;
     public static final int UP = -3;
     public static final int DONW = -4;
     Robot[] Player;

     ArrayList<Bomb> bombs = new ArrayList<>();
     ArrayList<Powerup> powerups = new ArrayList<>();
     Character[] characters;
     public final FieldContainer field;
     Random rnd;

     public Environment(FieldContainer field){
          this.field=field;
     }

     public void add(Optional<? extends Entity> entitySent){
          if (entitySent.isPresent()){
               Entity entity= (Entity)entitySent.get();
               
               if(entity.getClass()== Bomb.class) bombs.add((Bomb)entity);
               else if(entity.getClass()== Powerup.class) powerups.add((Powerup)entity);

          }
     }
}
