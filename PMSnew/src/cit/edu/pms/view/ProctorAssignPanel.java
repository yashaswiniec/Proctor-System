package cit.edu.pms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import cit.edu.pms.entity.Proctor;
import cit.edu.pms.entity.Student;
import cit.edu.pms.service.AdminService;
import cit.edu.pms.service.StudentService;

public class ProctorAssignPanel extends JPanel {

	private JComboBox sem;
	private JComboBox sec;
	private JComboBox proctorcombo;
	private JComboBox deptCombo;
	private JLabel labelSection,labelSem,labelProctor,lblTitle,lbldept;
	private JButton btnSelect,btnAssign;
	private Vector semester;
	private Vector section;
	private Vector proctorVector;
	private String  sectName,deptName,studName;
	private ArrayList<String>studentName;
	private ArrayList<String> assigned;
	private Vector dept;
	private int proctorID;
	private String semName, assgn;
	private JTable table;
	private JFrame parentFrame;
	private JPanel panel,pane;		
	AdminService record = new AdminService();
	private Proctor selectproctor;
    private	Student student;
	public ProctorAssignPanel(final JFrame frame)
	{		
		panel = this;
		setLayout(null);	
		lblTitle=new JLabel("Please select the below fields...");
		lblTitle.setBounds(100, 50, 500, 30);
		lblTitle.setFont(new Font("Serif", Font.ITALIC, 30));
		add(lblTitle);
		lbldept = new JLabel("Select dept");
		lbldept.setFont(new Font("Serif", Font.BOLD, 15));
		lbldept.setBounds(250, 100, 120, 30);
		add(lbldept);
		dept= record.getProctorDept();
		deptCombo = new JComboBox(dept);
		deptCombo.setBounds(350, 100, 120, 30);
		add(deptCombo);
		deptCombo.setSelectedIndex(-1);
		deptCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				if(ie.getStateChange()==ItemEvent.SELECTED)
				{
					deptName=(String) deptCombo.getSelectedItem();
				}
			}
		});
		labelSection = new JLabel("SelectSection:");
		labelSection.setFont(new Font("Serif", Font.BOLD, 15));
		labelSection.setBounds(250, 200, 120, 30);
		labelSem = new JLabel("SelectSemester:");
		labelSem.setBounds(250, 250, 120, 30);
		labelSem.setFont(new Font("Serif", Font.BOLD, 15));
		labelProctor = new JLabel("Selectproctor:");
		labelProctor.setBounds(250,300, 120, 30);
		labelProctor.setFont(new Font("Serif", Font.BOLD, 15));
		btnSelect = new JButton("SelectUSN");
		btnSelect.setBounds(300, 350, 120, 30);
		btnSelect.setBackground(Color.cyan);
		btnAssign = new JButton("Assign");
		btnAssign.setBounds(300, 400, 120, 30);	
		btnAssign.setBackground(Color.cyan);
		section = record.getsec();
		sec = new JComboBox(section);
		sec.setBounds(375, 200, 120, 30);
		sec.setSelectedIndex(-1);
		sec.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				if(ie.getStateChange()==ItemEvent.SELECTED)
				{
					sectName = (String) sec.getSelectedItem();		
				}
			}
		});
		
		semester = 	record.getsem();
		sem = new JComboBox(semester);
		sem.setBounds(375, 250, 120, 30);		
		sem.setSelectedIndex(-1);
		sem.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				if(ie.getStateChange()==ItemEvent.SELECTED)
				{
					semName =  (String) sem.getSelectedItem();
								
				}
			}
		});
		proctorVector = new Vector();
		proctorVector = record.getallproctor();
		proctorcombo=new JComboBox(proctorVector);		
		proctorcombo.setBounds(375,300, 120, 30);
		proctorcombo.setSelectedIndex(-1);
		proctorcombo.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				if(ie.getStateChange()==ItemEvent.SELECTED)
				{
					int index = proctorcombo.getSelectedIndex();
					selectproctor=(Proctor) proctorVector.get(index);
					proctorID = selectproctor.getProctorID();
				}
			}
		});		
		btnSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg) {
				JOptionPane.showMessageDialog(null,"Select USN's and assign","Message", JOptionPane.INFORMATION_MESSAGE);
				
				assgn ="No";
				if(record.getData(sectName, semName,assgn).getRowCount()>0)
				{
					table = new JTable(record.getData(sectName, semName,assgn));
					table.setAutoCreateRowSorter(true);
					table.setPreferredScrollableViewportSize(new Dimension(300,200));
					table.setBorder(BorderFactory.createLineBorder(Color.black));
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane.getViewport().add(table);
					pane=new JPanel();					
			 		add(BorderLayout.EAST,pane);
					pane.setBounds(700, 100, 400, 452);
					pane.setBackground(Color.LIGHT_GRAY);
					pane.add(scrollPane);
					setVisible(true);					
					revalidate();	
				}
				else
					JOptionPane.showMessageDialog(null,"Already assigned","Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAssign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
											
				int index[]=table.getSelectedRows();
				table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				try
				{				
				if(index[0]!=-1 && table.getSelectedRows()!=null)
				{
					Vector<Vector<String>> data = ((DefaultTableModel)table.getModel()).getDataVector();
					ArrayList<String> studentUSN = new ArrayList<String>();
					Vector<String > row = new Vector<String>();
					for(int i=0;i<index.length;i++)
					{
						Vector<String> usnData;
						usnData = data.get(index[i]);
						row.addAll(usnData);	
						
					}
					assigned = new ArrayList<String>();
					for(int i=0; i< row.size();i++)
					{
						String usn =row.get(i);						
						studentUSN.add(usn);
						String assgn;
						assgn="yes";
						assigned.add(assgn);
				
					}
					studentName = new ArrayList<String>();
					for(int i=0;i<studentUSN.size();i++)
					{
						studName=record.getName(studentUSN.get(i));
						studentName.add(studName);
					}
					
					
					record.createProctor(studentName,deptName,studentUSN, proctorID, Integer.parseInt(semName), sectName,assigned);
					JOptionPane.showMessageDialog(panel,"Assigned Successfully","Message", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
				}
				catch (Exception e)
				{
					e.printStackTrace();
					//JOptionPane.showMessageDialog(panel,"Please select a row", "Message", JOptionPane.ERROR_MESSAGE);
				}		
				
				revalidate();
				}	
			});		
		add(labelSection);
		add(labelSem);
		add(labelProctor);
		add(sem);
		add(sec);
		add(proctorcombo);
		add(btnSelect);
		add(btnAssign);		
		remove(panel);
		panel.setVisible(false);
		
	}	
}
