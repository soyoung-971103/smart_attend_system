package model;

public class TimeTableDTO {
	int id;
	int lecture_id;
	String weekday;
	byte istart; 
	byte ihour;
	int room_id;
	
	LectureDTO lecture;
	SubjectDTO subject; 
	TeacherDTO teacher;
	RoomDTO room;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLecture_id() {
		return lecture_id;
	}
	public void setLecture_id(int lecture_id) {
		this.lecture_id = lecture_id;
	}
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	public byte getIstart() {
		return istart;
	}
	public void setIstart(byte istart) {
		this.istart = istart;
	}
	public byte getIhour() {
		return ihour;
	}
	public void setIhour(byte ihour) {
		this.ihour = ihour;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public LectureDTO getLecture() {
		return lecture;
	}
	public void setLecture(LectureDTO lecture) {
		this.lecture = lecture;
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
	public RoomDTO getRoom() {
		return room;
	}
	public void setRoom(RoomDTO room) {
		this.room = room;
	}
}
