package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class ControlDAO extends DAOBase {
	
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<ControlDTO> dtoList = null;
	ControlDTO dto = null;
	HttpSession sesobj = null;

	public int update(ControlDTO dto) {
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update attendcontrol " + 
			"set subjecttime=?, lecturetime=?, mylecturetime=?, attendtime=? where id=?");
			
			
			pstmt.setByte(1, dto.getSubjecttime()); 
			pstmt.setByte(2, dto.getLecturetime());
			pstmt.setByte(3, dto.getMylecturetime());
			pstmt.setByte(4, dto.getAttendtime());
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
	

	public ArrayList<ControlDTO> List(){		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			dtoList = new ArrayList<ControlDTO>();
			rs = stmt.executeQuery("select * from attendcontrol");
			while(rs.next()) {
				dto = new ControlDTO();
				dto.setId(rs.getInt(1));
				dto.setSubjecttime(rs.getByte(2));
				dto.setLecturetime(rs.getByte(3));
				dto.setMylecturetime(rs.getByte(4));
				dto.setAttendtime(rs.getByte(5));
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

