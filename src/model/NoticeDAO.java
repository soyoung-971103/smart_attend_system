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
	String query = null;
	String url;
	int i, j;
	
	int page_line = 5; // 페이지당 line 수
	int page_block = 5; // 블록당 page 수
	
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
	
	public ArrayList<NoticeDTO> list(String text1, int start, int end){
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			if (text1 != null) rs=stmt.executeQuery("select * from notice where title like '%"+ text1 +"%' order by writeday limit "+start+", "+end);
			else rs=stmt.executeQuery("select * from notice order by writeday limit "+start+", "+end);
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
