package View;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Model.FieldContainer;
import processing.core.PApplet;

public class Inizia extends PApplet {

	boolean setup=false;
	final int NUMBER_OF_ATTRACTORS=100;

	FieldContainer field;

	public void settings(){
		size(500, 500);
		
		try {
			field = new FieldContainer("../Resources.size.txt",width,height);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void draw(){
		background(120);
	}		

	private void drawField(FieldContainer fd){
		int[]sizesXY=fd.getSizesXY();
		int x=sizesXY[0];
		int y=sizesXY[1];
		rect(x,y)
	}

public class Main {
     

     public static void main(String[] passedArgs) {

          System.out.println("dio");
     }
}
}
