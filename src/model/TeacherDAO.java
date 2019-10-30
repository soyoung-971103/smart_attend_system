package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

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
	ArrayList<LecturedayDTO> dtoListLectureday = null;
	ArrayList<RoomDTO> dtoListRoom = null;
	TeacherDTO dto = null;
	LectureDTO dtoLecture = null;
	DepartDTO dtoDepart = null;
	RoomDTO dtoRoom = null;
	BuildingDTO dtoBuilding = null;
	SubjectDTO dtoSubject = null;
	TimeTableDTO dtoTimeTable = null;
	LecturedayDTO dtoLectureday = null;
	
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
			dto = new TeacherDTO();
			dtoListDepart = new ArrayList<DepartDTO>();
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
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
				return dto;
			}
		} catch (SQLException e) { e.printStackTrace(); }
		finally { closeDBResources(rs, stmt, pstmt, conn); }
		
		return null;
	}
	public TeacherDTO teacherqalist(String name, String uid)
	{
		String query = "select teacher.*, depart.id, depart.name from teacher left join depart on teacher.depart_id = depart.id "
				+ "where teacher.uid='"+uid+"' and teacher.name='"+name+"'";
		dtoDepart = new DepartDTO();
		try {
			dto = new TeacherDTO();
			dtoListDepart = new ArrayList<DepartDTO>();
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				dto.setId(rs.getInt("teacher.id"));
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
				return dto;
			}
		} catch (SQLException e) { e.printStackTrace(); }
		finally { closeDBResources(rs, stmt, pstmt, conn); }
		
		return null;
	}
	public TeacherDTO tinName(int id)
	{
		String query = "select teacher.name from lecture left join subject on subject.id = lecture.subject_id left join teacher on teacher.id = lecture.teacher_id where lecture.id = " + id;
		
		try {
			dto = new TeacherDTO();
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			dto.setName(rs.getString("teacher.name"));
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
	
	public void saveqa(HttpServletRequest request, HttpServletResponse response) {
		try {//		qaday, qatitle, qaask	
			String str = request.getParameter("qatxt2").replace("\\", "\\\\");
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate("update mylecture set qaanswer = '"+str+"' where id = '"+request.getParameter("id")+"';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public ArrayList<LecturedayDTO> Check(int id, int sdate, int edate){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoListLectureday = new ArrayList<LecturedayDTO>();
			rs = stmt.executeQuery("SELECT lectureday.* FROM lectureday WHERE lectureday.lecture_id IN (SELECT DISTINCT mylecture.lecture_id FROM mylecture WHERE student_id in (SELECT mylecture.student_id FROM mylecture WHERE mylecture.lecture_id="+id+")) AND lectureday.normdate >="+sdate+" AND lectureday.normdate <="+edate);
			while(rs.next()) {
				//id lecture_id room_id th classification normdate normstart normhour normstate
				//restdate reststart resthour reststate state
				dtoLectureday =  new LecturedayDTO();
				
				dtoLectureday.setId(rs.getInt(1));
				dtoLectureday.setLecture_id(rs.getInt(2));
				dtoLectureday.setRoom_id(rs.getInt(3));
				dtoLectureday.setTh(rs.getByte(4));
				dtoLectureday.setClassification(rs.getByte(5));
				dtoLectureday.setNormdate(rs.getDate(6));
				dtoLectureday.setNormstart(rs.getByte(7));
				dtoLectureday.setNormhour(rs.getByte(8));
				dtoLectureday.setNormstate(rs.getString(9));
				dtoLectureday.setRestdate(rs.getDate(10));
				dtoLectureday.setReststart(rs.getByte(11));
				dtoLectureday.setResthour(rs.getByte(12));
				dtoLectureday.setReststate(rs.getString(13));
				dtoLectureday.setState(rs.getString(14));
				
				dtoListLectureday.add(dtoLectureday);				
			}
			return dtoListLectureday;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoListLectureday;	
	}	
	
	public ArrayList<RoomDTO> RoomCheck(int start, String date){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoListRoom = new ArrayList<RoomDTO>();
			
			rs = stmt.executeQuery("SELECT building.id as building_id, building.name as building_name, building.floor as building_fllor, room.*, depart.name as depart_name FROM building INNER JOIN room ON building.id = room.building_id INNER JOIN depart ON room.depart_id = depart.id where room.id NOT IN (SELECT lectureday.room_id FROM lectureday WHERE lectureday.normdate = '" +date+"' AND (lectureday.normstart >= "+start+" OR lectureday.normhour+lectureday.normstart > "+start+"))");
			while(rs.next()) {				
				dtoRoom =  new RoomDTO();
				dtoBuilding = new BuildingDTO();
				dtoDepart = new DepartDTO();
				
				dtoBuilding.setId(rs.getInt(1));
				dtoBuilding.setName(rs.getString(2));
				dtoBuilding.setFloor(rs.getByte(3));
				dtoRoom.setBuilding(dtoBuilding);
				
				dtoRoom.setId(rs.getInt(4));
				dtoRoom.setBuilding_id(rs.getInt(5));
				dtoRoom.setFloor(rs.getByte(6));
				dtoRoom.setHo(rs.getString(7));
				dtoRoom.setDepart_id(rs.getInt(8));
				dtoRoom.setName(rs.getString(9));
				dtoRoom.setKind(rs.getString(10));
				dtoRoom.setArea(rs.getInt(11));
				
				dtoDepart.setName(rs.getString(12));
				dtoRoom.setDepart(dtoDepart);
				
				dtoListRoom.add(dtoRoom);				
			}
			return dtoListRoom;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoListRoom;	
	}	
	
	public int RestUpdate(LecturedayDTO dto) {  		
		java.util.Calendar cal = Calendar.getInstance();
		java.util.Date utilDate = dto.getRestdate(); // your util date
		cal.setTime(utilDate);
				
    	int result = 0;
		try {
			conn = getConnection();		
			pstmt = conn.prepareStatement("update lectureday " + 
			"set normstate=?, restdate=?, reststart=?, resthour=?, reststate=?, state=?, room_id=? WHERE id=?");
			pstmt.setInt(1, 4);
    		pstmt.setDate(2, new java.sql.Date(cal.getTime().getTime()));
    		pstmt.setByte(3, dto.getReststart());
    		pstmt.setByte(4, dto.getResthour());
    		pstmt.setString(5, "정상");
    		pstmt.setString(6, "신청");
    		pstmt.setInt(7, dto.getRoom_id());
    		pstmt.setInt(8, dto.getId());
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
	
}
