package Model.Entities.Characters;
import java.util.ArrayList;
import Model.Environment;


public abstract class Robot extends Character{
     
     int[] cellDestination;
     ArrayList<Integer> directions;
     
     public Robot(float []position,Environment environment){
          super(position,environment);
          cellDestination=null;
     }

     public void advance(){
          if(cellDestination==null) return;
          if(directions==null)calculateDirections();
          goToDestination();

     }
     private void goToDestination() {
          if(directions.size()>0){
               isMoving=true;
               advanceCellDestination();
               switch(currentDirection){
                    case Environment.LEFT:
                         position[0]-=environment.field.getSquareSize();
                    break;
                    case Environment.RIGHT:
                          position[0]+=environment.field.getSquareSize();
                    break;
                    case Environment.UP:
                         position[1]-=environment.field.getSquareSize();
                    break;
                    case Environment.DONW:
                         position[1]+=environment.field.getSquareSize();
                    break;
               }         
          }
          else isMoving=false;
     }
     private void advanceCellDestination() {
          currentDirection=directions.get(0);
          directions.remove(0);
     }
     private void calculateDirections() {
          directions=environment.field.getDirections((int)(position[0]/environment.field.getSquareSize()), (int)(position[1]/environment.field.getSquareSize()), cellDestination[0], cellDestination[1]);
     }
     public void setDestination(int[]coords){
          coords=environment.field.getValidCoords(coords);
          resetDestination();
          cellDestination=new int[]{coords[0],coords[1]};
     }
     private void resetDestination() {
          directions=null;
     }

}