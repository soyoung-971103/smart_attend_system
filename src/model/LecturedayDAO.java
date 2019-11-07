package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class LecturedayDAO extends DAOBase{
	
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<LecturedayDTO> dtoList = null;
	LecturedayDTO dto = null;
	HttpSession sesobj = null;
	RoomDTO dtoRoom = null;
	LectureDTO dtoLecture = null;
	SubjectDTO dtoSubject = null;
	TeacherDTO dtoTeacher = null;
	DepartDTO dtoDepart = null;
	String url;
	int i, j;
	
	int page_line = 5; // 페이지당 line 수
	int page_block = 5; // 블록당 page 수
	
	public int create(LecturedayDTO dto) {
		int result = 0;	
				
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into lectureday(lecture_id, room_id, th, normdate, normstart, normhour, normstate) " + 
					"values(?, ?, ?, ?, ?, ?, ?)");
			
			pstmt.setInt(1, dto.getLecture_id());
			pstmt.setInt(2, dto.getRoom_id()); 
			pstmt.setByte(3, dto.getTh());
			pstmt.setDate(4, new java.sql.Date(dto.getNormdate().getTime()));
			pstmt.setByte(5, dto.getNormstart());
			pstmt.setByte(6, dto.getNormhour());
			pstmt.setString(7, dto.getNormstate());
			
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
	
	public int delete(int id) {
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from lectureday where id=? ");
			pstmt.setInt(1, id);
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
	
	public ArrayList<LecturedayDTO> List(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			dtoList = new ArrayList<LecturedayDTO>();
			rs = stmt.executeQuery("select lectureday.*, room.name, lecture.class, subject.grade, subject.name, teacher.name, depart.name FROM lectureday LEFT JOIN room ON lectureday.room_id = room.id LEFT JOIN lecture ON lectureday.lecture_id = lecture.id LEFT JOIN subject ON subject.id = lecture.subject_id LEFT JOIN teacher ON teacher.id = lecture.teacher_id LEFT JOIN depart ON depart.id = subject.depart_id");
			while(rs.next()) {
				dto = new LecturedayDTO();
				dtoRoom = new RoomDTO();
				dtoLecture = new LectureDTO();
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setLecture_id(rs.getInt(2));
				dto.setRoom_id(rs.getByte(3));
				dto.setTh(rs.getByte(4));
				dto.setClassification(rs.getByte(5));
				dto.setNormdate(rs.getDate(6));
				dto.setNormstart(rs.getByte(7));
				dto.setNormhour(rs.getByte(8));
				dto.setNormstate(rs.getString(9));
				dto.setRestdate(rs.getDate(10));
				dto.setReststart(rs.getByte(11));
				dto.setResthour(rs.getByte(12));
				dto.setReststate(rs.getString(13));
				dto.setState(rs.getString(14));
				dtoRoom.setName(rs.getString(15));
				dto.setRoom(dtoRoom);
				dtoLecture.setLecture_class(rs.getString(16));
				dto.setLecture(dtoLecture);
				dtoSubject.setGrade(rs.getByte(17));
				dtoSubject.setName(rs.getString(18));
				dtoLecture.setSubject(dtoSubject);
				dtoTeacher.setName(rs.getString(19));
				dtoLecture.setTeacher(dtoTeacher);
				dtoDepart.setName(rs.getString(20));
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
	
	public ArrayList<LecturedayDTO> List(int start, int end){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			dtoList = new ArrayList<LecturedayDTO>();
			rs = stmt.executeQuery("select lectureday.*, room.name, lecture.class, subject.grade, subject.name, teacher.name, depart.name FROM lectureday LEFT JOIN room ON lectureday.room_id = room.id LEFT JOIN lecture ON lectureday.lecture_id = lecture.id LEFT JOIN subject ON subject.id = lecture.subject_id LEFT JOIN teacher ON teacher.id = lecture.teacher_id LEFT JOIN depart ON depart.id = subject.depart_id limit"+start+","+end);
			while(rs.next()) {
				dto = new LecturedayDTO();
				dtoRoom = new RoomDTO();
				dtoLecture = new LectureDTO();
				dtoSubject = new SubjectDTO();
				dtoTeacher = new TeacherDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setLecture_id(rs.getInt(2));
				dto.setRoom_id(rs.getByte(3));
				dto.setTh(rs.getByte(4));
				dto.setClassification(rs.getByte(5));
				dto.setNormdate(rs.getDate(6));
				dto.setNormstart(rs.getByte(7));
				dto.setNormhour(rs.getByte(8));
				dto.setNormstate(rs.getString(9));
				dto.setRestdate(rs.getDate(10));
				dto.setReststart(rs.getByte(11));
				dto.setResthour(rs.getByte(12));
				dto.setReststate(rs.getString(13));
				dto.setState(rs.getString(14));
				dtoRoom.setName(rs.getString(15));
				dto.setRoom(dtoRoom);
				dtoLecture.setLecture_class(rs.getString(16));
				dto.setLecture(dtoLecture);
				dtoSubject.setGrade(rs.getByte(17));
				dtoSubject.setName(rs.getString(18));
				dtoLecture.setSubject(dtoSubject);
				dtoTeacher.setName(rs.getString(19));
				dtoLecture.setTeacher(dtoTeacher);
				dtoDepart.setName(rs.getString(20));
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
	public Integer rowcount(String sql) {
	      int c = 0;
	      try {
	         conn = getConnection();
	         stmt = conn.createStatement();
	         rs = stmt.executeQuery(sql);
	         if (rs.next())
	            c = rs.getInt(1);
	         return c;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return c;
	   }
	
	 // 하단 Pagination 만드는 함수 ----------------------------------------------
	   public String pagination(int nowpage, int recordcount, String nowurl) {
	      nowpage=(nowpage==0)?1:nowpage;
	      int pages = (int) (Math.ceil((float) recordcount / (float) page_line)); // 페이지수
	      int blocks = (int) (Math.ceil((float) pages / (float) page_block)); // 전체 블록수
	      int block = (int) (Math.ceil((float) nowpage / (float) page_block)); // 현재 블록
	      int page_s = page_block * (block - 1); // 현재 페이지
	      int page_e = page_block * block; // 마지막 페이지
	      if (blocks <= block)
	         page_e = pages;
	      /* 교수 학생 조교 정보 볼 때 페이지 번호 */
	      String s = "<nav><ul class='pagination pagination-sm justify-content-center'><li class='page-item'><a class='page-link' href='#'>◀</a></li>";

	      if (block > 1) // 이전 블록으로
	         s += "<li class='page-item'><a class='page-link' href='" + nowurl + "?npage=" + page_s + "'>◁</a></li>";

	      for (int i = page_s + 1; i <= page_e; i++) // 페이지들 표시
	      {
	         if (nowpage == i)
	            s += "<li class='page-item active'><span class='page-link' style='background-color:steelblue'>" + i
	                  + "</span></li>";
	         else
	            s += "<li class='page-item'><a class='page-link' href='" + nowurl + "?npage=" + i + "'>" + i
	                  + "</a></li>";
	      }

	      if (block < blocks) // 다음 블록으로
	         s += "<li class='page-item'><a class='page-link' href='" + nowurl + "?npage=" + (page_e + 1)
	               + "'>▷</a></li>";

	      s += "</ul></nav>";
	      return s;
	   }

}
