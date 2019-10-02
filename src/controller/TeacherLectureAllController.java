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

import model.ControlDAO;
import model.ControlDTO;
import model.DepartDAO;
import model.DepartDTO;
import model.SubjectDTO;
import model.TeacherLectureAllDAO;
import model.TeacherLectureAllDTO;
import model.TeacherLectureDTO;

/**
 * Servlet implementation class TeacherLectureAllController
 */
@WebServlet({"/te-lecall.do","/te-lec-select.do", "/te-lec-stu.do", "/te-lec-stu-list.do", "/te-lec-stu-check.do"})
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
    
    ArrayList<ControlDTO> dtoListControl = null;
	ControlDAO daoControl = new ControlDAO();
    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("te-lecall.do"))
			telecall(request, response);
		else if(action.equals("te-lec-select.do"))
			telecSelect(request,response);
		else if(action.equals("te-lec-stu.do"))
			stuList(request,response);
		else if(action.equals("te-lec-stu-list.do"))
			stuList2(request,response);
		else if(action.contentEquals("te-lec-stu-check.do"))
			stuCheck(request,response);
			
					
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
	
	private void stuCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		TeacherLectureAllDAO dao = new TeacherLectureAllDAO();
		
		String [] str = request.getParameter("str").split("-");

		dao.stuCheck(str[str.length-1], str[str.length-2], request.getParameter("rowno"), request.getParameter("colno"), request.getParameter("v"));
		stuList2(request, response);
	}
	
	//telecall -> te_lecall.jsp main화면
	private void telecall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		DepartDTO dto = new DepartDTO();
		DepartDAO dao = new DepartDAO();
		dto.setId(1);
		dto = dao.selectOne(dto);
		char [] str = new char[dto.getClassnum()];
		
		for(int i = 0; i < dto.getClassnum(); i++)
			str[i] = (char)(i + 65);
	
		dtoListControl = daoControl.List();
		
		request.setAttribute("departInfo", dto);
		request.setAttribute("classNum", str);
		request.setAttribute("controlList", dtoListControl);
		RequestDispatcher dis = request.getRequestDispatcher("te_lecall.jsp");
		
		dis.forward(request, response);
	}
	//telecSelect -> te_lecall.jsp 셀렉트 박스 과목 나타냄
	private void telecSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{

		String j = request.getParameter("str");
		String[] values = j.split("-");
		if(values[0].length() <= 3) values[0] += "0";
		
		StringBuffer re = new StringBuffer("");
		
		TeacherLectureAllDAO dao = new TeacherLectureAllDAO();
		ArrayList<SubjectDTO> strList = dao.selectList(values);
		
		re.append("<select name='sel5' class='form-control form-control-sm' >");
		for(int i = 0 ; i < strList.size(); i++) {
			re.append("<option value='"+strList.get(i).getId()+"'>"+strList.get(i).getName()+"</option>");
		}
		re.append("</select>");
		
		response.getWriter().write(re.toString());
	}
	
	

	private void stuList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		TeacherLectureAllDAO dao = new TeacherLectureAllDAO();
		ArrayList<TeacherLectureDTO> dto = null;

		StringBuffer re = new StringBuffer("");
		
		String[] values = request.getParameter("str").split("-");
		System.out.println(values[0] + " " + values[1] + " " + values[2] + " " + values[3]);
		if(values[0].length() <= 3) values[0] += "0";
		dto = dao.lecInfo(values[values.length-1]);
		
		if(dto.size() >= 1) {
			re.append("<tr class='mycolor1'><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>주</th>");
			
			for(int i = 0; i < dto.size(); i++)
				re.append("<th colspan='"+dto.get(i).getNormhour()+"'>"+(i+1)+"</th>");
			
			re.append("</tr><tr class='mycolor1' style='line-height:0.7rem'><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th style='vertical-align:middle'>월일</th>");
	
			for(int i = 0; i < dto.size(); i++) {
				String [] values1 = dto.get(i).getNormdate().split("-");
				re.append("<th colspan='"+dto.get(i).getNormhour()+"'>"+Integer.parseInt(values1[1])+"<br>"+values1[2]+"</th>");
			}
			
			re.append("</tr><tr class='mycolor1' style='line-height:0.7rem;'><th>&nbsp;</th><th></th><th></th><th></th><th></th><th></th><th></th><th style='vertical-align:middle'>보강</th>");
			
			for(int i = 0; i < dto.size(); i++)
			{
				if(dto.get(i).getNormstate() == 4); // 보강일 때
				
				else
				{
					for(int j = 0; j < dto.get(i).getNormhour(); j++)
						re.append("<th></th>");
				}
			}
			re.append("</tr><tr class='mycolor1'><th>학과</th><th>학년</th><th>학번</th><th>학적</th><th>이름</th><th>지각</th><th>결석</th><th>점수</th>");
			
			for(int i = 0; i < dto.size(); i++)
			{
				for(int j = 0; j < dto.get(i).getNormhour(); j++)
					re.append("<th>"+ (dto.get(i).getNormstart() + j + 1 - 9) +"</th>");
			}
			re.append("</tr>");
		}
		response.getWriter().write(re.toString());
	}
	
	private void stuList2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		ArrayList<TeacherLectureAllDTO> dtoList = null;
		TeacherLectureAllDAO dao = new TeacherLectureAllDAO();
		StringBuffer re = new StringBuffer("");
		int [] th_hour = new int[2];
		
		String[] values = request.getParameter("str").split("-");
		if(values[0].length() <= 3) values[0] += "0";
		
		dtoList = dao.stuList(values[values.length-1], values[values.length-2], th_hour);
		
		if(dtoList.size()>=1)
		{
			for(int i = 0; i < dtoList.size(); i++) {
			re.append("<tr><td class='mycolor3'>"+dtoList.get(i).getDepart()+"</td><td class='mycolor3'>"+dtoList.get(i).getStu().getGrade()+"</td><td class='mycolor3'>"+dtoList.get(i).getStu().getSchoolno()+"</td><td class='mycolor3'>"+dtoList.get(i).getStu().getState()+"</td><td class='mycolor3'>"+dtoList.get(i).getStu().getName()+"</td>"+
						"<td class='mycolor4'><font color='blue'><b>"+dtoList.get(i).getIlate()+"</b></font></td><td class='mycolor4'><font color='red'><b>"+dtoList.get(i).getIxhour()+"</b></font></td><td class='mycolor4'><b>"+dtoList.get(i).getIattend()+"</b></td>");
			for(int j = 0; j < th_hour[1] * 15; j++)
			{
				String str = null;
				if(dtoList.get(i).getH().get(j) != null)
				{
					if(dtoList.get(i).getH().get(j).equals("0"))
						str = "fa fa-circle-o fa-1x";
					else if(dtoList.get(i).getH().get(j).equals("1"))
						str = "text-primary fa fa-times-circle-o fa-1x";
					else if(dtoList.get(i).getH().get(j).equals("2"))
						str = "text-danger fa fa-close fa-1x";
					
					re.append("<td id='"+dtoList.get(i).getStu().getId()+"^"+(j+1)+"' style='line-height:1.3rem'><i class='"+str+"' onchange='j("+dtoList.get(i).getStu().getId()+");' onclick='choose1("+dtoList.get(i).getStu().getId()+","+(j+1)+");' style='cursor:pointer'></i></td>");
				}
				else if(dtoList.get(i).getH().get(j) == null)
				{
//					if(dtoList.get(i) == 4)
//						re.append("<td><font color='green'><b>보</b></font></td>");
//					else
						re.append("<td><i class='text-warning fa fa-question fa-1x'></i></td>");
					
				}
			}
			
			re.append("</tr>");
			}
		}
		response.getWriter().write(re.toString());
	}
}
