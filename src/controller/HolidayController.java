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

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.HolidayDAO;
import model.HolidayDTO;
//import service.Pagination;
/**
 * Servlet implementation class MemberController
 */
@WebServlet({"/holiday-list.do","/holiday-info.do","/holiday-delete.do","/holiday-update.do","/holiday-insert.do"})
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
		
		if(action.equals("holiday-list.do")) {
			list(request, response);
		}else if(action.equals("holiday-insert.do")) {
			insert(request, response);
		}else if(action.equals("holiday-update.do")) {
			update(request, response);
		}else if(action.equals("holiday-delete.do")) {
			delete(request, response);		
		}else if(action.equals("holiday-info.do")) {
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
  			request.setAttribute("holiday", dto);
  			request.getRequestDispatcher("ad_holidayupdate.jsp").forward(request, response);
  		}else {
  			request.getRequestDispatcher("holiday-list.do").forward(request, response);
  		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int result = dao.delete(Integer.parseInt(request.getParameter("id")));
		
		if(result >= 1) {	
			request.getRequestDispatcher("holiday-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_holiday.jsp").forward(request, response);
		}
	}
	
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		dto.setYyyy(Integer.parseInt(request.getParameter("yyyy")));
		SimpleDateFormat utilDate = new SimpleDateFormat("yyyy-MM-dd");
		String day = request.getParameter("holiday");
		try {
			dto.setHoliday(utilDate.parse(day));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setReason(request.getParameter("reason"));
		
		int result = dao.update(dto);
		
		if(result >= 1) {	
			request.getRequestDispatcher("holiday-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_holidayupdate.jsp").forward(request, response);
		}
	}
	
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		
		dto = new HolidayDTO();
    	
		dto.setYyyy(Integer.parseInt(request.getParameter("yyyy")));
		
		SimpleDateFormat utilDate = new SimpleDateFormat("yyyy-MM-dd");
		String day = request.getParameter("holiday");
		try {
			dto.setHoliday(utilDate.parse(day));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dto.setReason(request.getParameter("reason"));
		int result = dao.insert(dto);
		
		if(result >= 1) {	
			request.getRequestDispatcher("holiday-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_holidaynew.jsp").forward(request, response);
		}
	}
	


	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
    	dtoList = dao.List();
    	request.setAttribute("list", dtoList);    	
    	request.getRequestDispatcher("ad_holiday.jsp").forward(request, response);
		
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
