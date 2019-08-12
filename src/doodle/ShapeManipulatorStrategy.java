package doodle;
import doodle.PaintCommand;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class ShapeManipulatorStrategy implements EventHandler<MouseEvent> {
	
	private PaintModel paintModel;
	private boolean isFilled;
	private String currentColour;
	
	ShapeManipulatorStrategy(PaintModel paintModel){
		this.paintModel=paintModel;
	}
	
	
	// adds when user is done manipulating the final shape
	void addCommand(PaintCommand command){
		if (this.isFilled) {
			command.setFill(this.isFilled);
		}
		
		else {
			command.setFill(false);
		}
		command.setColor(this.getCurrentColour());
		this.paintModel.addCommand(command);
		
	}

	void delCommand(PaintCommand command) {
		this.paintModel.delCommand(command);
	}
	
	// handles all mouse events
	
	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			this.mouseDragged(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			this.mousePressed(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			this.mouseMoved(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			this.mouseClicked(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			this.mouseReleased(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			this.mouseEntered(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			this.mouseExited(event);
		}
	}
	public void mouseMoved(MouseEvent e) { }
	public void mouseDragged(MouseEvent e) { }
	public void mouseClicked(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }

	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public String getCurrentColour() {
		return currentColour;
	}

	public void setCurrentColour(String currentColour) {
		this.currentColour = currentColour;
	}
}
