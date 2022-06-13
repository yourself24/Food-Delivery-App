package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>Model for composite products</p>
 */
@SuppressWarnings("all")
public class CompositeProduct extends MenuItem implements Serializable {
   @Serial
   private static final long serialVersionUID = 2590153167416271592L;

   private final String title;
   private final Set<MenuItem> productList;

   public CompositeProduct(String title, HashSet<MenuItem> productList) {
      this.title = title;
      this.productList = productList;
   }

   public int computePrice() {
      return productList.stream().mapToInt(menuItem -> menuItem.computePrice()).sum();
   }

   public String getTitle() {
      return title;
   }

   public BaseProduct getBaseProduct() {
      float newRating = 0.0f;
      int newCalories = 0;
      int newProtein = 0;
      int newFat = 0;
      int newSodium = 0;
      int newPrice = 0;

      for (MenuItem menuItem : productList) {
         BaseProduct crtProduct = (menuItem instanceof BaseProduct) ? (BaseProduct) menuItem : ((CompositeProduct) menuItem).getBaseProduct();
         newRating += crtProduct.getRating();
         newCalories += crtProduct.getCalories();
         newProtein += crtProduct.getProtein();
         newFat += crtProduct.getFat();
         newSodium += crtProduct.getSodium();
         newPrice += crtProduct.getPrice();
      }

      String newValues = title + "," + (newRating / productList.size()) + "," + newCalories + "," + newProtein + "," + newFat + "," + newSodium + "," + (int)(9.0f / 10 * newPrice);
      return new BaseProduct(newValues);
   }

   public Set<MenuItem> getProductList() { return productList; }

   @Override
   public int hashCode() {
      return Objects.hash(title);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null) return false;
      return Objects.equals(title, ((MenuItem) o).getTitle());
   }
}
