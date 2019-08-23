package mvc;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JToggleButton;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import javax.swing.JSeparator;

public class DrawingFrame extends JFrame{
	
	private JButton btnBorderColor = new JButton("Border color");
	private JButton btnAreaColor = new JButton("Area color");
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private	JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnSquare = new JToggleButton("Square");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private JButton btnDelete = new JButton("Delete");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private final JButton btnModify = new JButton("Modify");
	private JButton btnUndo = new JButton("Undo");
	private JButton btnRedo = new JButton("Redo");
	private final JButton btnToFront = new JButton("To Front");
	private final JButton btnToBack = new JButton("To back");
	private final JButton btnBringToFront = new JButton("Bring to front");
	private final JButton btnBringToBack = new JButton("Bring to back");
	
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private final JList<String> listLog = new JList<String>(listModel);
	private final JScrollPane scrollPane = new JScrollPane(listLog);
	private final JButton btnOpenDrawing = new JButton("Open drawing");
	private final JButton btnSaveDrawing = new JButton("Save drawing");
	private final JButton btnOpenLog = new JButton("Open log");
	private final JButton btnSaveLog = new JButton("Save log");
	private JButton btnDrawLog = new JButton("Draw log");

	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmOpenDrawing = new JMenuItem("Open drawing");
	private final JMenuItem mntmSaveDrawing = new JMenuItem("Save drawing");
	private final JMenuItem mntmOpenLog = new JMenuItem("Open log");
	private final JMenuItem mntmSaveLog = new JMenuItem("Save log");


	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;

	}
	
	public DrawingFrame() {	
			
		setTitle("Paint");
		setMinimumSize(new Dimension(760, 530));
				
				JPanel panel_1 = new JPanel();
				
				JPanel panel = new JPanel();
				
				buttonGroup.add(tglbtnPoint);
				
				buttonGroup.add(tglbtnLine);
				
				buttonGroup.add(tglbtnCircle);
				
				buttonGroup.add(tglbtnSquare);
				
				buttonGroup.add(tglbtnRectangle);
				
				buttonGroup.add(tglbtnHexagon);
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(22)
							.addComponent(tglbtnPoint)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnLine)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnCircle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnSquare)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnRectangle)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tglbtnHexagon)
							.addContainerGap(50, Short.MAX_VALUE))
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(tglbtnHexagon)
							.addComponent(tglbtnRectangle)
							.addComponent(tglbtnSquare)
							.addComponent(tglbtnCircle)
							.addComponent(tglbtnLine)
							.addComponent(tglbtnPoint))
				);
				panel.setLayout(gl_panel);
				
				//border color
				btnBorderColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						btnBorderColor.setBackground(chooseColor(btnBorderColor.getBackground()));
					}
				});
				
				btnBorderColor.setBackground(Color.BLACK);
				
				//Area color
				btnAreaColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnAreaColor.setBackground(chooseColor(btnAreaColor.getBackground()));
					}
				});
				
				btnAreaColor.setBackground(Color.WHITE);
				GroupLayout gl_panel_1 = new GroupLayout(panel_1);
				gl_panel_1.setHorizontalGroup(
					gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(btnAreaColor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
									.addComponent(btnToFront, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
									.addComponent(btnToBack, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
									.addComponent(btnBorderColor, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
								.addComponent(btnBringToFront, Alignment.TRAILING)))
				);
				btnToBack.setEnabled(false);
				btnToBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.toBack();
					}
				});
				btnToFront.setEnabled(false);
				btnToFront.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.toFront();
					}
				});
				btnBringToFront.setEnabled(false);
				btnBringToFront.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.bringToFront();
					}
				});
				gl_panel_1.setVerticalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnBorderColor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAreaColor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnToFront)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnToBack)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnBringToFront))
				);
				panel_1.setLayout(gl_panel_1);
		
				view.setBackground(Color.WHITE);
				GridBagLayout gbl_view = new GridBagLayout();
				gbl_view.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
				gbl_view.rowHeights = new int[]{0, 0, 0, 0};
				gbl_view.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
				gbl_view.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
				view.setLayout(gbl_view);
				
				//getContentPane().add(view, BorderLayout.CENTER);

				view.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {

						controller.mouseClicked(arg0);

					}
				});
		
		JPanel panel_2 = new JPanel();
		
		buttonGroup.add(tglbtnSelect);
		
		//modify shape
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.modify();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModify.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.delete();
			}
		});
		btnDelete.setEnabled(false);
		
		btnOpenDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.openPaint();
			}
		});
		
		btnDrawLog.setEnabled(false);
		btnDrawLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.drawLog();
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDrawLog, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(btnOpenDrawing, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(btnSaveDrawing, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(btnOpenLog, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(btnSaveLog, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addComponent(tglbtnSelect, Alignment.TRAILING)
							.addComponent(btnModify, Alignment.TRAILING)
							.addComponent(btnDelete, Alignment.TRAILING)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(tglbtnSelect)
					.addGap(5)
					.addComponent(btnModify)
					.addGap(5)
					.addComponent(btnDelete)
					.addGap(47)
					.addComponent(btnOpenDrawing)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSaveDrawing)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOpenLog)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSaveLog)
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addComponent(btnDrawLog, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		btnSaveDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.savePaint();

			}
		});
		btnOpenLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openLog();
			}
		});
		btnSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();

			}
		});
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		
		//JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnBringToBack)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
								.addComponent(view, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
							.addGap(5)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 336, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBringToBack))
								.addComponent(view, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		//JList list = new JList();
		scrollPane.setViewportView(listLog);
		
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		btnUndo.setEnabled(false);
		

		panel_3.add(btnUndo);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});
		btnRedo.setEnabled(false);
		
		
		panel_3.add(btnRedo);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		getContentPane().setLayout(groupLayout);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		mntmOpenDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.openPaint();
			}
		});
		
		mnFile.add(mntmOpenDrawing);
		mntmSaveDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.savePaint();
			}
		});
		
		mnFile.add(mntmSaveDrawing);
		mntmOpenLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openLog();
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		mnFile.add(mntmOpenLog);
		mntmSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
			}
		});
		
		mnFile.add(mntmSaveLog);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
	}
	
	public Color chooseColor(Color orgColor) {
		Color newColor = JColorChooser.showDialog(null, "Select a color", orgColor);
		if (newColor != null) {
			return newColor;
		} else {
			return orgColor;
		}
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnSquare() {
		return tglbtnSquare;
	}

	public void setTglbtnSquare(JToggleButton tglbtnSquare) {
		this.tglbtnSquare = tglbtnSquare;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public JButton getBtnAreaColor() {
		return btnAreaColor;
	}

	public void setBtnAreaColor(JButton btnAreaColor) {
		this.btnAreaColor = btnAreaColor;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnModify() {
		return btnModify;
	}



	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}
	public void setListModel(DefaultListModel<String> listModel) {
		this.listModel = listModel;
	}

	public JButton getBtnDrawLog() {
		return btnDrawLog;
	}

	public void setBtnDrawLog(JButton btnDrawLog) {
		this.btnDrawLog = btnDrawLog;
	}
	

	
}
