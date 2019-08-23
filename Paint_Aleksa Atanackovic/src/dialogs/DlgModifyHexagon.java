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

public class DlgModifyHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int tmpX, tmpY, tmpWidth;
	private Color borderColor;
	private Color areaColor;
	private JButton btnAreaColor;
	private JButton btnBorderColor;
	private JTextField txtWidth;
	private JTextField txtY;
	private JTextField txtX;
	private boolean check = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgModifyHexagon dialog = new DlgModifyHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgModifyHexagon() {
		setTitle("Modify hexagon");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 441, 337);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		txtX = new JTextField();
		txtX.setColumns(10);
		JLabel lblXCoordinate = new JLabel("x coordinate center point ");
		txtY = new JTextField();
		txtY.setColumns(10);
		JLabel lblYCoordinate = new JLabel("y coordinate center point ");
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		JLabel lblWidth = new JLabel("width");
		btnBorderColor = new JButton("");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBorderColor.setBackground(chooseColor(btnBorderColor.getBackground()));
			}
		});
		JLabel lblBorderColor = new JLabel("border color");

		btnAreaColor = new JButton("");
		btnAreaColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAreaColor.setBackground(chooseColor(btnAreaColor.getBackground()));
			}
		});

		JLabel lblAreaColor = new JLabel("area color");

		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmpSX, tmpSY, tmpW;
				if (txtWidth.getText().isEmpty() || txtX.getText().isEmpty() || txtY.getText().isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "Put value in all fields", "Error",
							JOptionPane.WARNING_MESSAGE);

				} else {
					try {
						 tmpSX = Integer.parseInt(getTxtX().getText());
						 tmpSY = Integer.parseInt(getTxtY().getText());
						 tmpW = Integer.parseInt(getTxtWidth().getText());

					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(getParent(), "You must put number", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtX.setText("");
						txtY.setText("");
						txtWidth.setText("");
						return;
					}
					if (tmpSX <= 0 || tmpSY <= 0 || tmpW <= 0) {
						JOptionPane.showMessageDialog(getParent(), "Values must be grather than 0", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtX.setText("");
						txtY.setText("");
						txtWidth.setText("");
						return;
					} else {
						setTmpX(tmpSX); 
						setTmpY(tmpSY);;
						setTmpWidth(tmpW);
						setBorderColor(getBtnBorderColor().getBackground());
						setAreaColor(getBtnAreaColor().getBackground());
						setCheck(true);
					}
				}
				dispose();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				//setVisible(false);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnBorderColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtWidth)
									.addComponent(txtY)
									.addComponent(txtX, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
								.addComponent(btnAreaColor, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCancel)
							.addGap(29)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblXCoordinate)
						.addComponent(lblYCoordinate)
						.addComponent(lblWidth)
						.addComponent(lblBorderColor)
						.addComponent(lblAreaColor)
						.addComponent(btnModify))
					.addContainerGap(85, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXCoordinate))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYCoordinate))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(26)
							.addComponent(lblBorderColor)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(btnAreaColor, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(27)
							.addComponent(lblAreaColor)))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnModify)))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

	public int getTmpX() {
		return tmpX;
	}

	public void setTmpX(int tmpX) {
		this.tmpX = tmpX;
	}

	public int getTmpY() {
		return tmpY;
	}

	public void setTmpY(int tmpY) {
		this.tmpY = tmpY;
	}

	public int getTmpWidth() {
		return tmpWidth;
	}

	public void setTmpWidth(int tmpWidth) {
		this.tmpWidth = tmpWidth;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}

	public JButton getBtnAreaColor() {
		return btnAreaColor;
	}

	public void setBtnAreaColor(JButton btnAreaColor) {
		this.btnAreaColor = btnAreaColor;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}
	
	public Color chooseColor(Color lastColor) {
		Color newColor = JColorChooser.showDialog(null, "Choose color", lastColor);
		if (newColor != null) {
			return newColor;
		} else {
			return lastColor;
		}
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	

}
