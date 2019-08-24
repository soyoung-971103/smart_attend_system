package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public ArrayList<LectureDTO> list(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			alLecture = new ArrayList<LectureDTO>();
			rs=stmt.executeQuery("SELECT lecture.*, subject.name, subject.code, subject.ihour, subject.ipoint FROM lecture "
					+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
					+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
					+ "LEFT JOIN depart ON subject.depart_id=depart.id from subject");
			
			alLecture = new ArrayList<LectureDTO>();
			
			while(rs.next()) {
				lecture = new LectureDTO();
				depart = new DepartDTO();
				subject = new SubjectDTO();
				teacher = new TeacherDTO();
				lecture.setId(rs.getInt(1));
				lecture.setSubject_id(rs.getInt(2));
				lecture.setTeacher_id(rs.getInt(3));
				lecture.setLec_class(rs.getString(4));
				subject.setId(rs.getInt(5));
				subject.setCode(rs.getString(7));
				subject.setName(rs.getString(6));
				subject.setIpoint(rs.getFloat(9));
				subject.setIhour(rs.getByte(8));
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
	
	public int insert(LectureDTO dto) {    	
    	int result = 0;
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement("INSERT INTO lecture "
    				+ "SELECT * FROM lecture LEFT JOIN subject on lecture.subject_id=subject.id");	

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
