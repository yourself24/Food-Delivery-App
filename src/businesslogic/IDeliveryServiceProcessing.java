package businesslogic;

import model.MenuItem;
import model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Set;

/**
 * <p>Interface that holds admin and client operations</p>
 */
public interface IDeliveryServiceProcessing {
   /**
    * <p>Imports the products from .csv </p>
    * @post a list of products from .csv
    * @return success or fail
    */
   boolean importProducts();

   /**
    * <p>Adds a new base product into menu list</p>
    * @pre menuItem != null
    * @post menuItem is added to product list
    * @param menuItem
    * @return success or fail
    */
   boolean addProduct(MenuItem menuItem);

   /**
    * <p>Modifies products from menu list</p>
    * @pre menuItem != null
    * @post product list will contain a modified version of menuItem
    * @param menuItem item to be modified
    * @return success or fail
    */
   boolean modifyProduct(MenuItem menuItem);

   /**
    * <p>Deletes a product from menu list</p>
    * @pre menuItem != null
    * @post menuItem will no longer be in product list
    * @param menuItem to be deleted
    * @return success or fail
    */
   boolean deleteProduct(MenuItem menuItem);

   /**
    * <p>Adds a composite product to menu list</p>
    * @pre menuItem != null
    * @post product list will contain menuItem
    * @param menuItem to be created
    * @return success or fail
    */
   boolean createProduct(MenuItem menuItem);

   /**
    * <p>Creates a time report</p>
    * @pre 0 <= startHour < endHour <= 24
    * @post a time report with orders
    * @param startHour start hour
    * @param endHour end hour
    * @param reportField area where the report will be written
    */
   void generateTimeReport(int startHour, int endHour, JTextArea reportField);

   /**
    * <p>Creates a report containing products and their order times</p>
    * @pre orderAmount > 0
    * @post a stock report
    * @param orderedAmount minimum ordered time
    * @param reportField area where the report will be written
    */
   void generateStockReport(int orderedAmount, JTextArea reportField);

   /**
    * <p>Creates a report containing loyal customers</p>
    * @pre orderAmount > 0
    * @pre orderValue > 0
    * @post a loyalty report
    * @param orderedAmount minimum ordered amount
    * @param orderValue minimum ordered value
    * @param reportField area where the report will be written
    */
   void generateLoyaltyReport(int orderedAmount, int orderValue, JTextArea reportField);

   /**
    * <p>Creates a report containing ordered products in a given day</p>
    * @pre 0 <= selectedIndex <= 6
    * @post a date report
    * @param selectedIndex index of selected day
    * @param selectedDay selected day
    * @param reportField area where the report will be written
    */
   void generateDateReport(int selectedIndex, String selectedDay, JTextArea reportField);

   /**
    * <p>Adds the newOrder to order list</p>
    * @pre newOrder != null
    * @post newOrder will be stored into available orders list
    * @param newOrder new orders
    * @param orderedProducts order's products
    * @return success or fail
    */
   boolean placeOrder(Order newOrder, Set<MenuItem> orderedProducts);

   /**
    * <p>Searches a product in product list</p>
    * @post a table with the searched items
    * @param tableModel table with values
    * @param inputValues search criterias
    */
   void searchProduct(DefaultTableModel tableModel, List<String> inputValues);
}
