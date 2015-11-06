package cit.edu.pms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cit.edu.pms.entity.Student;
import cit.edu.pms.service.AdminService;
import cit.edu.pms.service.StudentService;

public class StudentInfoPanel extends JPanel{
	
	private JButton btn;
	private JLabel lbl, lblFullname, lblUSN,lblsem, lblDOB, lblAdd, lblPhone, lblEmail, lblBldgrp,lbldept;
	private JTextField txtFullname, txtUSN, txtsem,txtsec,txtDOB, txtAdd, txtPhone, txtEmail, txtBldgrp;	
	private JPanel panel;
	private Vector dept;
	private JComboBox deptcombo;
	AdminService record = new AdminService();
	private JFrame parentFrame;
	private String departmnt; 
	public StudentInfoPanel(final JFrame frame)
	{
		//this.uSN = usn.firstElement();	
		this.parentFrame = frame;
		panel = this;
		setLayout(null);
		lbl = new JLabel(" Please enter Details in the below fields**");
		lbl.setBounds(100, 35, 600, 30);
		lbl.setFont(new Font("verdana", Font.BOLD, 20));
		add(lbl);
		
		lbl = new JLabel(" Note: Fields marked with * are mondatory");
		lbl.setBounds(100, 65, 600, 30);
		lbl.setFont(new Font("verdana", Font.BOLD, 15));
		lbl.setForeground(Color.red);
		add(lbl);
		
		lblFullname = new JLabel("Full Name:");
		lblFullname.setBounds(200, 100, 150, 30);
		lblFullname.setFont(new Font("Serif", Font.BOLD, 15));
		add(lblFullname);
		
		lblFullname = new JLabel("*");
		lblFullname.setBounds(270, 100, 150, 30);
		lblFullname.setFont(new Font("Serif", Font.BOLD, 20));
		lblFullname.setForeground(Color.red);
		add(lblFullname);
		
		txtFullname = new JTextField(10);
		txtFullname.setBounds(350, 100, 150, 30);
		add(txtFullname);
		
		lblUSN = new JLabel("USN:");
		lblUSN.setBounds(200, 150, 150, 30);
		lblUSN.setFont(new Font("Serif", Font.BOLD, 15));
		add(lblUSN);
		
		lblUSN = new JLabel("*");
		lblUSN.setBounds(240, 150, 150, 30);
		lblUSN.setFont(new Font("Serif", Font.BOLD, 20));
		lblUSN.setForeground(Color.red);
		add(lblUSN);	
		
		txtUSN = new JTextField(10);
		txtUSN.setBounds(350, 150, 150, 30);
		add(txtUSN);
		
		lblsem = new JLabel("Sem&Section");
		lblsem.setBounds(200,200,150,30);
		lblsem.setFont(new Font("Serif", Font.BOLD, 15));
		add(lblsem);
		
		lblsem = new JLabel("*");
		lblsem.setBounds(290, 200, 150, 30);
		lblsem.setFont(new Font("Serif", Font.BOLD, 20));
		lblsem.setForeground(Color.red);
		add(lblsem);
		
		txtsem = new JTextField(10);
		txtsem.setBounds(350, 200, 60, 30);
		add(txtsem);
		
		txtsec = new JTextField(10);
		txtsec.setBounds(420, 200, 60, 30);
		add(txtsec);
		
		lblDOB = new JLabel("DOB:");
		lblDOB.setBounds(200, 250, 150, 30);
		lblDOB.setFont(new Font("Serif", Font.BOLD, 15));
		add(lblDOB);
		
		txtDOB = new JTextField(10);
		txtDOB.setBounds(350, 250, 150, 30);
		add(txtDOB);
		
		lblAdd = new JLabel("Address:");
		lblAdd.setBounds(200, 300, 150, 30);
		lblAdd.setFont(new Font("Serif", Font.BOLD, 15));
		add(lblAdd);
		
		txtAdd = new JTextField(10);
		txtAdd.setBounds(350, 300, 150, 30);
		add(txtAdd);
		
		lblPhone = new JLabel("Phone NO:");
		lblPhone.setBounds(200,350, 150, 30);
		lblPhone.setFont(new Font("Serif", Font.BOLD, 15));
		add(lblPhone);
		
		txtPhone = new JTextField(10);
		txtPhone.setBounds(350, 350, 150, 30);
		add(txtPhone);
		
		lblEmail=new JLabel("EmailID:");
		lblEmail.setBounds(200, 400, 150, 30);
		lblEmail.setFont(new Font("Serif", Font.BOLD, 15));
		add(lblEmail);
		
		txtEmail = new JTextField(10);
		txtEmail.setBounds(350, 400, 150, 30);
		add(txtEmail);
		
		lblBldgrp = new JLabel("Blood Group:");
		lblBldgrp.setBounds(200, 450, 150, 30);
		lblBldgrp.setFont(new Font("Serif", Font.BOLD, 15));
		add(lblBldgrp);
		
		txtBldgrp = new JTextField(10);
		txtBldgrp.setBounds(350, 450, 150, 30);
		add(txtBldgrp);
		
		btn = new JButton("SUBMIT");
		btn.setBounds(360, 550, 120, 30);
		btn.setBorder(BorderFactory.createEtchedBorder());
		btn.setForeground(Color.black);
		btn.setBackground(Color.cyan);
		add(btn);
		setVisible(true);
		revalidate();
		
		dept = record.getDept();
		lbldept = new JLabel("Select Dept");
		lbldept.setBounds(200, 500, 120, 30);
		lbldept.setFont(new Font("Serif", Font.BOLD, 15));
		add(lbldept);
		
		lbldept = new JLabel("*");
		lbldept.setBounds(280, 500, 150, 30);
		lbldept.setFont(new Font("Serif", Font.BOLD, 20));
		lbldept.setForeground(Color.red);
		add(lbldept);
		
		deptcombo = new JComboBox(dept);
		deptcombo.setBounds(350, 500, 100, 30);
		add(deptcombo);
		deptcombo.setSelectedIndex(-1);
		deptcombo.addItemListener(new ItemListener() {
		
			@Override
			public void itemStateChanged(ItemEvent ie) {
				if(ie.getStateChange()==ItemEvent.SELECTED)
				{
					departmnt = (String) deptcombo.getSelectedItem();
				}
				
			}
		});
		
		btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//try
				//{		
					if(txtFullname.getText()!=null&&txtUSN.getText()!=null&&txtsec.getText()!=null&&txtsem.getText()!=null&&departmnt!=null)
					{
						int sem = Integer.parseInt(txtsem.getText());
						String fname = txtFullname.getText();
						String usn = txtUSN.getText();
						String sec = txtsec.getText();
						String dob = txtDOB.getText();
						String add = txtAdd.getText();
						String pno = txtPhone.getText();
						String email = txtEmail.getText();
						String bldgrp = txtBldgrp.getText();
						String assign = "No";
						Student student = new Student(usn, fname, sem, sec, dob, add, pno, email, bldgrp,departmnt,assign);
						record.create(student);
						JOptionPane.showMessageDialog(null,"Student Record inserted Successfully","Message", JOptionPane.INFORMATION_MESSAGE);
						txtFullname.setText(null);
						txtUSN.setText(null);
						txtsem.setText(null);
						txtsec.setText(null);
						txtAdd.setText(null);
						txtBldgrp.setText(null);
						txtDOB.setText(null);
						txtEmail.setText(null);
						txtPhone.setText(null);
										
					}	
					
					else
					{
						JOptionPane.showMessageDialog(null,"Make sure fields should not be empty","Message", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		
	}
	
	

}
