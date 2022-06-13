package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>Model for menu items</p>
 */
@SuppressWarnings("all")
public class MenuItem implements Serializable {
   @Serial
   private static final long serialVersionUID = 2590153167416271592L;

   public MenuItem() { }

   @Override
   public int hashCode() {
      return this.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      return this.equals(obj);
   }

   public String getTitle() {
      return this.getTitle();
   }

   public Object[] getValues() {
      if (this instanceof BaseProduct) {
         return ((BaseProduct) this).getValues();
      } else if (this instanceof CompositeProduct){
         return ((((CompositeProduct) this).getBaseProduct().getValues()));
      }
      return null;
   }

   public int computePrice() {
      return this.computePrice();
   }
}
