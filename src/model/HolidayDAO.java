package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class HolidayDAO extends DAOBase{
	
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<HolidayDTO> dtoList = null;
	HolidayDTO dto = null;
	HttpSession sesobj = null;
	String query = null;
	String url;
	int i, j;
	
	int page_line = 5; // 페이지당 line 수
	int page_block = 5; // 블록당 page 수
	
	public int insert(HolidayDTO dto) {
		int result = 0;		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into holiday " + "values(?, ?, ?, ?)");
			pstmt.setInt(1, dto.getId());
			pstmt.setInt(2, dto.getYyyy()); 
			pstmt.setDate(3, new java.sql.Date(dto.getHoliday().getTime()+1));
			pstmt.setString(4, dto.getReason());
			
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
	
	public int update(HolidayDTO dto) {
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update holiday " + 
			"set yyyy=?, holiday=?, reason=? where id=?");
			
			pstmt.setInt(1, dto.getYyyy()); 
			pstmt.setDate(2, new java.sql.Date(dto.getHoliday().getTime()));
			pstmt.setString(3, dto.getReason());
			pstmt.setInt(4, dto.getId());
			
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
			pstmt = conn.prepareStatement("delete from holiday where id=? ");
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

	public ArrayList<HolidayDTO> List(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			dtoList = new ArrayList<HolidayDTO>();
			rs = stmt.executeQuery("select * from holiday");
			while(rs.next()) {
				dto = new HolidayDTO(); 
				dto.setId(rs.getInt(1));
				dto.setYyyy(rs.getInt(2));
				dto.setHoliday(rs.getDate(3));
				dto.setReason(rs.getString(4));
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}
	
	public HolidayDTO selectOne(HolidayDTO dtoInfo){
		try {
	  		conn = getConnection();
	  		stmt = conn.createStatement();
	  		rs = stmt.executeQuery("select * from holiday" + 
	  		" where id = " + dtoInfo.getId());
	  		if(rs.next()) {
	  			dto = new HolidayDTO();
	  			dto.setId(rs.getInt(1));
	  			dto.setYyyy(rs.getInt(2)); 
				dto.setHoliday(rs.getDate(3));
				dto.setReason(rs.getString(4));
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
	
	public ArrayList<HolidayDTO> selectSearchList(String text){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoList = new ArrayList<HolidayDTO>();
			rs = stmt.executeQuery("select * from holiday where yyyy="+text);
			while(rs.next()) {
				dto = new HolidayDTO();
				pstmt.setDate(1, new java.sql.Date(dto.getHoliday().getTime()));
				pstmt.setString(2, dto.getReason());
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}

}

