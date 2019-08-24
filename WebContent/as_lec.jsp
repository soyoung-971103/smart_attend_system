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
										form1.action="???.html?sel1=" + form1.sel1.value+"&sel2=" + form1.sel2.value+"&sel3=" + form1.sel3.value;
										form1.submit();
									}
									function make_lecure()
									{
										form1.action="lecture_allupdate.html?sel1=" + form1.sel1.value+"&sel2=" + form1.sel2.value;
										form1.submit();
									}
									function update_teacher(pos) 
									{
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
													<select name="sel1" id="sel1" class="form-control form-control-sm" onchange="">
														<option value="2019" selected>2019</option>
														<option value='2018'>2018</option>
														<option value='2017'>2017</option>
														<option value='2016'>2016</option>
														<option value='2015'>2015</option>
													</select>
													&nbsp;
													<select name="sel2" id="sel2" class="form-control form-control-sm" onchange="">
														<option value='1' selected>1 학기</option>
														<option value='2'>2 학기</option>
													</select>
													&nbsp;
													<select name="sel3" id="sel3" class="form-control form-control-sm" onchange="">
														<option value='1' selected>1 학년</option>
														<option value='2'>2 학년</option>
														<option value='3'>3 학년</option>
														<option value='4'>4 학년</option>
													</select>
												</div>
												&nbsp;<input type="button" class="btn btn-sm btn-primary" value="검색" onclick="javascript:find_text();">
											</div>

										</div>
									</div>
									<div class="col" align="right">
										<a href="make_lecture();" class="btn btn-sm btn-primary">반별과목 생성</a>
										<a href="make_lecture();" class="btn btn-sm btn-danger">반별과목 삭제</a>
									</div>
								</div>
								</form>
								
								<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
				
								<form name="form2" method="post" action="lecture_update.html">

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
											<tr>
												<td>${ lecture.subject.code}</td>
												<td>${ lecture.subject.name}</td>
												<td>${ lecture.subject.ipoint}</td>
												<td>${ lecture.subject.ihour}</td>

												<td>${ lecture.lec_class }</td>
												<td>
													<div class="form-inline justify-content-center">
														<select name="teacher1" class="form-control form-control-sm" onchange="update_teacher('1');">
															<option value='0' selected>&nbsp;</option>
															<option value='1'>교수님1</option>
															<option value='2'>교수님2</option>
															<option value='3'>교수님3</option>
															<option value='4'>교수님4</option>
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