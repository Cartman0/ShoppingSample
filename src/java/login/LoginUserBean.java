package login;

import java.io.Serializable;

public class LoginUserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String id_;
    private String name_;
    private int age_;

    public LoginUserBean() {
        this.id_ = "";
        this.name_ = "";
        this.age_ = 0;
    }
    
    public LoginUserBean(String id, String name, int age) {
        this.id_ = id;
        this.name_ = name;
        this.age_ = age;
    }
    
    public void setId(String id){
        this.id_ = id;
    }
    
    public String getId(){
        return this.id_;
    }
    
    public void setName(String name){
        this.name_ = name;
    }
    
    public String getName(){
        return this.name_;
    }
    
    public void setAge(int age){
        this.age_ = age;
    }
    
    public int getAge(){
        return this.age_;
    }

}
