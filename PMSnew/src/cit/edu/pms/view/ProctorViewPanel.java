package cit.edu.pms.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import cit.edu.pms.service.AdminService;

public class ProctorViewPanel extends JPanel{
	
	AdminService record = new AdminService();
	private JTable viewTable;
	private JPanel panel;
	
	public ProctorViewPanel (final JFrame frame)
	{
		panel = this;
		setVisible(true);
		setLayout(null);
		viewTable = new JTable(record.viewData());
		viewTable.setAutoCreateRowSorter(true);
		viewTable.setPreferredScrollableViewportSize(new Dimension(500,200));
		viewTable.setBorder(BorderFactory.createLineBorder(Color.black));
		viewTable.setBackground(Color.magenta);
		JScrollPane scrollPane = new JScrollPane(viewTable);
		scrollPane.getViewport().add(viewTable);
		panel=new JPanel();		
		panel.setBackground(Color.WHITE);
 		add(BorderLayout.CENTER,panel);
		panel.setBounds(300, 200, 600, 300);
		panel.add(scrollPane);
		panel.setBorder(BorderFactory.createTitledBorder (BorderFactory.createLineBorder(Color.black),
                "Proctor Allotment Details",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		setVisible(true);					
		revalidate();	
		
	}

}
