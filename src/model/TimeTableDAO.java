package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;


public class TimeTableDAO extends DAOBase {
	
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;  
	ArrayList<TimeTableDTO> dtoList = null;
	TimeTableDTO dto = null;
	HttpSession sesobj = null;
	RoomDTO dtoRoom = null;
	LectureDTO dtoLecture = null;
	SubjectDTO dtoSubject = null;
	TeacherDTO dtoTeacher = null;
	DepartDTO dtoDepart = null;
	
	
	public ArrayList<TimeTableDTO> Load(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoList = new ArrayList<TimeTableDTO>();
			rs = stmt.executeQuery("SELECT timetable.*, room.id, room.name, lecture.class, subject.grade, subject.ihour, subject.name, subject.depart_id, teacher.id, teacher.name FROM timetable LEFT JOIN room ON timetable.room_id = room.id LEFT JOIN lecture ON timetable.lecture_id = lecture.id LEFT JOIN subject ON subject.id = lecture.subject_id LEFT JOIN teacher ON teacher.id = lecture.teacher_id");
			while(rs.next()) {
				// 번호^학년^반^시간^요일^시작교시^시간^과목명^교수님번호^교수님^강의실번호^강의실
				///번호 id
				///학년 leture -> subject -> grade
				///반  leture - > class
				///시간 leture -> subject - > ihour
				///요일 Weekday
				///시작교시 istart
				///시간 ihour
				///과목명 lecture -> subject -> name
				//교수님번호 lectrue -> teacher -> id
				//교수님 lectrue -> teacher -> name
				///강의실번호 room -> id
				///강의실 room -> name
				dto = new TimeTableDTO();
				dtoRoom = new RoomDTO();
				dtoLecture = new LectureDTO();
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dto.setId(rs.getInt(1));
				dto.setLecture_id(rs.getInt(2));
				dto.setWeekday(rs.getString(3));
				dto.setIstart(rs.getByte(4));
				dto.setIhour(rs.getByte(5));
				dto.setRoom_id(rs.getInt(6));
				dtoRoom.setId(rs.getInt(7));
				dtoRoom.setName(rs.getString(8));
				dto.setRoom(dtoRoom);
				dtoLecture.set_class(rs.getString(9));
				dto.setLecture(dtoLecture);
				dtoSubject.setGrade(rs.getByte(10));
				dtoSubject.setIhour(rs.getByte(11));
				dtoSubject.setName(rs.getString(12));
				dtoSubject.setDepart_id(rs.getInt(13));
				dtoLecture.setSubject(dtoSubject);
				dtoTeacher.setId(rs.getInt(14));
				dtoTeacher.setName(rs.getString(15));
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
	
	public int insert(TimeTableDTO dto) {    	
    	int result = 0;
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement("insert into timetable(lecture_id, weekday, istart, ihour, room_id) " +  
    				"values(?, ?, ?, ?, ?)");	
    		pstmt.setInt(1, dto.getLecture_id());
    		pstmt.setString(2, dto.getWeekday());
    		pstmt.setByte(3, dto.getIstart());
    		pstmt.setByte(4, dto.getIhour());
    		pstmt.setInt(5, dto.getRoom_id()); 
    		result = pstmt.executeUpdate();	
    		return result;    		
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return result;
    }
	
	public ArrayList<TimeTableDTO> Tdetail(HttpSession session){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String year   = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
			String month   = new java.text.SimpleDateFormat("MM").format(new java.util.Date());
			int m = Integer.parseInt(month);
			int hak=0;

			dtoList = new ArrayList<TimeTableDTO>();
			rs = stmt.executeQuery("SELECT timetable.*, room.id, room.name, lecture.class, subject.grade, subject.ihour, subject.name, subject.depart_id, subject.yyyy, subject.term, teacher.id, teacher.name FROM timetable LEFT JOIN room ON timetable.room_id = room.id LEFT JOIN lecture ON timetable.lecture_id = lecture.id LEFT JOIN subject ON subject.id = lecture.subject_id LEFT JOIN teacher ON teacher.id = lecture.teacher_id "
					+ "where teacher.id="+session.getAttribute("id")+" and subject.yyyy="+year+" and subject.term="+hak);
			while(rs.next()) {
				dto = new TimeTableDTO();
				dtoRoom = new RoomDTO();
				dtoLecture = new LectureDTO();
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dto.setId(rs.getInt(1));
				dto.setLecture_id(rs.getInt(2));
				dto.setWeekday(rs.getString(3));
				dto.setIstart(rs.getByte(4));
				dto.setIhour(rs.getByte(5));
				dto.setRoom_id(rs.getInt(6));
				dtoRoom.setId(rs.getInt(7));
				dtoRoom.setName(rs.getString(8));
				dto.setRoom(dtoRoom);
				dtoLecture.set_class(rs.getString(9));
				dto.setLecture(dtoLecture);
				dtoSubject.setGrade(rs.getByte(10));
				dtoSubject.setIhour(rs.getByte(11));
				dtoSubject.setName(rs.getString(12));
				dtoSubject.setDepart_id(rs.getInt(13));
				dtoLecture.setSubject(dtoSubject);
				dtoTeacher.setId(rs.getInt(14));
				dtoTeacher.setName(rs.getString(15));
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
	
	public int delete(int id) {
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from timetable where id=? ");
			pstmt.setInt(1, id); //첫번째 물음표 대체
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
	
	
}