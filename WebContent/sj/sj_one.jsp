<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*, java.sql.*, java.io.*" %>
<% request.setCharacterEncoding("utf-8"); %>

<%@include file="common.jsp" %>

<%
	String act=request.getParameter("act");

	String npage=request.getParameter("npage");
	String text1=request.getParameter("text1");

	String no="";
	String name="";
	String kor ="";
	String eng ="";
	String mat ="";
	String hap ="";
	String avg =""; 
	if (act.equals("u"))
	{
		no=request.getParameter("no");

		query="select * from sj where no="+no+";";
		rs = stmt.executeQuery(query);
		rs.next();
		name=rs.getString("name");
		kor =rs.getString("kor");
		eng =rs.getString("eng");
		mat =rs.getString("mat");
		hap =rs.getString("hap");
		avg =rs.getString("avg");
	}
%>

<html>
<head>
	<title>성적처리 프로그램</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="font.css">
</head>

<script language="javascript">
	function cal_jumsu()
	{
		form1.hap.value=Number(form1.kor.value) + Number(form1.eng.value) + Number(form1.mat.value);
		form1.avg.value=(form1.hap.value/3.).toFixed(1);
	}
</script>

<body>

<form name="form1" method="post" action="sj_sql.jsp">

<input type="hidden" name="act" value="<%=act %>">
<input type="hidden" name="no" value="<%=no %>">
<input type="hidden" name="npage" value="<%=npage %>">
<input type="hidden" name="text1" value="<%=text1 %>">

<table width="300" class="mytable" bgcolor="lightyellow">
  <tr>
    <td width="100" align="center" bgcolor="lightblue">이름</td>
    <td width="200" align="left">
      <input type="text" name="name" size="20" value="<%=name %>">
    </td>
  </tr>
  <tr>
    <td width="100" align="center" bgcolor="lightblue">국어</td>
    <td width="200" align="left">
      <input type="text" name="kor" size="6" value="<%=kor %>" 
					onChange="javascript:cal_jumsu();">
    </td>
  </tr>
  <tr>
    <td width="100" align="center" bgcolor="lightblue">영어</td>
    <td width="200" align="left">
      <input type="text" name="eng" size="6" value="<%=eng %>" 
					onChange="javascript:cal_jumsu();">
    </td>
  </tr>
  <tr>
    <td width="100" align="center" bgcolor="lightblue">수학</td>
    <td width="200" align="left">
      <input type="text" name="mat" size="6" value="<%=mat %>" 
					onChange="javascript:cal_jumsu();">
    </td>
  </tr>
  <tr>
    <td width="100" align="center" bgcolor="lightblue">총점</td>
    <td width="200" align="left">
      <input type="text" name="hap" size="6" value="<%=hap %>" 
			readonly style="border:0;background-color:#ffffe0;text-align:right">
    </td>
  </tr>
  <tr>
    <td width="100" align="center" bgcolor="lightblue">평균</td>
    <td width="200" align="left">
      <input type="text" name="avg" size="6" value="<%=avg %>" 
			readonly style="border:0;background-color:#ffffe0;text-align:right">
    </td>
  </tr>
</table>

<table width="300" border="0">
	<tr height="35">
		<td align="center"> 
			<input type="submit" value="저장"> &nbsp
			<input type="button" value="이전화면으로" onclick="javascript:history.back();">
		</td>
	</tr>
</table>

</form>

</body>
</html>

<%
	if (act.equals("u"))
	{
		rs.close();
		stmt.close();
		conn.close();
	}
%>

