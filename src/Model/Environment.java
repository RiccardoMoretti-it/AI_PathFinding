package Model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import Model.Entities.Bomb;
import Model.Entities.Entity;
import Model.Entities.Powerup;
import Model.Entities.Characters.Robot;
import processing.core.PApplet;
import processing.core.PVector;


public class Environment {
     
     public static final int LEFT = -1;
     public static final int RIGHT = -2;
     public static final int UP = -3;
     public static final int DONW = -4;
     Robot[] Player;

     ArrayList<Entity> entities=new ArrayList<>();
     public final FieldContainer field;
     public final PApplet canvas;
     Random rnd;

     public Environment(FieldContainer field, PApplet canvas){
          this.canvas=canvas;
          this.field=field;
     }

     public void add(Optional<Entity> entitySent){
          if (entitySent.isPresent()){
               entities.add(entitySent.get());
          }
     }

     public void applyRule(Optional.of(Predicate<T> penis)){

     }
     public void getAllXEntitiesToDoY(){
          
     }
}
