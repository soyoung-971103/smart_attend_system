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

import model.ADRemoveDAO;
import model.ADRemoveDTO;
import model.ControlDAO;
import model.ControlDTO;
import model.DepartDAO;
import model.DepartDTO;
import model.LectureDAO;
import model.LectureDTO;
import model.MyLectureDAO;
import model.MyLectureDTO;
import model.NoticeDAO;
import model.NoticeDTO;
import model.TeacherDAO;
import model.TeacherDTO;

/** 
 * Servlet implementation class TeacherController
 */
//"/building-register.do", "/building-list.do", "/building-info.do", "/building-delete.do", "/building-update.do", "/building-search.do"
@WebServlet({"/teacher-inputdata.do", "/teacher-info.do", "/teacher-register.do", "/teacher-list.do", "/teacher-delete.do", "/teacher-update.do", "/teacher-qalist.do", "/te-lec-qaans.do", "/te-answer-save.do", "/te-main.do", })

public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ArrayList<TeacherDTO> dtoList = null;
    TeacherDTO dto = null;
    TeacherDAO dao = new TeacherDAO();
    HttpSession sesobj = null;
    String [] kind = {"전임교수", "겸임교수", "시간강사"};
    
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		

		sesobj = request.getSession();
		
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
		}else if(action.equals("teacher-qalist.do"))
			qalist(request, response);
		else if(action.equals("te-lec-qaans.do"))
			qaans(request, response);
		else if(action.equals("te-answer-save.do"))
			qasave(request, response);
		else if(action.equals("te-main.do"))
			temain(request,response);
		else
			;
		
	}
	private void Delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		dao.delete(request, response);
		response.sendRedirect("TeacherInquiry");
	}
	private void Inquiry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		dtoList = dao.list();
		

		request.setAttribute("alMember", dtoList);
		
		RequestDispatcher dis = request.getRequestDispatcher("ad_teacher.jsp");
		dis.forward(request, response);
	}
	private void Info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		if(id > 0) {

			dto = dao.info(id);
			
			ArrayList<DepartDTO> dtoListDepart = new ArrayList<DepartDTO>();
			DepartDAO daoDepart = new DepartDAO();
			dtoListDepart = daoDepart.List();
			
			request.setAttribute("Depart", dtoListDepart);
			
			request.setAttribute("kind", kind);
			request.setAttribute("member", dto);
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
		ArrayList<DepartDTO> dtoListDepart = new ArrayList<DepartDTO>();
		DepartDAO daoDepart = new DepartDAO();
		dtoListDepart = daoDepart.List();
		request.setAttribute("Depart", dtoListDepart);
		request.setAttribute("kind", kind);
		RequestDispatcher dis = request.getRequestDispatcher("ad_teachernew.jsp"); 
		dis.forward(request, response);
	}
	private void qalist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		sesobj = request.getSession();
		LectureDAO ldao = new LectureDAO();
		
		dto = dao.teacherqalist((String)sesobj.getAttribute("name"), (String)sesobj.getAttribute("uid"));
		ArrayList<LectureDTO> ldtolist = ldao.lecture_tsearch_qa(dto.getId());
		MyLectureDAO mdao = new MyLectureDAO();
		ArrayList<MyLectureDTO> mdtolist = mdao.findstu(ldtolist);
		System.out.println(mdtolist.size());
		request.setAttribute("dtolist", mdtolist);
	    request.getRequestDispatcher("te_lecqa.jsp").forward(request, response);
	}
	
	private void qaans(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		MyLectureDAO mdao = new MyLectureDAO();
		
		request.setAttribute("info", mdao.teqaansinfo(Integer.parseInt(request.getParameter("id"))));
	    request.getRequestDispatcher("te_lecqaedit.jsp").forward(request, response);
	}
	
	private void qasave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		dao.saveqa(request, response);
	    response.sendRedirect("teacher-qalist.do");
	}
	
	private void temain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		sesobj = request.getSession();
		//공지사항
		NoticeDAO ndao = new NoticeDAO();
		ArrayList<NoticeDTO> NoticeList = ndao.list(null);
		request.setAttribute("noticeList", NoticeList);
		
		//휴보강
		ADRemoveDAO adao = new ADRemoveDAO();
		ArrayList<ADRemoveDTO> adtoList = adao.DTOlist2(request, response);
		
		request.setAttribute("removeList", adtoList);
		//qa
		sesobj = request.getSession();
		LectureDAO ldao = new LectureDAO();
		
		dto = dao.teacherqalist((String)sesobj.getAttribute("name"), (String)sesobj.getAttribute("uid"));
		ArrayList<LectureDTO> ldtolist = ldao.lecture_tsearch_qa(dto.getId());
		MyLectureDAO mdao = new MyLectureDAO();
		ArrayList<MyLectureDTO> mdtolist = mdao.findstu(ldtolist);
		
		request.setAttribute("qaList", mdtolist);
		
	    request.getRequestDispatcher("te_main.jsp").forward(request, response);
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