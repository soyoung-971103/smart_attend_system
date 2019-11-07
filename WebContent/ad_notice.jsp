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
<!------------------------------------------------------------------------------>
<!-- 내용 시작 -->
<!------------------------------------------------------------------------------>
            
			<div class="container-fluid">
										
				<div class="row">
					<div class="col-xl-12">
						<div class="breadcrumb-holder">
							<h1 class="main-title float-left">직원</h1>
							<ol class="breadcrumb float-right">
								<li class="breadcrumb-item">Home</li>
								<li class="breadcrumb-item">직원</li>
								<li class="breadcrumb-item active">학생</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div class="card mb-3">
							<div class="card-header mycolor3" style="padding:10px">
								<h3><i class="fa fa-table"></i> 공지사항</h3>
							</div>
								
							<div class="card-body" style="padding: 10px">

									<script>
										function find_text() {
				
												form1.action = "notice-list.do?text1="+ form1.text1.value
											form1.submit();
										}
									</script>

									<form name="form1" method="post" action="notice-list.do">
										<div class="row" style="margin-bottom: 5px">
											<div class="col-auto" align="left">
												<div class="form-inline">
													<div class="input-group input-group-sm">
														<div class="input-group-prepend">
															<span class="input-group-text">제목</span>
														</div>
														<input type="text" name="text1" size="10" value=""
															class="form-control"
															onKeydown="if (event.keyCode == 13) { find_text(); }">
														<div class="input-group-append">
															<button class="btn btn-sm mycolor1" type="button"
																onClick="find_text();">검색</button>
														</div>
													</div>
												</div>
											</div>
											<div class="col" align="right">
												<a href="ad_noticenew.jsp" class="btn btn-sm mycolor1">추가</a>
											</div>
										</div>
									</form>
								<table
										class="table table-bordered table-hover table-responsive-sm mytable"
										style="width: 100%">
										<thead>
											<tr class="mycolor1">
											<th>날짜</th>
											<th>제목</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
									<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
									<c:forEach var="dto" items="${ noticelist }">
										<tr>
											<td>${ dto.writeday }</td>
										<td style="text-align:left">${ dto.title }</td>
										<td>
											<a href="notice-detail.do?id=${ dto.id }" class="btn btn-xs btn-outline-primary">수정</a>
											<a href="notice-delete.do?id=${ dto.id }" class="btn btn-xs btn-outline-danger" onClick="return confirm('삭제할까요 ?');">삭제</a>
										</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
								<nav>
										${pagination}
									</nav>
							</div>														
						</div>
					</div>
						
				</div>	

			</div>

<!------------------------------------------------------------------------------>
<!-- 내용 끝 -->
<!------------------------------------------------------------------------------>
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

<script>

	$(document).ready(function() {
		$('#example').DataTable( { 
			lengthChange: true,		// 표시 건수기능 숨기기
			lengthMenu: [ 10, 20, 30, 40, 50 ],
			searching: true,			// 검색 기능 숨기기
			ordering: true,				// 정렬 기능 숨기기
			info: true,					// 정보 표시 숨기기
			paging: true,				// 페이징 기능 숨기기
			order: [ [ 0, "asc" ] ],	// 0번째칼럼 정렬,복합정렬 가능
			scrollX: true,				// 가로 스크롤바
			scrollY: false,				// 세로스크롤바 : 세로길이(px) 지정가능
			language: {
					"emptyTable": "데이터가 없음.",
					"lengthMenu": "페이지당 _MENU_ 개",
					"info": "현재: _START_ - _END_ / _TOTAL_건",
					"infoEmpty": "데이터 없음",
					"infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
					"search": "이름검색: ",
					"zeroRecords": "일치하는 데이터가 없음.",
					"loadingRecords": "로딩중...",
					"processing":     "잠시만 기다려 주세요...",
					"paginate": { "first":"◀", "previous": "◁","next": " ▷","last": "▶" }
				},
			columns: [ { "searchable": true },{ "searchable":false },{ "searchable":false },{ "searchable":false },{ "searchable":false },{ "searchable":false } ],
			pagingType: "full_numbers"

		} );
	} );

</script>

</body>
</html>