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

/**
 * Servlet implementation class BuildingController
 */
@WebServlet({ "/building-register.do", "/building-list.do", "/building-info.do", "/building-delete.do", "/building-update.do", "/building-search.do" })
public class BuildingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildingController() {
        super();
        // TODO Auto-generated constructor stub
    }

    HttpSession sesobj = null;
    BuildingDTO dto = null;
    ArrayList<BuildingDTO> dtoList = null;
    BuildingDAO dao = new BuildingDAO();
    ArrayList<ControlDTO> dtoListControl = null;
	ControlDAO daoControl = new ControlDAO();
    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("building-list.do")) {
			list(request, response);
		}else if(action.equals("building-register.do")) {
			register(request, response);
		}else if(action.equals("building-info.do")) {
			info(request, response);
		}else if(action.equals("building-update.do")) {
			update(request, response);
		}else if(action.equals("building-delete.do")) {
			delete(request, response);
		}
		else
			;		
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
    	request.getRequestDispatcher("ad_building.jsp").forward(request, response);
		
	}

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {    	
    	dto = new BuildingDTO();
    	
    	dto.setName(request.getParameter("name"));
    	dto.setFloor(Byte.parseByte(request.getParameter("floor")));
    	
    	int result = dao.insert(dto);
    	
    	if(result >= 1) {	
			request.getRequestDispatcher("building-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_buildingnew.jsp").forward(request, response);
		}
    	
    }
    
    private void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {    
    	BuildingDTO dtoInfo = new BuildingDTO();
    	dtoInfo.setId(Integer.parseInt(request.getParameter("id")));
    	dto = dao.selectOne(dtoInfo);
    	
    	if(dto != null) {
  			request.setAttribute("building", dto);
  			request.getRequestDispatcher("ad_building_update.jsp").forward(request, response);
  		}else {
  			request.getRequestDispatcher("main.jsp").forward(request, response);
  		}    	
    	
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {        
    	dto.setName(request.getParameter("name"));
    	dto.setFloor(Byte.parseByte(request.getParameter("floor")));
    	dto.setId(Integer.parseInt(request.getParameter("id")));    	
    	
    	int result = dao.update(dto);
    	
    	if(result >= 1) {
  			request.getRequestDispatcher("building-list.do").forward(request, response);
  		}else {
  			request.getRequestDispatcher("main.jsp").forward(request, response);
  		}    	
    	
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
		int result = dao.delete(Integer.parseInt(request.getParameter("id")));
		
		if(result >= 1) {	
			response.sendRedirect("building-list.do");
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
