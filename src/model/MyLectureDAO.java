package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class MyLectureDAO extends DAOBase{ 
	
	Connection conn = null;  
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<MyLectureDTO> dtoList = null;
	TimeTableDTO dtoTimetable = null;
	MyLectureDTO dto = null;
	RoomDTO dtoRoom = null;
	SubjectDTO dtoSubject = null;
	DepartDTO dtoDepart = null;
	TeacherDTO dtoTeacher = null;
	LectureDTO dtoLecture = null;
	HttpSession sesobj = null;
	
	public ArrayList<MyLectureDTO> Sdetail(HttpSession session){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String year   = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
			String month   = new java.text.SimpleDateFormat("MM").format(new java.util.Date());
			int m = Integer.parseInt(month);
			int hak=0;
			if(m>8)  hak=2;
			else  hak=1;
			dtoList = new ArrayList<MyLectureDTO>();
			rs = stmt.executeQuery("SELECT mylecture.*, timetable.weekday, timetable.istart, timetable.ihour, timetable.room_id, room.id, room.name, lecture.class, subject.grade, subject.ihour, subject.name, subject.depart_id, subject.yyyy, subject.term, teacher.id, teacher.name FROM mylecture INNER JOIN timetable ON mylecture.lecture_id=timetable.lecture_id LEFT JOIN room ON timetable.room_id = room.id LEFT JOIN lecture ON timetable.lecture_id = lecture.id LEFT JOIN subject ON subject.id = lecture.subject_id LEFT JOIN teacher ON teacher.id = lecture.teacher_id "
					+ "where student_id="+session.getAttribute("id")+" and subject.yyyy="+year+" and subject.term="+hak);
			while(rs.next()) {
				dto = new MyLectureDTO();
				dtoRoom = new RoomDTO();
				dtoLecture = new LectureDTO();
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dtoTimetable = new TimeTableDTO();
				dto.setId(rs.getInt(1));
				dto.setLecture_id(rs.getInt(2));
				dto.setTimetable(dtoTimetable);
				dtoTimetable.setWeekday(rs.getString(87));
				dtoTimetable.setIstart(rs.getByte(88));
				dtoTimetable.setIhour(rs.getByte(89));
				dtoTimetable.setRoom_id(rs.getInt(90));
				dtoRoom.setId(rs.getInt(91));
				dtoRoom.setName(rs.getString(92));
				dto.setRoom(dtoRoom);
				dtoLecture.set_class(rs.getString(93));
				dto.setLecture(dtoLecture);
				dtoSubject.setGrade(rs.getByte(94));
				dtoSubject.setIhour(rs.getByte(95));
				dtoSubject.setName(rs.getString(96));
				dtoSubject.setDepart_id(rs.getInt(97));
				dtoLecture.setSubject(dtoSubject);
				dtoTeacher.setId(rs.getInt(98));
				dtoTeacher.setName(rs.getString(99));
				dtoLecture.setTeacher(dtoTeacher);
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}
}