<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
</script>
<%
	String dropUl[] = new String[5];
	String dropA[] = new String[5];
	String activA[][] = new String[5][10];
	String url = request.getRequestURI();
	String conPath = request.getContextPath();
	String com = url.substring(conPath.length());
	url = com;
	pageContext.setAttribute("url", url);
	for (int i = 0; i < 5; i++) {
		dropUl[i] = "";
		dropA[i] = "";
		for (int j = 0; j < 10; j++) {
			activA[i][j] = "";
		}
	}

	//직원(학사행정)
	if (url.equals("/ad_main.html")) {
		dropUl[0] = "display:block";
		dropA[0] = "subdrop";
		activA[0][0] = "subdrop";
	} else if (url.equals("/ad_control.html")) {
		dropUl[0] = "display:block";
		dropA[0] = "subdrop";
		activA[0][1] = "subdrop";
	} else if (url.equals("/ad_notice.jsp")) {
		dropUl[0] = "display:block";
		dropA[0] = "subdrop";
		activA[0][2] = "subdrop";
	} else if (url.equals("/ad_student.jsp")) {
		dropUl[0] = "display:block";
		dropA[0] = "subdrop";
		activA[0][3] = "subdrop";
	} else if (url.equals("/ad_teacher.jsp")) {
		dropUl[0] = "display:block";
		dropA[0] = "subdrop";
		activA[0][4] = "subdrop";
	} else if (url.equals("/ad_assist.jsp")) {
		dropUl[0] = "display:block";
		dropA[0] = "subdrop";
		activA[0][5] = "subdrop";
	} else if (url.equals("/ad_depart.jsp")) {
		dropUl[0] = "display:block";
		dropA[0] = "subdrop";
		activA[0][6] = "subdrop";
	} else if (url.equals("/ad_room.jsp")) {
		dropUl[0] = "display:block";
		dropA[0] = "subdrop";
		activA[0][7] = "subdrop";
	} else if (url.equals("/ad_building.jsp")) {
		dropUl[0] = "display:block";
		dropA[0] = "subdrop";
		activA[0][8] = "subdrop";
	} else if (url.equals("/ad_holiday.jsp")) {
		dropUl[0] = "display:block";
		dropA[0] = "subdrop";
		activA[0][9] = "subdrop";
	}

	//직원(전자출석)
	if (url.equals("/ad_timeall.html")) {
		dropUl[1] = "display:block";
		dropA[1] = "subdrop";
		activA[1][0] = "subdrop";
	} else if (url.equals("/ad_timeteacher.html")) {
		dropUl[1] = "display:block";
		dropA[1] = "subdrop";
		activA[1][1] = "subdrop";
	} else if (url.equals("/ad_lecmove.jsp")) {
		dropUl[1] = "display:block";
		dropA[1] = "subdrop";
		activA[1][2] = "subdrop";
	}

	//조교
	if (url.equals("/as_main.jsp")) {
		dropUl[2] = "display:block";
		dropA[2] = "subdrop";
		activA[2][0] = "subdrop";
	} else if (url.equals("/as_timeall.jsp")) {
		dropUl[2] = "display:block";
		dropA[2] = "subdrop";
		activA[2][1] = "subdrop";
	} else if (url.equals("/as_lecall.jsp")) {
		dropUl[2] = "display:block";
		dropA[2] = "subdrop";
		activA[2][2] = "subdrop";
	} else if (url.equals("/as_time.jsp")) {
		dropUl[2] = "display:block";
		dropA[2] = "subdrop";
		activA[2][3] = "subdrop";
	} else if (url.equals("/as_timeall.jsp")) {
		dropUl[2] = "display:block";
		dropA[2] = "subdrop";
		activA[2][4] = "subdrop";
	} else if (url.equals("/as_lecall.jsp")) {
		dropUl[2] = "display:block";
		dropA[2] = "subdrop";
		activA[2][5] = "subdrop";
	} else if (url.equals("/as_lecmove.jsp")) {
		dropUl[2] = "display:block";
		dropA[2] = "subdrop";
		activA[2][6] = "subdrop";
	}

	//교수
	if (url.equals("/te_main.jsp")) {
		dropUl[3] = "display:block";
		dropA[3] = "subdrop";
		activA[3][0] = "subdrop";
	} else if (url.equals("/te_time.jsp")) {
		dropUl[3] = "display:block";
		dropA[3] = "subdrop";
		activA[3][1] = "subdrop";
	} else if (url.equals("/te_lec.jsp")) {
		dropUl[3] = "display:block";
		dropA[3] = "subdrop";
		activA[3][2] = "subdrop";
	} else if (url.equals("/te_lecall.jsp")) {
		dropUl[3] = "display:block";
		dropA[3] = "subdrop";
		activA[3][3] = "subdrop";
	} else if (url.equals("/te_lecmove.jsp")) {
		dropUl[3] = "display:block";
		dropA[3] = "subdrop";
		activA[3][4] = "subdrop";
	} else if (url.equals("/te_lecqa.jsp")) {
		dropUl[3] = "display:block";
		dropA[3] = "subdrop";
		activA[3][5] = "subdrop";
	}

	//학생
	if (url.equals("/st_main.html")) {
		dropUl[3] = "display:block";
		dropA[4] = "subdrop";
		activA[4][0] = "subdrop";
	} else if (url.equals("/st_time.jsp")) {
		dropUl[4] = "display:block";
		dropA[4] = "subdrop";
		activA[4][1] = "subdrop";
	} else if (url.equals("/st_lecall.jsp")) {
		dropUl[4] = "display:block";
		dropA[4] = "subdrop";
		activA[4][2] = "subdrop";
	} else if (url.equals("/st_lec.jsp")) {
		dropUl[4] = "display:block";
		dropA[4] = "subdrop";
		activA[4][3] = "subdrop";
	} else if (url.equals("/st_lecsj.jsp")) {
		dropUl[4] = "display:block";
		dropA[4] = "subdrop";
		activA[4][4] = "subdrop";
	} else if (url.equals("/st_lecqa.jsp")) {
		dropUl[4] = "display:block";
		dropA[4] = "subdrop";
		activA[4][5] = "subdrop";
	}

	pageContext.setAttribute("dropUl", dropUl);
	pageContext.setAttribute("dropA", dropA);
	pageContext.setAttribute("activA", activA);
%>

<!--상단 메뉴 시작 -->
<div class="headerbar">

	<div class="headerbar-left">
		<a href="index.html" class="logo"><img
			src="my/images/induk_logo.png"> <span>전자출석 Demo</span></a>
	</div>

	<nav class="navbar-custom">
		<ul class="list-inline float-right mb-0">
			<li class="list-inline-item dropdown notif"><a
				class="nav-link dropdown-toggle nav-user" data-toggle="dropdown"
				href="#" role="button" ariaaspopup="false" aria-expanded="false">
					<img src="my/images/avatars/admin.png" alt="Profile image"
					class="avatar-rounded">
			</a>
				<div class="dropdown-menu dropdown-menu-right profile-dropdown">
					<div class="dropdown-item noti-title">
						<h5 class="text-overflow">
							<small>Hello, admin</small>
						</h5>
					</div>
					<a href="#" class="dropdown-item notify-item"> <i
						class="fa fa-power-off"></i> <span>Logout</span>
					</a>
				</div></li>
		</ul>

		<ul class="list-inline menu-left mb-0">
			<li class="float-left">
				<button class="button-menu-mobile open-left">
					<i class="fa fa-fw fa-bars"></i>
				</button>
			</li>
		</ul>
	</nav>

</div>
<!--상단 메뉴 끝 -->


<!-- 좌측 Sidebar 메뉴 시작-->
<div class="left main-sidebar">
	<div class="sidebar-inner leftscroll">
		<div id="sidebar-menu">
			<ul>
				<li class="submenu"><a href="#"
					class="<c:out value="${dropA[0]}"/>"><i
						class="fa fa-fw fa-table"></i> <span> 직원(학사행정) </span> <span
						class="menu-arrow"></span></a>
					<ul class="list-unstyled" style="<c:out value="${dropUl[0]}"/>">
						<li><a href="admain_list.do"
							class="<c:out value="${activA[0][0]}"/>"
							style="padding: 5px 0 5px 40px;">직원 메인</a></li>
						<li><a href="control-list.do"
							class="<c:out value="${activA[0][1]}"/>"
							style="padding: 5px 0 5px 40px;">제어판</a></li>
						<li><a href="notice-list.do"
							class="<c:out value="${activA[0][2]}"/>"
							style="padding: 5px 0 5px 40px;">공지사항</a></li>
						<li><hr
								style="background-color: gray; margin: 0 25px 0 25px;"></li>
						<li><a href="student-list.do"
							class="<c:out value="${activA[0][3]}"/>"
							style="padding: 5px 0 5px 40px;">학생정보</a></li>
						<li><a href="teacher-list.do"
							class="<c:out value="${activA[0][4]}"/>"
							style="padding: 5px 0 5px 40px;">교수정보</a></li>
						<li><a href="assist-list.do"
							class="<c:out value="${activA[0][5]}"/>"
							style="padding: 5px 0 5px 40px;">조교정보</a></li>
						<li><hr
								style="background-color: gray; margin: 0 25px 0 25px;"></li>
						<li><a href="depart-list.do"
							class="<c:out value="${activA[0][6]}"/>"
							style="padding: 5px 0 5px 40px;">학과/부서</a></li>
						<li><a href="room-list.do"
							class="<c:out value="${activA[0][7]}"/>"
							style="padding: 5px 0 5px 40px;">강의실</a></li>
						<li><a href="building-list.do"
							class="<c:out value="${activA[0][8]}"/>"
							style="padding: 5px 0 5px 40px;">건물</a></li>
						<li><a href="holiday-list.do"
							class="<c:out value="${activA[0][9]}"/>"
							style="padding: 5px 0 5px 40px;">휴일</a></li>
					</ul></li>
				<li class="submenu"><a href="#"
					class="<c:out value="${dropA[1]}"/>"><i
						class="fa fa-fw fa-table"></i> <span> 직원(전자출석) </span> <span
						class="menu-arrow"></span></a>
					<ul class="list-unstyled" style="<c:out value="${dropUl[1]}"/>">
						<li><a href="ad-timetable-all.do"
							class="<c:out value="${activA[1][0]}"/>"
							style="padding: 5px 0 5px 40px;">학과별 시간표</a></li>
						<li><a href="ad-te-lectureList.do"
							class="<c:out value="${activA[1][1]}"/>"
							style="padding: 5px 0 5px 40px;">교수별 강의현황</a></li>
						<li><a href="ad-lecmove-list.do"
							class="<c:out value="${activA[1][2]}"/>"
							style="padding: 5px 0 5px 40px;">휴보강</a></li>
					</ul></li>
				<li class="submenu"><a href="#"
					class="<c:out value="${dropA[2]}"/>"><i
						class="fa fa-fw fa-male"></i> <span>조교</span> <span
						class="menu-arrow"></span></a>
					<ul class="list-unstyled" style="<c:out value="${dropUl[2]}"/>">
						<li><a href="assistmain_list.do"
							class="<c:out value="${activA[2][0]}"/>"
							style="padding: 5px 0 5px 40px;">조교 메인</a></li>
						<c:forEach var="control" items="${controlList}">
							<c:if test="${control.subjecttime == 0}">
								<li><a href="subject-list.do"
									class="<c:out value="${activA[2][1]}"/>"
									style="padding: 5px 0 5px 40px;">학년별 교과목</a></li>
								<li><a href="as-lecture-list.do"
									class="<c:out value="${activA[2][2]}"/>"
									style="padding: 5px 0 5px 40px;">반별 교과목</a></li>
							</c:if>
						</c:forEach>
						<c:forEach var="control" items="${controlList}">
							<c:if test="${control.lecturetime == 0}">
								<li><a href="timetable-list.do"
									class="<c:out value="${activA[2][3]}"/>"
									style="padding: 5px 0 5px 40px;">시간표 작성</a></li>
							</c:if>
						</c:forEach>
						<li><a href="as-timetable-all.do"
							class="<c:out value="${activA[2][4]}"/>"
							style="padding: 5px 0 5px 40px;">학과별 시간표</a></li>
						<li><a href="lecture-sublecture.do"
							class="<c:out value="${activA[2][5]}"/>"
							style="padding: 5px 0 5px 40px;">과목별 출석부</a></li>
						<li><a href="as-lecmove-list.do"
							class="<c:out value="${activA[2][6]}"/>"
							style="padding: 5px 0 5px 40px;">휴보강</a></li>
					</ul></li>

				<li class="submenu"><a href="#"
					class="<c:out value="${dropA[3]}"/>"><i
						class="fa fa-fw fa-user"></i> <span> 교수 </span> <span
						class="menu-arrow"></span></a>
					<ul class="list-unstyled" style="<c:out value="${dropUl[3]}"/>">
						<li><a href="te-main.do"
							class="<c:out value="${activA[3][0]}"/>"
							style="padding: 5px 0 5px 40px;">교수 메인</a></li>
						<li><a href="timetable-tdetail.do"
							class="<c:out value="${activA[3][1]}"/>"
							style="padding: 5px 0 5px 40px;">시간표</a></li>
						<li><a href="te_lec.jsp"
							class="<c:out value="${activA[3][2]}"/>"
							style="padding: 5px 0 5px 40px;">일별 출석부</a></li>
						<li><a href="te-lecall.do"
							class="<c:out value="${activA[3][3]}"/>"
							style="padding: 5px 0 5px 40px;">과목별 출석부</a></li>
						<li><a href="te_lecmove.jsp"
							class="<c:out value="${activA[3][4]}"/>"
							style="padding: 5px 0 5px 40px;">휴보강</a></li>
						<li><a href="teacher-qalist.do"
							class="<c:out value="${activA[3][5]}"/>"
							style="padding: 5px 0 5px 40px;">교과목 문의</a></li>
					</ul></li>
				<li class="submenu"><a href="#"
					class="<c:out value="${dropA[4]}"/>"><i
						class="fa fa-fw fa-table"></i> <span> 학생 </span> <span
						class="menu-arrow"></span></a>
					<ul class="list-unstyled" style="<c:out value="${dropUl[4]}"/>">
						<li><a href="student-main.do"
							class="<c:out value="${activA[4][0]}"/>"
							style="padding: 5px 0 5px 40px;">학생 메인</a></li>
						<li><a href="mylecture-sdetail.do"
							class="<c:out value="${activA[4][1]}"/>"
							style="padding: 5px 0 5px 40px;">시간표</a></li>
						<li><a href="lecture-mylecture.do"
							class="<c:out value="${activA[4][2]}"/>"
							style="padding: 5px 0 5px 40px;">출석부</a></li>
						<li><hr
								style="background-color: gray; margin: 0 25px 0 25px;"></li>
						<li><a href="lecture-list.do"
							class="<c:out value="${activA[4][3]}"/>"
							style="padding: 5px 0 5px 40px;">수강신청</a></li>
						<li><a href="Mylecture-list.do"
							class="<c:out value="${activA[4][4]}"/>"
							style="padding: 5px 0 5px 40px;">수강과목</a></li>
						<li><a href="student-qna.do"
							class="<c:out value="${activA[4][5]}"/>"
							style="padding: 5px 0 5px 40px;">교과목 문의</a></li>
					</ul></li>
			</ul>

			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<!-- 좌측 Sidebar 메뉴 끝-->
