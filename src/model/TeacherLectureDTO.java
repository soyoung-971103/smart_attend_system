package model;

public class TeacherLectureDTO {
	int id;
	String subject_name;
	String subject_id;
	TeacherDTO teacher_id;
	String _class;
	int number;
	int normstart;
	int normhour;
	String buildName;
	String ho;
	String roomName;
	int classification;
	
	public int getClassification() {
		return classification;
	}
	public void setClassification(int classification) {
		this.classification = classification;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getNormstart() {
		return normstart;
	}
	public void setNormstart(int normstart) {
		this.normstart = normstart;
	}
	public int getNormhour() {
		return normhour;
	}
	public void setNormhour(int normhour) {
		this.normhour = normhour;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	public TeacherDTO getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(TeacherDTO teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String get_class() {
		return _class;
	}
	public void set_class(String _class) {
		this._class = _class;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	
}
