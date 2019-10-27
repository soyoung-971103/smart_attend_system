<!-------------------------------------------------------------------------------->
<!-- 프로그램 : 인덕대학교 컴퓨터소프트웨어학과 전자출석 Demo                              -->
<!--                                                                                                                  -->
<!-- 소속 : 인덕대학교  컴퓨터소프트웨어학과  창업동아리 겜지기                              -->
<!-- 교수 : 윤형태 (2019.5 -        )                                                                         -->
<!-- 학생 : 유소영(3), 김해리(3), 이민호(2), 김진혁(2)                                              -->
<!-------------------------------------------------------------------------------->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*, java.io.*"%>
<%!String info = null;
	String uid = null;
	String[] infoArry = new String[8];
	int sel1 = 0;
	int sel2 = 0;%>
<!DOCTYPE html>
<%@page import="jdk.internal.org.objectweb.asm.tree.IntInsnNode"%>
<%@page import="model.LecsjDTO"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	System.out.println("1234");
	info = (String) request.getAttribute("info");
	infoArry = info.split("/");
		for(int i=0;i<7;i++){
			System.out.println(i+" : "+infoArry[i]);
		} 
	sel1 = Integer.parseInt(infoArry[5]);
	System.out.println("sel1 : " + sel1);
	sel2 = Integer.parseInt(infoArry[6]);
	System.out.println("sel2 : " + sel2);
%>
<html lang="kr">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>인덕대학교 전자출석 Demo (겜지기)</title>

<link rel="shortcut icon" href="my/images/favicon.ico">

<!-- css 선언부 ---------------------------------------------------------------->
<link href="my/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="my/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="my/css/style.css" rel="stylesheet" type="text/css" />

<link href="my/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<!-- datatable.net -->

<link href="my/css/my.css" rel="stylesheet" type="text/css">

</head>

<body class="adminbody">
	<div id="main">
		<!--상단 메뉴 시작 -->
		<%@include file="main_menu.jsp"%>
		<!-- 좌측 Sidebar 메뉴 끝-->

		<div class="content-page">
			<div class="content">
				<div class="container-fluid">
					<!------------------------------------------------------------------------------>
					<!-- 내용 시작 -->
					<!------------------------------------------------------------------------------>
					<div class="row">
						<div class="col-xl-12">
							<div class="breadcrumb-holder">
								<h1 class="main-title float-left"><%=infoArry[3]%></h1>
								<ol class="breadcrumb float-right">
									<li class="breadcrumb-item">Home</li>
									<li class="breadcrumb-item active">성적조회</li>
								</ol>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>

					<div class="row">

						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
							<div class="card mb-3">
								<div class="card-header mycolor3" style="padding: 10px">
									<div class="row">
										<div class="col" align="left">
											<h3>
												<i class="fa fa-table"></i> 성적 조회
											</h3>
										</div>
									</div>

								</div>

								<div class="card-body" style="padding: 10px">

									<div class="row">
										<div class="col-auto" align="left">
											<h6>
												&nbsp;<font color="gray"><%=infoArry[4] + "년" + sel2 + "학기"%></font>
											</h6>
										</div>
										<div class="col" align="right">
											<h6>
												&nbsp;<font color="gray"><%=infoArry[2] + " " + infoArry[1]%></font>&nbsp;
											</h6>
										</div>
									</div>
									<div class="row" style="margin-bottom: 5px">
										<div class="col" align="right">
											<h6>
												학점: <font color="red"><%=infoArry[0] %></font> &nbsp; 평점: <font color="red"><%=infoArry[7] %></font>&nbsp;
											</h6>
										</div>
									</div>

									<form name="form1" action="lecture-search-ch.do" method="post">
										<div class="row">
											<div class="col" align="left">
												<div class="input-group input-group-sm">
													<div class="input-group-prepend">
														<span class="input-group-text">선택</span>
													</div>
													<div class="input-group-append">
														<select id="sel1" name="sel1" class="form-control form-control-sm">
															<%
																for (int i = 1; i < 4; i++) {
																	if (sel1 == i) {
															%>
															<option value='<%=i%>' selected="selected"><%=i%>학년
															</option>
															<%
																} else {
															%>
															<option value='<%=i%>'><%=i%>학년
															</option>
															<%
																}
																}
															%>
														</select> &nbsp; <select id="sel2" name="sel2"
															class="form-control form-control-sm">
															<%
																if (sel2 == 1) {
															%>
															<option value='1' selected="selected">1학기</option>
															<option value='2'>2학기</option>
															<%
																} else {
															%>
															<option value='1'>1학기</option>
															<option value='2' selected="selected">2학기</option>
															<%
																}
															%>
														</select>
													</div>
													&nbsp;
													<!--<button class="btn btn-sm mycolor1" onClick="submit">검색</button> -->
													<input type="submit" class="btn btn-sm mycolor1" value="검색">
												</div>
											</div>
										</div>
									</form>

									<table
										class="table table-bordered table-hover  table-responsive-sm mytable"
										style="width: 100%;">
										<tr class="mycolor1">
											<td>전공</td>
											<td>필수</td>
											<td>과목코드</td>
											<td>과목명</td>
											<td>학점</td>
											<td>출석</td>
											<td>평소</td>
											<td>중간</td>
											<td>기말</td>
											<td>실습</td>
											<td>총점</td>
											<td>학점</td>
											<td>등급</td>
											<td>재수강</td>
										</tr>
										<tbody>
											<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
											<c:forEach var="dto" items="${ lecturelist }">
												<tr>
													<td>${ dto.ismajor }</td>
													<td>${ dto.ischoice }</td>
													<td>${ dto.code }</td>
													<td>${ dto.subjectName }</td>
													<td>${ dto.subjectIpoint }</td>
													<td>${ dto.iattend }</td>
													<td>${ dto.inormal }</td>
													<td>${ dto.imiddle }</td>
													<td>${ dto.ilast }</td>
													<td>${ dto.ipractice }</td>
													<td class="mycolor4">${ dto.itotal }</td>
													<td class="mycolor4">${ dto.ipoint }</td>
													<td class="mycolor4">${ dto.igrade }</td>
													<c:if test="${dto.retake eq 0 }">
														<td></td>
													</c:if>
													<c:if test="${dto.retake eq 1 }">
														<td>재수강</td>
													</c:if>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- card body end -->
						</div>
						<!-- card end -->
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- row end -->
	<!------------------------------------------------------------------------------>
	<!-- 내용 끝 -->
	<!------------------------------------------------------------------------------>
	<%@ include file="main_bottom.jsp"%>
	<!-- js 선언부 ----------------------------------------------------------------->
	<script src="my/js/jquery.min.js"></script>
	<script src="my/js/moment.min.js"></script>

	<script src="my/js/popper.min.js"></script>
	<script src="my/js/bootstrap.min.js"></script>

	<script src="my/js/detect.js"></script>
	<script src="my/js/fastclick.js"></script>
	<script src="my/js/jquery.blockUI.js"></script>
	<script src="my/js/jquery.nicescroll.js"></script>

	<script src="my/js/pikeadmin.js"></script>

	<script src="my/js/jquery.dataTables.min.js"></script>
	<script src="my/js/dataTables.bootstrap4.min.js"></script>
</body>
</html>