package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.mysql.cj.Session;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import model.LecsjDAO;
import model.LecsjDTO;

/**
 * Servlet implementation class LecsjController
 */ 

@WebServlet({ "/Mylecture-list.do", "/Mylecture-search.do" })

public class LecsjController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LecsjController() {
		super();
	}

	Calendar cal = Calendar.getInstance();
	HttpSession sesobj = null;

	ArrayList<LecsjDTO> dtoListLecsj = null;
	LecsjDAO daoLecs = new LecsjDAO();
	String info = new String();
	int term = 0;

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		sesobj = request.getSession();
		// grade(request, response);
		System.out.println("process");

		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/');
		String action = uri.substring(lastIndex + 1);

		if (action.equals("Mylecture-list.do"))
			list(request, response);
		else if (action.equals("Mylecture-search.do"))
			search(request, response);
		else
			;
	}

	// 최초 학생수강정보 조회 불러오기 시작
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		term = (cal.get(Calendar.MONTH) < 9)?1:2;
		String uid=(String)sesobj.getAttribute("uid");

		info = daoLecs.info(uid, term);
		
		// <!--총점,이름,학번,학과,년도,학년,학기-->
		
		String[] tmp = (info!=null)?info.split("/"):null;
		System.out.println("info : "+info);
		dtoListLecsj = daoLecs.list(tmp[2],Integer.parseInt(tmp[5]) ,term);
		request.setAttribute("info", info);
		request.setAttribute("lecturelist", dtoListLecsj);
		request.getRequestDispatcher("st_lecsj.jsp").forward(request, response);

	}
	
	public void search(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int gread=Integer.parseInt((String)request.getParameter("sel1"));		
		term = Integer.parseInt((String)request.getParameter("sel2"));
		String uid=(String)sesobj.getAttribute("uid");
		
		info = daoLecs.searchInfo(uid, gread,term);
		// <!--총점,이름,학번,학과,년도,학년,학기-->
		String[] tmp = (info!=null)?info.split("/"):null;
		System.out.println("sel1/sel2/uid"+gread+"/"+term+"/"+uid);
		System.out.println(info);
		String[] infoA = info.split("/");
				for(int i=0;i<7;i++){
			System.out.println("Controler"+i+" : "+infoA[i]);
		} 
		dtoListLecsj = daoLecs.list(tmp[2],Integer.parseInt(tmp[5]) ,term);
		request.setAttribute("info", info);
		request.setAttribute("dtoListLecsj", dtoListLecsj);
		request.getRequestDispatcher("st_lecsj.jsp").forward(request, response);

	}

	// 최초 학생수강정보 조회 불러오기 끝
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			process(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		try {
			process(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
