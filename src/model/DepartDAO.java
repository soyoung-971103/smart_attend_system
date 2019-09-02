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
}
