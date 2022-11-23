package Model;

import java.io.FileNotFoundException;

public class FieldContainer {
     int width;
     int height;
     float squareSize;
     Field actualField;
     public FieldContainer(String input,int width, int height) throws FileNotFoundException{
          actualField=new Field(width, height, input);
          this.width=width;
          this.height=height;
          int greater = width>height ? width:height;
          squareSize= greater/ actualField.getGreaterSize();
     }
     public int[] getSizesXY(){
          return new int []{width,height};
     }
}
