package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

public class Pagination extends DAOBase{
	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	HttpSession sesobj = null;
	
	public int count(String query)
	{
		try {
			conn = getConnection();
			stmt = conn.createStatement();
	    	ResultSet rs = null;
			rs = stmt.executeQuery(query);
			rs.next();
			
			return rs.getInt("cnt");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	closeDBResources(rs, stmt, pstmt, conn);	}
		return 0;
	}
	public void paging(String start, String end, String table, String where)
	{
		
	}
}
