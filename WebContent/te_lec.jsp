<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	<link href="my/css/bootstrap-datetimepicker.min.css" rel="stylesheet">	<!-- datetimepicker -->
	<link href="my/css/dataTables.bootstrap4.min.css" rel="stylesheet">		<!-- datatable.net -->

	<link href="my/css/my.css" rel="stylesheet" type="text/css">

<link href="my/css/daterangepicker.css" rel="stylesheet" /> 

    <link rel="stylesheet" href="my/flatpickr_datetime/flatpickr.css">
	<link rel="stylesheet" type="text/css" href="my/flatpickr_datetime/material_blue.css">

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
								<li class="breadcrumb-item active">일별 출석부</li>
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
										<h3><i class="fa fa-table"></i> 출석부</h3>
									</div>
									<div class="col" align="right">
										<h3>교수님1</h3>
									</div>
								</div>
							</div>

							<div class="card-body" style="padding:10px">
								<form name="form1" action="" method="post">
								<div class="row mymargin5" style="width:100%;">
									<div class="col" align="left">
										<div class="form-inline">
											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text">날짜</span>
												</div>
												<input type="text" id="text1" name="text1" size="12" class="flatpickr form-control" style="text-align:center;background-color:white">
											</div>
										</div>
									</div>
								</div>
								</form>

								<div class="row mymargin5">

									<div class="col-12 col-lg-6" align="left">
										<div class="card border-dark mb-3" style="max-width: 20rem;">
											<div class="card-header bg-info text-white"  style="padding:10px">
												<h5 class="card-title" style="margin:0px">PHP</h5>
											</div>
											<div class="card-body" style="padding:10px">
												교수님1<br>
												컴퓨터소프트웨어학과	: 컴퓨터2실(인301)<br>
												3교시~4교시(11:00 ~ 12:50)<br>
												수강생 35명<br>
												<center><a href="te_leccall.jsp" class="btn btn-sm btn-primary mymargin5"> 강의전 </a></center>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-6" align="left">
										<div class="card border-dark mb-3" style="max-width: 20rem;">
											<div class="card-header bg-info text-white"  style="padding:10px">
												<h5 class="card-title" style="margin:0px">PHP</h5>
											</div>
											<div class="card-body" style="padding:10px">
												교수님1<br>
												컴퓨터소프트웨어학과	: 컴퓨터2실(인301)<br>
												3교시~4교시(11:00 ~ 12:50)<br>
												수강생 35명<br>
												<center><a href="te_leccall.html" class="btn btn-sm btn-primary mymargin5"> 강의완료 </a></center>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-6" align="left">
										<div class="card border-dark mb-3" style="max-width: 20rem;">
											<div class="card-header bg-info text-white"  style="padding:10px">
												<h5 class="card-title" style="margin:0px">PHP</h5>
											</div>
											<div class="card-body" style="padding:10px">
												교수님1<br>
												컴퓨터소프트웨어학과	: 컴퓨터2실(인301)<br>
												3교시~4교시(11:00 ~ 12:50)<br>
												수강생 35명<br>
												<center><a href="te_leccall.html" class="btn btn-sm btn-primary mymargin5"> 강의완료 </a></center>
											</div>
										</div>
									</div>
								</div>

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
<script src="my/js/popper.min.js"></script>
<script src="my/js/bootstrap.min.js"></script>

<script src="my/js/detect.js"></script>
<script src="my/js/fastclick.js"></script>
<script src="my/js/jquery.blockUI.js"></script>
<script src="my/js/jquery.nicescroll.js"></script>

<script src="my/js/pikeadmin.js"></script>

<script src="my/js/jquery.dataTables.min.js"></script>			<!-- dataTable.net -->
<script src="my/js/dataTables.bootstrap4.min.js"></script>

<script src="my/flatpickr_datetime/flatpickr.js"></script>		<!-- datetimepicker -->
<script src="my/flatpickr_datetime/flatpickr1.js"></script>
<script src="my/flatpickr_datetime/ko.js"></script>

<script src="my/js/moment.min.js"></script>
<script src="my/js/daterangepicker.js"></script>

<script>
	flatpickr("#text1", {
		dateFormat: "Y-m-d",
		locale: "ko",
		defaultDate: "today",
		onChange: function(selectedDates, dateStr, instance) 
		{
			// 프로그램 작성
		}
	});
</script>

</body>
</html>

