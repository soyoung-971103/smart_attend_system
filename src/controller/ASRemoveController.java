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

import model.ASRemoveDAO;
import model.ASRemoveDTO;
import model.ControlDAO;
import model.ControlDTO;

/**
 * Servlet implementation class ASRemoveController
 */
@WebServlet({"/as-lecmove-list.do"})
public class ASRemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesobj = null;
    ASRemoveDAO dao = new ASRemoveDAO();
    ArrayList<ASRemoveDTO> dtolist = null;
    ArrayList<ControlDTO> dtoListControl = null;
    ControlDAO daoControl = new ControlDAO();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ASRemoveController() {
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
		
		if(action.equals("as-lecmove-list.do")) {
			list(request, response);
		}
		
	}
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		if(request.getParameter("no") != null && request.getParameter("c") != null)
		{
			if(request.getParameter("c").equals("1"))
			{
				dao.departapp(request,response);
			}
			else if(request.getParameter("c").equals("2"))
			{
				dao.returnlec(request, response);
			}
		}
		
		dtolist = dao.DTOlist(request, response);
		request.setAttribute("dtolist", dtolist);
		
		dtoListControl = daoControl.List();
    	request.setAttribute("controlList", dtoListControl);
		RequestDispatcher dis = request.getRequestDispatcher("as_lecmove.jsp"); 
		dis.forward(request, response);
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
