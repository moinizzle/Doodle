package doodle;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class EraserCommand extends SquiggleCommand{
	
	@Override
	public void execute(GraphicsContext g) {
		g.setLineWidth(100);
		ArrayList<Point> points = this.getPoints();
		g.setStroke(this.getColor());
		for(int i=0;i<points.size()-1;i++){
			Point p1 = points.get(i);
			Point p2 = points.get(i+1);
			g.setLineWidth(20.0f);
			g.strokeLine(p1.x, p1.y, p2.x, p2.y);
		}
		g.setLineWidth(1);
	}

	
}
