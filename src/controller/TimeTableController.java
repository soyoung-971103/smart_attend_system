package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TimeTableDAO;
import model.TimeTableDTO;
import model.RoomDAO;
import model.RoomDTO;
import model.SubjectDAO;
import model.SubjectDTO;
import model.TeacherDAO;
import model.TeacherDTO;
import model.BuildingDAO;
import model.BuildingDTO;
import model.DepartDAO;
import model.DepartDTO;
import model.LectureDAO;
import model.LectureDTO;

/**
 * Servlet implementation class TimeController
 */
@WebServlet({ "/timetable-list.do", "/timetable-tdetail.do", "/timetable-insert.do", "/timetable-delete.do", "/as-timetable-all.do", "/ad-timetable-all.do" })
public class TimeTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeTableController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    HttpSession sesobj = null;
    TimeTableDTO dto = null;
    ArrayList<TimeTableDTO> dtoList = null;
    ArrayList<RoomDTO> dtoListRoom = null;
    ArrayList<BuildingDTO> dtoListBuilding = null;
    ArrayList<LectureDTO> dtoListLecture = null;
    ArrayList<DepartDTO> dtoListDepart = null;
    ArrayList<SubjectDTO> dtoListSubject = null;
    ArrayList<TeacherDTO> dtoListTeacher = null;
    TimeTableDAO dao = new TimeTableDAO();
    RoomDAO daoRoom = new RoomDAO();
    SubjectDAO daoSubject = new SubjectDAO();
    BuildingDAO daoBuilding = new BuildingDAO();
    LectureDAO daoLecture = new LectureDAO();
    DepartDAO daoDepart = new DepartDAO();
    TeacherDAO daoTeacher = new TeacherDAO();
    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();	
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("timetable-list.do")) {
			list(request, response);
		}else if(action.equals("timetable-insert.do")) {
			insert(request, response);
		}else if(action.equals("timetable-tdetail.do")) {
			Tdetail(request, response);
		}else if(action.equals("timetable-delete.do")) {
			delete(request, response);			
		}else if(action.equals("as-timetable-all.do")){
			as_ShowAll(request, response);
		}else if(action.equals("ad-timetable-all.do")){
			ad_ShowAll(request, response);
		}
		else
			;
		
	}
    
    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	
    	dto = new TimeTableDTO();
    	dto.setLecture_id(Integer.parseInt(request.getParameter("lid")));
    	dto.setWeekday(request.getParameter("w"));
    	dto.setIstart(Byte.parseByte(request.getParameter("is")));
    	dto.setIhour(Byte.parseByte(request.getParameter("ih")));
    	dto.setRoom_id(Integer.parseInt(request.getParameter("ri")));
    	
    	int result = dao.insert(dto);
    	
    	if(result >= 1) {	
    		response.sendRedirect("timetable-list.do");
		}else {
			response.sendRedirect("timetable-list.do");
		}
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	dtoList = dao.Load();
    	dtoListRoom = daoRoom.selectAllList();
    	dtoListBuilding = daoBuilding.selectAllList();
    	dtoListLecture = daoLecture.list();
    	dtoListDepart = daoDepart.List();
    	dtoListTeacher = daoTeacher.list();
    	
    	request.setAttribute("list", dtoList);
    	request.setAttribute("roomList", dtoListRoom);
    	request.setAttribute("buildingList", dtoListBuilding);
    	request.setAttribute("lectureList", dtoListLecture);
    	request.setAttribute("departList", dtoListDepart);
    	request.setAttribute("teacherList", dtoListTeacher);
    	request.getRequestDispatcher("as_time.jsp").forward(request, response);
	}
    
    protected void Tdetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		dtoList = dao.Tdetail(sesobj);
		
		dtoListRoom = daoRoom.selectAllList();
    	dtoListBuilding = daoBuilding.selectAllList();
    	dtoListLecture = daoLecture.list();
    	dtoListDepart = daoDepart.List();
    	dtoListTeacher = daoTeacher.list();
    	
    	request.setAttribute("list", dtoList);
    	request.setAttribute("roomList", dtoListRoom);
    	request.setAttribute("buildingList", dtoListBuilding);
    	request.setAttribute("lectureList", dtoListLecture);
    	request.setAttribute("departList", dtoListDepart);
    	request.setAttribute("teacherList", dtoListTeacher);
		
		request.getRequestDispatcher("te_time.jsp").forward(request, response);
	}
    
    private void as_ShowAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	dtoList = dao.Load();
    	dtoListDepart = daoDepart.List();
    	
    	request.setAttribute("list", dtoList);
    	request.setAttribute("departList", dtoListDepart);
    	request.getRequestDispatcher("as_timeall.jsp").forward(request, response);
	}
    
    private void ad_ShowAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	dtoList = dao.Load();
    	dtoListDepart = daoDepart.List();
    	
    	request.setAttribute("list", dtoList);
    	request.setAttribute("departList", dtoListDepart);
    	request.getRequestDispatcher("ad_timeall.jsp").forward(request, response);
	}
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int result = dao.delete(Integer.parseInt(request.getParameter("id")));
	
		if(result >= 1) {	//(삭제)성공
			response.sendRedirect("timetable-list.do");
		}else {
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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