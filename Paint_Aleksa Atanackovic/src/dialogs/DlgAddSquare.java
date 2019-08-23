package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DlgAddSquare extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtWidth;
	private int widthSquare = -1;
	private JLabel lblWidth;
	private JButton btnCancel;
	private JButton btnDraw;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgAddSquare dialog = new DlgAddSquare();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgAddSquare() {
		setModal(true);
		setTitle("Square");
		setBounds(100, 100, 304, 239);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblWidth = new JLabel("Width");
		}
		{
			txtWidth = new JTextField();
			txtWidth.setColumns(10);
		}
		{
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setActionCommand("Cancel");
		}
		{
			btnDraw = new JButton("Draw");
			btnDraw.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (txtWidth.getText().isEmpty()) {
						JOptionPane.showMessageDialog(getParent(), "Value can't be empty!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						try {
							widthSquare = Integer.parseInt(txtWidth.getText());
						} catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(getParent(), "You must put integer number", "Error",
									JOptionPane.ERROR_MESSAGE);
							txtWidth.setText("");
							return;
						}
						if (widthSquare <= 0) {
							JOptionPane.showMessageDialog(getParent(), "Width must be grather than 0",
									"Error", JOptionPane.ERROR_MESSAGE);
							txtWidth.setText("");
							return;
						}
						dispose();
					}
				}
			});
			btnDraw.setActionCommand("OK");
			getRootPane().setDefaultButton(btnDraw);
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(59)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnCancel)
							.addGap(29)
							.addComponent(btnDraw))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblWidth)
							.addGap(33)
							.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(62)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnDraw, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(19))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

	public int getWidthSquare() {
		return widthSquare;
	}

	public void setWidthSquare(int widthSquare) {
		this.widthSquare = widthSquare;
	}

}
