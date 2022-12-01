import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

import Entities.Characters.DrawableRobot;
import Model.*;
import Model.Entities.Entity;
import Model.Entities.Characters.Robot;
import processing.core.PApplet;
import processing.core.PVector;

public class Inizia extends PApplet {

	public static int LEFT_ClICK = 37;
	public static int RIGHT_ClICK = 39;

	int currentResolutionX;
	int currentResolutionY;

	boolean setup=false;
	String PATH;
	Environment environment;

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

		
		FieldContainer field=null;
		try {
			field = new FieldContainer(PATH,width,height);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		field.randomize();
		environment=new Environment(field,this);
	}

	public void draw(){
		if(!setup)setup();
		ArrayList<Robot> allRobots = utilities.getAllRobots(environment);
		if(wasResized()){
			updateResolution();
			environment.field.setSizes(width, height);
			allRobots.forEach(bot -> { 
				(bot).resize(environment.field.getOldSquareSize(),
						   environment.field.getOldSquareSize());});
		}
		drawField(environment.field);

		allRobots.forEach(bot->{
			if(frameCount%4==0)
				bot.advance();	
			bot.draw();
		});
	}		

	private void updateResolution() {
		currentResolutionX=width;
		currentResolutionY=height;
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

		drawBackground(startingPosition,environment.field);
		drawBoundaries(startingPosition);
		drawWalls(startingPosition);
	}

	private void drawWalls(PVector startingPosition) {
		
		fill(0);
		stroke(0);

		float offsetX;
		float offsetY;
		for(int x=0;x<environment.field.getCellsNumber()[0];x++)
			for(int y=0;y<environment.field.getCellsNumber()[1];y++)
				{
					offsetX=(float)environment.field.getSquareSize()*x;
					offsetY=(float)environment.field.getSquareSize()*y;
					if(environment.field.getValueAtPos(x, y)==Field.WALL){
	
						rect(startingPosition.x+offsetX+1,
							startingPosition.y+offsetY+1, 
							(float)environment.field.getSquareSize()-1, 
							(float)environment.field.getSquareSize()-1);
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
		for(int x=0;x<environment.field.getCellsNumber()[0];x++)
			for(int y=0;y<environment.field.getCellsNumber()[1];y++)
				{
					offsetX=(float)environment.field.getSquareSize()*x;
					offsetY=(float)environment.field.getSquareSize()*y;
					
					rect(startingPosition.x+offsetX+1,
						startingPosition.y+offsetY+1, 
						(float)environment.field.getSquareSize()-1, 
						(float)environment.field.getSquareSize()-1);
							
				}
	}
	private float[] normalizeMouse(){
		float x=mouseX- environment.field.getDrawingPosition()[0];
		float y=mouseY- environment.field.getDrawingPosition()[1];

		return new float[]{x,y};
	}
	public void mouseClicked() {
		if(mouseButton==LEFT_ClICK){
			float[]coords=normalizeMouse();
			environment.add(Optional.of(new DrawableRobot(coords,environment)));
		}
		if(mouseButton==RIGHT_ClICK){
			
			ArrayList<Robot> allRobots = utilities.getAllRobots(environment);
			
			float[]coords=normalizeMouse();
			for (Robot bot : allRobots) {
				(bot).setDestination(new int[]{(int)(coords[0]/environment.field.getSquareSize()),
										 (int)(coords[1]/environment.field.getSquareSize())});
			}
		}
	}
	public static void main(String[] passedArgs) {

		String[] processingArgs = {"Inizia"};
		Inizia inizia = new Inizia();
		PApplet.runSketch(processingArgs, inizia);
	}
}
