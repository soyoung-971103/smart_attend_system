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
							<h1 class="main-title float-left">교무처</h1>
							<ol class="breadcrumb float-right">
								<li class="breadcrumb-item">Home</li>
								<li class="breadcrumb-item">직원</li>
								<li class="breadcrumb-item active">학과 및 부서</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div class="card mb-3">
							<div class="card-header mycolor3" style="padding:10px">
								<h3><i class="fa fa-table"></i> 학과 및 부서 수정</h3>
							</div>
								
							<div class="card-body" style="padding:10px">

								<form name="form1" method="post" action="depart-update.do">

								<table class="table table-bordered mytable-centermiddle" style="width:100%;">
									<tr>
										<td class="mycolor2">번호</td>
										<td>
											<div class="form-inline">
												<input type="text" name="id" value="${depart.id }" class="form-control form-control-sm" style="width:50px">
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">학과/부서명</td>
										<td>
											<div class="form-inline">
												<input type="text" name="name" value="${depart.name }" class="form-control form-control-sm" required>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">반 개수</td>
										<td>
											<div class="form-inline">
											<select name="classnum" class="form-control form-control-sm" style="width:120px">
											<c:if test="${depart.classnum == 0 }">
												<option value="0" selected>없음</option>
												<option value='1'>1</option>
												<option value='2' >2</option>
												<option value='3'>3</option>
												<option value='4'>4</option>
												<option value='5'>5</option>
												<option value='6'>6</option>
											</c:if>
											<c:if test="${depart.classnum == 1 }">
												<option value="0">없음</option>
												<option value='1' selected>1</option>
												<option value='2' >2</option>
												<option value='3'>3</option>
												<option value='4'>4</option>
												<option value='5'>5</option>
												<option value='6'>6</option>
											</c:if>
											<c:if test="${depart.classnum == 2 }">
												<option value="0">없음</option>
												<option value='1'>1</option>
												<option value='2' selected>2</option>
												<option value='3'>3</option>
												<option value='4'>4</option>
												<option value='5'>5</option>
												<option value='6'>6</option>
											</c:if>
											<c:if test="${depart.classnum == 3 }">
												<option value="0">없음</option>
												<option value='1'>1</option>
												<option value='2'>2</option>
												<option value='3' selected>3</option>
												<option value='4'>4</option>
												<option value='5'>5</option>
												<option value='6'>6</option>
											</c:if>
											<c:if test="${depart.classnum == 4 }">
												<option value="0">없음</option>
												<option value='1'>1</option>
												<option value='2'>2</option>
												<option value='3'>3</option>
												<option value='4' selected>4</option>
												<option value='5'>5</option>
												<option value='6'>6</option>
											</c:if>
											<c:if test="${depart.classnum == 5 }">
												<option value="0">없음</option>
												<option value='1'>1</option>
												<option value='2'>2</option>
												<option value='3'>3</option>
												<option value='4'>4</option>
												<option value='5' selected>5</option>
												<option value='6'>6</option>
											</c:if>
											<c:if test="${depart.classnum == 6 }">
												<option value="0">없음</option>
												<option value='1'>1</option>
												<option value='2'>2</option>
												<option value='3'>3</option>
												<option value='4'>4</option>
												<option value='5'>5</option>
												<option value='6' selected>6</option>
											</c:if>
											</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">학제</td>
										<td>
											<div class="form-inline">
											<select name="gradesystem" class="form-control form-control-sm" style="width:80px">
											<c:if test="${depart.gradesystem == 2 }">
												<option value="0" ></option>
												<option value='2' selected>2년제</option>
												<option value='3'>3년제</option>
											</c:if>
											<c:if test="${depart.gradesystem == 3 }">
												<option value="0" ></option>
												<option value='2'>2년제</option>
												<option value='3' selected>3년제</option>
											</c:if>
											</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">학과/부서 약어</td>
										<td>
											<div class="form-inline">
												<input type="text" name="abbreviation" value="${depart.abbreviation }" class="form-control form-control-sm" required>
											</div>
										</td>
									</tr>
								</table>

								<div align="center">
									<input type="submit" value="저장" class="btn btn-sm mycolor1">&nbsp;
									<input type="button" value="이전화면" class="btn btn-sm mycolor1" onclick="history.back();">
								</div>
								<br>※ [번호]는 반드시 중복된 값이 없어야 합니다.

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