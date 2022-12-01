package Model.Entities.Characters;
import java.util.ArrayList;
import Model.Environment;
import Model.utilities;


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
               currentDirection=directions.get(0);

               ArrayList<Robot> allRobot=utilities.getAllRobots(environment);

               float moveByX=0;
               float moveByY=0;
               switch(currentDirection){
                    case Environment.LEFT:
                         moveByX=-environment.field.getSquareSize();
                    break;
                    case Environment.RIGHT:
                         moveByX=environment.field.getSquareSize();
                    break;
                    case Environment.UP:
                         moveByY=-environment.field.getSquareSize();
                    break;
                    case Environment.DONW:
                         moveByY=environment.field.getSquareSize();
                    break;
               }

               Boolean canMove=true;
               for(Robot bot : allRobot) {
                    if(bot != this){
                         float sqSize=environment.field.getSquareSize();
                         if((int)((position[0]+moveByX)/sqSize) == (int)(bot.getPosition()[0]/sqSize)
                         && (int)((position[1]+moveByY)/sqSize) == (int)(bot.getPosition()[1]/sqSize))
                              canMove=false;        
                    }
               }

               if(canMove){
                    position[0]+=moveByX;
                    position[1]+=moveByY;
                    directions.remove(0);
               }
          }
          else isMoving=false;
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