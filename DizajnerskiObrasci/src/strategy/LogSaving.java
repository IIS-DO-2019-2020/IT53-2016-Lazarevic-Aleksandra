package strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LogSaving implements Saving{
		
	private DefaultListModel<String> dlm;
	private int i=1;

	public LogSaving(DefaultListModel<String> dlm) {
		this.dlm=dlm;
	}

	@Override
	public void save() {
		JFileChooser fileChooser = new JFileChooser("c:\\Users\\Aleksandra\\Desktop\\java");
		fileChooser.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
		fileChooser.setDialogTitle("Save log file");
	        int chosen =fileChooser.showSaveDialog(null);
	        if (chosen == JFileChooser.APPROVE_OPTION) 
	        {	
	        	File logFile=new File(fileChooser.getSelectedFile().getPath());
	        	if(!logFile.getAbsolutePath().toString().contains(".log"))
	        	{
	        		logFile=new File(logFile.getAbsolutePath()+".log");
	        	}
	        	if (logFile.exists())
	        	{
	        		JOptionPane.showMessageDialog(null, "File name already exists", "Achtung!",JOptionPane.INFORMATION_MESSAGE);
	        		save();
	        	}
	        	else {
	        	try 
	        	{
	    			PrintWriter pw = new PrintWriter(logFile);
	    			for (int i=0;i<dlm.getSize();i++)
	    			{
	    				pw.println(dlm.get(i));
	    			}
	    			pw.close();
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


