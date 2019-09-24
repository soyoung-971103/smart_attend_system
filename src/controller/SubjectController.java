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
import model.DepartDAO;
import model.DepartDTO;
import model.NoticeDAO;

@WebServlet({"/subject-list.do","/subject-detail.do","/subject-register.do", "/subject-update.do", "/subject-delete.do", "/subject-subjectnew.do" })
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
	
	ArrayList<SubjectDTO> dtoList = null;
	ArrayList<DepartDTO> dtoListDepart = null;
	SubjectDTO dto = null;
	SubjectDAO dao = null;
	DepartDAO daoDepart = new DepartDAO();
	HttpSession session = null;
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	session = request.getSession();
    	dao = new SubjectDAO();
    	
    	String uri = request.getRequestURI();
    	int lastIndex = uri.lastIndexOf('/');
    	String action = uri.substring(lastIndex + 1);
    	
    	if(action.equals("subject-list.do")) 
			list(request, response);
    	else if(action.equals("subject-detail.do")) 
    		detail(request, response);
    	else if(action.equals("subject-register.do")) 
    		register(request, response);
    	else if(action.equals("subject-update.do")) 
    		update(request, response);
    	else if(action.equals("subject-delete.do")) 
    		delete(request, response);
    	else if(action.equals("subject-subjectnew.do")) 
    		subjectnew(request, response);
    	else
    		;
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String sel1;
		String sel2;
		sel1 = request.getParameter("sel1");
		sel2 = request.getParameter("sel2");
		dtoList = dao.list(sel1, sel2);
		request.setAttribute("sel1", sel1);
		request.setAttribute("sel2", sel2);
		request.setAttribute("subjectlist", dtoList);
		request.getRequestDispatcher("as_sub.jsp").forward(request, response);
	}
	
	private void subjectnew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
   	
   	dtoListDepart = daoDepart.List();

   	request.setAttribute("listDepart", dtoListDepart);    	   		
   	request.getRequestDispatcher("as_subnew.jsp").forward(request, response);
	
	} 
	
	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	dto = dao.detail(id);
    	dtoListDepart = daoDepart.List();

       	request.setAttribute("listDepart", dtoListDepart);
		request.setAttribute("subject", dto);
		request.getRequestDispatcher("as_subupdate.jsp").forward(request, response);
    }
    
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	response.setContentType("text/html;charset=UTF-8");
    	request.setCharacterEncoding("utf-8");
    	
    	int result = dao.register(request, response);
    	if(result > 0) {
    		request.getRequestDispatcher("subject-list.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("subject-fail.jsp");
    }
    
    protected void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	int result = dao.update(request, response); // 질의를 통해 수정된 레코드의 수
    	
    	if(result > 0) {
    		request.setAttribute("id", request.getParameter("id"));
    		request.getRequestDispatcher("subject-list.do").forward(request, response);
    	}
    	else 
    		response.sendRedirect("fail.jsp"); // 실패
    }
    
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	int result = dao.delete(request, response); // 질의를 통해 수정된 레코드의 수
    	if(result > 0) {// 삭제 성공 : 영향 받은 row(record)의 수
    		request.setAttribute("id", request.getParameter("id"));
    		request.getRequestDispatcher("subject-list.do").forward(request, response);
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
