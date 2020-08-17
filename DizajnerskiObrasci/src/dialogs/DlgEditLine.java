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

public class DlgEditLine extends JDialog  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_XFirst;
	private JTextField txt_YFirst;
	private JTextField txt_XLast;
	private JTextField txt_YLast;
	private int firstX,firstY,lastX,lastY;
	private Color color=Color.BLACK;
	private JButton btnColorLine;
	private boolean save;
	
	public static void main(String[] args) {
		try {
			DlgEditLine dialog = new DlgEditLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgEditLine() {
		setTitle("Edit Line");
		setBounds(200, 200, 300, 350);
		setModal(true);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.EAST);
		
		JLabel lblFirstPoint = new JLabel("First point");
		
		JLabel lblCoordinateX = new JLabel("Coordinate x:");
		
		txt_XFirst = new JTextField();
		txt_XFirst.setColumns(10);
		
		JLabel lblCoordinateY = new JLabel("Coordinate y:");
		
		txt_YFirst = new JTextField();
		txt_YFirst.setColumns(10);
		
		JLabel lblLastPoint = new JLabel("Last point");
		
		JLabel lblCoordinateX_1 = new JLabel("Coordinate x:");
		
		txt_XLast = new JTextField();
		txt_XLast.setColumns(10);
		
		JLabel lblCoordinateY_1 = new JLabel("Coordinate y:");
		
		txt_YLast = new JTextField();
		txt_YLast.setColumns(10);
		
		JLabel lblColor = new JLabel("Color");
		
		 btnColorLine = new JButton("");
		 btnColorLine.setBackground(new Color(204, 153, 153));
		btnColorLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp= JColorChooser.showDialog(null, "Choose color", color);
	 			if(temp!=null){color=temp;btnColorLine.setBackground(color);}
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(204, 153, 153));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					firstX=Integer.parseInt(txt_XFirst.getText());
					firstY=Integer.parseInt(txt_YFirst.getText());
					lastX=Integer.parseInt(txt_XLast.getText());
					lastY=Integer.parseInt(txt_YLast.getText());
					if(firstX<0||firstY<0||lastX<0||lastY<0)JOptionPane.showMessageDialog(null,"Values must be greater than  -1!", "Error!",JOptionPane.ERROR_MESSAGE);
					else if(firstX==lastX&&firstY==lastY)JOptionPane.showMessageDialog(null,"First and last point cannot be the same!", "Error!",JOptionPane.ERROR_MESSAGE);
					else if(firstX>603||firstY>437||lastX>603||lastY>437)JOptionPane.showMessageDialog(null,"Borders of paint window are 603 for x, 437 for y!", "Error!",JOptionPane.ERROR_MESSAGE);
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
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFirstPoint))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblLastPoint))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
									.addGap(57)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblCoordinateY)
										.addComponent(lblCoordinateX))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txt_YFirst, 0, 0, Short.MAX_VALUE)
										.addComponent(txt_XFirst, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))
								.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
									.addGap(60)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblCoordinateY_1)
										.addComponent(lblCoordinateX_1)
										.addComponent(lblColor, Alignment.LEADING))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txt_XLast, 0, 0, Short.MAX_VALUE)
										.addComponent(txt_YLast, 0, 0, Short.MAX_VALUE)
										.addComponent(btnColorLine, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)))
					.addGap(15))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(105, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblFirstPoint)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_XFirst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCoordinateX))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateY)
						.addComponent(txt_YFirst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblLastPoint)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateX_1)
						.addComponent(txt_XLast, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateY_1)
						.addComponent(txt_YLast, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblColor)
						.addComponent(btnColorLine, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnClose))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void editLine(int px,int py,int kx,int ky,Color color)
	{
		setTitle("Edit Line");
		setFirstX(px);
		setFirstY(py);
		setLastX(kx);
		setLastY(ky);
		setColor(color);
		txt_XFirst.setText(Integer.toString(px));
		txt_YFirst.setText(Integer.toString(py));
		txt_XLast.setText(Integer.toString(kx));
		txt_YLast.setText(Integer.toString(ky));
		btnColorLine.setBackground(color);
	}

	public int getFirstX() {
		return firstX;
	}

	public void setFirstX(int firstX) {
		this.firstX = firstX;
	}

	public int getFirstY() {
		return firstY;
	}

	public void setFirstY(int firstY) {
		this.firstY = firstY;
	}

	public int getLastX() {
		return lastX;
	}

	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
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
