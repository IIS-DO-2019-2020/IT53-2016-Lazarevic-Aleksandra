package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import shapes.Shape;

public class DrawingSaving implements Saving{
	private ArrayList<Shape> allShapes;
	
	public DrawingSaving(ArrayList<Shape> allShapes) {
		this.allShapes=allShapes;
	}

	@Override
	public void save() {
		
		JFileChooser fileChooser = new JFileChooser("c:\\Users\\Aleksandra\\Desktop\\java");
		fileChooser.setFileFilter(new FileNameExtensionFilter("ser file (.ser)", "ser"));
		fileChooser.setDialogTitle("Save ser file");
	        int chosen =fileChooser.showSaveDialog(null);
	        if (chosen == JFileChooser.APPROVE_OPTION) 
	        {	
	        	File serFile=new File(fileChooser.getSelectedFile().getPath());
	        	if(!serFile.getAbsolutePath().toString().contains(".ser"))
	        	{
	        		serFile=new File(serFile.getAbsolutePath()+".ser");
	        	}
	        	if (serFile.exists())
	        	{
	        		JOptionPane.showMessageDialog(null, "File name already exists", "Achtung!",JOptionPane.INFORMATION_MESSAGE);
	        		save();
	        	}
	        	else {
	        	try 
	        	{
	        		FileOutputStream fos = new FileOutputStream(serFile);
	        	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	        	    oos.writeObject(allShapes);
	        	    oos.close();
	        	    fos.close(); //kazu podrazumeva se?
	                JOptionPane.showMessageDialog(null, "File successfully saved", "Succesful!",JOptionPane.INFORMATION_MESSAGE);
	            } catch (Exception ex) 
	        	{
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Error while saving the file.", "Error!",JOptionPane.ERROR_MESSAGE);
	            }
	        	}
	    	}
		
	}

}
