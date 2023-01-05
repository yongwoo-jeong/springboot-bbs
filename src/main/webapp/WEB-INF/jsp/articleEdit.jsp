<%@ page import="com.springboot.bbs.vo.SearchCriteriaVO" %>
<%@ page import="com.springboot.bbs.vo.ArticleVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/upload.css">
    <script type="text/javascript" src="/js/articleEdit.js"></script>
    <title>게시판 - 등록</title>
</head>
<body>
<header class="title"><h1>게시판 - 등록</h1></header>
<main>
    <% ArticleVO article = (ArticleVO) request.getAttribute("article"); %>
    <form enctype="multipart/form-data" method="post" action=<%=request.getContextPath()%>/onEdit?id=<%=article.getArticleId()%> name="edit">
        <div class="upload_form_container">
            <div class="category_row container_row">
                <div><span>카테고리</span></div>
                <div>
                    <span><%=article.getCategoryName()%></span>
                </div>
            </div>
            <div class="container_row">
                <div><span>등록 일시</span></div>
                <div>
                    <span><%=article.getCreatedAt()%></span>
                </div>
            </div>
            <div class="container_row">
                <div><span>수정 일시</span></div>
                <div>
                    <span><%=article.getModifiedAt()%></span>
                </div>
            </div>
            <div class="container_row">
                <div><span>조회수</span></div>
                <div>
                    <span><%=article.getView()%></span>
                </div>
            </div>
            <div class="writer_row container_row">
                <div><span>작성자 *</span></div>
                <div><input name="writer" value=<%=article.getWriter()%> type="text" minlength="3" maxlength="4" required/></div>
            </div>
            <div class="password_row container_row">
                <div><span>비밀번호</span></div>
                <div>
                    <input class="password" name="password" placeholder="비밀번호" type="password" minlength="4" maxlength="15" required/>
                </div>
            </div>
            <div class="title_row container_row">
                <div><span>제목 *</span></div>
                <div><input name="title" value=<%=article.getTitle()%> type="text" minlength="4" maxlength="100" required/></div>
            </div>
            <div class="content_row container_row">
                <div><span>내용 *</span></div>
                <div><input name="content" value=<%=article.getContent()%> type="text" minlength="4" maxlength="2000" required/></div>
            </div>
            <div class="attach_file_row container_row">
                <div><span>파일첨부</span></div>
                <div class="filebox_container">
                    <div class="filebox">
                        <input class="upload_name1" />
                        <label for="file1">파일 찾기</label>
                        <input type="file" name="files" id="file1" />
                    </div>
                    <div class="filebox">
                        <input class="upload_name2" />
                        <label for="file2">파일 찾기</label>
                        <input type="file" name="files" id="file2" />
                    </div>
                    <div class="filebox">
                        <input class="upload_name3" />
                        <label for="file3">파일 찾기</label>
                        <input type="file" name="files" id="file3" />
                    </div>
                </div>
            </div>
        </div>
        <div class="button_container">
            <% SearchCriteriaVO searchCriteria = (SearchCriteriaVO) request.getAttribute("searchCriteria");
                if (searchCriteria.getKeyword()!=null){ %>
            <input type="hidden" name="keyword" value=<%=request.getParameter("keyword")%>>
            <%}%>
            <% if (searchCriteria.getCategoryId()!=null){ %>
            <input type="hidden" name="categoryId" value=<%=request.getParameter("categoryId")%>>
            <%}%>
            <% if (searchCriteria.getStartDate()!=null){ %>
            <input type="hidden" name="startDate" value=<%=request.getParameter("startDate")%>>
            <%}%>
            <% if (searchCriteria.getEndDate()!=null){ %>
            <input type="hidden" name="endDate" value=<%=request.getParameter("endDate")%>>
            <%}%>
            <% if (searchCriteria.getCurrentPage() != null){ %>
            <input type="hidden" name="currentPage" value=<%=request.getParameter("currentPage")%>>
            <%}%>
            <a onclick="history.back();" class="cancel_button">취소</a>
            <input type="submit" class="save_button" value="저장" />
        </div>
    </form>
</main>
<script>

</script>
</body>
</html>
