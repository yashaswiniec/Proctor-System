package cit.edu.pms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cit.edu.pms.entity.Proctor;
import cit.edu.pms.service.AdminService;

public class StudentUpdatePanel extends JPanel{
	
	private JComboBox<String> combopreSem;
	private JComboBox<String> combonextSem;
	private JComboBox<String> combodept;
	private JLabel lblold, lblnew, lbl,lbldept;
	private JButton update;
	private String psem, nsem;
	private Vector department;
	private String[] semester={"1","2","3","4","5","6","7","8"};
	private String dept;
	AdminService service = new AdminService();
	private int proctrID;
	
	public StudentUpdatePanel(final JFrame frame)
	{
		setLayout(null);
		lbl = new JLabel("** Do you want to move students to next sem? Please select below fields and click on update");
		lbl.setBounds(20,50,700,30);
		lbl.setFont(new Font("Serif", Font.BOLD, 15));
		add(lbl);
		lbldept = new JLabel("Select Department:");
		lbldept.setBounds(100,150,200,30);
		add(lbldept);
		lblold = new JLabel("Select previous sem:");
		lblold.setBounds(100,200,200,30);
		add(lblold);
		lblnew = new JLabel("Select next sem:");
		lblnew.setBounds(100, 250, 200, 30);
		add(lblnew);
		
		update = new JButton("Update");
		update.setBounds(120, 300, 120, 30);
		add(update);
		
		
		department= service.getProctorDept();
		combodept = new JComboBox(department);
		combodept.setBounds(250, 150, 120, 30);
		add(combodept);
		combodept.setSelectedIndex(-1);
		combodept.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				if(ie.getStateChange()==ItemEvent.SELECTED)
				{
					dept=(String) combodept.getSelectedItem();
				}
			}
		});
		
		combopreSem = new JComboBox<String>(semester);
		combopreSem.setBounds(250,200,120,30);
		add(combopreSem);
		combopreSem.setSelectedIndex(-1);
		combopreSem.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				if(ie.getStateChange()==ItemEvent.SELECTED)
				{
					psem = (String) combopreSem.getSelectedItem();
				}
				
			}
		});
		combonextSem = new JComboBox<String>(semester);
		combonextSem .setBounds(250,250,120,30);
		add(combonextSem );
		combonextSem .setSelectedIndex(-1);
		combonextSem .addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				if(ie.getStateChange()==ItemEvent.SELECTED)
				{
					nsem = (String) combonextSem .getSelectedItem();
				}
				
			}
		});
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String assgn = "no";
				proctrID = service.getProctorID(Integer.parseInt(psem));
				
				service.updatestudentSem(Integer.parseInt(psem), Integer.parseInt(nsem), dept,assgn,proctrID);
				JOptionPane.showMessageDialog(null,"Student Record updated Successfully","Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
	}

}
