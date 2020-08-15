package mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import mvc.Model;
import shapes.Shape;

public class View extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private Model model;

	public void setModel(Model model) {
		this.model = model;
	}

	public Model getModel() {
		return this.model;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (model != null) {
			Iterator<Shape> it = model.getAllShapes().iterator();
			while (it.hasNext()) {
				it.next().draw(g);
			}
		}
		repaint();
	}

}