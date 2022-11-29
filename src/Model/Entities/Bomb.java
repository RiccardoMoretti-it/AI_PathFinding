package Model.Entities;
import Model.Environment;

public abstract class Bomb extends Entity {


     public Bomb( float []position,Environment environment){
          super(position,environment);
          this.position=position;
          this.environment=environment;
     }
}
