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

	private final JPanel contentPanel = new JPanel();
	private JTextField txt_corX;
	private JTextField txt_corY;
	private JTextField txtWidth;
	private int corX,cory,heightY,widthX;
	private JTextField txtHeight;
	private Color colorOut=Color.BLACK,colorIn=Color.WHITE;
	private boolean save;
	JButton btnColorOut;
	JButton btnColorIn;
	


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
		getContentPane().setBackground(new Color(255, 255, 204));
		setTitle("Add Rectangle");
		setModal(true);
		setResizable(false);
		setBounds(200, 200, 300, 350);
		contentPanel.setBackground(new Color(255, 255, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblCoordinateX = new JLabel("Coordinate x:");
		
		txt_corX = new JTextField();
		txt_corX.setColumns(10);
		
		JLabel lblCoordinateY = new JLabel("Coordinate y:  ");
		
		txt_corY = new JTextField();
		txt_corY.setColumns(10);
		
		JLabel lblWidth = new JLabel("Width:  ");
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		
		JLabel lblColorOut = new JLabel("Outer Color:  ");
		
		btnColorOut = new JButton("");
		btnColorOut.setBackground(new Color(204, 153, 153));
		btnColorOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp= JColorChooser.showDialog(null, "Choose color", colorOut);
				if(temp!=null){colorOut=temp;btnColorOut.setBackground(colorOut);}
			}
		});
		
		JLabel lblColorIn = new JLabel("Inner color:");
		
		btnColorIn = new JButton("");
		btnColorIn.setBackground(new Color(204, 153, 153));
		btnColorIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp= JColorChooser.showDialog(null, "Choose color", colorIn);
	 			if(temp!=null){colorIn=temp;btnColorIn.setBackground(colorIn);}
			
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(255, 204, 153));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						corX=Integer.parseInt(txt_corX.getText());
						cory=Integer.parseInt(txt_corY.getText());
						heightY=Integer.parseInt(txtHeight.getText());
						widthX=Integer.parseInt(txtWidth.getText());
						if(corX<0||cory<0||heightY<4||widthX<4)JOptionPane.showMessageDialog(null,"Values must be greater than  (0,0,4,4)", "Error!",JOptionPane.ERROR_MESSAGE);
				else if(corX+widthX>603||cory+heightY>437)JOptionPane.showMessageDialog(null,"Borders of paint window are 603 for x, 437 for y!", "Error!",JOptionPane.ERROR_MESSAGE);
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
		btnClose.setBackground(new Color(255, 204, 153));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save=false;
				dispose();
			}
		}); 
		
		JLabel lblHeight = new JLabel("Height:");
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCoordinateX, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(13))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCoordinateY)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblHeight)
							.addGap(18))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblWidth)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblColorOut)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblColorIn)
							.addGap(18)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnColorIn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtWidth, 0, 0, Short.MAX_VALUE)
						.addComponent(txt_corY, 0, 0, Short.MAX_VALUE)
						.addComponent(txt_corX, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
						.addComponent(txtHeight)
						.addComponent(btnColorOut, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_corX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCoordinateX))
					.addGap(13)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_corY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCoordinateY))
					.addGap(13)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHeight))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnColorOut, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblColorOut))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnColorIn, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblColorIn, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(108)
					.addComponent(btnSave)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClose)
					.addContainerGap(43, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnClose))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	//color and coo already selected
			public void addRectangle(int x,int y,Color out,Color in)
			{
				setCorX(x);
				setCorY(y);
				setColorOut(out);
				setColorIn(in);
				txt_corX.setText(Integer.toString(x));
				txt_corY.setText(Integer.toString(y));
				txt_corX.setEditable(false);
				txt_corY.setEditable(false);
				btnColorOut.setBackground(out);
				btnColorIn.setBackground(in);

			}

			public void editRectangle(int x,int y,int height,int width,Color out,Color in)
			{
				setTitle("Edit Rectangle");
				setCorX(x);
				setCorY(y);
				setHeightY(height);
				setWidthX(width);
				setColorOut(out);
				setColorIn(in);
				txt_corX.setText(Integer.toString(x));
				txt_corY.setText(Integer.toString(y));
				txtHeight.setText(Integer.toString(height));
				txtWidth.setText(Integer.toString(width));
				btnColorOut.setBackground(out);
				btnColorIn.setBackground(in);
			}

	public int getCorX() {
		return this.corX;
	}

	public void setCorX(int x) {
		this.corX = x;
	}

	public int getCorY() {
		return this.cory;
	}

	public void setCorY(int y) {
		this.cory = y;
	}

	public void setHeightY(int height) {
		this.heightY = height;
	}


	public void setWidthX(int width) {
		this.widthX = width;
	}
	

	public int getHeightY() {
		return this.heightY;
	}

	public int getWidthX() {
		return this.widthX;
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

