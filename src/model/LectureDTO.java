package model;

public class LectureDTO {
	int id;
	int subject_id;
	int teacher_id;
	SubjectDTO subject;
	TeacherDTO teacher;	
	String lecture_class;
	String _class; //min
	int number;
	
	DepartDTO depart;
	LectureDTO lecture;
	
	public String get_class() {
		return _class;
	}
	public void set_class(String _class) {
		this._class = _class;
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public SubjectDTO getSubject() {
		return subject;
	}
	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}
	public TeacherDTO getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherDTO teacher) {
		this.teacher = teacher;
	}
	public String getLecture_class() {
		return lecture_class;
	}
	public void setLecture_class(String lecture_class) {
		this.lecture_class = lecture_class;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public DepartDTO getDepart() {
		return depart;
	}
	public void setDepart(DepartDTO depart) {
		this.depart = depart;
	}
	public LectureDTO getLecture() {
		return lecture;
	}
	public void setLecture(LectureDTO lecture) {
		this.lecture = lecture;
	}
}
