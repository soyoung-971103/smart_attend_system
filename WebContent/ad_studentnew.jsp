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

<script>	
	function add_depart(){
		form1.action = "student-studentnew.do?student_class="+ form1.depart_id.value
		form1.submit();
	}
</script>


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
							
							<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
								<form name="form1" method="post" action="student-register.do" enctype="multipart/form-data">

								<table class="table table-bordered mytable-centermiddle" style="width:100%;">
									<tr>
										<td class="mycolor2" style="vertical-align:middle;width:60px">상태</td>
										<td>
											<div class="form-inline">
												<select name="state" class="form-control form-control-sm" style="width:80px">
													<option value="재학" selected>재학</option>
													<option value="휴학">휴학</option>
													<option value="자퇴">자퇴</option>
												</select>
											</div>
										</td>
									</tr>
									<tr>
									<% 
										String student_class = request.getParameter("student_class");
										pageContext.setAttribute("student_class",student_class);
	
										if(request.getAttribute("depart_class") != null ) {
											int num = ((Integer)request.getAttribute("depart_class")).intValue();
											pageContext.setAttribute("classnum",num);
										}
									%>
										<td class="mycolor2">학과</td>
										<td>
											<div class="form-inline">
												<select name="depart_id" class="form-control form-control-sm" onchange="add_depart()">
													<option value="0" selected></option>
													<c:forEach var="depart" items="${listDepart}">
													
															<c:choose>
																<c:when test='${student_class eq depart.id }'>

																	<option value="${depart.getId()}" selected>${depart.getName()}</option>
																</c:when>
																<c:otherwise>
																	<option value="${depart.getId()}">${depart.getName()}</option>
																</c:otherwise>											
															</c:choose>
														</c:forEach>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">학년/반</td>
										<td>
											<div class="form-inline">
												<select name="grade" class="form-control form-control-sm" style="width:80px" onchange="add_grade()">
													<option value='1'>1학년</option>
													<option value='2'>2학년</option>
													<option value='3'>3학년</option>
													<option value='4'>4학년</option>
												</select>
												<input type="hidden" name="data1" value="">
												<input type="hidden" name="data2" value="">
												&nbsp;
												<select name="student_class" class="form-control form-control-sm" style="width:80px">
													<c:choose>
														<c:when test="${classnum eq 1 }">
														<option value="A">A 반</option>
														</c:when>
														<c:when test="${classnum eq 2 }">
														<option value="A">A 반</option>
														<option value="B">B 반</option>
														</c:when>
														<c:when test="${classnum eq 3 }">
														<option value="A">A 반</option>
														<option value="B">B 반</option>
														<option value="C">C 반</option>
														</c:when>
														<c:when test="${classnum eq 4 }">
														<option value="A">A 반</option>
														<option value="B">B 반</option>
														<option value="C">C 반</option>
														<option value="D">D 반</option>
														</c:when>
														<c:otherwise>
														<option value="A">A 반</option>
														<option value="B">B 반</option>
														<option value="C">C 반</option>
														<option value="D">D 반</option>
														<option value="E">E 반</option>
														</c:otherwise>
													</c:choose>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">학번</td>
										<td>
											<div class="form-inline">
												<input type="text" name="schoolno" value="" class="form-control form-control-sm" required>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">이름</td>
										<td>
											<div class="form-inline">
												<input type="text" name="name" value="" class="form-control form-control-sm" required>
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">핸드폰</td>
										<td>
											<div class="form-inline">
												<input type="text" name="phone1" size="3" maxlength="3" value="010" class="form-control form-control-sm">-
												<input type="text" name="phone2" size="4" maxlength="4" value=""	class="form-control form-control-sm">-
												<input type="text" name="phone3" size="4" maxlength="4" value="" class="form-control form-control-sm">
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">성별</td>
										<td>
											<div class="form-inline">
												<input type="radio" name="sex" value="0" checked> &nbsp; 남자 &nbsp;&nbsp;
												<input type="radio" name="sex" value="1"> &nbsp; 여자
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">E-Mail</td>
										<td>
											<input type="text" name="email" value="" class="form-control form-control-sm">
										</td>
									</tr>
									<tr>
										<td class="mycolor2">암호</td>
										<td>
											<div class="form-inline">
												<input type="text" name="pwd" value="" class="form-control form-control-sm">
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">생일</td>
										<td>
											<div class="form-inline">
												<input type="text" name="birthday1" size="4" maxlength="4" value="" class="form-control form-control-sm">-
												<input type="text" name="birthday2" size="2" maxlength="2" value=""	class="form-control form-control-sm">-
												<input type="text" name="birthday3" size="2" maxlength="2" value="" class="form-control form-control-sm">
											</div>
										</td>
									</tr>
									<tr>
										<td class="mycolor2">사진</td>
										<td style="text-align:left">
											<div class="form-inline mymargin5">
												<input type="file" name="pic" value="" class="form-control form-control-sm">
											</div>
											<img src="pic/st/hgd.bmp" class="img-thumbnail" width="120" height="160" border="1">
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