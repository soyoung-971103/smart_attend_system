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
	<script type="text/javascript">
		
	var n = function() {
		var k = <%=request.getParameter("id")%>;
		//document.getElementById('stuList').innerHTML="";
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange=function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	//document.getElementById("stuList").innerHTML=xhttp.responseText;
		    }
		  };
	    xhttp.open("GET", "student-lecture-ahcheck.do?id="+k, true);
	    xhttp.send();
	};
		var i = function(n) {
			
			if(n == undefined) n = 2;
			var id = <%=request.getParameter("id")%>;
			var lecture = "";

			document.getElementById('stuList').innerHTML="";
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange=function() {
			    if (this.readyState == 4 && this.status == 200) {
			    	document.getElementById("stuList").innerHTML=xhttp.responseText;
			    }
			  };
		    xhttp.open("GET", "teacher-lecture-info.do?n="+n+"&id="+id, true);
		    xhttp.send();
		};
		
		function choose1(rowno,colno)
		{
			pos1=rowno+"^"+colno;
			pos2=rowno+","+colno;
			document.getElementById( pos1 ).innerHTML="<div style='font-size:10px;'>"
			+"<i class='fa fa-circle-o fa-2x' onclick='choose3("+pos2+",0);'"+
			" onchange='j("+rowno+");' style='cursor:pointer'></i><br>"+
			"<i class='text-primary fa fa-times-circle-o fa-2x' onclick='choose3("+pos2+",1);'  style='cursor:pointer'>"+
			"</i><br><i class='text-danger fa fa-close fa-2x' onclick='choose3("+pos2+",2);' style='cursor:pointer'></font>";
		}
		//
		function choose3(rowno,colno, v)
		{
			pos1=rowno+"^"+colno;
			pos2=rowno+","+colno;
			if (v==0)			s="<i class='fa fa-circle-o fa-2x' onclick='choose1("+pos2+");' style='cursor:pointer'></i>";
			else if (v==1)	s="<i class='text-primary fa fa-times-circle-o fa-2x' onclick='choose1("+pos2+");'  style='cursor:pointer'></i>";
			else				s="<i class='text-danger fa fa-close fa-2x' onclick='choose1("+pos2+");' style='cursor:pointer'></i>";
			document.getElementById( pos1 ).innerHTML=s;
			j(rowno,colno, v);
			//stuList
		}
	</script>
</head>

<body class="adminbody" onLoad="i(2);">

<div id="main">

	<%@ include file="main_menu.jsp" %>
	<!-- 좌측 Sidebar 메뉴 끝-->
		
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
								<li class="breadcrumb-item">일별 출석부</li>
								<li class="breadcrumb-item active">강의출결</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div id="stuList" class="card mb-3">
							
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
		<span class="text-right">	Copyright 2019<a target="_blank" href="#">Induk University</a></span>
		<span class="float-right">Programmed by <a target="_blank" href="#"><b>Gamejigi</b></a></span>
	</footer>
</div>
<!-- js 선언부 ----------------------------------------------------------------->
<script type="text/javascript">
	//학생 개인 출석을 직접 눌렀을 때
	var j = function(rowno,colno, v) {
		var k = <%=request.getParameter("id")%>;
		document.getElementById('nStuCheck').innerHTML="";
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange=function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	document.getElementById("nStuCheck").innerHTML=xhttp.responseText;
		    }
		  };
	    xhttp.open("GET", "student-lecture-hcheck.do?rowno="+rowno+"&colno="+colno+"&v="+v+"&id="+k, true);
	    xhttp.send();
	};
</script>
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