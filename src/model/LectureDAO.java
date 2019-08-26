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
	HttpSession sesobj = null;
	ArrayList<LectureDTO> dtoList = null;
	LectureDTO dto = null;
	SubjectDTO dtoSubject = null;
	TeacherDTO dtoTeacher = null;
	
	public ArrayList<LectureDTO> List(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			dtoList = new ArrayList<LectureDTO>();
			rs = stmt.executeQuery("SELECT lecture.id, lecture.class, subject.grade, subject.ihour, subject.name, subject.depart_id, teacher.id, teacher.name FROM lecture LEFT JOIN subject ON lecture.subject_id = subject.id LEFT JOIN teacher ON lecture.teacher_id = teacher.id");
			while(rs.next()) {
				dto = new LectureDTO(); 
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dto.setId(rs.getInt(1));
				dto.set_class(rs.getString(2));
				dtoSubject.setGrade(rs.getByte(3));
				dtoSubject.setIhour(rs.getByte(4));
				dtoSubject.setName(rs.getString(5));
				dtoSubject.setDepart_id(rs.getInt(6));
				dto.setSubject(dtoSubject);
				dtoTeacher.setId(rs.getInt(7));
				dtoTeacher.setName(rs.getString(8));
				dto.setTeacher(dtoTeacher);
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

