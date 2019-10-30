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

//select DISTINCT min(subject.yyyy) as minyear, max(subject.yyyy) as maxyear from subject
//select depart.name, teacher.name, subject.name, subject.grade, lecture.class, lectureday.* from subject left join lecture on lecture.subject_id = subject.id left join lectureday on lectureday.lecture_id = lecture.id left join room on room.id = lectureday.room_id left join teacher on teacher.id = lecture.teacher_id left join depart on depart.id = teacher.depart_id where subject.yyyy=2019 and subject.term = 2 and lectureday.normstate='3'
public class ASRemoveDAO extends DAOBase{
	
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	HttpSession sesobj = null;
	
	ArrayList<ASRemoveDTO> dtolist = null;
	ASRemoveDTO dto = null;
	
	public ArrayList<ASRemoveDTO> DTOlist(HttpServletRequest request, HttpServletResponse response){
		TeacherDTO teacher = null;
		dtolist = new ArrayList<ASRemoveDTO>();
		try {
			String query="select depart.abbreviation, teacher.name, subject.name, subject.grade, lecture.class, room.name, building.name, lectureday.* from subject " +
						"left join lecture on lecture.subject_id = subject.id left join lectureday on lectureday.lecture_id = lecture.id left join room on room.id = lectureday.room_id "+
						"left join building on building.id = room.building_id left join teacher on teacher.id = lecture.teacher_id left join depart on depart.id = teacher.depart_id where lectureday.state='신청'";
			
			
			conn = getConnection();
			stmt = conn.createStatement();
	    	ResultSet rs = null;
			rs = stmt.executeQuery(query);

			while(rs.next())
			{
				dto = new ASRemoveDTO();
				
				teacher = new TeacherDTO();
				teacher.setName(rs.getString("teacher.name"));
				
				dto.setId(rs.getInt("lectureday.id"));
				dto.setDepart(rs.getString("depart.abbreviation"));
				dto.setTeacher_id(teacher);
				dto.setSubject_name(rs.getString("subject.name"));
				dto.setGrade(rs.getString("subject.grade"));
				dto.set_class(rs.getString("lecture.class"));
				dto.setNormdate(rs.getString("lectureday.normdate"));
				dto.setNormstart(rs.getInt("lectureday.normstart"));
				dto.setNormhour(rs.getInt("lectureday.normhour"));
				dto.setRestdate(rs.getString("lectureday.restdate"));
				dto.setResthour(rs.getInt("lectureday.resthour"));
				dto.setReststart(rs.getInt("lectureday.reststart"));
				dto.setRoomName(rs.getString("room.name"));
				dto.setBuildName(rs.getString("building.name"));
				dto.setState(rs.getString("lectureday.state"));
				dtolist.add(dto);
			}

			return dtolist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	closeDBResources(rs, stmt, pstmt, conn);	}
		return null;
	}
	public void departapp(HttpServletRequest request, HttpServletResponse response){
		try {
			String query="update lectureday set state = '학과장승인' where id='"+request.getParameter("no")+"'";	
			
			conn = getConnection();
			stmt = conn.createStatement();
	    	stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	closeDBResources(rs, stmt, pstmt, conn);	}
	}
	public void returnlec(HttpServletRequest request, HttpServletResponse response){
		try {
			String query="update lectureday set state = '반려' where id='"+request.getParameter("no")+"'";	
			
			conn = getConnection();
			stmt = conn.createStatement();
	    	stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	closeDBResources(rs, stmt, pstmt, conn);	}
		
	}
}
