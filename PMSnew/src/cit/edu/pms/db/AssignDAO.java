package cit.edu.pms.db;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import cit.edu.pms.entity.Proctor;
import cit.edu.pms.entity.Student;


public class AssignDAO {

	public void create(Student s)
	{
		String sql = "insert into Studentinfo (usn,name,sem,sec,DOB,Address,Phone_no,Email_ID,BloodGroup,dept,assigned) values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = DBConnectionManager.getConnection();
		try 
		{
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setString(1,s.getUsn());
			ps.setString(2,s.getFullName());
			ps.setInt(3,s.getSem());
			ps.setString(4, s.getSec());
			ps.setString(5, s.getDob());
			ps.setString(6,s.getAddress());
			ps.setString(7,s.getPhoneNo());
			ps.setString(8,s.getEmail());
			ps.setString(9,s.getBldgrp());
			ps.setString(10, s.getDept());
			ps.setString(11, s.getAssigned());
			ps.execute();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		public void updatestudentSem (int presem, int nextsem, String dept, String assign, int procID)
		{
			Connection con = DBConnectionManager.getConnection();
			String delete= "delete from Assignproctor where proctorID=?";
			String sql= "update studentinfo set sem=?, dept=?, assigned=? where sem=? ";
			try {
				PreparedStatement ps1 = con.prepareStatement(delete);
				ps1.setInt(1, procID);
				ps1.execute();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, nextsem);
				ps.setString(2, dept);
				ps.setString(3, assign);
				ps.setInt(4, presem);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		public Vector  getsem()
		{
			String sem;
			Vector semester = new Vector();
			Connection con = DBConnectionManager.getConnection();
			String sql= "select distinct sem from studentinfo ";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs!=null && rs.next())
				{
					sem= rs.getString(1);
					semester.add(sem);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return semester;
		}
		
		public Vector<String>  getsec()
		{
			String sec;
			Vector<String>section = new Vector<String>();
			Connection con = DBConnectionManager.getConnection();
			String sql= "select distinct sec from studentinfo ";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs!=null && rs.next())
				{
					sec = rs.getString(1);
					section.add(sec);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return section;
		}
		
		public Vector  getallproctor()
			{
				
				Vector proctor = new Vector();
				Connection con = DBConnectionManager.getConnection();
				String sql= "select ID, name from proctor ";
				try {
							PreparedStatement ps = con.prepareStatement(sql);
							ResultSet rs = ps.executeQuery();
							while(rs!=null && rs.next())
							{
								Proctor p = new Proctor(rs.getInt(1), rs.getString(2));
								proctor.add(p);
							}
							rs.close();
					} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return proctor;
			}
					
		public Vector<String>  getUSN(String sec, String sem )
			{
				String prc;
				Vector<String> usn = new Vector<String>();
				Connection con = DBConnectionManager.getConnection();
				String sql= "select USN from studentinfo where section=? and sem=? ";
				try {
							PreparedStatement ps = con.prepareStatement(sql);
							ps.setString(1, sec);
							ps.setString(2, sem);
							ResultSet rs = ps.executeQuery();
							while(rs!=null && rs.next())
							{
								prc = rs.getString(1);
								usn.add(prc);
							}
							rs.close();
					} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return usn;
			}
		
		public String  getName(String usn )
		{
			String name = null;
			
			Connection con = DBConnectionManager.getConnection();
			String sql= "select name from studentinfo where usn=?";
			try {
						PreparedStatement ps = con.prepareStatement(sql);						
						ps.setString(1,usn);						
						ResultSet rs = ps.executeQuery();
						while(rs!=null && rs.next())
						{
							name = rs.getString(1);							
						}
						rs.close();
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return name;
		}

	
			public DefaultTableModel  getData(String sec, String sem, String assign )
				{
					Vector<String> prc;
					Vector<Vector<String>> usn = new Vector<Vector<String>>();
					Vector <String>colNames = new Vector<String>();
					colNames.add(" USN List   ");
					Connection con = DBConnectionManager.getConnection();
					String sql= "select USN from studentinfo where sec=? and sem=? and assigned=? ";
						try {
							PreparedStatement ps = con.prepareStatement(sql);
							ps.setString(1, sec);
							ps.setString(2, sem);
							ps.setString(3, assign);
							ResultSet rs = ps.executeQuery();
							while(rs!=null && rs.next())
							{
								prc = new Vector<String>();
								prc.add(rs.getString(1));
								usn.add(prc);
							}
							rs.close();
							} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
						
						return new DefaultTableModel(usn,colNames);
				}
				
				public void createProctor(ArrayList<String> name,String dept,ArrayList<String> usn, int proctorID, int semName, String sec, ArrayList<String> assign)
					{
						String sql = "insert into Assignproctor (proctorID,dept,usn,studentname,semester,section) values(?,?,?,?,?,?)";					
						String insert = "update Studentinfo set Assigned=? where usn=?";
						Connection con = DBConnectionManager.getConnection();					
						try 
						{
							PreparedStatement ps = con.prepareStatement(sql);
							PreparedStatement ps1 = con.prepareStatement(insert);
												
							for(int i=0;i<usn.size();i++)
							{
								ps.setInt(1,proctorID);
								ps.setString(2, dept);
								ps.setString(3, usn.get(i));
								ps.setString(4, name.get(i));
								ps.setInt(5,semName);
								ps.setString(6, sec);								
								ps.execute();	
								ps1.setString(1, assign.get(i));
								ps1.setString(2,usn.get(i));
								ps1.executeUpdate();
							}
						} 
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						finally
						{
							try {
								con.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
							
					}
					
			public DefaultTableModel  getProctordata(String name)
				{
						Vector<String> prc;
						Vector<Vector<String>> usn = new Vector<Vector<String>>();
						Vector <String>colNames = new Vector<String>();
						colNames.add(" Student USN ");
						colNames.add("Student Name");
						Connection con = DBConnectionManager.getConnection();
						String sql= "select a.usn,a.studentname, b.name from Assignproctor a, proctor b where b.id = a.proctorid and b.name=? ";
						try {
							PreparedStatement ps = con.prepareStatement(sql);
							ps.setString(1, name);
							ResultSet rs = ps.executeQuery();
							while(rs!=null && rs.next())
							{
								prc = new Vector<String>();
								prc.add(rs.getString(1));								
								prc.add(rs.getString(2));
								usn.add(prc);
							}
							rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return new DefaultTableModel(usn,colNames);
				}
			
			public ArrayList<String> getproctorusn(String pold)
			{
				
				ArrayList<String> proctorUSN= new ArrayList<String>();
				Connection con = DBConnectionManager.getConnection();
				String sql= "select a.usn, b.name from Assignproctor a, proctor b where b.id = a.proctorid and b.name=? ";
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, pold);
					ResultSet rs = ps.executeQuery();
					while(rs!=null && rs.next())
					{
						String usn=null;
						usn = rs.getString(1);
						proctorUSN.add(usn);
					}
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return proctorUSN;
								
			}
			
			public void alterproctor(int iD, ArrayList<String> usn)
			{
				
				Connection con = DBConnectionManager.getConnection();
				String updateTable = "Update assignproctor set ProctorID=? where usn=?";
				try {
					PreparedStatement ps = con.prepareStatement(updateTable);
					for(int i=0; i<usn.size();i++)
					{
						ps.setInt(1, iD);
						ps.setString(2, usn.get(i));
						ps.executeUpdate();
					}				
					}
					
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally
					{
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}	
			
			
			public DefaultTableModel  viewData()
			{
				Vector row;
				Vector<Vector<String>> data = new Vector<Vector<String>>();				
				Vector <String>colNames = new Vector<String>();
				colNames.add("Department");
				colNames.add(" Proctor Name   ");
				colNames.add(" Assigned USN");
				colNames.add("Semester");
				colNames.add("Section");
				
				Connection con = DBConnectionManager.getConnection();
				String sql= "select distinct a.dept,a.name, b.usn,b.semester , b.section from proctor a, assignproctor b where b.proctorid=a.id ";
					try {
						PreparedStatement ps = con.prepareStatement(sql);
						ResultSet rs = ps.executeQuery();
						while(rs!=null && rs.next())
						{
							row = new Vector<String>();
							row.add(rs.getString(1));
							row.add(rs.getString(2));
							row.add(rs.getString(3));
							row.add(rs.getInt(4));							
							row.add(rs.getString(5));
							data.add(row);
						}
						rs.close();
						} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					
					return new DefaultTableModel(data,colNames);
			}
			
			public Vector getDept()
			{
				String dept;
				Vector<String> departmnt = new Vector<String>();
				Connection con = DBConnectionManager.getConnection();
				String sql= "select distinct dept from subjectcode";
				try {
							PreparedStatement ps = con.prepareStatement(sql);
							ResultSet rs = ps.executeQuery();
							while(rs!=null && rs.next())
							{
								dept = rs.getString(1);
								departmnt.add(dept);
							}
							rs.close();
					} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return departmnt;

			}
			
			public Vector getProctorDept()
			{
				String dept;
				Vector<String> departmnt = new Vector<String>();
				Connection con = DBConnectionManager.getConnection();
				String sql= "select distinct dept from proctor";
				try {
							PreparedStatement ps = con.prepareStatement(sql);
							ResultSet rs = ps.executeQuery();
							while(rs!=null && rs.next())
							{
								dept = rs.getString(1);
								departmnt.add(dept);
							}
							rs.close();
					} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return departmnt;

			}
			
			public int getProctorID(int semester)
			{
				int pID = 0;
				Connection con = DBConnectionManager.getConnection();
				String sql= "select distinct proctorID from assignproctor where semester=?";
				try {
							PreparedStatement ps = con.prepareStatement(sql);
							ps.setInt(1, semester);
							ResultSet rs = ps.executeQuery();
							
							while(rs!=null && rs.next())
							{
								pID = rs.getInt(1);
							}
							rs.close();
					} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return pID;

			}

			
			
			
			
			
			
			
	}

	
