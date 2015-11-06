package cit.edu.pms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cit.edu.pms.calc.ResultAnalysis;
import cit.edu.pms.entity.StudentResult;
import cit.edu.pms.service.StudentService;

public class StudentPreviousViewPanel extends JPanel {
	
	private JComboBox<String> comboSem;
	private JPanel iApane,attPane,resPane,jpanel;
	private JLabel lblview,labelttl;
	private String[] semesters={"1","2","3","4","5","6","7","8"};	
	private String sem;
	private int semster;
	private JButton btnsubmit;
	StudentService studentview = new StudentService();
	private JTable studentIATable, studentAttTable, studentResTable;
	private String uSN, procName;
	private JFrame parentframe;
	StudentViewPanel viewpanel;
	private Vector row;
	public StudentPreviousViewPanel(JFrame frame, Vector roW, String pname) {
		
		this.uSN=(String) roW.firstElement();	
		this.row=roW;
		this.procName=pname;
		jpanel=this;
		this.parentframe = frame;
		setVisible(true);
		setLayout(null);			
		semster = studentview.getSem(uSN);
		
		labelttl = new JLabel("=> Please select semester and submit to view previous records");
		labelttl.setBounds(100, 50, 650, 30);
		labelttl.setForeground(Color.blue);
		labelttl.setFont(new Font("Serif", Font.ITALIC, 20));
		add(labelttl);		
		
		lblview = new JLabel("Select Semester:");
		lblview.setBounds(200, 100, 120, 30);
		lblview.setFont(new Font("Serif", Font.BOLD, 15));
		add(lblview);
		
		comboSem = new JComboBox<String> (semesters);
		comboSem.setBounds(330, 100, 100, 30);				
		add(comboSem);		
		
		comboSem.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				if(ie.getStateChange()==ItemEvent.SELECTED)
				{
					sem = (String) comboSem.getSelectedItem();
				}
				
			}
		});
		comboSem.setSelectedIndex(-1);				
		btnsubmit = new JButton("SUBMIT");
		btnsubmit.setBounds(250, 150, 100, 30);
		btnsubmit.setBackground(Color.yellow);
		add(btnsubmit);
		
		btnsubmit.addActionListener(new ActionListener() {
			
			@Override
		public void actionPerformed(ActionEvent arg0) {
				
				if(iApane!=null)
				{
					iApane.setVisible(false);				
					revalidate();				
				}
				
				if(attPane!=null)
				{
					attPane.setVisible(false);
					revalidate();
				}
				if(resPane!=null)
				{
					resPane.setVisible(false);
					revalidate();
				}
			if(studentview.getData(uSN,Integer.parseInt(sem)).getDataVector().isEmpty()&&studentview.getAttendance(uSN,Integer.parseInt(sem)).getDataVector().isEmpty()&&studentview.getstudentResult(uSN,Integer.parseInt(sem)).getDataVector().isEmpty())
			{
				
				JOptionPane.showMessageDialog(null,"No result to show, Please update details","Message", JOptionPane.ERROR_MESSAGE);
					
			}
			else
			{				
				studentIATable = new JTable(studentview.getData(uSN,Integer.parseInt(sem)));	
				studentIATable.setAutoCreateRowSorter(true);
				studentIATable.setBackground(Color.green);
				studentIATable.setPreferredScrollableViewportSize(new Dimension(375,200));
				studentIATable.setAutoResizeMode(WHEN_FOCUSED);
				studentIATable.setEditingRow(0);	
				try
				{
					if(studentIATable.getRowCount()>0)
					{
						for(int i=0;i<studentIATable.getRowCount();i++)
						{
							for(int j=0;j<studentIATable.getColumnCount();j++)
							{
								if(studentIATable.getModel().getValueAt(i, j).equals("-1"))
								{
									studentIATable.getModel().setValueAt("A", i, j);
								}
							}
						}
					}
				}
				catch(Exception e)
				{
					
				}
				JScrollPane scrollPane = new JScrollPane(studentIATable);
				scrollPane.getViewport().add(studentIATable);
				iApane=new JPanel();
				iApane.setBackground(Color.ORANGE);
				iApane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createLineBorder(Color.black),
                        "Internals Report",
                        TitledBorder.CENTER,
                        TitledBorder.TOP));		 		
				iApane.setBounds(70, 200, 400, 300);				
				iApane.add(scrollPane);
				add(BorderLayout.WEST,iApane);			
				studentAttTable = new JTable(studentview.getAttendance(uSN,Integer.parseInt(sem)));	
				studentAttTable.setAutoCreateRowSorter(true);
				studentAttTable.setPreferredScrollableViewportSize(new Dimension(370,200));
				studentAttTable.setBackground(Color.green);				
				JScrollPane scrollpane = new JScrollPane(studentAttTable);
				scrollpane.getViewport().add(studentAttTable);
				attPane=new JPanel();				
				attPane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createLineBorder(Color.black),
		                    "Attendance Report",
		                    TitledBorder.CENTER,
		                    TitledBorder.TOP));		
				attPane.setBackground(Color.orange);		 		
		 		attPane.setBounds(550, 200, 400, 300);
		 		attPane.add(scrollpane);
		 		add(BorderLayout.CENTER,attPane);				
				String remarks = null;
				float percentage;
				ArrayList<Integer> totalMark = new ArrayList<Integer>();
				ArrayList<Integer> internalsMark= new ArrayList<Integer>(); 
				ArrayList<Integer> externalsMark = new ArrayList<Integer>();
				ArrayList<String> rmrks = new ArrayList<String>();
				StudentService service = new StudentService();
				StudentResult studentresult = new StudentResult(internalsMark, externalsMark, totalMark,rmrks);
				studentresult = service.getAllResult(uSN,Integer.parseInt(sem));
				internalsMark = studentresult.getiAmarks();
				externalsMark= studentresult.getExtMarks();
				totalMark=studentresult.getTtlMarks();
				ResultAnalysis resAnalysis = new ResultAnalysis(internalsMark, externalsMark, totalMark, uSN, Integer.parseInt(sem));		
				percentage = resAnalysis.gettotalpercentage();
				remarks = resAnalysis.getpercentageremarks();
				studentResTable = new JTable(studentview.getstudentResult(uSN,Integer.parseInt(sem)));	
				studentResTable.setAutoCreateRowSorter(true);
				studentResTable.setPreferredScrollableViewportSize(new Dimension(375,200));
				studentResTable.setBackground(Color.green);
				try
				{
					if(studentResTable.getRowCount()>0)
					{
						for(int i=0;i<studentResTable.getRowCount();i++)
						{
							for(int j=0;j<studentResTable.getColumnCount();j++)
							{
								if(studentResTable.getModel().getValueAt(i, j).equals("-1"))
								{
									studentResTable.getModel().setValueAt("A", i, j);
								}
							}
						}
					}
				}
				catch(Exception e)
				{
					
				}
				JScrollPane scrolPane = new JScrollPane(studentResTable);
				scrolPane.getViewport().add(studentResTable);
				resPane=new JPanel();
				resPane.setBackground(Color.orange);		 		
		 		resPane.setBounds(300, 450, 400, 300);				
		 		resPane.add(scrolPane);
		 		add(resPane);	
		 		resPane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createLineBorder(Color.black),
		                    "Final Exam Report",
		                    TitledBorder.CENTER,
		                    TitledBorder.TOP));
		 		resPane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createLineBorder(Color.black),
		                "Remarks:"+ "   "+ "Total Percentage= " +  "   "+percentage + "   "+ "Class Obtained: " +  "   "+remarks,
		                TitledBorder.CENTER,
		                TitledBorder.BOTTOM));
				setVisible(true);
				revalidate();						
			}
			}
			});
				
	}	
}	
		
	
	
	
	

	

