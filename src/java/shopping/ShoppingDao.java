package shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ショッピング風DAOクラス. DBの商品(item)テーブルと購入履歴(history)のテーブルを扱う
 */
public class ShoppingDao {
    private Connection con_ = null;
    private ResultSet rs_ = null;
    private PreparedStatement ps_ = null;

    public ShoppingDao() throws SQLException {
        // JDBCドライバのロード
        try {
            // 「com.mysql.jdbc.Driver」クラス名
            Class.forName("com.mysql.jdbc.Driver");

            // データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
            this.con_ = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping_sample",
                    "user",
                    "password");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    /**
     * データベースの全商品と在庫を取得します.
     *
     * @return	商品情報（ResultSet）
     * @throws SQLException
     */
    public ResultSet selectItem() throws SQLException {
        // SQL文を生成
        this.ps_ = this.con_.prepareStatement(
                "select item.item_id, item.item_name, item.price, stock.quantity from item inner join stock on item.item_id = stock.item_id"
        );

        // SQLを実行
        this.rs_ = this.ps_.executeQuery();

        return this.rs_;
    }

    /**
     * 商品IDを基にデータベースの商品情報と在庫を取得します.
     *
     * @return	商品情報（ResultSet）
     * @param item_id 商品ID
     * @throws SQLException
     */
    public ResultSet selectItem(String item_id) throws SQLException {
        // SQL文を生成
        this.ps_ = this.con_.prepareStatement(
                "select item.item_name, item.price, stock.quantity from item inner join stock on item.item_id = stock.item_id where item.item_id = ?"
        );
        // SQL文に商品IDを設定
        this.ps_.setString(1, item_id);
        // SQLを実行
        this.rs_ = this.ps_.executeQuery();

        return this.rs_;
    }

    /**
     * ユーザの購入履歴を取得します.
     *
     * @param user_id
     * @return	購入履歴（ResultSet）
     * @throws SQLException
     */
    public ResultSet selectHistory(String user_id) throws SQLException {
        // SQL文を生成
        this.ps_ = this.con_.prepareStatement("select history.item_id, item.item_name, history.quantity from history inner join item on history.id = ? and history.item_id = item.item_id");
        this.ps_.setString(1, user_id);

        // SQLを実行
        this.rs_ = this.ps_.executeQuery();
        return this.rs_;
    }

    /**
     * 商品IDを基にデータベースの在庫を更新（マイナス）します.
     *
     * @param item_id;
     * @param purchased_num 購入数
     * @throws SQLException
     */
    public void updateItem(String item_id, int purchased_num) throws SQLException {
         // SQL文を生成
        /* TODO:2-⑨update文を追加
         在庫から購入数を引く
         */
        this.ps_ = this.con_.prepareStatement("update stock set quantity = quantity - ? where item_id = ?");

        // SQL文に数量を設定
        this.ps_.setInt(1, purchased_num);
        // SQL文に商品IDを設定
        this.ps_.setString(2, item_id);
        // SQLを実行
        this.ps_.executeUpdate();
    }

    /**
     * 購入履歴テーブルを更新します.
     *
     * @param user_id	ユーザID
     * @param item_id 商品ID
     * @param purchased_num 購入数
     * @throws SQLException
     */
    public void updateHistory(String user_id, String item_id, int purchased_num) throws SQLException {
         // SQL文を生成
        // TODO:2-⑩insert文を追加
        this.ps_ = this.con_.prepareStatement("insert into history(id, item_id, quantity) values (?, ?, ?)");
        
        this.ps_.setString(1, user_id);
        this.ps_.setString(2, item_id);
        this.ps_.setInt(3, purchased_num);

        this.ps_.executeUpdate();
    }

    /**
     * コネクションをクローズします.
     */
    public void close() {
        try {
            // データベースとの接続を解除する
            if (this.con_ != null) {
                this.con_.close();
            }
            if (this.ps_ != null) {
                this.ps_.close();
            }
            if (this.rs_ != null) {
                this.rs_.close();
            }
        } catch (SQLException se) {
            // データベースとの接続解除に失敗した場合
            se.printStackTrace();
        }
    }
}
