package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.DepartDAO;
import model.DepartDTO;
import model.LectureDAO;
import model.LectureDTO;
import model.StudentDAO;
import model.StudentDTO;
//import service.Pagination;
/**
 * Servlet implementation class StudentController
 */
@WebServlet({"/student-list.do","/student-studentnew.do","/student-search.do","/student-register.do","/student-delete.do","/student-detail.do","/student-update.do",})
@MultipartConfig(location="", 
fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5,  
maxRequestSize=1024*1024*5*5)
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    ArrayList<StudentDTO> alStudent = null;
    StudentDTO dtoStudent = null;
    HttpSession sesobj = null;
    StudentDAO daoStudent = new StudentDAO();
    ArrayList<LectureDTO> alLecture = null;
    LectureDTO dtoLecture = null;
    LectureDAO daoLecture = new LectureDAO();
    ArrayList<DepartDTO> alDepart = null;
    DepartDTO dtoDepart = null;
    DepartDAO daoDepart = new DepartDAO();
    //Pagination pn = new Pagination();
    
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();	
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("student-list.do"))
			list(request, response);
		else if(action.equals("student-register.do")) 
			register(request, response);
		else if(action.equals("student-delete.do")) 
			delete(request, response);
		else if(action.equals("student-detail.do")) 
			detail(request, response);
		else if(action.equals("student-update.do")) 
			update(request, response);
		else if(action.equals("student-studentnew.do")) 
			studentnew(request, response);
    	else 
    		;
		
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		alStudent = daoStudent.list(request.getParameter("text1"));
		 
		request.setAttribute("studentlist", alStudent);
		request.getRequestDispatcher("ad_student.jsp").forward(request, response);
	}
	
	private void studentnew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	
		alDepart = daoDepart.List();
 
    	request.setAttribute("listDepart", alDepart);    	   		
    	request.getRequestDispatcher("ad_studentnew.jsp").forward(request, response);
	
	} 
	
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		dtoStudent = daoStudent.detail(id);
		request.setAttribute("student", dtoStudent);
		String phone[]=dtoStudent.getPhone().split("-");
		String birthday[]=dtoStudent.getBirthday().split("-");
		request.setAttribute("phone1", phone[0]);
		request.setAttribute("phone2", phone[1]);
		request.setAttribute("phone3", phone[2]);
		request.setAttribute("birthday1", birthday[0]);
		request.setAttribute("birthday2", birthday[1]);
		request.setAttribute("birthday3", birthday[2]);
		
		alDepart = daoDepart.List();
		 
    	request.setAttribute("listDepart", alDepart);  
		request.getRequestDispatcher("ad_studentupdate.jsp").forward(request, response);
    }
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int result = daoStudent.delete(request, response); // 질의를 통해 수정된 레코드의 수
    	if(result > 0) {// 삭제 성공 : 영향 받은 row(record)의 수
    		request.setAttribute("id", request.getParameter("id"));
    		request.getRequestDispatcher("student-list.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("fail.jsp"); // 실패
	}	
	
	private String partName;
    private String partValue;
	
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	response.setContentType("text/html;charset=UTF-8");
    	request.setCharacterEncoding("utf-8");
    	
    	Collection<Part> parts = request.getParts();
    	for(Part part : parts) {
	    	partName = part.getName();
	    	if(part.getContentType() != null) {
		    	partValue = getFileName(part);
		    	if (partValue != null && ! partValue.isEmpty()) {
			    	String absolutePath = getServletContext().getRealPath("/pic/st");
			    	part.write(absolutePath + File.separator + partValue);
		    	}
		    }
	    	else {
	    		partValue = request.getParameter(partName);
	    	}
	    	request.setAttribute(partName, partValue);
	    }
	    
    	int result = daoStudent.update(request, response); // 질의를 통해 수정된 레코드의 수
    	if(result > 0) {// 성공 가입
    		request.setAttribute("id", request.getParameter("id"));
    		request.getRequestDispatcher("student-list.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("update-fail.jsp"); // 실패			
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	response.setContentType("text/html;charset=UTF-8");
    	request.setCharacterEncoding("utf-8");
    	
    	Collection<Part> parts = request.getParts();
    	for(Part part : parts) {
	    	partName = part.getName();
	    	if(part.getContentType() != null) {
		    	partValue = getFileName(part);
		    	if (partValue != null && ! partValue.isEmpty()) {
			    	String absolutePath = getServletContext().getRealPath("/pic/st");
			    	part.write(absolutePath + File.separator + partValue);
		    	}
		    }
	    	else {
	    		partValue = request.getParameter(partName);
	    	}
	    	request.setAttribute(partName, partValue);
	    }
	    
    	int result = daoStudent.register(request, response); // 질의를 통해 수정된 레코드의 수
    	
    	if(result > 0) {// 성공 가입
    		//request.setAttribute("info-name", request.getParameter("name"));
    		request.getRequestDispatcher("student-list.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("register-fail.jsp"); // 실패			
	}
	
	private String getFileName(Part part) {
    	String contentDispositionHeader = part.getHeader("content-disposition");
    	String[] splitedContentDisposition = contentDispositionHeader.split(";");
    	for (String cd : splitedContentDisposition ) {
    		if (cd.trim().startsWith("filename")) {
    				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
    		}
    	}
    	return null;
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