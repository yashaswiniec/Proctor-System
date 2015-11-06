package cit.edu.pms.service;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import cit.edu.pms.db.AssignDAO;
import cit.edu.pms.db.StudentDAO;
import cit.edu.pms.entity.Student;

public class AdminService {
	AssignDAO dao;
	
	public Vector  getsem()
	{
		dao = new AssignDAO();
		return dao.getsem();
	}
	public Vector  getsec()
	{
		dao = new AssignDAO();
		return dao.getsec();
	}
	public Vector  getallproctor()
	{
		dao = new AssignDAO();
		return dao.getallproctor();
	}
	public int getProctorID(int semester)
	{
		dao = new AssignDAO();
		return dao.getProctorID(semester);

	}
	
	public Vector getUSN(String sec, String sem )
	{
		dao = new AssignDAO();
		return dao.getUSN(sec, sem);
	}
	
	public DefaultTableModel  getData(String sec, String sem, String assgn )
	{
		dao = new AssignDAO();
		return dao.getData(sec, sem,assgn);
	}
		
	public void createProctor(ArrayList<String> name,String dept,ArrayList<String> usn, int proctorID, int semName, String sec, ArrayList<String> assgn)
	{
		dao = new AssignDAO();
		dao.createProctor(name,dept,usn, proctorID, semName, sec,assgn);
	}
	public DefaultTableModel  getProctordata(String name)
	{
		dao = new AssignDAO();
		return dao.getProctordata(name);
	}
	/*public void delete(ArrayList<String> usn)
	{
		dao.delete(usn);
	}*/
	public void alterproctor(int pID, ArrayList<String> usn)
	{
		dao = new AssignDAO();
		dao.alterproctor( pID,usn);
	}
	
	public ArrayList<String> getproctorusn(String pold)
	{
		dao = new AssignDAO();
		return dao.getproctorusn(pold);
	}

	public DefaultTableModel  viewData()
	{
		dao = new AssignDAO();
		return dao.viewData();
	}
	public void create (Student student)
	{
		 dao= new AssignDAO();
		 dao.create(student);
	}
	public Vector getDept()
	{
		dao=new AssignDAO();
		return dao.getDept();
	}
	public Vector getProctorDept()
	{
		dao=new AssignDAO();
		return dao.getProctorDept();		
	}
	
	public void updatestudentSem (int presem, int nextsem, String dept, String assgn, int iD)
	{
		dao=new AssignDAO();
		dao.updatestudentSem(presem, nextsem, dept,assgn,iD);
	}
	
	public String getName(String usn )
	{
		dao=new AssignDAO();
		return dao.getName(usn);
	}
	
	


}
