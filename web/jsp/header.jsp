<%@ page language="java" contentType="text/html charset=UTF-8" %>
<jsp:useBean id="login_user_bean" scope="session" class="login.LoginUserBean"/>

<link href="css/shopping.css" rel="stylesheet" type="text/css" />

<%-- header --%>
<header>
    <%-- ログイン済みの場合はID を表示 --%>
    <p>
        ようこそ「<jsp:getProperty name="login_user_bean" property="name" />」さん！
        <%-- Getのクエリで購入履歴かログアウトか判断させる --%>
        <a href="ShoppingServlet?submit=history">購入履歴</a> 
        <a href="LoginServlet?submit=logout">ログアウト</a>
    </p>
</header>
