package cit.edu.pms.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import cit.edu.pms.db.StudentDAO;
import cit.edu.pms.entity.Student;
import cit.edu.pms.entity.StudentAttendance;
import cit.edu.pms.entity.StudentIA;
import cit.edu.pms.entity.StudentResult;
import cit.edu.pms.entity.SubjectCode;

public class StudentService {
	StudentDAO dao ;
	
	/*public void insertIA(String usn,ArrayList<String> code,ArrayList<Integer> list)
	{
		dao= new StudentDAO();
		dao.insertIA(usn, code, list);
	}*/
	public int getSem(String usn)
	{
		dao= new StudentDAO();
		return dao.getSem(usn);
	}
	public SubjectCode insertCode(int sem, String dept)	{
		dao= new StudentDAO();
		return dao.insertCode(sem,dept);
	}
	public ArrayList<String> getStudentinfo(String usn)
	{
		dao= new StudentDAO();
		return dao.getStudentinfo(usn);
	}
	public void insertAttendance(String usn, int sem, ArrayList<String> code,ArrayList<Integer> list,ArrayList<Integer> alist)
	
	{
		dao= new StudentDAO();
		dao.insertAttendance(usn,sem, code, list, alist);
	}
	
	public ArrayList<Integer> getAverage(String usn, int semstr)
	{
		dao= new StudentDAO();
		return dao.getAverage(usn,semstr);
	}
	public void insertResult(String usn,int sem, ArrayList<String> code,ArrayList<Integer> list,ArrayList<Integer> avg)
	{
		dao= new StudentDAO();
		dao.insertResult(usn,sem, code, list, avg);
	}
	public void updateAttendance( String usn,int sem, ArrayList<String> code,ArrayList<Integer> list,ArrayList<Integer> alist,ArrayList<Float> percent)
	{
		dao= new StudentDAO();
		dao.updateAttendance(usn,sem, code, list, alist, percent);
	}
	
	public String getDept( String usn)
	{
		dao= new StudentDAO();
		return dao.getDept(usn);		
	}
		
	
	public void updateResult(String usn,int sem,ArrayList<String>code, ArrayList<Integer> internal,ArrayList<Integer> external,ArrayList<Integer> total,ArrayList<String> remark)
	{
		dao= new StudentDAO();
		dao.updateResult(usn,sem, code, internal, external, total, remark);
	}
	public String getResultUSN(String usn,int sem)
	{
		dao= new StudentDAO();
		return dao.getResultUSN(usn,sem);
	}
	
	public DefaultTableModel getStudentResult(String usn,int sem)
	{
		dao= new StudentDAO();
		return dao.getStudentResult(usn,sem);
	}
	public DefaultTableModel getData(String uSN, int sem)
	{
		dao= new StudentDAO();
		return dao.getData(uSN,sem);
	}
	public DefaultTableModel getAttendance(String uSN,int sem) 
	{
		dao= new StudentDAO();
		return dao.getAttendance(uSN,sem);
	}
	public DefaultTableModel getstudentResult(String uSN, int sem) 
	{
		dao= new StudentDAO();
		return dao.getStudentResult(uSN,sem);
	}
	
	public void updateIA(String usn,int sem,ArrayList<Integer> iA1,ArrayList<Integer> iA2,ArrayList<Integer> iA3,ArrayList<Float> average,ArrayList<String> code)
	{
		dao= new StudentDAO();
		dao.updateIA(usn, sem,iA1, iA2, iA3,average, code);
		
	}
	public void insertIA(String usn,int sem,ArrayList<Integer> iA1,ArrayList<Integer> iA2,ArrayList<Integer> iA3,ArrayList<Float> average, ArrayList<String> code)
	{
		dao= new StudentDAO();
		dao.insertIA(usn, sem,iA1, iA2, iA3,average, code);		
	}
	public String getStudentIAUSN(String usn, int sem)
	{
		dao= new StudentDAO();
		return dao.getStudentIAUSN(usn,sem);
		
	}
	
	public StudentIA getallIA(String usn,int sem )
	{
		dao=new StudentDAO();
		return dao.getallIA(usn,sem);
	}
	public StudentResult getAllResult(String usn,int sem)
	{
		dao=new StudentDAO();
		return dao.getAllResult(usn,sem);
	}
	public String getAttendanceUSN(String usn, int sem)
	{
		dao=new StudentDAO();
		return dao.getAttendanceUSN(usn,sem);
	}

	public StudentAttendance getAllAttendance(String usn,int sem)
	{
		dao=new StudentDAO();
		return dao.getAllAttendance(usn,sem);
		
	}
	
}


