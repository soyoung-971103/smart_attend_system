package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class LecturedayDAO extends DAOBase{
	
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<LecturedayDTO> dtoList = null;
	LecturedayDTO dto = null;
	HttpSession sesobj = null;
	RoomDTO dtoRoom = null;
	LectureDTO dtoLecture = null;
	SubjectDTO dtoSubject = null;
	TeacherDTO dtoTeacher = null;
	DepartDTO dtoDepart = null;
	
	
	public int create(LecturedayDTO dto) {
		int result = 0;	
				
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into lectureday(lecture_id, room_id, th, normdate, normstart, normhour, normstate) " + 
					"values(?, ?, ?, ?, ?, ?, ?)");
			
			pstmt.setInt(1, dto.getLecture_id());
			pstmt.setInt(2, dto.getRoom_id()); 
			pstmt.setByte(3, dto.getTh());
			pstmt.setDate(4, new java.sql.Date(dto.getNormdate().getTime()));
			pstmt.setByte(5, dto.getNormstart());
			pstmt.setByte(6, dto.getNormhour());
			pstmt.setString(7, dto.getNormstate());
			
			result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return result;
	}
	
	public int delete(int id) {
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from lectureday where id=? ");
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();	
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return result;			
	}
	
	public ArrayList<LecturedayDTO> List(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			dtoList = new ArrayList<LecturedayDTO>();
			rs = stmt.executeQuery("select lectureday.*, room.name, lecture.class, subject.grade, subject.name, teacher.name, depart.name FROM lectureday LEFT JOIN room ON lectureday.room_id = room.id LEFT JOIN lecture ON lectureday.lecture_id = lecture.id LEFT JOIN subject ON subject.id = lecture.subject_id LEFT JOIN teacher ON teacher.id = lecture.teacher_id LEFT JOIN depart ON depart.id = subject.depart_id");
			while(rs.next()) {
				dto = new LecturedayDTO();
				dtoRoom = new RoomDTO();
				dtoLecture = new LectureDTO();
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setLecture_id(rs.getInt(2));
				dto.setRoom_id(rs.getByte(3));
				dto.setTh(rs.getByte(4));
				dto.setClassification(rs.getByte(5));
				dto.setNormdate(rs.getDate(6));
				dto.setNormstart(rs.getByte(7));
				dto.setNormhour(rs.getByte(8));
				dto.setNormstate(rs.getString(9));
				dto.setRestdate(rs.getDate(10));
				dto.setReststart(rs.getByte(11));
				dto.setResthour(rs.getByte(12));
				dto.setReststate(rs.getString(13));
				dto.setState(rs.getString(14));
				dtoRoom.setName(rs.getString(15));
				dto.setRoom(dtoRoom);
				dtoLecture.setLecture_class(rs.getString(16));
				dto.setLecture(dtoLecture);
				dtoSubject.setGrade(rs.getByte(17));
				dtoSubject.setName(rs.getString(18));
				dtoLecture.setSubject(dtoSubject);
				dtoTeacher.setName(rs.getString(19));
				dtoLecture.setTeacher(dtoTeacher);
				dtoDepart.setName(rs.getString(20));
				dto.setDepart(dtoDepart);
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
