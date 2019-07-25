<!-- 화살표 때문에 인코딩방식 설정 김진혁-->
<%
// 	 Class.forName("com.mysql.jdbc.Driver");

// 	url="jdbc:mysql://cs.induk.ac.kr:53306/attend?useUnicode=true&characterEncoding=utf8";
// 	conn = DriverManager.getConnection(url,"attend","attenddb");
// 	stmt=conn.createStatement(); 
%>
<%!
// 	String [] ProKinds = {"", "전임교수", "겸임교수", "시간강사"};
// 	String [] DepKinds = {"", "컴소과", "전자과", "통신과", "영어과", "시디과"};
// 	int DepKindsN = DepKinds.length;
// 	int ProkindsN = ProKinds.length;
// 	Connection conn = null;
// 	Statement stmt = null;
// 	ResultSet rs = null;
// 	String query= null;
// 	String url;
// 	int i,j;

// 	int page_line=5;		// 페이지당 line 수
// 	int page_block=5;		// 블록당   page 수

// 	// 레코드개수 세는 함수 ----------------------------------------------
// 	public Integer rowcount(String sql)
// 	{
// 		int c=0;
// 		try {
// 			rs = stmt.executeQuery(sql);
// 			rs.next();
// 			c=rs.getInt(1);
// 		} catch (SQLException e) { e.printStackTrace(); }
// 		return c;
// 	}

// 	// 하단 Pagination 만드는 함수 ----------------------------------------------
// 	public String pagination(int nowpage, int recordcount, String nowurl)
// 	{
// 		int pages=(int)( Math.ceil((float)recordcount/(float)page_line) );	// 페이지수
// 		int blocks=(int)( Math.ceil((float)pages/(float)page_block) );		// 전체 블록수
// 		int block =(int)( Math.ceil((float)nowpage/(float)page_block) );	// 현재 블록
// 		int page_s  = page_block * (block-1);		// 현재 페이지
// 		int page_e  = page_block * block;				// 마지막 페이지
// 		if (blocks <= block) page_e = pages;

// 		/*	교수 학생 조교 정보 볼 때 페이지 번호*/
// 		String s="<nav><ul class='pagination pagination-sm justify-content-center'><li class='page-item'><a class='page-link' href='#'>◀</a></li>";

// 		if (block>1)	// 이전 블록으로
// 			s+="<li class='page-item'><a class='page-link' href='"+nowurl+"&npage="+page_s+"'>◁</a></li>";

// 		for (int i=page_s+1; i<=page_e; i++)	// 페이지들 표시
// 		{
// 			 if (nowpage == i)
// 				s+="<li class='page-item active'><span class='page-link' style='background-color:steelblue'>"+i+"</span></li>";
// 		   else
// 				s+="<li class='page-item'><a class='page-link' href='"+nowurl+"&npage="+i+"'>"+i+"</a></li>";
// 		}

// 		if (block < blocks)    // 다음 블록으로
// 			s+="<li class='page-item'><a class='page-link' href='"+nowurl+"&npage="+(page_e+1)+"'>▷</a></li>";

// 		s+="</ul></nav>";
//         return s;
//     }
%>
