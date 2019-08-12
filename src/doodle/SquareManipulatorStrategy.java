package doodle;

import javafx.scene.input.MouseEvent;

public class SquareManipulatorStrategy extends RectangleManipulatorStrategy {

	SquareManipulatorStrategy(PaintModel paintModel) {
		super(paintModel);
		// TODO Auto-generated constructor stub
	}
	
	private SquareCommand squareCommand;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p2=new Point((int)e.getX(), (int)e.getY());
		this.squareCommand.setP2(p2);
	}

	@Override
	public void mousePressed(MouseEvent e) {
			Point p1 = new Point((int)e.getX(), (int)e.getY());
			Point p2 = new Point((int)e.getX(), (int)e.getY());

			this.squareCommand = new SquareCommand(p1,p2);
			this.addCommand(squareCommand);			
	}

}
