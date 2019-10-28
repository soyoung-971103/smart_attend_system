	package controller;
	
	import java.io.IOException;
	import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
	import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
	
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

import model.StudentLectureInfoDTO;
import model.TeacherLectureDAO;
	import model.TeacherLectureDTO;
	/*
	 * 	<div class="col-12 col-lg-6" align="left">
			<div class="card border-dark mb-3" style="max-width: 20rem;">
	<div class="card-header bg-info text-white"  style="padding:10px">
		<h5 class="card-title" style="margin:0px">PHP</h5>
	</div>
	<div class="card-body" style="padding:10px">
		교수님1<br>
		컴퓨터소프트웨어학과	: 컴퓨터2실(인301)<br>
		3교시~4교시(11:00 ~ 12:50)<br>
		수강생 35명<br>
		<center><a href="te_leccall.jsp" class="btn btn-sm btn-primary mymargin5"> 강의전 </a></center>
	</div>
			</div>
		</div>
	 */
	/**
	 * Servlet implementation class TeacherLectureController
	 */
	@WebServlet({"/teacher-lecture-search.do", "/teacher-lecture-info.do", "/student-lecture-hcheck.do", "/student-lecture-ahcheck.do", "/week-check.do"})
	public class TeacherLectureController extends HttpServlet {
		private static final long serialVersionUID = 1L;
	    HttpSession sesobj = null;
	    
	    
		protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			request.setCharacterEncoding("UTF-8");		
			sesobj = request.getSession();
			
			String uri = request.getRequestURI();
			int lastIndex = uri.lastIndexOf('/'); 
			String action = uri.substring(lastIndex + 1); 
			
			if(action.equals("teacher-lecture-search.do"))
				search(request, response);
			else if(action.equals("teacher-lecture-info.do"))
				info(request, response);
			else if(action.equals("student-lecture-hcheck.do"))
				checkQu(request, response);
			else if(action.equals("student-lecture-ahcheck.do"))
				allCheckQu(request, response);
			else if(action.equals("week-check.do"));
			
						
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
		//해당 과목 학생 전체 출석
		public void allCheckQu(HttpServletRequest request, HttpServletResponse response) throws Exception {
			TeacherLectureDAO lectureDAO = new TeacherLectureDAO();
			double dId = Double.parseDouble(request.getParameter("id"));
			String test = Double.toString(dId);
			String text = "";
			String id = test.substring(0,test.indexOf("."));
			String day = "";
			
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				String dat = test.substring(test.indexOf(".") + 1, test.length());
				if(dat.length() < 8) dat += "0";
				Date date = sdf.parse(dat);
				text=sdf2.format(date);
				day = getDateDay(text,"yyyy-MM-dd");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lectureDAO.stuAllCheck(text, id);
			info(request,response);
		}
		//te_lec.jsp 과목
		public void search(HttpServletRequest request, HttpServletResponse response) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date curToday = new Date();
				
				String text = request.getParameter("text1");
				if(text == null) text=sdf.format(curToday);
				StringBuffer re = new StringBuffer("");
				TeacherLectureDAO lectureDAO = new TeacherLectureDAO();
				ArrayList<TeacherLectureDTO> list = lectureDAO.search(text, request, response);
				
				for(int i = 0; i < list.size(); i++)
				{
					String[] str = list.get(i).getNormdate().split("-");
					TeacherLectureDTO tdto = list.get(i);
					int cnt = lectureDAO.countStudent(tdto.getLecture_id().getId());
					if(list.get(i).getNormstate().equals("정상")) {
						re.append("<div class='col-12 col-lg-6' align='left'>");
						re.append("<div class='card border-dark mb-3' style='max-width: 20rem;'>");
						re.append("<div class='card-header bg-info text-white'  style='padding:10px'>");
						re.append("<h5 class='card-title' style='margin:0px'>"+tdto.getSubject_name()+"</h5>");
						re.append("</div>");
						re.append("<div class='card-body' style='padding:10px' id='t'>");
						re.append(tdto.getTeacher_id().getName() + "<br>");
						re.append(tdto.getTeacher_id().getDepart_id().getName() + "	: "+tdto.getRoomName()+
					"("+tdto.getBuildName().charAt(0)+tdto.getHo()+")<br>");
						
						re.append((tdto.getNormstart()) + "교시~"+(tdto.getNormstart() + tdto.getNormhour() - 1)+
					"교시("+(tdto.getNormstart() + 8)+":00 ~ "+ (tdto.getNormstart() + tdto.getNormhour() + 7) +":50)<br>");
						
						re.append("수강생 "+ cnt +"명<br>");
						re.append("<center>");
						re.append("<a href='te_leccall.jsp?id="+tdto.getId()+'.'+str[0].toString()+str[1].toString()+str[2].toString()+"'class='btn btn-sm btn-primary mymargin5'> "+(tdto.getClassification() == 1 ? "강의 완료" : "강의전")+" </a>");
						re.append("</center>");
						re.append("</div></div></div>");
					}
					else if(tdto.getNormstate().equals("휴강") && tdto.getRestdate().equals(text))
					{
						re.append("<div class='col-12 col-lg-6' align='left'>");
						re.append("<div class='card border-dark mb-3' style='max-width: 20rem;'>");
						re.append("<div class='card-header bg-info text-white'  style='padding:10px'>");
						re.append("<h5 class='card-title' style='margin:0px'>"+tdto.getSubject_name()+"</h5>");
						re.append("</div>");
						re.append("<div class='card-body' style='padding:10px' id='t'>");
						re.append(tdto.getTeacher_id().getName() + "<br>");
						re.append(tdto.getTeacher_id().getDepart_id().getName() + "	: "+tdto.getRoomName()+
					"("+tdto.getBuildName().charAt(0)+tdto.getHo()+")<br>");
						
						re.append((tdto.getReststart()) + "교시~"+(tdto.getReststart() + tdto.getResthour() - 1)+
					"교시("+(tdto.getReststart() + 8)+":00 ~ "+ (tdto.getReststart() + tdto.getResthour() + 7) +":50)<br>");
						
						re.append("수강생 "+ cnt +"명<br>");
						re.append("<center>");
						re.append("<a href='te_leccall.jsp?id="+tdto.getId()+'.'+str[0].toString()+str[1].toString()+str[2].toString()+"'class='btn btn-sm btn-primary mymargin5'> "+(tdto.getClassification() == 1 ? "강의 완료" : "강의전")+" </a>");
						re.append("</center>");
						re.append("</div></div></div>");
					}
					
				}
				response.getWriter().write(re.toString());
			} catch (IOException e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//해당 과목 학생 리스트
		public void info(HttpServletRequest request, HttpServletResponse response) throws Exception 
		{//
			String test = request.getParameter("id");
			
			String text = "";
			String id = test.substring(0,test.indexOf("."));
			String day = "";
			int [] nStuCheck = new int[3];
			
			try {
				String dat = test.substring(test.indexOf(".") + 1, test.length());
				
				if(dat.length() <= 7) dat += "0";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(dat);
				text=sdf2.format(date);
				day = getDateDay(text,"yyyy-MM-dd");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			StringBuffer re = new StringBuffer("");
			TeacherLectureDAO lectureDAO = new TeacherLectureDAO();
			TeacherLectureDTO info = lectureDAO.lectureInfo(text, id, request, response);
			ArrayList<StudentLectureInfoDTO> list = lectureDAO.stuList(text, id, nStuCheck);

			re.append("<div class='card-header mycolor3' style='padding:10px'>" + 
			"<div class='row'>" + 
			"<div class='col' align='left'>" + 
			"<h3>"+info.getSubject_name()+"</h3>" + 
			"</div>" + 
			"<div class='col' align='right'>" + 
			"<h3>"+info.getTeacher_id().getName()+"</h3>" + 
			"</div>" + 
			"</div>" + 
			"</div>" + 
			"<div class='card-body' style='padding:10px 5px 0px 5px'>" + 
			"<form name='form1' method='post' action=''>" + 
			"<div class='row'>" + 
			"<div class='col-auto' align='left' style='margin:0px 0px 3px 8px'>" + 
			"<i class='fa fa-calendar-o fa-1x'></i> "+text+" ("+day+") <br>" + 
			"<i class='fa fa-clock-o fa-1x'></i> "+(info.getNormstart())+"교시~"+(info.getNormhour())+"교시 ("+(info.getNormstart() + 8) + ":00 ~"+(info.getNormstart() + info.getNormhour() + 7)+":50)<br>" + 
			"<i class='fa fa-building-o fa-1x'></i> "+info.getBuildName()+" "+info.getRoomName()+"("+info.getBuildName().substring(0,1)+info.getHo()+")<br>" + 
			"</div>" + 
			"<div id='nStuCheck' class='col' align='left' style='margin:0px 0px 3px 8px'>" + 
			"<i class='fa fa-circle-o fa-1x'></i> 출석 : "+nStuCheck[0]+"<br>" + 
			"<i class='text-primary fa fa-times-circle-o fa-1x'></i> 지각 : "+nStuCheck[1]+"<br>" + 
			"<i class='text-danger fa fa-close fa-1x'></i> 결석 : "+nStuCheck[2]+ 
			"</div>" + 
			"</div>" + 
			"<div class='row'>" + 
			"<div class='col' align='right' style='margin:10px 0px 3px 0px'>" + 
			"<a href='naver.com' onclick='return confirm(\"완료하시겠습니까?\")' class='btn btn-xs btn-primary'>출석시작->완료</a> " + 
			"<a href='javascript:n()' onclick='return confirm(\"전체출석 하시겠습니까?\")' class='btn btn-xs btn-primary'>전체출석</a> " + 
			"<a href='te_lec.jsp' class='btn btn-xs btn-primary'>목록</a><br>" + 
			"</div>" + 
			"</div>" + 
			"<table class='table table-bordered mytable-centermiddle' style='width:100%;'>" + 
			"<tr class='mycolor1'>" + 
			"<td style='vertical-align:middle'>학생정보</td>");
			
			for(int i = info.getNormstart(); i < (info.getNormstart()+info.getNormhour()); i++)
				re.append("<td style='line-height:1.0rem'>"+i+"<br><font size='1'>&nbsp;"+(i + 8)+":00~"+(i + 8)+":50</font>&nbsp;</td>");
			re.append("</tr>");
			
			for(int i = 0; i < list.size(); i++)
			{
				re.append("<tr>" + 
						"<td style='padding:0px;'>" + 
						"<table>" + 
						"<tr>" + 
						"<td align='left' style='border:0;padding-bottom:0px' rowspan='2'>" + 
						"<img class='img-fluid mx-auto' src='pic\\st\\hgd.bmp' width='45' height='60' border='1'>" + 
						"</td>" + 
						"<td align='left' style='border:0;padding:5px 0px 0px 0px;'>" + 
						"<font size='3'><b>"+list.get(i).getStu().getName()+"</b></font>" + 
						"</td>" + 
						"</tr>" + 
						"<tr>" + 
						"<td align='left' style='border:0;padding:0px;line-height:1.0rem'>" + 
						"<font size='1'>"+list.get(i).getStu().getSchoolno()+"<br>"+list.get(i).getStu().getState()+"</font>" + 
						"</td>" + 
						"</tr>" + 
						"<tr>" + 
						"<td align='left' style='border:0;vertical-align:top;padding:0px 5px 0px 5px' colspan='2'>" + 
						"<font size='1'>"+list.get(i).getDepart()+"</font>" + 
						"</td></tr></table></td>"); 
				for(int j = 0; j < list.get(i).getH().size();j++) {
					String str = null;
					if(list.get(i).getH().get(j) == null)
						str = "fa fa-question fa-2x";
					else if(list.get(i).getH().get(j).equals("0"))
						str = "fa fa-circle-o fa-2x";
					else if(list.get(i).getH().get(j).equals("1"))
						str = "text-primary fa fa-times-circle-o fa-2x";
					else if(list.get(i).getH().get(j).equals("2"))
						str = "text-danger fa fa-close fa-2x";
					
					re.append("<td id='"+list.get(i).getStu().getId()+"^"+(j+1)+"' style='line-height:1.3rem'><i class='"+str+"' onchange='j("+list.get(i).getStu().getId()+");' onclick='choose1("+list.get(i).getStu().getId()+","+(j+1)+");' style='cursor:pointer'></i></td>");
				}
			}
			re.append("</tr></table>" + 
			"</form>" + 
			"</div>");
			response.getWriter().write(re.toString());
		}
		
		//학생 출석 직접 클릭했을 때
		public void checkQu(HttpServletRequest request, HttpServletResponse response) throws Exception 
		{
			TeacherLectureDAO dao = new TeacherLectureDAO();
			
			try {
				int [] nStuCheck = new int[3];
				StringBuffer re = new StringBuffer("");
				double dId = Double.parseDouble(request.getParameter("id"));
				String test = Double.toString(dId);
				String text = "";
				String id = test.substring(0,test.indexOf("."));
				String rowno = request.getParameter("rowno");
				String colno = request.getParameter("colno");
				String v = request.getParameter("v");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				
				String dat = test.substring(test.indexOf(".") + 1, test.length());
				if(dat.length() <= 7) dat += "0";
				
				
				Date date = sdf.parse(dat);
				text=sdf2.format(date);
				dao.stuCheck(text, id, rowno, colno, v);
				dao.stuList(text, id, nStuCheck);
				re.append("<i class='fa fa-circle-o fa-1x'></i> 출석 : "+nStuCheck[0]+"<br>" + 
						"<i class='text-primary fa fa-times-circle-o fa-1x'></i> 지각 : "+nStuCheck[1]+"<br>" + 
						"<i class='text-danger fa fa-close fa-1x'></i> 결석 : "+nStuCheck[2]);
				response.getWriter().write(re.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public String getDateDay(String date, String dateType) throws Exception {
		    String day = "" ;
		    SimpleDateFormat dateFormat = new SimpleDateFormat(dateType) ;
		    Date nDate = dateFormat.parse(date) ;
		    Calendar cal = Calendar.getInstance() ;
		    cal.setTime(nDate);
		    int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
		    switch(dayNum){
		        case 1:
		            day = "일";
		            break ;
		        case 2:
		            day = "월";
		            break ;
		        case 3:
		            day = "화";
		            break ;
		        case 4:
		            day = "수";
		            break ;
		        case 5:
		            day = "목";
		            break ;
		        case 6:
		            day = "금";
		            break ;
		        case 7:
		            day = "토";
		            break ; 
		    }
		    return day ;
		}
	}