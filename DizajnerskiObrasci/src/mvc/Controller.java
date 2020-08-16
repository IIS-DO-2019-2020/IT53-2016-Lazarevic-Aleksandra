package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

import command.Command;
import dialogs.DlgAddEditCircle;
import dialogs.DlgAddEditHexagon;
import dialogs.DlgAddEditRectangle;
import dialogs.DlgAddEditSquare;
import command.AddShape;
import shapes.Circle;
import shapes.HexagonAdapter;
import hexagon.Hexagon;
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
			DlgAddEditSquare dlgSquare = new DlgAddEditSquare();
			dlgSquare.addSquare(e.getX(), e.getY(), outColor, inColor);
			dlgSquare.setVisible(true);
				frame.getBtnColorOut().setBackground(dlgSquare.getColorOut());
				frame.getBtnColorIn().setBackground(dlgSquare.getColorIn());
			Square square = new Square(new Point(dlgSquare.getX(), dlgSquare.getY()), dlgSquare.getSide(),dlgSquare.getColorOut(), dlgSquare.getColorIn());
			if(dlgSquare.getSave()){
				addCommand(new AddShape(model, square));
			}
		
		} else if (frame.getBtnRectangle().isSelected()) {
			
			DlgAddEditRectangle dlgRect = new DlgAddEditRectangle();
			dlgRect.addRectangle(e.getX(), e.getY(), outColor, inColor);
			dlgRect.setVisible(true);
				frame.getBtnColorOut().setBackground(dlgRect.getColorOut());
				frame.getBtnColorIn().setBackground(dlgRect.getColorIn());
			Rectangle rectangle = new Rectangle(new Point(dlgRect.getX(), dlgRect.getY()), dlgRect.getWidth(), dlgRect.getHeight(), dlgRect.getColorOut(),dlgRect.getColorIn());
			if(dlgRect.getSave()){
			addCommand(new AddShape(model, rectangle));
			}
		} else if (frame.getBtnCircle().isSelected()) {
			DlgAddEditCircle dlgCircle = new DlgAddEditCircle();
			dlgCircle.addCircle(e.getX(), e.getY(), outColor,inColor );
			dlgCircle.setVisible(true);
				frame.getBtnColorOut().setBackground(dlgCircle.getColorOut());
				frame.getBtnColorIn().setBackground(dlgCircle.getColorIn());
			Circle circle = new Circle(new Point(dlgCircle.getX(), dlgCircle.getY()), dlgCircle.getRadius(),dlgCircle.getColorOut(), dlgCircle.getColorIn());
			if(dlgCircle.getSave()){
			addCommand(new AddShape(model, circle));
			}
		} else if (frame.getBtnHexagon().isSelected()) {
			DlgAddEditHexagon dlgHexagon = new DlgAddEditHexagon();
			dlgHexagon.addHexagon(e.getX(), e.getY(), outColor,inColor );
			dlgHexagon.setVisible(true);
				frame.getBtnColorOut().setBackground(dlgHexagon.getColorOut());
				frame.getBtnColorIn().setBackground(dlgHexagon.getColorIn());
			hexagon.Hexagon hexagon = new Hexagon(dlgHexagon.getX(), dlgHexagon.getY(), dlgHexagon.getRadius());
			HexagonAdapter hexadapter = new HexagonAdapter(hexagon);
			hexadapter.setOutlineColor(dlgHexagon.getColorOut());
			hexadapter.setInsideColor(dlgHexagon.getColorIn());
			if(dlgHexagon.getSave()){
			addCommand(new AddShape(model, hexadapter));
			}
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
