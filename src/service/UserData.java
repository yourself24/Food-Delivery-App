package service;

import model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>For working with Users</p>
 */
public class UserData extends FileWriter {
   private static final String USER_FILE = "data/users.ser";

   /**
    * <p>Load all users</p>
    */
   @SuppressWarnings("unchecked")
   private static Set<User> loadUsers() {
      Set<User> availableUsers;

      availableUsers = (HashSet<User>) readObject(USER_FILE);

      if (availableUsers == null) {
         availableUsers = new HashSet<>();
      }

      return availableUsers;
   }
   /**
    * <p>Login a user if they were already
    * registered</p>
    */
   public static User loginUser(String userName) {
      Set<User> userList = UserData.loadUsers();

      for (User user : userList) {
         if (user.getUsername().compareTo(userName) == 0) {
            return user;
         }
      }

      return null;
   }
   /**
    * <p>Registers a NEW user</p>
    */
   public static boolean registerUser(User newUser) {
      Set<User> userList = UserData.loadUsers();
      newUser.setId(userList.size() + 1);

      return userList.add(newUser) && writeObject(userList, USER_FILE);
   }

   /**
    * <p> Finds the desired user by its id</p>
    */
   public static User findUser(int userId) {
      Set<User> allUsers = UserData.loadUsers();

      for (User user : allUsers) {
         if (user.getId() == userId) {
            return user;
         }
      }
      return null;
   }
}
