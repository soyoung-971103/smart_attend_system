<%
	Class.forName("com.mysql.jdbc.Driver");

	url="jdbc:mysql://cs.induk.ac.kr:53306/attend?useUnicode=true&characterEncoding=utf8";
	conn = DriverManager.getConnection(url,"attend","attenddb");
	stmt=conn.createStatement();
%>

<%!
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String query= null;
	String url;
	int i,j;

	int page_line=5;			// 페이지당 line 수
	int page_block=5;		// 블록당   page 수

	// 레코드개수 세는 함수 ----------------------------------------------
	public Integer rowcount(String sql)
	{
		int c=0;
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			c=rs.getInt(1);
		} catch (SQLException e) { e.printStackTrace(); }
		return c;
	}

	// 하단 Pagination 만드는 함수 ----------------------------------------------
	public String pagination(int nowpage, int recordcount, String nowurl) 
	{
		int pages=(int)( Math.ceil((float)recordcount/(float)page_line) );	// 페이지수
		int blocks=(int)( Math.ceil((float)pages/(float)page_block) );		// 전체 블록수
		int block =(int)( Math.ceil((float)nowpage/(float)page_block) );	// 현재 블록
		int page_s  = page_block * (block-1);		// 현재 페이지
		int page_e  = page_block * block;				// 마지막 페이지
		if (blocks <= block) page_e = pages;

		String s="<table width='400' border='0' cellspacing='0' cellpadding='0'><tr><td  height='30' align='center'>";

		if (block>1)	// 이전 블록으로
			s+="&nbsp;<a href='"+nowurl+"&npage="+page_s+"'><img src='images/i_prev.gif' align='absmiddle' border='0'></a>&nbsp;";

		for (int i=page_s+1; i<=page_e; i++)	// 페이지들 표시
		{
			 if (nowpage == i)
				s+="&nbsp;<font color='red'><b>"+i+"</b></font>&nbsp;";
		   else
				s+="&nbsp;<a href='"+nowurl+"&npage="+i+"'>["+i+"]</a>&nbsp;";
		}

		if (block < blocks)    // 다음 블록으로
			s+="&nbsp;<a href='"+nowurl+"&npage="+(page_e+1)+"'><img src='images/i_next.gif' align='absmiddle' border='0'></a>&nbsp;";

		s+="</td></tr></table>";

        return s;
    }

%>
