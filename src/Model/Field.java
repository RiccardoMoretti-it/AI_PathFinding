package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Field {
     public static final int WALL=-6;
     public static final int SPACE=-7;
     public int wallChance;
     private int[][] matrixField;

     public Field (String input) throws FileNotFoundException{

          int [] sizeXY;
          try (Scanner sc=new Scanner(new FileInputStream(input))) {
               sizeXY=getSizeFromInputFile(sc);
               wallChance=getWallChanceFromFile(sc);
          }    
          catch(FileNotFoundException e){
               throw new FileNotFoundException("errore nel path del file di input");
          }    
          matrixField=new int[sizeXY[0]][sizeXY[1]];
     }

     //BFS da "from" a "to"
     public ArrayList<Integer> getDirections(final int fromX,final int fromY,final int toX, final int toY){
          int[][] matrixCloned=cloneMatrix();
          int value=1;
          ArrayList<Integer[]> queue=new ArrayList<>();

          queue.add(new Integer[]{fromX,fromY});
          matrixCloned[fromX][fromY]=0;

          int x,y;
          while(queue.get(0)[0]!=toX || queue.get(0)[1]!=toY){
               value=matrixCloned[queue.get(0)[0]][queue.get(0)[1]];
               for(x=queue.get(0)[0]-1;x<=queue.get(0)[0]+1;x++)
                    for(y=queue.get(0)[1]-1;y<=queue.get(0)[1]+1;y++)

                    if((x==queue.get(0)[0] || y == queue.get(0)[1]) && utilities.between(x, 0, matrixCloned.length-1)&& utilities.between(y, 0, matrixCloned[0].length-1) ){
                         if(matrixCloned[x][y] == Field.SPACE){
                              queue.add(new Integer[]{x,y});
                              matrixCloned[x][y]=value+1;
                         }
                    }
               queue.remove(0);
          }
          return extractPath(matrixCloned,toX,toY);
     }
     private ArrayList<Integer> extractPath(int[][] matrixCloned, int x,int y) {
          ArrayList<Integer> direction= new ArrayList<>();
          while(matrixCloned[x][y]!=0){
               if(utilities.between(x+1, 0, matrixCloned.length-1) && matrixCloned[x+1][y]+1==matrixCloned[x][y]){
                    x++;
                    direction.add(0,Robot.LEFT);
               }
               else if(utilities.between(x-1, 0, matrixCloned.length-1) && matrixCloned[x-1][y]+1==matrixCloned[x][y]){
                    direction.add(0,Robot.RIGHT);
                    x--;
               }
               else if(utilities.between(y+1, 0, matrixCloned[0].length-1) && matrixCloned[x][y+1]+1==matrixCloned[x][y]){
                    direction.add(0,Robot.UP);
                    y++;
               }
               else if(utilities.between(y-1, 0, matrixCloned[0].length-1) && matrixCloned[x][y-1]+1==matrixCloned[x][y]){
                    direction.add(0,Robot.DONW);
                    y--;
               }
          }
          return direction;
     }
     void randomize() {
          Random rnd = new Random();
          
          for(int x=0;x<matrixField.length;x++){
          for(int y=0;y<matrixField[0].length;y++)
                    if(rnd.nextInt(100)<=wallChance)
                         matrixField[x][y]=WALL;
                    else 
                    matrixField[x][y]=SPACE;
               }
     }
     public int getValueAtPos(int x,int y){
          return matrixField[x][y];
     }
     public int getRowsNumber (){
          return matrixField[0].length ;
     }
     public int getColumnsNumber (){
          return matrixField.length ;
     }

     public int[][] cloneMatrix(){
          int[][] clonedMatrix=new int[matrixField.length][matrixField[0].length];
          for(int x=0;x<matrixField.length;x++)
               for(int y=0;y<matrixField[0].length;y++)
                    clonedMatrix[x][y]=matrixField[x][y];
          return clonedMatrix;
     }


     private int[] getSizeFromInputFile(Scanner sc) throws FileNotFoundException {
          
          int[] coords=new int[2];

          coords[0]=sc.nextInt();
          coords[1]=sc.nextInt();
     return coords;
}
     private int getWallChanceFromFile(Scanner sc) {
          return sc.nextInt();
     }
}
