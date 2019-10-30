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
	private StudentDTO student = null;
	private ArrayList<StudentDTO> alStudent = null;
	String query = null;
	String url;
	int i, j;

	int page_line = 5; // 페이지당 line 수
	int page_block = 5; // 블록당 page 수

	public int delete(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
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

	public StudentDTO detail(int id) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from student where id=" + id);
			if (rs.next()) {
				student = new StudentDTO();
				student.setId(rs.getInt(1));
				student.setDepart_id(rs.getInt(2));
				student.setGrade(rs.getByte(3));
				student.setStudent_class(rs.getString(4));
				student.setSchoolno(rs.getString(5));
				student.setName(rs.getString(6));
				student.setPhone(rs.getString(7));
				student.setSex(rs.getByte(8));
				student.setPwd(rs.getString(9));
				student.setPic(rs.getString(10));
				student.setState(rs.getString(11));
				student.setBirthday(rs.getString(12));
				student.setEmail(rs.getString(13));
			}
			return student;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return student;
	}

	public int register(HttpServletRequest request, HttpServletResponse response) {

		int result = 0;
		student = new StudentDTO();
		student.setDepart_id(Integer.parseInt(request.getParameter("depart_id")));
		student.setGrade(Byte.parseByte(request.getParameter("grade")));
		student.setStudent_class(request.getParameter("student_class"));
		student.setSchoolno(request.getParameter("schoolno"));
		student.setName(request.getParameter("name"));
		student.setPhone(request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-"
				+ request.getParameter("phone3"));
		student.setSex(Byte.parseByte(request.getParameter("sex")));
		student.setPwd(request.getParameter("pwd"));
		student.setState(request.getParameter("state"));
		student.setBirthday(request.getParameter("birthday1") + "-" + request.getParameter("birthday2") + "-"
				+ request.getParameter("birthday3"));
		student.setEmail(request.getParameter("email"));

		student.setPic((String) request.getAttribute("pic"));

		String sql = "insert into student(id, depart_id, grade, class, schoolno, name, "
				+ "phone, sex, pwd, pic, state, birthday, email) " + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, student.getId());
			pstmt.setInt(2, student.getDepart_id());
			pstmt.setInt(3, student.getGrade());
			pstmt.setString(4, student.getStudent_class());
			pstmt.setString(5, student.getSchoolno());
			pstmt.setString(6, student.getName());
			pstmt.setString(7, student.getPhone());
			pstmt.setByte(8, student.getSex());
			pstmt.setString(9, student.getPwd());
			pstmt.setString(10, student.getPic());
			pstmt.setString(11, student.getState());
			pstmt.setString(12, student.getBirthday());
			pstmt.setString(13, student.getEmail());

			result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return result;
	}

	public int update(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		student = new StudentDTO();
		student.setId(Integer.parseInt(request.getParameter("id")));
		student.setDepart_id(Integer.parseInt(request.getParameter("depart_id")));
		student.setGrade(Byte.parseByte(request.getParameter("grade")));
		student.setStudent_class(request.getParameter("student_class"));
		student.setSchoolno(request.getParameter("schoolno"));
		student.setName(request.getParameter("name"));
		student.setPhone(request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-"
				+ request.getParameter("phone3"));
		student.setSex(Byte.parseByte(request.getParameter("sex")));
		student.setPwd(request.getParameter("pwd"));
		student.setState(request.getParameter("state"));
		student.setBirthday(request.getParameter("birthday1") + "-" + request.getParameter("birthday2") + "-"
				+ request.getParameter("birthday3"));
		student.setEmail(request.getParameter("email"));

		student.setPic((String) request.getAttribute("pic"));

		String sql = "update student set depart_id=?, grade=?, class=?, schoolno=?, name=?, "
				+ "phone=?, sex=?, pwd=?, pic=?, state=?, birthday=?, email=? where id=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, student.getDepart_id());
			pstmt.setInt(2, student.getGrade());
			pstmt.setString(3, student.getStudent_class());
			pstmt.setString(4, student.getSchoolno());
			pstmt.setString(5, student.getName());
			pstmt.setString(6, student.getPhone());
			pstmt.setByte(7, student.getSex());
			pstmt.setString(8, student.getPwd());
			pstmt.setString(9, student.getPic());
			pstmt.setString(10, student.getState());
			pstmt.setString(11, student.getBirthday());
			pstmt.setString(12, student.getEmail());
			pstmt.setInt(13, student.getId());
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

	public ArrayList<StudentDTO> list(int start, int end) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from student limit "+start+","+end);
			alStudent = new ArrayList<StudentDTO>();
			while (rs.next()) {
				student = new StudentDTO();
				student.setId(rs.getInt(1));
				student.setDepart_id(rs.getInt(2));
				student.setGrade(rs.getByte(3));
				student.setStudent_class(rs.getString(4));
				student.setSchoolno(rs.getString(5));
				student.setName(rs.getString(6));
				student.setPhone(rs.getString(7));
				student.setSex(rs.getByte(8));
				student.setPwd(rs.getString(9));
				student.setPic(rs.getString(10));
				student.setState(rs.getString(11));
				student.setBirthday(rs.getString(12));
				student.setEmail(rs.getString(13));
				alStudent.add(student);
			}
			return alStudent;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alStudent;
	}

	public ArrayList<StudentDTO> search(String text1) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			if (text1 == null)
				rs = stmt.executeQuery("select * from student");
			else
				rs = stmt.executeQuery("select * from student where name like '%" + text1 + "%' order by name");
			// email, pw는 form을 구성하는 각 요소의 이름
			alStudent = new ArrayList<StudentDTO>();
			while (rs.next()) {
				student = new StudentDTO();
				student.setId(rs.getInt(1));
				student.setDepart_id(rs.getInt(2));
				student.setGrade(rs.getByte(3));
				student.setStudent_class(rs.getString(4));
				student.setSchoolno(rs.getString(5));
				student.setName(rs.getString(6));
				student.setPhone(rs.getString(7));
				student.setSex(rs.getByte(8));
				student.setPwd(rs.getString(9));
				student.setPic(rs.getString(10));
				student.setState(rs.getString(11));
				student.setBirthday(rs.getString(12));
				student.setEmail(rs.getString(13));
				alStudent.add(student);
			}
			return alStudent;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alStudent;
	}

	public StudentDTO list_id(String uid) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from student where schoolno= " + uid);
			if (rs.next()) {
				student = new StudentDTO();
				student.setId(rs.getInt(1));
				student.setDepart_id(rs.getInt(2));
				student.setGrade(rs.getByte(3));
				student.setStudent_class(rs.getString(4));
				student.setSchoolno(rs.getString(5));
				student.setName(rs.getString(6));
				student.setPhone(rs.getString(7));
				student.setSex(rs.getByte(8));
				student.setPwd(rs.getString(9));
				student.setPic(rs.getString(10));
				student.setState(rs.getString(11));
				student.setBirthday(rs.getString(12));
				student.setEmail(rs.getString(13));
			}
			return student;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
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
