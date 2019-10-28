package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class MyLectureDAO extends DAOBase{ 
	
	Connection conn = null;  
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<MyLectureDTO> dtoList = null;
	MyLectureDTO dto = null;
	TimeTableDTO dtoTimetable = null;
	RoomDTO dtoRoom = null;
	SubjectDTO dtoSubject = null;
	DepartDTO dtoDepart = null;
	TeacherDTO dtoTeacher = null;
	LectureDTO dtoLecture = null;
	HttpSession sesobj = null;
	
	public ArrayList<MyLectureDTO> Sdetail(HttpSession session){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String year   = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
			String month   = new java.text.SimpleDateFormat("MM").format(new java.util.Date());
			int m = Integer.parseInt(month);
			int hak=0;
			if(m>8)  hak=2;
			else  hak=1;
			dtoList = new ArrayList<MyLectureDTO>();
			rs = stmt.executeQuery("SELECT mylecture.*, timetable.weekday, timetable.istart, timetable.ihour, timetable.room_id, room.id, room.name, lecture.class, subject.grade, subject.ihour, subject.name, subject.depart_id, subject.yyyy, subject.term, teacher.id, teacher.name FROM mylecture INNER JOIN timetable ON mylecture.lecture_id=timetable.lecture_id LEFT JOIN room ON timetable.room_id = room.id LEFT JOIN lecture ON timetable.lecture_id = lecture.id LEFT JOIN subject ON subject.id = lecture.subject_id LEFT JOIN teacher ON teacher.id = lecture.teacher_id "
					+ "where student_id="+session.getAttribute("id")+" and subject.yyyy="+year+" and subject.term="+hak);
			while(rs.next()) {
				dto = new MyLectureDTO();
				dtoRoom = new RoomDTO();
				dtoLecture = new LectureDTO();
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dtoTimetable = new TimeTableDTO();
				dto.setId(rs.getInt(1));
				dto.setLecture_id(rs.getInt(2));
				dto.setTimetable(dtoTimetable);
				dtoTimetable.setWeekday(rs.getString(87));
				dtoTimetable.setIstart(rs.getByte(88));
				dtoTimetable.setIhour(rs.getByte(89));
				dtoTimetable.setRoom_id(rs.getInt(90));
				dtoRoom.setId(rs.getInt(91));
				dtoRoom.setName(rs.getString(92));
				dto.setRoom(dtoRoom);
				dtoLecture.set_class(rs.getString(93));
				dto.setLecture(dtoLecture);
				dtoSubject.setGrade(rs.getByte(94));
				dtoSubject.setIhour(rs.getByte(95));
				dtoSubject.setName(rs.getString(96));
				dtoSubject.setDepart_id(rs.getInt(97));
				dtoLecture.setSubject(dtoSubject);
				dtoTeacher.setId(rs.getInt(98));
				dtoTeacher.setName(rs.getString(99));
				dtoLecture.setTeacher(dtoTeacher);
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}
	public MyLectureDTO qainfo(int id) {
		dto = new MyLectureDTO();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from mylecture where id='"+id+"'");
			if(rs.next())
			{
				dto.setId(rs.getInt("id"));
				dto.setStudent_id(rs.getInt("student_id"));
				dto.setLecture_id(rs.getInt("lecture_id"));
				dto.setQaday(rs.getDate("qaday"));
				dto.setQatitle(rs.getString("qatitle"));
				dto.setQaask(rs.getString("qaask"));
				dto.setQaanswer(rs.getString("qaanswer"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	public ArrayList<MyLectureDTO> findstu(ArrayList<LectureDTO> ldtolist) {
		StudentDTO stdto = null;
		LectureDTO ledto = null;
		SubjectDTO sbdto = null;
		ArrayList<MyLectureDTO> mdtolist = new ArrayList<MyLectureDTO>();
		String where = "";
		for(int i = 0; i <ldtolist.size(); i++) {
			where += "mylecture.lecture_id="+ldtolist.get(i).getId();
			if(i < ldtolist.size() - 1)
				where += " or ";
		}
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from mylecture left join student on student.id = mylecture.student_id "
					+ "left join lecture on lecture.id = mylecture.lecture_id left join subject on subject.id = lecture.subject_id where mylecture.qaday is not null and " + where + " order by mylecture.qaanswer;");
			System.out.println("select * from mylecture left join student on student.id = mylecture.student_id left join lecture on lecture.id = mylecture.lecture_id left join subject on subject.id = lecture.subject_id where mylecture.qaday is not null and " + where + " order by mylecture.qaanswer;");
			while(rs.next())
			{
				dto = new MyLectureDTO();
				stdto = new StudentDTO();
				ledto = new LectureDTO();
				sbdto = new SubjectDTO();
				sbdto.setName(rs.getString("subject.name"));
				
				ledto.setSubject(sbdto);
				stdto.setName(rs.getString("student.name"));
				dto.setId(rs.getInt("mylecture.id"));
				dto.setStudent(stdto);
				dto.setLecture(ledto);
				dto.setQaday(rs.getDate("qaday"));
				dto.setQatitle(rs.getString("qatitle"));
				dto.setQaask(rs.getString("qaask"));
				dto.setQaanswer(rs.getString("qaanswer"));
				mdtolist.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mdtolist;
	}
	public MyLectureDTO teqaansinfo(int id) {
		dto = new MyLectureDTO();
		StudentDTO sdto = new StudentDTO();
		LectureDTO ldto = new LectureDTO();
		SubjectDTO sudto = new SubjectDTO();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select my.id, my.qaday, my.qatitle, my.qaask, my.qaanswer, my.departname, subject.name, stu.grade, stu.class, stu.schoolno, "
					+ "stu.name, stu.phone from mylecture as my left join lecture on lecture.id = my.lecture_id left join subject on subject.id = lecture.subject_id "
					+ "left join student as stu on stu.id = my.student_id where my.id = "+id);
			if(rs.next())
			{
				dto.setId(rs.getInt("my.id"));
				dto.setQaday(rs.getDate("my.qaday"));
				dto.setQatitle(rs.getString("my.qatitle"));
				dto.setQaask(rs.getString("my.qaask"));
				dto.setQaanswer(rs.getString("my.qaanswer"));
				dto.setDepartname(rs.getString("my.departname"));
				sdto.setName(rs.getString("stu.name"));
				sdto.setPhone(rs.getString("stu.phone"));
				sdto.setStudent_class(rs.getString("stu.class"));
				sdto.setSchoolno(rs.getString("stu.schoolno"));
				sdto.setGrade(rs.getByte("stu.grade"));
				dto.setStudent(sdto);
				
				sudto.setName(rs.getString("subject.name"));
				ldto.setSubject(sudto);
				dto.setLecture(ldto);
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	

	/*
	 * 
	 * 
	 */
}