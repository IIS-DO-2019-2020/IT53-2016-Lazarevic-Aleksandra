package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import command.Command;
import command.position.BringToBack;
import command.position.BringToFront;
import command.position.ToBack;
import command.position.ToFront;
import command.shape.AddShape;
import command.shape.DeselectShapes;
import command.shape.EditCircle;
import command.shape.EditHexagon;
import command.shape.EditLine;
import command.shape.EditPoint;
import command.shape.EditRectangle;
import command.shape.EditSquare;
import command.shape.RemoveShape;
import command.shape.SelectShape;
import dialogs.DlgAddEditCircle;
import dialogs.DlgAddEditHexagon;
import dialogs.DlgAddEditRectangle;
import dialogs.DlgAddEditRectangle;
import dialogs.DlgAddEditSquare;
import dialogs.DlgEditLine;
import dialogs.DlgEditPoint;
import shapes.Circle;
import shapes.HexagonAdapter;
import hexagon.Hexagon;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;

public class ControllerDrawing  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Model model;
	private Frame frame;
	private ArrayList <Command> commands= new ArrayList<Command>();
	private  Point firstPoint;
	private int indexOfCommand;
	
	public ControllerDrawing(Model model, Frame frame) {
		this.model = model;
		this.frame = frame;
	}
	public void addShape(MouseEvent e, Color outColor, Color inColor)
	{
		if (frame.getBtnPoint().isSelected())
		{
			Point point = new shapes.Point(e.getX(), e.getY(), outColor);
			//prvo deselektuje zbog undo
			areShapesSelected();
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
				areShapesSelected();
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
			Square square = new Square(new Point(dlgSquare.getCorX(), dlgSquare.getCorY()), dlgSquare.getSide(),dlgSquare.getColorOut(), dlgSquare.getColorIn());
			if(dlgSquare.getSave()){
				areShapesSelected();
				addCommand(new AddShape(model, square));
			}
		
		} else if (frame.getBtnRectangle().isSelected()) {
			DlgAddEditRectangle dlgRect = new DlgAddEditRectangle();
			dlgRect.addRectangle(e.getX(), e.getY(), outColor, inColor);
			dlgRect.setVisible(true);
				frame.getBtnColorOut().setBackground(dlgRect.getColorOut());
				frame.getBtnColorIn().setBackground(dlgRect.getColorIn());
			Rectangle rectangle = new Rectangle(new Point(dlgRect.getCorX(), dlgRect.getCorY()), dlgRect.getWidthX(), dlgRect.getHeightY(), dlgRect.getColorOut(),dlgRect.getColorIn());
			if(dlgRect.getSave()){
				areShapesSelected();
				addCommand(new AddShape(model, rectangle));
			}
		} else if (frame.getBtnCircle().isSelected()) {
			DlgAddEditCircle dlgCircle = new DlgAddEditCircle();
			dlgCircle.addCircle(e.getX(), e.getY(), outColor,inColor );
			dlgCircle.setVisible(true);
				frame.getBtnColorOut().setBackground(dlgCircle.getColorOut());
				frame.getBtnColorIn().setBackground(dlgCircle.getColorIn());
			Circle circle = new Circle(new Point(dlgCircle.getCorX(), dlgCircle.getCorY()), dlgCircle.getRadius(),dlgCircle.getColorOut(), dlgCircle.getColorIn());
			if(dlgCircle.getSave()){
				areShapesSelected();
				addCommand(new AddShape(model, circle));
			}
		} else if (frame.getBtnHexagon().isSelected()) {
			DlgAddEditHexagon dlgHexagon = new DlgAddEditHexagon();
			dlgHexagon.addHexagon(e.getX(), e.getY(), outColor,inColor );
			dlgHexagon.setVisible(true);
				frame.getBtnColorOut().setBackground(dlgHexagon.getColorOut());
				frame.getBtnColorIn().setBackground(dlgHexagon.getColorIn());
			hexagon.Hexagon hexagon = new Hexagon(dlgHexagon.getCorX(), dlgHexagon.getCorY(), dlgHexagon.getRadius());
			HexagonAdapter hexadapter = new HexagonAdapter(hexagon);
			hexadapter.setOutlineColor(dlgHexagon.getColorOut());
			hexadapter.setInsideColor(dlgHexagon.getColorIn());
			if(dlgHexagon.getSave()){
				areShapesSelected();
				addCommand(new AddShape(model, hexadapter));
			}
		}
		frame.update(); //da bi switch position radio 
		
	
	}
	
	public int getSelectedShapes() {
		int selected=0;
		for (Shape s : model.getAllShapes()) {
			if(s.isSelected())
			{
				selected++;
			}
	}
		return selected;
	}
	
	private void areShapesSelected() {
		int selected=getSelectedShapes();
		if(selected>0) deselectAll();
}
	public void deselectAll() {
		
			addCommand(new DeselectShapes(model, frame));
	}
	
	public void addCommand(Command c) {
		
		if(frame.getBtnRedo().isEnabled()) commands.remove(indexOfCommand+1); 
		commands.add(c);													 
		c.execute();
		indexOfCommand=commands.lastIndexOf(c); //last index of occurance
		frame.addToLogList(c.toString());
	
		frame.getBtnUndo().setEnabled(true);
		frame.getBtnRedo().setEnabled(false);
		frame.getBtnSelect().setEnabled(true);
		frame.getBtnSave().setEnabled(true);
		
		/*System.out.println("addcommand");
		for(Command r : commands)
		{
						System.out.println(r.toString()+commands.lastIndexOf(r)+indexOfCommand);
		}
	*/
	}

	public void undo() {
		commands.get(indexOfCommand).unexecute();
		frame.addToLogList("undo:" + commands.get(indexOfCommand).toString());
		indexOfCommand--;
		frame.getBtnRedo().setEnabled(true);
		if (indexOfCommand==-1) frame.getBtnUndo().setEnabled(false);
		
		/*	System.out.println("undo");
		for(Command r : commands)
		{
						System.out.println(r.toString()+commands.lastIndexOf(r)+indexOfCommand);    //ako opet nes ne valja
		}*/
	}
	public void redo() {
		indexOfCommand++;
		commands.get(indexOfCommand).execute();
		frame.addToLogList("redo:" + commands.get(indexOfCommand).toString());
		frame.getBtnUndo().setEnabled(true);
		
		if (indexOfCommand+1>=commands.size()) frame.getBtnRedo().setEnabled(false);
		
		/*System.out.println("redo");
		for(Command r : commands)
		{
						System.out.println(r.toString()+commands.lastIndexOf(r)+indexOfCommand);
		}*/
	}
	public void select(MouseEvent e) {
		//reverse jer poslednji dodat treba das selektuje
		Collections.reverse(model.getAllShapes());
		for (Shape s : model.getAllShapes()) {
			s.setObserver(frame);
			if (s.contains(e.getX(), e.getY())) { 
				addCommand(new SelectShape(s));
				break;	
			}
		}
		Collections.reverse(model.getAllShapes());
	}
	public void edit() {
		for (Shape s: model.getAllShapes())
		{
			if(s.isSelected())
			{
				if (s instanceof Point) {
					DlgEditPoint dialog = new DlgEditPoint();
					dialog.EditPoint(((Point) s).getX(), ((Point) s).getY(), s.getOutlineColor());
					dialog.setVisible(true);
					if (dialog.getSave()) {
						addCommand(new EditPoint((Point) s, new Point(dialog.getCorX(), dialog.getCorY(), dialog.getColor())));
					}
				} else if (s instanceof Line) {
					DlgEditLine dialog = new DlgEditLine();
					dialog.editLine(((Line) s).getStart().getX(), ((Line) s).getStart().getY(), ((Line) s).getEnd().getX(), ((Line) s).getEnd().getY(), s.getOutlineColor());
					dialog.setVisible(true);
					if (dialog.getSave()) {
					addCommand(new EditLine((Line) s, new Line(new Point(dialog.getFirstX(), dialog.getFirstY()),
								new Point(dialog.getLastX(), dialog.getLastY()), dialog.getColor())));
					}
				} else if (s instanceof Rectangle) {
					DlgAddEditRectangle dialog = new DlgAddEditRectangle();
					dialog.editRectangle(((Rectangle) s).getUpperLeft().getX(), ((Rectangle) s).getUpperLeft().getY(), ((Rectangle) s).getHeight(), ((Rectangle) s).getSide(), s.getOutlineColor(), ((Rectangle) s).getInsideColor());
					dialog.setVisible(true);
					if (dialog.getSave()) {
					addCommand(new EditRectangle((Rectangle) s,new Rectangle(new Point(dialog.getCorX(), dialog.getCorY()), dialog.getHeightY(),
								dialog.getWidthX(), dialog.getColorOut(), dialog.getColorIn())));
					}
				} else if (s instanceof Square) {
					DlgAddEditSquare dialog = new DlgAddEditSquare();
					dialog.editSquare(((Square) s).getUpperLeft().getX(), ((Square) s).getUpperLeft().getY(), ((Square) s).getSide(), s.getOutlineColor(), ((Square) s).getInsideColor());
					dialog.setVisible(true);
					if (dialog.getSave()) {
						addCommand(new EditSquare((Square) s, new Square(new Point(dialog.getCorX(), dialog.getCorY()),
								dialog.getSide(), dialog.getColorOut(), dialog.getColorIn())));
					}
				} else if (s instanceof Circle) {
					DlgAddEditCircle dialog = new DlgAddEditCircle();
					dialog.editCircle(((Circle) s).getCenter().getX(), ((Circle) s).getCenter().getY(), ((Circle) s).getRadius(), s.getOutlineColor(), ((Circle) s).getInsideColor());
					dialog.setVisible(true);
					if (dialog.getSave()) {
						addCommand(new EditCircle((Circle) s, new Circle(new Point(dialog.getCorX(), dialog.getCorY()),
										dialog.getRadius(), dialog.getColorOut(), dialog.getColorIn())));
					}
				} else if (s instanceof HexagonAdapter) {
					DlgAddEditHexagon dialog = new DlgAddEditHexagon();
					dialog.editHexagon(((HexagonAdapter) s).getX(), ((HexagonAdapter) s).getY(), ((HexagonAdapter) s).getR(), s.getOutlineColor(), ((HexagonAdapter) s).getInsideColor());
					dialog.setVisible(true);
					if (dialog.getSave()) {
						Hexagon hexagon = new Hexagon(dialog.getCorX(), dialog.getCorY(), dialog.getRadius());
						HexagonAdapter hexadapter = new HexagonAdapter(hexagon);
						hexadapter.setOutlineColor(dialog.getColorOut());
						hexadapter.setInsideColor(dialog.getColorIn());
						addCommand(new EditHexagon((HexagonAdapter) s, hexadapter));
					}
				}
			}
		}
	}
	public void delete() {	
		ArrayList<Shape> shapes=new ArrayList<>();
		
		for (Shape s : model.getAllShapes()) {
			s.setObserver(frame);
			if (s.isSelected()) { 
				//addCommand(new RemoveShape(model,s));
				shapes.add(s);
			}
		}
		for(Shape s : shapes)
		{
			if( model.getAllShapes().contains(s));
			addCommand(new RemoveShape(model,s));
		}
		
		/////
		if(model.getAllShapes().size()==0)
		{
			frame.backToBeginingState();
		}
	}
	
	public void toFront() {
		for (Shape s : model.getAllShapes()) {
			if (s.isSelected()) {
				addCommand(new ToFront(s, model));
				break;
			}
		}
		
	}
	public void toBack() {
		for (Shape s : model.getAllShapes()) {
			if (s.isSelected()) {
				addCommand(new ToBack(s, model));
				break;
			}
		}
	}
	public void bringToBack() {
		for (Shape s : model.getAllShapes()) {
			if (s.isSelected()) {
				addCommand(new BringToBack(s, model));
				break;
			}
		}
	}
	public void bringToFront() {
		for (Shape s : model.getAllShapes()) {
			if (s.isSelected()) {
				addCommand(new BringToFront(s, model));
				break;
			}
		}
	}
	
	public void Position() {
		int shapesDrawn=model.getAllShapes().size();
		int shapePosition = 0;	
		
		for (Shape s : model.getAllShapes())
		{
			if(s.isSelected())
			{
				shapePosition=model.getAllShapes().indexOf(s)+1;
				if(shapesDrawn==1)  
				{
					frame.resetPositionButtons();
				}
				else if (shapesDrawn>=2)
				{
					if ( shapePosition== 1)  //poslednji vidljivi
					{ 
						frame.getBtnToBack().setEnabled(false);
						frame.getBtnToFront().setEnabled(true);
						frame.getBtnBringBack().setEnabled(false);  
						frame.getBtnBringFront().setEnabled(true);
					} 
					else if (shapePosition == shapesDrawn)	//prvi vidljivi
					{
						frame.getBtnToBack().setEnabled(true);
						frame.getBtnToFront().setEnabled(false);
						frame.getBtnBringBack().setEnabled(true);
						frame.getBtnBringFront().setEnabled(false);
					}
					else if (shapePosition < shapesDrawn)  //izmedju vidljivi
					{
						frame.getBtnToBack().setEnabled(true);
						frame.getBtnToFront().setEnabled(true);
						frame.getBtnBringBack().setEnabled(true);
						frame.getBtnBringFront().setEnabled(true);
					}
				}
			}
		
		}
	}
}
