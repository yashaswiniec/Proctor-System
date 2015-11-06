package cit.edu.pms.entity;

import java.util.ArrayList;

public class SubjectCode {
	
	private ArrayList<String> subcode;
	private ArrayList<Integer>intMaxMarks;
	private ArrayList<Integer>extMaxMarks;
	public SubjectCode(ArrayList<String> subcode,
			ArrayList<Integer> intMaxMarks, ArrayList<Integer> extMaxMarks) {
		super();
		this.subcode = subcode;
		this.intMaxMarks = intMaxMarks;
		this.extMaxMarks = extMaxMarks;
	}
	public ArrayList<String> getSubcode() {
		return subcode;
	}
	public void setSubcode(ArrayList<String> subcode) {
		this.subcode = subcode;
	}
	public ArrayList<Integer> getIntMaxMarks() {
		return intMaxMarks;
	}
	public void setIntMaxMarks(ArrayList<Integer> intMaxMarks) {
		this.intMaxMarks = intMaxMarks;
	}
	public ArrayList<Integer> getExtMaxMarks() {
		return extMaxMarks;
	}
	public void setExtMaxMarks(ArrayList<Integer> extMaxMarks) {
		this.extMaxMarks = extMaxMarks;
	}
	
	
	
	}
