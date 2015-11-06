package cit.edu.pms.entity;

public class Student {
	
	
	private String usn;
	private String fullName;
	private int sem;
	private String sec;
	private String dob;
	private String address;
	private String phoneNo;
	private String email;
	private String bldgrp;
	private String dept;
	private String assigned;
	public Student(String usn, String fullName, int sem, String sec,
			String dob, String address, String phoneNo, String email,
			String bldgrp, String dept, String assigned) {
		super();
		this.usn = usn;
		this.fullName = fullName;
		this.sem = sem;
		this.sec = sec;
		this.dob = dob;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
		this.bldgrp = bldgrp;
		this.dept = dept;
		this.assigned = assigned;
	}
	public String getUsn() {
		return usn;
	}
	public void setUsn(String usn) {
		this.usn = usn;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getSem() {
		return sem;
	}
	public void setSem(int sem) {
		this.sem = sem;
	}
	public String getSec() {
		return sec;
	}
	public void setSec(String sec) {
		this.sec = sec;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBldgrp() {
		return bldgrp;
	}
	public void setBldgrp(String bldgrp) {
		this.bldgrp = bldgrp;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAssigned() {
		return assigned;
	}
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	
}