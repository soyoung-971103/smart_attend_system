package controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TeacherDAO;
import model.TeacherDTO;

/**
 * Servlet implementation class TeacherController
 */
//				Base					수정					추가				삭제					조회
@WebServlet({"/TeacherController", "/TeacherInfo", "/TeacherInsert", "/TeacherDelete", "/TeacherInquiry", "/TeacherUpdate"})

public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ArrayList<TeacherDTO> alMember = null;
    TeacherDTO member = null;
    HttpSession sesobj = null;
    TeacherDAO dao = new TeacherDAO();
    String query = null;
    
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		

		sesobj = request.getSession();
		
		System.out.println("process");		
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("TeacherUpdate")) {
			System.out.println(request.getParameter("name"));
			Update(request, response);
		}else if(action.equals("TeacherInfo")){
			Info(request, response);
		}else if(action.equals("TeacherInsert")) {
			Insert(request, response);
		}else if(action.equals("TeacherDelete")) {
			Delete(request, response);
		}else if(action.equals("TeacherInquiry")) {
			Inquiry(request, response);
		}
		else
			;
		
	}//
	private void Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		Connection cnt = dao.getConnection();
		Statement stmt = cnt.createStatement();
		int id = Integer.parseInt(request.getParameter("id"));
		query = "delete from teacher where id="+id+";";
		stmt.executeUpdate(query);
		response.sendRedirect("TeacherInquiry");
	}
	private void Inquiry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		alMember = new ArrayList<TeacherDTO>();
		String text1 = request.getParameter("text1") == null ? null : request.getParameter("text1");
		if(text1 != null)  query = "select * from teacher where name like '"+text1+"%'";
		else query = "select * from teacher";
		Connection cnt = dao.getConnection();
		Statement stmt = cnt.createStatement();
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				member = new TeacherDTO();
				member.setId(Integer.parseInt(rs.getString("id")));
				member.setDepart_id(rs.getString("depart_id"));
				member.setKind(rs.getString("kind"));
				member.setUid(rs.getString("uid"));
				member.setPwd(rs.getString("pwd"));
				member.setName(rs.getString("name"));
				member.setTel(rs.getString("tel"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setPic(rs.getString("pic"));
				
				alMember.add(member);
			}
		} catch (SQLException e) { System.out.println("TeacherInquiry Error");e.printStackTrace(); }
		request.setAttribute("text1", text1);
		request.setAttribute("alMember", alMember);
		RequestDispatcher dis = request.getRequestDispatcher("ad_teacher.jsp");
		dis.forward(request, response);
	}
	private void Info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		if(id > 0) {
			String query = "select * from teacher where id = "+id+";";
			
			Connection cnt = dao.getConnection();
			Statement stmt = cnt.createStatement();
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(query);
				rs.next();
			} catch (SQLException e) { e.printStackTrace(); }
			member.setId(id);
			member.setKind(rs.getString("kind"));
			member.setDepart_id(rs.getString("depart_id"));
			member.setUid(rs.getString("uid"));
			member.setPwd(rs.getString("pwd"));
			member.setName(rs.getString("name"));
			member.setTel(rs.getString("tel"));
			member.setPhone(rs.getString("phone"));
			member.setEmail(rs.getString("email"));
			member.setPic(rs.getString("pic"));
			
			request.setAttribute("member", member);
			request.setAttribute("id", id);
			RequestDispatcher dis = request.getRequestDispatcher("ad_teacherInfo.jsp"); 
			dis.forward(request, response);
		}
	}
	private void Update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{

		Connection cnt = dao.getConnection();
		Statement stmt = cnt.createStatement();
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
	    response.sendRedirect("TeacherInquiry");
	}
	private void Insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		Connection cnt = dao.getConnection();
		Statement stmt = cnt.createStatement();
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
	    response.sendRedirect("TeacherInquiry");
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		request.setCharacterEncoding("UTF-8");
			process(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
