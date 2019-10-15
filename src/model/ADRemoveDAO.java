package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//select DISTINCT min(subject.yyyy) as minyear, max(subject.yyyy) as maxyear from subject
//select depart.name, teacher.name, subject.name, subject.grade, lecture.class, lectureday.* from subject left join lecture on lecture.subject_id = subject.id left join lectureday on lectureday.lecture_id = lecture.id left join room on room.id = lectureday.room_id left join teacher on teacher.id = lecture.teacher_id left join depart on depart.id = teacher.depart_id where subject.yyyy=2019 and subject.term = 2 and lectureday.normstate='3'
public class ADRemoveDAO extends DAOBase{
	
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	HttpSession sesobj = null;
	
	ArrayList<ADRemoveDTO> dtolist = null;
	ADRemoveDTO dto = null;
	
	public String Year(HttpServletRequest request, HttpServletResponse response) {
		String _Year = "";
		
		try {
			String query="select DISTINCT min(subject.yyyy) as minyear, max(subject.yyyy) as maxyear from subject";
			conn = getConnection();
			stmt = conn.createStatement();
	    	ResultSet rs = null;
			rs = stmt.executeQuery(query);
			rs.next();
			for(int i = rs.getInt("minyear"); i <= rs.getInt("maxyear"); i++)
				_Year += Integer.toString(i) + "^";
			
			return _Year;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	closeDBResources(rs, stmt, pstmt, conn);	}
		return null;
	}
	public ArrayList<ADRemoveDTO> DTOlist(HttpServletRequest request, HttpServletResponse response){
		TeacherDTO teacher = null;
		dtolist = new ArrayList<ADRemoveDTO>();
		try {
			String query="select depart.abbreviation, teacher.name, subject.name, subject.grade, lecture.class, room.name, "
					+ "building.name, lectureday.* from subject left join lecture on lecture.subject_id = subject.id "
					+ "left join lectureday on lectureday.lecture_id = lecture.id left join room on room.id = lectureday.room_id "
					+ "left join building on building.id = room.building_id left join teacher on teacher.id = lecture.teacher_id "
					+ "left join depart on depart.id = teacher.depart_id where lectureday.state='학과장승인'";
			if(request.getParameter("sel1") != null && request.getParameter("sel2") != null)
				query += " and subject.yyyy="+request.getParameter("sel1")+" and subject.term = "+request.getParameter("sel2");
			
			conn = getConnection();
			stmt = conn.createStatement();
	    	ResultSet rs = null;
			rs = stmt.executeQuery(query);

			while(rs.next())
			{
				dto = new ADRemoveDTO();
				
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
	//te-main.do에서 쓸 값
	public ArrayList<ADRemoveDTO> DTOlist2(HttpServletRequest request, HttpServletResponse response){
		TeacherDTO teacher = null;
		dtolist = new ArrayList<ADRemoveDTO>();
		try {
			String query="select depart.abbreviation, teacher.name, subject.name, subject.grade, lecture.class, room.name, building.name, lectureday.* from subject "
					+ "left join lecture on lecture.subject_id = subject.id left join lectureday on lectureday.lecture_id = lecture.id left join room on room.id = lectureday.room_id "
					+ "left join building on building.id = room.building_id left join teacher on teacher.id = lecture.teacher_id left join depart on depart.id = teacher.depart_id where lectureday.state is not null";
			
			
			if(request.getParameter("sel1") != null && request.getParameter("sel2") != null)
				query += " and subject.yyyy="+request.getParameter("sel1")+" and subject.term = "+request.getParameter("sel2");
			
			System.out.println(query);
			conn = getConnection();
			stmt = conn.createStatement();
	    	ResultSet rs = null;
			rs = stmt.executeQuery(query);

			while(rs.next())
			{
				dto = new ADRemoveDTO();
				
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
	public void lastapp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		String no = request.getParameter("no");
		try {
			String query="update lectureday set state='최종승인' where id = " + no;
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	closeDBResources(rs, stmt, pstmt, conn);	}
		
	}
	public void returnlec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		String no = request.getParameter("no");
		try {
			String query="update lectureday set state='반려' where id = " + no;
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	closeDBResources(rs, stmt, pstmt, conn);	}
	}

}
