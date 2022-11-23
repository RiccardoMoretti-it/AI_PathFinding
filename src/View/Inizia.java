import java.io.File;
import java.io.FileNotFoundException;

import Model.FieldContainer;
import processing.core.PApplet;
import processing.core.PVector;

public class Inizia extends PApplet {

	int currentResolutionX;
	int currentResolutionY;

	boolean setup=false;
	String PATH;
	FieldContainer field;

	public void settings(){
		size(399, 500);
	}
	public void setup(){
		setup=true;	
		surface.setResizable(true);		
		String PATH=System.getProperty("user.dir")+File.separator+"src"+File.separator+"Resources"+File.separator+"size.txt";
		currentResolutionX=width;
		currentResolutionY=height;

		background(120);
		try {
			field = new FieldContainer(PATH,width,height);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void draw(){
		if(!setup)setup();
		if(wasResized()){
			field.setSizes(width, height);
		}
		drawField(field);
	}		

	private boolean wasResized() {
		if(width!=currentResolutionX || height!=currentResolutionY){
			currentResolutionX=width;
			currentResolutionY=height;
			return true;
		}
		return false;
	}
	private void drawField(FieldContainer fd){
		
		PVector startingPosition=new PVector((float)fd.getDrawingPosition()[0],(float)fd.getDrawingPosition()[1]);

		rect(startingPosition.x,startingPosition.y,fd.getFieldLength(), fd.getFieldHeight());
	}

	public static void main(String[] passedArgs) {

		String[] processingArgs = {"Inizia"};
		Inizia inizia = new Inizia();
		PApplet.runSketch(processingArgs, inizia);
	}
}
