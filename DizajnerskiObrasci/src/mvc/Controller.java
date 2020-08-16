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
			//after drawing new element,deselect selected shapes if exist
			areShapesSelected();
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
				//after drawing new element,deselect selectedshapes if exist
				areShapesSelected();
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
				//after drawing new element,deselect selectedshapes if exist
				areShapesSelected();
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
				//after drawing new element,deselect selectedshapes if exist
				areShapesSelected();
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
				//after drawing new element,deselect selectedshapes if exist
				areShapesSelected();
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
				//after drawing new element,deselect selectedshapes if exist
				areShapesSelected();
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
		commands.add(c);
		c.execute();
		frame.addToLogList(c.toString());
		
		frame.getBtnUndo().setEnabled(true);
		frame.getBtnSelect().setEnabled(true);
		frame.getBtnSave().setEnabled(true);
	}

	public void undo() {
		// TODO Auto-generated method stub
		
	}
	public void redo() {
		// TODO Auto-generated method stub
		
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
					addCommand(new EditRectangle((Rectangle) s,new Rectangle(new Point(dialog.getX(), dialog.getY()), dialog.getHeight(),
								dialog.getWidth(), dialog.getColorOut(), dialog.getColorIn())));
					}
				} else if (s instanceof Square) {
					DlgAddEditSquare dialog = new DlgAddEditSquare();
					dialog.editSquare(((Square) s).getUpperLeft().getX(), ((Square) s).getUpperLeft().getY(), ((Square) s).getSide(), s.getOutlineColor(), ((Square) s).getInsideColor());
					dialog.setVisible(true);
					if (dialog.getSave()) {
						addCommand(new EditSquare((Square) s, new Square(new Point(dialog.getX(), dialog.getY()),
								dialog.getSide(), dialog.getColorOut(), dialog.getColorIn())));
					}
				} else if (s instanceof Circle) {
					DlgAddEditCircle dialog = new DlgAddEditCircle();
					dialog.editCircle(((Circle) s).getCenter().getX(), ((Circle) s).getCenter().getY(), ((Circle) s).getRadius(), s.getOutlineColor(), ((Circle) s).getInsideColor());
					dialog.setVisible(true);
					if (dialog.getSave()) {
						addCommand(new EditCircle((Circle) s, new Circle(new Point(dialog.getX(), dialog.getY()),
										dialog.getRadius(), dialog.getColorOut(), dialog.getColorIn())));
					}
				} else if (s instanceof HexagonAdapter) {
					DlgAddEditHexagon dialog = new DlgAddEditHexagon();
					dialog.editHexagon(((HexagonAdapter) s).getX(), ((HexagonAdapter) s).getY(), ((HexagonAdapter) s).getR(), s.getOutlineColor(), ((HexagonAdapter) s).getInsideColor());
					dialog.setVisible(true);
					if (dialog.getSave()) {
						Hexagon hexagon = new Hexagon(dialog.getX(), dialog.getY(), dialog.getRadius());
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
		//deselektuj ako ne obrise u frame u - ne treba zbog dugmeta select koje to radi
		
		//ConcurrentModificationException :)))) cant remove with for 
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
		
		//ako je view prazan buttoni se ne mog kliknut
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
		int shapePosition = 0;								////ovde moze biti problem
		
		for (Shape s : model.getAllShapes())
		{
			if(s.isSelected())
			{
				shapePosition=model.getAllShapes().indexOf(s)+1;
				if(shapesDrawn==1)  //manje od 2 nacrtana, ne moze se nis
				{
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
					frame.getBtnBringBack().setEnabled(false);
					frame.getBtnBringFront().setEnabled(false);
				}
				else if (shapesDrawn>=2)		//vise od 2 nacrtana moze u zavisnosti od pozicije
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
					else if (shapePosition < shapesDrawn)
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
