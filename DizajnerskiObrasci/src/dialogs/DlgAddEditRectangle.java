package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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


public class DlgAddEditRectangle extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_X;
	private JTextField txt_Y;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private int x,y,width,height;
	private Color colorOut=Color.BLACK,colorIn=Color.WHITE;
	private boolean save=false;
	JButton btnColorOut;
	JButton btnColorIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgAddEditRectangle dialog = new DlgAddEditRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgAddEditRectangle() {
		setTitle("Add Rectangle");
		setModal(true);
		setResizable(false);
		setBounds(200, 200, 300, 350);
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
		
		JLabel lblHeight = new JLabel("Height:");
		
		txtHeight = new JTextField();
		txtHeight.setText("");
		txtHeight.setColumns(10);
		
		JLabel lblWidth = new JLabel("Width:");
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		
		JLabel lblColorOut = new JLabel("Outer Color:");
		
		 btnColorOut = new JButton("");
		 btnColorOut.setBackground(new Color(204, 153, 153));
		 btnColorOut.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		Color temp= JColorChooser.showDialog(null, "Choose color",colorOut );
				if(temp!=null){colorOut=temp;btnColorOut.setBackground(colorOut);}
		 	}
		 });
		
		JLabel lblColorIn = new JLabel("Inner Color:");
		
		 btnColorIn = new JButton("");
		 btnColorIn.setBackground(new Color(204, 153, 153));
		 btnColorIn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		Color temp= JColorChooser.showDialog(null, "choose color", colorIn);
	 			if(temp!=null){colorIn=temp;btnColorIn.setBackground(colorIn);}
		 	}
		 });
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(204, 153, 153));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					x=Integer.parseInt(txt_X.getText());
					y=Integer.parseInt(txt_Y.getText());
					height=Integer.parseInt(txtHeight.getText());
					width=Integer.parseInt(txtWidth.getText());
					if(x<0||y<0||height<4||width<4)JOptionPane.showMessageDialog(null,"Values must be greater than  (0,0,4,4)", "Error!",JOptionPane.ERROR_MESSAGE);
					else {
						save=true;
						dispose();
					}
					}
				 catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,"Values not correct!", "Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnClose = new JButton("Close");
		btnClose.setBackground(new Color(204, 153, 153));
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
					.addGap(43)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblColorIn)
						.addComponent(lblColorOut)
						.addComponent(lblWidth)
						.addComponent(lblHeight)
						.addComponent(lblCoordinateY)
						.addComponent(lblCoordinateX))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnColorIn, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
						.addComponent(btnColorOut, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(txt_X)
							.addComponent(txt_Y)
							.addComponent(txtHeight)
							.addComponent(txtWidth)))
					.addGap(25))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(101, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClose)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txt_X, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCoordinateX))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateY)
						.addComponent(txt_Y, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHeight)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWidth)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColorOut)
						.addComponent(btnColorOut, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColorIn)
						.addComponent(btnColorIn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnClose))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	//color and coo already selected
		public void addRectangle(int x,int y,Color out,Color in)
		{
			setX(x);
			setY(y);
			setColorOut(out);
			setColorIn(in);
			txt_X.setText(Integer.toString(x));
			txt_Y.setText(Integer.toString(y));
			txt_X.setEditable(false);
			txt_Y.setEditable(false);
			btnColorOut.setBackground(out);
			btnColorIn.setBackground(in);

		}

		public void editRectangle(int x,int y,int height,int width,Color out,Color in)
		{
			setTitle("Edit Rectangle");
			setX(x);
			setY(y);
			setHeight(height);
			setWidth(width);
			setColorOut(out);
			setColorIn(in);
			txt_X.setText(Integer.toString(x));
			txt_Y.setText(Integer.toString(y));
			txtHeight.setText(Integer.toString(height));
			txtWidth.setText(Integer.toString(width));
			btnColorOut.setBackground(out);
			btnColorIn.setBackground(in);
		}
		
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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