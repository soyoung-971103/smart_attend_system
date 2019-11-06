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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AssistDTO;
import model.ControlDAO;
import model.ControlDTO;
import model.DepartDAO;
import model.DepartDTO;
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
	
    MemberDTO dto = null;
    StudentDTO dtoStudent = null;
	TeacherDTO dtoTeacher = null;
	AssistDTO dtoAssist = null;
	DepartDTO dtoDepart = null;
    HttpSession sesobj = null;
    Cookie cok = null;
    MemberDAO dao = new MemberDAO();
    DepartDAO daoDepart = new DepartDAO();
    //Pagination pn = new Pagination();
    String admin_id = "admin";
    String admin_pw = "1234";
    String admin_name = "admin";
    ArrayList<ControlDTO> dtoListControl = null;
    ControlDAO daoControl = new ControlDAO();
    
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession(true);
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("member-login.do")) {
			login(request, response);
		}
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {			
		String kind = request.getParameter("login_kind");
		dtoDepart = new DepartDTO();
		String depart;
		dto = new MemberDTO();
		
		
		if(kind.equals("student")) {
			dtoStudent = new StudentDTO();
			dtoStudent.setSchoolno(request.getParameter("login_uid"));
			dtoStudent.setPwd(request.getParameter("login_password"));				
			dto = dao.loginCheckStudent(dtoStudent);				
		}else if(kind.equals("teacher")) {
			dtoTeacher = new TeacherDTO();
			dtoTeacher.setUid(request.getParameter("login_uid"));
			dtoTeacher.setPwd(request.getParameter("login_password"));			
			dto = dao.loginCheckTeacher(dtoTeacher);				
		}else if(kind.equals("assist")) {
			dtoAssist = new AssistDTO();
			dtoAssist.setUid(request.getParameter("login_uid"));
			dtoAssist.setPwd(request.getParameter("login_password"));				
			dto = dao.loginCheckAssist(dtoAssist);				
		}else if(kind.equals("admin")) {
			dto = new MemberDTO();
			dto.setName(admin_name);
			dto.setUid(admin_id);
			dto.setId(0);
			dto.setDepart_id(0);
		}else {
			dto = null;
		}		
		
		if(dto != null) {	
			if(dto.getDepart_id() != 0) {
				dtoDepart.setId(dto.getDepart_id());
				dtoDepart = daoDepart.selectOne(dtoDepart);
				depart = dtoDepart.getName();
				sesobj.setAttribute("depart", depart);
			}			
			
			sesobj.setAttribute("name", dto.getName());
			sesobj.setAttribute("uid", dto.getUid());
			sesobj.setAttribute("id", dto.getId());
			sesobj.setAttribute("kind", kind);			
			sesobj.setAttribute("depart_id", dto.getDepart_id());
			

			dtoListControl = daoControl.List();
			request.setAttribute("controlList", dtoListControl);
			
			if(kind.equals("student")) {
				request.getRequestDispatcher("student-main.do").forward(request, response);
			}else if(kind.equals("teacher")) {
				request.getRequestDispatcher("te-main.do").forward(request, response);
			}else if(kind.equals("assist")) {				
				request.getRequestDispatcher("assistmain_list.do").forward(request, response);
			}else if(kind.equals("admin")) {
				request.getRequestDispatcher("admain_list.do").forward(request, response);			
			}else {
				request.getRequestDispatcher("main.jsp").forward(request, response);
			}
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
