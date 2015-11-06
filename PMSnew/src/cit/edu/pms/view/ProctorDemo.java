package cit.edu.pms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import cit.edu.pms.db.LoginDAO;

public class ProctorDemo extends JFrame {
		
		public ProctorDemo()
		{
			setTitle("Proctor Management System");
			setSize(getMaximumSize());
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			add(new LoginPanel(this));
			setVisible(true);			
			
		}
		public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ProctorDemo admindemo=new ProctorDemo();
				admindemo.setLocationRelativeTo(null);
			}
		});
		}
	}


