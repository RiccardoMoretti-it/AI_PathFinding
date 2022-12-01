package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import Model.Entities.Entity;
import processing.core.PApplet;


public class Environment {
     
     public static final int LEFT = -1;
     public static final int RIGHT = -2;
     public static final int UP = -3;
     public static final int DONW = -4;

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

     public List<Optional<Entity>> getEntitiesWithCondition( final Predicate<Entity> pre) {
          final List<Optional<Entity>> newList = new ArrayList<>();
          entities.forEach(t -> newList.add(Optional.of(t).filter(pre)));
          return newList;
     }
}
