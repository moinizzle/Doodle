package doodle;

import javafx.scene.input.MouseEvent;

public class LineManipulatorStrategy extends ShapeManipulatorStrategy {

	LineManipulatorStrategy(PaintModel paintModel) {
		super(paintModel);
	}
	
	private LineCommand lineCommand;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.lineCommand.add(new Point((int)e.getX(), (int)e.getY()));
	}

	// create a new EraserCommand when mouse is pressed 
	@Override
	public void mousePressed(MouseEvent e) {
			this.lineCommand = new LineCommand();
			this.addCommand(lineCommand);
	}
	
}
