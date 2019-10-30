<!-------------------------------------------------------------------------------->	
<!-- 프로그램 : 인덕대학교 컴퓨터소프트웨어학과 전자출석 Demo                              -->
<!--                                                                                                                  -->
<!-- 소속 : 인덕대학교  컴퓨터소프트웨어학과  창업동아리 겜지기                              -->
<!-- 교수 : 윤형태 (2019.5 -        )                                                                         -->
<!-- 학생 : 유소영(3), 김해리(3), 이민호(2), 김진혁(2)                                              -->
<!-------------------------------------------------------------------------------->	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,model.TimeTableDTO, model.BuildingDTO, model.RoomDTO, model.LectureDTO, model.SubjectDTO, model.TeacherDTO, model.DepartDTO" %>
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
								<li class="breadcrumb-item">교수</li>
								<li class="breadcrumb-item active">휴보강</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					var f = function(n) {
						var str = $('#normdate').val();
						var lecture = "";
						//document.getElementById('ajaxtest').innerHTML="";
						var xhttp = new XMLHttpRequest();
						xhttp.open("post", "teacher-lecture-search.do?normdate=" + str + "&n=1", true);
					    xhttp.send();
					};
			  </script>
				<!--- 시간표 관련 JS  ---------------------------------------------->
				<script>

					function init() 
					{
						var sday=new Date();
						form1.startday.value=get_day( sday, -sday.getDay() );
						moveweek(0);
						load_lec();
					}
					
					function setCookie(cookie_name, value, days) { //쿠키추가
					  var exdate = new Date();
					  exdate.setDate(exdate.getDate() + days);
					  // 설정 일수만큼 현재시간에 만료값으로 지정
					
					  //var cookie_value = escape(value) + ((days == null) ? '' : ';    expires=' + exdate.toUTCString());
					  document.cookie = cookie_name + '=' + value;
					}

					function load_lec()			// 해당학기 시간표읽어 모두 표시
					{	
						// 학년^반^요일^시작교시^시간^과목명^교수님^강의실
						var timetable = new Array();
						<c:forEach var="item2" items="${list}" >
							timetable.push("${item2.lecture.subject.grade}^${item2.lecture._class}^${item2.weekday}^${item2.istart}^${item2.ihour}^${item2.lecture.subject.name}^${item2.lecture.teacher.name}^${item2.room.name}^${item2.lecture_id}");
						</c:forEach>
						for (i=0;i<timetable.length;i++)
						{
							show_lecture(2, timetable[i]);
						}
					}

					function moveweek(dir)
					{
						show_week(dir);
						clear_lecture();
					}

					function sel_lecture(pos)		// 시간표내의 강의 선택 
					{
						clear_lecture();

						var w=['일','월','화','수','목','금','토'];
						str = pos.split("^");
						form1.normdate.value=get_day( form1.startday.value, Number(str[2]) );
						form1.normweek.value=w[ str[2] ];
						form1.normstart.value=str[3];
						form1.normhour.value=str[4];
					
						document.getElementById( pos ).style.borderColor="red";
						
						setCookie("lecturenorm_data", pos, 1);
						
					}
					
					
				</script>

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

								<ul class="nav nav-tabs nav-pills nav-fill">
								  <li class="nav-item">
									<a class="nav-link active" href="teacher-lecmove-select.do"><h6>휴강날짜 선택</h6></a>
								  </li>
								  <li class="nav-item">
									<a class="nav-link" href="#" onclick="document.getElementById('form1').submit();"><h6>보강날짜 선택</h6></a>
								  </li>
								</ul>
						
								<form name="form1" method="post" action="teacher-lecmoverest.do" id="form1">

								<input type="hidden" name="startday" value="">

								<div class="row">
									<div class="col-auto" align="center">
										<br>
										<div class="form-inline" style="margin-bottom:3px">
											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text alert-primary"><a href="javascript:moveweek(-1);"><i class="fa fa-chevron-left"></i></a></span>&nbsp;
													<span class="input-group-text alert-primary">휴강일</span>&nbsp;
												</div>
												<input type="text" name="normdate" id="normdate" value="" style="width:70px;text-align:center" class="flatpickr form-control form-control-sm" style="text-align:center;background-color:white" onchange="f(1)">&nbsp;
												<input type="text" name="normweek" size="1" value="" style="text-align:center" class="form-control form-control-sm">&nbsp;
												<input type="text" name="normstart"  size="1" value="" style="text-align:center" class="form-control form-control-sm">&nbsp;
												<input type="text" name="normhour" size="1" value="" style="text-align:center" class="form-control form-control-sm">&nbsp;
												<div class="input-group-append">
													<span class="input-group-text alert-primary"><a href="javascript:moveweek(1);"><i class="fa fa-chevron-right"></i></a></span>
												</div>
											</div>
										</div>
										<div class="form-inline" style="margin-bottom:3px">
											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text"><i class="fa fa-chevron-left"></i></span>&nbsp;
													<span class="input-group-text">보강일</span>&nbsp;
												</div>
												<input type="text" name="restdate" " value="" readonly style="width:70px;text-align:center" class="form-control form-control-sm" >&nbsp;
												<input type="text" name="restweek" size="1" value="" readonly style="text-align:center" class="form-control form-control-sm" >&nbsp;
												<input type="text" name="reststart"  size="1" value="" readonly style="text-align:center" class="form-control form-control-sm" >&nbsp;
												<input type="text" name="resthour" size="1" value="" readonly style="text-align:center" class="form-control form-control-sm" >&nbsp;
												<div class="input-group-append">
													<span class="input-group-text"><i class="fa fa-chevron-right"></i></span>
												</div>
											</div>
										</div>
										<div class="form-inline" style="margin-bottom:3px">
											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text"><i class="fa fa-chevron-left" disabled></i></span>&nbsp;
													<span class="input-group-text">강의실</span>&nbsp;
												</div>
												<input type="text" name="bulding_name" value="인관" readonly style="width:76px;text-align:center" class="form-control form-control-sm" >&nbsp;
												<input type="text" name="room_name" value="컴퓨터실1" readonly style="width:193px;text-align:center" class="form-control form-control-sm" >&nbsp;
												<input type="hidden" name="building_no"  value="1">
												<input type="hidden" name="room_no" value="1">
											</div>
										</div>
										
										<table class="table table-bordered table-responsive mytable" style="width:100%;">
											<tr class="mycolor1">
												<td width="45">&nbsp;</td>
												<td id="w0" width="45">일<br>2019-06-02</td>
												<td id="w1" width="45">월<br>2019-06-03</td>
												<td id="w2" width="45">화<br>2019-06-04</td>
												<td id="w3" width="45">수<br>2019-06-05</td>
												<td id="w4" width="45">목<br>2019-06-06</td>
												<td id="w5" width="45">금<br>2019-06-07</td>
												<td id="w6" width="45">토<br>2019-06-08</td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>1</b>&nbsp;<font size="1">( 9:00)</font></td>
												<td class="mycolor3"><div id="010" class="lecbox"></div></td>
												<td><div id="011" class="lecbox"></div></td>
												<td><div id="012" class="lecbox"></div></td>
												<td><div id="013" class="lecbox"></div></td>
												<td><div id="014" class="lecbox"></div></td>
												<td><div id="015" class="lecbox"></div></td>
												<td><div id="016" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>2</b>&nbsp;<font size="1">(10:00)</font></td>
												<td class="mycolor3"><div id="020" class="lecbox"></div></td>
												<td><div id="021" class="lecbox"></div></td>
												<td><div id="022" class="lecbox"></div></td>
												<td><div id="023" class="lecbox"></div></td>
												<td><div id="024" class="lecbox"></div></td>
												<td><div id="025" class="lecbox"></div></td>
												<td><div id="026" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>3</b>&nbsp;<font size="1">(11:00)</font></td>
												<td class="mycolor3"><div id="030" class="lecbox"></div></td>
												<td><div id="031" class="lecbox"></div></td>
												<td><div id="032" class="lecbox"></div></td>
												<td><div id="033" class="lecbox"></div></td>
												<td><div id="034" class="lecbox"></div></td>
												<td><div id="035" class="lecbox"></div></td>
												<td><div id="036" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>4</b>&nbsp;<font size="1">(12:00)</font></td>
												<td class="mycolor3"><div id="040" class="lecbox"></div></td>
												<td><div id="041" class="lecbox"></div></td>
												<td><div id="042" class="lecbox"></div></td>
												<td><div id="043" class="lecbox"></div></td>
												<td><div id="044" class="lecbox"></div></td>
												<td><div id="045" class="lecbox"></div></td>
												<td><div id="046" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>5</b>&nbsp;<font size="1">( 1:00)</font></td>
												<td class="mycolor3"><div id="050" class="lecbox"></div></td>
												<td><div id="051" class="lecbox"></div></td>
												<td><div id="052" class="lecbox"></div></td>
												<td><div id="053" class="lecbox"></div></td>
												<td><div id="054" class="lecbox"></div></td>
												<td><div id="055" class="lecbox"></div></td>
												<td><div id="056" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>6</b>&nbsp;<font size="1">( 2:00)</font></td>
												<td class="mycolor3"><div id="060" class="lecbox"></div></td>
												<td><div id="061" class="lecbox"></div></td>
												<td><div id="062" class="lecbox"></div></td>
												<td><div id="063" class="lecbox"></div></td>
												<td><div id="064" class="lecbox"></div></td>
												<td><div id="065" class="lecbox"></div></td>
												<td><div id="066" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>7</b>&nbsp;<font size="1">( 3:00)</font></td>
												<td class="mycolor3"><div id="070" class="lecbox"></div></td>
												<td><div id="071" class="lecbox"></div></td>
												<td><div id="072" class="lecbox"></div></td>
												<td><div id="073" class="lecbox"></div></td>
												<td><div id="074" class="lecbox"></div></td>
												<td><div id="075" class="lecbox"></div></td>
												<td><div id="076" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>8</b>&nbsp;<font size="1">( 4:00)</font></td>
												<td class="mycolor3"><div id="080" class="lecbox"></div></td>
												<td><div id="081" class="lecbox"></div></td>
												<td><div id="082" class="lecbox"></div></td>
												<td><div id="083" class="lecbox"></div></td>
												<td><div id="084" class="lecbox"></div></td>
												<td><div id="085" class="lecbox"></div></td>
												<td><div id="086" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>9</b>&nbsp;<font size="1">( 5:00)</font></td>
												<td class="mycolor3"><div id="090" class="lecbox"></div></td>
												<td><div id="091" class="lecbox"></div></td>
												<td><div id="092" class="lecbox"></div></td>
												<td><div id="093" class="lecbox"></div></td>
												<td><div id="094" class="lecbox"></div></td>
												<td><div id="095" class="lecbox"></div></td>
												<td><div id="096" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#dddddd" colspan="30" style="height:3px;padding:0px"></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>10</b>&nbsp;<font size="1">(6:00)</font></td>
												<td class="mycolor3"><div id="100" class="lecbox"></div></td>
												<td><div id="101" class="lecbox"></div></td>
												<td><div id="102" class="lecbox"></div></td>
												<td><div id="103" class="lecbox"></div></td>
												<td><div id="104" class="lecbox"></div></td>
												<td><div id="105" class="lecbox"></div></td>
												<td><div id="106" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>11</b>&nbsp;<font size="1">(7:00)</font></td>
												<td class="mycolor3"><div id="110" class="lecbox"></div></td>
												<td><div id="111" class="lecbox"></div></td>
												<td><div id="112" class="lecbox"></div></td>
												<td><div id="113" class="lecbox"></div></td>
												<td><div id="114" class="lecbox"></div></td>
												<td><div id="115" class="lecbox"></div></td>
												<td><div id="116" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>12</b>&nbsp;<font size="1">(8:00)</font></td>
												<td class="mycolor3"><div id="120" class="lecbox"></div></td>
												<td><div id="121" class="lecbox"></div></td>
												<td><div id="122" class="lecbox"></div></td>
												<td><div id="123" class="lecbox"></div></td>
												<td><div id="124" class="lecbox"></div></td>
												<td><div id="125" class="lecbox"></div></td>
												<td><div id="126" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>13</b>&nbsp;<font size="1">(9:00)</font></td>
												<td class="mycolor3"><div id="130" class="lecbox"></div></td>
												<td><div id="131" class="lecbox"></div></td>
												<td><div id="132" class="lecbox"></div></td>
												<td><div id="133" class="lecbox"></div></td>
												<td><div id="134" class="lecbox"></div></td>
												<td><div id="135" class="lecbox"></div></td>
												<td><div id="136" class="lecbox"></div></td>
											</tr>
											<tr>
												<td bgcolor="#eeeeee"><b>14</b>&nbsp;<font size="1">(10:00)</font></td>
												<td class="mycolor3"><div id="140" class="lecbox"></div></td>
												<td><div id="141" class="lecbox"></div></td>
												<td><div id="142" class="lecbox"></div></td>
												<td><div id="143" class="lecbox"></div></td>
												<td><div id="144" class="lecbox"></div></td>
												<td><div id="145" class="lecbox"></div></td>
												<td><div id="146" class="lecbox"></div></td>
											</tr>
										</table>
									</div>
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