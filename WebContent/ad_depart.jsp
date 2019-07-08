    <!-------------------------------------------------------------------------------->
        <!-- 프로그램 : 인덕대학교 컴퓨터소프트웨어학과 전자출석 Demo -->
        <!-- -->
        <!-- 소속 : 인덕대학교 컴퓨터소프트웨어학과 창업동아리 겜지기 -->
        <!-- 교수 : 윤형태 (2019.5 - ) -->
        <!-- 학생 : 유소영(3), 김해리(3), 이민호(2), 김진혁(2) -->
        <!-------------------------------------------------------------------------------->
        <%@ page contentType="text/html;charset=utf-8" %>
        <%@ page import="java.util.*, java.sql.*, java.io.*" %>
            <% request.setCharacterEncoding("utf-8"); %>
        <%@ include file="common.jsp" %>
        <%@ include file="main_top.jsp" %>
            <%
	// 이전 문서의 변수들 (page는 예약어) ----------------------------------------------
	String text1 = request.getParameter("text1") == null ? "" : request.getParameter("text1");
	int npage= request.getParameter("page")==null ? npage=1 :  Integer.parseInt(request.getParameter("page"));

	// 레코드개수 세기  ----------------------------------------------
	String where = text1=="" ? "" : "where name like '"+text1+"%'";
	query="select count(*) from depart "+where;
	int count=rowcount(query);

	// 현재 페이지의 레코드위치 계산 및 해당 페이지 읽기 -------------------------------
	int	start = (npage-1) * page_line;

	query="select * from depart "+where+" order by name limit "+start+","+page_line+";";
	rs = stmt.executeQuery(query);
%>
        <!------------------------------------------------------------------------------>
        <!-- 내용 시작 -->
        <!------------------------------------------------------------------------------>
        <div class="row">
        <div class="col-xl-12">
        <div class="breadcrumb-holder">
        <h1 class="main-title float-left">교무처</h1>
        <ol class="breadcrumb float-right">
        <li class="breadcrumb-item">Home</li>
        <li class="breadcrumb-item">직원</li>
        <li class="breadcrumb-item active">학과 및 부서</li>
        </ol>
        <div class="clearfix"></div>
        </div>
        </div>
        </div>

        <div class="row">

        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="card mb-3">
        <div class="card-header mycolor3" style="padding:10px">
        <h3><i class="fa fa-table"></i> 학과 및 부서</h3>
        </div>

        <div class="card-body" style="padding:10px">

        <script>
        function find_text()
        {
        if (!form1.text1.value)
        form1.action="/member/lists/page";
        else
        form1.action="/member/lists/text1/" + form1.text1.value+"/page";
        form1.submit();
        }
        </script>

        <form name="form1" method="post" action="">
        <div class="row" style="margin-bottom:5px">
        <div class="col-auto" style="text-align:left">
        <div class="form-inline">
        <div class="input-group input-group-sm">
        <div class="input-group-prepend">
        <span class="input-group-text">이름</span>
        </div>
        <input type="text" name="text1" size="10" value="" class="form-control"
        onKeydown="if (event.keyCode == 13) { find_text(); }" >
        <div class="input-group-append">
        <button class="btn btn-sm mycolor1" type="button" onClick="find_text();">검색</button>
        </div>
        </div>
        </div>
        </div>
        <div class="col" align="right">
        <a href="ad_departnew.html" class="btn btn-sm mycolor1">추가</a>
        </div>
        </div>
        </form>

        <table class="table table-bordered table-hover table-responsive-sm mytable" style="width:100%">
        <thead>
        <tr class="mycolor1">
        <th>번호</th>
        <th>학과/부서명</th>
        <th>학제</th>
        <th>반수</th>
        <th width="95"></th>
        </tr>
        </thead>
        <tbody>
            <%
	while( rs.next() )
	{
		String id	=rs.getString("id");
		String name=rs.getString("name");
		String classnum	=rs.getString("classnum");
		String gradesystem	=rs.getString("gradesystem");
%>
        <tr>
        <td><%=id %></td>
        <td style="text-align:left"><%=name %></td>
        <td><%=classnum %></td>
        <td><%=gradesystem %></td>
        <td>
        <a href="ad_departEdit.jsp?id=<%=id %>" class="btn btn-xs btn-outline-primary">수정</a>
        <a href="ad_departDelete.jsp?id=<%=id %>" class="btn btn-xs btn-outline-danger" onClick="return confirm('삭제할까요
        ?');">삭제</a>
        </td>
        </tr>
            <%
	}
%>
        </tbody>
        </table>

        <nav>
        <ul class='pagination pagination-sm justify-content-center'>
        <li class='page-item'><a class="page-link" href="#">◀</a></li>
        <li class='page-item'><a class="page-link" href="#">◁</a></li>
        <li class='page-item'><a class="page-link" href="#">2</a></li>
        <li class='page-item'><a class="page-link" href="#">3</a></li>
        <li class='page-item active'><span class='page-link' style='background-color:steelblue'>4</span></li>
        <li class='page-item'><a class="page-link" href="#">5</a></li>
        <li class='page-item'><a class="page-link" href="#">6</a></li>
        <li class='page-item'><a class="page-link" href="#">▷</a></li>
        <li class='page-item'><a class="page-link" href="#">▶</a></li>
        </ul>
        </nav>

        </div> <!-- card body end -->
        </div> <!-- card end -->
        </div>

        </div> <!-- row end -->
        <!------------------------------------------------------------------------------>
        <!-- 내용 끝 -->
        <!------------------------------------------------------------------------------>
        <%@ include file="main_bottom.jsp" %>

            <%
			rs.close();
			stmt.close();
			conn.close();
		%>