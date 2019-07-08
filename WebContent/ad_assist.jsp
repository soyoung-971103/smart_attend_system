<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*, java.sql.*, java.io.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<%@ include file="common.jsp" %>
<%@ include file="main_top.jsp" %>

<%

	// 이전 문서의 변수들 (page는 예약어) ----------------------------------------------
	String text1 = request.getParameter("text1");
	if(text1 == null) text1 = "";

	int npage= request.getParameter("npage")==null ? npage=1 :  Integer.parseInt(request.getParameter("npage"));

	// 레코드개수 세기  ----------------------------------------------
	String where = text1=="" ? "" : "where name like '"+text1+"%'";
	query="select count(*) from staff "+where;
	int count=rowcount(query);

	// 현재 페이지의 레코드위치 계산 및 해당 페이지 읽기 -------------------------------
	int	start = (npage-1) * page_line;

	query="select * from staff "+where+" order by name limit "+start+","+page_line+";";
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
								<li class="breadcrumb-item active">교수</li>
							</ol>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>

				<div class="row">

					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
						<div class="card mb-3">
							<div class="card-header mycolor3" style="padding:10px">
								<h3><i class="fa fa-table"></i> 조교</h3>
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
									<div class="col-auto" align="left">
										<div class="form-inline">
											<div class="input-group input-group-sm">
												<div class="input-group-prepend">
													<span class="input-group-text">이름</span>
												</div>
												<input type="text" name="text1" size="10" value="<%= text1 %>" class="form-control">
												<div class="input-group-append">
													<button class="btn btn-sm mycolor1" type="button" >검색</button>
												</div>
											</div>
										</div>
									</div>
									<div class="col" align="right">
										<a href="ad_assistnew.html" class="btn btn-sm mycolor1">추가</a>
									</div>
								</div>
								</form>

								<table class="table table-bordered table-hover table-responsive-sm mytable" style="width:100%;" id="example">
									<tr class="mycolor1">
										<th>학과</th>
										<th>이름</th>
										<th>전화</th>
										<th>핸드폰</th>
										<th>이메일</th>
										<th width="95"></th>
									</tr>

									<%
										while(rs.next()){
											String id = rs.getString("id");
											String depart_id = rs.getString("depart_id");

											if(depart_id.equals("1")) depart_id = "컴소과";
											else if(depart_id.equals("2"))
												depart_id = "전자과";

											String name = rs.getString("name");
											String tel = rs.getString("tel");
											String phone = rs.getString("phone");
											String email = rs.getString("email");

									%>
										<tr>
										<td><%= depart_id %></td>
										<td><%= name %></td>
										<td><%= tel %></td>
										<td><%= phone %></td>
										<td><%= email %></td>
										<td>
											<a href="ad_assistupdate.jsp?id=<%=id%>" class="btn btn-xs btn-outline-primary">수정</a>
											<a href="ad_assistMA.jsp?id=<%=id%>&MA=DELETE" class="btn btn-xs btn-outline-danger" onClick="return confirm('삭제할까요 ?');">삭제</a>
										</td>
										</tr>
									<% }
									%>

								</table>

								<%
									String nurl = "ad_assist.jsp?text1="+text1;
									out.println(pagination(npage, count, nurl));
								%>

							</div>		<!-- card body end -->
						</div>		<!-- card end -->
					</div>

				</div>	<!-- row end -->
<!---------------------------------------- --------------------->
<%@ include file="main_bottom.jsp" %>
