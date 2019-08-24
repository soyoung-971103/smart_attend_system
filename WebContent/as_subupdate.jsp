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
								<li class="breadcrumb-item">조교</li>
								<li class="breadcrumb-item active">교과목</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div class="card mb-3">
							<div class="card-header mycolor3" style="padding:10px">
								<h3><i class="fa fa-table"></i> 교과목 : 입력</h3>
							</div>
								
							<div class="card-body" style="padding:10px">
							<%@ page import="java.util.*, model.SubjectDTO, model.DepartDTO" %>
							<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

								<form name="form1" method="post" action="subject-update.do">

								<table class="table table-bordered mytable-centermiddle" style="width:100%;">
									<tr>
										<td class="mycolor2">학과</td>
										<td>
											<div class="form-inline">
												<select name="depart_id" class="form-control form-control-sm">
													<option value="0" selected></option>
													
													<c:forEach var="depart" items="${listDepart}">
													<c:choose>
														<c:when  test="${subject.depart_id eq depart.id }">
														<option value='${depart.id }' selected>${depart.name }</option>	
														</c:when >
														<c:otherwise>
														<option value='${depart.id }'>${depart.name }</option>	
														</c:otherwise>
													</c:choose>
													</c:forEach>
													
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2" width="80">전공구분</td>
										<td>
											<div class="form-inline">
											<c:if test="${subject.ismajor eq '전공일반' }">
												<input type="radio" name="ismajor" value="전공일반" checked>&nbsp;전공일반 &nbsp;&nbsp;
												<input type="radio" name="ismajor" value="전공교양">&nbsp;전공교양 &nbsp;&nbsp;
												<input type="radio" name="ismajor" value="교양">&nbsp;교양
											</c:if>
											<c:if test="${subject.ismajor eq '전공교양' }">
												<input type="radio" name="ismajor" value="전공일반">&nbsp;전공일반 &nbsp;&nbsp;
												<input type="radio" name="ismajor" value="전공교양" checked>&nbsp;전공교양 &nbsp;&nbsp;
												<input type="radio" name="ismajor" value="교양">&nbsp;교양
											</c:if>
											<c:if test="${subject.ismajor eq '교양' }">
												<input type="radio" name="ismajor" value="전공일반">&nbsp;전공일반 &nbsp;&nbsp;
												<input type="radio" name="ismajor" value="전공교양">&nbsp;전공교양 &nbsp;&nbsp;
												<input type="radio" name="ismajor" value="교양" checked>&nbsp;교양
											</c:if>
											<input type="hidden" name="id" value="${subject.id }" class="form-control form-control-sm" required>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">필수/선택</td>
										<td>
											<div class="form-inline">
											<c:if test="${subject.ischoice eq '선택' }">
												<input type="radio" name="ischoice" value="선택" checked>&nbsp;선택 &nbsp;&nbsp;
												<input type="radio" name="ischoice" value="필수">&nbsp;필수
											</c:if>
											<c:if test="${subject.ischoice eq '필수' }">
												<input type="radio" name="ischoice" value="선택">&nbsp;선택 &nbsp;&nbsp;
												<input type="radio" name="ischoice" value="필수" checked>&nbsp;필수
											</c:if>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">과목구분</td>
										<td>
											<div class="form-inline">
											<c:if test="${subject.ispractice eq '실습' }">
												<input type="radio" name="ispractice" value="실습" checked>&nbsp;실습 &nbsp;&nbsp;
												<input type="radio" name="ispractice" value="이론">&nbsp;이론 &nbsp;&nbsp;
												<input type="radio" name="ispractice" value="현장실습">&nbsp;현장실습
											</c:if>
											<c:if test="${subject.ispractice eq '이론' }">
												<input type="radio" name="ispractice" value="실습">&nbsp;실습 &nbsp;&nbsp;
												<input type="radio" name="ispractice" value="이론" checked>&nbsp;이론 &nbsp;&nbsp;
												<input type="radio" name="ispractice" value="현장실습">&nbsp;현장실습
											</c:if>
											<c:if test="${subject.ispractice eq '현장실습' }">
												<input type="radio" name="ispractice" value="실습">&nbsp;실습 &nbsp;&nbsp;
												<input type="radio" name="ispractice" value="이론">&nbsp;이론 &nbsp;&nbsp;
												<input type="radio" name="ispractice" value="현장실습" checked>&nbsp;현장실습
											</c:if>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">과목코드</td>
										<td>
											<div class="form-inline">
												<input type="text" name="code" value="${subject.code}" class="form-control form-control-sm">
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">년도</td>
										<td>
											<div class="form-inline">
												<input type="text" name="yyyy" value="${subject.yyyy}" size="4" maxlength="4" class="form-control form-control-sm">
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">학년</td>
										<td>
											<div class="form-inline">
													<select name="grade" class="form-control form-control-sm">
													<c:if test="${subject.grade eq 1 }">
														<option value='1' selected>1학년</option>
														<option value='2'>2학년</option>
														<option value='3'>3학년</option>
														<option value='4'>4학년</option>
													</c:if>
													<c:if test="${subject.grade eq 2 }">
														<option value='1'>1학년</option>
														<option value='2' selected>2학년</option>
														<option value='3'>3학년</option>
														<option value='4'>4학년</option>
													</c:if>
													<c:if test="${subject.grade eq 3 }">
														<option value='1'>1학년</option>
														<option value='2'>2학년</option>
														<option value='3' selected>3학년</option>
														<option value='4'>4학년</option>
													</c:if>
													<c:if test="${subject.grade eq 4 }">
														<option value='1'>1학년</option>
														<option value='2'>2학년</option>
														<option value='3'>3학년</option>
														<option value='4' selected>4학년</option>
													</c:if>
													</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">학기</td>
										<td>
											<div class="form-inline">
											<c:if test="${subject.term eq 1 }">
												<input type="radio" name="term" value="1" checked>&nbsp;1 학기&nbsp;&nbsp;
												<input type="radio" name="term" value="2">&nbsp;2 학기
											</c:if>
											<c:if test="${subject.term eq 2 }">
												<input type="radio" name="term" value="1">&nbsp;1 학기&nbsp;&nbsp;
												<input type="radio" name="term" value="2" checked>&nbsp;2 학기
											</c:if>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">과목명</td>
										<td>
											<input type="text" name="name" value="${subject.name }" class="form-control form-control-sm">
										</td>
									</tr>
									<tr>
										<td class="mycolor2">학점</td>
										<td>
											<div class="form-inline">
												<select name="ipoint" class="form-control form-control-sm">
													<c:if test="${subject.ipoint eq 1 }">
													<option value='1' selected>1</option>
													<option value='2'>2</option>
													<option value='3'>3</option>
													<option value='4'>4</option>
													<option value='5'>5</option>
													</c:if>
													<c:if test="${subject.ipoint eq 2 }">
													<option value='1'>1</option>
													<option value='2' selected>2</option>
													<option value='3'>3</option>
													<option value='4'>4</option>
													<option value='5'>5</option>
													</c:if>
													<c:if test="${subject.ipoint eq 3 }">
													<option value='1'>1</option>
													<option value='2'>2</option>
													<option value='3' selected>3</option>
													<option value='4'>4</option>
													<option value='5'>5</option>
													</c:if>
													<c:if test="${subject.ipoint eq 4 }">
													<option value='1'>1</option>
													<option value='2'>2</option>
													<option value='3'>3</option>
													<option value='4' selected>4</option>
													<option value='5'>5</option>
													</c:if>
													<c:if test="${subject.ipoint eq 5 }">
													<option value='1'>1</option>
													<option value='2'>2</option>
													<option value='3'>3</option>
													<option value='4'>4</option>
													<option value='5' selected>5</option>
													</c:if>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">시간</td>
										<td>
											<div class="form-inline">
												<select name="ihour" class="form-control form-control-sm">
													<c:if test="${subject.ihour eq 1 }">
													<option value='1' selected>1</option>
													<option value='2'>2</option>
													<option value='3'>3</option>
													<option value='4'>4</option>
													<option value='5'>5</option>
													</c:if>
													<c:if test="${subject.ihour eq 2 }">
													<option value='1'>1</option>
													<option value='2' selected>2</option>
													<option value='3'>3</option>
													<option value='4'>4</option>
													<option value='5'>5</option>
													</c:if>
													<c:if test="${subject.ihour eq 3 }">
													<option value='1'>1</option>
													<option value='2'>2</option>
													<option value='3' selected>3</option>
													<option value='4'>4</option>
													<option value='5'>5</option>
													</c:if>
													<c:if test="${subject.ihour eq 4 }">
													<option value='1'>1</option>
													<option value='2'>2</option>
													<option value='3'>3</option>
													<option value='4' selected>4</option>
													<option value='5'>5</option>
													</c:if>
													<c:if test="${subject.ihour eq 5 }">
													<option value='1'>1</option>
													<option value='2'>2</option>
													<option value='3'>3</option>
													<option value='4'>4</option>
													<option value='5' selected>5</option>
													</c:if>
												</select>
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