<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<!--상단 메뉴 시작 -->
	<div class="headerbar">

        <div class="headerbar-left">
			<a href="index.html" class="logo"><img src="my/images/induk_logo.png"> <span>전자출석 Demo</span></a>
        </div>

        <nav class="navbar-custom">
			<ul class="list-inline float-right mb-0">
				<li class="list-inline-item dropdown notif">
					<a class="nav-link dropdown-toggle nav-user" data-toggle="dropdown" href="#" role="button" ariaaspopup="false" aria-expanded="false">
						<img src="my/images/avatars/admin.png" alt="Profile image" class="avatar-rounded">
					</a>
					<div class="dropdown-menu dropdown-menu-right profile-dropdown">
						<div class="dropdown-item noti-title">
							<h5 class="text-overflow"><small>Hello, admin</small> </h5>
						</div>
						<a href="#" class="dropdown-item notify-item">
							<i class="fa fa-power-off"></i> <span>Logout</span>
						</a>
					</div>
				</li>
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

					<li class="submenu">
						<a href="#"><i class="fa fa-fw fa-table"></i> <span> 직원(학사행정) </span> <span class="menu-arrow"></span></a>
						<ul class="list-unstyled">
							<li><a href="ad_main.html" style="padding:5px 0 5px 40px;">직원 메인</a></li>
							<li><a href="ad_control.html" style="padding:5px 0 5px 40px;">제어판</a></li>
							<li><a href="notice-list.do" style="padding:5px 0 5px 40px;">공지사항</a></li>
							<li><hr style="background-color:gray;margin:0 25px 0 25px;"></li>
							<li><a href="student-list.do" style="padding:5px 0 5px 40px;">학생정보</a></li>
							<li><a href="TeacherInquiry" style="padding:5px 0 5px 40px;">교수정보</a></li>
							<li><a href="AssistInquiry" style="padding:5px 0 5px 40px;">조교정보</a></li>
							<li><hr style="background-color:gray;margin:0 25px 0 25px;"></li>
							<li><a href="depart-list.do" style="padding:5px 0 5px 40px;">학과/부서</a></li>
							<li><a href="room-list.do" style="padding:5px 0 5px 40px;">강의실</a></li>
							<li><a href="building-list.do"style="padding:5px 0 5px 40px;">건물</a></li>
							<li><a href="holiday-list.do" style="padding:5px 0 5px 40px;">휴일</a></li>
						</ul>
					</li>
					<li class="submenu">
						<a href="#"><i class="fa fa-fw fa-table"></i> <span> 직원(전자출석) </span> <span class="menu-arrow"></span></a>
						<ul class="list-unstyled">
							<li><a href="ad_timeall.html" style="padding:5px 0 5px 40px;">학과별 시간표</a></li>
							<li><a href="ad_timeteacher.html" style="padding:5px 0 5px 40px;">교수별 강의현황</a></li>
							<li><a href="ad_lecmove.html" style="padding:5px 0 5px 40px;">휴보강</a></li>
						</ul>
					</li>
					<li class="submenu">
						<a href="#"><i class="fa fa-fw fa-male"></i> <span>조교</span> <span class="menu-arrow"></span></a>
						<ul class="list-unstyled">
							<li><a href="as_main.html" style="padding:5px 0 5px 40px;">조교 메인</a></li>
							<li><a href="subject-list.do" style="padding:5px 0 5px 40px;">학년별 교과목</a></li>
							<li><a href="lecture-list.do" style="padding:5px 0 5px 40px;">반별 교과목</a></li>
							<li><a href="as_time.html" style="padding:5px 0 5px 40px;">시간표 작성</a></li>
							<li><a href="as_timeall.html" style="padding:5px 0 5px 40px;">학과별 시간표</a></li>
							<li><a href="as_lecall.html" style="padding:5px 0 5px 40px;">과목별 출석부</a></li>
							<li><a href="as_lecmove.html" style="padding:5px 0 5px 40px;">휴보강</a></li>
						</ul>
					</li>
					<li class="submenu">
						<a href="#"><i class="fa fa-fw fa-user"></i> <span> 교수 </span> <span class="menu-arrow"></span></a>
						<ul class="list-unstyled">
							<li><a href="te_main.html" style="padding:5px 0 5px 40px;">교수 메인</a></li>
							<li><a href="timetable-detail.do" style="padding:5px 0 5px 40px;">시간표</a></li>
							<li><a href="te_lec.html" style="padding:5px 0 5px 40px;">일별 출석부</a></li>
							<li><a href="te_lecall.html" style="padding:5px 0 5px 40px;">과목별 출석부</a></li>
							<li><a href="te_lecmove.html" style="padding:5px 0 5px 40px;">휴보강</a></li>
							<li><a href="te_lecqa.html" style="padding:5px 0 5px 40px;">교과목 문의</a></li>
						</ul>
					</li>
					<li class="submenu">
						<a href="#"><i class="fa fa-fw fa-table"></i> <span> 학생 </span> <span class="menu-arrow"></span></a>
						<ul class="list-unstyled">
							<li><a href="st_main.html" style="padding:5px 0 5px 40px;">학생 메인</a></li>
							<li><a href="st_time.html" style="padding:5px 0 5px 40px;">시간표</a></li>
							<li><a href="st_lecall.html" style="padding:5px 0 5px 40px;">출석부</a></li>
							<li><hr style="background-color:gray;margin:0 25px 0 25px;"></li>
							<li><a href="st_lec.html" style="padding:5px 0 5px 40px;">수강신청</a></li>
							<li><a href="st_lecsj.html" style="padding:5px 0 5px 40px;">수강과목</a></li>
							<li><a href="st_lecqa.html" style="padding:5px 0 5px 40px;">교과목 문의</a></li>
						</ul>
					</li>

				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- 좌측 Sidebar 메뉴 끝-->