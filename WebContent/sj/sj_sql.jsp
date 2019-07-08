
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*, java.sql.*, java.io.*" %>
<% request.setCharacterEncoding("utf-8");  // 생략시 한글깨져 저장됨 %> 

<%@include file="common.jsp" %>

<%
	String act=request.getParameter("act");

	String npage=request.getParameter("npage");
	String text1=request.getParameter("text1");

	String no=request.getParameter("no");

	String name=request.getParameter("name");
	String kor=request.getParameter("kor");
	String eng=request.getParameter("eng");
	String mat=request.getParameter("mat");
	String hap=request.getParameter("hap");
	String avg=request.getParameter("avg");

	try {
		if (act.equals("c"))				// 추가
			query="insert into sj (name, kor, eng, mat, hap, avg) values ('"+name+"',"+kor+","+eng+","+mat+","+hap+","+avg+");"; 
		else 	if (act.equals("u"))		// 수정
			query="update sj set name='"+name+"',kor="+kor+",eng="+eng+",mat="+mat+",hap="+hap+",avg="+avg+" where no="+no+";";
		else 	if (act.equals("d"))		// 삭제
			query="delete from sj where no="+no+";"; 

		stmt.executeUpdate(query);
	}	
	catch(Exception e) {
		out.println( query+"<br>" );
		out.println(e);
		out.close();	// 에러메시지 보기위해 강제중단
	}

	stmt.close();
	conn.close();

	out.print("<script>location.href='sj.jsp?text1="+text1+"&npage="+npage+"'</script>");
%>


