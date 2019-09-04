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
	private LectureDTO lecture = null;
	private SubjectDTO subject = null;
	private TeacherDTO teacher = null;
	private RoomDTO room = null;
	private ArrayList<TimeTableDTO> alTimeTable = null;
	
	public ArrayList<TimeTableDTO> detail (int id) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from timetable left join lecture on timetable.lecture_id=lecture.id left join subject on lecture.subject_id=subject.id LEFT JOIN teacher ON lecture.teacher_id=teacher.id where teacher.id=" + id);
			alTimeTable = new ArrayList<TimeTableDTO>();
			if(rs.next()) {
				timetable = new TimeTableDTO();
				lecture = new LectureDTO();
				subject = new SubjectDTO();
				teacher = new TeacherDTO();
				room = new RoomDTO();
				timetable.setId(rs.getInt(1));
				timetable.setLecture(lecture);
				timetable.setLecture_id(rs.getInt(2));
				timetable.setWeekday(rs.getString(3));
				timetable.setIstart(rs.getByte(4));
				timetable.setIhour(rs.getByte(5));
				timetable.setRoom_id(rs.getInt(6));
				timetable.setLecture(lecture);
				lecture.setLec_class(rs.getString(7));
				lecture.setSubject(subject);
				subject.setGrade(rs.getByte(8));
				subject.setName(rs.getString(9));
				timetable.setTeacher(teacher);
				teacher.setName(rs.getString(10));
				timetable.setRoom(room);
				room.setName(rs.getString(11));
				alTimeTable.add(timetable);
			}
			return alTimeTable;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
			return alTimeTable;
	}
}
