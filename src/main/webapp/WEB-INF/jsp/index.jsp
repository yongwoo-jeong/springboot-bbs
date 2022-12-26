<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../css/index.css">
    <script type="text/javascript" defer src="js/index.js"></script>
    <meta charset="UTF-8" />
    <title>게시판 - 목록</title>
</head>
<body>
<div class="container">
    <header class="title"><h1>자유 게시판 - 목록</h1></header>
<%--    <form method="get" action=<%=request.getContextPath()%>/selectArticles.action>--%>
        <div class="search_nav">
            <div class="date_select_div">
                <span class="date_span">등록일</span>
                <input type="date" name="start_date" class="start_date"/>
                <span>~</span>
                <input type="date" name="last_date" class="end_date"/>
            </div>
            <div class="category_select_div">
                <div class="category_drop_down">
                    <form action="" method="post">
                        <label for="category"></label>
                        <select name="category" id="category">
                            <option value="">전체 카테고리</option>
                            <option value="JAVA">JAVA</option>
                            <option value="Javascript">Javascript</option>
                            <option value="Database">Database</option>
                        </select>
                    </form>
                </div>
            </div>
            <div class="search_input_div">
                <div>
                    <input type="text" name="keyword" placeholder="검색어를 입력해주세요. (제목 + 작성자 + 내용)" />
                </div>
                <input class="submit" type="submit" value="검색" />
            </div>
        </div>
<%--    </form>--%>
<%--    <div class="search_outcome">총 <%=totalArticle%>건</div>--%>
    <div class="post_container">
        <div class="post post_outline">
            <span>카테고리</span>
            <a class="post_title">제목</a>
            <span>작성자</span>
            <span>조회수</span>
            <span>등록일시</span>
            <span>수정일시</span>
        </div>
<%--        <%--%>
<%--            // articles 데이터 가져오기--%>

<%--        <% for(ArticleVO article : articles) {--%>
<%--        %>--%>
<%--        <div class="post">--%>
<%--            <span class="post_category"><%= new FindCategoryNameId().findCategoryName(article.getCategoryId())%></span>--%>
<%--            &lt;%&ndash;            <% System.out.println(po.getFile_id());%>&ndash;%&gt;--%>
<%--            &lt;%&ndash;            <% if ( true ){%>&ndash;%&gt;--%>
<%--            &lt;%&ndash;            <svg></svg>&ndash;%&gt;--%>
<%--            &lt;%&ndash;            <%} else {%>&ndash;%&gt;--%>
<%--            &lt;%&ndash;            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-5 h-5">&ndash;%&gt;--%>
<%--            &lt;%&ndash;              <path fill-rule="evenodd" d="M15.621 4.379a3 3 0 00-4.242 0l-7 7a3 3 0 004.241 4.243h.001l.497-.5a.75.75 0 011.064 1.057l-.498.501-.002.002a4.5 4.5 0 01-6.364-6.364l7-7a4.5 4.5 0 016.368 6.36l-3.455 3.553A2.625 2.625 0 119.52 9.52l3.45-3.451a.75.75 0 111.061 1.06l-3.45 3.451a1.125 1.125 0 001.587 1.595l3.454-3.553a3 3 0 000-4.242z" clip-rule="evenodd" />&ndash;%&gt;--%>
<%--            &lt;%&ndash;            </svg>&ndash;%&gt;--%>
<%--            &lt;%&ndash;            <%}%>&ndash;%&gt;--%>
<%--            <a class="post_title" href="/viewArticle.action?id=<%= article.getArticleId()%>"><%=(--%>
<%--                    article.getTitle().length() > 20) ? article.getTitle().substring(0,20)+"..." : article.getTitle()%></a>--%>
<%--            <span><%=article.getWriter()%></span>--%>
<%--            <span class="view_span"><%=article.getView()%></span>--%>
<%--            <span><%=article.getCreatedAt()%></span>--%>
<%--            <span></span>--%>
<%--        </div>--%>
<%--        <% } %>--%>
    </div>
    <div class="pagination_container">
<%--        <%  Integer currentPage = (Integer) request.getAttribute("currentPage");--%>
<%--            int lastPage = (int) Math.ceil(totalArticle/10);%>--%>
<%--        <%for (int i = 1; i<=10; i++){%>--%>
<%--        <a class="pagination"  <%=(i == currentPage) ? "style='color:red'" : "" %> href=<%=request.getContextPath()%>index.jsp?page=<%=i%><%=request.getAttribute("urlWithParam")%>><%=i%></a>--%>
<%--        <%if (i>lastPage) break;%>--%>
<%--        <%}%>--%>
    </div>
    <div class="upload_container">
        <button class="button upload_button" type="button" onclick="location.href='newArticleInput.jsp'">등록</button>
    </div>
</div>
</body>
</html>
