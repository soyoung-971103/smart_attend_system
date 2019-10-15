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

public class TeacherDAO extends DAOBase {

	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	HttpSession sesobj = null;
	ArrayList<TeacherDTO> dtoList = null;
	ArrayList<DepartDTO> dtoListDepart = null;
	TeacherDTO dto = null;
	DepartDTO dtoDepart = null;
	
	public ArrayList<TeacherDTO> list()
	{
		try {
			String query = "select teacher.*, depart.id, depart.name from teacher left join depart on teacher.depart_id = depart.id;";
			conn = getConnection();
			stmt = conn.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(query);
			
			dtoList = new ArrayList<TeacherDTO>();
			
			while(rs.next()) {
				dtoDepart = new DepartDTO();
				dto = new TeacherDTO();
				dto.setId(Integer.parseInt(rs.getString("teacher.id")));
				dtoDepart.setName(rs.getString("depart.name"));
				dto.setDepart_id(dtoDepart);
				dto.setKind(rs.getString("teacher.kind"));
				dto.setUid(rs.getString("teacher.uid"));
				dto.setPwd(rs.getString("teacher.pwd"));
				dto.setName(rs.getString("teacher.name"));
				dto.setTel(rs.getString("teacher.tel"));
				dto.setPhone(rs.getString("teacher.phone"));
				dto.setEmail(rs.getString("teacher.email"));
				dto.setPic(rs.getString("teacher.pic"));
				
				dtoList.add(dto);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		finally{	closeDBResources(rs, stmt, pstmt, conn);	}
		return dtoList;
	}
	
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		int result=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from teacher where id=?");
			pstmt.setString(1, request.getParameter("id"));
	    	result = pstmt.executeUpdate();
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	closeDBResources(rs, stmt, pstmt, conn);	}
		return result;
	}
	public TeacherDTO info(int id)
	{
		String query = "select teacher.*, depart.id, depart.name from teacher left join depart on teacher.depart_id = depart.id where teacher.id = "+id+";";
		dtoDepart = new DepartDTO();
		try {
			dto = new TeacherDTO();
			dtoListDepart = new ArrayList<DepartDTO>();
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				dto.setId(id);
				dto.setKind(rs.getString("kind"));
				dtoDepart.setName(rs.getString("depart.name"));
				dtoDepart.setId(Integer.parseInt(rs.getString("depart.id")));
				dto.setDepart_id(dtoDepart);
				dto.setUid(rs.getString("uid"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setPic(rs.getString("pic"));
				return dto;
			}
		} catch (SQLException e) { e.printStackTrace(); }
		finally { closeDBResources(rs, stmt, pstmt, conn); }
		
		return null;
	}
	public TeacherDTO teacherqalist(String name, String uid)
	{
		String query = "select teacher.*, depart.id, depart.name from teacher left join depart on teacher.depart_id = depart.id "
				+ "where teacher.uid='"+uid+"' and teacher.name='"+name+"'";
		dtoDepart = new DepartDTO();
		try {
			dto = new TeacherDTO();
			dtoListDepart = new ArrayList<DepartDTO>();
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				dto.setId(rs.getInt("teacher.id"));
				dto.setKind(rs.getString("kind"));
				dtoDepart.setName(rs.getString("depart.name"));
				dtoDepart.setId(Integer.parseInt(rs.getString("depart.id")));
				dto.setDepart_id(dtoDepart);
				dto.setUid(rs.getString("uid"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setPic(rs.getString("pic"));
				return dto;
			}
		} catch (SQLException e) { e.printStackTrace(); }
		finally { closeDBResources(rs, stmt, pstmt, conn); }
		
		return null;
	}
	public TeacherDTO tinName(int id)
	{
		String query = "select teacher.name from lecture left join subject on subject.id = lecture.subject_id left join teacher on teacher.id = lecture.teacher_id where lecture.id = " + id;
		
		try {
			dto = new TeacherDTO();
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			dto.setName(rs.getString("teacher.name"));
		} catch (SQLException e) { e.printStackTrace(); }
		finally { closeDBResources(rs, stmt, pstmt, conn); }
		
		return dto;
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
			String kind = request.getParameter("kind");
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
		    System.out.println(id + name + depart_id + kind + uid + pwd + tel + phone + email);
		    
		    query="update teacher set name='"+name+"', uid='"+uid+"', pwd='"+pwd+"', depart_id='"+depart_id+"', tel='"+tel+"',phone='"+phone+"',email='"+email+"',pic='"+pic+"', kind='"+kind+"' where id="+id+";";
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
			String depart_id = request.getParameter("depart_id");
			String kind = request.getParameter("kind");
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
		    query = "insert into teacher(depart_id, kind, uid, pwd, name, tel, phone, email, pic) "
		    		+ "values('"+depart_id+"', '"+ kind +"', '"+ uid +"', '"+ pwd +"', '"+ name +"', '"+tel+"', '"+phone+"', '"+email+"','"+pic+"')";
			stmt.executeUpdate(query);
		}catch(SQLException e) { e.printStackTrace(); }
		finally {closeDBResources(rs,stmt, pstmt, conn);}
	}
	
	public void saveqa(HttpServletRequest request, HttpServletResponse response) {
		try {//		qaday, qatitle, qaask	
			String str = request.getParameter("qatxt2").replace("\\", "\\\\");
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate("update mylecture set qaanswer = '"+str+"' where id = '"+request.getParameter("id")+"';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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