package model;

import java.util.ArrayList;

public class StudentLectureInfoDTO {
	StudentDTO stu;
	String depart;
	ArrayList<String> h;
	
	public StudentDTO getStu() {
		return stu;
	}
	public void setStu(StudentDTO stu) {
		this.stu = stu;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public ArrayList<String> getH() {
		return h;
	}
	public void setH(ArrayList<String> h) {
		this.h = h;
	}
}