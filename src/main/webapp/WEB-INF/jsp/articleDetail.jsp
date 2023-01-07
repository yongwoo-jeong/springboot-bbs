<%@ page import="java.util.List" %>
<%@ page import="com.springboot.bbs.vo.ArticleVO" %>
<%@ page import="com.springboot.bbs.vo.CommentVO" %>
<%@ page import="com.springboot.bbs.vo.FileVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/articleDetail.css">
    <title>Title</title>
</head>
<body>
<%
    ArticleVO article = (ArticleVO) request.getAttribute("targetArticle");
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
                <input name="content" class="comment_input" type="text" placeholder="댓글을 입력해주세요" />
                <input type="submit" class="save_button" value="저장" />
            </form>
        </div>
    </div>
    <div class="button_set_container">
        <div class="button_set">
            <a href="/<%=request.getAttribute("queryStringParam")%><%=request.getAttribute("currentPage")%>" class="list_button">목록</a>
            <a id="modi_btn" class="modi_del_btn">수정</a>
            <div style="display: none" class="modal-fade" id="modi_modal">
                <div class="modal-dialog">
                    <form method="post" action="/editArticle?id=<%=article.getArticleId()%>&<%=request.getAttribute("queryStringParam").toString().substring(1)%><%=request.getAttribute("currentPage")%>">
                        <input class="password" type="password" name="password" />
<%--                        <% if (!"".equals(request.getParameter("keyword"))){ %>--%>
<%--                        <input type="hidden" name="keyword" value=<%=request.getParameter("keyword")%>>--%>
<%--                        <%}%>--%>
<%--                        <% if (!"".equals(request.getParameter("categoryId"))){ %>--%>
<%--                        <input type="hidden" name="categoryId" value=<%=request.getParameter("categoryId")%>>--%>
<%--                        <%}%>--%>
<%--                        <% if (!"".equals(request.getParameter("startDate"))){ %>--%>
<%--                        <input type="hidden" name="startDate" value=<%=request.getParameter("startDate")%>>--%>
<%--                        <%}%>--%>
<%--                        <% if (!"".equals(request.getParameter("endDate"))){ %>--%>
<%--                        <input type="hidden" name="endDate" value=<%=request.getParameter("endDate")%>>--%>
<%--                        <%}%>--%>
<%--                        <% if (!"".equals(request.getParameter("currentPage"))){ %>--%>
<%--                        <input type="hidden" name="currentPage" value=<%=request.getParameter("currentPage")%>>--%>
<%--                        <%}%>--%>
                        <input type="submit" value="확인">
                    </form>
                    <button type="button" class="close" id="modiClose">&times;</button>
                </div>
            </div>
            <a id="del_btn" class="modi_del_btn">삭제</a>
            <div style="display: none" class="modal-fade" id="delete_modal">
                <div class="modal-dialog">
                    <form method="post" action="/deleteArticle?id=<%=article.getArticleId()%>&<%=request.getAttribute("queryStringParam").toString().substring(1)%><%=request.getAttribute("currentPage")%>">
                        <input class="password" type="password" name="password" />
<%--                        <% if (!"".equals(request.getParameter("keyword"))){ %>--%>
<%--                        <input type="hidden" name="keyword" value=<%=request.getParameter("keyword")%>>--%>
<%--                        <%}%>--%>
<%--                        <% if (!"".equals(request.getParameter("categoryId"))){ %>--%>
<%--                        <input type="hidden" name="categoryId" value=<%=request.getParameter("categoryId")%>>--%>
<%--                        <%}%>--%>
<%--                        <% if (!"".equals(request.getParameter("startDate"))){ %>--%>
<%--                        <input type="hidden" name="startDate" value=<%=request.getParameter("startDate")%>>--%>
<%--                        <%}%>--%>
<%--                        <% if (!"".equals(request.getParameter("endDate"))){ %>--%>
<%--                        <input type="hidden" name="endDate" value=<%=request.getParameter("endDate")%>>--%>
<%--                        <%}%>--%>
<%--                        <% if (!"".equals(request.getParameter("currentPage"))){ %>--%>
<%--                        <input type="hidden" name="currentPage" value=<%=request.getParameter("currentPage")%>>--%>
<%--                        <%}%>--%>
                        <input type="submit" value="확인">
                    </form>
                    <button type="button" class="close" id="delClose" >&times;</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/articleDetail.js"></script>
</body>
</html>
