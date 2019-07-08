
    <%@ page contentType="text/html;charset=utf-8" %>
        <%@ page import="java.util.*, java.sql.*, java.io.*" %>
            <% request.setCharacterEncoding("utf-8"); %>
        <%@ include file="common.jsp" %>

            <%
	String id=request.getParameter("id");

    query="delete from depart where id="+id+";";
	stmt.executeUpdate(query);

	stmt.close();
	conn.close();

	out.print("<script>location.href='ad_depart.jsp'</script>");
%>
