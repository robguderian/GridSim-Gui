

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
public class AddMachine extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton cancelButton;
	private JButton btnAdd;
	private JTextField txtMemSize;
	private JLabel lblMemSize;
	private AbstractAction addClicked;
	private AbstractAction deleteClicked;
	private JButton btnDeleteSelected;
	private JScrollPane jScrollPane1;
	private JLabel lblExistingJobs;
	private JList machinesDisplay;
	private JLabel lblInstructions;
	private JLabel lblsi;
	private JLabel lblSE;
	private JTextField txtNumMachines;
	private JTextField txtNumProcs;
	private JTextField txtNumInstructions;
	private JLabel lblName;
	private JTextField txtMachineName;
	private JButton okButton;
	private AbstractAction applyClicked;
	private AbstractAction closeAction;
	
	private DefaultComboBoxModel machinesDisplayModel;
	
	private GridData data;


	/**
	* Auto-generated main method to display this JFrame
	*/
	/*public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AddJob inst = new AddJob();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}*/
	
	public AddMachine(GridData data) {
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
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(getTxtMachineName(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getLblName(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getLblExistingJobs(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				            .addComponent(getTxtNumInstructions(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(getLblInstructions(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				            .addComponent(getTxtNumProcs(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(getLblsi(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				            .addComponent(getJTextField1(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(getLblSE(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, GroupLayout.PREFERRED_SIZE)
				        .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				            .addComponent(getLblMemSize(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(getTxtMemSize(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addGap(0, 19, GroupLayout.PREFERRED_SIZE)
				        .addComponent(getBtnAdd(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getJScrollPane1(), GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
				        .addGap(6)))
				.addComponent(getBtnDeleteSelected(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(0, 36, GroupLayout.PREFERRED_SIZE)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(okButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getLblSE(), GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
				        .addGap(6))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getLblsi(), GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
				        .addGap(6))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getLblInstructions(), GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
				        .addGap(6))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getLblName(), GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
				        .addGap(101))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(0, 0, Short.MAX_VALUE)
				        .addComponent(getLblMemSize(), GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(getJTextField1(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getTxtNumProcs(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getTxtNumInstructions(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getBtnAdd(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getTxtMachineName(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getTxtMemSize(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
				.addGap(94)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getJScrollPane1(), GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
				        .addGap(23))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getLblExistingJobs(), GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
				        .addGap(41))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getBtnDeleteSelected(), GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
				        .addGap(23))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(92)
				        .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE));
			thisLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {cancelButton, okButton});
			{
		
			}
			pack();
			this.setSize(554, 300);
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
	
	private JTextField getTxtMachineName() {
		if(txtMachineName == null) {
			txtMachineName = new JTextField();
		}
		return txtMachineName;
	}
	
	private JLabel getLblName() {
		if(lblName == null) {
			lblName = new JLabel();
			lblName.setText("Name:");
		}
		return lblName;
	}
	
	private JTextField getTxtNumInstructions() {
		if(txtNumInstructions == null) {
			txtNumInstructions = new JTextField();
		}
		return txtNumInstructions;
	}
	
	private JLabel getLblInstructions() {
		if(lblInstructions == null) {
			lblInstructions = new JLabel();
			lblInstructions.setText("# of Instructions/sec:");
		}
		return lblInstructions;
	}
	
	private JTextField getTxtNumProcs() {
		if(txtNumProcs == null) {
			txtNumProcs = new JTextField();
		}
		return txtNumProcs;
	}
	
	private JLabel getLblsi() {
		if(lblsi == null) {
			lblsi = new JLabel();
			lblsi.setText("Number of Processors:");
		}
		return lblsi;
	}
	
	private JTextField getJTextField1() {
		if(txtNumMachines == null) {
			txtNumMachines = new JTextField();
		}
		return txtNumMachines;
	}
	
	private JLabel getLblSE() {
		if(lblSE == null) {
			lblSE = new JLabel();
			lblSE.setText("Number of Machines:");
		}
		return lblSE;
	}
	
	private JButton getBtnAdd() {
		if(btnAdd == null) {
			btnAdd = new JButton();
			btnAdd.setText("Add");
			btnAdd.setAction(getAddClicked());
		}
		return btnAdd;
	}
	
	private JList getMachinesDisplay() {
		if(machinesDisplay == null) {
			machinesDisplayModel = 
				new DefaultComboBoxModel();
			machinesDisplay = new JList();
			machinesDisplay.setModel((ListModel)machinesDisplayModel);
		}
		return machinesDisplay;
	}
	
	private JLabel getLblExistingJobs() {
		if(lblExistingJobs == null) {
			lblExistingJobs = new JLabel();
			lblExistingJobs.setText("Existing Machines");
		}
		return lblExistingJobs;
	}
	
	private JScrollPane getJScrollPane1() {
		if(jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getMachinesDisplay());
		}
		return jScrollPane1;
	}
	
	private JButton getBtnDeleteSelected() {
		if(btnDeleteSelected == null) {
			btnDeleteSelected = new JButton();
			btnDeleteSelected.setText("Delete Selected");
			btnDeleteSelected.setAction(getDeleteClicked());
		}
		return btnDeleteSelected;
	}
	
	private AbstractAction getDeleteClicked() {
		if(deleteClicked == null) {
			deleteClicked = new AbstractAction("Delete Selected", null) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
					//machinesDisplayModel
					int []indicies = machinesDisplay.getSelectedIndices();
					
					// Go backwards to not mess up existing indicies
					for ( int i = indicies.length-1 ; i >= 0 ; i --)
					{
						//JOptionPane.showMessageDialog(null, "Index: "+ indicies[i] + " " + userListModel.getElementAt(indicies[i]).toString());
						if (data.deleteMachine(machinesDisplayModel.getElementAt(indicies[i])))
						{
							machinesDisplayModel.removeElementAt(indicies[i]);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Machine Not deleted for some reason");
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
					// try to add the job
					
					// TODO: do some verification
					int numInst = Integer.parseInt(txtNumInstructions.getText().toString());
					int numProc = Integer.parseInt(txtNumProcs.getText().toString());
					int numMachines = Integer.parseInt(txtNumMachines.getText().toString());
					int memSize = Integer.parseInt(txtMemSize.getText().toString());
					
					//if (data.addJob(numInst, begSize, endSize, txtMachineName.getText().toString()))
					if (data.addMachine(txtMachineName.getText(), numMachines, numProc, numInst, memSize))
					{
						// add the job to the display						
						//machinesDisplayModel.addElement(txtMachineName.getText().toString());
						refresh();
						// clean up
						txtMachineName.setText("");
						txtNumProcs.setText("");
						txtNumMachines.setText("");
						txtNumInstructions.setText("");
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Machine could not be added");
					}
				}
			};
		}
		return addClicked;
	}

	public void refresh()
	{
		machinesDisplayModel.removeAllElements();
		GridComputer[] c = data.getAllMachines();
		for(int i = 0 ; i<c.length; i++)
			machinesDisplayModel.addElement(c[i]);
	}
	
	private JLabel getLblMemSize() {
		if(lblMemSize == null) {
			lblMemSize = new JLabel();
			lblMemSize.setText("Amount of Ram (MB):");
		}
		return lblMemSize;
	}
	
	private JTextField getTxtMemSize() {
		if(txtMemSize == null) {
			txtMemSize = new JTextField();
		}
		return txtMemSize;
	}
}

