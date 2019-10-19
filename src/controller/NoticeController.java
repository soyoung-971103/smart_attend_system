package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
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

import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import model.NoticeDTO;
import model.LecturedayDAO;
import model.LecturedayDTO;
import model.NoticeDAO;

@WebServlet({"/main-list.do","/notice-detail.do", "/notice-register.do","/notice-update.do", "/notice-list.do", "/notice-delete.do" })
@MultipartConfig(location="", 
fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5, 
maxRequestSize=1024*1024*5*5)
public class NoticeController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    java.sql.Connection conn = null;
	java.sql.Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	NoticeDTO notice = null;
	ArrayList<NoticeDTO> alNotice = null;
	HttpSession session = null;
	NoticeDAO dao = null;

    protected void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	session = request.getSession();
    	dao = new NoticeDAO();
    	conn = dao.getConnection();
    	
    	String uri = request.getRequestURI();
    	int lastIndex = uri.lastIndexOf('/');
    	String action = uri.substring(lastIndex + 1);
    	
    	if(action.equals("notice-list.do")) 
			list(request, response);
    	else if(action.equals("notice-register.do")) 
			register(request, response);
    	else if(action.equals("notice-delete.do")) 
			delete(request, response);
    	else if(action.equals("notice-update.do")) 
			update(request, response);
    	else if(action.equals("notice-detail.do")) 
			detail(request, response);
    	else if(action.equals("main-list.do")) 
			m_list(request, response);
		else
    		;
    }
    
    protected void m_list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		alNotice = dao.list(request.getParameter("text1"));
		ArrayList<LecturedayDTO> alLectureday = new ArrayList<LecturedayDTO>();
		LecturedayDAO daoLectureday = new LecturedayDAO();
		alLectureday = daoLectureday.list();
		request.setAttribute("noticelist", alNotice); 
		request.setAttribute("lectureday", alLectureday);
		request.getRequestDispatcher("ad_main.jsp").forward(request, response);
	}
    
    protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		 alNotice = dao.list(request.getParameter("text1"));
			request.setAttribute("noticelist", alNotice);
			request.getRequestDispatcher("ad_notice.jsp").forward(request, response);
	}
    
    protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	notice = dao.detail(id);
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("ad_noticeupdate.jsp").forward(request, response);
    }
    
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	response.setContentType("text/html;charset=UTF-8");
    	request.setCharacterEncoding("utf-8");
    	
    	int result = dao.register(request, response);
    	if(result > 0) {
    		request.getRequestDispatcher("notice-list.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("register-fail.jsp");
    }
    
    protected void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	int result = dao.update(request, response); // 질의를 통해 수정된 레코드의 수
    	if(result > 0) {
    		request.setAttribute("id", request.getParameter("id"));
    		request.getRequestDispatcher("notice-list.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("fail.jsp"); // 실패
    }
    
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	int result = dao.delete(request, response); // 질의를 통해 수정된 레코드의 수
    	if(result > 0) {// 삭제 성공 : 영향 받은 row(record)의 수
    		request.setAttribute("id", request.getParameter("id"));
    		request.getRequestDispatcher("notice-list.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("fail.jsp"); // 실패
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
