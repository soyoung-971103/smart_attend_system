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

import model.DepartDAO;
import model.DepartDTO;
import model.TeacherDAO;
import model.TeacherDTO;

/**
 * Servlet implementation class TeacherController
 */
//"/building-register.do", "/building-list.do", "/building-info.do", "/building-delete.do", "/building-update.do", "/building-search.do"
@WebServlet({"/teacher-inputdata.do", "/teacher-info.do", "/teacher-register.do", "/teacher-list.do", "/teacher-delete.do", "/teacher-update.do"})

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
	String [] kind = {"전임교수", "겸임교수", "시간강사"};
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		

		sesobj = request.getSession();
		
		System.out.println("process");		
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("teacher-update.do")) {
			Update(request, response);
		}else if(action.equals("teacher-info.do")){
			Info(request, response);
		}else if(action.equals("teacher-register.do")) {
			Insert(request, response);
		}else if(action.equals("teacher-delete.do")) {
			Delete(request, response);
		}else if(action.equals("teacher-list.do")) {
			Inquiry(request, response);
		}else if(action.equals("teacher-inputdata.do")) {
			inputdata(request,response);
		}
		else
			;
		
	}//
	private void Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		dao.delete(request, response);
		response.sendRedirect("TeacherInquiry");
	}
	private void Inquiry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		alMember = dao.list();
		

		request.setAttribute("alMember", alMember);
		
		RequestDispatcher dis = request.getRequestDispatcher("ad_teacher.jsp");
		dis.forward(request, response);
	}
	private void Info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		if(id > 0) {

			member = dao.info(id);
			
			ArrayList<DepartDTO> daoDepartlist = new ArrayList<DepartDTO>();
			DepartDAO daoDepart = new DepartDAO();
			daoDepartlist = daoDepart.List();
			
			request.setAttribute("Depart", daoDepartlist);
			
			request.setAttribute("kind", kind);
			request.setAttribute("member", member);
			RequestDispatcher dis = request.getRequestDispatcher("ad_teacherInfo.jsp"); 
			dis.forward(request, response);
		}
	}
	private void Update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{

		dao.update(request, response);
		
	    response.sendRedirect("TeacherInquiry");
	}
	private void Insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		dao.insert(request, response);
	    response.sendRedirect("TeacherInquiry");
	}
	
	private void inputdata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		ArrayList<DepartDTO> daoDepartlist = new ArrayList<DepartDTO>();
		DepartDAO daoDepart = new DepartDAO();
		daoDepartlist = daoDepart.List();
		request.setAttribute("Depart", daoDepartlist);
		request.setAttribute("kind", kind);
		RequestDispatcher dis = request.getRequestDispatcher("ad_teachernew.jsp"); 
		dis.forward(request, response);
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