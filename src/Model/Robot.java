package Model;

import java.util.Random;

public class Robot{
     double[] position;
     boolean isMoving;
     FieldContainer field;
     Random rnd;
     public Robot(Double mouseX,Double mouseY,FieldContainer field){
          this.field=field;
          rnd=new Random();
          updatePosition(mouseX,mouseY);

     }
     public void updatePosition(Double mouseX,Double mouseY){
          double squareSize=field.getSquareSize();
          int cellX=(int)(mouseX/squareSize);
          int cellY=(int)(mouseY/squareSize);
          int[]coords=new int[]{cellX,cellY};

          coords=checkPositionAndUpdate(new int[]{cellX,cellY});
          position=new double[]{coords[0]*squareSize+squareSize*0.5,coords[1]*squareSize+squareSize*0.5};
     }
     private int[] checkPositionAndUpdate(int[]coords){
          int addX=0,addY=0;
          if(field.getStatusAtPos(coords[0], coords[1])==Field.WALL){
               do{
                    addX=rnd.nextInt(-1,2);
                    addY=rnd.nextInt(-1,2);
               }while(coords[0]+addX>field.getFieldCells()[0]-1 || coords[0]+addX<0 ||coords[1]+addY>field.getFieldCells()[0]-1 || coords[1]+addY<0);
               coords[0]+=addX;
               coords[1]+=addY;
               coords=checkPositionAndUpdate(coords);
          }
          return coords;
     }
     public double[] getPosition(){
          return position;
     }

     

}