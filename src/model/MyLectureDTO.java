package model;

import java.util.ArrayList;
import java.util.Date;

public class MyLectureDTO {
	
	int id;
	int student_id;
	StudentDTO student;
	int lecture_id;
	LectureDTO lecture;
	String departname;
	byte grade;
	byte term;
	byte iattend;
	byte imiddle;
	byte ilast;
	byte inormal;
	byte ipractice;
	byte itotal;
	float ipoint;
	String igrade;
	byte retake;
	byte ilate;
	int ixhour;
	byte qakind;
	Date qaday;
	String qatitle;
	String qaask;
	String qaanswer;
	ArrayList<Byte> hn;
	TimeTableDTO timetable;
	RoomDTO room;
	
	public ArrayList<Byte> getHn() {
		return hn;
	}
	public void setHn(ArrayList<Byte> hn) {
		this.hn = hn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	public int getLecture_id() {
		return lecture_id;
	}
	public void setLecture_id(int lecture_id) {
		this.lecture_id = lecture_id;
	}
	public LectureDTO getLecture() {
		return lecture;
	}
	public void setLecture(LectureDTO lecture) {
		this.lecture = lecture;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	public byte getGrade() {
		return grade;
	}
	public void setGrade(byte grade) {
		this.grade = grade;
	}
	public byte getTerm() {
		return term;
	}
	public void setTerm(byte term) {
		this.term = term;
	}
	public byte getIattend() {
		return iattend;
	}
	public void setIattend(byte iattend) {
		this.iattend = iattend;
	}
	public byte getImiddle() {
		return imiddle;
	}
	public void setImiddle(byte imiddle) {
		this.imiddle = imiddle;
	}
	public byte getIlast() {
		return ilast;
	}
	public void setIlast(byte ilast) {
		this.ilast = ilast;
	}
	public byte getInormal() {
		return inormal;
	}
	public void setInormal(byte inormal) {
		this.inormal = inormal;
	}
	public byte getIpractice() {
		return ipractice;
	}
	public void setIpractice(byte ipractice) {
		this.ipractice = ipractice;
	}
	public byte getItotal() {
		return itotal;
	}
	public void setItotal(byte itotal) {
		this.itotal = itotal;
	}
	public float getIpoint() {
		return ipoint;
	}
	public void setIpoint(float ipoint) {
		this.ipoint = ipoint;
	}
	public String getIgrade() {
		return igrade;
	}
	public void setIgrade(String igrade) {
		this.igrade = igrade;
	}
	public byte getRetake() {
		return retake;
	}
	public void setRetake(byte retake) {
		this.retake = retake;
	}
	public byte getIlate() {
		return ilate;
	}
	public void setIlate(byte ilate) {
		this.ilate = ilate;
	}
	public int getIxhour() {
		return ixhour;
	}
	public void setIxhour(int ixhour) {
		this.ixhour = ixhour;
	}
	public byte getQakind() {
		return qakind;
	}
	public void setQakind(byte qakind) {
		this.qakind = qakind;
	}
	public Date getQaday() {
		return qaday;
	}
	public void setQaday(Date qaday) {
		this.qaday = qaday;
	}
	public String getQatitle() {
		return qatitle;
	}
	public void setQatitle(String qatitle) {
		this.qatitle = qatitle;
	}
	public String getQaask() {
		return qaask;
	}
	public void setQaask(String qaask) {
		this.qaask = qaask;
	}
	public String getQaanswer() {
		return qaanswer;
	}
	public void setQaanswer(String qaanswer) {
		this.qaanswer = qaanswer;
	}
	public TimeTableDTO getTimetable() {
		return timetable;
	}
	public void setTimetable(TimeTableDTO timetable) {
		this.timetable = timetable;
	}
	public RoomDTO getRoom() {
		return room;
	}
	public void setRoom(RoomDTO room) {
		this.room = room;
	
	
	
}
