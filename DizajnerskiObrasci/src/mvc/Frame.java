package mvc;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import java.awt.Color;
import java.awt.Dimension;

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
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class Frame extends JFrame implements Observer{

	
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> dlm = new DefaultListModel<String>(); 
	private ControllerDrawing controller;
	private ControllerFiles controllerFiles;
	private JList<String> logList;
	private View view= new View();
	private Color chosenOutColor=new Color(255, 255, 255);
	private Color chosenInColor=new Color(0, 0, 0);
	private JButton btnEdit,btnDelete,btnColorIn,btnColorOut,btnUndo,btnRedo,btnSave,btnOpen,btnToFront,btnToBack,btnBringFront,btnBringBack;
	private JToggleButton btnSelect,btnPoint,btnLine,btnCircle,btnSquare,btnRectangle,btnHexagon;
	private JButton btnNext;
	
	public Frame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Frame.class.getResource("/images/paint.png")));
		JPanel jpMainPanel = new JPanel();
		jpMainPanel.setBackground(new Color(255, 255, 153));
		getContentPane().add(jpMainPanel, BorderLayout.CENTER);
		jpMainPanel.setLayout(new BorderLayout(5, 0));
		
		JPanel jpToolsPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) jpToolsPanel.getLayout();
		flowLayout_1.setVgap(2);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		jpToolsPanel.setBackground(new Color(255, 255, 204));
		jpMainPanel.add(jpToolsPanel, BorderLayout.NORTH);
		
		JPanel jpActionsPanel = new JPanel();
		jpActionsPanel.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout_5 = (FlowLayout) jpActionsPanel.getLayout();
		flowLayout_5.setHgap(2);
		flowLayout_5.setVgap(12);
		jpActionsPanel.setBackground(new Color(255, 255, 204));
		jpToolsPanel.add(jpActionsPanel);
		
		btnSelect = new JToggleButton("");
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(controller.getSelectedShapes()>0) 
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
		jpShapesPanel.setBackground(new Color(255, 255, 204));
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
		jpColorPanel.setBackground(new Color(255, 255, 204));
		
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
				jpColorPanel.setLayout(new MigLayout("", "[29px][29px]", "[32px,center]"));
				jpColorPanel.add(btnColorIn, "cell 0 0,alignx left,growy");
				jpColorPanel.add(btnColorOut, "cell 1 0,alignx left,growy");

		JPanel jpPositionPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) jpPositionPanel.getLayout();
		flowLayout_2.setHgap(2);
		flowLayout_2.setVgap(10);
		jpToolsPanel.add(jpPositionPanel);
		jpPositionPanel.setBorder(new TitledBorder(null, "Switch Position", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jpPositionPanel.setBackground(new Color(255, 255, 204));
		
		
		 btnToFront = new JButton("");
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
		
		btnToBack = new JButton("");
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
		
		btnBringFront = new JButton("");
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
		
		btnBringBack = new JButton("");
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
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(245,129,115));
		setJMenuBar(menuBar);
		
		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] possibleValues = { "Save Log", "Save Drawing"};
				int selectedValue=JOptionPane.showOptionDialog(null, "Choose option", "Save",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, possibleValues, possibleValues[0]);
			//	0-log 1-drawing
				controllerFiles.save(selectedValue);
				}
		});
		btnSave.setBackground(new Color(255, 204, 153));
		btnSave.setIcon(new ImageIcon(Frame.class.getResource("/images/diskette.png")));
		menuBar.add(btnSave);
		btnSave.setEnabled(false);
		
		btnOpen = new JButton("Open");
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] possibleValues = { "Open Log", "Open Drawing"};
				int selectedValue=JOptionPane.showOptionDialog(null, "Choose option", "Open",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, possibleValues, possibleValues[0]);
				controllerFiles.open(selectedValue);
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
				//	update();
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
				//	update();
				}
			}
		});
		btnRedo.setBackground(new Color(255, 204, 153));
		btnRedo.setIcon(new ImageIcon(Frame.class.getResource("/images/send.png")));
		menuBar.add(btnRedo);
		btnRedo.setEnabled(false);
		
		btnNext = new JButton("Next");
		btnNext.setBackground(new Color(255, 204, 153));
		btnNext.setVisible(false);
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controllerFiles.Next();
			}
		});
		btnNext.setIcon(new ImageIcon(Frame.class.getResource("/images/next.png")));
		menuBar.add(btnNext);

		JPanel panel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel.getLayout();
		flowLayout_4.setVgap(0);
		panel.setBackground(new Color(255, 255, 204));
		jpMainPanel.add(panel, BorderLayout.EAST);
		panel.setPreferredSize(new Dimension(250,605));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setAutoscrolls(true);
		panel.add(scrollPane);
		logList = new JList<String>();
		logList.setBorder(new LineBorder(new Color(255, 255, 255), 8));
		logList.setVisibleRowCount(26);
		logList.setModel(dlm);
		scrollPane.setViewportView(logList);
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnSelect.isSelected())
				{
					controller.select(e);
				}
				controller.addShape(e, btnColorOut.getBackground(), btnColorIn.getBackground());
			}
		});

		jpMainPanel.add(view, BorderLayout.CENTER);
		view.setBackground(Color.WHITE);
		view.setPreferredSize(new Dimension(603, 437));
		// velicina je 603x437

	}
	@Override
	public void update() {

		int selected =controller.getSelectedShapes();
		
		if(selected==1)
		{
			controller.Position();
			getBtnEdit().setEnabled(true);
			getBtnDelete().setEnabled(true);
		}
		else if (selected>1)
		{
		
			getBtnEdit().setEnabled(false);
			getBtnDelete().setEnabled(true);
			resetPositionButtons();
		}
		else {
			getBtnEdit().setEnabled(false);
			getBtnDelete().setEnabled(false);
			resetPositionButtons();
		}
	}
	public void resetPositionButtons()
	{
		getBtnToBack().setEnabled(false);
		getBtnToFront().setEnabled(false);
		getBtnBringBack().setEnabled(false);
		getBtnBringFront().setEnabled(false);
	}
	
	public void backToBeginingState()
	{
		 getBtnSelect().setEnabled(false);
		 getBtnEdit().setEnabled(false);
		 getBtnDelete().setEnabled(false);
		 getBtnToBack().setEnabled(false);
		 getBtnToFront().setEnabled(false);
		 getBtnBringBack().setEnabled(false);
		 getBtnBringFront().setEnabled(false);
		 getBtnColorIn().setBackground(Color.WHITE);
		 getBtnColorOut().setBackground(Color.BLACK);
	}
	
	public void addToLogList(String string)
	{
		this.dlm.addElement(string);
	}

	public JButton getBtnNext() {
		return btnNext;
	}
	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
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
	public void setController(ControllerDrawing controller) {
		this.controller = controller;
	}
	public void setControllerFiles(ControllerFiles controllerFiles) {
		this.controllerFiles = controllerFiles;
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
	public JButton getBtnSave() {
		return btnSave;
	}
	public JButton getBtnOpen() {
		return btnOpen;
	}
	public JButton getBtnToFront() {
		return btnToFront;
	}
	public JButton getBtnToBack() {
		return btnToBack;
	}
	public JButton getBtnBringFront() {
		return btnBringFront;
	}
	public JButton getBtnBringBack() {
		return btnBringBack;
	}


}
