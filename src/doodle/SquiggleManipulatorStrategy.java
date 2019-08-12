package doodle;
import javafx.scene.input.MouseEvent;

class SquiggleManipulatorStrategy extends ShapeManipulatorStrategy {
	
	SquiggleManipulatorStrategy(PaintModel paintModel) {
		super(paintModel);
	}

	private SquiggleCommand squiggleCommand;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.squiggleCommand.add(new Point((int)e.getX(), (int)e.getY()));
	}

	// create a new EraserCommand when mouse is pressed 
	@Override
	public void mousePressed(MouseEvent e) {
			this.squiggleCommand = new SquiggleCommand();
			this.addCommand(squiggleCommand);
	}
}

