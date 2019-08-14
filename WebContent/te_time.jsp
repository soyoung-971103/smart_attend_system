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

<body class="adminbody" onLoad="init();">

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
								<li class="breadcrumb-item active">시간표</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<!--- 시간표 관련 JS  ---------------------------------------------->
				<%@ page import="java.util.*,model.TimeTableDTO, model.SubjectDTO, model.TeacherDTO" %>
				<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
				<script>
					function init()			// 해당학기 시간표읽어 모두 표시
					{	
						// 학년^반^요일^시작교시^시간^과목명^교수님^강의실
						var timetable = [
							${timetable.subject.grade}+'^A^'+${timetable.weekday}'+^'+${timetable.istart}+'^'+${timetable.ihour}+'^'+${timetable.subject.name}+'^'+${timetable.teacher.name}+'^'+${timetable.room.name}
							];
						
						var timetable = [
							'2^A^1^1^4^PHP^교수님1^컴실2',
							'2^B^2^1^4^PHP^교수님1^컴실2',
							'1^A^3^2^3^C^교수님2^컴실1',
							'1^B^4^6^3^C^교수님2^컴실1'	];

						for (i=0;i<timetable.length;i++)
						{
							show_lecture(0,timetable[i]);
						}
					}
				</script>

				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div class="card mb-3">
							<div class="card-header mycolor3" style="padding:10px">
								<div class="row">
									<div class="col" align="left">
										<h3><i class="fa fa-table"></i> 시간표</h3>
									</div>
									<div class="col" align="right">
										<h3>교수님1</h3>
									</div>
								</div>
							</div>
							<div class="card-body" style="padding:10px">

								<table class="table table-bordered mytable" style="width:100%;">
									<tr class="mycolor1">
										<td width="80">&nbsp;</td>
										<td width="80">월</td>
										<td width="80">화</td>
										<td width="80">수</td>
										<td width="80">목</td>
										<td width="80">금</td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>1</b>&nbsp;<font size="1">( 9:00)</font></td>
										<td><div id="011" class="lecbox0"></div></td>
										<td><div id="012" class="lecbox0"></div></td>
										<td><div id="013" class="lecbox0"></div></td>
										<td><div id="014" class="lecbox0"></div></td>
										<td><div id="015" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>2</b>&nbsp;<font size="1">(10:00)</font></td>
										<td><div id="021" class="lecbox0"></div></td>
										<td><div id="022" class="lecbox0"></div></td>
										<td><div id="023" class="lecbox0"></div></td>
										<td><div id="024" class="lecbox0"></div></td>
										<td><div id="025" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>3</b>&nbsp;<font size="1">(11:00)</font></td>
										<td><div id="031" class="lecbox0"></div></td>
										<td><div id="032" class="lecbox0"></div></td>
										<td><div id="033" class="lecbox0"></div></td>
										<td><div id="034" class="lecbox0"></div></td>
										<td><div id="035" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>4</b>&nbsp;<font size="1">(12:00)</font></td>
										<td><div id="041" class="lecbox0"></div></td>
										<td><div id="042" class="lecbox0"></div></td>
										<td><div id="043" class="lecbox0"></div></td>
										<td><div id="044" class="lecbox0"></div></td>
										<td><div id="045" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>5</b>&nbsp;<font size="1">( 1:00)</font></td>
										<td><div id="051" class="lecbox0"></div></td>
										<td><div id="052" class="lecbox0"></div></td>
										<td><div id="053" class="lecbox0"></div></td>
										<td><div id="054" class="lecbox0"></div></td>
										<td><div id="055" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>6</b>&nbsp;<font size="1">( 2:00)</font></td>
										<td><div id="061" class="lecbox0"></div></td>
										<td><div id="062" class="lecbox0"></div></td>
										<td><div id="063" class="lecbox0"></div></td>
										<td><div id="064" class="lecbox0"></div></td>
										<td><div id="065" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>7</b>&nbsp;<font size="1">( 3:00)</font></td>
										<td><div id="071" class="lecbox0"></div></td>
										<td><div id="072" class="lecbox0"></div></td>
										<td><div id="073" class="lecbox0"></div></td>
										<td><div id="074" class="lecbox0"></div></td>
										<td><div id="075" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>8</b>&nbsp;<font size="1">( 4:00)</font></td>
										<td><div id="081" class="lecbox0"></div></td>
										<td><div id="082" class="lecbox0"></div></td>
										<td><div id="083" class="lecbox0"></div></td>
										<td><div id="084" class="lecbox0"></div></td>
										<td><div id="085" class="lecbox0"></div></td>

									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>9</b>&nbsp;<font size="1">( 5:00)</font></td>
										<td><div id="091" class="lecbox0"></div></td>
										<td><div id="092" class="lecbox0"></div></td>
										<td><div id="093" class="lecbox0"></div></td>
										<td><div id="094" class="lecbox0"></div></td>
										<td><div id="095" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#dddddd" colspan="30" style="height:3px;padding:0px"></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>10</b>&nbsp;<font size="1">(6:00)</font></td>
										<td><div id="101" class="lecbox0"></div></td>
										<td><div id="102" class="lecbox0"></div></td>
										<td><div id="103" class="lecbox0"></div></td>
										<td><div id="104" class="lecbox0"></div></td>
										<td><div id="105" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>11</b>&nbsp;<font size="1">(7:00)</font></td>
										<td><div id="111" class="lecbox0"></div></td>
										<td><div id="112" class="lecbox0"></div></td>
										<td><div id="113" class="lecbox0"></div></td>
										<td><div id="114" class="lecbox0"></div></td>
										<td><div id="115" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>12</b>&nbsp;<font size="1">(8:00)</font></td>
										<td><div id="121" class="lecbox0"></div></td>
										<td><div id="122" class="lecbox0"></div></td>
										<td><div id="123" class="lecbox0"></div></td>
										<td><div id="124" class="lecbox0"></div></td>
										<td><div id="125" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>13</b>&nbsp;<font size="1">(9:00)</font></td>
										<td><div id="131" class="lecbox0"></div></td>
										<td><div id="132" class="lecbox0"></div></td>
										<td><div id="133" class="lecbox0"></div></td>
										<td><div id="134" class="lecbox0"></div></td>
										<td><div id="135" class="lecbox0"></div></td>
									</tr>
									<tr>
										<td bgcolor="#eeeeee"><b>14</b>&nbsp;<font size="1">(10:00)</font></td>
										<td><div id="141" class="lecbox0"></div></td>
										<td><div id="142" class="lecbox0"></div></td>
										<td><div id="143" class="lecbox0"></div></td>
										<td><div id="144" class="lecbox0"></div></td>
										<td><div id="145" class="lecbox0"></div></td>
									</tr>
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
		<span class="float-right">Programmed By <a target="_blank" href="#"><b>Gamejigi</b></a></span>
	</footer>

</div>

<!-- js 선언부 ----------------------------------------------------------------->
<script src="my/js/my.js"></script>

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