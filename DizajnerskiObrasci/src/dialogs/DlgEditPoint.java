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


public class DlgEditPoint extends JDialog  {

	private final JPanel contentPanel = new JPanel();
	private JTextField txt_X;
	private JTextField txt_Y;
	private JButton btnColorPoint;
	private int korX,korY;
	private Color color=Color.BLACK;
	private boolean save;

	public static void main(String[] args) {
		try {
			DlgEditPoint dialog = new DlgEditPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgEditPoint() {
		setTitle("Edit Point");
		setBounds(200, 200, 300, 300);
		setModal(true);
		setResizable(false);
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
		
		JLabel lblColor = new JLabel("Color:");
		
		btnColorPoint = new JButton("");
		btnColorPoint.setBackground(new Color(204, 153, 153));
		btnColorPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp= JColorChooser.showDialog(null, "Choose color", color);
	 			if(temp!=null){color=temp;btnColorPoint.setBackground(color);}
			}
		});
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(new Color(204, 153, 153));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					korX=Integer.parseInt(txt_X.getText());
					korY=Integer.parseInt(txt_Y.getText());
					if(korX<0||korX<0)JOptionPane.showMessageDialog(null,"Values must be greater than  -1!", "Error!",JOptionPane.ERROR_MESSAGE);
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
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblColor)
						.addComponent(lblCoordinateY)
						.addComponent(lblCoordinateX))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnColorPoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(txt_Y, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(txt_X, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
					.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(103, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnClose)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateX)
						.addComponent(txt_X, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateY)
						.addComponent(txt_Y, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColor)
						.addComponent(btnColorPoint, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnClose))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	public void EditPoint(int x,int y,Color color)
	{
		setCorX(x);
		setCorY(y);
		setColor(color);
		txt_X.setText(Integer.toString(x));
		txt_Y.setText(Integer.toString(y));
		btnColorPoint.setBackground(color);
	}

	public int getCorX() {
		return korX;
	}

	public void setCorX(int korX) {
		this.korX = korX;
	}

	public int getCorY() {
		return korY;
	}

	public void setCorY(int korY) {
		this.korY = korY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean getSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}
	
}
