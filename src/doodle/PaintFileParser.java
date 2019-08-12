package doodle;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.paint.Color;

/**
 * Parse a file in Version 1.0 PaintSaveFile format. An instance of this class
 * understands the paint save file format, storing information about
 * its effort to parse a file. After a successful parse, an instance
 * will have an ArrayList of PaintCommand suitable for rendering.
 * If there is an error in the parse, the instance stores information
 * about the error. For more on the format of Version 1.0 of the paint 
 * save file format, see the associated documentation.
 * 
 * @author 
 *
 */
public class PaintFileParser {
	
	private int lineNumber = 0; // the current line being parsed
	private String errorMessage =""; // error encountered during parse
	private PaintModel paintModel;
	
	/**
	 * Below are Patterns used in parsing 
	 */
	private Pattern pFileStart=Pattern.compile("^PaintSaveFileVersion1.0$");
	private Pattern pFileEnd=Pattern.compile("^EndPaintSaveFile$");
	
	private Pattern pColor=Pattern.compile("^color:([0-9]*),([0-9]*),([0-9]*)$");
	private Pattern pFilled=Pattern.compile("^filled:(true|false)$");

	private Pattern pCircleStart=Pattern.compile("^Circle$");
	private Pattern pCenter = Pattern.compile("^center:\\(([0-9]*),([0-9]*)\\)$");
	private Pattern pRadius = Pattern.compile("^radius:([0-9]*)$");
	private Pattern pCircleEnd=Pattern.compile("^EndCircle$");
	
	// ADD MORE!!
	
	private Pattern pRectangleStart=Pattern.compile("^Rectangle$");
	private Pattern pRectangleP1 =Pattern.compile("^p1:\\(([0-9]*),([0-9]*)\\)$");
	private Pattern pRectangleP2 =Pattern.compile("^p2:\\(([0-9]*),([0-9]*)\\)$");
	private Pattern pRectangleEnd=Pattern.compile("^EndRectangle$");
	
	
	private Pattern pSquiggleStart=Pattern.compile("^Squiggle$");
	private Pattern pSquigglePointStart= Pattern.compile("^points$");
	private Pattern pSquigglePoint =Pattern.compile("^point:\\(([0-9]*),([0-9]*)\\)$");
	private Pattern pSquigglePointEnd=Pattern.compile("^endpoints$");
	private Pattern pSquiggleEnd=Pattern.compile("^EndSquiggle$");
	/**
	 * Store an appropriate error message in this, including 
	 * lineNumber where the error occurred.
	 * @param mesg
	 */
	private void error(String mesg){
		this.errorMessage = "Error in line "+lineNumber+" "+mesg;
	}
	
	/**
	 * 
	 * @return the error message resulting from an unsuccessful parse
	 */
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	/**
	 * Parse the inputStream as a Paint Save File Format file.
	 * The result of the parse is stored as an ArrayList of Paint command.
	 * If the parse was not successful, this.errorMessage is appropriately
	 * set, with a useful error message.
	 * 
	 * @param inputStream the open file to parse
	 * @param paintModel the paint model to add the commands to
	 * @return whether the complete file was successfully parsed
	 */
	
	public PaintModel getModel() {
		return this.paintModel;
	}
	public boolean parse(BufferedReader inputStream, PaintModel paintModel) {
		
		//Color color;
		this.paintModel = paintModel;
		this.errorMessage="";
		
		// During the parse, we will be building one of the 
		// following commands. As we parse the file, we modify 
		// the appropriate command.
		
		CircleCommand circleCommand = null; 
		RectangleCommand rectangleCommand = null;
		SquiggleCommand squiggleCommand = null;
	
		try {	
			//l: line
			//state: 
			int state=0; Matcher m; String l;
			
			this.lineNumber=0;
			//inputStream is reader
			while ((l = inputStream.readLine()) != null) {
				this.lineNumber++;

				
				l = l.replaceAll("\\s+", "");
				if(l.isEmpty()) continue;
				System.out.println(lineNumber+" "+l+" "+state);
				
				// switch means 
				//allows a var to be tested for 
				//equality list of values
								
				switch(state){
					
				case 0:
						m=pFileStart.matcher(l);
						if(m.matches()){
							state=1;
							break;
						}
						error("Expected Start of Paint Save File");
						return false;
					
				case 1: // Looking for the start of a new object or end of the save file
						m=pCircleStart.matcher(l);
						
						if(m.matches()){
							circleCommand = new CircleCommand(null, 0);
							// ADD CODE!!!
							state=2; 
							break;
						}
						
						m=pRectangleStart.matcher(l);
						rectangleCommand = new RectangleCommand(null, null);
						if(m.matches()){
							// ADD CODE!!!
							state=7; 
							break;
						}
						
						m=pSquiggleStart.matcher(l);
						
						if(m.matches()){
							squiggleCommand = new SquiggleCommand();
							// ADD CODE!!!
							state=12; 
							break;
						}
						
						m=pFileEnd.matcher(l);
						
						if(m.matches()) {
							
							//PaintPanel paintPanel = new PaintPanel(this.paintModel);
							//paintPanel.repaint();
							
							break;
						}
						error("");
						return false;

					case 2:
						// ADD CODE
						m=pColor.matcher(l);
						if (m.matches()) {
							Color color = null;
							color = Color.rgb(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)));
							circleCommand.setColor(color);
							state=3;
							break;
						}
						error("");
						return false;
						
											
					
					case 3:
						m=pFilled.matcher(l);
						if(m.matches()) {
							circleCommand.setFill(Boolean.parseBoolean(m.group(1)));
							state=4;
							break;
						}
						error("");
						return false;
						
					case 4:
						m=pCenter.matcher(l);
						if(m.matches()) {
							circleCommand.setCentre(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)))) ;
							state=5;
							break;
						}
						error("");
						return false;
						
					case 5:
						m=pRadius.matcher(l);
						if(m.matches()) {
							circleCommand.setRadius(Integer.parseInt(m.group(1)));
							state=6;
							break;
						}
						error("");
						return false;
					
					case 6:
						m=pCircleEnd.matcher(l);
						if(m.matches()) {
							this.paintModel.addCommand(circleCommand);
							state=1;
							break;
						}
						error("");
						return false;
						
					case 7:
						m=pColor.matcher(l);
						if(m.matches()) {
							Color color = null;
							color = Color.rgb(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)));
							rectangleCommand.setColor(color);
							state=8;
							break;
						}
						error("");
						return false;
					case 8:
						m=pFilled.matcher(l);
						if(m.matches()) {
							rectangleCommand.setFill(Boolean.parseBoolean(m.group(1)));
							state=9;
							break;
						
						}
						error("");
						return false;
						
					case 9:
						m=pRectangleP1.matcher(l);
						if(m.matches()) {
							rectangleCommand.setP1(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
							state=10;
							break;
						}
						error("");
						return false;
					case 10:
						m=pRectangleP2.matcher(l);
						if(m.matches()) {
							rectangleCommand.setP2(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
							state=11;
							break;
						}
						error("");
						return false;
						
					case 11:
						m=pRectangleEnd.matcher(l);
						if(m.matches()) {
							this.paintModel.addCommand(rectangleCommand);
							state=1;
							break;
						}
						return false;
					

					case 12:
						m=pColor.matcher(l);
						if(m.matches()) {
							Color color = null;
							color = Color.rgb(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)));
							squiggleCommand.setColor(color);
							state=13;
							break;
						}
						error("");
						return false;
						
						
					case 13:
						m=pFilled.matcher(l);
						if(m.matches()) {
							squiggleCommand.setFill(Boolean.parseBoolean(m.group(1)));
							state=14;
							break;
						}
						return false;
						
					case 14:
						m=this.pSquigglePointStart.matcher(l);
						if(m.matches()) {
							state=15;
							break;
						}
						error("");
						return false;
					
					case 15:
						m = this.pSquigglePoint.matcher(l);
						//n = this.pSquigglePointEnd.matcher(l);
						if(m.matches()) {
							squiggleCommand.add(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
							state=15;
							break;

						}

						

						m=this.pSquigglePointEnd.matcher(l);
						if(m.matches()) {
						state=16;
						break;
					
						}
						error("");
						return false;
						
					case 16:
						m = this.pSquiggleEnd.matcher(l);
						this.paintModel.addCommand(squiggleCommand);
						if(m.matches()) {
							state=1;
							break;
						}
						error("");
						return false;

				}
			}
		}  
		
		catch (Exception e){
			System.out.println(e);
		}
		
		
		return true;
	}

}
