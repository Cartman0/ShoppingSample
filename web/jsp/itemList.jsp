<%@ page import="java.util.ArrayList"%>
<%@ page import="shopping.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%-- 商品一覧画面 --%>
<!DOCTYPE html>
<html>
    <head>
        <title>ショッピング風サイト 商品一覧画面</title>
        <link href="css/shopping.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%-- TODO:2-1 jsp:includeでヘッダー画面を読み込む --%> 
        <jsp:include page="header.jsp"></jsp:include>

            <main>
                <h1>商品一覧</h1>
            <%-- リクエストスコープからBeanクラスの配列を取得 --%>
            <% ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getAttribute("itemList"); %>

            <form action="./BuyItemServlet">
                <table class="shopping_table">
                    <tbody>
                        <tr>
                            <th>商品ID</th>
                            <th>商品名</th>
                            <th>価格</th>
                            <th>在庫数</th>
                            <th>数量</th>
                        </tr>

                        <%-- Beanの要素数分（商品の種類分）テーブルを作成 --%>
                        <% for (ItemBean bean : itemList) {%>
                        <tr>
                            <%-- 商品ID --%>
                            <td><%= bean.getItemId()%></td>
                            <%-- 商品名 --%>
                            <td><%= bean.getItemName()%></td>
                            <%-- 価格 --%>
                            <td class="int"><%= bean.getPrice()%></td>
                            <%-- 数量（在庫） --%>
                            <td class="int"><%= bean.getQuantity()%></td>

                            <%-- TODO:2-2 在庫が0の場合はリストボックスと購入ボタンを表示しない処理を入れる --%>
                            <% if (bean.getQuantity() != 0) {%>
                            <td>
                                <select class="list" name="<%= bean.getItemId()%>list">
                                    <% for (int i = 0; i <= bean.getQuantity(); i++) {%>
                                    <option value="<%= i%>"><%= i%></option>
                                    <% }%>
                                </select>
                            </td>
                            <td class="button">
                                <input class="common_button" type="submit" value="購入" name="<%= bean.getItemId()%>">
                            </td>
                            <% } else { %>
                            <td class="button">売り切れ！</td>
                            <% } %>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
                <a class="common_button" href="./">戻る</a>
            </form>
        </main>
    </body>
</html>