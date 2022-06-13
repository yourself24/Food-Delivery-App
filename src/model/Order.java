package model;

import service.UserData;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * <p>Model for orders</p>
 */
public class Order implements Serializable {
   private int orderId;
   private final int clientId;
   private final Date orderDate;

   public Order(int clientId, Date orderDate) {
      this.clientId = clientId;
      this.orderDate = orderDate;
   }

   public int getOrderId() { return orderId; }

   public void setOrderId(int orderId) { this.orderId = orderId; }

   public int getClientId() { return clientId; }

   public Date getOrderDate() { return orderDate; }

   @Override
   public int hashCode() {
      return Objects.hash(orderId, clientId, orderDate);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Order order = (Order) o;
      return orderId == order.orderId && clientId == order.clientId && Objects.equals(orderDate, order.orderDate);
   }

   @Override
   public String toString() {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)", new Locale("en", "EN"));
      return "Order [id: " + orderId + "] placed by " + UserData.findUser(clientId) + " at " + dateFormat.format(orderDate);
   }
}
