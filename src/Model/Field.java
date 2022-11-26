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
          ArrayList<Integer[]> queue=new ArrayList<>();

          initializeQueueAndMatrix(queue,matrixCloned,fromX,fromY);
          Integer[] closest=getClosest(queue,matrixCloned,toX,toY);

          return extractPath(matrixCloned,closest[0],closest[1]);
     }
     private void initializeQueueAndMatrix(ArrayList<Integer[]>queue, int[][]matrix,int fromX,int fromY){
          queue.add(new Integer[]{fromX,fromY});
          matrix[fromX][fromY]=0;
     }
     private Integer[] getClosest(ArrayList<Integer[]> queue,int[][]matrix, int toX,int toY){
          Integer[] closest=queue.get(0);
          while(!isAtDestination(queue.get(0), toX, toY)){
               closest=checkNeighbours(matrix,queue,toX,toY,closest);
               if(queue.size()>1){
                    queue.remove(0);
               }
               else{
                    return closest;
               }
          }
          return queue.get(0);
     }

     //per qualche ragione closest non viene passato come reference
     Integer[] checkNeighbours(int[][]matrix,ArrayList<Integer[]> queue,int toX,int toY,Integer[]closest){
          int currentX,currentY;
          int value=matrix[queue.get(0)[0]][queue.get(0)[1]];
          currentX=queue.get(0)[0];
          currentY=queue.get(0)[1];
          
          for(int x=currentX-1;x<=currentX+1;x++)
          for(int y=currentY-1;y<=currentY+1;y++)
               if((x==currentX || y == currentY) && utilities.between(x, 0, matrix.length-1)&& utilities.between(y, 0, matrix[0].length-1) ){
                    if(matrix[x][y] == Field.SPACE){
                         queue.add(new Integer[]{x,y});
                         if(distance(toX, toY, x, y)<distance(toX, toY,closest[0], closest[1])){
                              closest=new Integer[]{x,y};
                         }
                         matrix[x][y]=value+1;
                    }
               }
          return closest;
     }
     private boolean isAtDestination(Integer[]coord,int toX,int toY){
          return (coord[0]==toX && coord[1]==toY);
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
private double distance(int x1,int y1,int x2,int y2){
     return Math.abs(Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)));
}
     private int getWallChanceFromFile(Scanner sc) {
          return sc.nextInt();
     }
}
