<!-------------------------------------------------------------------------------->
<!-- 프로그램 : 인덕대학교 컴퓨터소프트웨어학과 전자출석 Demo                              -->
<!--                                                                                                                  -->
<!-- 소속 : 인덕대학교  컴퓨터소프트웨어학과  창업동아리 겜지기                              -->
<!-- 교수 : 윤형태 (2019.5 -        )                                                                         -->
<!-- 학생 : 유소영(3), 김해리(3), 이민호(2), 김진혁(2)                                              -->
<!-------------------------------------------------------------------------------->	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*, java.io.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
String text1 = request.getParameter("text1");
%>
<!DOCTYPE html>
<html lang="kr">
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>인덕대학교 전자출석 Demo (겜지기)</title>

	<link rel="shortcut icon" href="my/images/favicon.ico">

	<link href="my/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="my/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="my/css/style.css" rel="stylesheet" type="text/css" />		

	<link href="my/css/dataTables.bootstrap4.min.css" rel="stylesheet">	<!-- datatable.net -->

	<link href="my/css/my.css" rel="stylesheet" type="text/css">
	
	

</head>

<body class="adminbody">

<div id="main">

	<%@include file="main_menu.jsp" %>

    <div class="content-page">
	    <div class="content">
			<div class="container-fluid">
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
                        <li class="breadcrumb-item active">교수</li>
                     </ol>
                     <div class="clearfix"></div>
                  </div>
               </div>
            </div>

            <div class="row">

               <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                  <div class="card mb-3">
                     <div class="card-header mycolor3" style="padding:10px">
                        <h3><i class="fa fa-table"></i> 교수</h3>
                     </div>

                     <div class="card-body" style="padding:10px">

                        <script>
                           function find_text()
                           {
                              if (!form1.text1.value)
                                 form1.action="/member/lists/page";
                              else
                                 form1.action="/member/lists/text1/" + form1.text1.value+"/page";
                              form1.submit();
                           }
                        </script>
                        <form name="form1" method="post" action="TeacherInquiry">
                        <div class="row" style="margin-bottom:5px">
                           <div class="col-auto" align="left">
                              <div class="form-inline">
                                 <div class="input-group input-group-sm">
                                    <div class="input-group-prepend">
                                       <span class="input-group-text">이름</span>
                                    </div>
                                    <input type="text" name="text1" size="10" value="${text1}" class="form-control">
                                    <div class="input-group-append">
                                       <button class="btn btn-sm mycolor1" type="button">검색</button>
                                    </div>
                                 </div>
                              </div>
                           </div>
                           <div class="col" align="right">
                              <a href="teacher-inputdata.do" class="btn btn-sm mycolor1">추가</a>
                           </div>
                        </div>
                        </form>

                        <table class="table table-bordered table-hover table-responsive-sm mytable" style="width:100%;" id="example">
                           <tr class="mycolor1">
                              <th>학과</th>
                              <th>구분</th>
                              <th>이름</th>
                              <th>전화</th>
                              <th>핸드폰</th>
                              <th>이메일</th>
                              <th width="95"></th>
                           </tr>
                        <c:forEach var="item" items="${alMember}">
                           <tr>
                              <td>
                                 ${item.getDepart_id().getName()}과
                              </td>
                              <td>
                              	${item.getKind() }
                              </td>
                              <td>${item.getName() }</td>
                              <td>${item.getTel() }</td>
                              <td>${item.getPhone() }</td>
                              <td>${item.getEmail() }</td>
                              <td>
                                 <a href="teacher-info.do?id=${item.id }" class="btn btn-xs btn-outline-primary">수정</a>
                                 <a href="teacher-delete.do?id=${item.id }" class="btn btn-xs btn-outline-danger" onClick="return confirm('삭제할까요 ?');">삭제</a>
                              </td>
                           </tr>
                        </c:forEach>
                        </table>
						<nav>
							<ul class="pagination pagination-sm justify-content-center">
								<li class="page-item"><a class="page-link" href="#">◀</a></li>
								<li class="page-item"><a class="page-link" href="#">◁</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item active"><span class="page-link" style="background-color:steelblue">4</span></li>
								<li class="page-item"><a class="page-link" href="#">5</a></li>
								<li class="page-item"><a class="page-link" href="#">6</a></li>
								<li class="page-item"><a class="page-link" href="#">▷</a></li>
								<li class="page-item"><a class="page-link" href="#">▶</a></li>
							</ul>
						</nav>
                        <%
                           String nurl = "TeacherController?text1="+text1;
                           //out.println(pagination(npage, count, nurl));
                        %>

                     </div>      <!-- card body end -->
                  </div>      <!-- card end -->
               </div>

            </div>   <!-- row end -->

<!---------------------------------------- --------------------->
<%@ include file="main_bottom.jsp" %>
