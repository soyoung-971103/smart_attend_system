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
								<li class="breadcrumb-item active">학생 정보</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div class="card mb-3">
							<div class="card-header mycolor3" style="padding:10px">
								<h3><i class="fa fa-table"></i> 학생 입력</h3>
							</div>
								
							<div class="card-body" style="padding:10px">

								<form method="post" action="student-update.do" enctype="multipart/form-data">

								<table class="table table-bordered mytable-centermiddle" style="width:100%;">
									<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
									<tr>
										<td class="mycolor2" style="vertical-align:middle;width:60px">상태</td>
										<td>
											<div class="form-inline">
												<select name="state" class="form-control form-control-sm" style="width:80px">
													<c:if test="${student.state eq '재학' }">
													<option value="재학" selected>재학</option>
													<option value="휴학">휴학</option>
													<option value="자퇴">자퇴</option>
													</c:if>
													<c:if test="${student.state eq '휴학' }">
													<option value="재학">재학</option>
													<option value="휴학" selected>휴학</option>
													<option value="자퇴">자퇴</option>
													</c:if>
													<c:if test="${student.state eq '자퇴' }">
													<option value="재학">재학</option>
													<option value="휴학">휴학</option>
													<option value="자퇴" selected>자퇴</option>
													</c:if>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">학과</td>
										<td>
											<div class="form-inline">
												<select name="depart_id" class="form-control form-control-sm">
													<c:if test="${student.depart_id eq 1 }">
													<option value='1' selected>컴퓨터소프트에어학과</option>
													<option value='2'>전자과</option>
													<option value='3'>영어과</option>
													<option value='4'>교무처</option>
													</c:if>
													<c:if test="${student.depart_id eq 2 }">
													<option value='1'>컴퓨터소프트에어학과</option>
													<option value='2' selected>전자과</option>
													<option value='3'>영어과</option>
													<option value='4'>교무처</option>
													</c:if>
													<c:if test="${student.depart_id eq 3 }">
													<option value='1'>컴퓨터소프트에어학과</option>
													<option value='2'>전자과</option>
													<option value='3' selected>영어과</option>
													<option value='4'>교무처</option>
													</c:if>
													<c:if test="${student.depart_id eq 4 }">
													<option value='1'>컴퓨터소프트에어학과</option>
													<option value='2'>전자과</option>
													<option value='3'>영어과</option>
													<option value='4' selected>교무처</option>
													</c:if>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">학년/반</td>
										<td>
											<div class="form-inline">
												<select name="grade" class="form-control form-control-sm" style="width:80px">
													<c:if test="${student.grade eq 1 }">
														<option value='1' selected>1학년</option>
														<option value='2'>2학년</option>
														<option value='3'>3학년</option>
													</c:if>
													<c:if test="${student.grade eq 2 }">
														<option value='1'>1학년</option>
														<option value='2' selected>2학년</option>
														<option value='3'>3학년</option>
													</c:if>
													<c:if test="${student.grade eq 3 }">
														<option value='1'>1학년</option>
														<option value='2'>2학년</option>
														<option value='3' selected>3학년</option>
													</c:if>
												</select>
												&nbsp;
												<select name="student_class" class="form-control form-control-sm" style="width:80px">
													<c:if test="${student.student_class eq 'A' }">
													<option value="A" selected>A 반</option>
													<option value="B">B 반</option>
													</c:if>
													<c:if test="${student.student_class eq 'B' }">
													<option value="A">A 반</option>
													<option value="B" selected>B 반</option>
													</c:if>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">학번</td>
										<td>
											<div class="form-inline">
												<input type="text" name="schoolno" value="${student.schoolno }" class="form-control form-control-sm" required>
												<input type="hidden" name="id" value="${student.id }" class="form-control form-control-sm" required>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">이름</td>
										<td>
											<div class="form-inline">
												<input type="text" name="name" value="${student.name }" class="form-control form-control-sm" required>
											</div>
										</td>
									</tr>
									
									<tr>
										<td class="mycolor2">핸드폰</td>
										<td>
											<div class="form-inline">
												<input type="text" name="phone1" size="3" maxlength="3" value="${phone1 }" class="form-control form-control-sm">-
												<input type="text" name="phone2" size="4" maxlength="4" value="${phone2 }"	class="form-control form-control-sm">-
												<input type="text" name="phone3" size="4" maxlength="4" value="${phone3 }" class="form-control form-control-sm">
											</div>
										</td>
									</tr>
									
									<tr>
										<td class="mycolor2">성별</td>
										<td>
											<div class="form-inline">
												<c:if test="${student.sex eq 0 }">
												<input type="radio" name="sex" value="0" checked > &nbsp; 남자 &nbsp;&nbsp;
												<input type="radio" name="sex" value="1"> &nbsp; 여자</c:if>
												<c:if test="${student.sex eq 1 }">
												<input type="radio" name="sex" value="0" > &nbsp; 남자 &nbsp;&nbsp;
												<input type="radio" name="sex" value="1" checked> &nbsp; 여자
												</c:if>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">E-Mail</td>
										<td>
											<input type="text" name="email" value="${student.email }" class="form-control form-control-sm">
										</td>
									</tr>
									<tr>
										<td class="mycolor2">암호</td>
										<td>
											<div class="form-inline">
												<input type="text" name="pwd" value="${student.pwd }" class="form-control form-control-sm">
											</div>
										</td>
									</tr>
									
									<tr>
										<td class="mycolor2">생일</td>
										<td>
											<div class="form-inline">
												<input type="text" name="birthday1" size="4" maxlength="4" value="${birthday1 }" class="form-control form-control-sm">-
												<input type="text" name="birthday2" size="2" maxlength="2" value="${birthday2 }"	class="form-control form-control-sm">-
												<input type="text" name="birthday3" size="2" maxlength="2" value="${birthday3 }" class="form-control form-control-sm">
											</div>
										</td>
									</tr>
									
									<tr>
										<td class="mycolor2">사진</td>
										<td style="text-align:left">
											<div class="form-inline mymargin5">
												<input type="file" name="pic" value="${student.pic }" class="form-control form-control-sm">
											</div>
											<img src="pic/st/${student.pic }" class="img-thumbnail" width="120" height="160" border="1">
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
<%@ include file="main_bottom.jsp" %>