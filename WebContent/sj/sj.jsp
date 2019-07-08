<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*, java.sql.*, java.io.*" %>
<% request.setCharacterEncoding("utf-8"); %>

<%@ include file="common.jsp" %>

<%
	// 이전 문서의 변수들 (page는 예약어) ----------------------------------------------
	String text1 = request.getParameter("text1") == null ? "" : request.getParameter("text1");
	int npage= request.getParameter("npage")==null ? npage=1 :  Integer.parseInt(request.getParameter("npage"));

	String url_arg="text1="+text1;
	String url_argpage=url_arg+"&npage="+npage;

	// 레코드개수 세기  ----------------------------------------------
	String where =(text1=="") ? "" : "where name like '"+text1+"%'";
	query="select count(*) from sj "+where;
	int count=rowcount(query);

	// 현재 페이지의 레코드위치 계산 및 해당 페이지 읽기 -------------------------------
	int	start = (npage-1) * page_line;

	query="select * from sj "+where+" order by name limit "+start+","+page_line+";";
	rs = stmt.executeQuery(query);
%>

<html>
<head>
	<title>성적처리 프로그램</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="font.css">
</head>
<body>

<table width="400">
	<form name="form1" method="post" action="sj.jsp">
	<tr>
		<td align="left">
			이름 : <input type="text" name="text1" size= "10" value="<%=text1 %>">
			<input type="button" value="검색" onClick="javascript:form1.submit();">
		</td>
		<td align="right"><a href="sj_one.jsp?act=c&<%=url_argpage %>" class="box">입력</a></td>
	</tr>
	</form>
</table>

<table width="400" class="mytable">
  <tr bgcolor="lightblue">
    <td width="100">이름</td>
    <td width="50" >국어</td>
    <td width="50" >영어</td>
    <td width="50" >수학</td>
    <td width="50" >총점</td>
    <td width="50" >평균</td>
    <td width="50" >삭제</td>
  </tr>

<%
	while( rs.next() )
	{
		String no		=rs.getString("no");
		String name	=rs.getString("name");
		String kor		=rs.getString("kor");
		String eng	=rs.getString("eng");
		String mat		=rs.getString("mat");
		String hap	=rs.getString("hap");
		String avg		=rs.getString("avg");
		avg=String.format("%6.1f", Float.parseFloat(avg) );
%>
		<tr bgcolor="lightyellow">
			<td>&nbsp 
				<a href="sj_one.jsp?act=u&<%=url_argpage %>&no=<%=no %>"><%=name %></a>
			</td>
			<td><%=kor %></td>
			<td><%=eng %></td>
			<td><%=mat %></td>
			<td><%=hap %></td>
			<td><%=avg %></td>
			<td>
				<a href="sj_sql.jsp?act=d&<%=url_argpage %>&no=<%=no %>" 
					   onClick="javascript:return confirm('삭제할까요 ?');">
					삭제
				</a>
			</td>
		</tr>
<%
	}
%>
</table>

<%
	out.println( pagination(npage, count,  "sj.jsp?"+url_arg ) );
%>

</body>
</html>

<%
	rs.close();
	stmt.close();
	conn.close();
%>
