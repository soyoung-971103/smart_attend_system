package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StudentDAO extends DAOBase {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StudentDTO dto = null;
	private NoticeDTO dtoNotice = null;
	private QnaDTO dtoQna = null;
	private StudentDTO dtoStudent = null;
	private LectureDTO dtoLecture = null;
	private SubjectDTO dtoSubject = null;
	private TeacherDTO dtoTeacher = null;
	private DepartDTO dtoDepart = null;
	private ArrayList<StudentDTO> dtoList = null;
	private ArrayList<QnaDTO> dtoListQna = null;
	private ArrayList<NoticeDTO> dtoListNotice = null;
	
	
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		int result=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from student where id=?");
			pstmt.setString(1, request.getParameter("id"));
	    	result = pstmt.executeUpdate(); // 질의를 통해 수정된 레코드의 수
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return result;
	}
	
	public StudentDTO detail (int id) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select student.*, depart.abbreviation from student left join depart on student.depart_id = depart.id where student.id=" + id);
			if(rs.next()) {
				dto = new StudentDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setDepart_id(rs.getInt(2));
				dto.setGrade(rs.getByte(3));
				dto.setStudent_class(rs.getString(4));
				dto.setSchoolno(rs.getString(5));
				dto.setName(rs.getString(6));
				dto.setPhone(rs.getString(7));
				dto.setSex(rs.getByte(8));
				dto.setPwd(rs.getString(9));
				dto.setPic(rs.getString(10));
				dto.setState(rs.getString(11));
				dto.setBirthday(rs.getString(12));
				dto.setEmail(rs.getString(13));
				dtoDepart.setName(rs.getString(14));
			}
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
			return dto;
	}
	
	public int register(HttpServletRequest request, HttpServletResponse response) {
		
		int result = 0;
		dto = new StudentDTO();
		dto.setDepart_id(Integer.parseInt(request.getParameter("depart_id")));
		dto.setGrade(Byte.parseByte(request.getParameter("grade")));
		dto.setStudent_class(request.getParameter("student_class"));
		dto.setSchoolno(request.getParameter("schoolno"));
		dto.setName(request.getParameter("name"));
		dto.setPhone(request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3"));
		dto.setSex(Byte.parseByte(request.getParameter("sex")));
		dto.setPwd(request.getParameter("pwd"));
		dto.setState(request.getParameter("state"));
		dto.setBirthday(request.getParameter("birthday1")+"-"+request.getParameter("birthday2")+"-"+request.getParameter("birthday3"));
		dto.setEmail(request.getParameter("email"));
		
		dto.setPic((String) request.getAttribute("pic"));
    	
    	String sql = "insert into student(id, depart_id, grade, class, schoolno, name, "
    			+ "phone, sex, pwd, pic, state, birthday, email) " + 
    			"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	
    	try {
			conn = getConnection();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, dto.getId());
	    	pstmt.setInt(2, dto.getDepart_id());
	    	pstmt.setInt(3, dto.getGrade());
	    	pstmt.setString(4, dto.getStudent_class());
	    	pstmt.setString(5, dto.getSchoolno());
	    	pstmt.setString(6, dto.getName());
	    	pstmt.setString(7, dto.getPhone());
	    	pstmt.setByte(8, dto.getSex());
	    	pstmt.setString(9, dto.getPwd());
	    	pstmt.setString(10, dto.getPic());
	    	pstmt.setString(11, dto.getState());
	    	pstmt.setString(12, dto.getBirthday());
	    	pstmt.setString(13, dto.getEmail());
	    	
	    	result = pstmt.executeUpdate();
	    	
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
    	return result;
	}

	public int update(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		dto = new StudentDTO();
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setDepart_id(Integer.parseInt(request.getParameter("depart_id")));
		dto.setGrade(Byte.parseByte(request.getParameter("grade")));
		dto.setStudent_class(request.getParameter("student_class"));
		dto.setSchoolno(request.getParameter("schoolno"));
		dto.setName(request.getParameter("name"));
		dto.setPhone(request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3"));
		dto.setSex(Byte.parseByte(request.getParameter("sex")));
		dto.setPwd(request.getParameter("pwd"));
		dto.setState(request.getParameter("state"));
		dto.setBirthday(request.getParameter("birthday1")+"-"+request.getParameter("birthday2")+"-"+request.getParameter("birthday3"));
		dto.setEmail(request.getParameter("email"));
		
		dto.setPic((String) request.getAttribute("pic"));
		
		String sql = "update student set depart_id=?, grade=?, class=?, schoolno=?, name=?, "
				+ "phone=?, sex=?, pwd=?, pic=?, state=?, birthday=?, email=? where id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, dto.getDepart_id());
	    	pstmt.setInt(2, dto.getGrade());
	    	pstmt.setString(3, dto.getStudent_class());
	    	pstmt.setString(4, dto.getSchoolno());
	    	pstmt.setString(5, dto.getName());
	    	pstmt.setString(6, dto.getPhone());
	    	pstmt.setByte(7, dto.getSex());
	    	pstmt.setString(8, dto.getPwd());
	    	pstmt.setString(9, dto.getPic());
	    	pstmt.setString(10, dto.getState());
	    	pstmt.setString(11, dto.getBirthday());
	    	pstmt.setString(12, dto.getEmail());
	    	pstmt.setInt(13, dto.getId());
	    	result = pstmt.executeUpdate(); // 질의를 통해 수정된 레코드의 수
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
			return result;
	}
	
	public ArrayList<StudentDTO> list(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select student.*, depart.abbreviation from student left join depart on student.depart_id = depart.id");
			dtoList = new ArrayList<StudentDTO>();
			while(rs.next()) {
				dto = new StudentDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setDepart_id(rs.getInt(2));
				dto.setGrade(rs.getByte(3));
				dto.setStudent_class(rs.getString(4));
				dto.setSchoolno(rs.getString(5));
				dto.setName(rs.getString(6));
				dto.setPhone(rs.getString(7));
				dto.setSex(rs.getByte(8));
				dto.setPwd(rs.getString(9));
				dto.setPic(rs.getString(10));
				dto.setState(rs.getString(11));
				dto.setBirthday(rs.getString(12));
				dto.setEmail(rs.getString(13));
				dtoDepart.setName(rs.getString(14));
				dto.setDepart(dtoDepart);
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}

	public ArrayList<StudentDTO> search(String text1){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			if (text1 == null) rs=stmt.executeQuery("select * from student");
			else rs=stmt.executeQuery("select student.*, depart.abbreviation from student left join depart on student.depart_id = depart.id where name like '%"+ text1 +"%' order by name");
			// email, pw는 form을 구성하는 각 요소의 이름
			dtoList = new ArrayList<StudentDTO>();
			while(rs.next()) {
				dto = new StudentDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setDepart_id(rs.getInt(2));
				dto.setGrade(rs.getByte(3));
				dto.setStudent_class(rs.getString(4));
				dto.setSchoolno(rs.getString(5));
				dto.setName(rs.getString(6));
				dto.setPhone(rs.getString(7));
				dto.setSex(rs.getByte(8));
				dto.setPwd(rs.getString(9));
				dto.setPic(rs.getString(10));
				dto.setState(rs.getString(11));
				dto.setBirthday(rs.getString(12));
				dto.setEmail(rs.getString(13));
				dtoDepart.setName(rs.getString(14));
				dto.setDepart(dtoDepart);
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;
	}

	public StudentDTO list_id(String uid){
		try {
			conn = getConnection();
			stmt = conn.createStatement();	
			rs = stmt.executeQuery("select student.*, depart.abbreviation from student left join depart on student.depart_id = depart.id where schoolno= " + uid);
			if(rs.next()) {
				dto = new StudentDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setDepart_id(rs.getInt(2));
				dto.setGrade(rs.getByte(3));
				dto.setStudent_class(rs.getString(4));
				dto.setSchoolno(rs.getString(5));
				dto.setName(rs.getString(6));
				dto.setPhone(rs.getString(7));
				dto.setSex(rs.getByte(8));
				dto.setPwd(rs.getString(9));
				dto.setPic(rs.getString(10));
				dto.setState(rs.getString(11));
				dto.setBirthday(rs.getString(12));
				dto.setEmail(rs.getString(13));
				dtoDepart.setName(rs.getString(14));
				dto.setDepart(dtoDepart);
			}
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;	
	}
	public ArrayList<MyLectureDTO> qnalist(HttpServletRequest request, HttpServletResponse response) {
		HttpSession ssion = request.getSession();
		MyLectureDTO mdto = null;
		SubjectDTO sdto = null;
		LectureDTO ldto = null;
		TeacherDTO tdto = null;
		
		ArrayList<MyLectureDTO> dtoList = new ArrayList<MyLectureDTO>();
		try {
			String query = "select mylecture.qaday, subject.name as subject_name, teacher.name as teacher_name, mylecture.qatitle, "
					+ "mylecture.qaanswer, mylecture.id from student left join mylecture on student.id = mylecture.student_id "
					+ "left join lecture on lecture.id = mylecture.lecture_id left join teacher on teacher.id = lecture.teacher_id "
					+ "left join subject on subject.id = lecture.subject_id where student.schoolno='"+ssion.getAttribute("uid")+"'";
			conn = getConnection();
			stmt = conn.createStatement();	
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				mdto = new MyLectureDTO();
				sdto = new SubjectDTO();
				ldto = new LectureDTO();
				tdto = new TeacherDTO();
				sdto.setName(rs.getString("subject_name"));
				ldto.setSubject(sdto);
				mdto.setQaday(rs.getDate("mylecture.qaday"));
				tdto.setName(rs.getString("teacher_name"));
				mdto.setQatitle(rs.getString("mylecture.qatitle"));
				mdto.setQaanswer(rs.getString("mylecture.qaanswer"));
				mdto.setId(rs.getInt("mylecture.id"));
				mdto.setLecture(ldto);
				mdto.setTeacher(tdto);
				dtoList.add(mdto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dtoList;
	}
	public ArrayList<LectureDTO> lecList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession ssion = request.getSession();
		ArrayList<LectureDTO> ldtoList = new ArrayList<LectureDTO>();
		LectureDTO ldto = null;
		SubjectDTO sdto = null;
		TeacherDTO tdto = null;

		try {//			
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select lecture.id, subject.name, teacher.name from student left join mylecture on mylecture.student_id = student.id "
					+ "left join lecture on lecture.id = mylecture.lecture_id left join subject on subject.id = lecture.subject_id "
					+ "left join teacher on teacher.id = lecture.teacher_id where mylecture.qaday not null and student.schoolno = '"+ssion.getAttribute("uid")+"' and subject.yyyy = 2019;");
			while(rs.next()) {
				ldto = new LectureDTO();
				sdto = new SubjectDTO();
				tdto = new TeacherDTO();
				sdto.setName(rs.getString("subject.name"));
				tdto.setName(rs.getString("teacher.name"));
				ldto.setSubject(sdto);
				ldto.setTeacher(tdto);
				ldto.setId(rs.getInt("lecture.id"));
				ldtoList.add(ldto);
			}
			return ldtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ldtoList;
	}//saveqa
	public void saveqa(HttpServletRequest request, HttpServletResponse response) {
		try {//		qaday, qatitle, qaask	
			String qatitle = request.getParameter("qatitle").replace("\\", "\\\\");
			String qaask = request.getParameter("qatxt1").replace("\\", "\\\\");
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate("update mylecture set qaday='"+request.getParameter("qawriteday")+"', qatitle='"+qatitle+"', qaask='"+qaask+"';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<NoticeDTO> NoticeList(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from notice");
			dtoListNotice = new ArrayList<NoticeDTO>();
			while(rs.next()) {
				dtoNotice = new NoticeDTO();
				dtoNotice.setId(rs.getInt(1));
				dtoNotice.setWriteday(rs.getDate(2));
				dtoNotice.setTitle(rs.getString(3));
				dtoNotice.setTxt1(rs.getString(4));
				dtoListNotice.add(dtoNotice);
			}
			return dtoListNotice;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoListNotice;	
	}
	
	public ArrayList<QnaDTO> QnaList(int id){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT qna.*, student.schoolno as student_schoolno, student.grade as student_grade, student.class as student_class, "
					+ "student.name as student_name, student.phone as student_phone, depart.name as depart_name, teacher.name as teacher_name,  "
					+ "subject.name as subject_name FROM qna LEFT JOIN student on qna.student_id = student.id LEFT JOIN depart on student.depart_id =  "
					+ "depart.id LEFT JOIN lecture ON qna.lecture_id = lecture.id LEFT JOIN subject ON lecture.subject_id = subject.id LEFT JOIN teacher  "
					+ "ON qna.teacher_id = teacher.id where student_id="+id);
			dtoListQna = new ArrayList<QnaDTO>();
			while(rs.next()) {				
				dtoQna = new QnaDTO();
				dtoStudent = new StudentDTO();
				dtoLecture = new LectureDTO();
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dtoDepart = new DepartDTO();
				dtoQna.setId(rs.getInt(1));
				dtoQna.setStudent_id(rs.getInt(2));
				dtoQna.setLecture_id(rs.getInt(3));
				dtoQna.setDay(rs.getDate(4));
				dtoQna.setQatitle(rs.getString(5));
				dtoQna.setQaask(rs.getString(6));
				dtoQna.setQaanswer(rs.getString(7));
				dtoQna.setC_confirm(rs.getByte(8));
				dtoQna.setTeacher_id(rs.getInt(9));
				dtoStudent.setSchoolno(rs.getString(10));
				dtoStudent.setGrade(rs.getByte(11));
				dtoStudent.setStudent_class(rs.getString(12));
				dtoStudent.setName(rs.getString(13));
				dtoStudent.setPhone(rs.getString(14));
				dtoDepart.setName(rs.getString(15));
				dtoStudent.setDepart(dtoDepart);
				dtoQna.setStudent(dtoStudent);
				dtoTeacher.setName(rs.getString(16));
				dtoQna.setTeacher(dtoTeacher);
				dtoSubject.setName(rs.getString(17));
				dtoLecture.setSubject(dtoSubject);
				dtoQna.setLecture(dtoLecture);
				dtoListQna.add(dtoQna);
			}
			return dtoListQna;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoListQna;	
	}

	public QnaDTO QnaInfo(int id){
		try {			
			dtoQna = new QnaDTO();
			dtoStudent = new StudentDTO();
			dtoLecture = new LectureDTO();
			dtoSubject = new SubjectDTO();
			dtoTeacher = new TeacherDTO();
			dtoDepart = new DepartDTO();
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT qna.*, student.schoolno as student_schoolno, student.grade as student_grade, student.class as student_class, "
					+ "student.name as student_name, student.phone as student_phone, depart.name as depart_name, teacher.name as teacher_name,  "
					+ "subject.name as subject_name FROM qna LEFT JOIN student on qna.student_id = student.id LEFT JOIN depart on student.depart_id =  "
					+ "depart.id LEFT JOIN lecture ON qna.lecture_id = lecture.id LEFT JOIN subject ON lecture.subject_id = subject.id LEFT JOIN teacher  "
					+ "ON qna.teacher_id = teacher.id where qna.id="+id);
			
			if(rs.next()) {			
				dtoQna.setId(rs.getInt(1));
				dtoQna.setStudent_id(rs.getInt(2));
				dtoQna.setLecture_id(rs.getInt(3));
				dtoQna.setDay(rs.getDate(4));
				dtoQna.setQatitle(rs.getString(5));
				dtoQna.setQaask(rs.getString(6));
				dtoQna.setQaanswer(rs.getString(7));
				dtoQna.setC_confirm(rs.getByte(8));
				dtoQna.setTeacher_id(rs.getInt(9));
				dtoStudent.setSchoolno(rs.getString(10));
				dtoStudent.setGrade(rs.getByte(11));
				dtoStudent.setStudent_class(rs.getString(12));
				dtoStudent.setName(rs.getString(13));
				dtoStudent.setPhone(rs.getString(14));
				dtoDepart.setName(rs.getString(15));
				dtoStudent.setDepart(dtoDepart);
				dtoQna.setStudent(dtoStudent);
				dtoTeacher.setName(rs.getString(16));
				dtoQna.setTeacher(dtoTeacher);
				dtoSubject.setName(rs.getString(17));
				dtoLecture.setSubject(dtoSubject);

				return dtoQna;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoQna;	
	}

}
