package model;
import java.util.Date;

public class LecturedayDTO {
	int id;
	int lecture_id;
	int room_id;
	byte th;
	byte starth;
	Date normdate;
	byte normstart;
	byte normhour;
	String normstate;
	Date restdate;
	byte reststart;
	byte resthour;
	String reststate;
	String state;
	DepartDTO depart;
	LectureDTO lecture;
	SubjectDTO subject;
	RoomDTO room;
	TeacherDTO teacher;
	
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
	public SubjectDTO getSubject() {
		return subject;
	}
	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}
	public RoomDTO getRoom() {
		return room;
	}
	public void setRoom(RoomDTO room) {
		this.room = room;
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
	public int getLecture_id() {
		return lecture_id;
	}
	public void setLecture_id(int lecture_id) {
		this.lecture_id = lecture_id;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public byte getTh() {
		return th;
	}
	public void setTh(byte th) {
		this.th = th;
	}
	public byte getStarth() {
		return starth;
	}
	public void setStarth(byte starth) {
		this.starth = starth;
	}
	public Date getNormdate() {
		return normdate;
	}
	public void setNormdate(Date normdate) {
		this.normdate = normdate;
	}
	public byte getNormstart() {
		return normstart;
	}
	public void setNormstart(byte normstart) {
		this.normstart = normstart;
	}
	public byte getNormhour() {
		return normhour;
	}
	public void setNormhour(byte normhour) {
		this.normhour = normhour;
	}
	public String getNormstate() {
		return normstate;
	}
	public void setNormstate(String normstate) {
		this.normstate = normstate;
	}
	public Date getRestdate() {
		return restdate;
	}
	public void setRestdate(Date restdate) {
		this.restdate = restdate;
	}
	public byte getReststart() {
		return reststart;
	}
	public void setReststart(byte reststart) {
		this.reststart = reststart;
	}
	public byte getResthour() {
		return resthour;
	}
	public void setResthour(byte resthour) {
		this.resthour = resthour;
	}
	public String getReststate() {
		return reststate;
	}
	public void setReststate(String reststate) {
		this.reststate = reststate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}	
}
