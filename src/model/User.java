package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * <p>Model class for isers</p>
 */
public class User implements Serializable {
   @Serial
   private static final long serialVersionUID = 3212112364826093568L;

   private int id;
   private String username;
   private String password;
   private String type;

   /**
    * <p>Default constructor ,no parameters</p>
    */
   public User() {
   }

   /**
  Followed by getters and setters
    */
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }


   public String getUsername() {
      return username;
   }


   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }


   public void setPassword(String password) {
      this.password = password;
   }


   public String getType() {
      return type;
   }


   public void setType(String type) {
      this.type = type;
   }

   @Override
   public String toString() {
      return username + " [id: " + id + "]";
   }

   @Override
   public int hashCode() {
      return Objects.hash(username);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return Objects.equals(username, user.username);
   }
}
