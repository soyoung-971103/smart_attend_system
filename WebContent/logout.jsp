<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% session.invalidate(); 
	Cookie[] cookies = request.getCookies() ;
    
    if(cookies != null){
        for(int i=0; i < cookies.length; i++){
             
            // 쿠키의 유효시간을 0으로 설정하여 만료시킨다
            cookies[i].setMaxAge(0) ;
             
            // 응답 헤더에 추가한다
            response.addCookie(cookies[i]) ;
        }
    }
    response.sendRedirect("login.jsp");    
	%>  
</body>
</html>