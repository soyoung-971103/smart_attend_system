package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NoticeDAO extends DAOBase {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private NoticeDTO dto = null;
	private ArrayList<NoticeDTO> dtoList = null;
	
	public ArrayList<NoticeDTO> list(String text1){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			if (text1 == null) rs=stmt.executeQuery("select * from notice order by writeday");
			else rs=stmt.executeQuery("select * from notice where title like '%"+ text1 +"%' order by writeday");
			// email, pw는 form을 구성하는 각 요소의 이름
			dtoList = new ArrayList<NoticeDTO>();
			while(rs.next()) {
				dto = new NoticeDTO();
				dto.setId(rs.getInt(1));
				dto.setWriteday(rs.getDate(2));;
				dto.setTitle(rs.getString(3));
				dto.setTxt1(rs.getString(4));
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
	
	public NoticeDTO detail(int id) {
		try {
			conn = getConnection();
			stmt = conn.createStatement(); // 연결 객체로부터 statement 객체 생성
			rs = stmt.executeQuery("select * from notice where id=" + id);
			// email, pw는 form을 구성하는 각 요소의 이름
			
			if(rs.next()) {
				dto = new NoticeDTO();
				dto.setId(rs.getInt(1));
				dto.setWriteday(rs.getDate(2));
				dto.setTitle(rs.getString(3));
				dto.setTxt1(rs.getString(4));
			}
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return dto;
	}
	
	public int register(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
		String today = formatter.format(new java.util.Date());
		dto = new NoticeDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setTxt1(request.getParameter("txt1"));
		try {
			dto.setWriteday(formatter.parse(today));			
    	} catch(ParseException e) {
    		e.printStackTrace();
    	}

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into notice values(?, ?, ?, ?)");
			pstmt.setInt(1, dto.getId());
	    	pstmt.setDate(2, new java.sql.Date(dto.getWriteday().getTime()));
	    	pstmt.setString(3, dto.getTitle());
	    	pstmt.setString(4, dto.getTxt1());
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
	
	public int update(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		dto = new NoticeDTO();
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setTitle(request.getParameter("title"));
		dto.setTxt1(request.getParameter("txt1"));
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update notice set title=?, txt1=? where id=?");
			pstmt.setString(1, dto.getTitle());
	    	pstmt.setString(2, dto.getTxt1());
	    	pstmt.setInt(3, dto.getId());
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
	
	public int delete(HttpServletRequest request, HttpServletResponse response) {
		int result=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from notice where id=?");
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
}
