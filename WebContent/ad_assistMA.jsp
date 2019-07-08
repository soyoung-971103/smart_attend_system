<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*, java.sql.*, java.io.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<%@ include file="common.jsp" %>

<%
  String id = request.getParameter("id");
  String MA = request.getParameter("MA");
  if(MA.equals("DELETE"))
  {
    query = "delete from staff where id="+id+";";

  }else if(MA.equals("UPDATE"))
  {
    String depart_id = request.getParameter("depart_id");
    String uid = request.getParameter("uid");
    String pwd = request.getParameter("pwd");
    String tel1 = request.getParameter("tel1");
    String tel2 = request.getParameter("tel2");
    String tel3 = request.getParameter("tel3");
    String phone1 = request.getParameter("phone1");
    String phone2 = request.getParameter("phone2");
    String phone3 = request.getParameter("phone3");
    String email = request.getParameter("email");
    String pic = request.getParameter("pic");

    String tel = String.format("%-3s%-4s%-4s",tel1, tel2, tel3);
    String phone = String.format("%-3s%-4s%-4s", phone1, phone2, phone3);

    query="update staff set name='"+name+"', uid='"+uid+"', pwd='"+pwd+"', depart_id='"+depart_id+"', tel='"+tel+"',phone='"+phone+"',email='"+email+"',pic='"+pic+"' where id="+id+";";
  }else if(MA.equals("INSERT"))
  {
    String id=request.getParameter("id");
    String name=request.getParameter("name");
    String classnum=request.getParameter("classnum");
    String gradesystem=request.getParameter("gradesystem");

    query="insert into staff (id, name, classnum, gradesystem) values ("+id+",'"+name+"',"+classnum+","+gradesystem+");";
  }else
  {
    response.sendRedirect("ad_teacher.jsp");
  }
  stmt.executeUpdate(query);
  stmt.close();
  conn.close();
  //request, response가 유지 안됨.
  response.sendRedirect("ad_teacher.jsp");
%>
