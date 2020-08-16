package mvc;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import observer.Observer;

import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame extends JFrame implements Observer{

	
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> dlm = new DefaultListModel<String>(); 
	private Controller controller;
	private ControllerFiles controllerFiles;
	private JList<String> logList;
	private View view= new View();
	private Color chosenOutColor=new Color(255, 255, 255);
	private Color chosenInColor=new Color(0, 0, 0);
	private JButton btnEdit,btnDelete,btnColorIn,btnColorOut,btnUndo,btnRedo;
	private JToggleButton btnSelect,btnPoint,btnLine,btnCircle,btnSquare,btnRectangle,btnHexagon;
	
	public Frame() {
		JPanel jpMainPanel = new JPanel();
		jpMainPanel.setBackground(new Color(255, 255, 153));
		getContentPane().add(jpMainPanel, BorderLayout.CENTER);
		jpMainPanel.setLayout(new BorderLayout(5, 0));
		
		JPanel jpToolsPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) jpToolsPanel.getLayout();
		flowLayout_1.setVgap(2);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		jpToolsPanel.setBackground(new Color(255, 255, 153));
		jpMainPanel.add(jpToolsPanel, BorderLayout.NORTH);
		
		JPanel jpActionsPanel = new JPanel();
		jpActionsPanel.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout_5 = (FlowLayout) jpActionsPanel.getLayout();
		flowLayout_5.setHgap(2);
		flowLayout_5.setVgap(12);
		jpActionsPanel.setBackground(new Color(255, 255, 153));
		jpToolsPanel.add(jpActionsPanel);
		
		btnSelect = new JToggleButton("");
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(controller.getSelectedShapes()>1) 
				{
					controller.deselectAll();
				}
				else
				{
				btnSelect.setSelected(true);
				}
			}
		});
		btnSelect.setBackground(new Color(255, 255, 204));
		btnSelect.setToolTipText("Select");
		btnSelect.setIcon(new ImageIcon(Frame.class.getResource("/images/cursor.png")));
		jpActionsPanel.add(btnSelect);
		btnSelect.setEnabled(false);
		
		btnEdit = new JButton("");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				controller.edit(); 
			}
		});
		btnEdit.setBackground(new Color(255, 255, 204));
		btnEdit.setForeground(new Color(0, 0, 0));
		btnEdit.setToolTipText("Edit");
		btnEdit.setIcon(new ImageIcon(Frame.class.getResource("/images/pencil.png")));
		jpActionsPanel.add(btnEdit);
		btnEdit.setEnabled(false);
		
		 btnDelete = new JButton("");
		 btnDelete.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		if (btnDelete.isEnabled()) {
					int option = JOptionPane.showConfirmDialog(Frame.this,
							"Are you sure you want to delete selected object/s?", "Delete", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION)
						controller.delete();
				}
		 	}
		 });
		btnDelete.setBackground(new Color(255, 255, 204));
		btnDelete.setToolTipText("Delete");
		btnDelete.setIcon(new ImageIcon(Frame.class.getResource("/images/delete.png")));
		jpActionsPanel.add(btnDelete);
		btnDelete.setEnabled(false);
		
		JPanel jpShapesPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jpShapesPanel.getLayout();
		flowLayout.setHgap(2);
		flowLayout.setVgap(12);
		flowLayout.setAlignment(FlowLayout.LEFT);
		jpToolsPanel.add(jpShapesPanel);
		jpShapesPanel.setBackground(new Color(255, 255, 153));
		jpShapesPanel.setBorder(new TitledBorder(null, "Shapes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		btnPoint = new JToggleButton("");
		btnPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnPoint.setBackground(new Color(255, 255, 204));
		btnPoint.setToolTipText("point");
		btnPoint.setIcon(new ImageIcon(Frame.class.getResource("/images/cross-shaped-target.png")));
		jpShapesPanel.add(btnPoint);
		
		btnLine = new JToggleButton("");
		btnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnLine.setBackground(new Color(255, 255, 204));
		btnLine.setToolTipText("line");
		btnLine.setIcon(new ImageIcon(Frame.class.getResource("/images/line.png")));
		jpShapesPanel.add(btnLine);
		
		 btnCircle = new JToggleButton("");
		 btnCircle.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 	}
		 });
		btnCircle.setBackground(new Color(255, 255, 204));
		btnCircle.setToolTipText("circle");
		btnCircle.setIcon(new ImageIcon(Frame.class.getResource("/images/clean.png")));
		jpShapesPanel.add(btnCircle);
		
		btnSquare = new JToggleButton("");
		btnSquare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnSquare.setBackground(new Color(255, 255, 204));
		btnSquare.setToolTipText("square");
		btnSquare.setIcon(new ImageIcon(Frame.class.getResource("/images/check-box.png")));
		jpShapesPanel.add(btnSquare);
		
		btnRectangle = new JToggleButton("");
		btnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnRectangle.setBackground(new Color(255, 255, 204));
		btnRectangle.setToolTipText("rectangle");
		btnRectangle.setIcon(new ImageIcon(Frame.class.getResource("/images/rectangle.png")));
		jpShapesPanel.add(btnRectangle);
		
		btnHexagon = new JToggleButton("");
		btnHexagon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnHexagon.setBackground(new Color(255, 255, 204));
		btnHexagon.setToolTipText("hexagon");
		btnHexagon.setIcon(new ImageIcon(Frame.class.getResource("/images/hexagon.png")));
		jpShapesPanel.add(btnHexagon);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(btnSelect);
		btnGroup.add(btnPoint);
		btnGroup.add(btnLine);
		btnGroup.add(btnSquare);
		btnGroup.add(btnRectangle);
		btnGroup.add(btnCircle);
		btnGroup.add(btnHexagon);
		
		JPanel jpColorPanel = new JPanel();
		jpToolsPanel.add(jpColorPanel);
		jpColorPanel.setBorder(new TitledBorder(null, "Color", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jpColorPanel.setBackground(new Color(255, 255, 153));
		
		 btnColorIn = new JButton("");
		btnColorIn.setToolTipText("inside");
		btnColorIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "choose color", chosenInColor);
				if (temp != null) {
					chosenInColor = temp;
					btnColorIn.setBackground(chosenInColor);
				}
			}
		});
		btnColorIn.setBackground(new Color(255, 255, 255));
		
		btnColorOut = new JButton("");
		btnColorOut.setToolTipText("outside");
        btnColorOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "choose color", chosenOutColor);
				if (temp != null) {
					chosenOutColor = temp;
					btnColorOut.setBackground(chosenOutColor);
				}
			}
		});
        btnColorOut.setBackground(new Color(0, 0, 0));
		
				
				GroupLayout gl_jpColorPanel = new GroupLayout(jpColorPanel);
				gl_jpColorPanel.setHorizontalGroup(
					gl_jpColorPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpColorPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(btnColorIn)
							.addGap(5)
							.addComponent(btnColorOut)
							.addContainerGap())
				);
				gl_jpColorPanel.setVerticalGroup(
					gl_jpColorPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpColorPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_jpColorPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnColorOut, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnColorIn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				jpColorPanel.setLayout(gl_jpColorPanel);

		JPanel jpPositionPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) jpPositionPanel.getLayout();
		flowLayout_2.setHgap(2);
		flowLayout_2.setVgap(10);
		jpToolsPanel.add(jpPositionPanel);
		jpPositionPanel.setBorder(new TitledBorder(null, "Switch Position", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jpPositionPanel.setBackground(new Color(255, 255, 153));
		
		
		JButton btnToFront = new JButton("");
		btnToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnToFront.isEnabled()) {
					controller.toFront();
					update();
				}
			}
		});
		btnToFront.setBackground(new Color(255, 255, 204));
		btnToFront.setToolTipText("bring to front");
		btnToFront.setIcon(new ImageIcon(Frame.class.getResource("/images/Bring to front (2).png")));
		jpPositionPanel.add(btnToFront);
		btnToFront.setEnabled(false);
		
		JButton btnToBack = new JButton("");
		btnToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnToBack.isEnabled()) {
					controller.toBack();
					update();
				}
			}
		});
		btnToBack.setBackground(new Color(255, 255, 204));
		btnToBack.setToolTipText("bing to back");
		btnToBack.setIcon(new ImageIcon(Frame.class.getResource("/images/Bring to back (1).png")));
		jpPositionPanel.add(btnToBack);
		btnToBack.setEnabled(false);
		
		JButton btnBringBack = new JButton("");
		btnBringBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnBringBack.isEnabled()) {
					controller.bringToBack();
					update();
				}
			}
		});
		btnBringBack.setBackground(new Color(255, 255, 204));
		btnBringBack.setToolTipText("bring back");
		btnBringBack.setIcon(new ImageIcon(Frame.class.getResource("/images/bring back (1).png")));
		jpPositionPanel.add(btnBringBack);
		btnBringBack.setEnabled(false);
		
		JButton btnBringFront = new JButton("");
		btnBringFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnBringFront.isEnabled()) {
					controller.bringToFront();
					update();
				}
			}
		});
		btnBringFront.setBackground(new Color(255, 255, 204));
		btnBringFront.setToolTipText("bring front");
		btnBringFront.setIcon(new ImageIcon(Frame.class.getResource("/images/Bring front (1).png")));
		jpPositionPanel.add(btnBringFront);
		btnBringFront.setEnabled(false);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(245,129,115));
		setJMenuBar(menuBar);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controllerFiles.save();
			}
		});
		btnSave.setBackground(new Color(255, 204, 153));
		btnSave.setIcon(new ImageIcon(Frame.class.getResource("/images/diskette.png")));
		menuBar.add(btnSave);
		btnSave.setEnabled(false);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controllerFiles.open();
			}
		});
		btnOpen.setBackground(new Color(255, 204, 153));
		btnOpen.setIcon(new ImageIcon(Frame.class.getResource("/images/folder.png")));
		menuBar.add(btnOpen);
		
		btnUndo = new JButton("Undo");
		btnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnUndo.isEnabled()) {
					controller.undo();
					update();
				}
			}
		});
		btnUndo.setBackground(new Color(255, 204, 153));
		btnUndo.setIcon(new ImageIcon(Frame.class.getResource("/images/left-turn-arrow (1).png")));
		menuBar.add(btnUndo);
		btnUndo.setEnabled(false);
		
		btnRedo = new JButton("Redo");
		btnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnRedo.isEnabled()) {
					controller.redo();
					update();
				}
			}
		});
		btnRedo.setBackground(new Color(255, 204, 153));
		btnRedo.setIcon(new ImageIcon(Frame.class.getResource("/images/send.png")));
		menuBar.add(btnRedo);
		btnRedo.setEnabled(false);

		JPanel panel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel.getLayout();
		flowLayout_4.setVgap(0);
		panel.setBackground(new Color(255, 255, 153));
		jpMainPanel.add(panel, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		logList = new JList<String>();
		logList.setVisibleRowCount(27);
		logList.setFixedCellWidth(230);
		logList.setModel(dlm);
		scrollPane.setViewportView(logList);
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnSelect.isSelected())
				{
					controller.select(e);
				}
				controller.draw(e, btnColorOut.getBackground(), btnColorIn.getBackground());
			}
		});

		jpMainPanel.add(view, BorderLayout.CENTER);
		view.setBackground(Color.WHITE);

	}
	@Override
	public void update() {
		//za kad budu selektovani
		//if log list empty - btnSave.setEnabeled(false);
		
		if(controller.getSelectedShapes()==1)
		{
			getBtnEdit().setEnabled(true);
			getBtnDelete().setEnabled(true);
		}
		else if (controller.getSelectedShapes()>1)
		{
			getBtnEdit().setEnabled(false);
			getBtnDelete().setEnabled(true);
		}
		else {
			getBtnEdit().setEnabled(false);
			getBtnDelete().setEnabled(false);
		}
	}
	
	public void backToBeginingState()
	{
		 getBtnSelect().setEnabled(false);
		 getBtnEdit().setEnabled(false);
		 getBtnDelete().setEnabled(false);
		 
	}
	
	public void addToLogList(String string)
	{
		this.dlm.addElement(string);
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}


	public JButton getBtnRedo() {
		return btnRedo;
	}


	public View getView() {
		return view;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}



	public DefaultListModel<String> getDlm() {
		return dlm;
	}


	public Color getChosenOutColor() {
		return chosenOutColor;
	}


	public Color getChosenInColor() {
		return chosenInColor;
	}


	public JButton getBtnEdit() {
		return btnEdit;
	}


	public JButton getBtnDelete() {
		return btnDelete;
	}


	public JButton getBtnColorIn() {
		return btnColorIn;
	}


	public JButton getBtnColorOut() {
		return btnColorOut;
	}


	public JToggleButton getBtnSelect() {
		return btnSelect;
	}


	public JToggleButton getBtnPoint() {
		return btnPoint;
	}


	public JToggleButton getBtnLine() {
		return btnLine;
	}


	public JToggleButton getBtnCircle() {
		return btnCircle;
	}


	public JToggleButton getBtnSquare() {
		return btnSquare;
	}


	public JToggleButton getBtnRectangle() {
		return btnRectangle;
	}


	public JToggleButton getBtnHexagon() {
		return btnHexagon;
	}

}
