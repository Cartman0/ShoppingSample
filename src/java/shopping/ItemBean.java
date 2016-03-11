package shopping;

import java.io.Serializable;

public class ItemBean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String item_id_;
    private String item_name_;
    private int price_;
    private int quantity_;
    
    public ItemBean(){
        this.item_id_ = "";
        this.item_name_ = "";
        this.price_ = 0;
        this.quantity_ = 0;
    }
    
    public ItemBean(String item_id, String item_name, int price, int quantity){
        this.item_id_ = item_id;
        this.item_name_ = item_name;
        this.price_ = price;
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
    
     public void setPrice(int price){
        this.price_ = price;
    }
    
    public int getPrice(){
        return this.price_;
    }
    
    public void setQuantity(int quantity){
        this.quantity_ = quantity;
    }
    
    public int getQuantity(){
        return this.quantity_;
    }
    
}
