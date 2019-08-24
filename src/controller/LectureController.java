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

@WebServlet({"/lecture-list.do","/lecture-register.do", "/lecture-update.do", "/lecture-delete.do" })
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
    	
    	if(action.equals("lecture-list.do")) 
			list(request, response);
    	/* else if(action.equals("lecture-register.do")) 
    		register(request, response);
    	else if(action.equals("lecture-update.do")) 
    		//update(request, response);
    	else if(action.equals("lecture-delete.do")) 
    		//delete(request, response); */
    	else
    		;
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		alLecture = dao.list();
    	request.setAttribute("list", alLecture);
    	request.getRequestDispatcher("as_lec.jsp").forward(request, response);
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
