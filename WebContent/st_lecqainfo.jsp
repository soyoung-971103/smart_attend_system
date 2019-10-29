<!-------------------------------------------------------------------------------->	
<!-- 프로그램 : 인덕대학교 컴퓨터소프트웨어학과 전자출석 Demo                              -->
<!--                                                                                                                  -->
<!-- 소속 : 인덕대학교  컴퓨터소프트웨어학과  창업동아리 겜지기                              -->
<!-- 교수 : 윤형태 (2019.5 -        )                                                                         -->
<!-- 학생 : 유소영(3), 김해리(3), 이민호(2), 김진혁(2)                                              -->
<!-------------------------------------------------------------------------------->	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="kr">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>인덕대학교 전자출석 Demo (겜지기)</title>

	<link rel="shortcut icon" href="my/images/favicon.ico">

	<!-- css 선언부 ---------------------------------------------------------------->
	<link href="my/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="my/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="my/css/style.css" rel="stylesheet" type="text/css" />		

	<link href="my/css/dataTables.bootstrap4.min.css" rel="stylesheet">	<!-- datatable.net -->

	<link href="my/css/my.css" rel="stylesheet" type="text/css">
		
</head>

<body class="adminbody">

<div id="main">

	<!--상단 메뉴 시작 -->
	<div class="headerbar">

        <div class="headerbar-left">
			<a href="index.html" class="logo"><img src="my/images/induk_logo.png"> <span>전자출석 Demo</span></a>
        </div>

        <nav class="navbar-custom">
			<ul class="list-inline float-right mb-0">
				<li class="list-inline-item dropdown notif">
					<a class="nav-link dropdown-toggle nav-user" data-toggle="dropdown" href="#" role="button" ariaaspopup="false" aria-expanded="false">
						<img src="my/images/avatars/admin.png" alt="Profile image" class="avatar-rounded">
					</a>
					<div class="dropdown-menu dropdown-menu-right profile-dropdown">
						<div class="dropdown-item noti-title">
							<h5 class="text-overflow"><small>Hello, admin</small> </h5>
						</div>
						<a href="#" class="dropdown-item notify-item">
							<i class="fa fa-power-off"></i> <span>Logout</span>
						</a>
					</div>
				</li>
			</ul>

			<ul class="list-inline menu-left mb-0">
				<li class="float-left">
					<button class="button-menu-mobile open-left">
						<i class="fa fa-fw fa-bars"></i>
					</button>
				</li>                        
			</ul>
        </nav>

	</div>
	<!--상단 메뉴 끝 -->
	
 
	<!-- 좌측 Sidebar 메뉴 시작-->
	<%@ include file="main_menu.jsp" %>
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
							<h1 class="main-title float-left">교무처</h1>
							<ol class="breadcrumb float-right">
								<li class="breadcrumb-item">Home</li>
								<li class="breadcrumb-item">학생</li>
								<li class="breadcrumb-item active">교과목 문의</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div class="card mb-3">
							<div class="card-header mycolor3" style="padding:10px">
								<h3><i class="fa fa-table"></i>교과목 문의</h3>
							</div>
								
							<div class="card-body" style="padding:10px">

								<div class="row" style="margin-bottom:10px">
									<div class="col-auto" align="left">
										<h6>&nbsp;<font color="gray">2019년 1학기</font></h6>
									</div>
									<div class="col" align="right">
										<h6>&nbsp;<font color="gray">${uid} ${name}</font>&nbsp;</h6>
									</div>
								</div>

								<form name="form1" method="post" action="">

								<table class="table table-bordered mytable-centermiddle" style="width:100%;">
									<tr>
										<td class="mycolor2" style="vertical-align:middle">교과목</td>
										<td align="left">${dto.lecture.subject.name}</td>
									</tr>
									<tr>
										<td class="mycolor2" style="vertical-align:middle">교수님</td>
										<td align="left">${dto.teacher.name}</td>
									</tr>
									<tr>
										<td class="mycolor2" style="vertical-align:middle">학생</td>
										<td align="left">${dto.student.depart.name}<br> ${dto.student.grade}학년 ${dto.student.student_class}반 ${dto.student.name}<br>
											<i class="fa fa-phone fa-x2">
												${fn:substring(dto.student.getPhone(),0,3)} -
												${fn:substring(dto.student.getPhone(),3,7)} -
												${fn:substring(dto.student.getPhone(),7,11)}
											</i>
										</td>
									</tr>
								</table>

								<table class="table table-bordered mytable-centermiddle" style="width:100%;">
									<tr>
										<td class="mycolor2" style="vertical-align:middle">날짜</td>
										<td align="left">
											<div class="form-inline">
												<input type="text" name="qawriteday" size="10" value="${dto.day}" class="form-control form-control-sm" readonly>
											</div>
										</td>
										<td class="mycolor2" style="vertical-align:middle">상태</td>
										<td align="left" style="text-align:center;vertical-align:middle">
											<c:if test="${dto.qaanswer ne null}">
												답변
											</c:if>
										</td>
									</tr>
									<tr>
										<td class="mycolor2" style="vertical-align:middle">제목</td>
										<td align="left" colspan="3">
											<c:choose>
												<c:when test="${dto.qaanswer ne null}">
													<input type="text" name="qatitle" value="${dto.qatitle}" class="form-control form-control-sm" readonly>
												</c:when>
												<c:otherwise>
													<input type="text" name="qatitle" value="${dto.qatitle}" class="form-control form-control-sm">
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td class="mycolor2" style="vertical-align:middle">질문</td>
										<td align="left" colspan="3">
											<c:choose>
												<c:when test="${dto.qaanswer ne null}">
													<textarea name="qatxt1" rows="5" class="form-control form-control-sm" readonly>${dto.qaask}</textarea>
												</c:when>
												<c:otherwise>
													<textarea name="qatxt1" rows="5" class="form-control form-control-sm">${dto.qaask}</textarea>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td class="mycolor2" style="vertical-align:middle" readonly>답변</td>
										<td align="left" colspan="3">

											<textarea name="qatxt2" rows="5" class="form-control form-control-sm" readonly>${dto.getQaanswer()}</textarea>

										</td>
									</tr>

								</table>

								<div align="center">									
									<input type="button" value="이전화면" class="btn btn-sm mycolor1" onclick="history.back();">
								</div>

								</form>


							</div>		<!-- card body end -->
						</div>		<!-- card end -->
					</div>
						
				</div>	<!-- row end -->
<!------------------------------------------------------------------------------>
<!-- 내용 끝 -->
<!------------------------------------------------------------------------------>
			</div>
		</div>
	</div>

	<!-- 하단 정보 -->
	<footer class="footer">
		<span class="text-right">	Copyright <a target="_blank" href="#">Induk University</a></span>
		<span class="float-right">Programmed by <a target="_blank" href="#"><b>Gamejigi</b></a></span>
	</footer>

</div>

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