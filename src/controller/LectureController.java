package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LectureDAO;
import model.LectureDTO;
import model.DepartDAO;
import model.DepartDTO;
import model.NoticeDAO;
import model.SubjectDAO;
import model.SubjectDTO;
import model.TeacherDAO;
import model.TeacherDTO;
import controller.TeacherController;

@WebServlet({"/as-lecture-list.do","/as-lecture-register.do", "/as-lecture-updateT.do", "/as-lecture-updateN.do", "/as-lecture-delete.do" })
@MultipartConfig(location="", 
fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5, 
maxRequestSize=1024*1024*5*5)
public class LectureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    java.sql.Connection conn = null;
	java.sql.Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	LectureDTO lecture = null;
	ArrayList<LectureDTO> alLecture = null;
	HttpSession session = null;
	LectureDAO dao = null;
	ArrayList<TeacherDTO> dtoListTeacher = null;
	TeacherController TeacherController = null;
	TeacherDAO daoTeacher = new TeacherDAO();
	ArrayList<SubjectDTO> dtoListSubject = null;
	SubjectDAO daoSubject = new SubjectDAO();
	DepartDAO daoDepart = new DepartDAO();
	ArrayList<DepartDTO> dtoListDepart = null;
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	session = request.getSession();
    	dao = new LectureDAO();
    	conn = dao.getConnection();
    	
    	String uri = request.getRequestURI();
    	int lastIndex = uri.lastIndexOf('/');
    	String action = uri.substring(lastIndex + 1);
    	
    	if(action.equals("as-lecture-list.do")) 
			ASlist(request, response);
    	else if(action.equals("as-lecture-register.do")) 
    		ASregister(request, response);
    	else if(action.equals("as-lecture-updateT.do")) 
    		ASupdateT(request, response);
    	else if(action.equals("as-lecture-updateN.do")) 
    		ASupdateN(request, response);
    	else if(action.equals("as-lecture-delete.do")) 
    		ASdelete(request, response);
    	else
    		;
	}
	
	protected void ASlist(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String sel1, sel2, sel3;
		sel1 = request.getParameter("sel1");
		sel2 = request.getParameter("sel2");
		sel3 = request.getParameter("sel3");

		alLecture = dao.list(sel1, sel2, sel3);
		dtoListTeacher = daoTeacher.list();
		request.setAttribute("sel1", sel1);
		request.setAttribute("sel2", sel2);
		request.setAttribute("sel3", sel3);
    	request.setAttribute("list", alLecture);
    	request.setAttribute("teacher", dtoListTeacher);
    	request.getRequestDispatcher("as_lec.jsp").forward(request, response);
	}
	
	protected void ASregister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	response.setContentType("text/html;charset=UTF-8"); 
    	request.setCharacterEncoding("utf-8");
    	
    	dao.register(request, response);
    	request.getRequestDispatcher("as-lecture-list.do").forward(request, response);

    }
	
	protected void ASupdateT(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int result = dao.updateT(request, response, id); // 질의를 통해 수정된 레코드의 수
    	
    	if(result > 0) {
    		request.setAttribute("id", request.getParameter("id"));
    		request.getRequestDispatcher("as-lecture-list.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("fail.jsp"); // 실패
    }
	
	protected void ASupdateN(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int result = dao.updateN(request, response, id); // 질의를 통해 수정된 레코드의 수
    	
    	if(result > 0) {
    		request.setAttribute("id", request.getParameter("id"));
    		request.getRequestDispatcher("as-lecture-list.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("fail.jsp"); // 실패
    }
	
	 protected void ASdelete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	dao.delete(); // 질의를 통해 수정된 레코드의 수
		request.getRequestDispatcher("as-lecture-list.do").forward(request, response);
	}
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
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
			process(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
