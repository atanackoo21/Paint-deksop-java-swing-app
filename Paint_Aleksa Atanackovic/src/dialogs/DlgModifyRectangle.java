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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgModifyRectangle extends JDialog {

	private final JPanel pnlModify = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtLength;
	private JTextField txtWidth;
	private JLabel lblWidth;
	private JLabel lblLength;
	private JButton btnBorderColor;
	private JLabel lblBorderColor;
	private JButton btnAreaColor;
	private JLabel lblBojaUnutrasnjosti;
	private JButton btnModify;
	private JButton btnCancel;
	private boolean confirm;
	private boolean check = false;

	private int tmpX, tmpY, widthRec, lengthRec;
	private Color borderColor, areaColor;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			DlgModifyRectangle dialog = new DlgModifyRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgModifyRectangle() {
		setTitle("Change rectangle");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 358, 349);
		getContentPane().setLayout(new BorderLayout());
		pnlModify.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlModify, BorderLayout.SOUTH);
		txtX = new JTextField();
		txtX.setColumns(10);
		txtY = new JTextField();
		txtY.setColumns(10);
		txtLength = new JTextField();
		txtLength.setColumns(10);
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		JLabel lblXCoordinate = new JLabel("x coordinate left point");
		JLabel lblYCoordinate = new JLabel("y coordinate left point");

		lblWidth = new JLabel("width");

		lblLength = new JLabel("length");

		btnBorderColor = new JButton("");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBorderColor.setBackground(chooseColor(btnBorderColor.getBackground()));
			}
		});

		lblBorderColor = new JLabel("border color");

		btnAreaColor = new JButton("");
		btnAreaColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAreaColor.setBackground(chooseColor(btnAreaColor.getBackground()));
			}
		});

		lblBojaUnutrasnjosti = new JLabel("Area color");

		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmpSX, tmpSY, tmpW, tmpL;
				if (txtWidth.getText().isEmpty() || txtX.getText().isEmpty() || txtY.getText().isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "Put value in all fields", "Error",
							JOptionPane.WARNING_MESSAGE);

				} else {
					try {
						 tmpSX = Integer.parseInt(getTxtX().getText());
						 tmpSY = Integer.parseInt(getTxtY().getText());
						 tmpW = Integer.parseInt(getTxtWidth().getText());
						 tmpL = Integer.parseInt(getTxtLength().getText());

					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(getParent(), "You must put number", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtX.setText("");
						txtY.setText("");
						txtWidth.setText("");
						txtLength.setText("");
						return;
					}
					if (tmpSX <= 0 || tmpSY <= 0 || tmpW <= 0 || tmpL <= 0) {
						JOptionPane.showMessageDialog(getParent(), "Values must be grather than 0", "Error",
								JOptionPane.ERROR_MESSAGE);
						txtX.setText("");
						txtY.setText("");
						txtWidth.setText("");
						txtLength.setText("");
						return;
					} else {
						setTmpX(tmpSX); 
						setTmpY(tmpSY);;
						setWidthRec(tmpW);
						setLengthRec(tmpL);
						setBorderColor(getBtnBorderColor().getBackground());
						setAreaColor(getBtnAreaColor().getBackground());
						setCheck(true);
					}
				}
				dispose();
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		GroupLayout gl_pnlModify = new GroupLayout(pnlModify);
		gl_pnlModify.setHorizontalGroup(
			gl_pnlModify.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlModify.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlModify.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlModify.createSequentialGroup()
							.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblXCoordinate))
						.addGroup(gl_pnlModify.createSequentialGroup()
							.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblYCoordinate))
						.addGroup(gl_pnlModify.createSequentialGroup()
							.addComponent(txtLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblLength))
						.addGroup(gl_pnlModify.createSequentialGroup()
							.addGroup(gl_pnlModify.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnAreaColor, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
								.addComponent(btnBorderColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
								.addComponent(txtWidth, Alignment.LEADING, 171, 171, 171)
								.addGroup(gl_pnlModify.createSequentialGroup()
									.addComponent(btnCancel)
									.addGap(18)))
							.addGroup(gl_pnlModify.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlModify.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_pnlModify.createParallelGroup(Alignment.LEADING)
										.addComponent(lblWidth)
										.addComponent(lblBojaUnutrasnjosti)
										.addComponent(lblBorderColor)))
								.addGroup(gl_pnlModify.createSequentialGroup()
									.addGap(2)
									.addComponent(btnModify)))))
					.addContainerGap(81, Short.MAX_VALUE))
		);
		gl_pnlModify.setVerticalGroup(
			gl_pnlModify.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlModify.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlModify.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXCoordinate))
					.addGap(18)
					.addGroup(gl_pnlModify.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYCoordinate))
					.addGap(18)
					.addGroup(gl_pnlModify.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLength))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlModify.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
					.addGap(18)
					.addGroup(gl_pnlModify.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBorderColor))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlModify.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAreaColor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBojaUnutrasnjosti))
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(gl_pnlModify.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnModify)
						.addComponent(btnCancel)))
		);
		pnlModify.setLayout(gl_pnlModify);
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public JTextField getTxtWidth() {
		return txtWidth ;
	}

	public JTextField getTxtLength() {
		return txtLength;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public JButton getBtnAreaColor() {
		return btnAreaColor;
	}

	public boolean isConfirm() {
		return confirm;
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

	public int getWidthRec() {
		return widthRec;
	}

	public void setWidthRec(int widthRec) {
		this.widthRec = widthRec;
	}

	public int getLengthRec() {
		return lengthRec;
	}

	public void setLengthRec(int lengthRec) {
		this.lengthRec = lengthRec;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
	
}
