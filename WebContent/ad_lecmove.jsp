
    <!-------------------------------------------------------------------------------->	
<!-- 프로그램 : 인덕대학교 컴퓨터소프트웨어학과 전자출석 Demo                              -->
<!--                                                                                                                  -->
<!-- 소속 : 인덕대학교  컴퓨터소프트웨어학과  창업동아리 겜지기                              -->
<!-- 교수 : 윤형태 (2019.5 -        )                                                                         -->
<!-- 학생 : 유소영(3), 김해리(3), 이민호(2), 김진혁(2)                                              -->
<!-------------------------------------------------------------------------------->	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
	<script>
		history.replaceState({}, null, location.pathname);
	</script>
 
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
								<li class="breadcrumb-item">직원</li>
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
										<h3>직원</h3>
									</div>
								</div>
							</div>
							<div class="card-body" style="padding:10px">

								<form name="form1" action="" method="post">

								<div class="row" style="margin-bottom:3px">
									<div class="col" align="left">
										<div class="form-inline">

											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text">년도</span>
												</div>
												<div class="input-group-append">
													<c:set var="lyear" value="${fn:split(year, '^')}" />
													<select name="sel1" class="form-control form-control-sm">
														<c:forEach var="YEAR" items="${lyear}">
															<c:choose>
																<c:when test="${YEAR eq y}">
																	<option value="${YEAR}" selected>${YEAR}</option>
																</c:when>
																<c:otherwise>
																	<option value="${YEAR}">${YEAR}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
													&nbsp;
													<select name="sel2" class="form-control form-control-sm">
														<c:forEach var="TERM" begin="1" end="2">
															<c:choose>
																<c:when test="${TERM eq t}">
																	<option value="${TERM}" selected>${TERM}</option>
																</c:when>
																<c:otherwise>
																	<option value="${TERM}">${TERM}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
												</div>
												<script>
													function search(number){
														if(number === 1)
														{
															form1.action="ad-lecmove-list.do";
															form1.submit();
														}
													}
													function check(number, no){
														if(number === 1)
														{
															if(confirm('최종 확인 하시겠습니까?')){
																form1.action="ad-lecmove-list.do?no="+no+"&c=1";
																form1.submit();
															}
														}
														if(number === 2){
															if(confirm('반려 하시겠습니까?')){
																form1.action="ad-lecmove-list.do?no="+no+"&c=2";
																form1.submit();
															}
														}
													}
												</script>
												&nbsp;<input type="button" class="btn btn-sm btn-primary" value="검색" onclick="search(1);">
											</div>

										</div>
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
										<td>직원</td>
									</tr>
									<!-- 0 신청 or 1 취소 or 2 학과장승인 or 3 반려 or 4 최종승인 -->
									<c:forEach var="item" items="${dtolist}">
										<tr>
											<td>${item.getDepart()}</td>
											<td>${item.getTeacher_id().getName()}</td>
											<td>${item.getSubject_name()}</td>
											<td>${item.getGrade()}학년/${item.get_class()}반</td>
											<td class="mycolor4">${item.getNormdate()}</td>
											<td class="mycolor4">
												<c:forEach var="i" begin="${item.getNormstart()}" end="${item.getNormhour() + item.getNormstart() - 1}" varStatus="status">
													${i}<c:if test="${i ne status.end}">,</c:if>
												</c:forEach>
												교시
											</td>
											<td class="mycolor3">${item.getRestdate()}</td>
											<td class="mycolor3">
												<c:forEach var="i" begin="${item.getReststart()}" end="${item.getResthour() + item.getReststart() - 1}" varStatus="status">
													${i}<c:if test="${i ne status.end}">,</c:if>
												</c:forEach>
												교시
											</td>
											<td class="mycolor3">${item.getBuildName()} ${item.getRoomName()}</td>
											<td><b>${item.getState()}</b></td>
											<td><!-- <a href="ad-lecmove-lastapp.do?LDID=${item.getId()}" class="btn btn-xs btn-outline-primary">최종승인</a> -->
												<a href="javascript:check(1, ${item.getId()});" class="btn btn-xs btn-outline-primary">최종승인</a>
												<a href="javascript:check(2, ${item.getId()});" class="btn btn-xs btn-outline-danger">반려</a>
											</td>
										</tr>
									</c:forEach>
								</table>
								</form>

								${page }

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