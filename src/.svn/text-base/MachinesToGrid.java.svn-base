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
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;

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
public class MachinesToGrid extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList available;
	private JLabel lblAvailable;
	private JList currentlyApplied;
	private JLabel lblApplied;
	private AbstractAction refreshClicked;
	private JButton btnRefresh;
	private AbstractAction comboChanged;
	private JScrollPane jScrollPane2;
	private JButton btnRemoveSelected;
	private AbstractAction removeClicked;
	private AbstractAction applyClicked;
	private JButton btnApplySelected;
	private JScrollPane jScrollPane1;
	private JLabel lblWhich;
	private JComboBox applyToCombo;
	
	private DefaultComboBoxModel availableModel;
	private DefaultComboBoxModel applyToComboModel;
	private DefaultComboBoxModel currentlyAppliedModel;
	
	private GridData data;
	
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	/*public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MachinesToGrid inst = new MachinesToGrid();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}*/
	
	public MachinesToGrid(GridData d) {
		super();
		initGUI();
		data = d;
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jScrollPane1 = new JScrollPane();
				{
					availableModel = new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					available = new JList();
					jScrollPane1.setViewportView(available);
					available.setModel((ListModel)availableModel);
				}
			}
			{
				lblAvailable = new JLabel();
				lblAvailable.setText("Available Machines:");
			}
			{
				applyToComboModel = new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
				applyToCombo = new JComboBox();
				applyToCombo.setModel((ComboBoxModel)applyToComboModel);
				applyToCombo.setAction(getComboChanged());
			}
			{
				jScrollPane2 = new JScrollPane();
				{
					currentlyAppliedModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					currentlyApplied = new JList();
					jScrollPane2.setViewportView(currentlyApplied);
					currentlyApplied.setModel((ListModel)currentlyAppliedModel);
				}
			}
			{
				lblApplied = new JLabel();
				lblApplied.setText("Machines In This Grid:");
			}
			{
				lblWhich = new JLabel();
				lblWhich.setText("Which Grid to Add To:");
			}
			{
				btnApplySelected = new JButton();
				btnApplySelected.setText(">");
				btnApplySelected.setAction(getApplyClicked());
			}
			{
				btnRemoveSelected = new JButton();
				btnRemoveSelected.setText("<");
				btnRemoveSelected.setAction(getRemoveClicked());
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(lblAvailable, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(lblApplied, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(23)
				        .addComponent(lblWhich, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addComponent(applyToCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addGap(20)
				        .addComponent(btnApplySelected, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addGap(20)
				        .addComponent(btnRemoveSelected, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addComponent(getBtnRefresh(), GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 15, Short.MAX_VALUE)))
				.addContainerGap(88, 88));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(lblAvailable, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
				        .addGap(18)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(lblWhich, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
				    .addComponent(applyToCombo, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(17)
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(getBtnRefresh(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
				            .addGroup(thisLayout.createSequentialGroup()
				                .addGap(20)
				                .addGroup(thisLayout.createParallelGroup()
				                    .addComponent(btnApplySelected, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				                    .addComponent(btnRemoveSelected, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
				                .addGap(22)))
				        .addGap(18)))
				.addGap(18)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(lblApplied, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 7, Short.MAX_VALUE)))
				.addContainerGap(19, 19));

			pack();
			this.setSize(538, 353);
			
			refresh();

		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private AbstractAction getApplyClicked() {
		if(applyClicked == null) {
			applyClicked = new AbstractAction(">", null) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
					// get the selected items
					int indicies[] = available.getSelectedIndices();
					GridComputer tempComputer;
					Grid tempGrid = (Grid)applyToComboModel.getSelectedItem();
					
					for (int i = 0; i < indicies.length; i++)
					{
						tempComputer = (GridComputer)availableModel.getElementAt(indicies[i]);
						tempComputer.assignTo(tempGrid);
						
					}
						
					update();
				}
			};
		}
		return applyClicked;
	}
	
	private AbstractAction getRemoveClicked() {
		if(removeClicked == null) {
			removeClicked = new AbstractAction("<", null) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
					
					// get the selected items
					int indicies[] = currentlyApplied.getSelectedIndices();
					GridComputer temp;
					for (int i = 0; i < indicies.length; i++)
					{
						temp = (GridComputer)currentlyAppliedModel.getElementAt(indicies[i]);
						temp.unassign();
					}
						
					update();
					
				}
			};
		}
		return removeClicked;
	}
	
	/*
	 * update all the fields/windows.
	 * Unselect everything.
	 * 
	 * Done when making this window visible, or by buttonclick.
	 */
	public void refresh()
	{
		// dump all data, then fill.
		availableModel.removeAllElements();
		currentlyAppliedModel.removeAllElements();
		applyToComboModel.removeAllElements();
		
		// Restore all the data
		Object[] in;
		if (data != null)
		{
			in = data.getUnassignedMachines();
			for (int i = 0; i < in.length; i++)
				availableModel.addElement(in[i]);
			
			in = data.getGrids();
			for (int i = 0; i < in.length; i++)
				applyToComboModel.addElement(in[i]);
			
		}
		
	}
	
	private void update()
	{
		// dump all data, then fill.
		availableModel.removeAllElements();
		currentlyAppliedModel.removeAllElements();
		
		// Restore all the data
		Object[] in;
		in = data.getUnassignedMachines();
		for (int i = 0; i < in.length; i++)
			availableModel.addElement(in[i]);
		in = data.getGridMachines((Grid)applyToComboModel.getSelectedItem());
		for (int i = 0; i < in.length; i++)
			currentlyAppliedModel.addElement(in[i]);
	}
	
	private AbstractAction getComboChanged() {
		if(comboChanged == null) {
			comboChanged = new AbstractAction("", null) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
					// update the right box
					currentlyAppliedModel.removeAllElements();
					
					// get the highlighted item
					//Grid g = (Grid)currentlyAppliedModel.getSelectedItem();
					JComboBox cb = (JComboBox) evt.getSource();
					//System.out.println("Grid is now " + g + "  " + cb.getSelectedIndex() + " " + cb.getSelectedItem());
					
					if (data != null)
					{	
						Object[] in = data.getGridMachines((Grid)cb.getSelectedItem());
						for (int i =0 ;i < in.length; i++)
							currentlyAppliedModel.addElement(in[i]);
					}
				}
			};
		}
		return comboChanged;
	}
	
	private JButton getBtnRefresh() {
		if(btnRefresh == null) {
			btnRefresh = new JButton();
			btnRefresh.setText("Refresh");
			btnRefresh.setAction(getRefreshClicked());
		}
		return btnRefresh;
	}
	
	private AbstractAction getRefreshClicked() {
		if(refreshClicked == null) {
			refreshClicked = new AbstractAction("Refresh", null) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
					refresh();
				}
			};
		}
		return refreshClicked;
	}

}
