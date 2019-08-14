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

import model.SubjectDAO;
import model.SubjectDTO;
import model.NoticeDAO;

@WebServlet({"/subject-list.do" })
@MultipartConfig(location="", 
fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5, 
maxRequestSize=1024*1024*5*5)
public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    java.sql.Connection conn = null;
	java.sql.Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	SubjectDTO subject = null;
	ArrayList<SubjectDTO> alSubject = null;
	HttpSession session = null;
	SubjectDAO dao = null;
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	session = request.getSession();
    	dao = new SubjectDAO();
    	conn = dao.getConnection();
    	
    	String uri = request.getRequestURI();
    	int lastIndex = uri.lastIndexOf('/');
    	String action = uri.substring(lastIndex + 1);
    	
    	if(action.equals("subject-list.do")) 
			list(request, response);
    	else
    		;
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		 alSubject = dao.list();
			request.setAttribute("subjectlist", alSubject);
			request.getRequestDispatcher("te_time.jsp").forward(request, response);
	}
}
