package cit.edu.pms.entity;

public class Proctor {
	
	private int proctorID;
	private String proctorName;
	public Proctor(int proctorID, String proctorName) {
		super();
		this.proctorID = proctorID;
		this.proctorName = proctorName;
	}
	public int getProctorID() {
		return proctorID;
	}
	public void setProctorID(int proctorID) {
		this.proctorID = proctorID;
	}
	public String getProctorName() {
		return proctorName;
	}
	public void setProctorName(String proctorName) {
		this.proctorName = proctorName;
	}
	@Override
	public String toString() {
		return this.proctorName;
	}
	
		

}
