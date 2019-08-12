package doodle;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class SquiggleCommand extends PaintCommand {
	
	private ArrayList<Point> points=new ArrayList<Point>();
	
	public void add(Point p){ 
		this.points.add(p); 
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Point> getPoints(){ 
		return this.points;	
	}
	
	public String pointsToString() {
		
		String s = "\tpoints\n";
		
		for(Point p: this.getPoints()) {
			s += "\t\tpoint:" + p.toString() + "\n";
		}
		s += "\tend points";
		return s;
	}
	
	
	@Override
	public void execute(GraphicsContext g) {
		ArrayList<Point> points = this.getPoints();
		g.setStroke(this.getColor());
		for(int i=0;i<points.size()-1;i++){
			Point p1 = points.get(i);
			Point p2 = points.get(i+1);
			g.strokeLine(p1.x, p1.y, p2.x, p2.y);
		}	
	}

	@Override
	public String toString() {
		String fc = super.toString();
		return "\nSquiggle\n" + fc + this.pointsToString() + "\nEnd Squiggle";
	}
}
