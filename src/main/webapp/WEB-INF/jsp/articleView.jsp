<%@ page import="java.util.List" %>
<%@ page import="com.example.ebrainstudy__springbootbbs.article.ArticleVO" %>
<%@ page import="com.example.ebrainstudy__springbootbbs.file.FileVO" %>
<%@ page import="com.example.ebrainstudy__springbootbbs.comment.CommentVO" %><%--
  Created by IntelliJ IDEA.
  User: jyw
  Date: 2022/12/14
  Time: 11:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/view_article.css">
    <script type="text/javascript" src="/js/articleView.js"></script>
    <title>Title</title>
</head>
<body>
<%
    ArticleVO article = (ArticleVO) request.getAttribute("article");
%>
<div class="container">
<header class="header">게시판 - 보기</header>
<div class="writer_time_row">
    <div class="writer_div">
        <span><%=article.getWriter()%></span>
    </div>
    <div>
        <span>등록일시</span>
        <% if (article.getModifiedAt() == null){%>
            <span><%=article.getCreatedAt()%></span>
        <%} else {%>
            <span><%=article.getCreatedAt()%></span>
            <span>수정일시</span>
            <span><%=article.getModifiedAt()%></span>
        <%}%>
    </div>
</div>
    <div class="title_row">
        <div>
            <% if (article.getCategoryId() == 1) {%>
                <span>[JAVA]</span>
            <% } else if (article.getCategoryId() == 2) { %>
                <span>[Javascript]</span>
            <% } else if (article.getCategoryId() == 3) {%>
                <span>[JAVA]</span>
            <% } %>
            <span><%=article.getTitle()%></span>
        </div>
        <div class="view">
            <span>조회수: </span>
            <span><%=article.getView()%></span>
        </div>
    </div>
    <div class="post_container">
        <p><%=article.getContent()%></p>
    </div>
    <div class="file_container">
        <%
            List<FileVO> FileList = (List<FileVO>) request.getAttribute("fileList");
            for (FileVO file : FileList){
        %>
        <div class="file">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="M3 16.5v2.25A2.25 2.25 0 005.25 21h13.5A2.25 2.25 0 0021 18.75V16.5M16.5 12L12 16.5m0 0L7.5 12m4.5 4.5V3" />
            </svg>
            <a href=/download?fileId=<%=file.getFileUuid()%>>
                <%=file.getNameOriginal()%>
            </a>
        </div>
        <% }%>
    </div>
    <div class="comments_container">
        <%
            List<CommentVO> commentList = (List<CommentVO>) request.getAttribute("commentList");
            for (CommentVO comment : commentList){
        %>
        <div class="comment_row">
            <div class="comment_date"><%=comment.getCreatedAt()%></div>
            <div><%=comment.getContent()%></div>
        </div>
        <% } %>
        <div>
            <form method="post" action=/addComment?id=<%=article.getArticleId()%> name="uploadComment">
                <input name="new_comment" class="comment_input" type="text" placeholder="댓글을 입력해주세요" />
                <input type="submit" class="save_button" value="저장" />
            </form>
        </div>
    </div>
    <div class="button_set_container">
        <div class="button_set">
<%--            <% Object searchQueryString = request.getAttribute("queryString");%>--%>
<%--            <% if (searchQueryString!=null) {%>--%>
<%--            <a href="/<%=searchQueryString%>" class="list_button">목록</a>--%>
<%--            <%} else { %>--%>
            <a href="/" class="list_button">목록</a>
<%--            <% } %>--%>
            <a id="modi_btn" class="modi_del_btn">수정</a>
            <a id="del_btn" class="modi_del_btn">삭제</a>
        </div>
    </div>
</div>
</body>
</html>
