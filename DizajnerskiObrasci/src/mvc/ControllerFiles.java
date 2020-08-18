package mvc;

import strategy.DrawingSaving;
import strategy.LogSaving;
import strategy.Saving;
import strategy.SavingManager;

public class ControllerFiles {
	
	private Model model;
	private Frame frame;
	
	//controller for saving and opening files
	
	public ControllerFiles(Model model, Frame frame) {
		this.model = model;
		this.frame = frame;
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

	public void open() {
		// TODO Auto-generated method stub
		
		//ako je log moze ReadFile rf=new ReadFile(putanja);
		// String[] commandsString=rf.OpenFile();
		
		///catch (IOExeption)
		
	}
}
