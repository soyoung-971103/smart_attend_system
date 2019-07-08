<%@ pagelanguage="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*, java.sql.*, java.io.*" %>
<% request.setCharacterEncoding("utf-8"); %>

<%@ page import="mypackage.Greeting" %>

<%
	Greeting gg = new Greeting();
%>

start

<%=gg.say() %>

end