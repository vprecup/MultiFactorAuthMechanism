package model;

public class Person
{
    private int id;
    private int user_id;
    private String firstname;
    private String lastname;
    private String phone;
    
    public int getId() {
        return id;
    }
    public void setId( int id ) {
        this.id = id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id( int user_id ) {
        this.user_id = user_id;
    }
    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

}