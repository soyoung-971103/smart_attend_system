package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LectureDAO extends DAOBase{
	
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<LectureDTO> dtoList = null;
	ArrayList<MyLectureDTO> dtoListMyLecture = null;
	LectureDTO dto = null;
	DepartDTO dtoDepart = null;
	SubjectDTO dtoSubject = null;
	TeacherDTO dtoTeacher = null;
	StudentDTO dtoStudent = null;
	MyLectureDTO dtoMyLecture = null;
	HttpSession sesobj = null;
	
	public ArrayList<LectureDTO> List(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			dtoList = new ArrayList<LectureDTO>();
			rs = stmt.executeQuery("SELECT lecture.id, lecture.class, subject.grade, subject.ihour, subject.name, subject.depart_id, teacher.id, teacher.name FROM lecture LEFT JOIN subject ON lecture.subject_id = subject.id LEFT JOIN teacher ON lecture.teacher_id = teacher.id");
			while(rs.next()) {
				dto = new LectureDTO(); 
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dto.setId(rs.getInt(1));
				dto.set_class(rs.getString(2));
				dtoSubject.setGrade(rs.getByte(3));
				dtoSubject.setIhour(rs.getByte(4));
				dtoSubject.setName(rs.getString(5));
				dtoSubject.setDepart_id(rs.getInt(6));
				dto.setSubject(dtoSubject);
				dtoTeacher.setId(rs.getInt(7));
				dtoTeacher.setName(rs.getString(8));
				dto.setTeacher(dtoTeacher);
				dtoList.add(dto);}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}
	
	public ArrayList<LectureDTO> selectAllList(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();			
			dtoList = new ArrayList<LectureDTO>();
			rs = stmt.executeQuery("select depart.name as depart_name, lecture.class as lecture_class, subject.*, teacher.name as teacher_name, lecture.id as lecture_lecture_id from lecture left join subject on lecture.subject_id = subject.id left join teacher on lecture.teacher_id = teacher.id left join depart on subject.depart_id = depart.id"); //where subject.grade = " + student.getGrade());
			while(rs.next()) {
				dto = new LectureDTO();
				dtoDepart = new DepartDTO();
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				
				dtoDepart.setName(rs.getString(1));				
				dto.setLecture_class(rs.getString(2));
				dtoDepart.setId(rs.getInt(4));
				dtoSubject.setDepart(dtoDepart);
				
				dtoSubject.setCode(rs.getString(5));
				dtoSubject.setYyyy(rs.getInt(6));
				dtoSubject.setGrade(rs.getByte(7));
				dtoSubject.setTerm(rs.getByte(8));
				dtoSubject.setIsmajor(rs.getString(9));
				dtoSubject.setIschoice(rs.getString(10));
				dtoSubject.setIspractice(rs.getString(11));
				dtoSubject.setName(rs.getString(12));
				dtoSubject.setIpoint(rs.getFloat(13));
				dtoSubject.setIhour(rs.getByte(14));
				dto.setSubject(dtoSubject);
				
				dtoTeacher.setName(rs.getString(15));
				dto.setTeacher(dtoTeacher);
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
				dtoStudent = new StudentDTO();
				dtoStudent.setId(rs.getInt(1));
			}				
			return dtoStudent.getId();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return dtoStudent.getId();		
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
			dtoListMyLecture = new ArrayList<MyLectureDTO>();
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select lecture_id from mylecture where " + 
					"student_id=" + id);
			while(rs.next()) {
				dtoMyLecture = new MyLectureDTO();
				dtoMyLecture.setLecture_id(rs.getInt(1));
				dtoListMyLecture.add(dtoMyLecture);
			}				
			return dtoListMyLecture;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return dtoListMyLecture;		
	}

	public ArrayList<MyLectureDTO> selectMyList(int id){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();			
			dtoListMyLecture = new ArrayList<MyLectureDTO>();
			rs = stmt.executeQuery("SELECT * FROM mylecture WHERE student_id= " +id);
			while(rs.next()) {
				dtoMyLecture = new MyLectureDTO();
				dtoMyLecture.setId(rs.getInt(1));
				dtoMyLecture.setStudent_id(rs.getInt(2));
				dtoMyLecture.setLecture_id(rs.getInt(3));
				dtoMyLecture.setDepartname(rs.getString(4));
				dtoMyLecture.setGrade(rs.getByte(5));
				dtoMyLecture.setTerm(rs.getByte(6));
				dtoMyLecture.setIattend(rs.getByte(7));
				dtoMyLecture.setImiddle(rs.getByte(8));
				dtoMyLecture.setIlast(rs.getByte(9));
				dtoMyLecture.setInormal(rs.getByte(10));
				dtoMyLecture.setIpractice(rs.getByte(11));
				dtoMyLecture.setItotal(rs.getByte(12));
				dtoMyLecture.setIpoint(rs.getFloat(13));
				dtoMyLecture.setIgrade(rs.getString(14));
				dtoMyLecture.setRetake(rs.getByte(15));
				dtoMyLecture.setIlate(rs.getByte(16));
				dtoMyLecture.setIxhour(rs.getInt(17));
				dtoMyLecture.setQakind(rs.getByte(18));
				dtoMyLecture.setQaday(rs.getDate(19));
				dtoMyLecture.setQatitle(rs.getString(20));
				dtoMyLecture.setQaask(rs.getString(21));
				dtoMyLecture.setQaanswer(rs.getString(22));
				
				dtoMyLecture.hn = new ArrayList<Byte>();
				dtoMyLecture.hn.add((byte) 0);
				for(int i = 23; i<=86; i ++) {
					dtoMyLecture.hn.add(rs.getByte(i));
				}
				
				dtoListMyLecture.add(dtoMyLecture);				
			}
			return dtoListMyLecture;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoListMyLecture;	
	}	

	public ArrayList<MyLectureDTO> selectSubjectList(String sel1, String sel2, String sel3, String sel4, String sel5){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();			
			dtoListMyLecture = new ArrayList<MyLectureDTO>();
			if(sel4.equals("0"))
				rs = stmt.executeQuery("select mylecture.*, student.schoolno as student_schoolno, student.name as student_name, student.state as student_state, student.grade as student_grade, lecture.class as lecture_class, lecture.subject_id as lecture_subject_id, subject.yyyy as subject_yyyy, subject.grade as subject_grade, subject.term as subject_term, subject.name as subject_name, depart.name as depart_name FROM mylecture left join student on mylecture.student_id=student.id LEFT join lecture on mylecture.lecture_id = lecture.id LEFT JOIN subject on lecture.subject_id = subject.id left join depart on student.depart_id = depart.id where subject.yyyy = "+sel1+" and subject.term= "+sel2+" and subject.grade= "+sel3+" and depart.id="+sel5);
			else
				rs = stmt.executeQuery("select mylecture.*, student.schoolno as student_schoolno, student.name as student_name, student.state as student_state, student.grade as student_grade, lecture.class as lecture_class, lecture.subject_id as lecture_subject_id, subject.yyyy as subject_yyyy, subject.grade as subject_grade, subject.term as subject_term, subject.name as subject_name, depart.name as depart_name FROM mylecture left join student on mylecture.student_id=student.id LEFT join lecture on mylecture.lecture_id = lecture.id LEFT JOIN subject on lecture.subject_id = subject.id left join depart on student.depart_id = depart.id where subject.yyyy = "+sel1+" and subject.term= "+sel2+" and subject.grade= "+sel3+" and lecture.class= '"+sel4+"' and depart.id="+sel5);
				
			while(rs.next()) {
				dtoMyLecture = new MyLectureDTO();
				dtoStudent = new StudentDTO();
				dto = new LectureDTO();
				dtoSubject = new SubjectDTO();
				dtoDepart = new DepartDTO();
				dtoMyLecture.setId(rs.getInt(1));
				dtoMyLecture.setStudent_id(rs.getInt(2));
				dtoMyLecture.setLecture_id(rs.getInt(3));
				dtoMyLecture.setDepartname(rs.getString(4));
				dtoMyLecture.setGrade(rs.getByte(5));
				dtoMyLecture.setTerm(rs.getByte(6));
				dtoMyLecture.setIattend(rs.getByte(7));
				dtoMyLecture.setImiddle(rs.getByte(8));
				dtoMyLecture.setIlast(rs.getByte(9));
				dtoMyLecture.setInormal(rs.getByte(10));
				dtoMyLecture.setIpractice(rs.getByte(11));
				dtoMyLecture.setItotal(rs.getByte(12));
				dtoMyLecture.setIpoint(rs.getFloat(13));
				dtoMyLecture.setIgrade(rs.getString(14));
				dtoMyLecture.setRetake(rs.getByte(15));
				dtoMyLecture.setIlate(rs.getByte(16));
				dtoMyLecture.setIxhour(rs.getInt(17));
				dtoMyLecture.setQakind(rs.getByte(18));
				dtoMyLecture.setQaday(rs.getDate(19));
				dtoMyLecture.setQatitle(rs.getString(20));
				dtoMyLecture.setQaask(rs.getString(21));
				dtoMyLecture.setQaanswer(rs.getString(22));
				
				dtoMyLecture.hn = new ArrayList<Byte>();
				dtoMyLecture.hn.add((byte) 0);
				for(int i = 23; i<=86; i ++) {
					dtoMyLecture.hn.add(rs.getByte(i));
				}
				
				dtoStudent.setSchoolno(rs.getString(87));//음.....
				dtoStudent.setName(rs.getString(88));
				dtoStudent.setState(rs.getString(89));
				dtoStudent.setGrade(rs.getByte(90));
				
				dto.setLecture_class(rs.getString(91));
				dto.setSubject_id(rs.getInt(92));				
				
				dtoSubject.setYyyy(rs.getInt(93));
				dtoSubject.setGrade(rs.getByte(94));
				dtoSubject.setTerm(rs.getByte(95));
				dtoSubject.setName(rs.getString(96));
				dto.setSubject(dtoSubject);
				dtoMyLecture.setLecture(dto);
				
				dtoDepart.setName(rs.getString(97));
				dtoStudent.setDepart(dtoDepart);
				
				dtoMyLecture.setStudent(dtoStudent);
				dtoListMyLecture.add(dtoMyLecture);				
			}
			return dtoListMyLecture;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoListMyLecture;	
	}	
	
	public ArrayList<LectureDTO> list(String sel1, String sel2, String sel3){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoList = new ArrayList<LectureDTO>();
			if(sel1 != null && sel2 != null && !sel3.equals("0"))
				rs=stmt.executeQuery("SELECT lecture.*, subject.id, subject.name, subject.code, subject.ihour, subject.ipoint, subject.yyyy, subject.term, subject.grade, teacher.id, teacher.name FROM lecture "
						+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
						+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
						+ "LEFT JOIN depart ON subject.depart_id=depart.id "
						+ "Where subject.yyyy="+sel1+" and subject.term="+sel2+" and subject.grade="+sel3);
			else if(sel1 != null && sel2 != null && sel3.equals("0"))
				rs=stmt.executeQuery("SELECT lecture.*, subject.id, subject.name, subject.code, subject.ihour, subject.ipoint, subject.yyyy, subject.term, subject.grade, teacher.id, teacher.name FROM lecture "
						+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
						+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
						+ "LEFT JOIN depart ON subject.depart_id=depart.id "
						+ "Where subject.yyyy="+sel1+" and subject.term="+sel2);
			else
				rs=stmt.executeQuery("SELECT lecture.*, subject.id, subject.name, subject.code, subject.ihour, subject.ipoint, teacher.id, teacher.name FROM lecture "
						+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
						+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
						+ "LEFT JOIN depart ON subject.depart_id=depart.id");
			
			dtoList = new ArrayList<LectureDTO>();
			
			while(rs.next()) {
				dto = new LectureDTO();
				dtoDepart = new DepartDTO();
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dto.setId(rs.getInt(1));
				dto.setSubject_id(rs.getInt(2));
				dto.setTeacher_id(rs.getInt(3));
				dto.set_class(rs.getString(4)); 
				dto.setNumber(rs.getByte(5));
				dtoSubject.setId(rs.getInt(6));
				dtoSubject.setName(rs.getString(7));
				dtoSubject.setCode(rs.getString(8));
				dtoSubject.setIhour(rs.getByte(9));
				dtoSubject.setIpoint(rs.getFloat(10));
				dtoTeacher.setId(11);
				dtoTeacher.setName(rs.getString(12));
				dto.setSubject(dtoSubject);
				dto.setDepart(dtoDepart);
				dto.setTeacher(dtoTeacher);
				dtoList.add(dto);
			} return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return dtoList;
	}
	
	public ArrayList<LectureDTO> list(){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoList = new ArrayList<LectureDTO>();
			rs=stmt.executeQuery("SELECT lecture.*, subject.id, subject.name, subject.code, subject.ihour, subject.ipoint, teacher.id, teacher.name FROM lecture "
					+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
					+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
					+ "LEFT JOIN depart ON subject.depart_id=depart.id");
			
			dtoList = new ArrayList<LectureDTO>();
			
			while(rs.next()) {
				dto = new LectureDTO();
				dtoDepart = new DepartDTO();
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dto.setId(rs.getInt(1));
				dto.setSubject_id(rs.getInt(2));
				dto.setTeacher_id(rs.getInt(3));
				dto.set_class(rs.getString(4));
				dto.setNumber(rs.getByte(5));
				dtoSubject.setId(rs.getInt(6));
				dtoSubject.setName(rs.getString(7));
				dtoSubject.setCode(rs.getString(8));
				dtoSubject.setIhour(rs.getByte(9));
				dtoSubject.setIpoint(rs.getFloat(10));
				dtoTeacher.setId(11);
				dtoTeacher.setName(rs.getString(12));
				dto.setSubject(dtoSubject);
				dto.setDepart(dtoDepart);
				dto.setTeacher(dtoTeacher);
				dtoList.add(dto);
			} return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return dtoList;
	}
	
	public int register(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
    	String sql1 = "SELECT lecture.*, depart.name FROM lecture "
				+ "LEFT JOIN subject ON lecture.subject_id=subject.id "
				+ "LEFT JOIN teacher ON lecture.teacher_id=teacher.id "
				+ "LEFT JOIN depart ON subject.depart_id=depart.id";
    	
    	try {
			conn = getConnection();
			stmt = conn.createStatement();
	    	pstmt = conn.prepareStatement(sql1);

	    	String sql2 = "insert into lecture (subject_id, class) select subject.id, 1 from subject LEFT JOIN depart ON subject.depart_id=depart.id where depart.classnum >= 1";
	    	pstmt = conn.prepareStatement(sql2);
	    	result = pstmt.executeUpdate(sql2);
	    	
	    	String sql3 = "update lecture set class = 'A' where class='1'";
	    	pstmt = conn.prepareStatement(sql3);
	    	result = pstmt.executeUpdate(sql3);
	    	
	    	String sql4 = "INSERT INTO lecture (subject_id, class) SELECT subject.id, 2 FROM subject LEFT JOIN depart ON subject.depart_id=depart.id WHERE depart.classnum >=2";
	    	pstmt = conn.prepareStatement(sql4);
	    	result = pstmt.executeUpdate(sql4);
	    	
	    	String sql5 = "update lecture set class = 'B' where class='2'";
	    	pstmt = conn.prepareStatement(sql5);
	    	result = pstmt.executeUpdate(sql5);
	    	
	    	String sql6 = "INSERT INTO lecture (subject_id, class) SELECT subject.id, 3 FROM subject LEFT JOIN depart ON subject.depart_id=depart.id WHERE depart.classnum >=3";
	    	pstmt = conn.prepareStatement(sql6);
	    	result = pstmt.executeUpdate(sql6);
	    	
	    	String sql7 = "update lecture set class = 'C' where class='3'";
	    	pstmt = conn.prepareStatement(sql7);
	    	result = pstmt.executeUpdate(sql7);
	    	
	    	String sql8 = "insert into lecture (subject_id, class) select subject.id, 4 from subject LEFT JOIN depart ON subject.depart_id=depart.id where depart.classnum >= 4";
	    	pstmt = conn.prepareStatement(sql8);
	    	result = pstmt.executeUpdate(sql8);
	    	
	    	String sql9 = "update lecture set class = 'D' where class='4'";
	    	pstmt = conn.prepareStatement(sql9);
	    	result = pstmt.executeUpdate(sql9);
	    	
	    	String sql10 = "insert into lecture (subject_id, class) select subject.id, 5 from subject LEFT JOIN depart ON subject.depart_id=depart.id where depart.classnum >= 5";
	    	pstmt = conn.prepareStatement(sql10);
	    	result = pstmt.executeUpdate(sql10);
	    	
	    	String sql11 = "update lecture set class = 'E' where class='5'";
	    	pstmt = conn.prepareStatement(sql11);
	    	result = pstmt.executeUpdate(sql11);
	    	
	    	return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
    	return result;
	}
	
	public int updateT(HttpServletRequest request, HttpServletResponse response, int id) {
		int result = 0;
		dto = new LectureDTO();
		dto.setTeacher_id(Integer.parseInt(request.getParameter("teacherno")));
		
		String sql = "update lecture set teacher_id=? where id="+id;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getTeacher_id());
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
	
	public int updateN(HttpServletRequest request, HttpServletResponse response, int id) {
		int result = 0;
		dto = new LectureDTO();
		dto.setNumber(Byte.parseByte(request.getParameter("numberno"))); 
		
		String sql = "update lecture set number=? where id="+id;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNumber());
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
	
	public int delete() {
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from lecture");
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

	
}
