package doodle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class FeatureChooserPanel extends GridPane implements EventHandler<ActionEvent>  {
	
	private View view;

	public FeatureChooserPanel(View view) {
		
		this.view = view;
		ToggleGroup toggleGroup = new ToggleGroup();
		
		String[] buttonLabels = {"Fill"};

		for (String label : buttonLabels) {
			ToggleButton button = new ToggleButton(label);
			button.setCursor(Cursor.HAND);
			button.setMinWidth(80);
			button.setToggleGroup(toggleGroup);
			this.add(button, 1, 0);
			//col++;
			button.setOnAction(this);
		}
		SplitMenuButton colourMenu = new SplitMenuButton();
		colourMenu.setAlignment(Pos.CENTER);
		colourMenu.setMinWidth(120);
		colourMenu.setText("Colour");
		this.add(colourMenu, 0, 0);
		ColourHandlerMenu colourHandlerMenu = new ColourHandlerMenu(this.view, colourMenu);
		String[] colourLabels = {
				"Random (default)", "Black", "White", "Blue", "Red", "Yellow", "Brown", "Green", "Purple"};
		
		for (String colour : colourLabels) {
			MenuItem colourItem = new MenuItem(colour);
			colourMenu.getItems().add(colourItem);
			colourItem.setOnAction(colourHandlerMenu);
			
		}
	}
	
	public View getView() {
		return this.view;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		ToggleButton selectedTogger = (ToggleButton) event.getSource();
		
		if (!(this.view.getCurrentShape() == null)) {
			if (selectedTogger.getText() == "Fill") {
				ShapeManipulatorStrategy strategy = ShapeManipulatorFactory.create(this.view.getCurrentShape(), view.getPaintModel());
				if (selectedTogger.isSelected()) {
					this.view.getShapeChooserPanel().setFill(true);
					strategy.setFilled(this.view.getShapeChooserPanel().isFill());
					strategy.setCurrentColour(this.view.getCurrentColour());
				}
				else {
					this.view.getShapeChooserPanel().setFill(false);
					strategy.setFilled(this.view.getShapeChooserPanel().isFill());
					strategy.setCurrentColour(this.view.getCurrentColour());
				}
				
				if (this.view.getCurrentShape() == "Eraser") {
					strategy.setCurrentColour("White");
				}
				this.view.setPaintPanelShapeManipulatorStrategy(strategy);	
			}
		}
		else {selectedTogger.setSelected(false);}
	}
}
