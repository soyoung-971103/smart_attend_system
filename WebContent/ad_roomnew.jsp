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
							<h1 class="main-title float-left">교무처</h1>
							<ol class="breadcrumb float-right">
								<li class="breadcrumb-item">Home</li>
								<li class="breadcrumb-item">직원</li>
								<li class="breadcrumb-item active">강의실</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div class="card mb-3">
							<div class="card-header mycolor3" style="padding:10px">
								<h3><i class="fa fa-table"></i> 강의실 입력</h3>
							</div>
								
							<div class="card-body" style="padding:10px">

								<form name="form1" method="post" action="room-register.do">

								<table class="table table-bordered mytable-centermiddle" style="width:100%;">
									<%@ page import="java.util.*,model.BuildingDTO, model.RoomDTO, model.DepartDTO" %>
									<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
									<tr>
										<td class="mycolor2" width="80">건물명</td>
										<td>
											<div class="form-inline">
												<select name="building_id" class="form-control form-control-sm">
													<option value="0" selected></option>
													<c:forEach var="building" items="${listBuilding}">
														<option value='${building.id }'>${building.name }</option>
													</c:forEach>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">층</td>
										<td>
											<div class="form-inline">
												<select name="floor" class="form-control form-control-sm" style="width:70px">
													<option value="0" selected></option>
													<c:forEach var="i" begin="1" end="5">
														<option value='${ i}'>${i }층</option>
													</c:forEach>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">호수</td>
										<td>
											<div class="form-inline">
												<input type="text" name="ho" value="" class="form-control form-control-sm" required>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">소속</td>
										<td>
											<div class="form-inline">
												<select name="depart_id" class="form-control form-control-sm">
													<option value="0" selected></option>
													<c:forEach var="depart" items="${listDepart}">
														<option value='${depart.id }'>${depart.name }</option>		
													</c:forEach>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">명칭</td>
										<td>
											<div class="form-inline">
												<input type="text" name="name" value="" class="form-control form-control-sm" required>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">구분</td>
										<td>
											<div class="form-inline">
												<select name="kind" class="form-control form-control-sm">
													<option value="0" selected></option>
													<option value='1'>컴퓨터실</option>
													<option value='2'>강의실</option>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">면적(m<sup>2</sup>)</td>
										<td>
											<div class="form-inline">
												<input type="text" name="area" value="" class="form-control form-control-sm" style="width:80px">
											</div>
										</td>
									</tr>

								</table>

								<div align="center">
									<input type="submit" value="저장" class="btn btn-sm mycolor1">&nbsp;
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