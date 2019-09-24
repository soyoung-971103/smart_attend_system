package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StudentDAO extends DAOBase {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StudentDTO dto = null;
	private ArrayList<StudentDTO> dtoList = null;
	
	
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		int result=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from student where id=?");
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
	
	public StudentDTO detail (int id) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from student where id=" + id);
			if(rs.next()) {
				dto = new StudentDTO();
				dto.setId(rs.getInt(1));
				dto.setDepart_id(rs.getInt(2));
				dto.setGrade(rs.getByte(3));
				dto.setStudent_class(rs.getString(4));
				dto.setSchoolno(rs.getString(5));
				dto.setName(rs.getString(6));
				dto.setPhone(rs.getString(7));
				dto.setSex(rs.getByte(8));
				dto.setPwd(rs.getString(9));
				dto.setPic(rs.getString(10));
				dto.setState(rs.getString(11));
				dto.setBirthday(rs.getString(12));
				dto.setEmail(rs.getString(13));
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
	
	public int register(HttpServletRequest request, HttpServletResponse response) {
		
		int result = 0;
		dto = new StudentDTO();
		dto.setDepart_id(Integer.parseInt(request.getParameter("depart_id")));
		dto.setGrade(Byte.parseByte(request.getParameter("grade")));
		dto.setStudent_class(request.getParameter("student_class"));
		dto.setSchoolno(request.getParameter("schoolno"));
		dto.setName(request.getParameter("name"));
		dto.setPhone(request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3"));
		dto.setSex(Byte.parseByte(request.getParameter("sex")));
		dto.setPwd(request.getParameter("pwd"));
		dto.setState(request.getParameter("state"));
		dto.setBirthday(request.getParameter("birthday1")+"-"+request.getParameter("birthday2")+"-"+request.getParameter("birthday3"));
		dto.setEmail(request.getParameter("email"));
		
		dto.setPic((String) request.getAttribute("pic"));
    	
    	String sql = "insert into student(id, depart_id, grade, class, schoolno, name, "
    			+ "phone, sex, pwd, pic, state, birthday, email) " + 
    			"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	
    	try {
			conn = getConnection();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, dto.getId());
	    	pstmt.setInt(2, dto.getDepart_id());
	    	pstmt.setInt(3, dto.getGrade());
	    	pstmt.setString(4, dto.getStudent_class());
	    	pstmt.setString(5, dto.getSchoolno());
	    	pstmt.setString(6, dto.getName());
	    	pstmt.setString(7, dto.getPhone());
	    	pstmt.setByte(8, dto.getSex());
	    	pstmt.setString(9, dto.getPwd());
	    	pstmt.setString(10, dto.getPic());
	    	pstmt.setString(11, dto.getState());
	    	pstmt.setString(12, dto.getBirthday());
	    	pstmt.setString(13, dto.getEmail());
	    	
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
		dto = new StudentDTO();
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setDepart_id(Integer.parseInt(request.getParameter("depart_id")));
		dto.setGrade(Byte.parseByte(request.getParameter("grade")));
		dto.setStudent_class(request.getParameter("student_class"));
		dto.setSchoolno(request.getParameter("schoolno"));
		dto.setName(request.getParameter("name"));
		dto.setPhone(request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3"));
		dto.setSex(Byte.parseByte(request.getParameter("sex")));
		dto.setPwd(request.getParameter("pwd"));
		dto.setState(request.getParameter("state"));
		dto.setBirthday(request.getParameter("birthday1")+"-"+request.getParameter("birthday2")+"-"+request.getParameter("birthday3"));
		dto.setEmail(request.getParameter("email"));
		
		dto.setPic((String) request.getAttribute("pic"));
		
		String sql = "update student set depart_id=?, grade=?, class=?, schoolno=?, name=?, "
				+ "phone=?, sex=?, pwd=?, pic=?, state=?, birthday=?, email=? where id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, dto.getDepart_id());
	    	pstmt.setInt(2, dto.getGrade());
	    	pstmt.setString(3, dto.getStudent_class());
	    	pstmt.setString(4, dto.getSchoolno());
	    	pstmt.setString(5, dto.getName());
	    	pstmt.setString(6, dto.getPhone());
	    	pstmt.setByte(7, dto.getSex());
	    	pstmt.setString(8, dto.getPwd());
	    	pstmt.setString(9, dto.getPic());
	    	pstmt.setString(10, dto.getState());
	    	pstmt.setString(11, dto.getBirthday());
	    	pstmt.setString(12, dto.getEmail());
	    	pstmt.setInt(13, dto.getId());
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
	
	public ArrayList<StudentDTO> list(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from student");
			dtoList = new ArrayList<StudentDTO>();
			while(rs.next()) {
				dto = new StudentDTO();
				dto.setId(rs.getInt(1));
				dto.setDepart_id(rs.getInt(2));
				dto.setGrade(rs.getByte(3));
				dto.setStudent_class(rs.getString(4));
				dto.setSchoolno(rs.getString(5));
				dto.setName(rs.getString(6));
				dto.setPhone(rs.getString(7));
				dto.setSex(rs.getByte(8));
				dto.setPwd(rs.getString(9));
				dto.setPic(rs.getString(10));
				dto.setState(rs.getString(11));
				dto.setBirthday(rs.getString(12));
				dto.setEmail(rs.getString(13));
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}

	public ArrayList<StudentDTO> search(String text1){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			if (text1 == null) rs=stmt.executeQuery("select * from student");
			else rs=stmt.executeQuery("select * from student where name like '%"+ text1 +"%' order by name");
			// email, pw는 form을 구성하는 각 요소의 이름
			dtoList = new ArrayList<StudentDTO>();
			while(rs.next()) {
				dto = new StudentDTO();
				dto.setId(rs.getInt(1));
				dto.setDepart_id(rs.getInt(2));
				dto.setGrade(rs.getByte(3));
				dto.setStudent_class(rs.getString(4));
				dto.setSchoolno(rs.getString(5));
				dto.setName(rs.getString(6));
				dto.setPhone(rs.getString(7));
				dto.setSex(rs.getByte(8));
				dto.setPwd(rs.getString(9));
				dto.setPic(rs.getString(10));
				dto.setState(rs.getString(11));
				dto.setBirthday(rs.getString(12));
				dto.setEmail(rs.getString(13));
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;
	}

	public StudentDTO list_id(String uid){
		try {
			conn = getConnection();
			stmt = conn.createStatement();	
			rs = stmt.executeQuery("select * from student where schoolno= " + uid);
			if(rs.next()) {
				dto = new StudentDTO();
				dto.setId(rs.getInt(1));
				dto.setDepart_id(rs.getInt(2));
				dto.setGrade(rs.getByte(3));
				dto.setStudent_class(rs.getString(4));
				dto.setSchoolno(rs.getString(5));
				dto.setName(rs.getString(6));
				dto.setPhone(rs.getString(7));
				dto.setSex(rs.getByte(8));
				dto.setPwd(rs.getString(9));
				dto.setPic(rs.getString(10));
				dto.setState(rs.getString(11));
				dto.setBirthday(rs.getString(12));
				dto.setEmail(rs.getString(13));
			}
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;	
	}

}
