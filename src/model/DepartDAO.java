package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class DepartDAO extends DAOBase{
	
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<DepartDTO> dtoList = null;
	DepartDTO dto = null;
	HttpSession sesobj = null;
	String query = null;
	String url;
	int i, j;
	
	int page_line = 5; // 페이지당 line 수
	int page_block = 5; // 블록당 page 수
	
	public int insert(DepartDTO dto) {
		int result = 0;	
				
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into depart " + 
					"values(?, ?, ?, ?, ?)");
			
			pstmt.setInt(1, dto.getId());
			pstmt.setString(2, dto.getName()); 
			pstmt.setByte(3, dto.getClassnum());
			pstmt.setByte(4, dto.getGradesystem());
			pstmt.setString(5, dto.getAbbreviation());
			
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
	
	public int update(DepartDTO dto) {
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update depart " + 
			"set name=?, classnum=?, gradesystem=?, abbreviation=? where id=?");
			
			
			pstmt.setString(1, dto.getName()); 
			pstmt.setByte(2, dto.getClassnum());
			pstmt.setByte(3, dto.getGradesystem());
			pstmt.setString(4, dto.getAbbreviation());
			pstmt.setInt(5, dto.getId());
			
			
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
			pstmt = conn.prepareStatement("delete from depart where id=? ");
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

	public ArrayList<DepartDTO> List(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			dtoList = new ArrayList<DepartDTO>();
			rs = stmt.executeQuery("select * from depart");
			while(rs.next()) {
				dto = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setClassnum(rs.getByte(3));
				dto.setGradesystem(rs.getByte(4));
				dto.setAbbreviation(rs.getString(5));
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}
	
	public ArrayList<DepartDTO> List(int start, int end){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			dtoList = new ArrayList<DepartDTO>();
			rs = stmt.executeQuery("select * from depart limit "+start+","+end);
			while(rs.next()) {
				dto = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setClassnum(rs.getByte(3));
				dto.setGradesystem(rs.getByte(4));
				dto.setAbbreviation(rs.getString(5));
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}
	
	public ArrayList<DepartDTO> List(String text1, int start, int end){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			dtoList = new ArrayList<DepartDTO>();
			rs = stmt.executeQuery("select * from depart WHERE name LIKE '%"+text1+"%' limit "+start+","+end);
			while(rs.next()) {
				dto = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setClassnum(rs.getByte(3));
				dto.setGradesystem(rs.getByte(4));
				dto.setAbbreviation(rs.getString(5));
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}
	
	public DepartDTO selectOne(DepartDTO dtoInfo){
		try {
	  		conn = getConnection();
	  		stmt = conn.createStatement();
	  		rs = stmt.executeQuery("select * from depart" + 
	  		" where id = " + dtoInfo.getId());
	  		if(rs.next()) {
	  			dto = new DepartDTO();
	  			dto.setId(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setClassnum(rs.getByte(3));
				dto.setGradesystem(rs.getByte(4));
				dto.setAbbreviation(rs.getString(5));
	  		}      			
	  		return dto;
	  	} catch (SQLException e) {	
	  		e.printStackTrace();
	  	}
	  	finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
	  	return dto;
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
