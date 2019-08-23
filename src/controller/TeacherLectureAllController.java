package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TeacherLectureAllController
 */
@WebServlet("/te-lecall.do")
public class TeacherLectureAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesobj = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherLectureAllController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("te-lecall.do"))
			telecall(request, response);
					
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		request.setCharacterEncoding("UTF-8");
    		response.setContentType("text/html; charset=utf-8");
    		process(request,response);
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void telecall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		

		//request.setAttribute("alMember", alMember);
		
		RequestDispatcher dis = request.getRequestDispatcher("te_lecall.jsp");
		dis.forward(request, response);
	}

}
