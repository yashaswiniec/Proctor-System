package cit.edu.pms.entity;

import java.util.ArrayList;

public class StudentResult {
	private ArrayList<Integer> iAmarks;
	private ArrayList<Integer> extMarks;
	private ArrayList<Integer> ttlMarks;
	private ArrayList<String> remarks;
	public StudentResult(ArrayList<Integer> iAmarks,
			ArrayList<Integer> extMarks, ArrayList<Integer> ttlMarks,
			ArrayList<String> remarks) {
		super();
		this.iAmarks = iAmarks;
		this.extMarks = extMarks;
		this.ttlMarks = ttlMarks;
		this.remarks = remarks;
	}
	public ArrayList<Integer> getiAmarks() {
		return iAmarks;
	}
	public void setiAmarks(ArrayList<Integer> iAmarks) {
		this.iAmarks = iAmarks;
	}
	public ArrayList<Integer> getExtMarks() {
		return extMarks;
	}
	public void setExtMarks(ArrayList<Integer> extMarks) {
		this.extMarks = extMarks;
	}
	public ArrayList<Integer> getTtlMarks() {
		return ttlMarks;
	}
	public void setTtlMarks(ArrayList<Integer> ttlMarks) {
		this.ttlMarks = ttlMarks;
	}
	public ArrayList<String> getRemarks() {
		return remarks;
	}
	public void setRemarks(ArrayList<String> remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "StudentResult [extMarks=" + extMarks + ", iAmarks=" + iAmarks
				+ ", remarks=" + remarks + ", ttlMarks=" + ttlMarks + "]";
	}
	
	
}