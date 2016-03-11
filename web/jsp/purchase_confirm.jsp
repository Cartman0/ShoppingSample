<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="item_bean" scope="request" class="shopping.ItemBean"/>

<%-- 購入確認画面 --%>
<!DOCTYPE html>
<html>
    <head>
        <title>ショッピング風サイト 購入確認画面</title>
        <link href="css/shopping.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <main>
            <h1>購入確認</h1>
            <p>次の商品を購入しますか？</p>

            <form action="./ResultServlet" method="post">
                <table class="shopping_table">
                    <tbody>
                        <tr>
                            <th>商品ID</th>
                            <th>商品名</th>
                            <th>価格</th>
                            <th>在庫数</th>
                            <th>購入数</th>
                        </tr>
                        <tr>
                            <%-- リクエストスコープから表示する値を取得 --%>
                            <td><jsp:getProperty name="item_bean" property="itemId" /></td>
                            <td><jsp:getProperty name="item_bean" property="itemName" /></td>
                            <td class="int"><jsp:getProperty name="item_bean" property="price" /></td>
                            <td class="int"><jsp:getProperty name="item_bean" property="quantity" /></td>
                            <td class="int"><%=request.getAttribute("purchased_num")%></td>
                            <td class="button">
                                <input class="common_button" type="submit" value="購入する">
                                <% //TODO:2-5 hiddenでパラメータをセットしておく %>
                                <%-- 購入処理を行うため、hidden（画面には表示されない情報）に商品IDと購入数を設定しておく --%>
                                <input type="hidden" name="item_id" value="<jsp:getProperty property="itemId" name="item_bean"/>">
                                <input type="hidden" name="item_quantity" value="<%= request.getAttribute("purchased_num")%>">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <form action="./ShoppingServlet" method="post">
                <input class="common_button" type="submit" value="一覧に戻る">
            </form>
        </main>
    </body>
</html>