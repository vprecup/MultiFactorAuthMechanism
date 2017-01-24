package model;

public class User {
   
   private int id;
   private String username; 
   private String password;
   private String email;
   private int role_id;

   public int getId() {
      return id;
   }
   public void setId( int id ) {
      this.id = id;
   }
   public String getUsername() {
      return username;
   }
   public void setUsername( String username ) {
      this.username = username;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword( String password ) {
      this.password = password;
   }
   public int getRole_id() {
      return role_id;
   }
   public void setRole_id( int role_id ) {
      this.role_id = role_id;
   }
   public String getEmail(){
      return email;
   }
   public void setEmail(String email){
      this.email = email;
   } 
}