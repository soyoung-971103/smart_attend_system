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
}
