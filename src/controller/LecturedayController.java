package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
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

import model.ControlDAO;
import model.ControlDTO;
import model.DepartDAO;
import model.DepartDTO;
import model.HolidayDAO;
import model.HolidayDTO;
import model.LectureDAO;
import model.LectureDTO;
import model.LecturedayDAO;
import model.LecturedayDTO;
import model.SubjectDAO;
import model.SubjectDTO;
import model.TeacherDAO;
import model.TeacherDTO;
import model.TimeTableDAO;
import model.TimeTableDTO;
import model.StudentDAO;

/**
 * Servlet implementation class LecturedayController
 */
@WebServlet({"/lectureday-insert.do","/lectureday-list.do","/lectureday-delete.do"})
public class LecturedayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LecturedayController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ArrayList<LecturedayDTO> dtoList = null;
    ArrayList<TimeTableDTO> dtoListTimeTable = null;
    ArrayList<HolidayDTO> dtoListHoliday = null;
    ArrayList<LectureDTO> dtoListLecture = null;
    ArrayList<SubjectDTO> dtoListSubject = null;
    ArrayList<DepartDTO> dtoListDepart = null;
    ArrayList<TeacherDTO> dtoListTeacher = null;
    TimeTableDTO dtoTimeTable = null;
    TimeTableDAO daoTimeTable = new TimeTableDAO();
    HolidayDTO dtoHoliday = null;
    HolidayDAO daoHoliday = new HolidayDAO();
    LecturedayDTO dto = null;
    LecturedayDAO dao = new LecturedayDAO();
    LectureDTO dtoLecture = null;
    LectureDAO daoLecture = new LectureDAO();
    SubjectDTO dtoSubject = null;
    SubjectDAO daoSubject = new SubjectDAO();
    DepartDTO dtoDepart = null;
    DepartDAO daoDepart = new DepartDAO();
    TeacherDTO dtoTeacher = null;
    TeacherDAO daoTeacher= new TeacherDAO();
    HttpSession sesobj = null;
    ArrayList<ControlDTO> dtoListControl = null;
    ControlDAO daoControl = new ControlDAO();
    StudentDAO daoStudent = new StudentDAO();

    
    int result = 0;
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		sesobj = request.getSession();
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf('/'); 
		String action = uri.substring(lastIndex + 1); 
		
		if(action.equals("lectureday-insert.do")) {
			insert(request, response);	
		}
		else if(action.equals("lectureday-list.do")) {
			list(request, response);	
		}
		else if(action.equals("lectureday-delete.do")) {
			delete(request, response);	
		}
		else
			;	
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		
		String tmp=request.getParameter("npage");
		int npage = Integer.parseInt(tmp == null?"1":tmp);
		
		int limit=5;
		int start =(npage-1);
		start*=limit;
		String pagination=daoStudent.pagination(npage, daoStudent.rowcount("SELECT  COUNT(*) FROM lectureday"), request.getRequestURI());
		request.setAttribute("pagination", pagination);
		dtoList = dao.List(start,limit);
		request.setAttribute("lectureday", dtoList);
		 dtoListControl = daoControl.List();
	     request.setAttribute("controlList", dtoListControl);
		 request.getRequestDispatcher("te_lecmove.jsp").forward(request, response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
		int id = Integer.parseInt(request.getParameter("id"));
		int result = dao.delete(id); // 질의를 통해 수정된 레코드의 수
    	if(result > 0) {// 삭제 성공 : 영향 받은 row(record)의 수
    		request.setAttribute("id", request.getParameter("id"));
    		request.getRequestDispatcher("student-list.do").forward(request, response);
    	}
    	else
    		response.sendRedirect("fail.jsp"); // 실패
	}
	
	 protected void weekday(byte grade, String _class, int did, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int i=0;
	    	int sId=0;
	    	String date = null, Smon = null, Stue = null, Swed = null, Sthu = null, Sfri = null;
	    	String d = "0000-00-00";
	    	String cls = null;
			
			dto = new LecturedayDTO();
			dtoHoliday = new HolidayDTO();
			dtoTimeTable = new TimeTableDTO();
			dtoLecture = new LectureDTO();
			dtoSubject = new SubjectDTO();
			
			dtoListHoliday = daoHoliday.List();
			dtoListTimeTable = daoTimeTable.Load();
			dtoListLecture = daoLecture.List();
			dtoListSubject = daoSubject.List();
			
			
			String sdate = request.getParameter("dstart");
			Date day = null, dday = null;
			Date mon = null, tue = null, wed = null, thu = null, fri = null;
			Calendar cal = Calendar.getInstance();
			Calendar monCal = Calendar.getInstance();
			Calendar tueCal = Calendar.getInstance();
			Calendar wedCal = Calendar.getInstance();
			Calendar thuCal = Calendar.getInstance();
			Calendar friCal = Calendar.getInstance();
			
			DateFormat utilDate = new SimpleDateFormat("yyyy-MM-dd");
			try {
				day = utilDate.parse(sdate);
				dday = utilDate.parse(d);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			 
		     
			 for(int j=0; j<7; j++) { 
				cal.setTime(day);
				int dayNum = cal.get(Calendar.DAY_OF_WEEK);
			    switch(dayNum){
			        case 1:
			            cal.add(Calendar.DATE, 1);
			            day = cal.getTime();
			            break ;
			        case 2:
			            mon = day;
			            Smon = utilDate.format(mon);
			            cal.add(Calendar.DATE, 1);
			            day = cal.getTime();
			            break ;
			        case 3:
			            tue = day;
			            Stue = utilDate.format(tue);
			            cal.add(Calendar.DATE, 1);
			            day = cal.getTime();
			            break ;
			        case 4:
			            wed = day;
			            Swed = utilDate.format(wed);
			            cal.add(Calendar.DATE, 1);
			            day = cal.getTime();
			            break ;
			        case 5:
			            thu = day;
			            Sthu = utilDate.format(thu);
			            cal.add(Calendar.DATE, 1);
			            day = cal.getTime();
			            break ;
			        case 6:
			            fri = day;
			            Sfri = utilDate.format(fri);
			            cal.add(Calendar.DATE, 1);
			            day = cal.getTime();
			            break ;
			        case 7:
			            cal.add(Calendar.DATE, 1);
			            day = cal.getTime();
			            break ;           
			    }
			 }
			 date = utilDate.format(dtoListHoliday.get(i).getHoliday());
			 
			 
			
			//int result=0;
			for(byte th=1; th<=15; th++) {
				if(i < dtoListHoliday.size()) {
					date = utilDate.format(dtoListHoliday.get(i).getHoliday());
					dday = dtoListHoliday.get(i).getHoliday();
				}
				
				for(int num=0; num < dtoListTimeTable.size(); num++) {
					
					
					int lId = dtoListTimeTable.get(num).getLecture_id();
					for(int s=0; s < dtoListLecture.size(); s++) {
						if(lId == dtoListLecture.get(s).getId()) {
							sId = dtoListLecture.get(s).getSubject_id();
							cls = dtoListLecture.get(s).getLecture_class();
						}
					}
					String weekday = dtoListTimeTable.get(num).getWeekday();
					byte grd = dtoListSubject.get(sId).getGrade();
					int depart = dtoListSubject.get(sId).getDepart_id();
					
					monCal.setTime(mon);
					tueCal.setTime(tue);
					wedCal.setTime(wed);
					thuCal.setTime(thu);
					friCal.setTime(fri);
					
				        	if(!Smon.equals(date) && _class.equals(cls) && grade == grd && weekday.equals("1") && did == depart) {
								dto.setLecture_id(dtoListTimeTable.get(num).getLecture_id());
								dto.setRoom_id(dtoListTimeTable.get(num).getRoom_id());
								dto.setNormstart(dtoListTimeTable.get(num).getIstart());
								dto.setNormhour(dtoListTimeTable.get(num).getIhour());
								dto.setNormdate(mon);
								dto.setTh(th);
								dto.setNormstate("정상");
								result = dao.create(dto);
								monCal.add(Calendar.DATE, 7);
								mon = monCal.getTime();
								Smon = utilDate.format(mon);
							}
				        	else if(!Stue.equals(date) && _class.equals(cls) && grade == grd && weekday.equals("2") && did == depart) {
								dto.setLecture_id(dtoListTimeTable.get(num).getLecture_id());
								dto.setRoom_id(dtoListTimeTable.get(num).getRoom_id());
								dto.setNormstart(dtoListTimeTable.get(num).getIstart());
								dto.setNormhour(dtoListTimeTable.get(num).getIhour());
								dto.setNormdate(tue);
								dto.setTh(th);
								dto.setNormstate("정상");
								result = dao.create(dto);
								tueCal.add(Calendar.DATE, 7);
								tue = tueCal.getTime();
								Stue = utilDate.format(tue);
							}
				        	else if(!Swed.equals(date) && _class.equals(cls) && grade == grd && weekday.equals("3") && did == depart) {
								dto.setLecture_id(dtoListTimeTable.get(num).getLecture_id());
								dto.setRoom_id(dtoListTimeTable.get(num).getRoom_id());
								dto.setNormstart(dtoListTimeTable.get(num).getIstart());
								dto.setNormhour(dtoListTimeTable.get(num).getIhour());
								dto.setNormdate(wed);
								dto.setTh(th);
								dto.setNormstate("정상");
								result = dao.create(dto);
								wedCal.add(Calendar.DATE, 7);
								wed = wedCal.getTime();
								Swed = utilDate.format(wed);
				        	}
				        	else if(!Sthu.equals(date) && _class.equals(cls) && grade == grd && weekday.equals("4") && did == depart) {
								dto.setLecture_id(dtoListTimeTable.get(num).getLecture_id());
								dto.setRoom_id(dtoListTimeTable.get(num).getRoom_id());
								dto.setNormstart(dtoListTimeTable.get(num).getIstart());
								dto.setNormhour(dtoListTimeTable.get(num).getIhour());
								dto.setNormdate(thu);
								dto.setTh(th);
								dto.setNormstate("정상");
								result = dao.create(dto);
								thuCal.add(Calendar.DATE, 7);
								thu = thuCal.getTime();
								Sthu = utilDate.format(thu);
							}
				        	else if(!Sfri.equals(date) && _class.equals(cls) && grade == grd && weekday.equals("5") && did == depart) {
								dto.setLecture_id(dtoListTimeTable.get(num).getLecture_id());
								dto.setRoom_id(dtoListTimeTable.get(num).getRoom_id());
								dto.setNormstart(dtoListTimeTable.get(num).getIstart());
								dto.setNormhour(dtoListTimeTable.get(num).getIhour());
								dto.setNormdate(fri);
								dto.setTh(th);
								dto.setNormstate("정상");
								result = dao.create(dto);
								friCal.add(Calendar.DATE, 7);
								fri = friCal.getTime();
								Sfri = utilDate.format(fri);
							}
							else if(_class.equals(cls) && grade == grd && did == depart) {
								dto.setLecture_id(dtoListTimeTable.get(num).getLecture_id());
								dto.setRoom_id(dtoListTimeTable.get(num).getRoom_id());
								dto.setNormstart(dtoListTimeTable.get(num).getIstart());
								dto.setNormhour(dtoListTimeTable.get(num).getIhour());
								dto.setNormdate(dday);
								dto.setTh(th);
								dto.setNormstate("휴강");
								result = dao.create(dto);
								if(Smon.equals(date)) {
									monCal.add(Calendar.DATE, 7);
									mon = monCal.getTime();
									Smon = utilDate.format(mon);
								}
								else if(Stue.equals(date)) {
									tueCal.add(Calendar.DATE, 7);
									tue = tueCal.getTime();
									Stue = utilDate.format(tue);
								}
								else if(Swed.equals(date)) {
									wedCal.add(Calendar.DATE, 7);
									wed = wedCal.getTime();
									Swed = utilDate.format(wed);
								}
								else if(Sthu.equals(date)) {
									thuCal.add(Calendar.DATE, 7);
									thu = thuCal.getTime();
									Sthu = utilDate.format(thu);
								}
								else if(Sfri.equals(date)) {
									friCal.add(Calendar.DATE, 7);
									fri = friCal.getTime();
									Sfri = utilDate.format(fri);
								}
								i++;
							}
							
					
				}
				
			}	
			
			
	    }
	
    private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{	
		int did = Integer.parseInt(request.getParameter("depart"));
		weekday((byte) 1, "A", did, request, response);
		weekday((byte) 1, "B", did, request, response);
		
		weekday((byte) 2, "A", did, request, response);
		weekday((byte) 2, "B", did, request, response);
		
		weekday((byte) 3, "A", did, request, response);
		weekday((byte) 3, "B", did, request, response);
	//	for(int i=274; i<=454; i++)dao.delete(i);
		if(result >= 1) {	
			request.getRequestDispatcher("control-list.do").forward(request, response);
		}else {
			request.getRequestDispatcher("ad_holidaynew.jsp").forward(request, response);
		}
		
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
