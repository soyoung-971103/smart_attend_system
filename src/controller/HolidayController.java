package controller;

import java.io.IOException;
import java.sql.Date;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
//import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HolidayDAO;
import model.HolidayDTO;
//import service.Pagination;
/**
 * Servlet implementation class MemberController
 */
@WebServlet({"/Holiday-list.do","/Holiday-info.do","/Holiday-delete.do","/Holiday-update.do","/Holiday-insert.do"})
public class HolidayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolidayController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    ArrayList<HolidayDTO> dtoList = null;
    HolidayDTO dto = null;
    HttpSession sesobj = null;
    HolidayDAO dao = new HolidayDAO();
    
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();
		
		System.out.println("process");		
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("Holiday-list.do")) {
			list(request, response);
		}else if(action.equals("Holiday-insert.do")) {
			insert(request, response);
		}else if(action.equals("Holiday-update.do")) {
			update(request, response);
		}else if(action.equals("Holiday-delete.do")) {
			delete(request, response);		
		}else if(action.equals("Holiday-info.do")) {
			info(request, response);		
		}
		else
			;
		
	}
	
	
	private void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		HolidayDTO dtoInfo = new HolidayDTO();
    	dtoInfo.setId(Integer.parseInt(request.getParameter("id")));
    	dto = dao.selectOne(dtoInfo);
    	
    	if(dto != null) {
  			request.setAttribute("Holiday", dto);
  			request.getRequestDispatcher("ad_Holiday_update.jsp").forward(request, response);
  		}else {
  			request.getRequestDispatcher("main.jsp").forward(request, response);
  		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int result = dao.delete(Integer.parseInt(request.getParameter("id")));
		
		if(result >= 1) {	
			request.getRequestDispatcher("Holiday-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_Holiday.jsp").forward(request, response);
		}
	}
	
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			
		dto = new HolidayDTO();
    	
		dto.setYyyy(Integer.parseInt(request.getParameter("yyyy")));
		String from = (request.getParameter("holiday"));
		Date d = Date.valueOf(from);
		dto.setHoliday(d);
		dto.setReason(request.getParameter("reason"));
		
		int result = dao.update(dto);
		
		if(result >= 1) {	
			request.getRequestDispatcher("Holiday-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_Holiday.jsp").forward(request, response);
		}
	}
	
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		dto = new HolidayDTO();
    	
		dto.setYyyy(Integer.parseInt(request.getParameter("yyyy")));
		String from = (request.getParameter("holiday"));
		Date d = Date.valueOf(from);
		dto.setHoliday(d);
		dto.setReason(request.getParameter("reason"));
		
		int result = dao.insert(dto);
		
		if(result >= 1) {	
			request.getRequestDispatcher("Holiday-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_Holidaynew.jsp").forward(request, response);
		}
	}
	

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
    	dtoList = dao.List();
    	request.setAttribute("list", dtoList);    	
    	request.getRequestDispatcher("ad_building.jsp").forward(request, response);
		
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
