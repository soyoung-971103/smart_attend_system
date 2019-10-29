package controller;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BuildingDTO;
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
import model.LecturedayDTO;
import model.RoomDTO;
import model.TeacherDAO;
import model.TeacherDTO;
import model.TimeTableDTO;

/** 
 * Servlet implementation class TeacherController
 */
//"/building-register.do", "/building-list.do", "/building-info.do", "/building-delete.do", "/building-update.do", "/building-search.do"
@WebServlet({"/teacher-inputdata.do", "/teacher-info.do", "/teacher-register.do", "/teacher-list.do", "/teacher-delete.do", "/teacher-update.do", "/teacher-qalist.do", "/te-lec-qaans.do", "/te-answer-save.do", "/te-main.do","/teacher-lecmove-select.do", "/teacher-lecmoverest.do", "/teacher-lecrestsave.do"})

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
    ArrayList<TimeTableDTO> dtoListTimeTable = null;
    ArrayList<LecturedayDTO> dtoListLectureday = null;
    ArrayList<RoomDTO> dtoListRoom = null;
    TeacherDTO dto = null;
    DepartDTO dtoDepart = null;
	RoomDTO dtoRoom = null;
	BuildingDTO dtoBuilding = null;
	LecturedayDTO dtoLectureday = null;
    TeacherDAO dao = new TeacherDAO();    
    HttpSession sesobj = null;
    Cookie cookies[] = null;
    String [] kind = {"전임교수", "겸임교수", "시간강사"};
    
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		

		sesobj = request.getSession();
		cookies = request.getCookies();
		
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
		}else if(action.equals("teacher-lecmove-select.do")) {
			lecmoveSelect(request,response);
		}else if(action.equals("teacher-lecmoverest.do")) {
			lecmoveRest(request,response);
		}else if(action.equals("teacher-lecrestsave.do")) {
			lecRestSave(request,response);
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
		response.sendRedirect("teacher-list.do");
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
		
	    response.sendRedirect("teacher-list.do");
	}
	private void Insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		dao.insert(request, response);
	    response.sendRedirect("teacher-list.do");
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
	
	private void lecmoveSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		int id = (int) sesobj.getAttribute("id");
		int sdate = Integer.parseInt(getCurMonday());
		int edate = Integer.parseInt(getCurSunday());
		dtoListTimeTable = dao.Load(id, sdate, edate);		
		sesobj.setAttribute("sdate", sdate);
		sesobj.setAttribute("edate", edate);
		request.setAttribute("list", dtoListTimeTable);
    	request.getRequestDispatcher("te_lecmovenorm.jsp").forward(request, response);
	}
	
	private void lecmoveRest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		String normdate = request.getParameter("normdate");
		String normweek = request.getParameter("normweek");
		String normstart = request.getParameter("normstart");
		String normhour = request.getParameter("normhour");
		String lecturenorm_data = null;	
		String day1 = null;
		String w = null;
		int start, hour;
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");		
		Calendar calendar = Calendar.getInstance();		
		Date date = null;
		int week;
		String timeTableList = null;
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("lecturenorm_data")) {
				lecturenorm_data = cookie.getValue();
			}
		}		

		int sdate = Integer.parseInt(getMonday(normdate.substring(0, 4), normdate.substring(5, 7), normdate.substring(8, 10)));
		int edate = Integer.parseInt(getSunday(normdate.substring(0, 4), normdate.substring(5, 7), normdate.substring(8, 10)));
		String lectureState[] = lecturenorm_data.split("\\^");
		int lectureId = Integer.parseInt(lectureState[8]);
		int[][] timeTable = new int[14][7];
		String t;

		dtoListLectureday = dao.Check(lectureId, sdate, edate);		
		
		for(LecturedayDTO day : dtoListLectureday) {
			if(day.getNormstate().equals("1")) { 
				date = day.getNormdate();
				calendar.setTime(date);		
				week = calendar.get(Calendar.DAY_OF_WEEK);
				for(int i = 0; i < day.getNormhour(); i++)
					timeTable[day.getNormstart()+i][(week-1)] = 1;			
			}else if(day.getNormstate().equals("4") && day.getReststate().equals("1") && day.getState().equals("최종승인")){
				date = day.getRestdate();
				calendar.setTime(date);		
				week = calendar.get(Calendar.DAY_OF_WEEK);
				for(int i = 0; i < day.getNormhour(); i++)
					timeTable[day.getNormstart()+i][(week-1)] = 1;
			}
		}
		for(int i=0;i<14;i++)
			timeTable[i][0] = 1;
		
		timeTableList = Integer.toString(timeTable[0][0]);
		for(int i=0;i<14;i++) {
			for(int j=0;j<7;j++) {
				if(i!=0 || j!=0) {
					t = Integer.toString(timeTable[i][j]);
					timeTableList = timeTableList +"^"+t;
				}
			}
		}
		
		if(request.getParameter("day") != null) {
			day1 = request.getParameter("day");
			start =Integer.parseInt(request.getParameter("start"));
			hour =Integer.parseInt(request.getParameter("hour"));
			w = request.getParameter("w");
			
			request.setAttribute("restdate", day1);
			request.setAttribute("restweek", w);
			request.setAttribute("reststart", start);
			request.setAttribute("resthour", hour);
			request.setAttribute("dtoListRoom", dtoListRoom);
			
			dtoListRoom = dao.RoomCheck(start, day1);
		}
		
		request.setAttribute("normdate", normdate);
		request.setAttribute("normweek", normweek);
		request.setAttribute("normstart", normstart);
		request.setAttribute("normhour", normhour);		
		request.setAttribute("lecturenorm_data", lecturenorm_data);
		request.setAttribute("timeTableList", timeTableList);
		
		request.getRequestDispatcher("te_lecmoverest.jsp").forward(request, response);
	}
	
	private void lecRestSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		String restdate_s = request.getParameter("restdate");
		Byte reststart = Byte.parseByte(request.getParameter("reststart"));
		Byte resthour = Byte.parseByte(request.getParameter("resthour"));
		int room_id = Integer.parseInt(request.getParameter("room_no"));
		String lecturenorm_data = null;	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 		java.util.Date date1;
 		Date date2 =null; 		
 		dtoLectureday= new LecturedayDTO();
 		
		try {
			date1 = formatter.parse(restdate_s);
			String test1 = formatter.format(date1);
			date2 = formatter.parse(test1);		
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dtoLectureday.setRestdate(null);
		}
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("lecturenorm_data")) {
				lecturenorm_data = cookie.getValue();
			}
		}	
		
		String lectureState[] = lecturenorm_data.split("\\^");
		int lectureId = Integer.parseInt(lectureState[8]);
		
		dtoLectureday.setId(lectureId);
		dtoLectureday.setResthour(resthour);
		dtoLectureday.setReststart(reststart);
		dtoLectureday.setRoom_id(room_id);
		dtoLectureday.setRestdate(date2);
		
		if(dao.RestUpdate(dtoLectureday) != 0) {
			request.getRequestDispatcher("te_lecmove.jsp").forward(request, response);
		}		
		
	}
	
	
	
	public static String getCurMonday(){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
 		Calendar c = Calendar.getInstance();
 		c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
 		return formatter.format(c.getTime());
 	}

 	public static String getCurSunday(){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
 		Calendar c = Calendar.getInstance();	
 		c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
 		c.add(c.DATE,7);
 		return formatter.format(c.getTime());
 	}
 	
 	//특정 년,월,주 차에 월요일 구하기
 	public static String getMonday(String yyyy,String mm, String wk){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
 		Calendar c = Calendar.getInstance();
 		int y=Integer.parseInt(yyyy);
 		int m=Integer.parseInt(mm)-1;
 		int w=Integer.parseInt(wk); 		
 		c.set(y, m, w);
 		c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
 		return formatter.format(c.getTime());

 	} 	 	

 	//특정 년,월,주 차에 일요일 구하기
 	public static String getSunday(String yyyy,String mm, String wk){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
 		Calendar c = Calendar.getInstance();
 		int y=Integer.parseInt(yyyy);
 		int m=Integer.parseInt(mm)-1;
 		int w=Integer.parseInt(wk); 		
 		c.set(y, m, w);
 		c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
 		c.add(c.DATE,7);
 		return formatter.format(c.getTime());

 	}
 	//출처: https://jang8584.tistory.com/217 [개발자의 길]
	
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
