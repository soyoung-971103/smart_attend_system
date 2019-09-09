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
	<%@ include file="main_menu.jsp" %>
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
								<li class="breadcrumb-item active">출석부</li>
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
										<h3><i class="fa fa-table"></i>출석부</h3>
									</div>
								</div>
							</div>
							<div class="card-body" style="padding:10px">

								<div class="row">
									<div class="col-auto" align="left">
										<h6>&nbsp;<font color="gray">2019년 1학기</font></h6>
									</div>
									<div class="col" align="right">
										<h6>&nbsp;<font color="gray">${student.schoolno} ${student.name }</font>&nbsp;</h6>
									</div>
								</div>

								<table class="table table-bordered table-hover table-responsive-sm mytable" style="width:100%;font-size:8pt;">
								<thead>
									<tr class="mycolor1">
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th>주</th>
										<th colspan="2">1</th>
										<th colspan="2">2</th>
										<th colspan="2">3</th>
										<th colspan="2">4</th>
										<th colspan="2">5</th>
										<th colspan="2">6</th>
										<th colspan="2">7</th>
										<th colspan="2">8</th>
										<th colspan="2">9</th>
										<th colspan="2">10</th>
										<th colspan="2">11</th>
										<th colspan="2">12</th>
										<th colspan="2">13</th>
										<th colspan="2">14</th>
										<th colspan="2">15</th>
									</tr>
									<tr class="mycolor1" style="line-height:0.7rem">
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th style="vertical-align:middle">월일</th>
										<th colspan="2">3<br>05</th>
										<th colspan="2">3<br>12</th>
										<th colspan="2">3<br>19</th>
										<th colspan="2">3<br>26</th>
										<th colspan="2">4<br>02</th>
										<th colspan="2">4<br>09</th>
										<th colspan="2">4<br>16</th>
										<th colspan="2">4<br>23</th>
										<th colspan="2">4<br>30</th>
										<th colspan="2">5<br>02</th>
										<th colspan="2">5<br>09</th>
										<th colspan="2">5<br>16</th>
										<th colspan="2">5<br>23</th>
										<th colspan="2">5<br>30</th>
										<th colspan="2">6<br>06</th>
									</tr>
									<tr class="mycolor1" style="line-height:0.7rem;">
										<th>&nbsp;</th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th>보강</th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th>4<br>23</th>
										<th>4<br>23</th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th>6<br>06</th>
										<th>6<br>06</th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
									<tr class="mycolor1">
										<th>과목명</th>
										<th>학년</th>
										<th>반</th>
										<th>학점</th>
										<th>시간</th>
										<th>지각</th>
										<th>결석</th>
										<th>점수</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
										<th>3</th>
										<th>4</th>
									</tr>
								</thead>
								<%@ page import="java.util.*,model.BuildingDTO" %>
          						<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
								<tbody>
									<c:forEach var="mylec" items="${list}">
										<tr>
											<td class="mycolor3">${ mylec.departname}</td>
											<td class="mycolor3">${ mylec.grade}</td>
											<td class="mycolor3">A</td>
											<td class="mycolor3">${mylec.ipoint }</td>
											<td class="mycolor3">2</td>
											<td class="mycolor4"><font color="blue"><b>${mylec.ilate }</b></font></td>
											<td class="mycolor4"><font color="red"><b>${mylec.ixhour }</b></font></td>
											<td class="mycolor4"><b>${mylec.itotal }</b></td>
											<c:forEach var="i" begin="1" end="30">
												<c:choose>
													<c:when test="${mylec.hn[i] == 0}">
														<td><i class="fa fa-circle-o fa-1x"></i></td>
													</c:when>												
													<c:when test="${mylec.hn[i] == 1}">
														<td><i class="text-primary fa fa-times-circle-o fa-1x"></i></td>
													</c:when>											
													<c:when test="${mylec.hn[i] == 2}">
														<td><i class="text-danger fa fa-close fa-1x"></i></td>
													</c:when>
													<c:otherwise>
														<td><i class="text-warning fa fa-question fa-1x"></i></td>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</tr>
									</c:forEach>
								</tbody>
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