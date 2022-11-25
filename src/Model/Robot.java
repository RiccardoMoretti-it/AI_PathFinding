package Model;

import java.util.ArrayList;
import java.util.Random;


public class Robot{
     public static final int LEFT = -1;
     public static final int RIGHT = -2;
     public static final int UP = -3;
     public static final int DONW = -4;

     double[] position;
     int[] cellDestination;
     ArrayList<Integer> directions;
     int currentDirection;
     boolean isMoving;
     FieldContainer field;
     Random rnd;
     
     public Robot(Double mouseX,Double mouseY,FieldContainer field){
          this.field=field;
          cellDestination=null;
          rnd=new Random();
          updatePosition(mouseX,mouseY);
     }
     public void updatePosition(Double mouseX,Double mouseY){
          double squareSize=field.getSquareSize();
          int cellX=(int)(mouseX/squareSize);
          int cellY=(int)(mouseY/squareSize);
          int[]coords=new int[]{cellX,cellY};

          coords=getValidCoords(new int[]{cellX,cellY});
          position=new double[]{coords[0]*squareSize+squareSize*0.5,coords[1]*squareSize+squareSize*0.5};
     }

     private int[] getValidCoords(int[]coords){
          int addX=0,addY=0;
          if(field.getStatusAtPos(coords[0], coords[1])==Field.WALL){
               do{
                    addX=rnd.nextInt(3)-1;
                    addY=rnd.nextInt(3)-1;
               }while(coords[0]+addX>field.getFieldCells()[0]-1 || coords[0]+addX<0 ||coords[1]+addY>field.getFieldCells()[0]-1 || coords[1]+addY<0);
               coords[0]+=addX;
               coords[1]+=addY;
               coords=getValidCoords(coords);
          }
          return coords;
     }
     public double[] getPosition(){
          return position;
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
                    case LEFT:
                         position[0]-=field.getSquareSize();
                    break;
                    case RIGHT:
                          position[0]+=field.getSquareSize();
                    break;
                    case UP:
                         position[1]-=field.getSquareSize();
                    break;
                    case DONW:
                         position[1]+=field.getSquareSize();
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
          directions=field.getDirections((int)(position[0]/field.squareSize), (int)(position[1]/field.squareSize), cellDestination[0], cellDestination[1]);
     }
     public void setDestination(int cellX,int cellY){
          resetDestination();
          cellDestination=new int[]{cellX,cellY};
     }
     private void resetDestination() {
          directions=null;
     }

}