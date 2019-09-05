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

public class LectureDAO extends DAOBase{
	
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<LectureDTO> alLecture = null;
	SubjectDTO subject = null;
	DepartDTO depart = null;
	TeacherDTO teacher = null;
	LectureDTO lecture = null;
	HttpSession sesobj = null;
	
	public ArrayList<LectureDTO> list(String sel1, String sel2, String sel3){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			alLecture = new ArrayList<LectureDTO>();
			if(sel1 != null && sel2 != null && !sel3.equals("0"))
				rs=stmt.executeQuery("SELECT lecture.*, subject.name, subject.code, subject.ihour, subject.ipoint, subject.yyyy, subject.term, subject.grade, teacher.id, teacher.name FROM lecture "
						+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
						+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
						+ "LEFT JOIN depart ON subject.depart_id=depart.id "
						+ "Where subject.yyyy="+sel1+" and subject.term="+sel2+" and subject.grade="+sel3);
			else if(sel1 != null && sel2 != null && sel3.equals("0"))
				rs=stmt.executeQuery("SELECT lecture.*, subject.name, subject.code, subject.ihour, subject.ipoint, subject.yyyy, subject.term, subject.grade, teacher.id, teacher.name FROM lecture "
						+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
						+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
						+ "LEFT JOIN depart ON subject.depart_id=depart.id "
						+ "Where subject.yyyy="+sel1+" and subject.term="+sel2);
			else
				rs=stmt.executeQuery("SELECT lecture.*, subject.name, subject.code, subject.ihour, subject.ipoint, teacher.id, teacher.name FROM lecture "
						+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
						+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
						+ "LEFT JOIN depart ON subject.depart_id=depart.id");
			
			alLecture = new ArrayList<LectureDTO>();
			
			while(rs.next()) {
				lecture = new LectureDTO();
				depart = new DepartDTO();
				subject = new SubjectDTO();
				teacher = new TeacherDTO();
				lecture.setId(rs.getInt(1));
				lecture.setSubject_id(rs.getInt(2));
				lecture.setTeacher_id(rs.getInt(3));
				lecture.set_class(rs.getString(4));
				subject.setId(rs.getInt(5));
				subject.setCode(rs.getString(7));
				subject.setName(rs.getString(6));
				subject.setIhour(rs.getByte(8));
				subject.setIpoint(rs.getFloat(9));
				teacher.setId(10);
				teacher.setName(rs.getString(11));
				lecture.setSubject(subject);
				lecture.setDepart(depart);
				lecture.setTeacher(teacher);
				alLecture.add(lecture);
			} return alLecture;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return alLecture;
	}
	
	public ArrayList<LectureDTO> list(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			alLecture = new ArrayList<LectureDTO>();
			rs=stmt.executeQuery("SELECT lecture.*, subject.id, subject.name, subject.code, subject.ihour, subject.ipoint, teacher.id, teacher.name FROM lecture "
					+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
					+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
					+ "LEFT JOIN depart ON subject.depart_id=depart.id");
			
			alLecture = new ArrayList<LectureDTO>();
			
			while(rs.next()) {
				lecture = new LectureDTO();
				depart = new DepartDTO();
				subject = new SubjectDTO();
				teacher = new TeacherDTO();
				lecture.setId(rs.getInt(1));
				lecture.setSubject_id(rs.getInt(2));
				lecture.setTeacher_id(rs.getInt(3));
				lecture.set_class(rs.getString(4));
				lecture.setNumber(rs.getInt(5));
				subject.setId(rs.getInt(6));
				subject.setName(rs.getString(7));
				subject.setCode(rs.getString(8));
				subject.setIhour(rs.getByte(9));
				subject.setIpoint(rs.getFloat(10));
				teacher.setId(11);
				teacher.setName(rs.getString(12));
				lecture.setSubject(subject);
				lecture.setDepart(depart);
				lecture.setTeacher(teacher);
				alLecture.add(lecture);
			} return alLecture;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return alLecture;
	}
	
	public int register(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
    	String sql1 = "SELECT lecture.*, depart.name FROM lecture "
				+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
				+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
				+ "LEFT JOIN depart ON subject.depart_id=depart.id";
    	
    	try {
			conn = getConnection();
			stmt = conn.createStatement();
	    	pstmt = conn.prepareStatement(sql1);

	    	String sql2 = "insert into lecture (subject_id, class) select subject.id, 1 from subject LEFT JOIN depart ON subject.depart_id=depart.id where depart.classnum >= 1";
	    	pstmt = conn.prepareStatement(sql2);
	    	result = pstmt.executeUpdate(sql2);
	    	
	    	String sql3 = "update lecture set class = 'A' where class='1'";
	    	pstmt = conn.prepareStatement(sql3);
	    	result = pstmt.executeUpdate(sql3);
	    	
	    	String sql4 = "INSERT INTO lecture (subject_id, class) SELECT subject.id, 2 FROM subject LEFT JOIN depart ON subject.depart_id=depart.id WHERE depart.classnum >=2";
	    	pstmt = conn.prepareStatement(sql4);
	    	result = pstmt.executeUpdate(sql4);
	    	
	    	String sql5 = "update lecture set class = 'B' where class='2'";
	    	pstmt = conn.prepareStatement(sql5);
	    	result = pstmt.executeUpdate(sql5);
	    	
	    	String sql6 = "INSERT INTO lecture (subject_id, class) SELECT subject.id, 3 FROM subject LEFT JOIN depart ON subject.depart_id=depart.id WHERE depart.classnum >=3";
	    	pstmt = conn.prepareStatement(sql6);
	    	result = pstmt.executeUpdate(sql6);
	    	
	    	String sql7 = "update lecture set class = 'C' where class='3'";
	    	pstmt = conn.prepareStatement(sql7);
	    	result = pstmt.executeUpdate(sql7);
	    	
	    	String sql8 = "insert into lecture (subject_id, class) select subject.id, 4 from subject LEFT JOIN depart ON subject.depart_id=depart.id where depart.classnum >= 4";
	    	pstmt = conn.prepareStatement(sql8);
	    	result = pstmt.executeUpdate(sql8);
	    	
	    	String sql9 = "update lecture set class = 'D' where class='4'";
	    	pstmt = conn.prepareStatement(sql9);
	    	result = pstmt.executeUpdate(sql9);
	    	
	    	String sql10 = "insert into lecture (subject_id, class) select subject.id, 5 from subject LEFT JOIN depart ON subject.depart_id=depart.id where depart.classnum >= 5";
	    	pstmt = conn.prepareStatement(sql10);
	    	result = pstmt.executeUpdate(sql10);
	    	
	    	String sql11 = "update lecture set class = 'E' where class='5'";
	    	pstmt = conn.prepareStatement(sql11);
	    	result = pstmt.executeUpdate(sql11);
	    	
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
    	return result;
	}
	
	public int update(HttpServletRequest request, HttpServletResponse response, int id) {
		int result = 0;
		lecture = new LectureDTO();
		lecture.setTeacher_id(Integer.parseInt(request.getParameter("teacherno")));
		
		String sql = "update lecture set teacher_id=? where id="+id;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecture.getTeacher_id());
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
	
	public int delete() {
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from lecture");
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
