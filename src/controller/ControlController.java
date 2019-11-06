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

import model.ControlDAO;
import model.ControlDTO;
import model.DepartDAO;
import model.DepartDTO;
import model.SubjectDTO;
import model.TimeTableDAO;
import model.TimeTableDTO;

/**
 * Servlet implementation class ControlController
 */
@WebServlet({ "/control-list.do", "/control-update.do" })
public class ControlController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlController() {
        super();
        // TODO Auto-generated constructor stub
    }

    ArrayList<ControlDTO> dtoListControl = null;
    ControlDTO dto = null;
    HttpSession sesobj = null;
    ControlDAO dao = new ControlDAO();
    ArrayList<DepartDTO> dtoListDepart = null;
    DepartDTO dtoDepart = null;
    DepartDAO daoDepart = new DepartDAO();
    
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("control-list.do")) {
			list(request, response);
		}else if(action.equals("control-update.do")) {
			update(request, response);
		}
		else
			;
		
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
		dto = new ControlDTO();
		
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setSubjecttime(Byte.parseByte(request.getParameter("subjecttime")));
		dto.setLecturetime(Byte.parseByte(request.getParameter("lecturetime")));
		dto.setMylecturetime(Byte.parseByte(request.getParameter("mylecturetime")));
		dto.setAttendtime(Byte.parseByte(request.getParameter("attendtime")));
		
		int result = dao.update(dto);
		
		if(result >= 1) {	
			request.getRequestDispatcher("control-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_control.jsp").forward(request, response);
		}
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
    	dtoListControl = dao.List();
    	dtoListDepart = daoDepart.List();
    	request.setAttribute("controlList", dtoListControl);    
    	request.setAttribute("departList", dtoListDepart); 
    	request.getRequestDispatcher("ad_control.jsp").forward(request, response);
		
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