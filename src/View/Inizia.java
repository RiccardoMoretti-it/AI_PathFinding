import java.io.File;
import java.io.FileNotFoundException;

import Model.*;
import processing.core.PApplet;
import processing.core.PVector;

public class Inizia extends PApplet {

	public static int LEFT_ClICK = 37;
	public static int RIGHT_ClICK = 39;

	int currentResolutionX;
	int currentResolutionY;

	boolean setup=false;
	String PATH;
	FieldContainer field;
	Robot bot;

	public void settings(){
		size(400, 400);
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
		field.randomize();
	}

	public void draw(){
		if(!setup)setup();
		if(wasResized()){
			updateResolution();
			field.setSizes(width, height);
			adjustRobotPosition();
		}
		drawField(field);

		if(bot!=null){
			if(frameCount%4==0)
			bot.advance();
			drawBot();		print("\n"+bot.getPosition()[0],bot.getPosition()[1]);

		}
	}		

	private void adjustRobotPosition() {
		float ratio=field.getSquareSize()/field.getOldSquareSize();
		float[] coords=bot.getPosition();
		coords[0]*=ratio;
		coords[1]*=ratio;

		bot.setNewPosition(coords);
	}
	private void updateResolution() {
		currentResolutionX=width;
		currentResolutionY=height;
	}
	private void drawBot() {
			float startX=field.getDrawingPosition()[0]+bot.getPosition()[0];
			float startY=field.getDrawingPosition()[1]+bot.getPosition()[1];
			fill(200,40,40);
			ellipse((float)startX,(float)startY,(float)field.getSquareSize(),(float)field.getSquareSize());
	
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

		drawBackground(startingPosition,field);
		drawBoundaries(startingPosition);
		drawWalls(startingPosition);
	}

	private void drawWalls(PVector startingPosition) {
		
		fill(0);
		stroke(0);

		float offsetX;
		float offsetY;
		for(int x=0;x<field.getCellsNumber()[0];x++)
			for(int y=0;y<field.getCellsNumber()[1];y++)
				{
					offsetX=(float)field.getSquareSize()*x;
					offsetY=(float)field.getSquareSize()*y;
					if(field.getValueAtPos(x, y)==Field.WALL){
	
						rect(startingPosition.x+offsetX+1,
							startingPosition.y+offsetY+1, 
							(float)field.getSquareSize()-1, 
							(float)field.getSquareSize()-1);
					}
				}
	}
	void drawBackground(PVector startingPosition,FieldContainer fd){
		fill(255,255,255,2);
		rect(startingPosition.x,startingPosition.y,fd.getFieldLength(), fd.getFieldHeight());
		drawBoundaries(startingPosition);
	}

	private void drawBoundaries(PVector startingPosition){
		fill(255,255,255,2);
		stroke(0);
		float offsetX;
		float offsetY;
		for(int x=0;x<field.getCellsNumber()[0];x++)
			for(int y=0;y<field.getCellsNumber()[1];y++)
				{
					offsetX=(float)field.getSquareSize()*x;
					offsetY=(float)field.getSquareSize()*y;
					
					rect(startingPosition.x+offsetX+1,
						startingPosition.y+offsetY+1, 
						(float)field.getSquareSize()-1, 
						(float)field.getSquareSize()-1);
							
				}
	}
	private float[] normalizeMouse(){
		float x=mouseX- field.getDrawingPosition()[0];
		float y=mouseY- field.getDrawingPosition()[1];

		return new float[]{x,y};
	}
	public void mouseClicked() {
		if(mouseButton==LEFT_ClICK){
			float[]coords=normalizeMouse();
			bot=new Robot(coords[0],coords[1], field);
		}
		if(mouseButton==RIGHT_ClICK){
			if(bot!=null){
				float[]coords=normalizeMouse();
				bot.setDestination(new int[]{(int)(coords[0]/field.getSquareSize()), (int)(coords[1]/field.getSquareSize())});
			}
		}
	}
	public static void main(String[] passedArgs) {

		String[] processingArgs = {"Inizia"};
		Inizia inizia = new Inizia();
		PApplet.runSketch(processingArgs, inizia);
	}
}
