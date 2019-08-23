package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DlgAddCircle extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	int tmpRadius = -1;
	private JTextField txtRadius;
	private JLabel lblNewLabel;
	private JButton btnDraw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgAddCircle dialog = new DlgAddCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgAddCircle() {
		setModal(true);
		setTitle("Add circle");
		setBounds(100, 100, 252, 253);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel = new JLabel("Radius");
		}
		{
			txtRadius = new JTextField();
			txtRadius.setColumns(10);
		}
		{
			//okButtnon clicked
			btnDraw = new JButton("Draw");
			btnDraw.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (txtRadius.getText().isEmpty()) {
						JOptionPane.showMessageDialog(getParent(), "Value can't be empty!", "Error!",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						try {
							tmpRadius = Integer.parseInt(txtRadius.getText());
						} catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(getParent(), "You must put number", "Error",
									JOptionPane.ERROR_MESSAGE);
							txtRadius.setText("");
							return;
						}
						if (tmpRadius <= 0) {
							JOptionPane.showMessageDialog(getParent(), "Radius must be grather than 0", "Error",
									JOptionPane.ERROR_MESSAGE);
							txtRadius.setText("");
							return;
						}
					}
					dispose();
				}
			});
			btnDraw.setActionCommand("OK");
			getRootPane().setDefaultButton(btnDraw);
		}
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(88)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(59)
							.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(70, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addGap(18)
					.addComponent(btnDraw)
					.addGap(46))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel)
					.addGap(38)
					.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDraw)
						.addComponent(btnCancel))
					.addGap(25))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public int getRadius() {
	
			return tmpRadius;
		
	}
}
