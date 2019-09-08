package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubjectDAO extends DAOBase{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; 
	private SubjectDTO subject = null;
	private DepartDTO depart = null;
	private ArrayList<SubjectDTO> alSubject = null;
	
	public ArrayList<SubjectDTO> list(String sel1, String sel2){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			if(sel1 != null && !(sel2.equals("0"))) rs=stmt.executeQuery("SELECT subject.*, depart.name FROM subject LEFT JOIN depart ON subject.depart_id=depart.id "
					+ "where yyyy="+sel1+" and grade="+sel2); 
			else if(sel1 != null && sel2.equals("0")) rs=stmt.executeQuery("SELECT subject.*, depart.name FROM subject LEFT JOIN depart ON subject.depart_id=depart.id where yyyy="+sel1);
			
			else rs=stmt.executeQuery("SELECT subject.*, depart.name FROM subject LEFT JOIN depart ON subject.depart_id=depart.id");
			
			alSubject = new ArrayList<SubjectDTO>();
			
			while(rs.next()) {
				subject = new SubjectDTO();
				depart = new DepartDTO();
				subject.setId(rs.getInt(1));
				subject.setDepart_id(rs.getInt(2));
				subject.setCode(rs.getString(3));
				subject.setYyyy(rs.getInt(4));
				subject.setGrade(rs.getByte(5));
				subject.setTerm(rs.getByte(6));
				subject.setIsmajor(rs.getString(7));
				subject.setIschoice(rs.getString(8));
				subject.setIspractice(rs.getString(9));
				subject.setName(rs.getString(10));
				subject.setIpoint(rs.getFloat(11));
				subject.setIhour(rs.getByte(12));
				subject.setDepart(depart);
				depart.setName(rs.getString(13));
				alSubject.add(subject);
			} return alSubject;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return alSubject;
	}
	
	public ArrayList<SubjectDTO> List(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();

			rs=stmt.executeQuery("SELECT subject.*, depart.name FROM subject LEFT JOIN depart ON subject.depart_id=depart.id");
			
			alSubject = new ArrayList<SubjectDTO>();
			
			while(rs.next()) {
				subject = new SubjectDTO();
				depart = new DepartDTO();
				subject.setId(rs.getInt(1));
				subject.setDepart_id(rs.getInt(2));
				subject.setCode(rs.getString(3));
				subject.setYyyy(rs.getInt(4));
				subject.setGrade(rs.getByte(5));
				subject.setTerm(rs.getByte(6));
				subject.setIsmajor(rs.getString(7));
				subject.setIschoice(rs.getString(8));
				subject.setIspractice(rs.getString(9));
				subject.setName(rs.getString(10));
				subject.setIpoint(rs.getFloat(11));
				subject.setIhour(rs.getByte(12));
				subject.setDepart(depart);
				depart.setName(rs.getString(13));
				alSubject.add(subject);
			} return alSubject;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return alSubject;
	}
	
public int register(HttpServletRequest request, HttpServletResponse response) {
		
		int result = 0;
		subject = new SubjectDTO();
		subject.setDepart_id(Integer.parseInt(request.getParameter("depart_id")));
		subject.setCode(request.getParameter("code"));
		subject.setYyyy(Integer.parseInt(request.getParameter("yyyy")));
		subject.setGrade(Byte.parseByte(request.getParameter("grade")));
		subject.setTerm(Byte.parseByte(request.getParameter("term")));
		subject.setIsmajor(request.getParameter("ismajor"));
		subject.setIschoice(request.getParameter("ischoice"));
		subject.setIspractice(request.getParameter("ispractice"));
		subject.setName(request.getParameter("name"));
		subject.setIpoint(Float.parseFloat(request.getParameter("ipoint")));
		subject.setIhour(Byte.parseByte(request.getParameter("ihour")));
    	
    	String sql = "insert into subject(id, depart_id, code, yyyy, grade, term, "
    			+ "ismajor, ischoice, ispractice, name, ipoint, ihour) " + 
    			"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	
    	try {
			conn = getConnection();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, subject.getId());
	    	pstmt.setInt(2, subject.getDepart_id());
	    	pstmt.setString(3, subject.getCode());
	    	pstmt.setInt(4, subject.getYyyy());
	    	pstmt.setByte(5, subject.getGrade());
	    	pstmt.setByte(6, subject.getTerm());
	    	pstmt.setString(7, subject.getIsmajor());
	    	pstmt.setString(8, subject.getIschoice());
	    	pstmt.setString(9, subject.getIspractice());
	    	pstmt.setString(10, subject.getName());
	    	pstmt.setFloat(11, subject.getIpoint());
	    	pstmt.setByte(12, subject.getIhour());
	    	
	    	result = pstmt.executeUpdate();
	    	
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
    	return result;
	}

	public int update(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		subject = new SubjectDTO();
		subject.setId(Integer.parseInt(request.getParameter("id")));
		subject.setDepart_id(Byte.parseByte(request.getParameter("depart_id")));
		subject.setCode(request.getParameter("code"));
		subject.setYyyy(Integer.parseInt(request.getParameter("yyyy")));
		subject.setGrade(Byte.parseByte(request.getParameter("grade")));
		subject.setTerm(Byte.parseByte(request.getParameter("term")));
		subject.setIsmajor(request.getParameter("ismajor"));
		subject.setIschoice(request.getParameter("ischoice"));
		subject.setIspractice(request.getParameter("ispractice"));
		subject.setName(request.getParameter("name"));
		subject.setIpoint(Float.parseFloat(request.getParameter("ipoint")));
		subject.setIhour(Byte.parseByte(request.getParameter("ihour")));
		
		String sql = "update subject set depart_id=?, code=?, yyyy=?, grade=?, term=?, "
				+ "ismajor=?, ischoice=?, ispractice=?, name=?, ipoint=?, ihour=? where id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, subject.getDepart_id());
	    	pstmt.setString(2, subject.getCode());
	    	pstmt.setInt(3, subject.getYyyy());
	    	pstmt.setByte(4, subject.getGrade());
	    	pstmt.setByte(5, subject.getTerm());
	    	pstmt.setString(6, subject.getIsmajor());
	    	pstmt.setString(7, subject.getIschoice());
	    	pstmt.setString(8, subject.getIspractice());
	    	pstmt.setString(9, subject.getName());
	    	pstmt.setFloat(10, subject.getIpoint());
	    	pstmt.setByte(11, subject.getIhour());
	    	pstmt.setInt(12, subject.getId());
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
	
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		int result=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from subject where id=?");
			pstmt.setString(1, request.getParameter("id"));
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
	
	public SubjectDTO detail (int id) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from subject where id=" + id);
			if(rs.next()) {
				subject = new SubjectDTO();
				subject.setId(rs.getInt(1));
				subject.setDepart_id(rs.getInt(2));
				subject.setCode(rs.getString(3));
				subject.setYyyy(rs.getInt(4));
				subject.setGrade(rs.getByte(5));
				subject.setTerm(rs.getByte(6));
				subject.setIsmajor(rs.getString(7));
				subject.setIschoice(rs.getString(8));
				subject.setIspractice(rs.getString(9));
				subject.setName(rs.getString(10));
				subject.setIpoint(rs.getFloat(11));
				subject.setIhour(rs.getByte(12));
			}
			return subject;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
			return subject;
	}
}
