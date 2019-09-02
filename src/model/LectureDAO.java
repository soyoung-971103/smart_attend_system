package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class LectureDAO extends DAOBase{
	
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<LectureDTO> dtoList = null;
	ArrayList<MyLectureDTO> mylec_dtoList = null;
	LectureDTO dto = null;
	DepartDTO depart_dto = null;
	SubjectDTO subject_dto = null;
	TeacherDTO teacher_dto = null;
	StudentDTO studentdto = null;
	DepartDTO departdto = null;
	MyLectureDTO mylecdto = null;
	HttpSession sesobj = null;
	
	public ArrayList<LectureDTO> selectAllList(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();			
			dtoList = new ArrayList<LectureDTO>();
			rs = stmt.executeQuery("select depart.name as depart_name, lecture.class as lecture_class, subject.*, teacher.name as teacher_name, lecture.id as lecture_lecture_id from lecture left join subject on lecture.subject_id = subject.id left join teacher on lecture.teacher_id = teacher.id left join depart on subject.depart_id = depart.id"); //where subject.grade = " + student.getGrade());
			while(rs.next()) {
				dto = new LectureDTO();
				depart_dto = new DepartDTO();
				subject_dto = new SubjectDTO();
				teacher_dto = new TeacherDTO();
				
				depart_dto.setName(rs.getString(1));				
				dto.setLecture_class(rs.getString(2));
				depart_dto.setId(rs.getInt(4));
				subject_dto.setDepart(depart_dto);
				
				subject_dto.setCode(rs.getString(5));
				subject_dto.setYyyy(rs.getInt(6));
				subject_dto.setGrade(rs.getByte(7));
				subject_dto.setTerm(rs.getByte(8));
				subject_dto.setIsmajor(rs.getString(9));
				subject_dto.setIschoice(rs.getString(10));
				subject_dto.setIspractice(rs.getString(11));
				subject_dto.setName(rs.getString(12));
				subject_dto.setIpoint(rs.getFloat(13));
				subject_dto.setIhour(rs.getByte(14));
				dto.setSubject(subject_dto);
				
				teacher_dto.setName(rs.getString(15));
				dto.setTeacher(teacher_dto);
				dto.setId(rs.getInt(16));
				
				dtoList.add(dto);				
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}
	
	public int select_id(String hakbun) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id from student where " + 
					"schoolno=" + hakbun);
			if(rs.next()) {
				studentdto = new StudentDTO();
				studentdto.setId(rs.getInt(1));
			}				
			return studentdto.getId();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return studentdto.getId();		
	}

	public int insert(ArrayList<MyLectureDTO> dto) {    	
    	int result = 0;
    	try {
    		for(MyLectureDTO list : dto) {
    			conn = getConnection();
            	pstmt = conn.prepareStatement("insert into mylecture(student_id, lecture_id, grade, term, departname) " +  
        				"values(?, ?, ?, ? ,?)");	
        		
            	pstmt.setInt(1, list.getStudent_id());
            	pstmt.setInt(2, list.getLecture_id());
            	pstmt.setByte(3, list.getGrade());
            	pstmt.setByte(4, list.getTerm());
            	pstmt.setString(5, list.getDepartname());
            	result = pstmt.executeUpdate();	  
    		}  
    		return result;  
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		} 
    	System.out.println("test-insert-fail");
		return result;
    }

	public int delete(int hakbun) {
		int result = 0;
		try {			
			conn = getConnection();
        	pstmt = conn.prepareStatement("delete from mylecture where student_id = ?");	
    		
        	System.out.println(hakbun);
        	pstmt.setInt(1, hakbun);
        	result = pstmt.executeUpdate();
        	 
        	return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return result;		
	}

	public ArrayList<MyLectureDTO> select_mylecture(int id) {
		try {
			mylec_dtoList = new ArrayList<MyLectureDTO>();
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select lecture_id from mylecture where " + 
					"student_id=" + id);
			while(rs.next()) {
				mylecdto = new MyLectureDTO();
				mylecdto.setLecture_id(rs.getInt(1));
				mylec_dtoList.add(mylecdto);
			}				
			return mylec_dtoList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return mylec_dtoList;		
	}

	public ArrayList<MyLectureDTO> selectMyList(int id){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();			
			mylec_dtoList = new ArrayList<MyLectureDTO>();
			rs = stmt.executeQuery("SELECT * FROM mylecture WHERE student_id= " +id);
			while(rs.next()) {
				mylecdto = new MyLectureDTO();
				mylecdto.setId(rs.getInt(1));
				mylecdto.setStudent_id(rs.getInt(2));
				mylecdto.setLecture_id(rs.getInt(3));
				mylecdto.setDepartname(rs.getString(4));
				mylecdto.setGrade(rs.getByte(5));
				mylecdto.setTerm(rs.getByte(6));
				mylecdto.setIattend(rs.getByte(7));
				mylecdto.setImiddle(rs.getByte(8));
				mylecdto.setIlast(rs.getByte(9));
				mylecdto.setInormal(rs.getByte(10));
				mylecdto.setIpractice(rs.getByte(11));
				mylecdto.setItotal(rs.getByte(12));
				mylecdto.setIpoint(rs.getFloat(13));
				mylecdto.setIgrade(rs.getString(14));
				mylecdto.setRetake(rs.getByte(15));
				mylecdto.setIlate(rs.getByte(16));
				mylecdto.setIxhour(rs.getInt(17));
				mylecdto.setQakind(rs.getByte(18));
				mylecdto.setQaday(rs.getDate(19));
				mylecdto.setQatitle(rs.getString(20));
				mylecdto.setQaask(rs.getString(21));
				mylecdto.setQaanswer(rs.getString(22));
				
				mylecdto.hn = new ArrayList<Byte>();
				mylecdto.hn.add((byte) 0);
				for(int i = 23; i<=86; i ++) {
					mylecdto.hn.add(rs.getByte(i));
				}
				
				mylec_dtoList.add(mylecdto);				
			}
			return mylec_dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mylec_dtoList;	
	}	

	public ArrayList<MyLectureDTO> selectSubjectList(String sel1, String sel2, String sel3, String sel4, String sel5){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();			
			mylec_dtoList = new ArrayList<MyLectureDTO>();
			if(sel4.equals("0"))
				rs = stmt.executeQuery("select mylecture.*, student.schoolno as student_schoolno, student.name as student_name, student.state as student_state, student.grade as student_grade, lecture.class as lecture_class, lecture.subject_id as lecture_subject_id, subject.yyyy as subject_yyyy, subject.grade as subject_grade, subject.term as subject_term, subject.name as subject_name, depart.name as depart_name FROM mylecture left join student on mylecture.student_id=student.id LEFT join lecture on mylecture.lecture_id = lecture.id LEFT JOIN subject on lecture.subject_id = subject.id left join depart on student.depart_id = depart.id where subject.yyyy = "+sel1+" and subject.term= "+sel2+" and subject.grade= "+sel3+" and depart.id="+sel5);
			else
				rs = stmt.executeQuery("select mylecture.*, student.schoolno as student_schoolno, student.name as student_name, student.state as student_state, student.grade as student_grade, lecture.class as lecture_class, lecture.subject_id as lecture_subject_id, subject.yyyy as subject_yyyy, subject.grade as subject_grade, subject.term as subject_term, subject.name as subject_name, depart.name as depart_name FROM mylecture left join student on mylecture.student_id=student.id LEFT join lecture on mylecture.lecture_id = lecture.id LEFT JOIN subject on lecture.subject_id = subject.id left join depart on student.depart_id = depart.id where subject.yyyy = "+sel1+" and subject.term= "+sel2+" and subject.grade= "+sel3+" and lecture.class= '"+sel4+"' and depart.id="+sel5);
				
			while(rs.next()) {
				mylecdto = new MyLectureDTO();
				studentdto = new StudentDTO();
				dto = new LectureDTO();
				subject_dto = new SubjectDTO();
				departdto = new DepartDTO();
				mylecdto.setId(rs.getInt(1));
				mylecdto.setStudent_id(rs.getInt(2));
				mylecdto.setLecture_id(rs.getInt(3));
				mylecdto.setDepartname(rs.getString(4));
				mylecdto.setGrade(rs.getByte(5));
				mylecdto.setTerm(rs.getByte(6));
				mylecdto.setIattend(rs.getByte(7));
				mylecdto.setImiddle(rs.getByte(8));
				mylecdto.setIlast(rs.getByte(9));
				mylecdto.setInormal(rs.getByte(10));
				mylecdto.setIpractice(rs.getByte(11));
				mylecdto.setItotal(rs.getByte(12));
				mylecdto.setIpoint(rs.getFloat(13));
				mylecdto.setIgrade(rs.getString(14));
				mylecdto.setRetake(rs.getByte(15));
				mylecdto.setIlate(rs.getByte(16));
				mylecdto.setIxhour(rs.getInt(17));
				mylecdto.setQakind(rs.getByte(18));
				mylecdto.setQaday(rs.getDate(19));
				mylecdto.setQatitle(rs.getString(20));
				mylecdto.setQaask(rs.getString(21));
				mylecdto.setQaanswer(rs.getString(22));
				
				mylecdto.hn = new ArrayList<Byte>();
				mylecdto.hn.add((byte) 0);
				for(int i = 23; i<=86; i ++) {
					mylecdto.hn.add(rs.getByte(i));
				}
				
				studentdto.setSchoolno(rs.getString(87));//음.....
				studentdto.setName(rs.getString(88));
				studentdto.setState(rs.getString(89));
				studentdto.setGrade(rs.getByte(90));
				
				dto.setLecture_class(rs.getString(91));
				dto.setSubject_id(rs.getInt(92));				
				
				subject_dto.setYyyy(rs.getInt(93));
				subject_dto.setGrade(rs.getByte(94));
				subject_dto.setTerm(rs.getByte(95));
				subject_dto.setName(rs.getString(96));
				dto.setSubject(subject_dto);
				mylecdto.setLecture(dto);
				
				departdto.setName(rs.getString(97));
				studentdto.setDepart(departdto);
				
				mylecdto.setStudent(studentdto);
				mylec_dtoList.add(mylecdto);				
			}
			return mylec_dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mylec_dtoList;	
	}	

	
}
