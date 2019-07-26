package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AssistDAO extends DAOBase {

	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<AssistDTO> dtoList = null;
	HttpSession sesobj = null;
	AssistDTO member = null;
	DepartDTO depart = null;
	
	public ArrayList<AssistDTO> list()
	{
		try {
			String query = "select staff.*, depart.id, depart.name from staff left join depart on staff.depart_id = depart.id;";
			conn = getConnection();
			stmt = conn.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(query);
			
			dtoList = new ArrayList<AssistDTO>();
			
			while(rs.next()) {
				member = new AssistDTO();
				depart = new DepartDTO();
				
				member.setId(Integer.parseInt(rs.getString("staff.id")));
				depart.setName(rs.getString("depart.name"));
				member.setDepart_id(depart);
				member.setUid(rs.getString("staff.uid"));
				member.setPwd(rs.getString("staff.pwd"));
				member.setName(rs.getString("staff.name"));
				member.setTel(rs.getString("staff.tel"));
				member.setPhone(rs.getString("staff.phone"));
				member.setEmail(rs.getString("staff.email"));
				member.setPic(rs.getString("staff.pic"));
				
				dtoList.add(member);
			}
		} catch (SQLException e) { System.out.println("TeacherInquiry Error");e.printStackTrace(); }
		finally{	closeDBResources(rs, stmt, pstmt, conn);	}
		return dtoList;
	}
	
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		int result=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from staff where id=?");
			pstmt.setString(1, request.getParameter("id"));
	    	result = pstmt.executeUpdate();
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	closeDBResources(rs, stmt, pstmt, conn);	}
		return result;
	}
	public AssistDTO info(int id)
	{
		String query = "select staff.*, depart.id, depart.name from staff left join depart on staff.depart_id = depart.id where staff.id = "+id+";";
		
		depart = new DepartDTO();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			member.setId(id);
			depart.setName(rs.getString("depart.name"));
			depart.setId(Integer.parseInt(rs.getString("depart.id")));
			member.setDepart_id(depart);
			member.setUid(rs.getString("staff.uid"));
			member.setPwd(rs.getString("staff.pwd"));
			member.setName(rs.getString("staff.name"));
			member.setTel(rs.getString("staff.tel"));
			member.setPhone(rs.getString("staff.phone"));
			member.setEmail(rs.getString("staff.email"));
			member.setPic(rs.getString("staff.pic"));
			

		} catch (SQLException e) { e.printStackTrace(); }
		finally { closeDBResources(rs, stmt, pstmt, conn); }
		
		return member;
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
	{
		String query = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String depart_id = request.getParameter("depart_id");
		    String uid = request.getParameter("uid");
		    String pwd = request.getParameter("pwd");
		    String tel1 = request.getParameter("tel1");
		    String tel2 = request.getParameter("tel2");
		    String tel3 = request.getParameter("tel3");
		    String phone1 = request.getParameter("phone1");
		    String phone2 = request.getParameter("phone2");
		    String phone3 = request.getParameter("phone3");
		    String email = request.getParameter("email");
		    String pic = request.getParameter("pic");

		    String tel = String.format("%-3s%-4s%-4s",tel1, tel2, tel3);
		    String phone = String.format("%-3s%-4s%-4s", phone1, phone2, phone3);
		    query="update staff set name='"+name+"', uid='"+uid+"', pwd='"+pwd+"', depart_id='"+depart_id+"', tel='"+tel+"',phone='"+phone+"',email='"+email+"',pic='"+pic+"' where id="+id+";";
			stmt.executeUpdate(query);
		}catch(SQLException e) {	e.printStackTrace(); }
		finally { closeDBResources(rs,stmt, pstmt, conn); }
	}
	
	public void insert(HttpServletRequest request, HttpServletResponse response)
	{
		String query = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String name = request.getParameter("name");
			int depart_id = Integer.parseInt(request.getParameter("depart_id"));
		    String uid = request.getParameter("uid");
		    String pwd = request.getParameter("pwd");
		    String tel1 = request.getParameter("tel1");
		    String tel2 = request.getParameter("tel2");
		    String tel3 = request.getParameter("tel3");
		    String phone1 = request.getParameter("phone1");
		    String phone2 = request.getParameter("phone2");
		    String phone3 = request.getParameter("phone3");
		    String email = request.getParameter("email");
		    String pic = request.getParameter("pic");
		    if(pic.equals("")) pic = null;
		    
		    String tel = String.format("%-3s%-4s%-4s",tel1, tel2, tel3);
		    String phone = String.format("%-3s%-4s%-4s", phone1, phone2, phone3);
		    query = "insert into staff(depart_id, uid, pwd, name, tel, phone, email, pic) "
		    		+ "values('"+depart_id+"', '"+ uid +"', '"+ pwd +"', '"+ name +"', '"+tel+"', '"+phone+"', '"+email+"','"+pic+"')";
			stmt.executeUpdate(query);
		}catch(SQLException e) { e.printStackTrace(); }
		finally {closeDBResources(rs,stmt, pstmt, conn);}
	}
	
	
	
	
	
	
	
	
	
	
	public void closeDBResources(ResultSet rs, Statement stmt, PreparedStatement pstmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
		

}
