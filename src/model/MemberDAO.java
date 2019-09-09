package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;


public class MemberDAO extends DAOBase{

	Connection conn = null; 
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	ArrayList<MemberDTO> dtoList = null;
	MemberDTO dto = null;
	HttpSession sesobj = null;
	
	
	public MemberDTO loginCheck(MemberDTO loginmember) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from student where " + 
					"schoolno=" + loginmember.getSchoolno() + " and " + 
							"pwd='" + loginmember.getPwd() + "'");
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setId(rs.getInt(1));
				dto.setPwd(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setPhone(rs.getString(4));
				dto.setSchoolno(rs.getString(5));
			}				
			
			return dto;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return dto;			
	}
	
	
	/*
	public int insert(MemberDTO dto) {
		int result = 0;	
				
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into ja_026_member " + 
					"values(?, ?, ?, ?)");
			
			pstmt.setString(1, dto.getEmail()); 
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPhone());
			
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

	public MemberDTO selectRow(String email) {		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from ja_026_member where " + 
			"email='" + email + "'");
			dto = null;
			//statement ��ü�� ������ sql ����. result set�� ��ȯ ����
			if(rs.next()) {//���� ����� ���� ���ڵ尡 �����ϸ�  true, �ƴϸ� false
				dto = new MemberDTO();
				dto.setEmail(rs.getString(1));
				dto.setPw(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setPhone(rs.getString(4));
			}
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return dto;
	}
	
	public int delete(String email) {
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from ja_026_member where email=?");
			pstmt.setString(1, email); //ù��° ����ǥ ��ü
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

	public int update(MemberDTO dto) {
		int result = 0;
		
		try {
			conn = getConnection();
			if(dto.getPw() != "" && dto.getPw() != null) {
				pstmt = conn.prepareStatement("update ja_026_member " 
						+ "set pw=?, name=?, phone=? where email=?");
				pstmt.setString(4, dto.getEmail()); //ù��° ����ǥ ��ü
				pstmt.setString(1, dto.getPw());
				pstmt.setString(2, dto.getName());
				pstmt.setString(3, dto.getPhone());
			}else {
				pstmt = conn.prepareStatement("update ja_026_member " 
						+ "set name=?, phone=? where email=?");
				pstmt.setString(3, dto.getEmail()); //ù��° ����ǥ ��ü
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getPhone());				
			}
			
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
		//�����ϵ� ���·� ���� �����ؼ� ���
		
	}

	public MemberDTO loginCheck(MemberDTO loginmember) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from ja_026_member where " + 
					"email='" + loginmember.getEmail() + "' and " + 
							"pw='" + loginmember.getPw() + "'");
			//statement ��ü�� ������ sql ����. result set�� ��ȯ ����
			if(rs.next()) {//���� ����� ���� ���ڵ尡 �����ϸ�  true, �ƴϸ� false
				dto = new MemberDTO();
				dto.setEmail(rs.getString(1));
				dto.setPw(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setPhone(rs.getString(4));
			}				
			
			return dto;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
		return dto;			
	}
	*/
	
	/*
	public ArrayList<MemberDTO> selectListBetween(int start, int end){
		  try {
	  		conn = getConnection();
	      	stmt = conn.createStatement();
	      	rs = stmt.executeQuery("select * from (select rownum rnum, email, pw, name, phone from ja_026_member) a where a.rnum between "+start+" and "+end+""); //"where id = '"+id+"'"
	  		dtoList = new ArrayList<MemberDTO>();	      	
	  		
	  		//statement ��ü�� ������ sql ����. result set�� ��ȯ ����
	  		while(rs.next()) {
	  			dto = new MemberDTO();
	  			dto.setEmail(rs.getString(2));
				dto.setPw(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setPhone(rs.getString(5));
				dtoList.add(dto);
	  		}
	  		//rs.close();stmt.close();conn.close();
	  		return dtoList;
	  	}catch(SQLException e){
	  		e.printStackTrace();
	  	}
			return dtoList;
	  }
	
	public int selectCount() {
	  	int totalRows = 0;
	  	try {
	  		conn = getConnection();
	  		stmt = conn.createStatement();
	  		rs = stmt.executeQuery("select count(*) from ja_026_member");
	  		if(rs.next())
	  			totalRows = rs.getInt(1);
	  		return totalRows;
	  	} catch (SQLException e) {
	  		e.printStackTrace();
	  	}
	  	finally {
			this.closeDBResources(rs, stmt, pstmt, conn);
		}
	  	return totalRows;
	  }
	
	*/
}
