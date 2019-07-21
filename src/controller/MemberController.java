package controller;

import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberDTO;
//import service.Pagination;
/**
 * Servlet implementation class MemberController
 */
@WebServlet({"/member-login.do","/member-register.do"})
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
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
	
	/*
	private void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String email = (String) sesobj.getAttribute("email");
		
		member = dao.selectRow(email);
		
		if(member!= null) {	
			request.setAttribute("name", member.getName());
			request.setAttribute("phone", member.getPhone());
			request.getRequestDispatcher("customer-update.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("customer-fail.jsp").forward(request, response);
		}
	}
	
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int result = dao.delete(request.getParameter("email"));
		
		if(result >= 1) {	//ȸ��Ż��(����)����
			response.sendRedirect("member-list.do");
		}else {
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		}
	}	
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			
		member = new MemberDTO();
		
		member.setEmail(request.getParameter("email"));
		member.setPw(request.getParameter("pw"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		
		int result = dao.update(member);
		
		if(result >= 1) {	
			request.setAttribute("name", request.getParameter("email"));
			request.getRequestDispatcher("update-success.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("update-fail.jsp").forward(request, response);
		}
	}
	
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		member = new MemberDTO();
    	
		member.setEmail(request.getParameter("email"));
		member.setPw(request.getParameter("pw"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		
		int result = dao.insert(member);
		
		if(result >= 1) {	
			request.setAttribute("name", request.getParameter("email"));
			request.getRequestDispatcher("register-success.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("register-fail.jsp").forward(request, response);
		}
	}
	
	*/
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		System.out.println("login-2");		
		
		MemberDTO loginmember = new MemberDTO();
		
		loginmember.setId(Integer.parseInt(request.getParameter("login_uid")));
		loginmember.setPwd(request.getParameter("login_password"));
				
		member = dao.loginCheck(loginmember);	
		
		
		if(member!= null) {	
			request.setAttribute("name", member.getName());
			sesobj.setAttribute("uid", member.getId());
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
	/*
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int totalRows = 0;//��ü ��ǰ �� ���ڵ�  or ���� ��
		totalRows = dao.selectCount();
		int rowsPerPage = 3; //�� �������� ��Ÿ���� ��ǰ �� 
		int paginationPerPage= 2;//�� �������� ��Ÿ���� ������ ��ȣ ��
		int pageNum = 1;
		if(request.getParameter("pageNum")!=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum")); //��û�� ������
			if(pageNum < 0)
				pageNum = 1;
		}
		//�ݵ�� �Ѱ���� �� �ƴϸ� ���� ������ �� ��
		
		pn.processPaging(totalRows, pageNum, rowsPerPage, paginationPerPage);
		
		if((alMember = dao.selectListBetween(pn.getStartRow(), pn.getEndRow())) != null) {
			request.setAttribute("list", alMember);
			request.setAttribute("pagination", pn);
			request.getRequestDispatcher("customer-list.jsp").forward(request, response);
			//���ܹ߻��ϸ� ȣ���� ������ �ѱ�, �ֻ������� �ѱ�� �ű⼭ �޽��� ó����
		}else {
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		}
	}
	
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
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
