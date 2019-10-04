package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TeacherDAO extends DAOBase {

	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	HttpSession sesobj = null;
	ArrayList<TeacherDTO> dtoList = null;
	ArrayList<LectureDTO> dtoListLecture = null;
	ArrayList<DepartDTO> dtoListDepart = null;
	ArrayList<TimeTableDTO> dtoListTimeTable = null;
	TeacherDTO dto = null;
	LectureDTO dtoLecture = null;
	DepartDTO dtoDepart = null;
	RoomDTO dtoRoom = null;
	SubjectDTO dtoSubject = null;
	TimeTableDTO dtoTimeTable = null;
	
	public ArrayList<TeacherDTO> list()
	{
		try {
			String query = "select teacher.*, depart.id, depart.name from teacher left join depart on teacher.depart_id = depart.id;";
			conn = getConnection();
			stmt = conn.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(query);
			
			dtoList = new ArrayList<TeacherDTO>();
			
			while(rs.next()) {
				dtoDepart = new DepartDTO();
				dto = new TeacherDTO();
				dto.setId(Integer.parseInt(rs.getString("teacher.id")));
				dtoDepart.setName(rs.getString("depart.name"));
				dto.setDepart_id(dtoDepart);
				dto.setKind(rs.getString("teacher.kind"));
				dto.setUid(rs.getString("teacher.uid"));
				dto.setPwd(rs.getString("teacher.pwd"));
				dto.setName(rs.getString("teacher.name"));
				dto.setTel(rs.getString("teacher.tel"));
				dto.setPhone(rs.getString("teacher.phone"));
				dto.setEmail(rs.getString("teacher.email"));
				dto.setPic(rs.getString("teacher.pic"));
				
				dtoList.add(dto);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		finally{	closeDBResources(rs, stmt, pstmt, conn);	}
		return dtoList;
	}
	
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		int result=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from teacher where id=?");
			pstmt.setString(1, request.getParameter("id"));
	    	result = pstmt.executeUpdate();
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	closeDBResources(rs, stmt, pstmt, conn);	}
		return result;
	}
	public TeacherDTO info(int id)
	{
		String query = "select teacher.*, depart.id, depart.name from teacher left join depart on teacher.depart_id = depart.id where teacher.id = "+id+";";
		dtoDepart = new DepartDTO();
		try {
			dtoListDepart = new ArrayList<DepartDTO>();
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			dto.setId(id);
			dto.setKind(rs.getString("kind"));
			dtoDepart.setName(rs.getString("depart.name"));
			dtoDepart.setId(Integer.parseInt(rs.getString("depart.id")));
			dto.setDepart_id(dtoDepart);
			dto.setUid(rs.getString("uid"));
			dto.setPwd(rs.getString("pwd"));
			dto.setName(rs.getString("name"));
			dto.setTel(rs.getString("tel"));
			dto.setPhone(rs.getString("phone"));
			dto.setEmail(rs.getString("email"));
			dto.setPic(rs.getString("pic"));
		} catch (SQLException e) { e.printStackTrace(); }
		finally { closeDBResources(rs, stmt, pstmt, conn); }
		
		return dto;
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
	{
		String query = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String depart_id = request.getParameter("depart_id");
			String kind = request.getParameter("kind");
		    String uid = request.getParameter("uid");
		    String pwd = request.getParameter("pwd");
		    String tel1 = request.getParameter("tel1");
		    String tel2 = request.getParameter("tel2");
		    String tel3 = request.getParameter("tel3");
		    String phone1 = request.getParameter("phone1");
		    String phone2 = request.getParameter("phone2");
		    String phone3 = request.getParameter("phone3");
		    String email = request.getParameter("email");
		    String pic = request.getParameter("pic");
	
		    String tel = String.format("%-3s%-4s%-4s",tel1, tel2, tel3);
		    String phone = String.format("%-3s%-4s%-4s", phone1, phone2, phone3);
		    System.out.println(id + name + depart_id + kind + uid + pwd + tel + phone + email);
		    
		    query="update teacher set name='"+name+"', uid='"+uid+"', pwd='"+pwd+"', depart_id='"+depart_id+"', tel='"+tel+"',phone='"+phone+"',email='"+email+"',pic='"+pic+"', kind='"+kind+"' where id="+id+";";
			stmt.executeUpdate(query);
		}catch(SQLException e) {	e.printStackTrace(); }
		finally { closeDBResources(rs,stmt, pstmt, conn); }
	}
	
	public void insert(HttpServletRequest request, HttpServletResponse response)
	{
		String query = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String name = request.getParameter("name");
			String depart_id = request.getParameter("depart_id");
			String kind = request.getParameter("kind");
		    String uid = request.getParameter("uid");
		    String pwd = request.getParameter("pwd");
		    String tel1 = request.getParameter("tel1");
		    String tel2 = request.getParameter("tel2");
		    String tel3 = request.getParameter("tel3");
		    String phone1 = request.getParameter("phone1");
		    String phone2 = request.getParameter("phone2");
		    String phone3 = request.getParameter("phone3");
		    String email = request.getParameter("email");
		    String pic = request.getParameter("pic");
		    if(pic.equals("")) pic = null;
		    
		    String tel = String.format("%-3s%-4s%-4s",tel1, tel2, tel3);
		    String phone = String.format("%-3s%-4s%-4s", phone1, phone2, phone3);
		    query = "insert into teacher(depart_id, kind, uid, pwd, name, tel, phone, email, pic) "
		    		+ "values('"+depart_id+"', '"+ kind +"', '"+ uid +"', '"+ pwd +"', '"+ name +"', '"+tel+"', '"+phone+"', '"+email+"','"+pic+"')";
			stmt.executeUpdate(query);
		}catch(SQLException e) { e.printStackTrace(); }
		finally {closeDBResources(rs,stmt, pstmt, conn);}
	}
	public void closeDBResources(ResultSet rs, Statement stmt, PreparedStatement pstmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	public ArrayList<TimeTableDTO> Load(int id, int sdate, int edate){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoListTimeTable = new ArrayList<TimeTableDTO>();
			rs = stmt.executeQuery("SELECT timetable.*, room.id, room.name, lecture.class, subject.grade, subject.ihour, subject.name, subject.depart_id, teacher.id, teacher.name, lectureday.* FROM lectureday LEFT JOIN timetable ON lectureday.lecture_id = timetable.lecture_id LEFT JOIN room ON timetable.room_id = room.id LEFT JOIN lecture ON timetable.lecture_id = lecture.id LEFT JOIN subject ON subject.id = lecture.subject_id LEFT JOIN teacher ON teacher.id = lecture.teacher_id where lecture.teacher_id="+id+" and lectureday.normdate >= "+sdate+" and lectureday.normdate <= "+edate);
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
				dtoTimeTable = new TimeTableDTO();
				dtoRoom = new RoomDTO();
				dtoLecture = new LectureDTO();
				dtoSubject = new SubjectDTO();
				dto = new TeacherDTO();
				dtoTimeTable.setId(rs.getInt(1));
				dtoTimeTable.setLecture_id(rs.getInt(2));
				dtoTimeTable.setWeekday(rs.getString(3));
				dtoTimeTable.setIstart(rs.getByte(4));
				dtoTimeTable.setIhour(rs.getByte(5));
				dtoTimeTable.setRoom_id(rs.getInt(6));
				dtoRoom.setId(rs.getInt(7));
				dtoRoom.setName(rs.getString(8));
				dtoTimeTable.setRoom(dtoRoom);
				dtoLecture.set_class(rs.getString(9));
				dtoTimeTable.setLecture(dtoLecture);
				dtoSubject.setGrade(rs.getByte(10));
				dtoSubject.setIhour(rs.getByte(11));
				dtoSubject.setName(rs.getString(12));
				dtoSubject.setDepart_id(rs.getInt(13));
				dtoLecture.setSubject(dtoSubject);
				dto.setId(rs.getInt(14));
				dto.setName(rs.getString(15));
				dtoLecture.setTeacher(dto);
				dtoListTimeTable.add(dtoTimeTable);
			}
			return dtoListTimeTable;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoListTimeTable;	
	}
	
}