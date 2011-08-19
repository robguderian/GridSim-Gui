

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class AddJob extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton cancelButton;
	private JButton btnAdd;
	private JTextField txtGrowth;
	private JLabel lblGrowth;
	private JLabel lblGrowthRate;
	private JComboBox cboGrowthRate;
	private JLabel jLabel1;
	private JTextField txtCount;
	private AbstractAction addClicked;
	private AbstractAction deleteClicked;
	private JButton btnDeleteSelected;
	private JScrollPane jScrollPane1;
	private JLabel lblExistingJobs;
	private JList machinesDisplay;
	private JLabel lblInstructions;
	private JLabel lblsi;
	private JLabel lblSE;
	private JTextField txtEndInstructions;
	private JTextField txtBeginningSize;
	private JTextField txtNumInstructions;
	private JLabel lblName;
	private JTextField txtJobName;
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
	
	public AddJob(GridData data) {
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
				    .addComponent(getTxtJobName(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getLblName(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getLblExistingJobs(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				            .addComponent(getTxtNumInstructions(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(getLblInstructions(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				            .addComponent(getTxtBeginningSize(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(getLblsi(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				            .addComponent(getJTextField1(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(getLblSE(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				            .addComponent(getTxtCount(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(getJLabel1(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(getLblGrowthRate(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				                .addGap(7))
				            .addComponent(getCboGrowthRate(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
				    .addComponent(getJScrollPane1(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(getBtnDeleteSelected(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getLblGrowth(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getTxtGrowth(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 1, Short.MAX_VALUE)
				.addComponent(getBtnAdd(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(okButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(getLblGrowthRate(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getJLabel1(), GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 17, GroupLayout.PREFERRED_SIZE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getLblSE(), GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 8, GroupLayout.PREFERRED_SIZE))
				    .addComponent(getLblsi(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getLblInstructions(), GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 17, GroupLayout.PREFERRED_SIZE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getLblName(), GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 84, GroupLayout.PREFERRED_SIZE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getLblGrowth(), GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 35, GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getBtnAdd(), GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				        .addGap(56))
				    .addComponent(getCboGrowthRate(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getTxtCount(), GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				        .addGap(56))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getJTextField1(), GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				        .addGap(56))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getTxtBeginningSize(), GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				        .addGap(56))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getTxtNumInstructions(), GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				        .addGap(56))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getTxtJobName(), GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				        .addGap(56))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getTxtGrowth(), GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 56, Short.MAX_VALUE)))
				.addGap(38)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getBtnDeleteSelected(), GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
				        .addGap(23))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getJScrollPane1(), GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
				        .addGap(23))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(getLblExistingJobs(), GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
				        .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addContainerGap());
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
	
	private JTextField getTxtJobName() {
		if(txtJobName == null) {
			txtJobName = new JTextField();
		}
		return txtJobName;
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
			lblInstructions.setText("# of Instructions:");
		}
		return lblInstructions;
	}
	
	private JTextField getTxtBeginningSize() {
		if(txtBeginningSize == null) {
			txtBeginningSize = new JTextField();
		}
		return txtBeginningSize;
	}
	
	private JLabel getLblsi() {
		if(lblsi == null) {
			lblsi = new JLabel();
			lblsi.setText("Size of data - Start:");
		}
		return lblsi;
	}
	
	private JTextField getJTextField1() {
		if(txtEndInstructions == null) {
			txtEndInstructions = new JTextField();
		}
		return txtEndInstructions;
	}
	
	private JLabel getLblSE() {
		if(lblSE == null) {
			lblSE = new JLabel();
			lblSE.setText("Size of data - end:");
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
			lblExistingJobs.setText("Existing Jobs");
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
						if (data.deleteJob(machinesDisplayModel.getElementAt(indicies[i])))
						{
							machinesDisplayModel.removeElementAt(indicies[i]);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Job Not deleted for some reason");
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
					int begSize = Integer.parseInt(txtBeginningSize.getText().toString());
					int endSize = Integer.parseInt(txtEndInstructions.getText().toString());
					int count = Integer.parseInt(txtCount.getText().toString());
					double growth = Double.parseDouble(txtGrowth.getText().toString());
					String growthRate = cboGrowthRate.getSelectedItem().toString();
					if (data.addJob(numInst, begSize, endSize, txtJobName.getText().toString(), growthRate,count))
					{						
						// add the job to the display
						//machinesDisplayModel.addElement(txtJobName.getText().toString());
						
						refresh();
						
						// clean up
						txtJobName.setText("");
						txtBeginningSize.setText("");
						txtEndInstructions.setText("");
						txtNumInstructions.setText("");
						txtCount.setText("");
						txtGrowth.setText("");
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Job could not be added");
					}
				}
			};
		}
		return addClicked;
	}
	
	public void refresh()
	{
		GridJob[] g = data.getAllJobs();
		machinesDisplayModel.removeAllElements();
		for(int i = 0 ; i<g.length; i++)
			machinesDisplayModel.addElement(g[i]);
	}
	
	private JTextField getTxtCount() {
		if(txtCount == null) {
			txtCount = new JTextField();
		}
		return txtCount;
	}
	
	private JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Number of Jobs");
		}
		return jLabel1;
	}
	
	private JComboBox getCboGrowthRate() {
		if(cboGrowthRate == null) {
			ComboBoxModel cboGrowthRateModel = 
				new DefaultComboBoxModel(
						new String[] { "None", "Linear x to y", "Linear", "Exponential", "Factorial", "Bell", "-|x| Curve" });
			cboGrowthRate = new JComboBox();
			cboGrowthRate.setModel(cboGrowthRateModel);
		}
		return cboGrowthRate;
	}
	
	private JLabel getLblGrowthRate() {
		if(lblGrowthRate == null) {
			lblGrowthRate = new JLabel();
			lblGrowthRate.setText("Growth Rate:");
		}
		return lblGrowthRate;
	}
	
	private JLabel getLblGrowth() {
		if(lblGrowth == null) {
			lblGrowth = new JLabel();
			lblGrowth.setText("Growth Factor");
		}
		return lblGrowth;
	}
	
	private JTextField getTxtGrowth() {
		if(txtGrowth == null) {
			txtGrowth = new JTextField();
		}
		return txtGrowth;
	}

}
