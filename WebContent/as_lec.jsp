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
								<li class="breadcrumb-item active">강의</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div class="card mb-3">
							<div class="card-header mycolor3" style="padding:10px">
								<h3><i class="fa fa-table"></i> 강의</h3>
							</div>
								
							<div class="card-body" style="padding:10px">
							<!------------------------------------------------------------>

								<script>
									function find_text()
									{
										form1.action="lecture-list.do?sel1=" + form1.sel1.value+"&sel2=" + form1.sel2.value+"&sel3=" + form1.sel3.value;
										form1.submit();
									}
									function make_lecure()
									{
										form1.action="lecture-list.do?sel1=" + form1.sel1.value+"&sel2=" + form1.sel2.value;
										form1.submit();
									}
									function update_teacher(pos) 
									{
										form2.action="lecture-update.do?id=" + pos;
										form2.teacherno.value=eval("form2.teacher"+pos).value;
										form2.submit();
									}
								</script>

								<form name="form1" action="" method="post">
								<div class="row" style="margin-bottom:3px">
									<div class="col" align="left">
										<div class="form-inline">
											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text">년도</span>
												</div>
												<div class="input-group-append">
													<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
													<select name="sel1" id="sel1" class="form-control form-control-sm" onchange="">
														<c:forEach var="i" step="1" begin="0" end="4">
															<c:choose>
															    <c:when test="${sel1 eq 2019-i}">
															       <option value="${ 2019-i}" selected>${2019-i }</option>
															    </c:when>
															    <c:otherwise>
																	<option value="${2019-i }">${2019-i }</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
													&nbsp;
													<select name="sel2" id="sel2" class="form-control form-control-sm" onchange="">
														<c:forEach var="i" step="1" begin="1" end="2">
															<c:choose>
															    <c:when test="${sel1 eq i}">
															       <option value="${i}" selected>${i }학기</option>
															    </c:when>
															    <c:otherwise>
																	<option value="${i}">${i }학기</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
													&nbsp;
													<select name="sel3" id="sel3" class="form-control form-control-sm" onchange="">
														<c:forEach var="i" step="1" begin="0" end="4">
															<c:choose>
																<c:when test="${sel2 eq i}">
																	<c:choose>
																		<c:when test="${i eq 0}">
																			<option value='0' selected>전체</option>
																		</c:when>
																		<c:otherwise>
																			<option value='${i }' selected>${i }</option>
																		</c:otherwise>
																	</c:choose>
																</c:when>
																<c:otherwise>
																	<c:choose>
																		<c:when test="${i eq 0}">
																			<option value='0'>전체</option>
																		</c:when>
																		<c:otherwise>
																			<option value='${i }'>${i }</option>
																		</c:otherwise>
																	</c:choose>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
												</div>
												&nbsp;<input type="button" class="btn btn-sm btn-primary" value="검색" onclick="javascript:find_text();">
											</div>

										</div>
									</div>
									<div class="col" align="right">
										<a href="lecture-register.do" class="btn btn-sm btn-primary">반별과목 생성</a>
										<a href="lecture-delete.do" class="btn btn-sm btn-danger">반별과목 삭제</a>
									</div>
								</div>
								</form>
				
								<form name="form2" method="post" action="">

								<input type="hidden" name="teacherno" value="">

								<table class="table table-bordered table-hover table-responsive-sm mytable" style="width:100%">
									<thead>
										<tr class="mycolor1">
											<th>과목코드</th>
											<th>과목명</th>
											<th>학점</th>
											<th>시간</th>
											<th>반</th>
											<th width="120">담당교수</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="lecture" items="${ list }">
											<input type="hidden" name="id" id="id" value="${lecture.id }" class="form-control form-control-sm" required>
											<tr>
												<td>${ lecture.subject.code}</td>
												<td>${ lecture.subject.name}</td>
												<td>${ lecture.subject.ipoint}</td>
												<td>${ lecture.subject.ihour}</td>
												<td>${ lecture._class }</td>
												<td>
													<div class="form-inline justify-content-center">
														<select name="teacher${lecture.id }" class="form-control form-control-sm" onchange="update_teacher('${lecture.id }');">
															<c:forEach var="teacher" items="${ teacher }">
																<c:choose>
																    <c:when test="${lecture.teacher_id eq teacher.id}">
																       <option value='${teacher.id }' selected>${teacher.name }</option>
																    </c:when>
																    <c:otherwise>
																       <option value='${teacher.id }'>${teacher.name }</option>
																    </c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
													</div>
												</td>
											</tr>
										</c:forEach>
										
									</tbody>
								</table>
								</form>

							<!------------------------------------------------------------>
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