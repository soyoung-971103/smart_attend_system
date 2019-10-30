package model;

import java.util.Date;

public class QnaDTO {
	int id;
	int student_id;
	int lecture_id;
	Date day;
	String qatitle;
	String qaask;
	String qaanswer;
	byte c_confirm;
	int teacher_id;
	StudentDTO student;
	LectureDTO lecture;
	TeacherDTO teacher;
	
	
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	public LectureDTO getLecture() {
		return lecture;
	}
	public void setLecture(LectureDTO lecture) {
		this.lecture = lecture;
	}
	public TeacherDTO getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherDTO teacher) {
		this.teacher = teacher;
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
	public int getLecture_id() {
		return lecture_id;
	}
	public void setLecture_id(int lecture_id) {
		this.lecture_id = lecture_id;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
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
	public byte getC_confirm() {
		return c_confirm;
	}
	public void setC_confirm(byte c_confirm) {
		this.c_confirm = c_confirm;
	}
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	
	
}
