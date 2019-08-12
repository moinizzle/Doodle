package doodle;

import javafx.scene.input.MouseEvent;

public class EraserManipulatorStrategy extends SquiggleManipulatorStrategy {

	EraserManipulatorStrategy(PaintModel paintModel) {
		super(paintModel);
	}
	
	private EraserCommand eraserCommand;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.eraserCommand.add(new Point((int)e.getX(), (int)e.getY()));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.eraserCommand = new EraserCommand();
		
		this.addCommand(eraserCommand);
		this.eraserCommand.add(new Point((int)e.getX(), (int)e.getY()));
	}
}


