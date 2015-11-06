package cit.edu.pms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public  class AssignPanel extends JPanel{
	
	private JButton btnAdd,btnAlter,btnAssign,btnlog,btnView,btnUpdatesem;	
	private JPanel panel;
	private JFrame parentFrame;
	private ProctorAssignPanel proctorAssignpanel = null;
	private StudentInfoPanel infopanel = null;
	private ProctorAlterPanel proctoralterpanel = null;
	private ProctorViewPanel proctorviewpanel = null;
	private StudentUpdatePanel studentUpdate;
	private JLabel lblTitle;
	
	public  AssignPanel(final JFrame frame)
	{
		panel = this;	
		this.parentFrame = frame;
		setLayout(new BorderLayout());
		lblTitle = new JLabel("Please add all students info before Assigning **");
		lblTitle.setBounds(50, 35, 200, 30);
		lblTitle.setFont(new Font("verdana", Font.ITALIC, 20));
		add(lblTitle,BorderLayout.PAGE_START);
		final JToolBar leftpanel = new JToolBar(JToolBar.VERTICAL);				
		leftpanel.setBorder(new EmptyBorder(new Insets(100, 10, 20, 40)));		
		leftpanel.setBackground(Color.DARK_GRAY);
		btnAdd = new JButton("AddStudentinfo  ");
		btnAssign = new JButton("AssignProctor");		
		btnAlter = new JButton("AlterProctor");	
		btnlog = new JButton("Logout       ");
		btnView = new JButton("View Proctor");
		btnUpdatesem = new JButton("UpdateStudentSem");		
		leftpanel.add(btnAdd);
		btnAdd.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(Box.createVerticalStrut(10));
		leftpanel.add(btnUpdatesem);
		btnUpdatesem.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(Box.createVerticalStrut(10));
		btnAssign.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnAssign);
		leftpanel.add(Box.createVerticalStrut(10));
		btnAlter.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnAlter);			
		leftpanel.add(Box.createVerticalStrut(10));
		btnView.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnView);	
		leftpanel.add(Box.createVerticalStrut(10));
		btnlog.setMaximumSize(new Dimension(150, 30));
		leftpanel.add(btnlog);	
		parentFrame.add(leftpanel, BorderLayout.WEST);
						
		btnAssign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("AssignProctor"))
				{
					if (proctoralterpanel!=null)
					{
						panel.remove(proctoralterpanel);
						proctoralterpanel.setVisible(false);
						proctoralterpanel = null;
					}
					if(infopanel!=null)
					{
						panel.remove(infopanel);
						infopanel.setVisible(false);
						infopanel = null;
					}
					if(proctorAssignpanel!= null)
					{
						panel.remove(proctorAssignpanel);
						proctorAssignpanel.setVisible(false);
						proctorAssignpanel = null;
					}
					if(proctorviewpanel!= null)
					{
						panel.remove(proctorviewpanel);
						proctorviewpanel.setVisible(false);
						proctorviewpanel = null;
					}
					if(studentUpdate!= null)
					{
						panel.remove(studentUpdate);
						studentUpdate.setVisible(false);
						studentUpdate= null;
					}
										
						proctorAssignpanel=  new ProctorAssignPanel(parentFrame);	
						add(proctorAssignpanel);
						proctorAssignpanel.setVisible(true);
						revalidate();
				}
			}
		});	
		
		btnlog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parentFrame.remove(panel);
				parentFrame.remove(leftpanel);				
				LoginPanel logpanel = new LoginPanel(parentFrame);
				parentFrame.add(logpanel);
				logpanel.setVisible(true);
				parentFrame.revalidate();
				
			}
		});

		btnAlter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (proctoralterpanel!=null)
				{
					panel.remove(proctoralterpanel);
					proctoralterpanel.setVisible(false);
					proctoralterpanel = null;
				}
				if(infopanel!=null)
				{
					panel.remove(infopanel);
					infopanel.setVisible(false);
					infopanel = null;
				}
				if(proctorAssignpanel!= null)
				{
					panel.remove(proctorAssignpanel);
					proctorAssignpanel.setVisible(false);
					proctorAssignpanel = null;
					
				}
				if(proctorviewpanel!= null)
				{
					panel.remove(proctorviewpanel);
					proctorviewpanel.setVisible(false);
					proctorviewpanel = null;
				}
				if(studentUpdate!= null)
				{
					panel.remove(studentUpdate);
					studentUpdate.setVisible(false);
					studentUpdate= null;
				}
				
					proctoralterpanel=  new ProctorAlterPanel(parentFrame);	
					add(proctoralterpanel);
					proctoralterpanel.setVisible(true);
					revalidate();
			
			}
	
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (proctoralterpanel!=null)
				{
					panel.remove(proctoralterpanel);
					proctoralterpanel.setVisible(false);
					proctoralterpanel = null;
				}
				if(infopanel!=null)
				{
					panel.remove(infopanel);
					infopanel.setVisible(false);
					infopanel = null;
				}
				if(proctorAssignpanel!= null)
				{
					panel.remove(proctorAssignpanel);
					proctorAssignpanel.setVisible(false);
					proctorAssignpanel = null;
				}
				if(proctorviewpanel!= null)
				{
					panel.remove(proctorviewpanel);
					proctorviewpanel.setVisible(false);
					proctorviewpanel = null;
				}
				if(studentUpdate!= null)
				{
					panel.remove(studentUpdate);
					studentUpdate.setVisible(false);
					studentUpdate= null;
				}
							
					infopanel = new StudentInfoPanel(parentFrame);
					add(infopanel);
					infopanel.setVisible(true);
					revalidate();				
				
			}
		});
		btnView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (proctoralterpanel!=null)
				{
					panel.remove(proctoralterpanel);
					proctoralterpanel.setVisible(false);
					proctoralterpanel = null;
				}
				if(infopanel!=null)
				{
					panel.remove(infopanel);
					infopanel.setVisible(false);
					infopanel = null;
				}
				if(proctorAssignpanel!= null)
				{
					panel.remove(proctorAssignpanel);
					proctorAssignpanel.setVisible(false);
					proctorAssignpanel = null;
				}
				if(proctorviewpanel!= null)
				{
					panel.remove(proctorviewpanel);
					proctorviewpanel.setVisible(false);
					proctorviewpanel = null;
				}
				if(studentUpdate!= null)
				{
					panel.remove(studentUpdate);
					studentUpdate.setVisible(false);
					studentUpdate= null;
				}
				
				proctorviewpanel = new ProctorViewPanel(parentFrame);
				add(proctorviewpanel);
				proctorviewpanel.setVisible(true);
				revalidate();
			}
		});
	
		btnUpdatesem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (proctoralterpanel!=null)
				{
					panel.remove(proctoralterpanel);
					proctoralterpanel.setVisible(false);
					proctoralterpanel = null;
				}
				if(infopanel!=null)
				{
					panel.remove(infopanel);
					infopanel.setVisible(false);
					infopanel = null;
				}
				if(proctorAssignpanel!= null)
				{
					panel.remove(proctorAssignpanel);
					proctorAssignpanel.setVisible(false);
					proctorAssignpanel = null;
				}
				if(proctorviewpanel!= null)
				{
					panel.remove(proctorviewpanel);
					proctorviewpanel.setVisible(false);
					proctorviewpanel = null;
				}
				if(studentUpdate!= null)
				{
					panel.remove(studentUpdate);
					studentUpdate.setVisible(false);
					studentUpdate= null;
				}
				
				studentUpdate = new StudentUpdatePanel(parentFrame);
				add(studentUpdate);
				studentUpdate.setVisible(true);
				revalidate();
			}
				
			
		});
		
	}	
}
