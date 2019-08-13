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
	ArrayList<LectureDTO> dtoList = null;
	LectureDTO dto = null;
	DepartDTO depart_dto = null;
	SubjectDTO subject_dto = null;
	TeacherDTO teacher_dto = null;
	HttpSession sesobj = null;
	
	public ArrayList<LectureDTO> selectAllList(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();			
			dtoList = new ArrayList<LectureDTO>();
			rs = stmt.executeQuery("select depart.name as depart_name, lecture.class as lecture_class, subject.*, teacher.name as teacher_name from lecture left join subject on lecture.subject_id = subject.id left join teacher on lecture.teacher_id = teacher.id left join depart on subject.depart_id = depart.id"); //where subject.grade = " + student.getGrade());
			while(rs.next()) {
				dto = new LectureDTO();
				depart_dto = new DepartDTO();
				subject_dto = new SubjectDTO();
				teacher_dto = new TeacherDTO();
				
				depart_dto.setName(rs.getString(1));				
				dto.setLecture_class(rs.getString(2));
				dto.setId(rs.getInt(3));
				depart_dto.setId(rs.getInt(4));
				subject_dto.setDepart(depart_dto);
				
				subject_dto.setCode(rs.getString(5));
				subject_dto.setYyyy(rs.getInt(6));
				subject_dto.setGrade(rs.getByte(7));
				subject_dto.setTerm(rs.getByte(8));
				subject_dto.setIsmajor(rs.getString(9));
				subject_dto.setIschoice(rs.getString(10));
				subject_dto.setIspractice(rs.getString(11));
				subject_dto.setName(rs.getString(12));
				subject_dto.setIpoint(rs.getFloat(13));
				subject_dto.setIhour(rs.getByte(14));
				dto.setSubject(subject_dto);
				
				teacher_dto.setName(rs.getString(15));
				dto.setTeacher(teacher_dto);
				
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
