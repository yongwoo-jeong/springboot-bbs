<%--
  Created by IntelliJ IDEA.
  User: jyw
  Date: 2022/12/14
  Time: 11:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>비밀번호 확인</title>
</head>
<body>
    <% String action = (String) request.getAttribute("action"); %>
    <% String articleId = (String) request.getAttribute("articleId"); %>
    <h3>비밀번호 확인</h3>
    <div class="container" style="display: flex">
            <form method="post" action="/passwordVerify?id=<%=articleId%>&action=<%=action%>" >
            <div class="password_row" style="display: flex">
                <div style="background-color: rgba(0,0,0,0.25)">비밀번호*</div>
                <div>
                    <input type="text" placeholder="비밀번호를 입력해주세요" name="password" />
                </div>
            </div>
            <div class="button_row">
                <a onclick="window.close()">취소</a>
                <input type="submit" value="확인"/>
            </div>
        </form>
    </div>
</body>
</html>
