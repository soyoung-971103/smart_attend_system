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
<link href="my/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="my/css/style.css" rel="stylesheet" type="text/css" />

<link href="my/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<!-- datatable.net -->

<link href="my/css/my.css" rel="stylesheet" type="text/css">

</head>

<body class="adminbody">

	<div id="main">
		<%@include file="main_menu.jsp"%>

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
									<li class="breadcrumb-item active">학생 정보</li>
								</ol>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>

					<div class="row">

						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
							<div class="card mb-3">
								<div class="card-header mycolor3" style="padding: 10px">
									<h3>
										<i class="fa fa-table"></i> 학생
									</h3>
								</div>

								<div class="card-body" style="padding: 10px">

									<script>
										function find_text() {
											if (!form1.text1.value)
												form1.action = "/member/lists/page";
											else
												form1.action = "/member/lists/text1/"
														+ form1.text1.value
														+ "/page";
											form1.submit();
										}
									</script>

									<form method="post" action="student-search.do">
										<div class="row" style="margin-bottom: 5px">
											<div class="col-auto" align="left">
												<div class="form-inline">
													<div class="input-group input-group-sm">
														<div class="input-group-prepend">
															<span class="input-group-text">이름</span>
														</div>
														<input type="text" name="text1" size="10" value=""
															class="form-control"
															onKeydown="if (event.keyCode == 13) { find_text(); }">
														<div class="input-group-append">
															<button class="btn btn-sm mycolor1" type="button"
																onClick="find_text();">검색</button>
														</div>
													</div>
												</div>
											</div>
											<div class="col" align="right">
												<a href="student-studentnew.do" class="btn btn-sm mycolor1">추가</a>
											</div>
										</div>
									</form>

									<table
										class="table table-bordered table-hover table-responsive-sm mytable"
										style="width: 100%">
										<thead>
											<tr class="mycolor1">
												<th>학과</th>
												<th>학년</th>
												<th>반</th>
												<th>학번</th>
												<th>이름</th>
												<th>핸드폰</th>
												<th>성별</th>
												<th>상태</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
											<c:forEach var="dto" items="${ studentlist }">
												<tr>
													<td>${ dto.depart.name }</td>
													<td>${ dto.grade }</td>
													<td>${ dto.student_class }</td>
													<td>${ dto.schoolno }</td>
													<td>${ dto.name }</td>
													<td>${ dto.phone }</td>
													<c:if test="${dto.sex eq 0 }">
														<td>남</td>
													</c:if>
													<c:if test="${dto.sex eq 1 }">
														<td>여</td>
													</c:if>
													<td>${ dto.state }</td>
													<td><a href="student-detail.do?id=${ dto.id }"
														class="btn btn-xs btn-outline-primary">수정</a> <a
														href="student-delete.do?id=${ dto.id }"
														class="btn btn-xs btn-outline-danger"
														onClick="return confirm('삭제할까요 ?');">삭제</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

									<nav>
										<!-- <ul class="pagination pagination-sm justify-content-center">
										<li class="page-item"><a class="page-link" href="#">◀</a></li>
										<li class="page-item"><a class="page-link" href="#">◁</a></li>
										<li class="page-item"><a class="page-link" href="#">2</a></li>
										<li class="page-item"><a class="page-link" href="#">3</a></li>
										<li class="page-item active"><span class="page-link" style="background-color:steelblue">4</span></li>
										<li class="page-item"><a class="page-link" href="#">5</a></li>
										<li class="page-item"><a class="page-link" href="#">6</a></li>
										<li class="page-item"><a class="page-link" href="#">▷</a></li>
										<li class="page-item"><a class="page-link" href="#">▶</a></li>
									</ul> -->
										${pagination}
									</nav>

								</div>
								<!-- card body end -->
							</div>
							<!-- card end -->
						</div>

					</div>
					<!-- row end -->
					<%@ include file="main_bottom.jsp"%>
