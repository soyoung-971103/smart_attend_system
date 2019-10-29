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
								<li class="breadcrumb-item">학생</li>
								<li class="breadcrumb-item active">메인</li>
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
										<h3><i class="fa fa-table"></i> 공지사항 </h3>
									</div>
								</div>
							</div>
							<div class="card-body" style="padding:10px">

								<div class="row" style="margin-bottom:10px">
									<div class="col-auto" align="left">
										<h6>&nbsp;<font color="gray">2019년 1학기</font></h6>
									</div>
									<div class="col" align="right">
										<h6>&nbsp;<font color="gray">201912001 홍길동</font>&nbsp;</h6>
									</div>
								</div>
								
								<%@ page import="java.util.*,model.NoticeDTO, model.QnaDTO" %>
								<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
								
								<table class="table table-bordered mytable" style="width:100%;">
									<tr class="mycolor1">
										<td>날짜</td>
										<td>제목</td>
										<td width="60"></td>
									</tr>
									<c:forEach var="notice" items="${dtoListNotice}">
										<tr>
											<td>${notice.writeday }</td>
											<td align="left">${notice.title }</td>
											<td>
												<a href="" class="btn btn-xs btn-outline-primary">보기</a>
											</td>
										</tr>
									</c:forEach>
								</table>

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


				<div class="row">
			
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div class="card mb-3">
							<div class="card-header mycolor3" style="padding:10px">
								<div class="row">
									<div class="col" align="left">
										<h3><i class="fa fa-table"></i> 교과목 문의 </h3>
									</div>
									<div class="col" align="right">
										<h3></h3>
									</div>
								</div>
							</div>
							<div class="card-body" style="padding:10px">

								<table class="table table-bordered mytable" style="width:100%;">
									<tr class="mycolor1">
										<td>날짜</td>
										<td>교과목</td>
										<td>제목</td>
										<td>학생</td>
										<td>상태</td>
										<td width="60"></td>
									</tr>
									<c:forEach var="qna" items="${dtoListQna}">
										<tr>
											<td>${qna.day }</td>
											<td>${qna.lecture.subject.name }</td>
											<td>${qna.qatitle }</td>
											<td>${qna.teacher.name }</td>
											<c:choose>
												<c:when test="${qna.c_confirm eq 0 }">
													<td>미답변</td>
												</c:when>
												<c:when test="${qna.c_confirm eq 1 }">
													<td><font color="red">답변</font></td>
												</c:when>
											</c:choose>	
											<td>
												<a href="student-qnainfo.do?id=${qna.id }" class="btn btn-xs btn-outline-primary">보기</a>
											</td>
										</tr>
									</c:forEach>
								</table>

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