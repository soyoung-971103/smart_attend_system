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
import model.LecturedayDAO;
import model.LecturedayDTO;
import model.NoticeDAO;
import model.NoticeDTO;

/**
 * Servlet implementation class AssistMainController
 */
@WebServlet("/admain-list.do")
public class AdMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdMainController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ArrayList<LecturedayDTO> dtoList = null;
    ArrayList<NoticeDTO> dtoListNotice = null;
    LecturedayDTO dto = null;
    LecturedayDAO dao = new LecturedayDAO();
    NoticeDTO dtoNotice = null;
    NoticeDAO daoNotice = new NoticeDAO();
    ArrayList<ControlDTO> dtoListControl = null;
	ControlDAO daoControl = new ControlDAO();
    HttpSession sesobj = null;
    String pagination = null;
    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("admain-list.do")) {
			list(request, response);	
		}
		else
			;	
	}
    
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
    	String tmp=request.getParameter("npage");
	    int npage = Integer.parseInt(tmp == null?"1":tmp);
	    int limit=5;
	    int start =(npage-1);
	    start*=limit;
	    pagination = dao.pagination(npage, dao.rowcount("SELECT COUNT(*) FROM notice"), request.getRequestURI());
	    request.setAttribute("pagination", pagination);
	    
    	dtoList = dao.List();
    	dtoListNotice = daoNotice.list(null, start, limit);
    	request.setAttribute("controlList", dtoListControl);
    	request.setAttribute("noticeList", dtoListNotice);
    	request.setAttribute("list", dtoList);    	
    	request.getRequestDispatcher("ad_main.jsp").forward(request, response);
		
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
