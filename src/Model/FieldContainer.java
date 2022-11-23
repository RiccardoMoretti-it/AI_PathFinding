package Model;

import java.io.FileNotFoundException;


public class FieldContainer {

     double squareSize;
     Field actualField;
     double drawingPositionX;
     double drawingPositionY;
     boolean drawMiddleLeft;

     public FieldContainer(String input,int width, int height) throws FileNotFoundException{
          actualField=new Field(input);
          this.setSizes(width, height);
     }

     public void setSizes(int width, int height){

          double ratioHeight = height/actualField.getRowsNumber(); 
          double ratioWidth  = width/actualField.getColumnsNumber(); 

          squareSize= ratioHeight<ratioWidth ? ratioHeight : ratioWidth;
          drawMiddleLeft=ratioHeight<ratioWidth ? false : true;

          drawingPositionX=0;
          drawingPositionY=0;
          if(drawMiddleLeft)
               drawingPositionY=(height/2 - squareSize*actualField.getColumnsNumber()/2);
          else
               drawingPositionX=(width/2 - squareSize*actualField.getRowsNumber()/2);
     }
     
     public double[] getDrawingPosition(){
          return new double []{drawingPositionX,drawingPositionY};
     }
     public boolean drawMiddleLeft (){
          return drawMiddleLeft;
     }
     public double getSquareSize (){
          return squareSize;
     }
     public int getFieldLength(){
          return (int)(actualField.getColumnsNumber()*squareSize);
     }
     public int getFieldHeight(){
          return (int)(actualField.getRowsNumber()*squareSize);
     }
}
