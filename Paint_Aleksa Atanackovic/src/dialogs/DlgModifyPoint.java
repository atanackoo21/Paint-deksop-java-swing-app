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

public class DlgModifyPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JButton btnColor;
	private int tmpX,tmpY;
	private Color color;
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
			DlgModifyPoint dialog = new DlgModifyPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgModifyPoint() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setModal(true);
		setResizable(false);
		setTitle("Modify point");
		setBounds(100, 100, 349, 262);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.SOUTH);

		txtX = new JTextField();
		txtX.setColumns(10);

		JLabel lblXCoordinate = new JLabel("x coordinate");

		txtY = new JTextField();
		txtY.setColumns(10);

		JLabel lblYKordinataTacke = new JLabel("y coordinate");

		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ttmpX, ttmpY;
				
				if (txtX.getText().isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "Coordinate X can't be empty!", "Error!",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if (txtY.getText().isEmpty()) {
						JOptionPane.showMessageDialog(getParent(), "Coordinate Y can't be empty!", "Error!",
								JOptionPane.ERROR_MESSAGE);
						return;
				} else {
					try {
						 ttmpX = Integer.parseInt(txtX.getText());
						 ttmpY = Integer.parseInt(txtY.getText());
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(getParent(), "You must put number", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtX.setText("");
						txtY.setText("");
						return;
					}
					if (ttmpX <= 0 || ttmpY <= 0) {
						JOptionPane.showMessageDialog(getParent(), "Values must be grather than 0", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtX.setText("");
						txtY.setText("");
						return;
					} else {
						setTmpX(ttmpX); 
						setTmpY(ttmpY);
						setColor(getBtnColor().getBackground());
						setCheck(true);
					}
				}
				dispose();
				
				//setVisible(false);
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel lblColor = new JLabel("color");

		btnColor = new JButton("");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnColor.setBackground(chooseColor(btnColor.getBackground()));
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
										.addComponent(txtY, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)))
							.addGap(36)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblXCoordinate)
								.addComponent(lblColor)
								.addComponent(lblYKordinataTacke))
							.addGap(45))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGap(42)
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
							.addComponent(btnModify)
							.addGap(76))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXCoordinate))
					.addPreferredGap(ComponentPlacement.UNRELATED, 25, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYKordinataTacke))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(30)
							.addComponent(lblColor))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModify)
						.addComponent(btnCancel))
					.addGap(29))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}

	public Color chooseColor(Color lastColor) {
		Color newColor = JColorChooser.showDialog(null, "Choose color", lastColor);
		if (newColor != null) {
			return newColor;
		} else {
			return lastColor;
		}
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}
