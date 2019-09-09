package model;

import java.util.ArrayList;

public class StudentLectureInfoDTO {
	StudentDTO stu;
	String depart;
	int iattend;	//출석 점수
	int ilate;	//지각 시간
	int ixhour;	//결석 시간
	ArrayList<String> h;
	
	public StudentDTO getStu() {
		return stu;
	}
	public int getIattend() {
		return iattend;
	}
	public void setIattend(int iattend) {
		this.iattend = iattend;
	}
	public int getIlate() {
		return ilate;
	}
	public void setIlate(int ilate) {
		this.ilate = ilate;
	}
	public int getIxhour() {
		return ixhour;
	}
	public void setIxhour(int ixhour) {
		this.ixhour = ixhour;
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