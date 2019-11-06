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

import model.BuildingDAO;
import model.BuildingDTO;
import model.ControlDAO;
import model.ControlDTO;
import model.DepartDAO;
import model.DepartDTO;
import model.RoomDAO;
import model.RoomDTO;

/**
 * Servlet implementation class RoomController
 */
@WebServlet({ "/room-list.do","/room-new.do" , "/room-register.do", "/room-info.do", "/room-update.do", "/room-delete.do" })
public class RoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    HttpSession sesobj = null;
    ArrayList<RoomDTO> dtoList = null;
    ArrayList<DepartDTO> dtoListDepart = null;
    ArrayList<BuildingDTO> dtoListBuilding = null;
    RoomDTO dto = null;
    RoomDAO dao = new RoomDAO();
    DepartDAO daoDepart = new DepartDAO();
    BuildingDAO daoBuilding = new BuildingDAO();
    ArrayList<ControlDTO> dtoListControl = null;
	ControlDAO daoControl = new ControlDAO();
    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();	
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("room-list.do")) {
			list(request, response);
		}else if(action.equals("room-new.do")) {
			roomNew(request, response);
		}else if(action.equals("room-register.do")) {
			register(request, response);
		}else if(action.equals("room-info.do")) {
			info(request, response);
		}else if(action.equals("room-update.do")) {
			update(request, response);
		}else if(action.equals("room-delete.do")) {
			delete(request, response);
		}
		else
			;		
	}
    
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {    	
    	dto = new RoomDTO();
    	dto.setBuilding_id(Integer.parseInt(request.getParameter("building_id")));
    	dto.setFloor(Byte.parseByte(request.getParameter("floor")));
    	dto.setHo(request.getParameter("ho"));
    	dto.setDepart_id(Integer.parseInt(request.getParameter("depart_id")));
    	dto.setName(request.getParameter("name"));
    	dto.setKind(request.getParameter("kind"));
    	dto.setArea(Integer.parseInt(request.getParameter("area")));
    	    	
    	int result = dao.insert(dto);
    	
    	if(result >= 1) {	
			request.getRequestDispatcher("room-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_roomnew.jsp").forward(request, response);
		}
    	
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	String text1 = request.getParameter("text1");

    	if(text1 == null)    	
    		dtoList = dao.selectAllList();
    	else
    		dtoList = dao.selectAllList(text1);

    	dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
    	request.setAttribute("list", dtoList);    	
    	request.getRequestDispatcher("ad_room.jsp").forward(request, response);
		
	}
    
    private void roomNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {    	
    	dtoListDepart = daoDepart.List();
    	dtoListBuilding = daoBuilding.selectAllList();
 
    	request.setAttribute("listDepart", dtoListDepart);    	
    	request.setAttribute("listBuilding", dtoListBuilding);  
    	dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
    	request.getRequestDispatcher("ad_roomnew.jsp").forward(request, response);
	
	} 

    private void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {        
    	RoomDTO dtoInfo = new RoomDTO();
    	dtoInfo.setId(Integer.parseInt(request.getParameter("id")));
    	dto = dao.selectOne(dtoInfo);
    	
    	dtoListDepart = daoDepart.List();
    	dtoListBuilding = daoBuilding.selectAllList();

    	dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
    	
    	if(dto != null) {
  			request.setAttribute("room", dto);  			 
  	    	request.setAttribute("listDepart", dtoListDepart);    	
  	    	request.setAttribute("listBuilding", dtoListBuilding);    
  			request.getRequestDispatcher("ad_room_update.jsp").forward(request, response);
  		}else {
  			request.getRequestDispatcher("main.jsp").forward(request, response);
  		}    	
    	
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {        
    	dto = new RoomDTO();
    	dto.setId(Integer.parseInt(request.getParameter("id")));
    	dto.setBuilding_id(Integer.parseInt(request.getParameter("building_id")));
    	dto.setFloor(Byte.parseByte(request.getParameter("floor")));
    	dto.setHo(request.getParameter("ho"));
    	dto.setDepart_id(Integer.parseInt(request.getParameter("depart_id")));
    	dto.setName(request.getParameter("name"));
    	dto.setKind(request.getParameter("kind"));
    	dto.setArea(Integer.parseInt(request.getParameter("area")));
    	
    	int result = dao.update(dto);
    	
    	if(result >= 1) {
  			request.getRequestDispatcher("room-list.do").forward(request, response);
  		}else {
  			request.getRequestDispatcher("main.jsp").forward(request, response);
  		}    	
    	
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int result = dao.delete(Integer.parseInt(request.getParameter("id")));
		
		if(result >= 1) {	//(삭제)성공
			response.sendRedirect("room-list.do");
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
		try {
			process(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
