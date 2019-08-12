package doodle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

public class ColourHandlerMenu implements EventHandler<ActionEvent> {
	
	private View view;
	private SplitMenuButton colourMenu;
	
	public ColourHandlerMenu(View view, SplitMenuButton colourMenu) {
		this.view = view;
		this.colourMenu = colourMenu;
	}
	@Override
	public void handle(ActionEvent event) {
		
		if (!(this.view.getCurrentShape() == null)) {
			MenuItem selectedColour = (MenuItem) event.getSource();
			String colour = selectedColour.getText();
			this.colourMenu.setText(colour);
			this.view.setCurrentColour(colour);
			ShapeManipulatorStrategy strategy = ShapeManipulatorFactory.create(this.view.getCurrentShape(), view.getPaintModel());
			strategy.setFilled(this.view.getShapeChooserPanel().isFill());
			strategy.setCurrentColour(this.view.getCurrentColour());
			if (this.view.getCurrentShape() == "Eraser") {
				strategy.setCurrentColour("White");
			}
			this.view.setPaintPanelShapeManipulatorStrategy(strategy);
		}
	}

}
