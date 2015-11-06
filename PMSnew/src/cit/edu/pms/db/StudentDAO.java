package cit.edu.pms.db;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import cit.edu.pms.entity.Student;
import cit.edu.pms.entity.StudentAttendance;
import cit.edu.pms.entity.StudentIA;
import cit.edu.pms.entity.StudentResult;
import cit.edu.pms.entity.SubjectCode;


public class StudentDAO {	
	
	public int getSem(String usn)
	{
		int sem = 0;
		Connection con = DBConnectionManager.getConnection();
		String sql= "select distinct sem from studentinfo where usn=?  ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usn);
			ResultSet rs = ps.executeQuery();
			
			while(rs!=null && rs.next())
			{
				sem = rs.getInt(1);				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return sem;
	}
	
	public SubjectCode insertCode(int sem, String dept)
	{
		Connection con = DBConnectionManager.getConnection();
			String sql=	"select subcode,iAMaxmarks,extmaxmarks from subjectcode where sem=? and dept=?";
			ArrayList<String> code = new ArrayList<String>();
			ArrayList<Integer> iAMax = new ArrayList<Integer>();
			ArrayList<Integer> extMax = new ArrayList<Integer>();
			SubjectCode subcode = null;			
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1, sem);
			ps.setString(2, dept);
			ResultSet rs = ps.executeQuery();
			while(rs!=null && rs.next())
			{
				String subCode;
				subCode= rs.getString(1);
				code.add(subCode);
				int iA,ext;
				iA=rs.getInt(2);
				iAMax.add(iA);
				ext=rs.getInt(3);
				extMax.add(ext);				
			}			
			subcode = new SubjectCode(code, iAMax, extMax);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subcode;
	}
		
			
		
		public ArrayList<String> getStudentinfo(String usn)
		{
			Connection con = DBConnectionManager.getConnection();
			String subcode;
			 
				String sql=	"select name,dob,address,phone_no,email_id from studentinfo where usn=?";
				ArrayList<String> info = new ArrayList<String>();
			try {
				
					PreparedStatement ps= con.prepareStatement(sql);
					ps.setString(1, usn);
					ResultSet rs = ps.executeQuery();
					while(rs!=null && rs.next())
					{
						
						subcode= rs.getString(1);
						info.add(subcode);
						subcode= rs.getString(2);
						info.add(subcode);
						subcode= rs.getString(3);
						info.add(subcode);
						subcode= rs.getString(4);
						info.add(subcode);
						subcode= rs.getString(5);
						info.add(subcode);
					}
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return info;			
			
		}
		public void insertAttendance(String usn, int sem, ArrayList<String> code,ArrayList<Integer> list,ArrayList<Integer> alist)
		{
			String sql = "insert into attendance (studentusn,sem,subcode,Total_classes,classes_attended) values(?,?,?,?,?)";
			Connection con = DBConnectionManager.getConnection();
			
			try 
			{
				PreparedStatement ps = con.prepareStatement(sql);
				for(int i=0;i<code.size();i++)
				{
					ps.setString(1, usn);
					ps.setInt(2,sem);
					ps.setString(3, code.get(i));
					ps.setInt(4, list.get(i));
					ps.setInt(5, alist.get(i));
					ps.execute();
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
					e.printStackTrace();
				}
			}
		}
		
		public ArrayList<Integer> getAverage(String usn, int sem)
		{
				Connection con = DBConnectionManager.getConnection();			
				String sql=	"select average from studentIAtable where student_usn=? and sem=?";
				ArrayList<Integer> avg = new ArrayList<Integer>();
			try {
					PreparedStatement ps= con.prepareStatement(sql);
					ps.setString(1, usn);
					ps.setInt(2, sem);
					ResultSet rs = ps.executeQuery();
					while(rs!=null && rs.next())
					{
						int ia;
						ia= rs.getInt(1);
						avg.add(ia);					
					}
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return avg;
		}
		
		public  void insertResult(String usn, int sem, ArrayList<String> code,ArrayList<Integer> list,ArrayList<Integer> avg)
		{
			String sql = "insert into studentresult (studentusn,sem,subcode,internals,externals) values(?,?,?,?,?)";
			Connection con = DBConnectionManager.getConnection();
			try 
			{				
				PreparedStatement ps = con.prepareStatement(sql);					
				for(int i=0; i<code.size(); i++)
				{
					ps.setString(1, usn);
					ps.setInt(2, sem);
					ps.setString(3, code.get(i));
					ps.setInt(4, avg.get(i));
					ps.setInt(5, list.get(i));
					ps.execute();					
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
					e.printStackTrace();
				}
			}					
		}
		
		
		public String getResultUSN( String uSN, int sem)
		{
			String usn = null;			
			String sql = "select studentusn from studentresult where studentusn=? and sem=?";
			Connection con = DBConnectionManager.getConnection();			
			try 
			{				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, uSN);
				ps.setInt(2, sem);
				ResultSet rs = ps.executeQuery();				
				while(rs!=null && rs.next())
				{
					usn=rs.getString(1);					
				}
				rs.close(); 
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
					e.printStackTrace();
				}
			
			}			
			return usn;		
		}
		
		public String getAttendanceUSN(String usN,int sem)
		{
			String usn = null;			
			String sql = "select studentusn from attendance where studentusn=? and sem=?";
			Connection con = DBConnectionManager.getConnection();
			
			try 
			{
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, usN);
				ps.setInt(2, sem);
				ResultSet rs = ps.executeQuery();				
				while(rs!=null && rs.next())
				{
					usn=rs.getString(1);					
				}
				rs.close(); 
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
					e.printStackTrace();
				}
			
			}
			
			return usn;
		
		}
		
		
		public void updateAttendance( String usn, int sem,ArrayList<String> code,ArrayList<Integer> list,ArrayList<Integer> alist,ArrayList<Float> percent)
		{
			String sql = "update attendance set Total_classes=?,classes_attended=?,percentage=? where subcode=? and studentusn=? and sem=?";
			Connection con = DBConnectionManager.getConnection();		
			try 
			{			
				PreparedStatement ps = con.prepareStatement(sql);	
				for(int i=0; i<code.size();i++)
				{
					ps.setInt(1, list.get(i));
					ps.setInt(2, alist.get(i));
					ps.setFloat(3,percent.get(i));
					ps.setString(4, code.get(i));
					ps.setString(5, usn);
					ps.setInt(6, sem);
					ps.executeUpdate();
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
					e.printStackTrace();
				}
			}
		}
		
		public void updateResult(String usn,int sem,ArrayList<String> code, ArrayList<Integer> internal,ArrayList<Integer> external,ArrayList<Integer> total,ArrayList<String> remark)
		{
			String sql = "update studentresult set internals=?, Externals =?,Total=?,remarks=? where subcode=? and studentusn=? and sem=?";
			Connection con = DBConnectionManager.getConnection();
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				for(int i=0;i<code.size();i++)
				{
					ps.setInt(1, internal.get(i));
					ps.setInt(2, external.get(i));
					ps.setInt(3, total.get(i));
					ps.setString(4, remark.get(i));
					ps.setString(5, code.get(i));
					ps.setString(6, usn);
					ps.setInt(7, sem);
					ps.executeUpdate();	
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public StudentResult getAllResult(String usn, int sem)
		{
			ArrayList<Integer> intern = new ArrayList<Integer>();
			ArrayList<Integer> extern = new ArrayList<Integer>();
			ArrayList<Integer> ttl = new ArrayList<Integer>();
			ArrayList<String> remrks = new ArrayList<String>();
			StudentResult studentresult = null;
			String query = "select internals,externals,Total,remarks from studentresult where studentusn=? and sem=?";
			Connection con = DBConnectionManager.getConnection();
			try {
				
				PreparedStatement ps = con.prepareStatement(query);	
				ps.setString(1, usn);
				ps.setInt(2, sem);
				ResultSet rs = ps.executeQuery();
				while(rs!=null && rs.next())
				{
					int ia1,ia2,total;
					String rmk;
					ia1=rs.getInt(1);
					intern.add(ia1);					
					ia2=rs.getInt(2);
					extern.add(ia2);
					total = rs.getInt(3);
					ttl.add(total);
					rmk = rs.getString(4);
					remrks.add(rmk);
							
				}
				studentresult = new StudentResult(intern, extern,ttl,remrks);
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return studentresult;
		}
		
		
		public DefaultTableModel getData(String usn, int sem)
		{
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Subcode");
			columnNames.add("IA1");
			columnNames.add("IA2");
			columnNames.add("IA3");
			columnNames.add("Average");			
			String sql = "select subcode,IA1,IA2,IA3,average from StudentIATable where student_usn =? and sem=?";
			Connection con = DBConnectionManager.getConnection();
			Vector<String> row ;
			Vector<Vector<String>> data = new Vector<Vector<String>>();			
			try
			{
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, usn);
				ps.setInt(2, sem);
				ResultSet rs = ps.executeQuery();
				while(rs!=null && rs.next())
				{
					row =  new Vector<String>();
					row.add(rs.getString(1));
					row.add(rs.getString(2));
					row.add(rs.getString(3));
					row.add(rs.getString(4));
					row.add(String.valueOf(rs.getInt(5)));
					data.add(row);					
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
			return new DefaultTableModel(data,columnNames);
			
		}
		
		public DefaultTableModel getAttendance(String usn, int sem)
		{
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Subcode");
			columnNames.add("Total Classes");
			columnNames.add("Classes Attended");
			columnNames.add("Percentage");
			String sql = "select subcode,Total_classes,Classes_Attended,percentage from Attendance where studentusn =? and sem=?";
			Connection con = DBConnectionManager.getConnection();
			Vector<String> row ;
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			
			try
			{
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, usn);
				ps.setInt(2, sem);
				ResultSet rs = ps.executeQuery();
				while(rs!=null && rs.next())
				{
					row =  new Vector<String>();
					row.add(rs.getString(1));
					row.add(String.valueOf(rs.getInt(2)));
					row.add(String.valueOf(rs.getInt(3)));
					row.add(String.valueOf(rs.getInt(4)));
					data.add(row);					
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
			return new DefaultTableModel(data,columnNames);
			
		}
		
		public DefaultTableModel getStudentResult(String usn, int sem)
		{
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Subcode");
			columnNames.add("Internals");
			columnNames.add("Externals");
			columnNames.add("Total");
			columnNames.add("Remarks");
			String sql = "select subcode,Internals,Externals,Total,Remarks from studentresult where studentusn =? and sem=?";
			Connection con = DBConnectionManager.getConnection();
			Vector<String> row ;
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			
			try
			{
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, usn);
				ps.setInt(2, sem);
				ResultSet rs = ps.executeQuery();
				while(rs!=null && rs.next())
				{
					row =  new Vector<String>();
					row.add(rs.getString(1));
					row.add(String.valueOf(rs.getInt(2)));
					row.add(String.valueOf(rs.getInt(3)));
					row.add(String.valueOf(rs.getInt(4)));
					row.add(rs.getString(5));
					data.add(row);
					
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
			return new DefaultTableModel(data,columnNames);
		}
			
		public String getDept( String usn)
		{
			String dept = null;
			Vector<String> departmnt = new Vector<String>();
			Connection con = DBConnectionManager.getConnection();
			String sql= "select dept from studentinfo where usn=?";
			try {
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, usn);
						ResultSet rs = ps.executeQuery();
						
						while(rs!=null && rs.next())
						{
							dept = rs.getString(1);
							
						}
						rs.close();
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return dept;
		}

		
		public void insertIA(String usn,int sem, ArrayList<Integer> iA1,ArrayList<Integer> iA2,ArrayList<Integer> iA3,ArrayList<Float> avg,ArrayList<String> code)
		{
			
			String sql = "insert into studentiatable ( ia1,ia2,ia3,average,subcode,student_usn,sem) values (?,?,?,?,?,?,?)";
			Connection con = DBConnectionManager.getConnection();
			try {
				PreparedStatement ps= con.prepareStatement(sql);
				for(int i=0;i<code.size();i++)
				{
					ps.setInt(1, iA1.get(i));
					ps.setInt(2, iA2.get(i));
					ps.setInt(3, iA3.get(i));
					ps.setFloat(4, avg.get(i));
					ps.setString(5, code.get(i));
					ps.setString(6, usn);
					ps.setInt(7, sem);
					ps.executeUpdate();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		public void updateIA(String usn,int sem,ArrayList<Integer> iA1,ArrayList<Integer> iA2,ArrayList<Integer> iA3,ArrayList<Float> avg,ArrayList<String> code)
		{
			String sql = "update studentiatable set ia1 =?,ia2=?,ia3=?,average=? where subcode=? and student_usn=? and sem=?";
			Connection con = DBConnectionManager.getConnection();
			try {
				PreparedStatement ps= con.prepareStatement(sql);
				for(int i=0;i<code.size();i++)
				{
					ps.setInt(1, iA1.get(i));
					ps.setInt(2, iA2.get(i));
					ps.setInt(3, iA3.get(i));
					ps.setFloat(4, avg.get(i));
					ps.setString(5, code.get(i));
					ps.setString(6, usn);
					ps.setInt(7, sem);
					ps.executeUpdate();
				}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public String getStudentIAUSN(String usn,int sem)
		{
			String uSN = null;
			String query = "select distinct student_usn from studentiatable where student_usn=? and sem=?";
			Connection con = DBConnectionManager.getConnection();
			try {
				
				PreparedStatement ps1 = con.prepareStatement(query);
				ps1.setString(1, usn);
				ps1.setInt(2, sem);
				ResultSet rs = ps1.executeQuery();
				while(rs!=null && rs.next())
				{
					uSN = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uSN;
				
		}
		
		public StudentIA getallIA(String usn, int sem)
		{
			ArrayList<Integer> iA1 = new ArrayList<Integer>();
			ArrayList<Integer> iA2 = new ArrayList<Integer>();
			ArrayList<Integer> iA3 = new ArrayList<Integer>();
			ArrayList<Float> avg = new ArrayList<Float>();
			StudentIA studentIA = null;
			String query = "select IA1,IA2,IA3,Average from studentiatable where student_usn=?and sem=?";
			Connection con = DBConnectionManager.getConnection();
			try {
				
				PreparedStatement ps = con.prepareStatement(query);	
				ps.setString(1, usn);
				ps.setInt(2, sem);
				ResultSet rs = ps.executeQuery();
				while(rs!=null && rs.next())
				{
					int ia1,ia2,ia3;
					float average;
					ia1=rs.getInt(1);
					iA1.add(ia1);
					ia2=rs.getInt(2);
					iA2.add(ia2);
					ia3=rs.getInt(3);
					iA3.add(ia3);
					average = rs.getInt(4);
					avg.add(average);				
				}
				
				 studentIA = new StudentIA(iA1, iA2, iA3, avg);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return studentIA;
			
		}
		
		public StudentAttendance getAllAttendance(String usn, int sem)
		{
			ArrayList<Integer> totalclass = new ArrayList<Integer>();
			ArrayList<Integer> present= new ArrayList<Integer>();
			ArrayList<Float> percent= new ArrayList<Float>();
			System.out.println(usn);
			StudentAttendance attend = null;
			String query = "select Total_classes,Classes_attended,percentage from attendance where studentusn=? and sem=?";
			Connection con = DBConnectionManager.getConnection();
			try {
				
				PreparedStatement ps = con.prepareStatement(query);	
				ps.setString(1, usn);
				ps.setInt(2, sem);
				ResultSet rs = ps.executeQuery();
				while(rs!=null && rs.next())
				{
					int ttlclass,ttlpresent;
					float ttlpercent;
					ttlclass=rs.getInt(1);
					totalclass.add(ttlclass);
					ttlpresent=rs.getInt(2);
					present.add(ttlpresent);
					ttlpercent = rs.getFloat(3);
					percent.add(ttlpercent);
									
				}
				attend = new StudentAttendance(totalclass, present, percent);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return attend;
		}		
}




