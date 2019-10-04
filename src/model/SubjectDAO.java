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
	private SubjectDTO dto = null;
	private DepartDTO dtoDepart = null;
	private ArrayList<SubjectDTO> dtoList = null;
	
	public ArrayList<SubjectDTO> list(String sel1, String sel2){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			if(sel1 != null && !(sel2.equals("0"))) rs=stmt.executeQuery("SELECT subject.*, depart.name FROM subject LEFT JOIN depart ON subject.depart_id=depart.id "
					+ "where yyyy="+sel1+" and grade="+sel2);
			else if(sel1 != null && sel2.equals("0")) rs=stmt.executeQuery("SELECT subject.*, depart.name FROM subject LEFT JOIN depart ON subject.depart_id=depart.id where yyyy="+sel1);
			
			else rs=stmt.executeQuery("SELECT subject.*, depart.name FROM subject LEFT JOIN depart ON subject.depart_id=depart.id");
			
			dtoList = new ArrayList<SubjectDTO>();
			
			while(rs.next()) {
				dto = new SubjectDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setDepart_id(rs.getInt(2));
				dto.setCode(rs.getString(3));
				dto.setYyyy(rs.getInt(4));
				dto.setGrade(rs.getByte(5));
				dto.setTerm(rs.getByte(6));
				dto.setIsmajor(rs.getString(7));
				dto.setIschoice(rs.getString(8));
				dto.setIspractice(rs.getString(9));
				dto.setName(rs.getString(10));
				dto.setIpoint(rs.getFloat(11));
				dto.setIhour(rs.getByte(12));
				dto.setDepart(dtoDepart);
				dtoDepart.setName(rs.getString(13));
				dtoList.add(dto);
			} return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return dtoList;
	}
	
	public ArrayList<SubjectDTO> List(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();

			rs=stmt.executeQuery("SELECT subject.*, depart.name FROM subject LEFT JOIN depart ON subject.depart_id=depart.id");
			
			dtoList = new ArrayList<SubjectDTO>();
			
			while(rs.next()) {
				dto = new SubjectDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setDepart_id(rs.getInt(2));
				dto.setCode(rs.getString(3));
				dto.setYyyy(rs.getInt(4));
				dto.setGrade(rs.getByte(5));
				dto.setTerm(rs.getByte(6));
				dto.setIsmajor(rs.getString(7));
				dto.setIschoice(rs.getString(8));
				dto.setIspractice(rs.getString(9));
				dto.setName(rs.getString(10));
				dto.setIpoint(rs.getFloat(11));
				dto.setIhour(rs.getByte(12));
				dto.setDepart(dtoDepart);
				dtoDepart.setName(rs.getString(13));
				dtoList.add(dto);
			} return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return dtoList;
	}
	
public int register(HttpServletRequest request, HttpServletResponse response) {
		
		int result = 0;
		dto = new SubjectDTO();
		dto.setDepart_id(Integer.parseInt(request.getParameter("depart_id")));
		dto.setCode(request.getParameter("code"));
		dto.setYyyy(Integer.parseInt(request.getParameter("yyyy")));
		dto.setGrade(Byte.parseByte(request.getParameter("grade")));
		dto.setTerm(Byte.parseByte(request.getParameter("term")));
		dto.setIsmajor(request.getParameter("ismajor"));
		dto.setIschoice(request.getParameter("ischoice"));
		dto.setIspractice(request.getParameter("ispractice"));
		dto.setName(request.getParameter("name"));
		dto.setIpoint(Float.parseFloat(request.getParameter("ipoint")));
		dto.setIhour(Byte.parseByte(request.getParameter("ihour")));
    	
    	String sql = "insert into subject(id, depart_id, code, yyyy, grade, term, "
    			+ "ismajor, ischoice, ispractice, name, ipoint, ihour) " + 
    			"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	
    	try {
			conn = getConnection();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, dto.getId());
	    	pstmt.setInt(2, dto.getDepart_id());
	    	pstmt.setString(3, dto.getCode());
	    	pstmt.setInt(4, dto.getYyyy());
	    	pstmt.setByte(5, dto.getGrade());
	    	pstmt.setByte(6, dto.getTerm());
	    	pstmt.setString(7, dto.getIsmajor());
	    	pstmt.setString(8, dto.getIschoice());
	    	pstmt.setString(9, dto.getIspractice());
	    	pstmt.setString(10, dto.getName());
	    	pstmt.setFloat(11, dto.getIpoint());
	    	pstmt.setByte(12, dto.getIhour());
	    	
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
		dto = new SubjectDTO();
		dto.setId(Integer.parseInt(request.getParameter("id"))); 
		dto.setDepart_id(Byte.parseByte(request.getParameter("depart_id")));
		dto.setCode(request.getParameter("code"));
		dto.setYyyy(Integer.parseInt(request.getParameter("yyyy")));
		dto.setGrade(Byte.parseByte(request.getParameter("grade")));
		dto.setTerm(Byte.parseByte(request.getParameter("term")));
		dto.setIsmajor(request.getParameter("ismajor"));
		dto.setIschoice(request.getParameter("ischoice"));
		dto.setIspractice(request.getParameter("ispractice"));
		dto.setName(request.getParameter("name"));
		dto.setIpoint(Float.parseFloat(request.getParameter("ipoint")));
		dto.setIhour(Byte.parseByte(request.getParameter("ihour")));
		
		String sql = "update subject set depart_id=?, code=?, yyyy=?, grade=?, term=?, "
				+ "ismajor=?, ischoice=?, ispractice=?, name=?, ipoint=?, ihour=? where id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, dto.getDepart_id());
	    	pstmt.setString(2, dto.getCode());
	    	pstmt.setInt(3, dto.getYyyy());
	    	pstmt.setByte(4, dto.getGrade());
	    	pstmt.setByte(5, dto.getTerm());
	    	pstmt.setString(6, dto.getIsmajor());
	    	pstmt.setString(7, dto.getIschoice());
	    	pstmt.setString(8, dto.getIspractice());
	    	pstmt.setString(9, dto.getName());
	    	pstmt.setFloat(10, dto.getIpoint());
	    	pstmt.setByte(11, dto.getIhour());
	    	pstmt.setInt(12, dto.getId());
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
				dto = new SubjectDTO();
				dto.setId(rs.getInt(1));
				dto.setDepart_id(rs.getInt(2));
				dto.setCode(rs.getString(3));
				dto.setYyyy(rs.getInt(4));
				dto.setGrade(rs.getByte(5));
				dto.setTerm(rs.getByte(6));
				dto.setIsmajor(rs.getString(7));
				dto.setIschoice(rs.getString(8));
				dto.setIspractice(rs.getString(9));
				dto.setName(rs.getString(10));
				dto.setIpoint(rs.getFloat(11));
				dto.setIhour(rs.getByte(12));
			}
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
			return dto;
	}
}