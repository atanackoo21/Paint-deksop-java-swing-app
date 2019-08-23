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

public class DlgModifyLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartPointX;
	private JTextField txtStartPointY;
	private JTextField txtEndPointX;
	private JTextField txtEndPointY;
	private JButton btnColor;
	private boolean confirm;
	private int tmpStartX, tmpStartY, tmpEndX, tmpEndY;
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
			DlgModifyLine dialog = new DlgModifyLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgModifyLine() {
		setTitle("Modify line");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 385, 352);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		txtStartPointX = new JTextField();
		txtStartPointX.setColumns(10);
		JLabel lblXCoordinate = new JLabel("x coordinate for start point");
		txtStartPointY = new JTextField();
		txtStartPointY.setColumns(10);
		JLabel lblYCoordinate = new JLabel("y coordinate for start point");
		txtEndPointX = new JTextField();
		txtEndPointX.setColumns(10);
		JLabel lblXCoordinateEndPoint = new JLabel("x coordinate for end point");
		txtEndPointY = new JTextField();
		txtEndPointY.setColumns(10);
		JLabel lblYCoordinateEndPoint = new JLabel("y coordinate for end point");
		btnColor = new JButton("");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnColor.setBackground(chooseColor(btnColor.getBackground()));
			}
		});
		JLabel lblBorderColor = new JLabel("Border color");
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmpEX, tmpEY, tmpSX, tmpSY;
				
				if (getTxtStartPointX().getText().isEmpty() || getTxtStartPointY().getText().isEmpty()
						|| getTxtEndPointY().getText().isEmpty() || getTxtEndPointY().getText().isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "Field can't be empty.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						 tmpSX = Integer.parseInt(getTxtStartPointX().getText());
						 tmpSY = Integer.parseInt(getTxtStartPointY().getText());
						 tmpEX = Integer.parseInt(getTxtEndPointX().getText());
						 tmpEY = Integer.parseInt(getTxtEndPointY().getText());
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(getParent(), "You must put number", "Error",
								JOptionPane.ERROR_MESSAGE);
						 txtStartPointX.setText("");
						 txtStartPointY.setText("");
						 txtEndPointX.setText("");
						 txtEndPointY.setText("");
						return;
					}
					if (tmpSX <= 0 || tmpSY <= 0 || tmpEX <= 0 || tmpEY <= 0) {
						JOptionPane.showMessageDialog(getParent(), "Values must be grather than 0", "Error",
								JOptionPane.ERROR_MESSAGE);
						 txtStartPointX.setText("");
						 txtStartPointY.setText("");
						 txtEndPointX.setText("");
						 txtEndPointY.setText("");
						return;
					} else {
						setTmpStartX(tmpSX); 
						setTmpStartY(tmpSY);
						setTmpEndX(tmpEX);
						setTmpEndY(tmpEY);
						setColor(getBtnColor().getBackground());
						setCheck(true);
					}
				}
				dispose();
			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
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
								.addComponent(btnColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtEndPointY)
								.addComponent(txtEndPointX)
								.addComponent(txtStartPointY)
								.addComponent(txtStartPointX, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
							.addGap(27))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addContainerGap(71, Short.MAX_VALUE)
							.addComponent(btnCancel)
							.addGap(42)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblXCoordinate)
						.addComponent(lblYCoordinate)
						.addComponent(lblXCoordinateEndPoint)
						.addComponent(lblYCoordinateEndPoint)
						.addComponent(lblBorderColor)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnModify)))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtStartPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXCoordinate))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtStartPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYCoordinate))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEndPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXCoordinateEndPoint))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEndPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYCoordinateEndPoint))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBorderColor))
					.addGap(28)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnModify))
					.addGap(13))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

	public JTextField getTxtStartPointX() {
		return txtStartPointX;
	}

	public JTextField getTxtStartPointY() {
		return txtStartPointY;
	}

	public JTextField getTxtEndPointX() {
		return txtEndPointX;
	}

	public JTextField getTxtEndPointY() {
		return txtEndPointY;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public Color chooseColor(Color lastColor) {
		Color newColor = JColorChooser.showDialog(null, "Choose color", lastColor);
		if (newColor != null) {
			return newColor;
		} else {
			return lastColor;
		}
	}
	

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getTmpStartX() {
		return tmpStartX;
	}

	public void setTmpStartX(int tmpStartX) {
		this.tmpStartX = tmpStartX;
	}

	public int getTmpStartY() {
		return tmpStartY;
	}

	public void setTmpStartY(int tmpStartY) {
		this.tmpStartY = tmpStartY;
	}

	public int getTmpEndX() {
		return tmpEndX;
	}

	public void setTmpEndX(int tmpEndX) {
		this.tmpEndX = tmpEndX;
	}

	public int getTmpEndY() {
		return tmpEndY;
	}

	public void setTmpEndY(int tmpEndY) {
		this.tmpEndY = tmpEndY;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
	

}
