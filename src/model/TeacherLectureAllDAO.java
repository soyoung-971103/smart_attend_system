package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class TeacherLectureAllDAO extends DAOBase{
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	HttpSession sesobj = null;
	
	public ArrayList<SubjectDTO> selectList(String [] values)
	{
		
		ArrayList<SubjectDTO> str = new ArrayList<SubjectDTO>();
		SubjectDTO dto = null;
		try {
			String SQL = "select subject.id, subject.name from subject left join lecture on subject.id = lecture.subject_id WHERE subject.depart_id = '1' and subject.yyyy='"+values[0]+"' and subject.term = '"+values[1]+"' and subject.grade = '"+values[2]+"' and lecture.class='"+values[3]+"';";
	  		conn = getConnection();
	  		stmt = conn.createStatement();
	  		rs = stmt.executeQuery(SQL);
	  		if(rs.next()) {
	  			dto = new SubjectDTO();
	  			
	  			dto.setId(rs.getInt("id"));
	  			dto.setName(rs.getString("name"));
	  			
	  			str.add(dto);
	  		}      			//
	  		return str;
	  	} catch (SQLException e) {	
	  		e.printStackTrace();
	  	}
	  	finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
	  	return null;
		
	}
	public ArrayList<TeacherLectureDTO> lecInfo(String value)
	{
		TeacherLectureDTO dto = null;
		ArrayList<TeacherLectureDTO> dtoList = null;
		try {
			dtoList = new ArrayList<TeacherLectureDTO>();
			String SQL = "select * from subject left join lecture on subject.id = lecture.subject_id left join lectureday on lectureday.lecture_id = lecture.id WHERE lecture.id = lectureday.lecture_id and subject.id = '"+value+"' ORDER BY lectureday.th ASC;";
	  		conn = getConnection();
	  		stmt = conn.createStatement();
	  		rs = stmt.executeQuery(SQL);
	  		while(rs.next()) {
	  			dto = new TeacherLectureDTO();
	  			dto.setTh(rs.getInt("th"));
	  			dto.setNormhour(rs.getInt("subject.ihour"));
	  			dto.setNormdate(rs.getString("normdate"));
	  			dto.setNormstart(rs.getInt("normstart"));
	  			
	  			dtoList.add(dto);
	  		}      			//
	  		return dtoList;
	  	} catch (SQLException e) {	
	  		e.printStackTrace();
	  	}
	  	finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
	  	return null;
	}
	public ArrayList<TeacherLectureAllDTO> stuList(String value, String grade, int []th_hour){
		TeacherLectureAllDTO dto = null;
		ArrayList<TeacherLectureAllDTO> dtoList = null;
		StudentDTO stdto = null;
		
		try {
			String SQL ="select subject.ihour, lecture.id from subject left join lecture on lecture.subject_id = subject.id where subject.id = "+value+" and lecture.class='"+grade+"';";
			
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			rs.next();
			th_hour[0] = rs.getInt("subject.ihour");
			String lecture_id = rs.getString("lecture.id");
			
			ArrayList<String> check = null;
			dtoList = new ArrayList<TeacherLectureAllDTO>();
			
			SQL = "select mylecture.*, student.* from subject left join lecture on subject.id = lecture.subject_id left join mylecture on mylecture.lecture_id = lecture.id left join student on student.id = mylecture.student_id where student.id = mylecture.student_id and subject.id='"+value+"' and mylecture.lecture_id='"+lecture_id+"'";
			conn = getConnection();
	  		stmt = conn.createStatement();
	  		rs = stmt.executeQuery(SQL);
	  		while(rs.next()) {
	  			stdto = new StudentDTO();
	  			dto = new TeacherLectureAllDTO();
	  			
	  			dto.setDepart(rs.getString("mylecture.departname"));
	  			
	  			stdto.setId(rs.getInt("student.id"));
	  			stdto.setGrade(rs.getByte("student.grade"));
	  			stdto.setSchoolno(rs.getString("student.schoolno"));
	  			stdto.setState(rs.getString("student.state"));
	  			stdto.setName(rs.getString("student.name"));
	  			dto.setStu(stdto);
	  			
	  			check = new ArrayList<String>();
	  			
	  			for(int i = 1; i <= th_hour[0] * 15; i++)
	  			{
	  				check.add(rs.getString("h"+i));
	  			}
	  			
	  			dto.setH(check);
	  			dto.setIattend(rs.getInt("mylecture.iattend"));
	  			dto.setIlate(rs.getInt("mylecture.ilate"));
	  			dto.setIxhour(rs.getInt("mylecture.ixhour"));
	  			
	  			dtoList.add(dto);
	  		}      			
	  		
	  		return dtoList;
	  	} catch (SQLException e) {	
	  		e.printStackTrace();
	  	}
	  	finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return null;
	}
	public void stuCheck(String subId, String lecClass, String rowno, String colno, String v){
		
		String SQL = "select * from lecture right join mylecture on mylecture.lecture_id = lecture.id left join subject on subject.id = lecture.subject_id left join student on student.id = mylecture.student_id where lecture.class='"+lecClass+"' and subject.id = '"+subId+"' and student.id = '"+rowno+"';";

		ArrayList<String> thList = null;
		
		try {
			thList = new ArrayList<String>();
			
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			rs.next();
			
			int hour = rs.getInt("subject.ihour");
			int lecture_id = rs.getInt("lecture.id");
			int iattend = 20;//rs.getInt("mylecture.iattend");
			int ixhour = 0;//rs.getInt("mylecture.ixhour");
			int ilate = 0;//rs.getInt("mylecture.ilate");
			//int check = rs.getInt(str);
			//if(check != Integer.parseInt(v));
			int th = Integer.parseInt(colno) / hour;
			
			thList = new ArrayList<String>();
			for(int i = 0; i < hour * 15; i++) 
				thList.add(rs.getString("mylecture.h"+(i+1)));
				
			thList.set(((th)*hour + (Integer.parseInt(colno) % hour) - 1), v);
			for(int i = 0; i < hour * 15; i++)
			{
				if(thList.get(i) == null);
				
				else if(thList.get(i).equals("2"))
					ixhour++;
				
				else if(thList.get(i).equals("1"))
					ilate++;
			}
			
			if(ixhour != 0 || ilate >= hour)
				iattend=weekhour1[hour-1][ixhour+(ilate/hour)-1];
			SQL = "UPDATE mylecture SET ilate='"+ilate+"',ixhour='"+ixhour+"',iattend='"+iattend+"', h"+((th)*hour + (Integer.parseInt(colno) % hour))+" = "+v+" WHERE mylecture.student_id = "+rowno+" and mylecture.lecture_id = "+ lecture_id+";";
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
