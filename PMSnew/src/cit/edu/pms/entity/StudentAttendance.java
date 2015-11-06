package cit.edu.pms.entity;

import java.util.ArrayList;

public class StudentAttendance {
	
	private ArrayList<Integer> totalClass;
	private ArrayList<Integer> totalPresent;
	private ArrayList<Float> percentage;
	public StudentAttendance(ArrayList<Integer> totalClass,
			ArrayList<Integer> totalPresent, ArrayList<Float> percentage) {
		super();
		this.totalClass = totalClass;
		this.totalPresent = totalPresent;
		this.percentage = percentage;
	}
	public ArrayList<Integer> getTotalClass() {
		return totalClass;
	}
	public void setTotalClass(ArrayList<Integer> totalClass) {
		this.totalClass = totalClass;
	}
	public ArrayList<Integer> getTotalPresent() {
		return totalPresent;
	}
	public void setTotalPresent(ArrayList<Integer> totalPresent) {
		this.totalPresent = totalPresent;
	}
	public ArrayList<Float> getPercentage() {
		return percentage;
	}
	public void setPercentage(ArrayList<Float> percentage) {
		this.percentage = percentage;
	}
	@Override
	public String toString() {
		return "StudentAttendance [percentage=" + percentage + ", totalClass="
				+ totalClass + ", totalPresent=" + totalPresent + "]";
	}
	
	

}
