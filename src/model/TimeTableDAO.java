package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TimeTableDAO extends DAOBase {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private TimeTableDTO timetable = null;
	private SubjectDTO subject = null;
	private TeacherDTO teacher = null;
	private RoomDTO room = null;
	private ArrayList<TimeTableDTO> alTimeTable = null;
	
	public ArrayList<TimeTableDTO> list(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			rs=stmt.executeQuery("select timetable.*, lecture.subject_id, lecture.teacher_id, "
					+ "subject.name, subject.grade, subject.term, subject.yyyy, "
					+ "teacher.name, room.name FROM timetable " 
					+ "LEFT JOIN lecture ON timetable.lecture_id=lecture.id "
					+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
					+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
					+ "LEFT JOIN room ON timetable.room_id=room.id");
			// email, pw는 form을 구성하는 각 요소의 이름
			alTimeTable = new ArrayList<TimeTableDTO>();
			while(rs.next()) {
				timetable = new TimeTableDTO();
				subject = new SubjectDTO();
				teacher = new TeacherDTO();
				room = new RoomDTO();
				timetable.setId(rs.getInt(1));
				timetable.setLecture_id(rs.getInt(2));
				subject.setName(rs.getString(3));
				subject.setGrade(rs.getByte(4));
				subject.setTerm(rs.getByte(5));
				subject.setYyyy(rs.getInt(6));
				timetable.setSubject(subject);
				teacher.setName(rs.getString(7));
				timetable.setTeacher(teacher);
				room.setName(rs.getString(8));
				timetable.setRoom(room);
				timetable.setWeekday(rs.getString(9));
				timetable.setIstart(rs.getByte(10));
				timetable.setIhour(rs.getByte(11));
				timetable.setRoom_id(rs.getInt(12));
				alTimeTable.add(timetable);
			} return alTimeTable;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return alTimeTable;
	}
}
