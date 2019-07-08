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

import model.MemberDAO;
import model.MemberDTO;

@WebServlet({"/building-list.do","/building-register.do", "/building-update.do", "/building-delete.do"})
public class BuildingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildingController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ArrayList<MemberDTO> alMember = null;
    MemberDTO member = null;
    HttpSession sesobj = null;
    MemberDAO dao = new MemberDAO();
    //Pagination pn = new Pagination();
    
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();
		
		System.out.println("process");		
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("member-list.do")) {
			//list(request, response);
		}else if(action.equals("member-login.do")) {
			login(request, response);
		}else if(action.equals("member-register.do")) {
			//register(request, response);
		}else if(action.equals("member-update.do")) {
			//update(request, response);
		}else if(action.equals("member-delete.do")) {
			//delete(request, response);		
		}else if(action.equals("member-info.do")) {
			//info(request, response);		
		}
		else
			;
		
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
