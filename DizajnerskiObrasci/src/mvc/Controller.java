package mvc;

import java.io.Serializable;

public class Controller  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Model model;
	private Frame frame;
	public Controller(Model model, Frame frame) {
		this.model = model;
		this.frame = frame;
	}

}
