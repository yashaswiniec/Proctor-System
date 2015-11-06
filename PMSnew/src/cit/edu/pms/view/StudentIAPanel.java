package cit.edu.pms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cit.edu.pms.calc.IAAverageCalculation;
import cit.edu.pms.entity.StudentIA;
import cit.edu.pms.entity.SubjectCode;
import cit.edu.pms.service.StudentService;

public class StudentIAPanel extends JPanel{
	
	private JLabel label, lblNote;
	private JTextField txtfield[] = new JTextField[54] ;	
	private JButton submit, update;
	private String usn,uSN;	
	private ArrayList<String> subjcode;	
	private ArrayList<Integer> intMAx;
	private ArrayList<Integer> extMAx;
	private int sem;	
	StudentService record = new StudentService();
	private JPanel panel;
	private String dept;
	private int subcode[]={6,12,18,24,30,36,42,48};
	private int maxMarks[] = {7,13,19,25,31,37,43,49};
	private int ia1[]={8,14,20,26,32,38,44,50};
	private int ia2[]={9,15,21,27,33,39,45,51};
	private int ia3[] ={10,16,22,28,34,40,46,52};
	private int avg[]={11,17,23,29,35,41,47,53};
	private ArrayList<Integer> iA1;
	private ArrayList<Integer> iA2;
	private ArrayList<Integer> iA3;
	private ArrayList<Float> average;
	SubjectCode subjectcode;
	public StudentIAPanel(final JFrame frame, Vector row)
	{
		this.usn = (String) row.firstElement();	
		setLayout(null);
		System.out.println("IA"+usn);
		setVisible(true);
		label = new JLabel("Please Enter the marks in the below given fields **");
		label.setBounds(50, 35, 600, 30);
		label.setFont(new Font("verdana", Font.ITALIC, 20));
		add(label);
		lblNote = new JLabel(" Note: Please Enter A in case of Absent **");
		lblNote.setBounds(50, 80, 600, 30);
		lblNote.setFont(new Font("verdana", Font.ITALIC, 20));
		add(lblNote);
		panel = new JPanel(new GridLayout(9, 6));
		panel.setBounds(75, 200, 500, 400);
		add(panel,BorderLayout.CENTER);
		for(int i=0;i<txtfield.length;i++)
		{
			txtfield[i] = new JTextField();
			txtfield[i].setBackground(Color.lightGray);
			panel.add(txtfield[i]);
		}
		txtfield[0].setText("SUBCODE");
		txtfield[1].setText("MAX MARKS");
		txtfield[2].setText("IA1");
		txtfield[3].setText("IA2");
		txtfield[4].setText("IA3");
		txtfield[5].setText("AVERAGE");
		
		for(int i=0; i<=5;i++)
		{
			txtfield[i].setBackground(Color.orange);
			txtfield[i].setEditable(false);
		}
		
		
		update = new JButton("UPDATE");
		update.setBounds(250, 700, 100, 30);
		update.setBackground(Color.cyan);
		add(update);
		
		submit = new JButton("PREVIEW");
		submit.setBounds(350, 700, 100, 30);
		submit.setBackground(Color.cyan);
		add(submit);
									
		sem = record.getSem(usn);		
		dept = record.getDept(usn);
		subjcode=new ArrayList<String>();
		intMAx = new ArrayList<Integer>();
		extMAx = new ArrayList<Integer>();
		subjectcode = new SubjectCode(subjcode, intMAx, extMAx);
		subjectcode=record.insertCode(sem,dept);	
		
		if(sem==8)
		{
			for(int i=0;i<6;i++)
			{
				txtfield[subcode[i]].setText(subjectcode.getSubcode().get(i));
				txtfield[maxMarks[i]].setText(String.valueOf(subjectcode.getIntMaxMarks().get(i)));
				txtfield[subcode[i]].setEditable(false);
				txtfield[maxMarks[i]].setEditable(false);
			}
			for(int i=0;i<8;i++)
			{
				txtfield[ia1[i]].setText("0");
				txtfield[ia2[i]].setText("0");
				txtfield[ia3[i]].setText("0");
				txtfield[avg[i]].setText("0");
				txtfield[avg[i]].setEditable(false);
			}
			for(int i=6;i<8;i++)
			{
				txtfield[ia1[i]].setEditable(false);
				txtfield[ia2[i]].setEditable(false);
				txtfield[ia3[i]].setEditable(false);
				txtfield[subcode[i]].setEditable(false);
				txtfield[subcode[i]].setBackground(Color.pink);
				txtfield[maxMarks[i]].setEditable(false);
				txtfield[maxMarks[i]].setBackground(Color.pink);
				txtfield[ia1[i]].setBackground(Color.pink);
				txtfield[ia2[i]].setBackground(Color.pink);
				txtfield[ia3[i]].setBackground(Color.pink);
				
			}
			for(int i=4;i<6;i++)
			{
				txtfield[ia2[i]].setEditable(false);
				txtfield[ia3[i]].setEditable(false);
				txtfield[ia2[i]].setBackground(Color.pink);
				txtfield[ia3[i]].setBackground(Color.pink);
			}
			
		}
		else
		{
			for(int i=0;i<8;i++)
			{
				txtfield[subcode[i]].setText(subjectcode.getSubcode().get(i));
				txtfield[maxMarks[i]].setText(String.valueOf(subjectcode.getIntMaxMarks().get(i)));
				txtfield[subcode[i]].setEditable(false);
				txtfield[maxMarks[i]].setEditable(false);
				txtfield[avg[i]].setEditable(false);
				txtfield[subcode[i]].setBackground(Color.pink);
				txtfield[maxMarks[i]].setBackground(Color.pink);
				txtfield[ia1[i]].setText("0");
				txtfield[ia2[i]].setText("0");
				txtfield[ia3[i]].setText("0");
				txtfield[avg[i]].setText("0");
			}
			for(int i=6;i<8;i++)
			{
				txtfield[ia2[i]].setEditable(false);
				txtfield[ia3[i]].setEditable(false);
				txtfield[ia2[i]].setBackground(Color.pink);
				txtfield[ia3[i]].setBackground(Color.pink);
						
			}
		}
		iA1 = new ArrayList<Integer>();
		iA2 = new ArrayList<Integer>();
		iA3 = new ArrayList<Integer>();
		average = new ArrayList<Float>();
		
		if(sem==8)
		{
			for(int i=0;i<8;i++)
			{
				int int1,int2,int3;
				float avrg;			
				int1= Integer.parseInt(txtfield[ia1[i]].getText());
				iA1.add(int1);
				int2=Integer.parseInt(txtfield[ia2[i]].getText());
				iA2.add(int2);
				int3= Integer.parseInt(txtfield[ia3[i]].getText());		
				iA3.add(int3);
				avrg = Integer.parseInt(txtfield[avg[i]].getText());
				average.add(avrg);
			}
			
		}
		else
		{
			for(int i=0;i<8;i++)
			{
				int int1,int2,int3;
				float avrg;			
				int1=Integer.parseInt(txtfield[ia1[i]].getText());
				iA1.add(int1);
				int2= Integer.parseInt(txtfield[ia2[i]].getText());
				iA2.add(int2);
				int3=Integer.parseInt(txtfield[ia3[i]].getText());		
				iA3.add(int3);
				avrg = Integer.parseInt(txtfield[avg[i]].getText());
				average.add(avrg);
			}
		}
		uSN=record.getStudentIAUSN(usn,sem);
		if(uSN!=null)
		{
			//do nothing
		}
		else
		{
			record.insertIA(usn,sem, iA1, iA2, iA3,average, subjectcode.getSubcode());
		}
		for(int i=0;i<8;i++)
		{
			txtfield[ia1[i]].setText(null);
			txtfield[ia2[i]].setText(null);
			txtfield[ia3[i]].setText(null);
			txtfield[avg[i]].setText(null);
			txtfield[avg[i]].setBackground(Color.pink);		
		}
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
			try
			{
				iA1 = new ArrayList<Integer>();
				iA2 = new ArrayList<Integer>();
				iA3 = new ArrayList<Integer>();
				boolean found = false;
				if(sem==8)
				{
					int int1,int2,int3;
					for(int i=0;i<4;i++)
					{							
						if(txtfield[ia1[i]].getText().isEmpty())
						{
							int1=0;
							iA1.add(int1);
						}
						else if(txtfield[ia1[i]].getText().equals("A") )
						{
							int1= -1;
							iA1.add(int1);	
						}
						else 
						{
							int1= Integer.parseInt(txtfield[ia1[i]].getText());
							iA1.add(int1);	
						}
						if(txtfield[ia2[i]].getText().isEmpty())
						{
							int2=0;
							iA2.add(int2);	
						}
						else if(txtfield[ia2[i]].getText().equals("A") )
						{
							int2= -1;
							iA2.add(int1);	
						}
						else 
						{
							int2= Integer.parseInt(txtfield[ia2[i]].getText());
							iA2.add(int2);	
						}
						if(txtfield[ia3[i]].getText().isEmpty())
						{
							int3=0;
							iA3.add(int3);	
						}	
						else if(txtfield[ia3[i]].getText().equals("A") )
						{
							int3= -1;
							iA3.add(int1);	
						}
						else 
						{
							int3= Integer.parseInt(txtfield[ia3[i]].getText());					
							iA3.add(int3);	
						}
						
					}
					for(int i=4;i<6;i++)
					{
						if(txtfield[ia1[i]].getText().isEmpty())
						{
							int1=0;
							iA1.add(int1);
						}
						
						else if(txtfield[ia1[i]].getText().equals("A") )
						{
							int1= -1;
							iA1.add(int1);	
						}
						else 
						{
							int1= Integer.parseInt(txtfield[ia1[i]].getText());
							iA1.add(int1);	
						}
						if(txtfield[ia2[i]].getText().isEmpty())
						{
							int2=0;
							iA2.add(int2);
						}
						if(txtfield[ia3[i]].getText().isEmpty())
						{
							int3=0;
							iA3.add(int3);
						}
					}
					
					for(int i=0;i<4;i++)
					{
						if( iA1.get(i)>25)
						{
							found = true;
						}
						else if ( iA2.get(i)>25)
						{
							found = true;
						}
						else if (iA3.get(i)>25)
						{
							found = true;
						}
					}
					
					if(found)
					{
						JOptionPane.showMessageDialog(null,"Marks should be <=25","Message", JOptionPane.ERROR_MESSAGE);
												
					}
					else if(iA1.get(4)>100)
					{
						JOptionPane.showMessageDialog(null,"Project marks should be <=100","Message", JOptionPane.ERROR_MESSAGE);
					}
					else if(iA1.get(5)>50)
					{
						JOptionPane.showMessageDialog(null,"Seminar marks should be <=50","Message", JOptionPane.ERROR_MESSAGE);
						
					}
					
					else
					{
						IAAverageCalculation averageCalcn = new IAAverageCalculation(iA1, iA2, iA3,sem);
						average = averageCalcn.calculateavg();						
						record.updateIA(usn,sem, iA1, iA2, iA3,average, subjectcode.getSubcode());
						JOptionPane.showMessageDialog(null,"Marks updated sucessfully","Message", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				
				else
				{
					for(int i=0;i<8;i++)
					{
						int int1,int2,int3;
						if(txtfield[ia1[i]].getText().isEmpty())
						{
							int1=0;
							iA1.add(int1);
						}
						
						else if(txtfield[ia1[i]].getText().equals("A") )
						{
							int1= -1;
							iA1.add(int1);	
						}
						else
						{
							int1= Integer.parseInt(txtfield[ia1[i]].getText());
							iA1.add(int1);	
						}
						if(txtfield[ia2[i]].getText().isEmpty())
						{
							int2=0;
							iA2.add(int2);	
						}
						else if(txtfield[ia2[i]].getText().equals("A"))
						{
							int2= -1;
							iA2.add(int2);	
						}
						else
						{
							int2= Integer.parseInt(txtfield[ia2[i]].getText());
							iA2.add(int2);	
						}
						if(txtfield[ia3[i]].getText().isEmpty())
						{
							int3=0;
							iA3.add(int3);	
						}				
						else if(txtfield[ia3[i]].getText().equals("A"))
						{
							int3= -1;
							iA3.add(int3);	
						}
						else
						{
							int3= Integer.parseInt(txtfield[ia3[i]].getText());					
							iA3.add(int3);	
						}
					}
					
				for(int i=0;i<8;i++)
					{
						if( iA1.get(i)>25)
						{
							found = true;
						}
						else if ( iA2.get(i)>25)
						{
							found = true;
						}
						else if (iA3.get(i)>25)
						{
							found = true;
						}
					}
					
					if(found)
					{
						JOptionPane.showMessageDialog(null,"Marks should be <=25","Message", JOptionPane.ERROR_MESSAGE);
					}
					
				else
					{
						IAAverageCalculation averageCalcn = new IAAverageCalculation(iA1, iA2, iA3,sem);
						average = averageCalcn.calculateavg();
						record.updateIA(usn,sem, iA1, iA2, iA3,average, subjectcode.getSubcode());
						JOptionPane.showMessageDialog(null,"Marks updated sucessfully","Message", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"Please enter valid marks","Message", JOptionPane.ERROR_MESSAGE);
			}
			}			
		});
		
	
		submit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				iA1 = new ArrayList<Integer>();
				iA2 = new ArrayList<Integer>();
				iA3 = new ArrayList<Integer>();
				average = new ArrayList<Float>();
				StudentIA ia = new StudentIA(iA1, iA2, iA3, average);
				ia= record.getallIA(usn,sem);
				if(sem==8)
				{
					for(int i=0;i<4;i++)
					{
						iA1=ia.getiA1();
						iA2=ia.getiA2();
						iA3=ia.getiA3();
						average=ia.getAverage();
						if(iA1.get(i)==-1)
						{
							txtfield[ia1[i]].setText("A");

						}
						else
						{
							txtfield[ia1[i]].setText(String.valueOf(iA1.get(i)));
						}
						
						if(iA2.get(i)==-1)
						{
							txtfield[ia2[i]].setText("A");
						}
						else
						{
							txtfield[ia2[i]].setText(String.valueOf(iA2.get(i)));
						}
						if(iA3.get(i)==-1)
						{
							txtfield[ia3[i]].setText("A");
						}
						else
						{
							txtfield[ia3[i]].setText(String.valueOf(iA3.get(i)));
						}					
					}
					for(int i=0;i<6;i++)
					{
						if(iA1.get(i)==-1)
						{
							txtfield[ia1[i]].setText("A");
						}
						else
						{
							txtfield[ia1[i]].setText(String.valueOf(iA1.get(i)));
						}
						if(average.get(i)==-1)
						{
							txtfield[avg[i]].setText("A");
						}
						else
						{
							txtfield[avg[i]].setText(String.valueOf(average.get(i)));
						}
					}
					}
				
				else
				{
					for(int i=0;i<8;i++)
					{
						iA1=ia.getiA1();
						iA2=ia.getiA2();
						iA3=ia.getiA3();
						average=ia.getAverage();
						average=ia.getAverage();
						if(iA1.get(i)==-1)
						{
							txtfield[ia1[i]].setText("A");
						}
						else
						{
							txtfield[ia1[i]].setText(String.valueOf(iA1.get(i)));
						}
						
						if(iA2.get(i)==-1)
						{
							txtfield[ia2[i]].setText("A");
						}
						else
						{
							txtfield[ia2[i]].setText(String.valueOf(iA2.get(i)));
						}
						if(iA3.get(i)==-1)
						{
							txtfield[ia3[i]].setText("A");
						}
						else
						{
							txtfield[ia3[i]].setText(String.valueOf(iA3.get(i)));
						}
						
					txtfield[avg[i]].setText(String.valueOf(average.get(i)));
					}
				}
				}			
		});
		
		}
		
  }
