package doodle;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TriangleManipulatorStrategy extends ShapeManipulatorStrategy {

	TriangleManipulatorStrategy(PaintModel paintModel) {
		super(paintModel);
	}
		
	private TriangleCommand triangleCommand;
	private LineCommand lineCommand;
	private int clickCount;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.lineCommand.add(new Point((int) e.getX(), (int) e.getY()));
	}
	
	// mouse clicked
	@Override
	public void mousePressed(MouseEvent e) {
		
		if ((clickCount % 2 ) == 0) {
			Point p1 = new Point((int)e.getX(), (int)e.getY());
			Point p2 = new Point((int)e.getX(), (int)e.getY());
			this.lineCommand = new LineCommand();
			this.lineCommand.add(p1);
			this.lineCommand.add(p2);
			this.lineCommand.setColor(Color.GREY);
			this.lineCommand.setFill(true);
			this.addCommand(lineCommand);
		}
		
		else {
			ArrayList<Point> points = this.lineCommand.getPoints();
			Point p1 = points.get(0);
			Point p2 = points.get(points.size() - 1);
			this.triangleCommand = new TriangleCommand(p1, p2);
			Point p3 = new Point((int) e.getX(), (int) e.getY());
			this.triangleCommand.setP3(p3);
			this.addCommand(triangleCommand);
			this.delCommand(lineCommand);	
		}
		clickCount++;
	}
}
