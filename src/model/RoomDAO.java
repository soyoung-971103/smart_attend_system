package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;


public class RoomDAO extends DAOBase{

	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<RoomDTO> dtoList = null;
	RoomDTO dto = null;
	BuildingDTO dtoBuilding = null;
	DepartDTO dtoDepart = null;
	HttpSession sesobj = null;	
	
	public ArrayList<RoomDTO> selectAllList(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoList = new ArrayList<RoomDTO>();
			rs = stmt.executeQuery("SELECT room.*, building.name, building.floor, depart.name FROM room LEFT JOIN "
					+ "building ON room.building_id = building.id LEFT JOIN depart ON room.depart_id = depart.id");
			while(rs.next()) {
				dto = new RoomDTO();
				dtoBuilding = new BuildingDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setBuilding_id(rs.getInt(2));
				dto.setFloor(rs.getByte(3));
				dto.setHo(rs.getString(4));
				dto.setDepart_id(rs.getInt(5));
				dto.setName(rs.getString(6));
				dto.setKind(rs.getString(7));
				dto.setArea(rs.getInt(8));		
				dtoBuilding.setName(rs.getString(9));
				dtoBuilding.setFloor(rs.getByte(10));
				dto.setBuilding(dtoBuilding);
				dtoDepart.setName(rs.getString(11));
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
	
	public ArrayList<RoomDTO> selectAllList(String text1){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			dtoList = new ArrayList<RoomDTO>();
			rs = stmt.executeQuery("SELECT room.*, building.name, building.floor, depart.name FROM room LEFT JOIN "
					+ "building ON room.building_id = building.id LEFT JOIN depart ON room.depart_id = depart.id WHERE room.name LIKE '%"+text1+"%'");
			while(rs.next()) {
				dto = new RoomDTO();
				dtoBuilding = new BuildingDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setBuilding_id(rs.getInt(2));
				dto.setFloor(rs.getByte(3));
				dto.setHo(rs.getString(4));
				dto.setDepart_id(rs.getInt(5));
				dto.setName(rs.getString(6));
				dto.setKind(rs.getString(7));
				dto.setArea(rs.getInt(8));		
				dtoBuilding.setName(rs.getString(9));
				dtoBuilding.setFloor(rs.getByte(10));
				dto.setBuilding(dtoBuilding);
				dtoDepart.setName(rs.getString(11));
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
	
	public int insert(RoomDTO dto) {    	
    	int result = 0;
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement("insert into room(building_id, floor, ho, depart_id, name, kind, area) " +  
    				"values(?, ?, ?, ?, ?, ?, ?)");	
    		pstmt.setInt(1, dto.getBuilding_id());
    		pstmt.setByte(2, dto.getFloor());
    		pstmt.setString(3, dto.getHo());
    		pstmt.setInt(4, dto.getDepart_id());
    		pstmt.setString(5, dto.getName()); 
    		pstmt.setString(6, dto.getKind());
    		pstmt.setInt(7, dto.getArea());
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

	public RoomDTO selectOne(RoomDTO dtoInfo){
		try {
	  		conn = getConnection();
	  		stmt = conn.createStatement();
	  		rs = stmt.executeQuery("SELECT room.*, building.name, building.floor, depart.name FROM room LEFT JOIN building ON room.building_id = building.id LEFT JOIN depart ON room.depart_id = depart.id" + 
			" WHERE room.id = " + dtoInfo.getId());
	  		if(rs.next()) {
	  			dto = new RoomDTO();
				dtoBuilding = new BuildingDTO();
				dtoDepart = new DepartDTO();
				dto.setId(rs.getInt(1));
				dto.setBuilding_id(rs.getInt(2));
				dto.setFloor(rs.getByte(3));
				dto.setHo(rs.getString(4));
				dto.setDepart_id(rs.getInt(5));
				dto.setName(rs.getString(6));
				dto.setKind(rs.getString(7));
				dto.setArea(rs.getInt(8));		
				dtoBuilding.setName(rs.getString(9));
				dtoBuilding.setFloor(rs.getByte(10));
				dto.setBuilding(dtoBuilding);
				dtoDepart.setName(rs.getString(11));
				dto.setDepart(dtoDepart);
				dtoList.add(dto);
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

	public int update(RoomDTO dto) {
		int result = 0;
		
		try {
			conn = getConnection();			
			pstmt = conn.prepareStatement("update room " + 
			"set building_id=?, floor=?, ho=?, depart_id=?, name=?, kind=?, area=? where id=?");
    		pstmt.setInt(1, dto.getBuilding_id());
    		pstmt.setByte(2, dto.getFloor());
    		pstmt.setString(3, dto.getHo());
    		pstmt.setInt(4, dto.getDepart_id());
    		pstmt.setString(5, dto.getName()); 
    		pstmt.setString(6, dto.getKind());
    		pstmt.setInt(7, dto.getArea());
    		pstmt.setInt(8, dto.getId());
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
			pstmt = conn.prepareStatement("delete from room where id=? ");
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
	
}
