package model;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * <p>Model class for all menu items</p>
 */
@SuppressWarnings("all")
public class BaseProduct extends MenuItem implements Serializable {
   @Serial
   private static final long serialVersionUID = 2590153167416271592L;

   private String title;
   private float rating;
   private int calories;
   private int protein;
   private int fat;
   private int sodium;
   private int price;

   public BaseProduct(String line) {
      String[] values = line.split(",");
      this.title = values[0];
      this.rating = Float.parseFloat(values[1]);
      this.calories = Integer.parseInt(values[2]);
      this.protein = Integer.parseInt(values[3]);
      this.fat = Integer.parseInt(values[4]);
      this.sodium = Integer.parseInt(values[5]);
      this.price = Integer .parseInt(values[6]);
   }

   public int computePrice() { return price; }

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

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public float getRating() {
      return rating;
   }

   public void setRating(float rating) {
      this.rating = rating;
   }

   public int getCalories() {
      return calories;
   }

   public void setCalories(int calories) {
      this.calories = calories;
   }

   public int getProtein() {
      return protein;
   }

   public void setProtein(int protein) {
      this.protein = protein;
   }

   public int getFat() {
      return fat;
   }

   public void setFat(int fat) {
      this.fat = fat;
   }

   public int getSodium() {
      return sodium;
   }

   public void setSodium(int sodium) {
      this.sodium = sodium;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public Object[] getValues() {
      Object[] values = new Object[BaseProduct.class.getDeclaredFields().length - 1];
      Field[] fields = BaseProduct.class.getDeclaredFields();
      for (int i = 1; i < fields.length; i++) {
         fields[i].setAccessible(true);
         try {
            values[i - 1] = fields[i].get(this);
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         }
      }
      return values;
   }
}
