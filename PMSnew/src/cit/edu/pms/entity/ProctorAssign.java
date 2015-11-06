package cit.edu.pms.entity;

public class ProctorAssign {
	
	private int proctorID;
	private int userID;
	private int semester;
	private String section;
	public ProctorAssign(int proctorID, int userID, int semester, String section) {
		super();
		this.proctorID = proctorID;
		this.userID = userID;
		this.semester = semester;
		this.section = section;
	}
	public int getProctorID() {
		return proctorID;
	}
	public void setProctorID(int proctorID) {
		this.proctorID = proctorID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	

}
