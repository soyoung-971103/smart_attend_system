    <!-------------------------------------------------------------------------------->
        <!-- 프로그램 : 인덕대학교 컴퓨터소프트웨어학과 전자출석 Demo -->
        <!-- -->
        <!-- 소속 : 인덕대학교 컴퓨터소프트웨어학과 창업동아리 겜지기 -->
        <!-- 교수 : 윤형태 (2019.5 - ) -->
        <!-- 학생 : 유소영(3), 김해리(3), 이민호(2), 김진혁(2) -->
        <!-------------------------------------------------------------------------------->
        <%@ page contentType="text/html;charset=utf-8" %>
        <%@ page import="java.util.*, java.sql.*, java.io.*" %>
            <% request.setCharacterEncoding("utf-8"); %>
        <%@ include file="common.jsp" %>
        <%@ include file="main_top.jsp" %>

        <%
        	String id=request.getParameter("id");

        	query="select * from depart where id="+id+";";
	        rs = stmt.executeQuery(query);

        	rs.next();
	        String name =rs.getString("name");
	        String classnum =rs.getString("classnum");
	        String gradesystem =rs.getString("gradesystem");
        %>
        <!------------------------------------------------------------------------------>
        <!-- 내용 시작 -->
        <!------------------------------------------------------------------------------>
        <div class="row">
        <div class="col-xl-12">
        <div class="breadcrumb-holder">
        <h1 class="main-title float-left">교무처</h1>
        <ol class="breadcrumb float-right">
        <li class="breadcrumb-item">Home</li>
        <li class="breadcrumb-item">직원</li>
        <li class="breadcrumb-item active">학과 및 부서</li>
        </ol>
        <div class="clearfix"></div>
        </div>
        </div>
        </div>

        <div class="row">

        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="card mb-3">
        <div class="card-header mycolor3" style="padding:10px">
        <h3><i class="fa fa-table"></i> 학과 및 부서 수정</h3>
        </div>

        <div class="card-body" style="padding:10px">

        <form name="form1" method="post" action="ad_departUpdate.jsp">
            <input type="hidden" name="id" value="<%=id %>">

        <table class="table table-bordered mytable-centermiddle" style="width:100%;">
        <tr>
        <td class="mycolor2">번호</td>
        <td>
        <div class="form-inline">
            <a class="form-control form-control-sm" style="width:50px"><%=id %></a>
        </div>
        </td>
        </tr>
        <tr>
        <td class="mycolor2">학과/부서명</td>
        <td>
        <div class="form-inline">
        <input type="text" name="name" value="<%=name %>" class="form-control form-control-sm" required>
        </div>
        </td>
        </tr>
        <tr>
        <td class="mycolor2">반 개수</td>
        <td>
        <div class="form-inline">
        <select name="classnum" class="form-control form-control-sm" style="width:120px">
        <option value="0"  <%if(classnum.equals("0")) out.print("selected"); %>  >없음</option>
        <option value="1"  <%if(classnum.equals("1")) out.print("selected"); %>  >1</option>
        <option value="2"  <%if(classnum.equals("2")) out.print("selected"); %> >2</option>
        <option value="3"  <%if(classnum.equals("3")) out.print("selected"); %>  %> >3C</option>
        <option value="4"  <%if(classnum.equals("4")) out.print("selected");  %> >4</option>
        <option value="5"  <%if(classnum.equals("5")) out.print("selected");  %> >5</option>
        <option value="6"  <%if(classnum.equals("6")) out.print("selected");  %> >6</option>
        </select>
        </div>
        </td>
        </tr>
        <tr>
        <td class="mycolor2">학제</td>
        <td>
        <div class="form-inline">
        <select name="gradesystem" class="form-control form-control-sm" style="width:80px">
        <option value="0"<% if(gradesystem.equals("0")) out.print("selected");  %>></option>
        <option value='2'<% if(gradesystem.equals("2")) out.print("selected");  %>>2년제</option>
        <option value='3'<% if(gradesystem.equals("3")) out.print("selected");  %>>3년제</option>
        </select>
        </div>
        </td>
        </tr>
        </table>

        <div align="center">
        <input type="submit" value="저장" class="btn btn-sm mycolor1">&nbsp;
        <input type="button" value="이전화면" class="btn btn-sm mycolor1" onclick="history.back();">
        </div>
        <br>※ [번호]는 반드시 중복된 값이 없어야 합니다.

        </form>


        </div> <!-- card body end -->
        </div> <!-- card end -->
        </div>

        </div> <!-- row end -->
        <!------------------------------------------------------------------------------>
        <!-- 내용 끝 -->
        <!------------------------------------------------------------------------------>
        <%@ include file="main_bottom.jsp" %>

            <%
			rs.close();
			stmt.close();
			conn.close();
		%>