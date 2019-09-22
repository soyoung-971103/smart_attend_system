package controller;

import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AssistDTO;
import model.MemberDAO;
import model.MemberDTO;
import model.StudentDTO;
import model.TeacherDTO;
//import service.Pagination;
/**
 * Servlet implementation class MemberController
 */
@WebServlet({"/member-login.do","/member-register.do","/member-list.do"})
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    ArrayList<MemberDTO> alMember = null;
    MemberDTO member = null;
    StudentDTO student = null;
	TeacherDTO teacher = null;
	AssistDTO assist = null;
    HttpSession sesobj = null;
    MemberDAO dao = new MemberDAO();
    //Pagination pn = new Pagination();
    
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession(true);
		
		System.out.println("process1");		
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("member-login.do")) {
			login(request, response);
		}
		
	}
	
	/*
	private void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String email = (String) sesobj.getAttribute("email");
		
		member = dao.selectRow(email);
		
		if(member!= null) {	
			request.setAttribute("name", member.getName());
			request.setAttribute("phone", member.getPhone());
			request.getRequestDispatcher("customer-update.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("customer-fail.jsp").forward(request, response);
		}
	}
	
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int result = dao.delete(request.getParameter("email"));
		
		if(result >= 1) {	//ȸ��Ż��(����)����
			response.sendRedirect("member-list.do");
		}else {
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		}
	}	
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			
		member = new MemberDTO();
		
		member.setEmail(request.getParameter("email"));
		member.setPw(request.getParameter("pw"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		
		int result = dao.update(member);
		
		if(result >= 1) {	
			request.setAttribute("name", request.getParameter("email"));
			request.getRequestDispatcher("update-success.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("update-fail.jsp").forward(request, response);
		}
	}
	
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		member = new MemberDTO();
    	
		member.setEmail(request.getParameter("email"));
		member.setPw(request.getParameter("pw"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		
		int result = dao.insert(member);
		
		if(result >= 1) {	
			request.setAttribute("name", request.getParameter("email"));
			request.getRequestDispatcher("register-success.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("register-fail.jsp").forward(request, response);
		}
	}
	
	*/
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {			
		String kind = request.getParameter("login_kind");
		member = new MemberDTO();
		
		if(kind.equals("student")) {
			student = new StudentDTO();
			student.setSchoolno(request.getParameter("login_uid"));
			student.setPwd(request.getParameter("login_password"));				
			member = dao.loginCheckStudent(student);		
			
		}else if(kind.equals("teacher")) {
			teacher = new TeacherDTO();
			teacher.setUid(request.getParameter("login_uid"));
			teacher.setPwd(request.getParameter("login_password"));				
			member = dao.loginCheckTeacher(teacher);	
			
		}else if(kind.equals("assist")) {
			assist = new AssistDTO();
			assist.setUid(request.getParameter("login_uid"));
			assist.setPwd(request.getParameter("login_password"));				
			member = dao.loginCheckAssist(assist);	
			
		}else {
			member = null;
		}		
		
		if(member != null) {	
			sesobj.setAttribute("name", member.getName());
			sesobj.setAttribute("uid", member.getUid());
			sesobj.setAttribute("kind", kind);
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
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
