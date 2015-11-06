package cit.edu.pms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cit.edu.pms.entity.Proctor;
import cit.edu.pms.service.AdminService;

public class ProctorAlterPanel extends JPanel{
	
	private JLabel preLabel, alterLabel;
	private JButton btnSubmit;
	private JComboBox comboProctor,proctorcombo;
	private JPanel panel;
	private Vector proctorName;
	AdminService record = new AdminService();	
	private String proctorOld, proctorNew;
	Proctor proctorAlter;
	private int pID;
	
	public ProctorAlterPanel(final JFrame frame)
	{
		 setVisible(true);
		 setLayout(null);
		 preLabel= new JLabel("Select Old proctor:");
		 preLabel.setFont(new Font("Serif", Font.BOLD, 15));
		 preLabel.setBounds(250, 200, 200, 30);
		 add(preLabel);
		 alterLabel = new JLabel("Select to Alter:");
		 alterLabel.setBounds(250, 250, 200, 30);
		 alterLabel.setFont(new Font("Serif", Font.BOLD, 15));
		 add(alterLabel);
		 
		 proctorName = record.getallproctor();
			comboProctor=new JComboBox( proctorName);		
			comboProctor.setBounds(375,200, 120, 30);
			comboProctor.setSelectedIndex(-1);
			comboProctor.addItemListener(new ItemListener() {			
				@Override
				public void itemStateChanged(ItemEvent ie) {
					if(ie.getStateChange()==ItemEvent.SELECTED)
					{
						int index = comboProctor.getSelectedIndex();
						proctorAlter=(Proctor) proctorName.get(index);
						proctorOld= proctorAlter.getProctorName();
										
					}
				}
			});	
			add(comboProctor);
			proctorcombo=new JComboBox( proctorName);		
			proctorcombo.setBounds(375,250, 120, 30);
			proctorcombo.setSelectedIndex(-1);
			proctorcombo.addItemListener(new ItemListener() {			
				@Override
				public void itemStateChanged(ItemEvent ie) {
					if(ie.getStateChange()==ItemEvent.SELECTED)
					{
					
						int index = proctorcombo.getSelectedIndex();
						proctorAlter=(Proctor) proctorName.get(index);
						proctorNew= proctorAlter.getProctorName();
						pID = proctorAlter.getProctorID();
					}
				}
			});	
			add(proctorcombo);
			btnSubmit = new JButton("SUBMIT");
			btnSubmit.setBounds(400, 300, 120, 30);
			add(btnSubmit);
			btnSubmit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					ArrayList<String> usnList = new ArrayList<String>();
					usnList=record.getproctorusn(proctorOld);
					record.alterproctor( pID,usnList);
					JOptionPane.showMessageDialog(panel,"Altered Successfully","Message", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					revalidate();
				}
			});
		}

}
