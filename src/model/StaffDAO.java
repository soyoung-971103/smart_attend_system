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

public class StaffDAO extends DAOBase {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StaffDTO staff = null;
	private ArrayList<StaffDTO> alStaff = null;
	
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		int result=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from staff where uid=?");
			pstmt.setString(1, request.getParameter("uid"));
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
	
	public StaffDTO info (HttpSession session) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			/*
	    	rs = stmt.executeQuery("select * from staff" + 
					" where email='" + request.getParameter("email") + "'");
			 		*/
			rs = stmt.executeQuery("select * from staff" + 
					" where uid='" + (String) session.getAttribute("uid") + "'"); 
			// email, pw는 form을 구성하는 각 요소의 이름
			if(rs.next()) {
				staff = new StaffDTO();
				staff.setDepart_id(rs.getInt(2));
         	    staff.setUid(rs.getString(3));
         	    staff.setPwd(rs.getString(4));
         	    staff.setName(rs.getString(5));
         	    staff.setTel(rs.getString(6));
         	    staff.setPhone(rs.getString(7));
         	    staff.setEmail(rs.getString(8));
         	    staff.setPic(rs.getString(9));
			}
			return staff;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
			return staff;
	}
	
	public int register(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into staff values( ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(2, request.getParameter("depart_id"));
			pstmt.setString(3, request.getParameter("uid"));
			pstmt.setString(4, request.getParameter("pwd"));
	    	pstmt.setString(5, request.getParameter("name"));
	    	pstmt.setString(6, request.getParameter("tel"));
	    	pstmt.setString(7, request.getParameter("phone"));
	    	pstmt.setString(8, request.getParameter("email"));
	    	pstmt.setString(9, request.getParameter("pic"));
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
	
	public int update(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update staff set depart_id=?, pwd=?, name=?, tel=?, phone=?, email=?, pic=? where uid=?");
			pstmt.setString(2, request.getParameter("depart_id"));
			pstmt.setString(4, request.getParameter("pwd"));
	    	pstmt.setString(5, request.getParameter("name"));
	    	pstmt.setString(6, request.getParameter("tel"));
	    	pstmt.setString(7, request.getParameter("phone"));
	    	pstmt.setString(8, request.getParameter("email"));
	    	pstmt.setString(9, request.getParameter("pic"));
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
	
	public StaffDTO login(HttpServletRequest request, HttpServletResponse response) {
	      try {
	         conn = getConnection();
	         stmt = conn.createStatement();
	         rs = stmt.executeQuery("select * from staff " + 
	               "where uid='" + request.getParameter("uid") + 
	               "' and pwd='" + request.getParameter("password") + "'");
	               
	               if(rs.next()) {
	            	   staff = new StaffDTO();
	            	   staff.setDepart_id(rs.getInt(2));
	            	   staff.setUid(rs.getString(3));
	            	   staff.setPwd(rs.getString(4));
	            	   staff.setName(rs.getString(5));
	            	   staff.setTel(rs.getString(6));
	            	   staff.setPhone(rs.getString(7));
	            	   staff.setEmail(rs.getString(8));
	            	   staff.setPic(rs.getString(9));
	               }
	               return staff;
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
				this.closeDBResources(rs, stmt, pstmt, conn);
			}
	      return staff;
	   }
	
	public ArrayList<StaffDTO> list(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from staff");
			// email, pw는 form을 구성하는 각 요소의 이름
			alStaff = new ArrayList<StaffDTO>();
			while(rs.next()) {
				staff = new StaffDTO();
				staff.setDepart_id(rs.getInt(2));
         	    staff.setUid(rs.getString(3));
         	    staff.setPwd(rs.getString(4));
         	    staff.setName(rs.getString(5));
         	    staff.setTel(rs.getString(6));
         	    staff.setPhone(rs.getString(7));
         	    staff.setEmail(rs.getString(8));
         	    staff.setPic(rs.getString(9));
				alStaff.add(staff);
			} return alStaff;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return alStaff;
	}
}
