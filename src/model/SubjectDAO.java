package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class SubjectDAO extends DAOBase{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private SubjectDTO lecture = null;
	private ArrayList<SubjectDTO> alLecture = null;
	
	public ArrayList<SubjectDTO> list(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs=stmt.executeQuery("select * from subject");
			alLecture = new ArrayList<SubjectDTO>();
			while(rs.next()) {
				lecture = new SubjectDTO();
				lecture.setId(rs.getInt(1));
				lecture.setDepart_id(rs.getInt(2));
				lecture.setCode(rs.getString(3));
				lecture.setYyyy(rs.getInt(4));
				lecture.setGrade(rs.getByte(5));
				lecture.setTerm(rs.getByte(6));
				lecture.setIsmajor(rs.getString(7));
				lecture.setIschoice(rs.getString(8));
				lecture.setIspractice(rs.getString(9));
				lecture.setName(rs.getString(10));
				lecture.setIpoint(rs.getFloat(11));
				lecture.setIhour(rs.getByte(12));
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
}
