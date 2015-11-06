package cit.edu.pms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JTextField;

import cit.edu.pms.calc.ResultAnalysis;
import cit.edu.pms.db.StudentDAO;
import cit.edu.pms.entity.StudentResult;
import cit.edu.pms.entity.SubjectCode;
import cit.edu.pms.service.StudentService;

public class StudentResultPanel extends JPanel {
	
	private JLabel label, labelNote;
	private JTextField txtMarks[] = new JTextField[36];	
	private JButton btnEdit, btnupdate;
	private String studentUSN,uSN;
	private ArrayList<Integer> internals;
	private ArrayList<Integer> externals;	
	private ArrayList<Integer> total;
	private ArrayList<String> remarks; 	
	private ArrayList<String> subCode;	
	private ArrayList<Integer> intmaxMarks;
	private ArrayList<Integer> extmaxMarks;
	private ArrayList<Integer >maxMarks;

	private int subcode[] = {4,8,12,16,20,24,28,32};
	private int maxEXTmarks[]={5,9,13,17,21,25,29,33};
	private int theorymarks[]={7,11,15,19,23,27,31,35};	
	private int external[]={7,11,15,19,23,27,31,35};
	private int internal[]={6,10,14,18,22,26,30,34};	
	private String department;
	private JPanel jpanel;
	private int sem;
	StudentService record = new StudentService();
	SubjectCode code;
	public StudentResultPanel(final JFrame frame, Vector row)
	{
		
		this.studentUSN = (String) row.firstElement();			
		setLayout(null);
		setVisible(true);
		label = new JLabel("Please Enter the marks in the below given fields **");
		label.setBounds(50, 35, 600, 30);
		label.setFont(new Font("verdana", Font.ITALIC, 20));
		add(label);
		labelNote = new JLabel(" Note: Please Enter A in case of Absent **");
		labelNote.setBounds(50, 80, 600, 30);
		labelNote.setFont(new Font("verdana", Font.ITALIC, 20));
		add(labelNote);
		
		jpanel = new JPanel(new GridLayout(9, 6));
		jpanel.setBounds(75, 200, 600, 400);
		add(jpanel,BorderLayout.CENTER);
		btnupdate = new JButton("UPDATE");
		btnupdate.setBounds(250, 700, 100, 30);
		btnupdate.setBackground(Color.cyan);
		add(btnupdate);
		
		btnEdit = new JButton("PREVIEW");
		btnEdit.setBounds(350, 700, 100, 30);
		btnEdit.setBackground(Color.cyan);
		add(btnEdit);
		for(int i=0;i<txtMarks.length;i++)
		{
			txtMarks[i] = new JTextField();
			txtMarks[i].setBackground(Color.lightGray);
			jpanel.add(txtMarks[i]);
		}
		txtMarks[0].setText("SUBCODE");
		txtMarks[1].setText("MAX MARKS");
		txtMarks[2].setText("INTERNALS");
		txtMarks[3].setText("EXTERNAL");
		
		for(int i=0; i<=3;i++)
		{
			txtMarks[i].setBackground(Color.orange);
			txtMarks[i].setEditable(false);
		}		
					
		subCode = new ArrayList<String>();	
		extmaxMarks=new ArrayList<Integer>();
		sem = record.getSem(studentUSN);
		department=record.getDept(studentUSN);	
		code = new SubjectCode(subCode, intmaxMarks, extmaxMarks);
		code=record.insertCode(sem, department);
		internals = new ArrayList<Integer>();
		externals = new ArrayList<Integer>();		
		internals = record.getAverage(studentUSN,sem);
		
	if(internals.size()!=0)
		{
		
			if(sem==8)
			{
				maxMarks = new ArrayList<Integer>();
				for(int i=0;i<6;i++)
				{
					txtMarks[subcode[i]].setText(code.getSubcode().get(i));	
					txtMarks[internal[i]].setText(String.valueOf(internals.get(i)));
					txtMarks[maxEXTmarks[i]].setText(String.valueOf(code.getExtMaxMarks().get(i)));
					txtMarks[maxEXTmarks[i]].setEditable(false);
					txtMarks[subcode[i]].setEditable(false);
					txtMarks[internal[i]].setEditable(false);
					txtMarks[subcode[i]].setBackground(Color.pink);
					txtMarks[internal[i]].setBackground(Color.pink);
					txtMarks[maxEXTmarks[i]].setBackground(Color.pink);
					int ext=0;
					externals.add(ext);						
					int max;
					max = Integer.parseInt(txtMarks[maxEXTmarks[i]].getText());
					maxMarks.add(max);
					
				}
					txtMarks[external[5]].setText(String.valueOf(internals.get(5)));
					txtMarks[external[5]].setBackground(Color.pink);
					txtMarks[external[5]].setEditable(false);
			}
			else
			{
				maxMarks = new ArrayList<Integer>();
				for(int i=0;i<8;i++)
				{
					
					txtMarks[subcode[i]].setText(code.getSubcode().get(i));	
					txtMarks[internal[i]].setText(String.valueOf(internals.get(i)));
					txtMarks[maxEXTmarks[i]].setText(String.valueOf(code.getExtMaxMarks().get(i)));
					txtMarks[maxEXTmarks[i]].setEditable(false);
					txtMarks[subcode[i]].setEditable(false);
					txtMarks[internal[i]].setEditable(false);
					txtMarks[subcode[i]].setBackground(Color.pink);
					txtMarks[internal[i]].setBackground(Color.pink);
					txtMarks[maxEXTmarks[i]].setBackground(Color.pink);
					int ext=0;			
					externals.add(ext);
					txtMarks[external[i]].setText(null);
					int max;
					max = Integer.parseInt(txtMarks[maxEXTmarks[i]].getText());
					maxMarks.add(max);
					
				}
			}
		uSN = record.getResultUSN(studentUSN,sem);
		if(uSN!=null)
		{
			//do nothing
		}
		else
		{
			record.insertResult(studentUSN,sem, code.getSubcode(), internals, externals);
		}
		
		btnupdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			try
			{
				externals = new ArrayList<Integer>();
				total=new ArrayList<Integer>();
				remarks = new ArrayList<String>();
				boolean found = false;
				int theory;
				if(sem==8)
				{
					int thry;
					for(int i=0;i<5;i++)
					{
						if(txtMarks[theorymarks[i]].getText().equalsIgnoreCase("A"))
							{
								thry=-1;
								externals.add(thry);
							}
						else
							{
								thry=Integer.parseInt(txtMarks[theorymarks[i]].getText());
								externals.add(thry);
							}
					}
						thry = Integer.parseInt(txtMarks[theorymarks[5]].getText());
						externals.add(thry);
					for(int i=0;i<5;i++)
					{
						if(externals.get(i)>100)
						{
							found=true;
						}
					}
					if(found)
					{
						JOptionPane.showMessageDialog(null,"External marks should be <=100 ","Message", JOptionPane.ERROR_MESSAGE);
						
					}
					else
					{
						internals= new ArrayList<Integer>();
						internals = record.getAverage(studentUSN,sem);
						ResultAnalysis resultanalysis = new ResultAnalysis(internals, externals, maxMarks, studentUSN,sem);
						total = resultanalysis.calculateTotal();
						internals = record.getAverage(studentUSN,sem);
						remarks=resultanalysis.findRemarks();
						record.updateResult(studentUSN, sem,code.getSubcode(), internals, externals, total, remarks);
						JOptionPane.showMessageDialog(null,"Marks updated sucessfully","Message", JOptionPane.INFORMATION_MESSAGE);
						
					}
				}
				else
				{						
						for(int i=0;i<8;i++)
						{							
							if(txtMarks[theorymarks[i]].getText().isEmpty())
							{
								theory = 0;
								externals.add(theory);
							}
							else if(txtMarks[theorymarks[i]].getText().equalsIgnoreCase("A"))
							{
								theory = -1;
								externals.add(theory);
							}
							else
							{
								theory=Integer.parseInt(txtMarks[theorymarks[i]].getText());
								externals.add(theory);
							}
						}
						for(int i=0;i<6;i++)
						{						
							if(externals.get(i)>100)
							{
								found = true;
							}
						}
						for(int i=6;i<8;i++)
						{
							if(externals.get(i)>50)
							{
								found = true;
							}
						}
						if(found)
						{
							JOptionPane.showMessageDialog(null,"Theory marks should be <=100 and Lab marks<=50","Message", JOptionPane.ERROR_MESSAGE);
							
						}
						else
						{
							if(externals.size()>=8 )
							{
								
								internals= new ArrayList<Integer>();
								internals = record.getAverage(studentUSN,sem);
								ResultAnalysis resultanalysis = new ResultAnalysis(internals, externals, maxMarks, studentUSN,sem);
								total = resultanalysis.calculateTotal();
								internals = record.getAverage(studentUSN,sem);
								remarks=resultanalysis.findRemarks();
								record.updateResult(studentUSN, sem,code.getSubcode(), internals, externals, total, remarks);
								JOptionPane.showMessageDialog(null,"Marks updated sucessfully","Message", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
			}
			catch(Exception e)
			{
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Please Enter Valid marks","Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				internals = new ArrayList<Integer>();
				externals= new ArrayList<Integer>();
				ArrayList<Integer> ttlMarks = new ArrayList<Integer>();
				remarks = new ArrayList<String>();
				StudentResult result = new StudentResult(internals, externals,ttlMarks,remarks);
				result= record.getAllResult(studentUSN,sem);
				if(sem==8)
				{
					for(int i=0;i<6;i++)
					{
						if(result.getExtMarks().get(i)==-1)
						{
							txtMarks[external[i]].setText("A");
						}
						else
						{
							txtMarks[external[i]].setText(String.valueOf(result.getExtMarks().get(i)));
						}				
					}
				}
				else
				{
					for(int i=0;i<8;i++)
					{
						if(result.getExtMarks().get(i)==-1)
						{
							txtMarks[external[i]].setText("A");
						}
						else
						{
							txtMarks[external[i]].setText(String.valueOf(result.getExtMarks().get(i)));
						}	
					}
				}
			}
		});	
	
		
	}
				
}
}