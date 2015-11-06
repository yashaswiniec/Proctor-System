package cit.edu.pms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cit.edu.pms.entity.StudentAttendance;
import cit.edu.pms.entity.SubjectCode;
import cit.edu.pms.service.StudentService;

public class StudentAttendancePanel extends JPanel{

	private JLabel labelTitle;	
	private JTextField txtSUB[]=new JTextField[27];
	private JButton submit, update ;
	private String studentusn,departmt,studentuSN;
	private JPanel panel;
	private ArrayList<Integer> totalclass;
	private ArrayList<Integer> classno;
	private ArrayList<Float> percentage;
	private ArrayList<String> subjectcode;
	private int subjectCode[]={3,6,9,12,15,18,21,24};
	private int ttlclass[]={4,7,10,13,16,19,22,25};
	private int ttlpresent[]={5,8,11,14,17,20,23,26};
	private int sem;
	StudentService service = new StudentService();
	ArrayList<Integer> intern = new ArrayList<Integer>();
	ArrayList<Integer> extern = new ArrayList<Integer>();
	SubjectCode subcode;
	public StudentAttendancePanel(JFrame frame,Vector row)
	{
		
		this.studentusn = (String) row.firstElement();		
		setLayout(null);
		setVisible(true);
		labelTitle = new JLabel("Please Enter the details in the below given fields **");
		labelTitle.setBounds(50, 25, 600, 30);
		labelTitle.setFont(new Font("verdana", Font.ITALIC, 20));
		add(labelTitle);
		
		submit = new JButton("EDIT");
		submit.setBounds(400, 700, 100, 30);
		submit.setBackground(Color.magenta);
		add(submit);
		
		update = new JButton("UPDATE");
		update.setBounds(300, 700, 100, 30);
		update.setBackground(Color.magenta);
		add(update);
		panel = new JPanel(new GridLayout(9, 6));
		panel.setBounds(75, 200, 600, 400);
		add(panel,BorderLayout.CENTER);
		
		for(int i=0;i<txtSUB.length;i++)
		{
			txtSUB[i] = new JTextField();
			txtSUB[i].setBackground(Color.lightGray);
			panel.add(txtSUB[i]);
		}
		txtSUB[0].setText("SUBCODE");
		txtSUB[1].setText("TOTAL CLASS");
		txtSUB[2].setText("ATTENDED");
		
		
		for(int i=0; i<=2;i++)
		{
			txtSUB[i].setBackground(Color.orange);
			txtSUB[i].setEditable(false);
		}		
		subjectcode = new ArrayList<String>();		
		sem = service.getSem(studentusn);
		departmt=service.getDept(studentusn);	
		intern = new ArrayList<Integer>();
		extern = new ArrayList<Integer>();
		subcode = new SubjectCode(subjectcode, intern, extern);
		subcode=service.insertCode(sem, departmt);
		totalclass = new ArrayList<Integer>();
		classno = new ArrayList<Integer>();
		if(sem==8)
		{
			for(int i=0;i<6;i++)
			{
				int ttlclas, totalpresent;				
				txtSUB[subjectCode[i]].setText(subcode.getSubcode().get(i));				
				txtSUB[subjectCode[i]].setEditable(false);	
				txtSUB[subjectCode[i]].setBackground(Color.PINK);
				txtSUB[ttlclass[i]].setText("0");
				txtSUB[ttlpresent[i]].setText("0");
				ttlclas = Integer.parseInt(txtSUB[ttlclass[i]].getText());
				totalclass.add(ttlclas);
				totalpresent = Integer.parseInt(txtSUB[ttlpresent[i]].getText());
				classno.add(totalpresent);
			}
				
		}
		else
		{
			for(int i=0;i<8;i++)
			{
				int ttlclas, totalpresent;			
				txtSUB[subjectCode[i]].setText(subcode.getSubcode().get(i));
				txtSUB[subjectCode[i]].setBackground(Color.PINK);
				txtSUB[subjectCode[i]].setEditable(false);
				txtSUB[ttlclass[i]].setText("0");
				txtSUB[ttlpresent[i]].setText("0");
				ttlclas = Integer.parseInt(txtSUB[ttlclass[i]].getText());
				totalclass.add(ttlclas);
				totalpresent = Integer.parseInt(txtSUB[ttlpresent[i]].getText());
				classno.add(totalpresent);
			}
		}
		studentuSN = service.getAttendanceUSN(studentusn,sem);
		
		if(studentuSN!=null)
		{
			//do nothing
		}
		else
		{
			service.insertAttendance(studentusn,sem, subcode.getSubcode(), totalclass, classno);
		}
		for(int i=0;i<8;i++)
		{
			txtSUB[ttlclass[i]].setText(null);
			txtSUB[ttlpresent[i]].setText(null);
		}
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					boolean found = false;
					totalclass = new ArrayList<Integer>();
	 				classno =new ArrayList<Integer>();
	 				percentage = new ArrayList<Float>();				
					int total,present;									
					float percent;
					for(int i=0;i<8;i++)
					{
						if(txtSUB[ttlclass[i]].getText().isEmpty())
						{
							total=0;
							totalclass.add(total);
						}
						else 
						{
						total = Integer.parseInt(txtSUB[ttlclass[i]].getText());
						totalclass.add(total);
						}
						if(txtSUB[ttlpresent[i]].getText().isEmpty())
						{
							present=0;
							classno.add(present);
						}
						else
						{
							present = Integer.parseInt(txtSUB[ttlpresent[i]].getText());
							classno.add(present);
						}
						if(classno.get(i)!=0&&totalclass.get(i)!=0)
							{
							percent = (float)classno.get(i)/(float)totalclass.get(i);					
							percentage.add(percent*100);
							}
						else
							{
								float j=0;
								percentage.add(j);
							}
					}		
					for(int i=0;i<8;i++)
					{
						if(classno.get(i)>totalclass.get(i))
						{
							found = true;
						}
					}
					if(found)
					{
						JOptionPane.showMessageDialog(null,"Total class present should be <= total no of classes held","Message", JOptionPane.ERROR_MESSAGE);
					}
					else
					{	
						service.updateAttendance(studentusn, sem,subcode.getSubcode(), totalclass, classno, percentage);
						JOptionPane.showMessageDialog(null,"Attendance status updated sucessfully","Message", JOptionPane.INFORMATION_MESSAGE);
					}
			}
		});
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				totalclass = new ArrayList<Integer>();
				classno = new ArrayList<Integer>();
				percentage=new ArrayList<Float>();
				StudentAttendance attendance = new StudentAttendance(totalclass, classno, percentage);
				attendance=service.getAllAttendance(studentusn,sem);
				for(int i=0;i<8;i++)
				{
					txtSUB[ttlclass[i]].setText(String.valueOf(attendance.getTotalClass().get(i)));
					txtSUB[ttlpresent[i]].setText(String.valueOf(attendance.getTotalPresent().get(i)));
				}
			}
		});	
		
	}
}
