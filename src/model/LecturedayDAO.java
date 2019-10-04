package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LecturedayDAO extends DAOBase {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private LecturedayDTO dtoLectureday = null;
	private DepartDTO dtoDepart = null;
	private LectureDTO dtoLecture = null;
	private SubjectDTO dtoSubject = null;
	private RoomDTO dtoRoom = null;
	private TeacherDTO dtoTeacher = null;
	private ArrayList<LecturedayDTO> alLectureday = null;
	
	public ArrayList<LecturedayDTO> list(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs=stmt.executeQuery("SELECT lectureday.*, depart.name, teacher.name, subject.name, lecture.class, subject.grade, room.name FROM lectureday LEFT JOIN lecture ON lectureday.lecture_id = lecture.id LEFT JOIN subject ON subject.id = lecture.subject_id LEFT JOIN teacher ON teacher.id = lecture.teacher_id LEFT JOIN depart ON subject.depart_id=depart.id LEFT JOIN room ON lectureday.room_id=room.id");
			// email, pw는 form을 구성하는 각 요소의 이름
			alLectureday = new ArrayList<LecturedayDTO>();
			while(rs.next()) {
				dtoLectureday = new LecturedayDTO();
				dtoDepart = new DepartDTO();
				dtoLecture = new LectureDTO();
				dtoSubject = new SubjectDTO();
				dtoRoom = new RoomDTO();
				dtoTeacher = new TeacherDTO();
				dtoLectureday.setId(rs.getInt(1));
				dtoLectureday.setLecture_id(rs.getInt(2));;
				dtoLectureday.setRoom_id(rs.getInt(3));
				dtoLectureday.setTh(rs.getByte(4));
				dtoLectureday.setStarth(rs.getByte(5));
				dtoLectureday.setNormdate(rs.getDate(6));
				dtoLectureday.setNormstart(rs.getByte(7));
				dtoLectureday.setNormhour(rs.getByte(8));
				dtoLectureday.setNormstate(rs.getString(9));
				dtoLectureday.setRestdate(rs.getDate(10));
				dtoLectureday.setReststart(rs.getByte(11));
				dtoLectureday.setResthour(rs.getByte(12));
				dtoLectureday.setReststate(rs.getString(13));
				dtoLectureday.setState(rs.getString(14));
				dtoDepart.setName(rs.getString(15));
				dtoTeacher.setName(rs.getString(16));
				dtoSubject.setName(rs.getString(17));
				dtoLecture.set_class(rs.getString(18));
				dtoSubject.setGrade(rs.getByte(19)); 
				dtoRoom.setName(rs.getString(20));
				dtoLectureday.setDepart(dtoDepart);
				dtoLectureday.setTeacher(dtoTeacher);
				dtoLectureday.setSubject(dtoSubject);
				dtoLectureday.setLecture(dtoLecture);
				dtoLectureday.setRoom(dtoRoom);
				alLectureday.add(dtoLectureday);
			} return alLectureday;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return alLectureday;
	}
	
	
	
	public int update(HttpServletRequest request, HttpServletResponse response) {
		int result=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update lectureday set state='취소' where id=?");
			pstmt.setString(1, request.getParameter("id"));
	    	result = pstmt.executeUpdate(); // 질의를 통해 수정된 레코드의 수
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return result;
	}
}