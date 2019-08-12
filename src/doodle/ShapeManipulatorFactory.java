package doodle;

public class ShapeManipulatorFactory {
	
	public static ShapeManipulatorStrategy create(String strategyName, PaintModel paintModel){
		
		ShapeManipulatorStrategy strategy=null;
		
		switch (strategyName) {
			case "Squiggle": strategy = new SquiggleManipulatorStrategy(paintModel);
				break;
			case "Line": strategy = new LineManipulatorStrategy(paintModel);
				break;
			case "Circle": strategy = new CircleManipulatorStrategy(paintModel);
				break;
			case "Triangle": strategy = new TriangleManipulatorStrategy(paintModel);
				break;
			case "Rectangle": strategy = new RectangleManipulatorStrategy(paintModel);
				break;
			case "Eraser": strategy = new EraserManipulatorStrategy(paintModel);
				break;
		}
		return strategy;
	}
		
	
}
