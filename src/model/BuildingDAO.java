package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;


public class BuildingDAO extends DAOBase{

	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<BuildingDTO> dtoList = null;
	BuildingDTO dto = null;
	HttpSession sesobj = null;
	
	
	public ArrayList<BuildingDTO> selectAllList(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoList = new ArrayList<BuildingDTO>();
			rs = stmt.executeQuery("select * from building");
			while(rs.next()) {
				dto = new BuildingDTO();
				dto.setId(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setFloor(rs.getByte(3));
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}
	
	public ArrayList<BuildingDTO> selectAllList(String text1){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoList = new ArrayList<BuildingDTO>();
			rs = stmt.executeQuery("select * from building where name like '%"+text1+"%'");
			while(rs.next()) {
				dto = new BuildingDTO();
				dto.setId(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setFloor(rs.getByte(3));
				dtoList.add(dto);
			}
			return dtoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoList;	
	}
	
	
	public int insert(BuildingDTO dto) {    	
    	int result = 0;
    	try {
    		conn = getConnection();
        	pstmt = conn.prepareStatement("insert into building(name, floor) " +  
    				"values(?, ?)");	
    		
    		pstmt.setString(1, dto.getName()); 
    		pstmt.setByte(2, dto.getFloor());
    		result = pstmt.executeUpdate();	
    		return result;    		
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return result;
    }

	public BuildingDTO selectOne(BuildingDTO dtoInfo){
		try {
	  		conn = getConnection();
	  		stmt = conn.createStatement();
	  		rs = stmt.executeQuery("select * from building" + 
	  		" where id = " + dtoInfo.getId());
	  		if(rs.next()) {
	  			dto = new BuildingDTO();
	  			dto.setId(rs.getInt(1));
	  			dto.setName(rs.getString(2));
	  			dto.setFloor(rs.getByte(3));
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

	public int update(BuildingDTO dto) {
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update building " + 
			"set name=?, floor=? where id=?");
			pstmt.setString(1, dto.getName()); 
			pstmt.setByte(2, dto.getFloor());    
			pstmt.setInt(3, dto.getId());
			
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
			pstmt = conn.prepareStatement("delete from building where id=? ");
			pstmt.setInt(1, id); //첫번째 물음표 대체
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
	
	public ArrayList<BuildingDTO> selectSearchList(String text){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoList = new ArrayList<BuildingDTO>();
			rs = stmt.executeQuery("select * from building where name="+text);
			while(rs.next()) {
				dto = new BuildingDTO();
				dto.setId(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setFloor(rs.getByte(3));
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
