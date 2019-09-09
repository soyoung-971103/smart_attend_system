package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DepartDAO;
import model.DepartDTO;
import model.LectureDAO;
import model.LectureDTO;
import model.MyLectureDTO;
import model.NoticeDAO;
import model.SubjectDAO;
import model.StudentDAO;
import model.StudentDTO;
import model.SubjectDTO;
import model.TeacherDAO;
import model.TeacherDTO;
import controller.TeacherController;

/**
 * Servlet implementation class LectureController
 */

@WebServlet({"/as-lecture-list.do","/as-lecture-register.do", "/as-lecture-updateT.do", "/as-lecture-updateN.do", "/as-lecture-delete.do", "/lecture-list.do", "/lecture-save.do", "/lecture-mylecture.do", "/lecture-sublecture.do"})
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
	
    ArrayList<StudentDTO> alStudent = null;
    StudentDTO student = null;
    HttpSession sesobj = null;
    HttpSession session = null;
    StudentDAO daoStudent = new StudentDAO();
    DepartDAO depart_dao = new DepartDAO();
    ArrayList<LectureDTO> alLecture = null;
    ArrayList<MyLectureDTO> alMyLec = null;
    ArrayList<DepartDTO> alDepart = null;
    DepartDTO depart = null;
    LectureDTO lecture = null;
    LectureDAO dao = null;
    LectureDAO lecture_dao = new LectureDAO();
    MyLectureDTO mylec = null;
    //Pagination pn = new Pagination();
	
//heari
	ArrayList<TeacherDTO> dtoListTeacher = null;
	TeacherController TeacherController = null;
	TeacherDAO daoTeacher = new TeacherDAO();
	ArrayList<SubjectDTO> dtoListSubject = null;
	SubjectDAO daoSubject = new SubjectDAO();
	DepartDAO daoDepart = new DepartDAO();
	ArrayList<DepartDTO> dtoListDepart = null;
    
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();
		dao = new LectureDAO();
    		conn = dao.getConnection();	
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("lecture-list.do"))
			list(request, response);
		else if(action.equals("lecture-save.do")) 
			save(request, response);
		else if(action.equals("lecture-mylecture.do")) 
			myList(request, response);
		else if(action.equals("lecture-sublecture.do")) 
			subList(request, response);	
		else if(action.equals("as-lecture-list.do")) 
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
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Cookie[] cookies = request.getCookies();
		String name;
		String value = null;
		int sta = 0;
		
		String uid = (String)sesobj.getAttribute("uid");
		student = daoStudent.list_id(uid);		
		alLecture = lecture_dao.selectAllList();		
	
		
		 if(cookies != null){	         
			 for(int i=0; i < cookies.length; i++){
	            Cookie c = cookies[i] ;	   
	            if(c.getName().length() >= 5) {
	            	name = c.getName().substring(0, 5);
		            if(name.equals("count"))
		            	sta++; 
	            }   
	        }
		 }
			
		 if(sta == 0) {
			 alMyLec = lecture_dao.select_mylecture(student.getId());
			 if(alMyLec != null) {
				 int z=1;
				 for(MyLectureDTO alm : alMyLec) {
					 for(LectureDTO al : alLecture) {
						 if(al.getId() == alm.getLecture_id()) {						 
							// 강의번호, 학과명, 학년, 반, 전공일반/교양, 선택/필수, 과목코드, 과목명, 학점, 시간, 교수님, 수강구분
							 value = Integer.toString(al.getId()) + "^" + al.getSubject().getDepart().getName() + "^" + al.getSubject().getGrade() + "^" + al.getLecture_class() + "^" + al.getSubject().getIsmajor() + "^" + al.getSubject().getIschoice() + "^" + al.getSubject().getCode() + "^" + al.getSubject().getName() + "^" + al.getSubject().getIpoint() + "^" + al.getSubject().getIhour() + "^" + al.getTeacher().getName() + "^" + "정상";
							 Cookie setCookie = new Cookie("lec"+z, value);
							 setCookie.setMaxAge(60*60*24); // 기간을 하루로 지정
							 response.addCookie(setCookie);
							 z++;		
						 }
					 }
				 }
				 Cookie setCookie = new Cookie("count", Integer.toString(z-1));
				 setCookie.setMaxAge(60*60*24); // 기간을 하루로 지정
				 response.addCookie(setCookie);
			 }
		 }
		
		request.setAttribute("list", alLecture);
		request.setAttribute("student", student);
		request.getRequestDispatcher("st_lec.jsp").forward(request, response);
	}	
	
	protected void save(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		alMyLec = new ArrayList<MyLectureDTO>();
		sesobj = request.getSession(true);		
		String uid = (String)sesobj.getAttribute("uid");
		Cookie[] cookies = request.getCookies();
		String name;
		String[] values;
		String lec;
		
		 if(cookies != null){	         
			 for(int i=0; i < cookies.length; i++){
	            Cookie c = cookies[i] ;		            
	            mylec = new MyLectureDTO();
				 
	            name = c.getName().substring(0, 3);
	            System.out.println(name);
	            
	            if(name.equals("lec")) {
	            	lec = c.getValue();
	            	values = lec.split("\\^");
	            	if(values.length != 0) {
	            		mylec.setLecture_id(Integer.parseInt(values[0]));
		            	mylec.setDepartname(values[1]);
		            	mylec.setGrade(Byte.parseByte(values[2]));
		            	mylec.setTerm((byte)2);
		            	mylec.setStudent_id(lecture_dao.select_id((String)sesobj.getAttribute("uid")));
		            	alMyLec.add(mylec);
	            	}
	            }    
	        }
	        
			student = daoStudent.list_id(uid);
	        lecture_dao.delete(student.getId());
	        lecture_dao.insert(alMyLec);
		 }
		 request.getRequestDispatcher("lecture-list.do").forward(request, response);		
	}	
	
	protected void myList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		String uid = (String)sesobj.getAttribute("uid");
		student = daoStudent.list_id(uid);		
		alMyLec = lecture_dao.selectMyList(student.getId());
		
		request.setAttribute("list", alMyLec);
		request.setAttribute("student", student);
		request.getRequestDispatcher("st_lecall.jsp").forward(request, response);
	}	
	
	protected void subList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String sel1, sel2, sel3, sel4, sel5;

		alDepart = depart_dao.List();
		
		if((sel1 = request.getParameter("sel1"))!=null) {
			sel2 = request.getParameter("sel2");
			sel3 = request.getParameter("sel3");
			sel4 = request.getParameter("sel4");
			sel5 = request.getParameter("sel5");			
		}else {
			sel1 = "2019";
			sel2 = "2";
			sel3 = "1";
			sel4 = "0";
			sel5 = Integer.toString(alDepart.get(0).getId());
		}
		
		alMyLec = lecture_dao.selectSubjectList(sel1, sel2, sel3, sel4, sel5);
		
		request.setAttribute("list", alMyLec);
		request.setAttribute("depart_list", alDepart);
		request.setAttribute("sel1", sel1);
		request.setAttribute("sel2", sel2);
		request.setAttribute("sel3", sel3);
		request.setAttribute("sel4", sel4);
		request.setAttribute("sel5", sel5);
		request.getRequestDispatcher("as_lecall.jsp").forward(request, response);
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
