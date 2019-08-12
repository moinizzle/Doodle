package doodle;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import doodle.PaintCommand;
import javafx.scene.canvas.GraphicsContext;

public class PaintModel extends Observable implements Observer {

	private ArrayList<PaintCommand> commands = new ArrayList<PaintCommand>();

	public void reset(){
		for(PaintCommand c: this.commands){
			c.deleteObserver(this);
		}
		this.commands.clear();
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addCommand(PaintCommand command){
		this.commands.add(command);
		command.addObserver(this);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void delCommand(PaintCommand command) {
		this.commands.remove(command);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setCommands(ArrayList<PaintCommand> commands) {
		this.commands = commands;
	}
	
	public ArrayList<PaintCommand> getCommands() {
		return commands;
	}

	public void executeAll(GraphicsContext g) {
		for(PaintCommand c: this.commands){
			c.execute(g);
		}
	}
	
	public void save(PrintWriter writer) {
		
		writer.print("PaintSaveFileVersion1.0");
		
		for(PaintCommand c : this.getCommands()) {
			writer.print(c.toString());
		}
		
		writer.print("\nEndPaintSaveFile");
	}
	
	/**
	 * We Observe our model components, the PaintCommands
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.setChanged();
		this.notifyObservers();
	}
}
