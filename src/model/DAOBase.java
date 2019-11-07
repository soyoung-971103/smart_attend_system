package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOBase implements DAO {
	//						1				2						3									4											5																6
	int [][] weekhour1 = {{17,14,10}, {18,17,16,15,13,11,10}, {19,18,17,16,15,14,13,12,11,11,10}, {19,18,17,16,15,15,14,14,13,13,12,12,11,11,10}, {19,19,18,18,17,17,16,16,15,15,14,14,13,13,12,12,11,11,10}, {19,19,18,18,17,17,16,16,16,15,15,15,14,14,14,13,13,13,12,12,11,11,10}};
	private Connection conn = null;
	int page_line = 5; // 페이지당 line 수
    int page_block = 5; // 블록당 page 수
    String query = null;
    String url;

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
    
	public void closeDBResources(ResultSet rs, Statement stmt, PreparedStatement pstmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
		
	
	@Override
	public Connection getConnection() throws SQLException {		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String url="jdbc:mysql://gamejigix.induk.ac.kr:53306/attend?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "attend", "attenddb");
			return conn; 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn; //or return null
	}

}
