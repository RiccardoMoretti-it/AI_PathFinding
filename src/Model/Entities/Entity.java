package Model.Entities;
import Model.Drawable;
import Model.Environment;

public abstract class Entity implements Drawable {     

     protected Environment environment;
     protected float[] position;
     protected float speed;
     
     protected Entity(float[]position,Environment environment){
          this.environment=environment;
          updatePosition(position);
     }
     public void updatePosition(float []position){
          float squareSize=environment.field.getSquareSize();
          
          int cellX=(int)(position[0]/squareSize);
          int cellY=(int)(position[1]/squareSize);
          int[]coords=new int[]{cellX,cellY};

          coords=environment.field.getValidCoords(coords);
          this.position=new float[]{(float)(coords[0]*squareSize+squareSize*0.5),(float)(coords[1]*squareSize+squareSize*0.5)};
     }
     
     public void setNewPosition(float []coords){
          position=coords;
     }
     public float[] getPosition(){
          return position;
     }

     public void resize(float oldSquareSize, float squareSize){
		float ratio=environment.field.getSquareSize()/environment.field.getOldSquareSize();
		position[0]*=ratio;
		position[1]*=ratio;
     }

}
