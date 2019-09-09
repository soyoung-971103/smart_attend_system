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

import model.DepartDAO;
import model.DepartDTO;

/**
 * Servlet implementation class MemberController
 */
@WebServlet({"/depart-list.do","/depart-info.do","/depart-delete.do","/depart-update.do","/depart-insert.do"})
public class DepartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    ArrayList<DepartDTO> dtoList = null;
    DepartDTO dto = null;
    HttpSession sesobj = null;
    DepartDAO dao = new DepartDAO();
    
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();
		
		System.out.println("process");		
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("depart-list.do")) {
			list(request, response);
		}else if(action.equals("depart-insert.do")) {
			insert(request, response);
		}else if(action.equals("depart-update.do")) {
			update(request, response);
		}else if(action.equals("depart-delete.do")) {
			delete(request, response);		
		}else if(action.equals("depart-info.do")) {
			info(request, response);		
		}
		else
			;
		
	}
	
	
	private void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		DepartDTO dtoInfo = new DepartDTO();
    	dtoInfo.setId(Integer.parseInt(request.getParameter("id")));
    	dto = dao.selectOne(dtoInfo);
    	
    	if(dto != null) {
  			request.setAttribute("depart", dto);
  			request.getRequestDispatcher("ad_departUpdate.jsp").forward(request, response);
  		}else {
  			request.getRequestDispatcher("depart-list.do").forward(request, response);
  		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int result = dao.delete(Integer.parseInt(request.getParameter("id")));
		
		if(result >= 1) {	
			request.getRequestDispatcher("depart-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_depart.jsp").forward(request, response);
		}
	}
	
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setName(request.getParameter("name"));
		dto.setClassnum(Byte.parseByte(request.getParameter("classnum")));
		dto.setGradesystem(Byte.parseByte(request.getParameter("gradesystem")));
		dto.setAbbreviation(request.getParameter("abbreviation"));
		
		int result = dao.update(dto);
		
		if(result >= 1) {	
			request.getRequestDispatcher("depart-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_departUpdate.jsp").forward(request, response);
		}
	}
	
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		dto = new DepartDTO();
    	
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setName(request.getParameter("name"));
		dto.setClassnum(Byte.parseByte(request.getParameter("classnum")));
		dto.setGradesystem(Byte.parseByte(request.getParameter("gradesystem")));
		dto.setAbbreviation(request.getParameter("abbreviation"));
		
		int result = dao.insert(dto);
		
		if(result >= 1) {	
			request.getRequestDispatcher("depart-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_departnew.jsp").forward(request, response);
		}
	}
	

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
    	dtoList = dao.List();
    	request.setAttribute("list", dtoList);    	
    	request.getRequestDispatcher("ad_depart.jsp").forward(request, response);
		
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
