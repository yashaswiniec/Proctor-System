package cit.edu.pms.entity;

import java.util.ArrayList;

public class StudentIA {
	
	private ArrayList<Integer> iA1;
	private ArrayList<Integer> iA2;
	private ArrayList<Integer> iA3;
	private ArrayList<Float> average;
	public StudentIA(ArrayList<Integer> iA1, ArrayList<Integer> iA2,
			ArrayList<Integer> iA3, ArrayList<Float> average) {
		super();
		this.iA1 = iA1;
		this.iA2 = iA2;
		this.iA3 = iA3;
		this.average = average;
	}
	public ArrayList<Integer> getiA1() {
		return iA1;
	}
	public void setiA1(ArrayList<Integer> iA1) {
		this.iA1 = iA1;
	}
	public ArrayList<Integer> getiA2() {
		return iA2;
	}
	public void setiA2(ArrayList<Integer> iA2) {
		this.iA2 = iA2;
	}
	public ArrayList<Integer> getiA3() {
		return iA3;
	}
	public void setiA3(ArrayList<Integer> iA3) {
		this.iA3 = iA3;
	}
	public ArrayList<Float> getAverage() {
		return average;
	}
	public void setAverage(ArrayList<Float> average) {
		this.average = average;
	}
	@Override
	public String toString() {
		return "StudentIA [average=" + average + ", iA1=" + iA1 + ", iA2="
				+ iA2 + ", iA3=" + iA3 + "]";
	}
		
}
