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
			Saving savingLog = new LogSaving();
			SavingManager manager = new SavingManager(savingLog);
		}
		else if(selectedValue==1)
		{
			Saving savingDrawing = new DrawingSaving();
			SavingManager manager = new SavingManager(savingDrawing);
		}
		
	}

	public void open() {
		// TODO Auto-generated method stub
		
	}
}
