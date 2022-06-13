package service;

import model.MenuItem;
import model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>Manipulating orders</p>
 */
public class OrderData extends FileWriter {
   private static final String ORDER_FILE = "data/order.ser";


   /**
    * <p>Loads all orders and creates a new hashmap with orders in case there are none</p>
    */
   public static boolean addOrder(Order newOrder, Set<MenuItem> orderedProducts) {
      Map<Order, Set<MenuItem>> orderList = OrderData.loadOrders();
      newOrder.setOrderId(orderList.size() + 1);
      orderList.put(newOrder, orderedProducts);

      return writeObject(orderList, ORDER_FILE);
   }
   @SuppressWarnings("unchecked")
   public static Map<Order, Set<MenuItem>> loadOrders() {
      Map<Order, Set<MenuItem>> availableOrders;

      availableOrders = (HashMap<Order, Set<MenuItem>>) readObject(ORDER_FILE);

      if (availableOrders == null) {
         availableOrders = new HashMap<>();
      }

      return availableOrders;
   }
   /**
    * <p>adding a new order into the hashMap of active orders</p>
    */

}
