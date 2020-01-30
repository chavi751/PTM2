package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JoystickDisplayer extends Canvas {

	

	private StringProperty joystickFileName;
	DoubleProperty xBin;
	DoubleProperty yBin;
	double xPos;
	double yPos;
	boolean firstTime=true;
	double xPosD=0;
	double yPosD=0;
	
	double radius;
	
	
	
	public JoystickDisplayer() {
		joystickFileName=new SimpleStringProperty();
		xBin=new SimpleDoubleProperty();
		yBin=new SimpleDoubleProperty();
		
		
		setxPos(getWidth()/4);
		setyPos(getHeight()/4);
	
	}
	
	public String getJoystickFileName() {
		return joystickFileName.get();
	}




	public void setJoystickFileName(String joystickFileName) {
		this.joystickFileName.set(joystickFileName);
	}




	public void redraw() {
		GraphicsContext gc = getGraphicsContext2D();
		Image joystick=null;
		double W=getWidth();
		double H=getHeight();
		
		this.xPosD=W/4;
		this.yPosD=H/4;
		this.radius=H/2-H/4;
		
		
		
		/*
		try {
			joystick=new Image(new FileInputStream(joystickFileName.get()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		gc.clearRect(0, 0, W, H);
		/*
		if(joystick==null)
			System.out.println("problem");
		*/
		//need only for first time to put joystick in the center
		if(firstTime==true) {
			firstTime=false;
			setxPos(xPosD);
			setyPos(yPosD);
		}
		
		gc.setFill(javafx.scene.paint.Color.BLACK);
		gc.fillOval(0,0, W, H);
		gc.setFill(javafx.scene.paint.Color.BLACK);
		gc.fillOval(W*0.05,H*0.05, W*0.9, H*0.9);
		gc.setFill(javafx.scene.paint.Color.RED);
		gc.fillOval(xPos,yPos, W/2, H/2);
		//gc.drawImage(joystick,xPos,yPos, W/2, H/2);
	
	}
	public void moveJoysticTo(double x, double y) {
		
		/*System.out.println("///////");
		System.out.println((Math.pow((x-xPosD),2)));
		System.out.println(Math.pow((y-yPosD),2));
		System.out.println(Math.pow(radius,2));*/
		if((Math.pow((x-xPosD),2)+Math.pow((y-yPosD),2))<Math.pow(radius,2)) {
			setxPos(x);
			setyPos(y);
		}
		else {
			double dist=Math.sqrt((Math.pow((x-xPosD),2)+Math.pow((y-yPosD),2)));
			double xA=(x-xPosD)/dist;
			double yA=(y-yPosD)/dist;
			setxPos(xA*radius+xPosD);
			setyPos(yA*radius+yPosD);
			
		}
	}
	
	
	public void setJoystickToDefultPosition() {
		
		setxPos(this.xPosD);
		setyPos(this.yPosD);
		redraw();
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
		xBin.set((this.xPos-this.xPosD)/this.radius);
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
		yBin.set((this.yPos-this.yPosD)/this.radius*-1);
	}


	

	

	
	
	
}
