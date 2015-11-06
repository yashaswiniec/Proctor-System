package cit.edu.pms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import cit.edu.pms.entity.Login;
import cit.edu.pms.view.ProctorPanel;
import cit.edu.pms.service.LoginService;

public class LoginPanel extends JPanel {
	private JButton submit;
	  private JPanel panel;
	  private JLabel labelName,labelPass,label;
	  final JTextField  textname,textpasswowd;	  
	 	 	  
	 public LoginPanel(final JFrame frame)
	   {
		    panel = this;
			setLayout(null);
			panel.setBackground(Color.white);
			panel.setBorder(BorderFactory.createEtchedBorder());			  
			
			label = new JLabel("Welcome to proctoral system");
			label.setBounds(400,50,650,50);
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Serif", Font.BOLD, 40));			
			add(label);
			
			labelName = new JLabel("USER NAME:");
			labelName.setBounds(380, 294, 200, 30);
			labelName.setFont(new Font("Serif", Font.BOLD, 15));
			add(labelName);
			
			textname = new JTextField(10);
			textname.setBounds(480, 296, 200, 30);
			add(textname);
			
			labelPass = new JLabel("PASSWORD:");
			labelPass.setBounds(380, 334, 200, 30);
			labelPass.setFont(new Font("Serif", Font.BOLD, 15));
			add(labelPass);
			textpasswowd = new JPasswordField(10);
			textpasswowd.setBounds(480, 336, 200, 30);
			add(textpasswowd);
			submit = new JButton("LOGIN");
			submit.setBounds(500, 380, 95, 30);
			add(submit);
			
			textname.setText("admin");
			textpasswowd.setText("admin");			
		
		  submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					String userName=textname.getText();
					String pass,role;				 
					pass = LoginService.getName(userName);
					role = LoginService.getRole(userName);
				   
				   if(pass!=null && pass.equals(textpasswowd.getText()))
				   {
				   if (role.equalsIgnoreCase("admin")) {
					   		AssignPanel assignPanel = new AssignPanel(frame);
					   		panel.setVisible(false);
					   		frame.remove(panel);
					   		frame.add(assignPanel);
					   		panel=null;
					   		revalidate();	
				   	}
				   else if (role.equalsIgnoreCase("proctor"))
					   
				   {
					   		Login users = new Login();
					   		users.setUserName(userName);
					   		ProctorPanel proctorpanel = new ProctorPanel(frame,userName);
					   		panel.setVisible(false);
					   		frame.remove(panel);
					   		frame.add(proctorpanel);
					   		panel = null;
					   		revalidate();
				   }
				   }
				   else{
					JOptionPane.showMessageDialog(null,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE);
				   }
				}					
		});
	 	   
	 } 
		
	}