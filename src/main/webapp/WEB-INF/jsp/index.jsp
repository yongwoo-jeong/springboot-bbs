<%@ page import="com.springboot.bbs.vo.ArticleVO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/css/index.css">
    <script type="text/javascript" src="/js/index.js"></script>
    <meta charset="UTF-8" />
    <title>게시판 - 목록</title>
</head>
<body>
<div class="container">
    <header class="title"><h1>자유 게시판 - 목록</h1></header>
    <form method="get" action=<%=request.getContextPath()%>>
        <div class="search_nav">
            <div class="date_select_div">
                <span class="date_span">등록일</span>
                <input type="date" name="startDate" class="start_date"/>
                <span>~</span>
                <input type="date" name="endDate" class="end_date"/>
            </div>
            <div class="category_select_div">
                <div class="category_drop_down">
                    <form action="" method="post">
                        <label for="category"></label>
                        <select name="category" id="category">
                            <option selected="selected" value="All">전체 카테고리</option>
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
    </form>
<%--    <% int articleCount = (int) request.getAttribute("articlesCount");%>--%>
<%--    <div class="search_outcome">총 <%=articleCount%>건</div>--%>
    <div class="post_container">
        <div class="post post_outline">
            <span>카테고리</span>
            <a class="post_title">제목</a>
            <span>작성자</span>
            <span>조회수</span>
            <span>등록일시</span>
            <span>수정일시</span>
        </div>
        <%
    List<ArticleVO> articles = (List<ArticleVO>) request.getAttribute("articles");
    for(ArticleVO article : articles) { %>
        <div class="post">
            <span class="post_category"><%=article.getCategoryName()%></span>
<%--                <% if ( true ){%>--%>
<%--                <svg></svg>--%>
<%--                <%} else {%>--%>
<%--            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">--%>
<%--                <path stroke-linecap="round" stroke-linejoin="round" d="M3 16.5v2.25A2.25 2.25 0 005.25 21h13.5A2.25 2.25 0 0021 18.75V16.5M16.5 12L12 16.5m0 0L7.5 12m4.5 4.5V3" />--%>
<%--            </svg>--%>

<%--            <%}%>--%>
            <a class="post_title" href="${pageContext.request.contextPath}article?id=<%= article.getArticleId()%>"><%=(
                    article.getTitle().length() > 20) ? article.getTitle().substring(0,20)+"..." : article.getTitle()%></a>
            <span><%=article.getWriter()%></span>
            <span class="view_span"><%=article.getView()%></span>
            <span><%=article.getCreatedAt()%></span>
            <span></span>
        </div>
    <% } %>
    </div>
    <div class="pagination_container">
<%--        <%  Integer currentPage = (Integer) request.getAttribute("currentPage");--%>
<%--            int lastPage = (int) Math.ceil((double) articleCount/10);%>--%>
<%--        <%for (int i = 1; i<=lastPage; i++){%>--%>
<%--        <a class="pagination"  <%=(i == currentPage) ? "style='color:red'" : "" %> href=<%=request.getContextPath()%><%=request.getAttribute("queryString")%><%=i%>><%=i%></a>--%>
<%--        <%}%>--%>
    </div>
    <div class="upload_container">
        <button class="button upload_button" type="button" onclick=location.href=<%=request.getContextPath()%>"/upload">등록</button>
    </div>
</div>
</body>
</html>
