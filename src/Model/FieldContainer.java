package Model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

//la graficazione di Field
public class FieldContainer {

     float squareSize;
     float oldSquareSize;
     Field actualField;
     float drawingPositionX;
     float drawingPositionY;
     boolean anchorMiddleLeft;
     Random rnd;

     public FieldContainer(String input,int width, int height) throws FileNotFoundException{
          rnd=new Random();
          actualField=new Field(input);
          this.setSizes(width, height);
     }

     public void setSizes(int width, int height){
          oldSquareSize=squareSize;
          float ratioHeight = (float)(height*1.0/actualField.getRowsNumber()); 
          float ratioWidth  = (float)(width*1.0/actualField.getColumnsNumber()); 
          squareSize= ratioHeight<ratioWidth ? ratioHeight : ratioWidth;
          anchorMiddleLeft=ratioHeight<ratioWidth ? false : true;

          drawingPositionX=0;
          drawingPositionY=0;
          if(anchorMiddleLeft)
               drawingPositionY=(height/2 - squareSize*actualField.getRowsNumber()/2);
          else
               drawingPositionX=(width/2 - squareSize*actualField.getColumnsNumber()/2);
     }
     
     public void randomize(){
          actualField.randomize();
     }

     public ArrayList<Integer> getDirections(int fromX,int fromY,int toX, int toY){
          return actualField.getDirections(fromX,fromY,toX, toY);
     }
     public float[] getDrawingPosition(){
          return new float []{drawingPositionX,drawingPositionY};
     }
     public boolean isAnchoredMiddleLeft (){
          return anchorMiddleLeft;
     }
     public float getSquareSize (){
          return squareSize;
     }
     public float getOldSquareSize (){
          return oldSquareSize;
     }
     public int getFieldLength(){
          return (int)(actualField.getColumnsNumber()*squareSize);
     }
     public int getFieldHeight(){
          return (int)(actualField.getRowsNumber()*squareSize);
     }
     public int[] getCellsNumber(){
          return new int[]{actualField.getColumnsNumber(),actualField.getRowsNumber()};
     }
     public int getValueAtPos(int x,int y){
          return actualField.getValueAtPos(x,y);
     }
     public int[][] cloneMatrix(){
          return actualField.cloneMatrix();
     }
     
     public int[] getValidCoords(final int[]coords){
          int addX=0,addY=0;
          if(getValueAtPos(coords[0], coords[1])==Field.WALL){
               do{
                    addX=rnd.nextInt(3)-1;
                    addY=rnd.nextInt(3)-1;
               }while(coords[0]+addX>getCellsNumber()[0]-1 || coords[0]+addX<0 ||coords[1]+addY>getCellsNumber()[0]-1 || coords[1]+addY<0);
               coords[0]+=addX;
               coords[1]+=addY;
               return getValidCoords(coords);
          }
          return coords;
     }
}
