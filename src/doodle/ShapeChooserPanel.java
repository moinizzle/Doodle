package doodle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view
	
	private boolean isFill;
	private ToggleGroup toggleGroup;
	 
	
	public ShapeChooserPanel(View view) {

		this.view = view;
		toggleGroup = new ToggleGroup();

		String[] buttonLabels = {"Squiggle", "Line", "Circle", "Triangle", "Rectangle", "Eraser"};

		int row = 0;
		for (String label : buttonLabels) {
			ToggleButton button = new ToggleButton(label);
			button.setCursor(Cursor.HAND);
			button.setMinWidth(80);
			button.setToggleGroup(toggleGroup);
			this.add(button, 0, row);
			row++;
			button.setOnAction(this);
		}
		
		//toggleGroup.selectToggle(toggleGroup.getToggles().get(0));
	}

	@Override
	public void handle(ActionEvent event) {

		// obtain command's name from event source (name of button)
		String command = ((ToggleButton) event.getSource()).getText();
		// ask factory to make a new strategy
		// strategyName = command
		this.view.setCurrentShape(command);
		ShapeManipulatorStrategy strategy = ShapeManipulatorFactory.create(this.view.getCurrentShape(), view.getPaintModel());
		strategy.setFilled(this.isFill());
		strategy.setCurrentColour(this.view.getCurrentColour());
		if (this.view.getCurrentShape() == "Eraser") {
			strategy.setCurrentColour("White");
		}
		this.view.setPaintPanelShapeManipulatorStrategy(strategy);
	}

	public boolean isFill() {
		return isFill;
	}

	public void setFill(boolean isFill) {
		this.isFill = isFill;
	}

}	
