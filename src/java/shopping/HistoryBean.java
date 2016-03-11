package shopping;

import java.io.Serializable;

/**
 * HistoryBean
 */
public class HistoryBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String item_id_; // 商品ID
    private String item_name_; // 商品名
    private int quantity_; // 購入した数

    public HistoryBean() {
        this.item_id_ = "";
        this.item_name_ = "";
        this.quantity_ = 0;
    }
    
    public HistoryBean(String item_id, String item_name, int quantity) {
        this.item_id_ = item_id;
        this.item_name_ = item_name;
        this.quantity_ = quantity;
    }
    
    public void setItemId(String item_id){
        this.item_id_ = item_id;
    }
    
    public String getItemId(){
        return this.item_id_;
    }
    
    public void setItemName(String item_name){
        this.item_name_ = item_name;
    }
    
    public String getItemName(){
        return this.item_name_;
    }
    
    public void setQuantity(int quantity){
        this.quantity_ = quantity;
    }
    
    public int getQuantity(){
        return this.quantity_;
    }
    
}
