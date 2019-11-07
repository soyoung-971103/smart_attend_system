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

import model.ControlDAO;
import model.ControlDTO;
import model.DepartDAO;
import model.DepartDTO;
import model.LectureDAO;
import model.LectureDTO;
import model.MyLectureDAO;
import model.MyLectureDTO;
import model.NoticeDTO;
import model.QnaDTO;
import model.StudentDAO;
import model.StudentDTO;
import model.SubjectDAO;
import model.SubjectDTO;
import model.TeacherDAO;
import model.TeacherDTO;
//import service.Pagination;
/**
 * Servlet implementation class StudentController
 */
@WebServlet({"/student-list.do", "/student-search.do", "/student-studentnew.do","/student-supdate.do","/student-register.do","/student-delete.do","/student-detail.do","/student-update.do","/student-qna.do", "/lecqnainsert.do", "/savestqa.do", "/stqaload.do", "/student-main.do", "/student-qnainfo.do"})
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

	
    ArrayList<StudentDTO> dtoList = null;
    StudentDTO dto = null;
    QnaDTO dtoQna = null;
    StudentDAO dao = new StudentDAO();
    HttpSession sesobj = null;
	DepartDAO daoDepart = new DepartDAO();
    //Pagination pn = new Pagination();
    ArrayList<DepartDTO> dtoListDepart = new ArrayList<DepartDTO>();
    ArrayList<ControlDTO> dtoListControl = null;
    ArrayList<NoticeDTO> dtoListNotice = null;
    ArrayList<QnaDTO> dtoListQna = null;
	ControlDAO daoControl = new ControlDAO();	
	ArrayList<StudentDTO> alStudent = null;
	StudentDTO student = null;
	ArrayList<LectureDTO> alLecture = null;
	LectureDTO lecture = null;
	LectureDAO lecture_dao = new LectureDAO();
	String pagination = null;
	 DepartDTO dtoDepart = null;
	 ArrayList<LectureDTO> dtoListLecture = null;
	 LectureDTO dtoLecture = null;
	 LectureDAO daoLecture = new LectureDAO();
    
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
		else if(action.equals("student-qna.do"))
			qna(request,response);
		else if(action.equals("lecqnainsert.do"))
			qnaadd(request,response);
		else if(action.equals("savestqa.do"))
			saveqa(request,response);
		else if(action.equals("stqaload.do"))
			loadqa(request,response);
		else if(action.equals("student-main.do"))
			stMain(request,response);
		else if(action.equals("student-qnainfo.do"))
			stMainQnaInfo(request,response);
		else if(action.equals("student-studentnew.do")) 
			studentnew(request, response);
		else if(action.equals("student-supdate.do")) 
			supdate(request, response);
		else if (action.equals("student-search.do"))
			search(request, response);
		else 
    		;
		
	}	
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		dtoListControl = daoControl.List();
		
		String tmp=request.getParameter("npage");
		String text1 = request.getParameter("text1");
		int npage = Integer.parseInt(tmp == null?"1":tmp);
		
		int limit=5;
		int start =(npage-1);
		start*=limit;
		pagination=dao.pagination(npage, dao.rowcount("SELECT  COUNT(*) FROM student"), request.getRequestURI());
		request.setAttribute("pagination", pagination);
		request.setAttribute("npage", new Integer(npage));
		dtoList = dao.list(text1,start,limit);
		
		dtoListDepart = daoDepart.List();	
 
		request.setAttribute("listDepart", dtoListDepart);
		request.setAttribute("controlList", dtoListControl);
		request.setAttribute("studentlist", dtoList);
		request.getRequestDispatcher("ad_student.jsp").forward(request, response);
	}
		
	protected void search(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, ServletException, IOException {
		alStudent = dao.search(request.getParameter("text1"));
		request.setAttribute("studentlist", alStudent);
		dtoListControl = daoControl.List();
		request.setAttribute("controlList", dtoListControl);
		request.getRequestDispatcher("ad_student.jsp").forward(request, response);
	}
	
	private void studentnew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		ArrayList<DepartDTO> dtoListDepart = new ArrayList<DepartDTO>();
		DepartDAO daoDepart = new DepartDAO();
		dtoListDepart = daoDepart.List();
		request.setAttribute("listDepart", dtoListDepart);
		dtoListDepart = daoDepart.List();
		dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
		 
    	request.setAttribute("listDepart", dtoListDepart);    	   		
    	request.getRequestDispatcher("ad_studentnew.jsp").forward(request, response);
	
	} 
		
		
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		ArrayList<DepartDTO> dtoListDepart = new ArrayList<DepartDTO>();
		DepartDAO daoDepart = new DepartDAO();

		int id = Integer.parseInt(request.getParameter("id"));
		dto = dao.detail(id);
		request.setAttribute("student", dto);
		if(dto.getPhone() != null) {String phone[]=dto.getPhone().split("-");
		request.setAttribute("phone1", phone[0]);
		request.setAttribute("phone2", phone[1]);
		request.setAttribute("phone3", phone[2]);}
		if(dto.getBirthday() != null) {String birthday[]=dto.getBirthday().split("-");
		request.setAttribute("birthday1", birthday[0]);
		request.setAttribute("birthday2", birthday[1]);
		request.setAttribute("birthday3", birthday[2]); }
		
		dtoListDepart = daoDepart.List();
		 
    	request.setAttribute("listDepart", dtoListDepart);  
    	dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
    	
		request.getRequestDispatcher("ad_studentupdate.jsp").forward(request, response);
    }
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
		int result = dao.delete(request, response); // 질의를 통해 수정된 레코드의 수
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
	    
    	int result = dao.update(request, response);
    	if(result > 0) {// 성공 가입
    		request.setAttribute("id", request.getParameter("id"));
    		request.getRequestDispatcher("student-list.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("update-fail.jsp"); // 실패			
	}
    
    protected void supdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	int id = Integer.parseInt(request.getParameter("id"));
	    
    	int result = dao.supdate(request, response,id);
    	if(result > 0) {// 성공 가입
    		request.setAttribute("id", request.getParameter("id"));
    		request.getRequestDispatcher("student-detail.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("update-fail.jsp"); // 실패			
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		ArrayList<DepartDTO> dtoListDepart = new ArrayList<DepartDTO>();
		DepartDAO daoDepart = new DepartDAO();
		dtoListDepart = daoDepart.List();
		
		dao.register(request, response);
		dto = dao.detailId();

		request.setAttribute("student", dto);
		request.setAttribute("listDepart", dtoListDepart);

		dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
		request.getRequestDispatcher("ad_studentupdate.jsp").forward(request, response);
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

	protected void qna(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession ssion = request.getSession();
		String tmp=request.getParameter("npage");
		int npage = Integer.parseInt(tmp == null?"1":tmp);
		
		int limit=5;
		int start =(npage-1);
		start*=limit;
		String uid = (String) ssion.getAttribute("uid");
		String query = "select count(*) from student left join mylecture on student.id = mylecture.student_id "
				+ "left join lecture on lecture.id = mylecture.lecture_id left join teacher on teacher.id = lecture.teacher_id "
				+ "left join subject on subject.id = lecture.subject_id where student.schoolno='"+uid+"'";
		
		pagination = dao.pagination(npage, dao.rowcount(query), request.getRequestURI());
		request.setAttribute("pagination", pagination);
		
		ArrayList<MyLectureDTO> mdtoList = new ArrayList<MyLectureDTO>();
		mdtoList = dao.qnalist(uid, start, limit);
		
		if(mdtoList.size() == 1) {
			if(mdtoList.get(0).getQaday() == null)
				request.setAttribute("dtoList", null);
			else
				request.setAttribute("dtoList", mdtoList);
		}
		
		dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
		request.getRequestDispatcher("st_lecqa.jsp").forward(request, response);
	}
	
	protected void qnaadd(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		TeacherDAO tdao = new TeacherDAO();
		DepartDAO ddao = new DepartDAO();
		DepartDTO ddto = new DepartDTO();
		
		ArrayList<LectureDTO> ldtoList = dao.lecList(request, response);
		
		dto = dao.list_id((String)sesobj.getAttribute("uid"));
		ddto.setId(dto.getDepart_id());
		ddto = ddao.selectOne(ddto);
		
		request.setAttribute("depart_name", ddto.getName());
		request.setAttribute("stu", dto);
		request.setAttribute("leclist", ldtoList);

		dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
		request.getRequestDispatcher("st_lecqanew.jsp").forward(request, response);
	}
	
	protected void saveqa(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		dao.saveqa(request,response);
		response.sendRedirect("student-qna.do");
	}
	protected void loadqa(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		LectureDAO ldao = new LectureDAO();
		TeacherDAO tdao = new TeacherDAO();
		MyLectureDAO mdao = new MyLectureDAO();
		MyLectureDTO mdto = mdao.qainfo(Integer.parseInt(request.getParameter("qa")));
		
		SubjectDAO sdao = new SubjectDAO();
		
		dto = dao.detail(mdto.getStudent_id());
		
		LectureDTO ldto = ldao.lecture_search(mdto.getLecture_id());
		SubjectDTO sdto = sdao.detail(ldto.getSubject_id());
		TeacherDTO tdto = tdao.info(ldto.getTeacher_id());
		
		request.setAttribute("tea", tdto);
		request.setAttribute("sub", sdto);
		request.setAttribute("stu", dto);
		request.setAttribute("dto", mdto);

		dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
		request.getRequestDispatcher("st_lecqaedit.jsp").forward(request, response);
	}
	
	protected void stMain(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		int id = (Integer) sesobj.getAttribute("id");
		dtoListQna = dao.QnaList(id);
		dtoListNotice = dao.NoticeList();
		request.setAttribute("dtoListQna", dtoListQna);
		request.setAttribute("dtoListNotice", dtoListNotice);

		dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
		request.getRequestDispatcher("st_main.jsp").forward(request, response);
	}
	
	protected void stMainQnaInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		dtoQna = dao.QnaInfo(id);
		request.setAttribute("dto", dtoQna);

		dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
		request.getRequestDispatcher("st_lecqainfo.jsp").forward(request, response);
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
