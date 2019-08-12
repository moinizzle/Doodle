package doodle;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class LineCommand extends SquiggleCommand {
	
	@Override
	public void execute(GraphicsContext g) {
		ArrayList<Point> points = this.getPoints();
		
		if(!points.isEmpty()) {
			Point p1 = points.get(0);
			Point p2 = points.get(points.size() - 1);
			g.setStroke(this.getColor());
			g.strokeLine(p1.x, p1.y, p2.x, p2.y);
		}
	}
}
