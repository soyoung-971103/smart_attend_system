<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*, java.sql.*, java.io.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<%@ include file="common.jsp" %>

<%
  String id = request.getParameter("id");
  String MA = request.getParameter("MA");

  if(MA.equals("DELETE"))
  {
    query = "delete from teacher where id="+id+";";

  }else if(MA.equals("UPDATE"))
  {
    
  }else if(MA.equals("INSERT"))
  {

  }
  stmt.executeUpdate(query);
  stmt.close();
  conn.close();
  //request, response가 유지 안됨.
  response.sendRedirect("ad_teacher.jsp");
%>
