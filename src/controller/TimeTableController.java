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
import model.BuildingDAO;
import model.BuildingDTO;
import model.LectureDAO;
import model.LectureDTO;

/**
 * Servlet implementation class TimeController
 */
@WebServlet({ "/timetable-list.do", "/timetable-insert.do", "/timetable-update.do", "/timetable-delete.do" })
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
    TimeTableDAO dao = new TimeTableDAO();
    RoomDAO daoRoom = new RoomDAO();
    BuildingDAO daoBuilding = new BuildingDAO();
    LectureDAO daoLecture = new LectureDAO();
    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();
		
		System.out.println("process");		
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("timetable-list.do")) {
			list(request, response);
		}else if(action.equals("timetable-insert.do")) {
			insert(request, response);
		}else if(action.equals("timetabel-update.do")) {
			//update(request, response);
		}else if(action.equals("timetable-delete.do")) {
			delete(request, response);			
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
			request.getRequestDispatcher("timetable-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("as_time.jsp").forward(request, response);
		}
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	dtoList = dao.Load();
    	dtoListRoom = daoRoom.selectAllList();
    	dtoListBuilding = daoBuilding.selectAllList();
    	dtoListLecture = daoLecture.List();
    	
    	request.setAttribute("list", dtoList);
    	request.setAttribute("roomList", dtoListRoom);
    	request.setAttribute("buildingList", dtoListBuilding);
    	request.setAttribute("lectureList", dtoListLecture);
    	request.getRequestDispatcher("as_time.jsp").forward(request, response);
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
