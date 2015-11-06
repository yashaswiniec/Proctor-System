package cit.edu.pms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import cit.edu.pms.entity.Login;
import cit.edu.pms.view.LoginPanel;
import cit.edu.pms.service.AdminService;

public class ProctorPanel extends JPanel {

	private JPanel panel;
	private JLabel label,noteLabel;
	private String proctorName;
	private JButton  btnIA, btnAttendance, btnResult,btnRecord,btnlogout;
	private JTable listTable;
	AdminService adminRecord = new AdminService();
	private LoginPanel loginpanel=null;
	private JFrame parentFrame;
	private JToolBar leftpanel;
	private StudentIAPanel iaPanel=null;
	private StudentAttendancePanel attendPanel=null;
	private StudentResultPanel resultPanel=null;
	private StudentInfoPanel infoPanel=null;
	
	public ProctorPanel( JFrame frame, String userName)
	{
		this.parentFrame = frame;
		this.proctorName = userName;
		panel = this;
		setLayout(null);
		label = new JLabel("Please click on USN and select the field to enter Student Details**");
		label.setBounds(300, 50, 700, 30);
		label.setFont(new Font("Serif", Font.ITALIC, 20));	
		add(label);
		setLayout(new BorderLayout());		
		leftpanel = new JToolBar(JToolBar.VERTICAL);		
		leftpanel.setBorder(new EmptyBorder(new Insets(100, 10, 20, 40)));		
		leftpanel.setBackground(Color.DARK_GRAY);		
		leftpanel.add(Box.createVerticalStrut(10));		
		btnIA = new JButton("ADD/UPDATE IA");
		btnIA.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnIA);
		leftpanel.add(Box.createVerticalStrut(10));
		btnAttendance = new JButton("ADD/UPDATE ATTENDANCE");
		btnAttendance.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnAttendance);
		leftpanel.add(Box.createVerticalStrut(10));
		btnResult = new JButton("ADD/UPDATE RESULT");
		btnResult.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnResult);
		leftpanel.add(Box.createVerticalStrut(10));
		btnRecord = new JButton("View Record");	
		btnRecord.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnRecord);
		leftpanel.add(Box.createVerticalStrut(10));
		btnlogout = new JButton("Logout");
		btnlogout.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnlogout);		
		add(BorderLayout.WEST,leftpanel);		
		listTable = new JTable(adminRecord.getProctordata(proctorName));	
		listTable.setAutoCreateRowSorter(true);
		listTable.setPreferredScrollableViewportSize(new Dimension(400,380));
		JScrollPane scrollPane = new JScrollPane(listTable);		
		scrollPane.getViewport().add(listTable);
		listTable.setBackground(Color.cyan);
		JPanel pane=new JPanel();		
 		add(BorderLayout.EAST,pane);
		pane.setBounds(700, 100, 400, 452);
		pane.add(scrollPane);
		setVisible(true);
		revalidate();	
		MyButtonListener listener = new MyButtonListener();
		btnIA.addActionListener(listener);
		btnAttendance.addActionListener(listener);
		btnResult.addActionListener(listener);
		btnRecord.addActionListener(listener);
		btnlogout.addActionListener(listener);
	}
	class MyButtonListener implements ActionListener
		{
			@Override
		public void actionPerformed(ActionEvent e)
		{
			
			if(e.getActionCommand().equals("ADD/UPDATE IA"))
				{
							
					int index=listTable.getSelectedRow();					
					if(index!=-1)
					{
						Vector<Vector<String>> data = ((DefaultTableModel)listTable.getModel()).getDataVector();
						Vector<String> row = data.get(index);
						
						if( attendPanel!=null)
						{
							parentFrame.remove(attendPanel);
							attendPanel.setVisible(false);
							attendPanel=null;
							
							parentFrame.revalidate();
						}
						if(resultPanel!=null)
						{
							parentFrame.remove(resultPanel);
							resultPanel.setVisible(false);
							resultPanel=null;
							
						}
						if(iaPanel!=null)
						{
							parentFrame.remove(iaPanel);
							iaPanel.setVisible(false);
							iaPanel=null;
						}
						if(infoPanel!=null)
						{
							parentFrame.remove(infoPanel);
							infoPanel.setVisible(false);
							infoPanel=null;
							
						}
						iaPanel = new StudentIAPanel(parentFrame,row);
						label.setVisible(false);
						add(iaPanel);
						iaPanel.setVisible(true);
						revalidate();
					}
				
					else	
					{
						JOptionPane.showMessageDialog(panel,"Please select a row", "Message", JOptionPane.ERROR_MESSAGE);
					} 
				}				
				
				if(e.getActionCommand().equals("ADD/UPDATE ATTENDANCE"))
				{
					int index=listTable.getSelectedRow();					
					if(index!=-1)
					{
						Vector<Vector<String>> data = ((DefaultTableModel)listTable.getModel()).getDataVector();
						
							Vector<String> row = data.get(index);
							if( attendPanel!=null)
							{
								parentFrame.remove(attendPanel);
								attendPanel.setVisible(false);
								attendPanel=null;
								
								parentFrame.revalidate();
							}
							if(resultPanel!=null)
							{
								parentFrame.remove(resultPanel);
								resultPanel.setVisible(false);
								resultPanel=null;
								
							}
							if(iaPanel!=null)
							{
								parentFrame.remove(iaPanel);
								iaPanel.setVisible(false);
								iaPanel=null;
							}
							if(infoPanel!=null)
							{
								parentFrame.remove(infoPanel);
								infoPanel.setVisible(false);
								infoPanel=null;
								
							}
							attendPanel = new StudentAttendancePanel(parentFrame, row);
							label.setVisible(false);
							add(attendPanel);
							attendPanel.setVisible(true);
							revalidate();			
					
					}		
					else	
					{
						JOptionPane.showMessageDialog(panel,"Please select a row", "Message", JOptionPane.ERROR_MESSAGE);
					} 
								
				}
				
				if(e.getActionCommand().equals("ADD/UPDATE RESULT"))
				{
					int index=listTable.getSelectedRow();					
					if(index!=-1)
					{
						Vector<Vector<String>> data = ((DefaultTableModel)listTable.getModel()).getDataVector();
						Vector<String> row = data.get(index);
						if( attendPanel!=null)
						{
							parentFrame.remove(attendPanel);
							attendPanel.setVisible(false);
							attendPanel=null;
							
							parentFrame.revalidate();
						}
						if(resultPanel!=null)
						{
							parentFrame.remove(resultPanel);
							resultPanel.setVisible(false);
							resultPanel=null;
							
						}
						if(iaPanel!=null)
						{
							parentFrame.remove(iaPanel);
							iaPanel.setVisible(false);
							iaPanel=null;
						}
						if(infoPanel!=null)
						{
							parentFrame.remove(infoPanel);
							infoPanel.setVisible(false);
							infoPanel=null;
							
						}
						
						resultPanel = new StudentResultPanel(parentFrame, row);
						label.setVisible(false);
						add(resultPanel);
						resultPanel.setVisible(true);
						revalidate();						
					}		
					else	
					{
						JOptionPane.showMessageDialog(panel,"Please select a row", "Message", JOptionPane.ERROR_MESSAGE);
					} 
								
				}				
				
				else if(e.getActionCommand().equals("View Record"))
				{
					int index=listTable.getSelectedRow();
					if(index!=-1)
					{
						Vector<Vector<String>> data = ((DefaultTableModel)listTable.getModel()).getDataVector();
						Vector<String> row = data.get(index);
						StudentViewPanel viewPanel = new StudentViewPanel(parentFrame,row,proctorName);
						parentFrame.remove(panel);						
						parentFrame.add(viewPanel);
						viewPanel.setVisible(true);							
						parentFrame.revalidate();					
					}									
					else	
					{
						JOptionPane.showMessageDialog(panel,"Please select a row", "Message", JOptionPane.ERROR_MESSAGE);
					}
									
				}
				
				if(e.getActionCommand().equals("Logout"))
				{
					parentFrame.remove(panel);
					parentFrame.remove(leftpanel);
					LoginPanel loginpanel = new LoginPanel(parentFrame);
					parentFrame.add(loginpanel);
					loginpanel.setVisible(true);
					parentFrame.revalidate();
				}
			
			}
					
		}
							
	}
		

	


