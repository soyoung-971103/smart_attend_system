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
import model.MyLectureDAO;
import model.MyLectureDTO;
import model.BuildingDAO;
import model.BuildingDTO;
import model.ControlDAO;
import model.ControlDTO;
import model.DepartDAO;
import model.DepartDTO;
import model.NoticeDAO;
import model.RoomDAO;
import model.RoomDTO;
import model.SubjectDAO;
import model.SubjectDTO;
import model.TeacherDAO;
import model.TeacherDTO;
import model.TimeTableDAO;
import model.TimeTableDTO;
import controller.TeacherController;

@WebServlet({"/mylecture-sdetail.do" })
@MultipartConfig(location="", 
fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5,  
maxRequestSize=1024*1024*5*5)
public class MyLectureController extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyLectureController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	HttpSession sesobj = null;
	LectureDTO lecture = null;
	ArrayList<MyLectureDTO> dtoList = null;
	ArrayList<TimeTableDTO> dtoListTime = null;
    ArrayList<RoomDTO> dtoListRoom = null;
    ArrayList<BuildingDTO> dtoListBuilding = null;
    ArrayList<LectureDTO> dtoListLecture = null;
    ArrayList<DepartDTO> dtoListDepart = null;
    ArrayList<SubjectDTO> dtoListSubject = null;
    ArrayList<TeacherDTO> dtoListTeacher = null;
	MyLectureDAO dao = new MyLectureDAO();
    TimeTableDAO daoTimetable = new TimeTableDAO();
    RoomDAO daoRoom = new RoomDAO();
    SubjectDAO daoSubject = new SubjectDAO();
    BuildingDAO daoBuilding = new BuildingDAO();
    LectureDAO daoLecture = new LectureDAO();
    DepartDAO daoDepart = new DepartDAO();
    TeacherDAO daoTeacher = new TeacherDAO();
    ArrayList<ControlDTO> dtoListControl = null;
	ControlDAO daoControl = new ControlDAO();
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	sesobj = request.getSession();
    	dao = new MyLectureDAO();
    	
    	String uri = request.getRequestURI();
    	int lastIndex = uri.lastIndexOf('/');
    	String action = uri.substring(lastIndex + 1);
    	
    	if(action.equals("mylecture-sdetail.do")) 
			Sdetail(request, response);
    	else
    		;
	}
	
	protected void Sdetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		dtoList = dao.Sdetail(sesobj);
		
		dtoListRoom = daoRoom.selectAllList();
		dtoListTime = daoTimetable.Load();
    	dtoListBuilding = daoBuilding.selectAllList();
    	dtoListLecture = daoLecture.list();
    	dtoListDepart = daoDepart.List();
    	dtoListTeacher = daoTeacher.list();
    	dtoListControl = daoControl.List();
    	
    	request.setAttribute("controlList", dtoListControl);
    	request.setAttribute("list", dtoList);
    	request.setAttribute("timeList", dtoListTime);
    	request.setAttribute("roomList", dtoListRoom);
    	request.setAttribute("buildingList", dtoListBuilding);
    	request.setAttribute("lectureList", dtoListLecture);
    	request.setAttribute("departList", dtoListDepart);
    	request.setAttribute("teacherList", dtoListTeacher);
		
		request.getRequestDispatcher("st_time.jsp").forward(request, response);
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