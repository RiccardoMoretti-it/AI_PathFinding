package Model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class Field {
     final int WALL=0;
     final int SPACE=1;
     private int[][] matrixField;

     public Field (int width,int height,String input) throws FileNotFoundException{
          int greater=width;
          if(height>greater) greater=height;
          int [] sizeXY=getSize(input);
          matrixField=new int[sizeXY[0]][sizeXY[1]];

     }

     private int[] getSize(String string) throws FileNotFoundException {
          
          int[] coords=new int[2];

          try (Scanner sc=new Scanner(new FileInputStream(string))) {
               coords[0]=sc.nextInt();
               coords[1]=sc.nextInt();
          }
          catch(FileNotFoundException e){
               throw new FileNotFoundException("errore nel path del file di input");
          }
          return coords;
     }

     void modifyMatrix(int number, int x, int y){
          matrixField[x][y] = number;
     }

     int[][] getMatrixField(){
          return matrixField;
     }
     void randomize() throws NullPointerException{
          Random rnd = new Random();
          
          for(int y=0;y<matrixField.length;y++)
               for(int x=0;x<matrixField[0].length;x++){
                    if(rnd.nextInt(4)==0)
                         matrixField[x][y]=WALL;
                    else 
                    matrixField[x][y]=SPACE;
               }
     }
     public int getGreaterSize (){
          return matrixField.length > matrixField[0].length ? matrixField.length : matrixField[0].length;
     }
}
