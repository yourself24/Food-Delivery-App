package service;

import model.MenuItem;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Class that manipulates product models</p>
 */
public class ProductData extends ObjectData {
   private static final String MENU_FILE = "data/menu.ser";

   public static boolean addProduct(MenuItem menuItem) {
      Set<MenuItem> menuList = ProductData.loadProducts();

      return menuList.add(menuItem) && writeObject(menuList, MENU_FILE);
   }

   public static boolean modifyProduct(MenuItem menuItem) {
      Set<MenuItem> menuList = ProductData.loadProducts();

      if (!menuList.remove(menuItem)) {
         return false;
      }

      menuList.add(menuItem);

      return writeObject(menuList, MENU_FILE);
   }

   public static boolean removeProduct(MenuItem menuItem) {
      Set<MenuItem> menuList = ProductData.loadProducts();

      if (!menuList.remove(menuItem)) {
         return false;
      }

      return writeObject(menuList, MENU_FILE);
   }

   public static boolean addProducts(HashSet<MenuItem> productSet) {
      return writeObject(productSet, MENU_FILE);
   }

   @SuppressWarnings("unchecked")
   public static Set<MenuItem> loadProducts() {
      Set<MenuItem> availableProducts;

      availableProducts = (HashSet<MenuItem>) ObjectData.readObject(MENU_FILE);

      if (availableProducts == null) {
         availableProducts = new HashSet<>();
      }

      return availableProducts;
   }
}
