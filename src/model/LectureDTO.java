package model;

public class LectureDTO {

	int id;
	int subject_id;
	int teacher_id ;
	String _class;
	byte number;
	SubjectDTO subject;
	TeacherDTO teacher;
	
	public byte getNumber() {
		return number;
	}
	public void setNumber(byte number) {
		this.number = number;
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
	
	
	
}
