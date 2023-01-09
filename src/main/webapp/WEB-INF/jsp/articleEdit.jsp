<%@ page import="com.springboot.bbs.vo.SearchCriteriaVO" %>
<%@ page import="com.springboot.bbs.vo.ArticleVO" %>
<%@ page import="com.springboot.bbs.vo.FileVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springboot.bbs.vo.CategoryVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/upload.css">
    <title>게시판 - 등록</title>
</head>
<body>
<header class="title"><h1>게시판 - 등록</h1></header>
<main>
    <% String queryString = request.getQueryString();
        ArticleVO article = (ArticleVO) request.getAttribute("article"); %>
    <form enctype="multipart/form-data" method="post" action="<%=request.getContextPath()%>/updateAction?id=<%=article.getArticleId()%>&<%=queryString%>" name="edit">
        <div class="upload_form_container">
            <div class="category_row container_row">
                <div><span>카테고리</span></div>
                <div>
                    <% List<CategoryVO> categories = (List<CategoryVO>) request.getAttribute("categories"); %>
                    <span><%=categories.get(article.getCategoryId()-1).getCategoryName()%></span>
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
                    <% List<FileVO> fileList = (List<FileVO>) request.getAttribute("fileList");
                     if (fileList != null) {
                        for (int i=0; i<fileList.size(); i++){ %>
							<div class="exist_file_row" id="exist_file_row<%=i%>" >
                                <div class="icon_div">
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="M3 16.5v2.25A2.25 2.25 0 005.25 21h13.5A2.25 2.25 0 0021 18.75V16.5M16.5 12L12 16.5m0 0L7.5 12m4.5 4.5V3" /></svg>
                                </div>
                                <a class="file_name"><%=fileList.get(i).getNameOriginal()%></a>
                                <a href="/download?fileId=<%=fileList.get(i).getFileUuid()%>" class="file_download">Download</a>
                                <input id="file_uuid<%=i%>" type="hidden" value=<%=fileList.get(i).getFileUuid()%>>
                                <div class="delete_file" id="delete_row<%=i%>">&times;</div>
                            </div>
                            <div style="display: none" class="restore_row" id="restoreRow<%=i%>">
                                <a class="afterDelete" id="afterDelete<%=i%>">파일 삭제됨</a>
                                <div class="restoreBtn" id="restoreBtn<%=i%>">취소</div>
                            </div>
                        <% }} %>
                    <% for (int i=1; i<=(3-fileList.size()); i++){ %>
                    <div class="filebox">
                        <input class="upload_name<%=i%>" />
                        <label for="file<%=i%>">파일 찾기</label>
                        <input type="file" name="files" id="file<%=i%>" />
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
        <div class="button_container">
<%--            <% SearchCriteriaVO searchCriteria = (SearchCriteriaVO) request.getAttribute("searchCriteria");--%>
<%--                if (searchCriteria.getKeyword()!=null){ %>--%>
<%--            <input type="hidden" name="keyword" value=<%=request.getParameter("keyword")%>>--%>
<%--            <%}%>--%>
<%--            <% if (searchCriteria.getCategoryId()!=null){ %>--%>
<%--            <input type="hidden" name="categoryId" value=<%=request.getParameter("categoryId")%>>--%>
<%--            <%}%>--%>
<%--            <% if (searchCriteria.getStartDate()!=null){ %>--%>
<%--            <input type="hidden" name="startDate" value=<%=request.getParameter("startDate")%>>--%>
<%--            <%}%>--%>
<%--            <% if (searchCriteria.getEndDate()!=null){ %>--%>
<%--            <input type="hidden" name="endDate" value=<%=request.getParameter("endDate")%>>--%>
<%--            <%}%>--%>
<%--            <% if (searchCriteria.getCurrentPage() != null){ %>--%>
<%--            <input type="hidden" name="currentPage" value=<%=request.getParameter("currentPage")%>>--%>
<%--            <%}%>--%>
            <a onclick="history.back();" class="cancel_button">취소</a>
            <input type="hidden" name="deleteFileList" id="deleteFileList" value="">
            <input type="submit" class="save_button" value="저장" />
        </div>
    </form>
</main>
  <script type="text/javascript" src="/js/articleEdit.js"></script>
</body>
</html>
