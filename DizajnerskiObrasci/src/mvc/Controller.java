package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

import command.Command;
import command.AddShape;
import shapes.Circle;
import shapes.HexagonAdapter;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Square;

public class Controller  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Model model;
	private Frame frame;
	private ArrayList <Command> commands= new ArrayList<Command>();
	private  Point firstPoint;
	
	public Controller(Model model, Frame frame) {
		this.model = model;
		this.frame = frame;
	}
	public void draw(MouseEvent e, Color outColor, Color inColor)
	{
		if (frame.getBtnPoint().isSelected()) 
		{
			Point point = new shapes.Point(e.getX(), e.getY(), outColor);
			addCommand(new AddShape(model, point));
		} 
		else if (frame.getBtnLine().isSelected())
		{
			if (firstPoint == null) {

				firstPoint = new Point(e.getX(), e.getY(), outColor);
			} else {
				Point secondPoint = new Point(e.getX(), e.getY(), outColor);
				Line line = new Line(firstPoint, secondPoint, outColor);
				firstPoint = null;
				addCommand(new AddShape(model, line));
			}
		} 
		else if (frame.getBtnSquare().isSelected())
		{
			//Dialog
			//AddShape
		
		} else if (frame.getBtnRectangle().isSelected()) {
			//dialog
			//AddShape
		} else if (frame.getBtnCircle().isSelected()) {
			//Dialog
			//AddShape
		
		} else if (frame.getBtnHexagon().isSelected()) {
			//Dialog
			//AddShape
		
		}
		frame.update();
	
	}
	public void addCommand(Command c) {
		commands.add(c);
		c.execute();
		frame.addToLogList(c.toString());
	

		frame.getBtnUndo().setEnabled(true);
	}
	public void save() {
		// TODO Auto-generated method stub
		
	}
	public void open() {
		// TODO Auto-generated method stub
		
	}

	public void undo() {
		// TODO Auto-generated method stub
		
	}
	public void redo() {
		// TODO Auto-generated method stub
		
	}
	public void select() {
		// TODO Auto-generated method stub
		
	}
	public void edit() {
		// TODO Auto-generated method stub
		
	}
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	public void toFront() {
		// TODO Auto-generated method stub
		
	}
	public void toBack() {
		// TODO Auto-generated method stub
		
	}
	public void bringToBack() {
		// TODO Auto-generated method stub
		
	}
	public void bringToFront() {
		// TODO Auto-generated method stub
		
	}
}
