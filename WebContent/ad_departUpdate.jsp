    <%@ page contentType="text/html;charset=utf-8" %>
        <%@ page import="java.util.*, java.sql.*, java.io.*" %>
            <% request.setCharacterEncoding("utf-8"); %>
        <%@ include file="common.jsp" %>

            <%
	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String classnum=request.getParameter("classnum");
	String gradesystem=request.getParameter("gradesystem");

    query="update depart set name='"+name+"', classnum="+classnum+", gradesystem="+gradesystem+" where id="+id+";";

	stmt.executeUpdate(query);

	stmt.close();
	conn.close();

	out.print("<script>location.href='ad_depart.jsp'</script>");
%>


