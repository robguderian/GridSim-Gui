import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class AddGrid extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton cancelButton;
	private JScrollPane jScrollPane1;
	private JButton deleteSelected;
	private AbstractAction addClicked;
	private AbstractAction deleteClicked;
	private JLabel lblExistingUsers;
	private JList userList;
	private JButton addUser;
	private JLabel lblUserName;
	private JTextField txtNewName;
	private JButton okButton;
	private AbstractAction applyClicked;
	private AbstractAction closeAction;
	
	private GridData data;
	
	private DefaultComboBoxModel userListModel; // the listbox controller

	
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	/*public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AddUser inst = new AddUser(null);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}*/
	
	public AddGrid(GridData data) {
		super();
		this.data = data;
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				cancelButton = new JButton();
				cancelButton.setText("Cancel");
				cancelButton.setAction(getCloseAction());
			}
			{
				okButton = new JButton();
				okButton.setText("Apply");
				okButton.setAction(getApplyClicked());
			}
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(38, 38)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(getJScrollPane1(), GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
				        .addGap(12)
				        .addComponent(getDeleteSelected(), GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(getLblExistingUsers(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
				            .addComponent(getLblUserName(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
				        .addComponent(getTxtNewName(), GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(thisLayout.createSequentialGroup()
				                .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
				            .addGroup(thisLayout.createSequentialGroup()
				                .addPreferredGap(okButton, getAddUser(), LayoutStyle.ComponentPlacement.INDENT)
				                .addComponent(getAddUser(), GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap(6, 6));
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(7, 7)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(getTxtNewName(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getLblUserName(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getAddUser(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(20)
				.addComponent(getLblExistingUsers(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(12)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(getJScrollPane1(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(87)
				        .addComponent(getDeleteSelected(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(60)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(okButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(11, 11));
			thisLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {cancelButton, okButton});
			{
		
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private AbstractAction getCloseAction() {
		if(closeAction == null) {
			closeAction = new AbstractAction("Close", null) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
					setVisible(false);
				}
			};
		}
		return closeAction;
	}
	
	private AbstractAction getApplyClicked() {
		if(applyClicked == null) {
			applyClicked = new AbstractAction("Apply", null) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
					setVisible(false);
				}
			};
		}
		return applyClicked;
	}
	
	public JTextField getTxtNewName() {
		if(txtNewName == null) {
			txtNewName = new JTextField();
			txtNewName.setText("");
			
		}
		return txtNewName;
	}
	
	private JLabel getLblUserName() {
		if(lblUserName == null) {
			lblUserName = new JLabel();
			lblUserName.setText("New Grid Name");
		}
		return lblUserName;
	}
	
	private JButton getAddUser() {
		if(addUser == null) {
			addUser = new JButton();
			addUser.setText("Add");
			addUser.setAction(getAddClicked());
		}
		return addUser;
	}
	
	public JList getUserList() {
		if(userList == null) {
			
			// get a list of all the users
			
			userListModel = new DefaultComboBoxModel(data.getGrids());
			userList = new JList();
			userList.setModel((ListModel)userListModel);
			
			
		}
		return userList;
	}
	
	private JLabel getLblExistingUsers() {
		if(lblExistingUsers == null) {
			lblExistingUsers = new JLabel();
			lblExistingUsers.setText("Existing Users");
		}
		return lblExistingUsers;
	}
	
	private JButton getDeleteSelected() {
		if(deleteSelected == null) {
			deleteSelected = new JButton();
			deleteSelected.setText("Delete Selected Grid");
			deleteSelected.setAction(getDeleteClicked());
		}
		return deleteSelected;
	}
	
	private AbstractAction getDeleteClicked() {
		if(deleteClicked == null) {
			deleteClicked = new AbstractAction("Delete Selected Grid", null) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
					// TODO
					/*
					 * Delete the selected user. from the list
					 */
					int []indicies = userList.getSelectedIndices();
					
					// Go backwards to not mess up existing indicies
					for ( int i = indicies.length-1 ; i >= 0 ; i --)
					{
						//JOptionPane.showMessageDialog(null, "Index: "+ indicies[i] + " " + userListModel.getElementAt(indicies[i]).toString());
						if (data.deleteGrid(userListModel.getElementAt(indicies[i])))
						{
							userListModel.removeElementAt(indicies[i]);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Grid Not deleted for some reason");
						}
						
					}
				}
			};
		}
		return deleteClicked;
	}
	
	private AbstractAction getAddClicked() {
		if(addClicked == null) {
			addClicked = new AbstractAction("Add", null) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
					
					/*
					 * Grab the data from the box in front of add.
					 * Insert it into the users 'database'
					 * Update the listbox.
					 */
					if (txtNewName.getText().length() > 0)
					{
						if (data.addGrid(txtNewName.getText()))
						{
							refresh();
						
							// clear the textbox
							txtNewName.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Add failed - does the grid name already exist?");
						}
					}
				}
			};
		}
		return addClicked;
	}
	
	private JScrollPane getJScrollPane1() {
		if(jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getUserList());
		}
		return jScrollPane1;
	}
	
	public void refresh()
	{
		//userListModel.addElement(txtNewName.getText());
		userListModel.removeAllElements();
		
		Grid[] g = data.getGrids();
		for (int i = 0; i < g.length; i++)
			userListModel.addElement(g[i]);
	}

}

