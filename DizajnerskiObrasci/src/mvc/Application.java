package mvc;

import java.awt.Color;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		Model model= new Model();
		Frame frame=new Frame();
		frame.getView().setModel(model);
		ControllerDrawing controller= new ControllerDrawing(model,frame);
		frame.setController(controller);
		ControllerFiles controllerFiles = new ControllerFiles(model,frame,controller);
		frame.setControllerFiles(controllerFiles);

		frame.setTitle("Paint by Aleksandra");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(300, 200, 878, 590);
		frame.setVisible(true);
		frame.setResizable(false); 
	}

}
