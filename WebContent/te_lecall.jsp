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

<body class="adminbody" onLoad="f();">

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
								<form name="form1" action="" method="post">
								<div class="row" style="margin-bottom:3px">
									<div class="col" align="left">
										<div class="form-inline">

											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text">년도</span>
												</div>
												<div class="input-group-append">
													<select name="sel1" class="form-control form-control-sm" onchange="f()">
														<option value="2019" selected>2019</option>
														<option value='2018'>2018</option>
														<option value='2017'>2017</option>
														<option value='2016'>2016</option>
														<option value='2015'>2015</option>
													</select>
													&nbsp;
													<select name="sel2" class="form-control form-control-sm" onchange="f()">
														<option value='1' selected>1학기</option>
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
													<select name="sel3" class="form-control form-control-sm" onchange="f()">
														<c:forEach var="i" begin="1" end="${departInfo.getGradesystem() }" varStatus="status">
															<c:choose>
																<c:when test="${status.first}">
																	<option value="${i}" selected>${i}학년</option>
																</c:when>
																<c:otherwise>
																	<option value="${i}">${i}학년</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
													&nbsp;
													<select name="sel4" class="form-control form-control-sm" onchange="f()">
														<c:forEach var="i" items="${classNum }" varStatus="status">
															<c:choose>
																<c:when test="${status.first}">
																	<option value="${i}" selected>${i}</option>
																</c:when>
																<c:otherwise>
																	<option value="${i}">${i}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
												</div>
											</div>
											&nbsp;
											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text">과목</span>
												</div>
												<div id="lecSelect" class="input-group-append">
													<!-- <select name="sel5"></select> -->
													<select name='sel5' class='form-control form-control-sm' >
													</select>
												</div>
												&nbsp;
												<input type="button" value= "검색" onclick="stuList_find();" class="btn btn-sm btn-primary">
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
									document.getElementById( pos1 ).innerHTML="<div style='font-size:10px;'>"
									+"<i class='fa fa-circle-o fa-1x' onclick='choose3("+pos2+",0);'"+
									" onchange='j("+rowno+");' style='cursor:pointer'></i><br>"+
									"<i class='text-primary fa fa-times-circle-o fa-1x' onclick='choose3("+pos2+",1);'  style='cursor:pointer'>"+
									"</i><br><i class='text-danger fa fa-close fa-1x' onclick='choose3("+pos2+",2);' style='cursor:pointer'></font>";
								}
								//
								function choose3(rowno,colno, v)
								{
									pos1=rowno+"^"+colno;
									pos2=rowno+","+colno;
									if (v==0)			s="<i class='fa fa-circle-o fa-1x' onclick='choose1("+pos2+");' style='cursor:pointer'></i>";
									else if (v==1)	s="<i class='text-primary fa fa-times-circle-o fa-1x' onclick='choose1("+pos2+");'  style='cursor:pointer'></i>";
									else				s="<i class='text-danger fa fa-close fa-1x' onclick='choose1("+pos2+");' style='cursor:pointer'></i>";
									document.getElementById( pos1 ).innerHTML=s;
									j(rowno,colno, v);
									//stuList
								}
								</script>

								<table class="table table-bordered table-sm table-hover table-responsive-sm mytable" style="width:100%;font-size:8pt;">
								<thead id="lectureInfo">
									
								</thead>
								<tbody id="studentInfo">
									
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
<script> 
	var strmaker = function(){
		var s1 = document.form1.sel1.value;
		var s2 = document.form1.sel2.value;
		var s3 = document.form1.sel3.value;
		var s4 = document.form1.sel4.value;
		var s5 = document.form1.sel5.value;
		
		var str = s1+"-"+s2+"-"+s3+"-"+s4+"-"+s5;
		
		return str;
	}
	var f = function() {

		var str = strmaker();
		//document.getElementById('lecSelect').innerHTML="";
		
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange=function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	document.getElementById("lecSelect").innerHTML = xhttp.responseText;
		    }
		  };
	    xhttp.open("GET", "te-lec-select.do?str="+encodeURI(str), true);
	    xhttp.send();

	}; 
	var stuList_find = function(){
		var str = strmaker();
				
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange=function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	document.getElementById("lectureInfo").innerHTML = xhttp.responseText;
		    	stuList_find2();
		    }
		  };
	    xhttp.open("GET", "te-lec-stu.do?str="+encodeURI(str), true);
	    xhttp.send();
	    
	}
	
	var stuList_find2 = function(){
		var str = strmaker();
				
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange=function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	document.getElementById("studentInfo").innerHTML = xhttp.responseText;
		    }
		  };
	    xhttp.open("GET", "te-lec-stu-list.do?str="+encodeURI(str), true);
	    xhttp.send();
	}
	var j = function(rowno, colno, v) {
		var str = strmaker();
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange=function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	document.getElementById("studentInfo").innerHTML=xhttp.responseText;
		    }
		  };
	    xhttp.open("GET", "te-lec-stu-check.do?rowno="+encodeURI(rowno)+"&colno="+encodeURI(colno)+"&v="+encodeURI(v)+"&str="+encodeURI(str), true);
	    xhttp.send();
	};
</script>
	
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

<!-- <td><font color="green"><b>보</b></font></td> -->