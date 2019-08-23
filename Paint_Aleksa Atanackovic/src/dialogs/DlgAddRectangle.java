package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgAddRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtHeight;
	private JTextField txtWidth;
	private JButton btnDraw;
	private JButton cancelButton;
	private int width=-1;
	private int height=-1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgAddRectangle dialog = new DlgAddRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgAddRectangle() {
		setModal(true);
		setTitle("Draw rectangle");
		setBounds(100, 100, 250, 301);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblLength = new JLabel("length");
		lblLength.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		
		JLabel lblWidth = new JLabel("width");
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblWidth, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblLength, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLength)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWidth)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(84, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDraw = new JButton("Draw");
				btnDraw.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtWidth.getText().isEmpty() || txtHeight.getText().isEmpty()) {
							JOptionPane.showMessageDialog(getParent(), "Value can't be empty!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							try {
								height = Integer.parseInt(txtHeight.getText());
							} catch (NumberFormatException nfe) {
								JOptionPane.showMessageDialog(getParent(), "Height must be integer number !",
										"Error", JOptionPane.ERROR_MESSAGE);
								txtHeight.setText("");
								return;
							}
							try{
								width = Integer.parseInt(txtWidth.getText());
							} catch(NumberFormatException nfe){
								JOptionPane.showMessageDialog(getParent(), "Width must be integer number",
										"Error", JOptionPane.ERROR_MESSAGE);
								txtWidth.setText("");
								return;
							}
							if(height <= 0){
								JOptionPane.showMessageDialog(getParent(), "Height must be grather than 0");
								txtHeight.setText("");
								return;
							} else if(width <= 0){
								JOptionPane.showMessageDialog(getParent(), "Width must be grather than 0");	
								txtWidth.setText("");
								return;
							}
						} 

						dispose();

					}
				});
				getRootPane().setDefaultButton(btnDraw);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(50)
						.addComponent(cancelButton)
						.addGap(18)
						.addComponent(btnDraw)
						.addGap(61))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(cancelButton)
							.addComponent(btnDraw))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	public int getRecWidth() {
		return width;
	}

	public int getRecHeight() {
		return height;
	}

	
}
