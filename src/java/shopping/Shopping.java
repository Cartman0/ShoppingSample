package shopping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 商品・履歴検索処理
 *
 */
public class Shopping {

    /**
     * 商品一覧のリスト(全商品)を返却します.
     *
     * @return	商品一覧のリスト
     */
    public ArrayList<ItemBean> getItem() {
        ArrayList<ItemBean> bean_list = new ArrayList<>();
        ShoppingDao dao = null;
        ResultSet rs;

        try {
            // DAOクラスをインスタンス化
            dao = new ShoppingDao();
            // 現在の商品一覧を検索
            rs = dao.selectItem();

            // 検索結果を1レコードずつ処理
            while (rs.next()) {
                // 商品IDを設定
                String item_id = rs.getString("item_id");
                // 商品名を設定
                String item_name = rs.getString("item_name");
                // 商品価格を設定
                int price = rs.getInt("price");
                // 商品数を設定
                int quantity = rs.getInt("quantity");

                // 商品一覧を格納するBeanクラスをインスタンス化
                ItemBean item_bean = new ItemBean(item_id, item_name, price, quantity);
                System.out.println("itembean: " + item_bean);
                // Beanクラスをリストに追加
                bean_list.add(item_bean);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            // 処理終了時に各接続を解除
            if (dao != null) {
                dao.close();
            }
        }
        return bean_list;
    }

    /**
     * 商品IDを基に商品情報を返却します.
     *
     * @param item_id
     * @return	商品情報
     */
    public ItemBean getItem(String item_id) {
        ItemBean item_bean = null;
        ShoppingDao dao = null;
        ResultSet rs;

        try {
            // DAOクラスをインスタンス化
            dao = new ShoppingDao();
            // 現在の商品一覧を検索
            rs = dao.selectItem(item_id);

            // 検索結果を処理
            while (rs.next()) {
                // 商品名を設定
                String item_name = rs.getString("item_name");
                // 商品価格を設定
                int price = rs.getInt("price");
                // 商品数を設定
                int quantity = rs.getInt("quantity");
                item_bean = new ItemBean(item_id, item_name, price, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 処理終了時に各接続を解除
            if (dao != null) {
                dao.close();
            }
        }
        return item_bean;
    }

    /**
     * 購入履歴のリストを返却
     *
     * @param user_id ユーザのID
     * @return 購入履歴のリスト
     */
    public ArrayList<HistoryBean> getHistory(String user_id) {
        ArrayList<HistoryBean> history_bean_list = new ArrayList<>();
        ShoppingDao dao;
        ResultSet rs;

        try {
            dao = new ShoppingDao();

            // idから現在の商品一覧(購入履歴)を検索
            rs = dao.selectHistory(user_id);

            // 検索結果を1レコードずつ処理
            while (rs.next()) {
                // 商品ID を取得
                String item_id = rs.getString("item_id");
                // 商品名を取得
                String item_name = rs.getString("item_name");
                // 商品数を設定
                int quantity = rs.getInt("quantity");

                HistoryBean history_bean = new HistoryBean(item_id, item_name, quantity);

                // リストに追加
                history_bean_list.add(history_bean);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return history_bean_list;
    }
}
