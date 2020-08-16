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



public class DlgAddEditSquare extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField txt_X;
	private JTextField txt_Y;
	private JTextField txtSideLength;
	private int x,y,side;
	private Color colorOut=Color.black,colorIn=Color.white;
	private boolean save;
	JButton btnColorOut;
	JButton btnColorIn;


	public static void main(String[] args) {
		try {
			DlgAddEditSquare dialog = new DlgAddEditSquare();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgAddEditSquare() {
		getContentPane().setBackground(new Color(255, 255, 204));
		setTitle("Add Square");
		setModal(true);
		setResizable(false);
		setBounds(200, 200, 300, 300);
		contentPanel.setBackground(new Color(255, 255, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblCoordinateX = new JLabel("Coordinate x:");
		
		txt_X = new JTextField();
		txt_X.setColumns(10);
		
		JLabel lblCoordinateY = new JLabel("Coordinate y:  ");
		
		txt_Y = new JTextField();
		txt_Y.setColumns(10);
		
		JLabel lblSideLength = new JLabel("Side Length:  ");
		
		txtSideLength = new JTextField();
		txtSideLength.setColumns(10);
		
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
		btnSave.setBackground(new Color(204, 153, 153));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					x=Integer.parseInt(txt_X.getText());
					y=Integer.parseInt(txt_Y.getText());
					side=Integer.parseInt(txtSideLength.getText());
					if(x<0||y<0||side<4)JOptionPane.showMessageDialog(null,"Values must be greater than (0,0,4)", "Error!",JOptionPane.ERROR_MESSAGE);
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
					.addGap(19)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCoordinateY)
						.addComponent(lblCoordinateX, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblColorOut)
							.addComponent(lblSideLength)
							.addComponent(lblColorIn)))
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnColorIn, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnColorOut, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtSideLength, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(txt_Y, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(txt_X, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_X, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCoordinateX))
					.addGap(13)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_Y, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCoordinateY))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(16)
							.addComponent(lblSideLength))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(13)
							.addComponent(txtSideLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(13)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblColorOut)
						.addComponent(btnColorOut, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClose)
							.addGap(16)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnClose))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	//color and coo already selected
	public void addSquare(int x,int y,Color out,Color in)
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
	public void editSquare(int x,int y,int side,Color out,Color in)
	{
		setTitle("Izmeni kvadrat");
		setX(x);
		setY(y);
		setSide(side);
		setColorOut(out);
		setColorIn(in);
		txt_X.setText(Integer.toString(x));
		txt_Y.setText(Integer.toString(y));
		txtSideLength.setText(Integer.toString(side));
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

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
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

