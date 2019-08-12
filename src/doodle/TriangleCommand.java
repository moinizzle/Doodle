package doodle;

import javafx.scene.canvas.GraphicsContext;

public class TriangleCommand extends PaintCommand {
	
	private Point p1, p2, p3;
	
	public TriangleCommand(Point p1, Point p2) {
		this.p1 = p1; this.p2 = p2; this.setP3(null);
		this.setChanged();
		this.notifyObservers();
	}
	
	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
		this.setChanged();
		this.notifyObservers();
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
		this.setChanged();
		this.notifyObservers();
	}
	
	public Point getP3() {
		return p3;
	}

	public void setP3(Point p3) {
		this.p3 = p3;
		this.setChanged();
		this.notifyObservers();
	}
	
	@Override
	public void execute(GraphicsContext g) {

		if (this.isFill()){
			g.setFill(this.getColor());
			g.fillPolygon(new double[]{this.getP1().x, this.getP2().x, this.getP3().x},
                    new double[]{this.getP1().y, this.getP2().y, this.getP3().y}, 3);
		} 
		
		else {
			g.setStroke(this.getColor());
			g.strokePolygon(new double[]{this.getP1().x, this.getP2().x, this.getP3().x},
                    new double[]{this.getP1().y, this.getP2().y, this.getP3().y}, 3);
		}
		
		
	}



}
