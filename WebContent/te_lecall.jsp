<!-------------------------------------------------------------------------------->	
<!-- 프로그램 : 인덕대학교 컴퓨터소프트웨어학과 전자출석 Demo                              -->
<!--                                                                                                                  -->
<!-- 소속 : 인덕대학교  컴퓨터소프트웨어학과  창업동아리 겜지기                              -->
<!-- 교수 : 윤형태 (2019.5 -        )                                                                         -->
<!-- 학생 : 유소영(3), 김해리(3), 이민호(2), 김진혁(2)                                              -->
<!-------------------------------------------------------------------------------->	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
								<li class="breadcrumb-item">교수님</li>
								<li class="breadcrumb-item active">과목별 출석부</li>
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
										<h3>교수님1</h3>
									</div>
								</div>
							</div>
							<div class="card-body" style="padding:10px">

								<script>
									function find_text()
									{
										//alert(form1.sel1.value+"^"+form1.sel2.value+"^"+form1.sel3.value+"^"+form1.sel4.value+"^"+form1.sel5.value);
										//form1.action="";
										//form1.submit();
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
													<select name="sel1" class="form-control form-control-sm" >
														<option value="2019" selected>2019</option>
														<option value='2018'>2018</option>
														<option value='2017'>2017</option>
														<option value='2016'>2016</option>
														<option value='2015'>2015</option>
													</select>
													&nbsp;
													<select name="sel2" class="form-control form-control-sm" >
														<option value='1'>1학기</option>
														<option value='2'>2학기</option>
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
														<option value='1'>1학년</option>
														<option value='2'>2학년</option>
														<option value='3'>3학년</option>
														<option value='4'>4학년</option>
													</select>
													&nbsp;
													<select name="sel4" class="form-control form-control-sm" >
														<option value='A'>A</option>
														<option value='B'>B</option>
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
														<option value='1'>PHP</option>
														<option value='2'>데이터베이스실무1</option>
														<option value='3'>데이터베이스실무2</option>
													</select>
												</div>
												&nbsp;
												<input type="button" value= "검색" onclick="find_text();" class="btn btn-sm btn-primary">
											</div>

										</div>
									</div>
								</div>
								</form>

								<script>
									function choose1(rowno,colno)
									{
										pos1=rowno+"^"+colno;
										pos2=rowno+","+colno;
										document.getElementById( pos1 ).innerHTML="<div style='font-size:10px;'><i class='fa fa-circle-o fa-1x' onclick='choose3("+pos2+",0);' style='cursor:pointer'></i><br><i class='text-primary fa fa-times-circle-o fa-1x' onclick='choose3("+pos2+",1);' style='cursor:pointer'></i><br><i class='text-danger fa fa-close fa-1x' onclick='choose3("+pos2+",2);' style='cursor:pointer'></font>";
									}
									
									function choose3(rowno,colno, v)
									{
										pos1=rowno+"^"+colno;
										pos2=rowno+","+colno;
										if (v==0)			s="<i class='fa fa-circle-o fa-1x' onclick='choose1("+pos2+");' style='cursor:pointer'></i>";
										else if (v==1)	s="<i class='text-primary fa fa-times-circle-o fa-1x' onclick='choose1("+pos2+");' style='cursor:pointer'></i>";
										else				s="<i class='text-danger fa fa-close fa-1x' onclick='choose1("+pos2+");' style='cursor:pointer'></i>";
										document.getElementById( pos1 ).innerHTML=s;
									}
								</script>

								<table class="table table-bordered table-sm table-hover table-responsive-sm mytable" style="width:100%;font-size:8pt;">
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
									<tr>
										<td class="mycolor3">컴퓨터소프트웨어학과</td>
										<td class="mycolor3">2</td>
										<td class="mycolor3">201912001</td>
										<td class="mycolor3">제적</td>
										<td class="mycolor3">홍길동1</td>
										<td class="mycolor4"><font color="blue"><b>1</b></font></td>
										<td class="mycolor4"><font color="red"><b>2</b></font></td>
										<td class="mycolor4"><b>18</b></td>
										<td id="1^1"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,1);" style="cursor:pointer"></i></td>
										<td id="1^2"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,2);" style="cursor:pointer"></i>  </td>
										<td id="1^3"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,3);" style="cursor:pointer"></i></td>
										<td id="1^4"><i class="text-danger fa fa-close fa-1x" onclick="choose1(1,4);" style="cursor:pointer"></i></td>
										<td id="1^5"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,5);" style="cursor:pointer"></i></td>
										<td id="1^6"><i class="text-primary fa fa-times-circle-o fa-1x" onclick="choose1(1,6);" style="cursor:pointer"></i></td>
										<td id="1^7"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,7);" style="cursor:pointer"></i></td>
										<td id="1^8"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,8);" style="cursor:pointer"></i></td>
										<td id="1^9"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,9);" style="cursor:pointer"></i></td>
										<td id="1^10"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,10);" style="cursor:pointer"></i></td>
										<td id="1^11"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,11);" style="cursor:pointer"></i></td>
										<td id="1^12"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,12);" style="cursor:pointer"></i></td>
										<td id="1^13"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,13);" style="cursor:pointer"></i></td>
										<td id="1^14"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,14);" style="cursor:pointer"></i></td>
										<td id="1^15"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,15);" style="cursor:pointer"></i></td>
										<td id="1^16"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,16);" style="cursor:pointer"></i></td>
										<td id="1^17"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,17);" style="cursor:pointer"></i></td>
										<td id="1^18"><i class="fa fa-circle-o fa-1x" onclick="choose1(1,18);" style="cursor:pointer"></i></td>
										<td><font color="green"><b>보</b></font></td>
										<td><font color="green"><b>보</b></font></td>
										<td><i class="text-warning fa fa-question fa-1x"></i></td>
										<td><i class="text-warning fa fa-question fa-1x"></i></td>
										<td><i class="text-warning fa fa-question fa-1x"></i></td>
										<td><i class="text-warning fa fa-question fa-1x"></i></td>
										<td><i class="text-warning fa fa-question fa-1x"></i></td>
										<td><i class="text-warning fa fa-question fa-1x"></i></td>
										<td><i class="text-warning fa fa-question fa-1x"></i></td>
										<td><i class="text-warning fa fa-question fa-1x"></i></td>
										<td><i class="text-warning fa fa-question fa-1x"></i></td>
										<td><i class="text-warning fa fa-question fa-1x"></i></td>
									</tr>
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