package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class DlgAddEditHexagon extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_X;
	private JTextField txt_Y;
	private JTextField txtRadius;
	private int corX,corY,radius;
	private Color colorOut=Color.BLACK,colorIn=Color.WHITE;
	private boolean save;
	JButton btnColorIn;
	JButton btnColorOut;

	public static void main(String[] args) {
		try {
			DlgAddEditHexagon dialog = new DlgAddEditHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgAddEditHexagon() {
		setTitle("Add Hexagon");
		setModal(true);
		setResizable(false);
		setBounds(200, 200, 300, 325);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		
		JLabel lblCoordinateX = new JLabel("Coordinate x:");
		txt_X = new JTextField();
		txt_X.setColumns(10);
		
		JLabel lblCoordinateY = new JLabel("Coordinate y:");
		
		txt_Y = new JTextField();
		txt_Y.setColumns(10);
		
		JLabel lblRadius = new JLabel("Radius:");
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		
		JLabel lblColorOut = new JLabel("Outer color:");
		
		btnColorIn = new JButton("");
		btnColorIn.setBackground(new Color(204, 153, 153));
		btnColorIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp= JColorChooser.showDialog(null, "choose color",colorIn );
				if(temp!=null){colorIn=temp;btnColorIn.setBackground(colorIn);}
			}
		});
		
		JLabel lblColorIn = new JLabel("Inner color:");
		
		 btnColorOut = new JButton("");
		 btnColorOut.setBackground(new Color(204, 153, 153));
		 btnColorOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp= JColorChooser.showDialog(null, "choose color", colorOut);
	 			if(temp!=null){colorOut=temp;btnColorOut.setBackground(colorOut);}
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(255, 204, 153));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					corX=Integer.parseInt(txt_X.getText());
					corY=Integer.parseInt(txt_Y.getText());
					radius=Integer.parseInt(txtRadius.getText());
					if(corX<0||corY<0||radius<4)JOptionPane.showMessageDialog(null,"Values must be greater than (0,0,4)", "Error!",JOptionPane.ERROR_MESSAGE);
					else if(corX+radius>603||corY+radius>437)JOptionPane.showMessageDialog(null,"Borders of paint window are 603 for x, 437 for y!", "Error!",JOptionPane.ERROR_MESSAGE);
					else if(corX-radius<0||corY-radius<0)JOptionPane.showMessageDialog(null,"Shape out of borders!", "Error!",JOptionPane.ERROR_MESSAGE);
					else {
						setSave(true);
						dispose();
					}
					}
				 catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,"Values not correct!", "Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnClose = new JButton("Close");
		btnClose.setBackground(new Color(255, 204, 153));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save=false;
				dispose();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblCoordinateX)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblRadius)
										.addComponent(lblCoordinateY)
										.addComponent(lblColorIn)))
								.addComponent(lblColorOut))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColorOut, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
								.addComponent(btnColorIn, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
								.addComponent(txt_Y, 124, 124, Short.MAX_VALUE)
								.addComponent(txt_X, 124, 124, Short.MAX_VALUE)
								.addComponent(txtRadius, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap(111, Short.MAX_VALUE)
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClose)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateX)
						.addComponent(txt_X, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateY)
						.addComponent(txt_Y, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblColorIn)
						.addComponent(btnColorIn, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblColorOut)
						.addComponent(btnColorOut, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnClose))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	//color and coo already selected
	public void addHexagon(int x,int y,Color out,Color in)
	{
		
		setCorX(x);
		setCorY(y);
		setColorOut(out);
		setColorIn(in);
		txt_X.setText(Integer.toString(x));
		txt_Y.setText(Integer.toString(y));
		txt_X.setEditable(false);
		txt_Y.setEditable(false);
		btnColorIn.setBackground(in);
		btnColorOut.setBackground(out);
		
	}
	public void editHexagon(int x,int y,int r,Color out,Color in)
	{
		setTitle("Edit Hexagon");
		setCorX(x);
		setCorY(y);
		setRadius(r);
		setColorOut(out);
		setColorIn(in);
		txt_X.setText(Integer.toString(x));
		txt_Y.setText(Integer.toString(y));
		txtRadius.setText(Integer.toString(r));
		btnColorIn.setBackground(in);
		btnColorOut.setBackground(out);
	}
	
	public int getCorX() {
		return corX;
	}

	public void setCorX(int x) {
		this.corX = x;
	}

	public int getCorY() {
		return corY;
	}

	public void setCorY(int y) {
		this.corY = y;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Color getColorOut() {
		return colorOut;
	}

	public void setColorOut(Color colorOut) {
		this.colorOut = colorOut;
	}

	public Color getColorIn() {
		return colorIn;
	}

	public void setColorIn(Color colorIn) {
		this.colorIn = colorIn;
	}

	public boolean getSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

}
