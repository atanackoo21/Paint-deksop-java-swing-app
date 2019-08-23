package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DlgModifyCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JButton btnBorder;
	private JButton btnArea;
	private boolean confirm;
	private int tmpCenterX, tmpCenterY;
	private int radius;
	private Color areaColor;
	private Color borderColor;
	private boolean check = false;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			DlgModifyCircle dialog = new DlgModifyCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgModifyCircle() {
		setTitle("Izmeni krug");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 322, 352);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		txtX = new JTextField();
		txtX.setColumns(10);
		JLabel lblXKordinataCentra = new JLabel("x coordinate ");
		txtY = new JTextField();
		txtY.setColumns(10);
		JLabel lblYKordinataCentra = new JLabel("y cooordinate");
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		JLabel lblDuzinaPoluprecnika = new JLabel("radius");
		JLabel lblBojaIvice = new JLabel("border color");
		btnArea = new JButton("");
		btnArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnArea.setBackground(chooseColor(btnArea.getBackground()));
			}
		});
		JLabel lblBojaUnutrasnjosti = new JLabel("interior color");
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmpSX, tmpSY, tmpRadius;
				if (txtRadius.getText().isEmpty() || txtX.getText().isEmpty() || txtY.getText().isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "Field cannot be empty.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						 tmpSX = Integer.parseInt(getTxtX().getText());
						 tmpSY = Integer.parseInt(getTxtY().getText());
						 tmpRadius = Integer.parseInt(getTxtRadius().getText());

					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(getParent(), "You must put number", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtX.setText("");
						txtY.setText("");
						txtRadius.setText("");
						return;
					}
					if (tmpSX <= 0 || tmpSY <= 0 || tmpRadius <= 0) {
						JOptionPane.showMessageDialog(getParent(), "Values must be grather than 0", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtX.setText("");
						txtY.setText("");
						txtRadius.setText("");
						return;
					} else {
						setTmpCenterX(tmpSX); 
						setTmpCenterY(tmpSY);;
						setRadius(tmpRadius);
						setBorderColor(getBtnBorderColor().getBackground());
						setAreaColor(getBtnAreaColor().getBackground());
						setCheck(true);
					}
				}
				dispose();
			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBorder = new JButton("");
		btnBorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBorder.setBackground(chooseColor(btnBorder.getBackground()));
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnArea, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(txtRadius)
								.addComponent(txtY)
								.addComponent(txtX, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnBorder, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(44)
							.addComponent(btnCancel)))
					.addGap(35)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblXKordinataCentra)
						.addComponent(lblYKordinataCentra)
						.addComponent(lblDuzinaPoluprecnika)
						.addComponent(lblBojaIvice)
						.addComponent(lblBojaUnutrasnjosti)
						.addComponent(btnModify))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXKordinataCentra))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYKordinataCentra))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDuzinaPoluprecnika))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBojaIvice)
						.addComponent(btnBorder, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnArea, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBojaUnutrasnjosti))
					.addGap(38)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnModify))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}

	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public JButton getBtnBorderColor() {
		return btnBorder;
	}

	public JButton getBtnAreaColor() {
		return btnArea;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public Color chooseColor(Color lastColor) {
		Color novaBoja = JColorChooser.showDialog(null, "Choose color", lastColor);
		if (novaBoja != null) {
			return novaBoja;
		} else {
			return lastColor;
		}
	}

	public int getTmpCenterX() {
		return tmpCenterX;
	}

	public void setTmpCenterX(int tmpCenterX) {
		this.tmpCenterX = tmpCenterX;
	}

	public int getTmpCenterY() {
		return tmpCenterY;
	}

	public void setTmpCenterY(int tmpCenterY) {
		this.tmpCenterY = tmpCenterY;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}
