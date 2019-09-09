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
				<%@ page import="java.util.*" %>
          		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
								
				<div class="row">
					<div class="col-xl-12">
						<div class="breadcrumb-holder">
							<h1 class="main-title float-left">컴퓨터소프트웨어학과</h1>
							<ol class="breadcrumb float-right">
								<li class="breadcrumb-item">Home</li>
								<li class="breadcrumb-item">조교</li>
								<li class="breadcrumb-item active">과목별 교과목</li>
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
										<h3><i class="fa fa-table"></i> 과목별 출석부</h3>
									</div>
									<div class="col" align="right">
									</div>
								</div>
							</div>
							<div class="card-body" style="padding:10px">

								<script>
									function find_text()
									{
										form1.action="lecture-sublecture.do?sel1=" + form1.sel1.value+"&sel2=" + form1.sel2.value+"&sel3=" + form1.sel3.value+"&sel4=" + form1.sel4.value+"&sel5=" + form1.sel5.value;
										form1.submit();
									}
								</script>

								<form name="form1" action="" method="post">
								<div class="row" style="margin-bottom:3px">
									<div class="col" align="left">
										<div class="form-inline" style="line-height:0.2rem">

											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text">학기</span>
												</div>
												<div class="input-group-append">
													<select name="sel1" class="form-control form-control-sm" >
														<c:forEach var="i" begin="1" end="5">
															<c:choose>
																<c:when test="${sel1 == 2020-i}">
																	<option value=${2020-i } selected>${2020-i }</option>
																</c:when>	
																<c:otherwise>
																	<option value=${2020-i }>${2020-i }</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
													&nbsp;
													<select name="sel2" class="form-control form-control-sm" >
														<c:forEach var="i" begin="1" end="2">
															<c:choose>
																<c:when test="${sel2 == i}">
																	<option value=${i } selected>${i }학기</option>
																</c:when>	
																<c:otherwise>
																	<option value=${i }>${i }학기</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
												</div>
											</div>
											&nbsp;
											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text">학년</span>
												</div>
												<div class="input-group-append">
													<select name="sel3" class="form-control form-control-sm" >
														<c:forEach var="i" begin="1" end="4">
															<c:choose>
																<c:when test="${sel3 == i}">
																	<option value=${i } selected>${i }학년</option>
																</c:when>	
																<c:otherwise>
																	<option value=${i }>${i }학년</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
													&nbsp;
													<select name="sel4" class="form-control form-control-sm" >
														<c:choose>
															<c:when test="${sel4 == '0'}">	
																<option value='0' selected>전체</option>
															</c:when>	
															<c:otherwise>
																<option value='0'>전체</option>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${sel4 == 'A'}">	
																<option value='A' selected>A</option>
															</c:when>	
															<c:otherwise>
																<option value='A'>A</option>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${sel4 == 'B'}">	
																<option value='B' selected>B</option>
															</c:when>	
															<c:otherwise>
																<option value='B'>B</option>
															</c:otherwise>
														</c:choose>
													</select>
												</div>
											</div>
											&nbsp;
											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text">과목</span>
												</div>
												<div class="input-group-append">
													<select name="sel5" class="form-control form-control-sm" >
														<c:forEach var="depart" items="${depart_list}">															
															<c:choose>
																<c:when test="${sel5 == depart.id}">	
																	<option value='${depart.id }' selected>${depart.name }</option>	
																</c:when>	
																<c:otherwise>
																	<option value='${depart.id }'>${depart.name }</option>	
																</c:otherwise>
															</c:choose>
															
														</c:forEach>
													</select>
												</div>
												&nbsp<input type="button" value= "검색" onClick="javascript:find_text();" class="btn btn-sm btn-primary">
											</div>
										</div>
									</div>
								</div>
								</form>

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
										<th style="vertical-align:middle">보강</th>
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
										<th>학과</th>
										<th>학년</th>
										<th>학번</th>
										<th>학적</th>
										<th>이름</th>
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
								<tbody>
									<c:forEach var="sublec" items="${list}">
									<tr>
										<td class="mycolor3">${sublec.student.depart.name }</td>
										<td class="mycolor3">${sublec.student.grade }</td>
										<td class="mycolor3">${sublec.student.schoolno }</td>
										<td class="mycolor3">${sublec.student.state }</td>
										<td class="mycolor3">${sublec.student.name }</td>
										<td class="mycolor4"><font color="blue"><b>${sublec.ilate }</b></font></td>
										<td class="mycolor4"><font color="red"><b>${sublec.ixhour }</b></font></td>
										<td class="mycolor4"><b>${sublec.itotal }</b></td>
										<c:forEach var="i" begin="1" end="30">
											<c:choose>
												<c:when test="${sublec.hn[i] == 0}">
													<td><i class="fa fa-circle-o fa-1x"></i></td>
												</c:when>												
												<c:when test="${sublec.hn[i] == 1}">
													<td><i class="text-primary fa fa-times-circle-o fa-1x"></i></td>
												</c:when>											
												<c:when test="${sublec.hn[i] == 2}">
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