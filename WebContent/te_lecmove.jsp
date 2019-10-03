<!-------------------------------------------------------------------------------->	
<!-- 프로그램 : 인덕대학교 컴퓨터소프트웨어학과 전자출석 Demo                              -->
<!--                                                                                                                  -->
<!-- 소속 : 인덕대학교  컴퓨터소프트웨어학과  창업동아리 겜지기                              -->
<!-- 교수 : 윤형태 (2019.5 -        )                                                                         -->
<!-- 학생 : 유소영(3), 김해리(3), 이민호(2), 김진혁(2)                                              -->
<!-------------------------------------------------------------------------------->	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
							<h1 class="main-title float-left">컴퓨터소프트웨어학과</h1>
							<ol class="breadcrumb float-right">
								<li class="breadcrumb-item">Home</li>
								<li class="breadcrumb-item">교수</li>
								<li class="breadcrumb-item active">휴보강</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div class="card mb-3">
							<div class="card-header mycolor3" style="padding:10px">
								<div class="row">
									<div class="col" align="left">
										<h3><i class="fa fa-table"></i> 휴보강 </h3>
									</div>
									<div class="col" align="right">
										<h3>교수님1</h3>
									</div>
								</div>
							</div>
							<div class="card-body" style="padding:10px">

								<form name="form1" action="" method="post">
								<div class="row" style="margin-bottom:3px">
									<div class="col" align="left">
									</div>
									<div class="col" align="right">
										<a href="te_lecmovenorm.html" class="btn btn-sm mycolor1">신청</a>
									</div>
								</div>

								<table class="table table-bordered table-responsive-sm mytable" style="width:100%;">
									<tr class="mycolor1">
										<td>학과</td>
										<td>교수님</td>
										<td>교과목</td>
										<td>학년/반</td>
										<td>휴강날짜</td>
										<td>휴강교시</td>
										<td>보강날짜</td>
										<td>보강교시</td>
										<td>보강강의실</td>
										<td>처리상태</td>
										<td>학과장</td>									
									</tr>
									<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
									<c:forEach var="dto" items="${ lectureday }">
									<tr>
										<td>${ dto.depart.name }</td>
										<td>${ dto.teacher.name }</td>
										<td>${ dto.subject.name }</td>
										<td>${ dto.subject.grade }학년/${ dto.lecture._class }반</td>
										<td class="mycolor4">${ dto.normdate }</td>
										<td class="mycolor4"><c:if test="${dto.normstart ne 0}"><c:forEach var="i" step="1" begin="${ dto.normstart }" end="${ dto.normstart + dto.normhour -2 }"> ${i }, </c:forEach>${ dto.normstart + dto.normhour -1 } 교시</c:if></td>
										<td class="mycolor3">${ dto.restdate }</td>
										<td class="mycolor3"><c:if test="${dto.reststart ne 0}"><c:forEach var="i" step="1" begin="${ dto.reststart }" end="${ dto.reststart + dto.resthour -2 }"> ${i }, </c:forEach>${ dto.reststart + dto.resthour -1 } 교시</c:if></td>
										<td class="mycolor3">${ dto.room.name }</td>
										<td><b>신청</b></td>
										<td>					<!-- 0 신청 or 1 취소 or 2 학과장승인 or 3 반려 or 4 최종승인 -->
											<a href="teacher-bogangDelete.do?id=${ dto.id }" class="btn btn-xs btn-outline-danger">취소</a>
										</td>
									</tr>
									</c:forEach>

								</table>
								</form>

								<nav>
									<ul class='pagination pagination-sm justify-content-center'>
										<li class='page-item'><a class="page-link" href="#">◀</a></li>
										<li class='page-item'><a class="page-link" href="#">◁</a></li>
										<li class='page-item'><a class="page-link" href="#">2</a></li>
										<li class='page-item'><a class="page-link" href="#">3</a></li>
										<li class='page-item active'><span class='page-link' style='background-color:steelblue'>4</span></li>
										<li class='page-item'><a class="page-link" href="#">5</a></li>
										<li class='page-item'><a class="page-link" href="#">6</a></li>
										<li class='page-item'><a class="page-link" href="#">▷</a></li>
										<li class='page-item'><a class="page-link" href="#">▶</a></li>
									</ul>
								</nav>

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