package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ADRemoveDAO;
import model.ADRemoveDTO;

/**
 * Servlet implementation class ADRemoveController
 */
@WebServlet({"/ad-lecmove-list.do"})
public class ADRemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    HttpSession sesobj = null;
    ADRemoveDAO dao = new ADRemoveDAO();
    ArrayList<ADRemoveDTO> dtolist = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADRemoveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		

		sesobj = request.getSession();
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("ad-lecmove-list.do")) {
			list(request, response);
		}
		
	}//
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	
		if(request.getAttribute("dtolist") != null) request.removeAttribute("dtolist");
		
		String minmax = dao.Year(request, response);
		dtolist = dao.DTOlist(request, response);
		
		String year = request.getParameter("sel1");
		String term = request.getParameter("sel2");
		
		if(year != null && term != null) {request.setAttribute("y", year);	request.setAttribute("t", term);}
		
		if(minmax != null) {request.setAttribute("year", minmax);}
		else {System.out.println("minmax error");}
		
		if(dtolist != null) { request.setAttribute("dtolist", dtolist); }
		
		//최종승인 or 반려가 들어왔을 떄
		if(request.getParameter("no") != null && request.getParameter("c") != null)
		{
			//최종승인
			if(request.getParameter("c").equals("1"))
				dao.lastapp(request,response);
			//반려
			else if(request.getParameter("c").equals("2"))
				dao.returnlec(request,response);
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("ad_lecmove.jsp"); 
		dis.forward(request, response);
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
		try {
			process(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
