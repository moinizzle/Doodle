package doodle;

import java.util.Observable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class PaintCommand extends Observable {
	
	private Color color;
	private boolean fill;
	private int r;
	private int g;
	private int b;
	
	PaintCommand(){

		this.color = PaintCommand.generateColour();
		this.r = (int)this.color.getRed()*255;
		this.g = (int)this.color.getGreen()*255;
		this.b = (int)this.color.getBlue()*255;
		
		this.fill = (1==(int)(Math.random()*2));
	}
	
	PaintCommand(boolean isFilled){
		this.fill = isFilled;
	}
	
	public static Color generateColour() {
		
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		
		return Color.rgb(r, g, b);
		
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setColor(String colour) {
			if (colour == "Random (default)") {this.setColor(PaintCommand.generateColour());}
			else if (colour == "Black") {this.setColor(Color.BLACK);}
			else if (colour == "White") {this.setColor(Color.WHITE);}
			else if (colour == "Blue") {this.setColor(Color.BLUE);}
			else if (colour == "Red") {this.setColor(Color.RED);}
			else if (colour == "Yellow") {this.setColor(Color.YELLOW);}
			else if (colour == "Brown") {this.setColor(Color.BROWN);}
			else if (colour == "Green") {this.setColor(Color.GREEN);}
			else if (colour == "Purple") {this.setColor(Color.PURPLE);}
	}
	
	public boolean isFill() {
		return fill;
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public String toString(){
		
		String s = "";
		s+="\tcolor:"+this.r+","+g+","+b+"\n";
		s+="\tfilled:"+this.fill+"\n";
		return s;
	}
	
	public abstract void execute(GraphicsContext g);
}
