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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cit.edu.pms.calc.ResultAnalysis;
import cit.edu.pms.db.StudentDAO;
import cit.edu.pms.entity.StudentResult;
import cit.edu.pms.service.StudentService;

public class StudentViewPanel extends JPanel{
	
	private JPanel panel,pane;
	private JLabel lblname,lblusn,lblDOB,lblAddress,lblphone,lblemail,lblTitle;
	private JLabel name,usn,dob,address,phone,email;
	private String uSN,pName;	
	private ArrayList<String> list;
	StudentDAO dao = new StudentDAO();
	private JButton btnIARecord,btnAttRecord,btnResult,btnBack,btnView;
	private JTable iaTable,attendTable,resultTable;
	private JFrame parentFrame;	
	private int semester;
	private float percent;
	private String remarks = null;
	StudentPreviousViewPanel previewpanel;	
	StudentService studentview = new StudentService();
	
	public StudentViewPanel( final JFrame frame,  final Vector row, final String proctorName)
	{

		this.parentFrame = frame;		
		this.pName=proctorName;
		panel=this;			
		setLayout(null);
		setVisible(true);
		
		this.uSN = (String) row.firstElement();
		list=new ArrayList<String>();
		list=dao.getStudentinfo(uSN);
		
		lblname = new JLabel("Firstname:");
		lblname.setBounds(20, 50,200, 50);
		lblname.setFont(new Font("Serif", Font.BOLD,25));
		add(lblname);
				
		name = new JLabel();
		name.setBounds(180, 50, 500, 50);
		name.setText(list.get(0));
		name.setFont(new Font("Serif", Font.BOLD, 20));
		add(name);
		
		lblusn = new JLabel("USN:");
		lblusn.setBounds(20, 100,200, 50);
		lblusn.setFont(new Font("Serif", Font.BOLD,25));
		add(lblusn);
		
		usn = new JLabel();
		usn.setBounds(180, 100, 200, 50);
		usn.setText(uSN);
		usn.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		add(usn);
		
		lblDOB= new JLabel("Date of Birth:");
		lblDOB.setBounds(20, 150,200, 50);
		lblDOB.setFont(new Font("Serif", Font.BOLD,25));
		add(lblDOB);
		
		dob = new JLabel();
		dob.setBounds(180, 150, 200, 50);
		dob.setText(list.get(1));
		dob.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		add(dob);

		lblAddress= new JLabel("Address:");
		lblAddress.setBounds(20, 200,200, 50);
		lblAddress.setFont(new Font("Serif", Font.BOLD,25));
		add(lblAddress);
		
		address = new JLabel();
		address.setBounds(180, 200, 200, 50);
		address.setText(list.get(2));
		address.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		add(address);
		
		lblphone= new JLabel("Phone_no:");
		lblphone.setBounds(20, 250,200, 50);
		lblphone.setFont(new Font("Serif", Font.BOLD,25));
		add(lblphone);
		
		phone = new JLabel();
		phone.setBounds(180, 250, 200, 50);
		phone.setText(list.get(3));
		phone.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		add(phone);
		
		lblemail= new JLabel("Email_ID:");
		lblemail.setBounds(20, 300,200, 50);
		lblemail.setFont(new Font("Serif", Font.BOLD,25));
		add(lblemail);
		
		email = new JLabel();
		email.setBounds(180, 300, 200, 50);
		email.setText(list.get(4));
		email.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		add(email);		
		
		btnIARecord = new JButton("    View IA       ");
		btnAttRecord = new JButton("View Attendance");
		btnResult = new JButton("    View Result    ");
		btnBack = new JButton("      Return       ");
		btnView = new JButton("View Previous Records");
		lblTitle = new JLabel("Student Record Details***");
		lblTitle.setBounds(200,20,600,25);
		lblTitle.setBackground(Color.pink);
		lblTitle.setFont(new Font("SANS_SERIF",Font.BOLD,30));	
		add(lblTitle);
		final JToolBar leftpanel = new JToolBar(JToolBar.VERTICAL);
		leftpanel.setBorder(new EmptyBorder(new Insets(100, 10, 20, 40)));		
		leftpanel.setBackground(Color.DARK_GRAY);
		btnIARecord.setMaximumSize(new Dimension(150, 30));
		btnIARecord.setBorder(BorderFactory.createEtchedBorder());
		leftpanel.add(btnIARecord);
		leftpanel.add(Box.createVerticalStrut(10));
		btnAttRecord.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnAttRecord);
		leftpanel.add(Box.createVerticalStrut(10));
		btnResult.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnResult);
		leftpanel.add(Box.createVerticalStrut(10));
		btnView.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnView);
		leftpanel.add(Box.createVerticalStrut(10));
		btnBack.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnBack);
		parentFrame.add(leftpanel, BorderLayout.WEST);
		
		semester=studentview.getSem(uSN);
		btnIARecord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			if(studentview.getData(uSN,semester).getDataVector().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"No result to show, Please update details","Message", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(previewpanel!=null)
				{
					previewpanel.setVisible(false);
					parentFrame.remove(previewpanel);
					revalidate();
				}
				if(pane!=null)
				{
					pane.setVisible(false);
					parentFrame.remove(pane);
					revalidate();
				}
				parentFrame.add(panel);
				
				iaTable = new JTable(studentview.getData(uSN,semester));	
				iaTable.setAutoCreateRowSorter(true);
				iaTable.setBackground(Color.green);
				iaTable.setPreferredScrollableViewportSize(new Dimension(400,200));
				iaTable.setAutoResizeMode(WHEN_FOCUSED);
				iaTable.setEditingRow(0);		
				if(iaTable.getRowCount()>0)
				{
					for(int i=0;i<iaTable.getRowCount();i++)
					{
						for(int j=0;j<iaTable.getColumnCount();j++)
						{
							if(iaTable.getModel().getValueAt(i, j).equals("-1"))
							{
								iaTable.getModel().setValueAt("A", i, j);
							}
						}
					}
				}
				JScrollPane scrollPane = new JScrollPane(iaTable);
				scrollPane.getViewport().add(iaTable);
				pane=new JPanel();
				pane.setBackground(Color.ORANGE);
				pane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createLineBorder(Color.black),
                        "Internals Report",
                        TitledBorder.CENTER,
                        TitledBorder.TOP));
		 		add(BorderLayout.EAST,pane);
				pane.setBounds(500, 100, 600, 300);
				
				pane.add(scrollPane);				
				setVisible(true);
				revalidate();		
				
				}
			}			
		});
				
		btnAttRecord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(studentview.getAttendance(uSN,semester).getDataVector().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"No Record to show, Please update details","Message", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(previewpanel!=null)
					{
						previewpanel.setVisible(false);
						parentFrame.remove(previewpanel);
						revalidate();
					}
					if(pane!=null)
					{
						pane.setVisible(false);
						parentFrame.remove(pane);
						revalidate();
					}
					parentFrame.add(panel);
					attendTable = new JTable(studentview.getAttendance(uSN,semester));	
					attendTable.setAutoCreateRowSorter(true);
					attendTable.setPreferredScrollableViewportSize(new Dimension(400,200));
					attendTable.setBackground(Color.green);				
					JScrollPane scrollPane = new JScrollPane(attendTable);
					scrollPane.getViewport().add(attendTable);
					 pane=new JPanel();				 
					 pane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createLineBorder(Color.black),
		                        "Attendance Report",
		                        TitledBorder.CENTER,
		                        TitledBorder.TOP));
					pane.setBackground(Color.orange);
			 		add(BorderLayout.EAST,pane);
					pane.setBounds(500, 100, 600, 300);					
					pane.add(scrollPane);				
					setVisible(true);
					revalidate();					
				}
				
			}
		});

		btnResult.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			try
			{
				if(studentview.getstudentResult(uSN,semester).getDataVector().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"No Record to show, Please update details","Message", JOptionPane.ERROR_MESSAGE);
				}
					
				else
				{
					if(previewpanel!=null)
					{
						previewpanel.setVisible(false);
						parentFrame.remove(previewpanel);
						revalidate();
					}
					if(pane!=null)
					{
						pane.setVisible(false);
						parentFrame.remove(pane);
						revalidate();
					}
					parentFrame.add(panel);								
					ArrayList<Integer> totalMarks = new ArrayList<Integer>();
					ArrayList<Integer> internalsMarks = new ArrayList<Integer>(); 
					ArrayList<Integer> externalsMarks = new ArrayList<Integer>();
					ArrayList<String> remark = new ArrayList<String>();
					StudentService service = new StudentService();
					StudentResult studentresult = new StudentResult(internalsMarks, externalsMarks, totalMarks,remark);
					studentresult = service.getAllResult(uSN,semester);
					internalsMarks = studentresult.getiAmarks();
					externalsMarks = studentresult.getExtMarks();
					totalMarks=studentresult.getTtlMarks();
					ResultAnalysis resAnalysis = new ResultAnalysis(internalsMarks, externalsMarks, totalMarks, uSN, semester);
					percent = resAnalysis.gettotalpercentage();
					remarks=resAnalysis.getpercentageremarks();
					resultTable = new JTable(studentview.getstudentResult(uSN,semester));	
					resultTable.setAutoCreateRowSorter(true);
					resultTable.setPreferredScrollableViewportSize(new Dimension(400,200));
					resultTable.setBackground(Color.green);
					if(resultTable.getRowCount()>0)
					{
						for(int i=0;i<resultTable.getRowCount();i++)
						{
							for(int j=0;j<resultTable.getColumnCount();j++)
							{
								if(resultTable.getModel().getValueAt(i, j).equals("-1"))
								{
									resultTable.getModel().setValueAt("A", i, j);
								}
							}
						}
					}
					JScrollPane scrollPane = new JScrollPane(resultTable);
					scrollPane.getViewport().add(resultTable);
					pane=new JPanel();
					pane.setBackground(Color.orange);
			 		add(BorderLayout.EAST,pane);		 		
					pane.setBounds(500, 100, 600, 300);				
					pane.add(scrollPane);
					pane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createLineBorder(Color.black),
		                        "Final Exam Report",
		                        TitledBorder.CENTER,
		                        TitledBorder.TOP));
					pane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createLineBorder(Color.black),
	                        "Remarks:"+ "   "+ "Total Percentage= " +  "   "+percent + "   "+ "Class Obtained: " +  "   "+remarks,
	                        TitledBorder.CENTER,
	                        TitledBorder.BOTTOM));
					setVisible(true);
					revalidate();	
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parentFrame.remove(panel);
				if(previewpanel!=null)
				{
					parentFrame.remove(previewpanel);
				}
				parentFrame.remove(leftpanel);
				ProctorPanel proctorpanel = new ProctorPanel(parentFrame, pName);
				parentFrame.add(proctorpanel);
				proctorpanel.setVisible(true);
				parentFrame.revalidate();
			}
		});		
		
		btnView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parentFrame.remove(panel);				
				previewpanel = new StudentPreviousViewPanel( parentFrame,row,proctorName);
				parentFrame.add(previewpanel);
				previewpanel.setVisible(true);
				parentFrame.revalidate();				
			}
		});

	}
}
	



