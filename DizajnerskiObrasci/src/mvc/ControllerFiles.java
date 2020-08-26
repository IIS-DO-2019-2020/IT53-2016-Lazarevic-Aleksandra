package mvc;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import hexagon.Hexagon;
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
import shapes.HexagonAdapter;
import shapes.Circle;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;
import strategy.DrawingSaving;
import strategy.LogSaving;
import strategy.Saving;
import strategy.SavingManager;

public class ControllerFiles implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private Frame frame;
	private ControllerDrawing controllerDrawing;
	private BufferedReader br;
	private String line;
	
	//controller for saving and opening files
	
	public ControllerFiles(Model model, Frame frame,ControllerDrawing controllerDrawing) {
		this.model = model;
		this.frame = frame;
		this.controllerDrawing=controllerDrawing;
	}

	public void save(int selectedValue) {
		if(selectedValue==0)
		{
			Saving savingLog = new LogSaving(frame.getDlm());
			SavingManager manager = new SavingManager(savingLog);
			manager.save();
		}
		else if(selectedValue==1)
		{
			Saving savingDrawing = new DrawingSaving(model.getAllShapes());
			SavingManager manager = new SavingManager(savingDrawing);
			manager.save();
		}
		
	}

	public void open(int selectedValue) {
		if(selectedValue==0)
		{
			JFileChooser jfc=new JFileChooser("c:\\Users\\Aleksandra\\Desktop\\java");
			jfc.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
			jfc.setDialogTitle("Open log file");
		        int chosen =jfc.showOpenDialog(null);
		        if (chosen == JFileChooser.APPROVE_OPTION) {
		        	File logFile=new File(jfc.getSelectedFile().getAbsolutePath());
		        	try 
		        	{
		        		frame.backToBeginingState();
		        		frame.getBtnUndo().setEnabled(false);
						controllerDrawing.getCommandStack().clear();
						controllerDrawing.setUndoRedoPointer(-1);
						model.getAllShapes().clear();
						frame.getDlm().clear();
		        		frame.getBtnNext().setVisible(true);
		        		frame.getBtnNext().setEnabled(true);
		        		br = new BufferedReader(new FileReader(logFile));
		        	} catch (Exception ex) 
		        	{
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error while opening the file.", "Error!",JOptionPane.ERROR_MESSAGE);
		            }
		
		}
		}
		else if(selectedValue==1)
		{
			JFileChooser jfc=new JFileChooser("c:\\Users\\Aleksandra\\Desktop\\java");
			jfc.setFileFilter(new FileNameExtensionFilter("ser file (.ser)", "ser"));
			jfc.setDialogTitle("Open ser file");
		        int chosen =jfc.showOpenDialog(null);
		        if (chosen == JFileChooser.APPROVE_OPTION) {
		        	File serFile=new File(jfc.getSelectedFile().getAbsolutePath());
		        	try 
		        	{
		        		FileInputStream fis = new FileInputStream(serFile);
						ObjectInputStream ois = new ObjectInputStream(fis);
						frame.backToBeginingState();
						frame.getBtnUndo().setEnabled(false);
						controllerDrawing.getCommandStack().clear();
						controllerDrawing.setUndoRedoPointer(-1);
						model.getAllShapes().clear();
						frame.getDlm().clear();
						ArrayList<Shape> list = (ArrayList<Shape>) ois.readObject();
						
						for (Shape s : list) {
							controllerDrawing.addCommand(new AddShape(model, s));
							//model.addShape(s);
							s.setObserver(frame);
						}
						ois.close();
						fis.close();
		                JOptionPane.showMessageDialog(null, "Like picasso painted it himself!", "Succesful!",JOptionPane.INFORMATION_MESSAGE);
		            } catch (Exception ex) 
		        	{
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error while opening the file.", "Error!",JOptionPane.ERROR_MESSAGE);
		            }
		
		}
	}
	}

	public void Next() {
	
		try {
			if((line = br.readLine()) != null)  
			{ 
				br.mark(1);
				nextLine();
				String[] command = line.split("\\W");
				if(command[0].contentEquals("add")||command[0].contentEquals("edit")||command[0].contentEquals("remove")||command[0].contentEquals("select")||command[0].contentEquals("tofront")||command[0].contentEquals("toback")||command[0].contentEquals("bringtofront")||command[0].contentEquals("bringtoback"))
				{
					if(command[1].contentEquals("point"))
					{
						Point p=new Point(Integer.parseInt(command[2]),Integer.parseInt(command[3]),new Color(Integer.parseInt(command[5]),Integer.parseInt(command[6]),Integer.parseInt(command[7])));
					
						if(command[0].contentEquals("add"))
						{
							controllerDrawing.addCommand(new AddShape(model, p));
						}
						else if(command[0].contentEquals("edit"))
						{
							Point newP=new Point(Integer.parseInt(command[10]),Integer.parseInt(command[11]),new Color(Integer.parseInt(command[13]),Integer.parseInt(command[14]),Integer.parseInt(command[15])));
							for (Shape s : model.getAllShapes()) {
								if (s.equals(p)) {
									controllerDrawing.addCommand(new EditPoint((Point) s, newP));
									break;
								}
							}
						}
						else if(command[0].contentEquals("remove"))
						{
							controllerDrawing.addCommand(new RemoveShape(model, p));
						}
						else if(command[0].contentEquals("select"))
						{
							for (Shape s : model.getAllShapes()) {
								if (s.equals(p)) {
									s.setObserver(frame);
									controllerDrawing.addCommand(new SelectShape(s));
								}
							}
						}
						else if(command[0].contentEquals("tofront"))
						{
							controllerDrawing.toFront();
						}
						else if(command[0].contentEquals("toback"))
						{
							controllerDrawing.toBack();
						}
						else if(command[0].contentEquals("bringtofront"))
						{
							controllerDrawing.bringToFront();
						}
						else if(command[0].contentEquals("bringtoback"))
						{
							controllerDrawing.bringToBack();
						}
					}
					if(command[1].contentEquals("line"))
					{
						Line l=new Line(new Point(Integer.parseInt(command[3]),Integer.parseInt(command[4])),new Point(Integer.parseInt(command[6]),Integer.parseInt(command[7])),new Color(Integer.parseInt(command[9]),Integer.parseInt(command[10]),Integer.parseInt(command[11])));
						
						if(command[0].contentEquals("add"))
						{
							controllerDrawing.addCommand(new AddShape(model, l));
						}
						else if(command[0].contentEquals("edit"))
						{
							Line newL=new Line(new Point(Integer.parseInt(command[15]),Integer.parseInt(command[16])),new Point(Integer.parseInt(command[18]),Integer.parseInt(command[19])),new Color(Integer.parseInt(command[21]),Integer.parseInt(command[22]),Integer.parseInt(command[23])));
							for (Shape s : model.getAllShapes()) {
								if (s.equals(l)) {
									controllerDrawing.addCommand(new EditLine((Line) s, newL));
									break;
								}
							}
						}
						else if(command[0].contentEquals("remove"))
						{
							controllerDrawing.addCommand(new RemoveShape(model, l));
						}
						else if(command[0].contentEquals("select"))
						{
							for (Shape s : model.getAllShapes()) {
								if (s.equals(l)) {
									s.setObserver(frame);
									controllerDrawing.addCommand(new SelectShape(s));
								}
							}
						}
						else if(command[0].contentEquals("tofront"))
						{
							controllerDrawing.toFront();
						}
						else if(command[0].contentEquals("toback"))
						{
							controllerDrawing.toBack();
						}
						else if(command[0].contentEquals("bringtofront"))
						{
							controllerDrawing.bringToFront();
						}
						else if(command[0].contentEquals("bringtoback"))
						{
							controllerDrawing.bringToBack();
						}
					}
					if(command[1].contentEquals("circle")) 
					{
						Circle c=new Circle(new Point(Integer.parseInt(command[3]),Integer.parseInt(command[4])), Integer.parseInt(command[6]), new Color(Integer.parseInt(command[8]),Integer.parseInt(command[9]),Integer.parseInt(command[10])), new Color(Integer.parseInt(command[12]),Integer.parseInt(command[13]),Integer.parseInt(command[14])));
						
						if(command[0].contentEquals("add"))
						{
							controllerDrawing.addCommand(new AddShape(model, c));
						}
						else if(command[0].contentEquals("edit"))
						{
							Circle newC=new Circle(new Point(Integer.parseInt(command[18]),Integer.parseInt(command[19])), Integer.parseInt(command[21]), new Color(Integer.parseInt(command[23]),Integer.parseInt(command[24]),Integer.parseInt(command[25])), new Color(Integer.parseInt(command[27]),Integer.parseInt(command[28]),Integer.parseInt(command[29])));
							for (Shape s : model.getAllShapes()) {
								if (s.equals(c)) {
									controllerDrawing.addCommand(new EditCircle((Circle) s, newC));
									break;
								}
							}
						}
						else if(command[0].contentEquals("remove"))
						{
							controllerDrawing.addCommand(new RemoveShape(model, c));
						}
						else if(command[0].contentEquals("select"))
						{
							for (Shape s : model.getAllShapes()) {
								if (s.equals(c)) {
									s.setObserver(frame);
									controllerDrawing.addCommand(new SelectShape(s));
								}
							}
						}
						else if(command[0].contentEquals("tofront"))
						{
							controllerDrawing.toFront();
						}
						else if(command[0].contentEquals("toback"))
						{
							controllerDrawing.toBack();
						}
						else if(command[0].contentEquals("bringtofront"))
						{
							controllerDrawing.bringToFront();
						}
						else if(command[0].contentEquals("bringtoback"))
						{
							controllerDrawing.bringToBack();
						}
					}
					if(command[1].contentEquals("square")) 
					{
						Square sq=new Square(new Point(Integer.parseInt(command[3]),Integer.parseInt(command[4])), Integer.parseInt(command[6]), new Color(Integer.parseInt(command[8]),Integer.parseInt(command[9]),Integer.parseInt(command[10])), new Color(Integer.parseInt(command[12]),Integer.parseInt(command[13]),Integer.parseInt(command[14])));
						
						if(command[0].contentEquals("add"))
						{
							controllerDrawing.addCommand(new AddShape(model, sq));
						}
						else if(command[0].contentEquals("edit"))
						{
							Square newS=new Square(new Point(Integer.parseInt(command[18]),Integer.parseInt(command[19])), Integer.parseInt(command[21]), new Color(Integer.parseInt(command[23]),Integer.parseInt(command[24]),Integer.parseInt(command[25])), new Color(Integer.parseInt(command[27]),Integer.parseInt(command[28]),Integer.parseInt(command[29])));
							for (Shape s : model.getAllShapes()) {
								if (s.equals(sq)) {
									controllerDrawing.addCommand(new EditSquare((Square) s, newS));
									break;
								}
							}
						}
						else if(command[0].contentEquals("remove"))
						{
							controllerDrawing.addCommand(new RemoveShape(model, sq));
						}
						else if(command[0].contentEquals("select"))
						{
							for (Shape s : model.getAllShapes()) {
								if (s.equals(sq)) {
									s.setObserver(frame);
									controllerDrawing.addCommand(new SelectShape(s));
								}
							}
						}
						else if(command[0].contentEquals("tofront"))
						{
							controllerDrawing.toFront();
						}
						else if(command[0].contentEquals("toback"))
						{
							controllerDrawing.toBack();
						}
						else if(command[0].contentEquals("bringtofront"))
						{
							controllerDrawing.bringToFront();
						}
						else if(command[0].contentEquals("bringtoback"))
						{
							controllerDrawing.bringToBack();
						}
					}
					if(command[1].contentEquals("rectangle")) 
					{
						Rectangle r=new Rectangle(new Point(Integer.parseInt(command[3]),Integer.parseInt(command[4])), Integer.parseInt(command[6]),Integer.parseInt(command[8]), new Color(Integer.parseInt(command[10]),Integer.parseInt(command[11]),Integer.parseInt(command[12])), new Color(Integer.parseInt(command[14]),Integer.parseInt(command[15]),Integer.parseInt(command[16])));
					
						if(command[0].contentEquals("add"))
						{
							controllerDrawing.addCommand(new AddShape(model, r));
						}
						else if(command[0].contentEquals("edit"))
						{
							Rectangle newR=new Rectangle(new Point(Integer.parseInt(command[20]),Integer.parseInt(command[21])), Integer.parseInt(command[23]),Integer.parseInt(command[25]), new Color(Integer.parseInt(command[27]),Integer.parseInt(command[28]),Integer.parseInt(command[29])), new Color(Integer.parseInt(command[31]),Integer.parseInt(command[32]),Integer.parseInt(command[33])));
							for (Shape s : model.getAllShapes()) {
								if (s.equals(r)) {
									controllerDrawing.addCommand(new EditRectangle((Rectangle) s, newR));
									break;
								}
							}
						}
						else if(command[0].contentEquals("remove"))
						{
							controllerDrawing.addCommand(new RemoveShape(model, r));
						}
						else if(command[0].contentEquals("select"))
						{
							for (Shape s : model.getAllShapes()) {
								if (s.equals(r)) {
									s.setObserver(frame);
									controllerDrawing.addCommand(new SelectShape(s));
								}
							}
						}
						else if(command[0].contentEquals("tofront"))
						{
							controllerDrawing.toFront();
						}
						else if(command[0].contentEquals("toback"))
						{
							controllerDrawing.toBack();
						}
						else if(command[0].contentEquals("bringtofront"))
						{
							controllerDrawing.bringToFront();
						}
						else if(command[0].contentEquals("bringtoback"))
						{
							controllerDrawing.bringToBack();
						}
					}
					if(command[1].contentEquals("hexagon")) 
					{
						Hexagon h = new Hexagon(Integer.parseInt(command[3]),Integer.parseInt(command[4]), Integer.parseInt(command[6]));
						HexagonAdapter ha = new HexagonAdapter(h);
						ha.setOutlineColor(new Color(Integer.parseInt(command[8]),Integer.parseInt(command[9]),Integer.parseInt(command[10])));
						ha.setInsideColor(new Color(Integer.parseInt(command[12]),Integer.parseInt(command[13]),Integer.parseInt(command[14])));
						
						if(command[0].contentEquals("add"))
						{
							controllerDrawing.addCommand(new AddShape(model, ha));
						}
						else if(command[0].contentEquals("edit"))
						{
							Hexagon newH = new Hexagon(Integer.parseInt(command[18]),Integer.parseInt(command[19]), Integer.parseInt(command[21]));
							HexagonAdapter newHa = new HexagonAdapter(newH);
							newHa.setOutlineColor(new Color(Integer.parseInt(command[23]),Integer.parseInt(command[24]),Integer.parseInt(command[25])));
							newHa.setInsideColor(new Color(Integer.parseInt(command[27]),Integer.parseInt(command[28]),Integer.parseInt(command[29])));
							
							for (Shape s : model.getAllShapes()) {
								if (s.equals(ha)) {
									controllerDrawing.addCommand(new EditHexagon((HexagonAdapter) s, newHa));
									break;
								}
							}
						}
						else if(command[0].contentEquals("remove"))
						{
							controllerDrawing.addCommand(new RemoveShape(model, ha));
						}
						else if(command[0].contentEquals("select"))
						{
							for (Shape s : model.getAllShapes()) {
								if (s.equals(ha)) {
									s.setObserver(frame);
									controllerDrawing.addCommand(new SelectShape(s));
								}
							}
						}
						else if(command[0].contentEquals("tofront"))
						{
							controllerDrawing.toFront();
						}
						else if(command[0].contentEquals("toback"))
						{
							controllerDrawing.toBack();
						}
						else if(command[0].contentEquals("bringtofront"))
						{
							controllerDrawing.bringToFront();
						}
						else if(command[0].contentEquals("bringtoback"))
						{
							controllerDrawing.bringToBack();
						}
					}
				}
				else if (command[0].contentEquals("undo"))
				{
					controllerDrawing.undo();
				}
				else if (command[0].contentEquals("redo"))
				{
					controllerDrawing.redo();
				}
				else if (command[0].contentEquals("deselectAll"))
				{
					controllerDrawing.addCommand(new DeselectShapes(model, frame));
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

private void nextLine() {
		
		try {
			if(br.readLine()==null)
			{
				frame.getBtnNext().setEnabled(false);
				br.close();
				
			}
			else
			{
				br.reset();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
