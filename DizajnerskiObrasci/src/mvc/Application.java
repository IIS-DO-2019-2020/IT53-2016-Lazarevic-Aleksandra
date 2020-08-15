package mvc;

import java.awt.Color;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
Model model= new Model();
Frame frame=new Frame();
frame.getView().setModel(model);
Controller controller= new Controller(model,frame);
frame.setController(controller);

frame.setTitle("Paint by Aleksandra");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setBounds(200, 200, 870, 590);
frame.setVisible(true);
frame.setResizable(false);
	}

}
