package businesslogic;

import model.BaseProduct;
import model.MenuItem;
import model.Order;
import model.User;
import presentation.panels.EmployeePanel;
import service.OrderData;
import service.FoodData;
import service.UserData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>Service class to update application status</p>
 * @invariant isWellFormed
 */
@SuppressWarnings("deprecation")
public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
   private static final DeliveryService INSTANCE = new DeliveryService();
   private Set<MenuItem> menuItems;
   private Map<Order, Set<MenuItem>> orderList;

   /**
    * <p>Default constructor</p>
    */
   private DeliveryService() {
      addObserver(EmployeePanel.getInstance());
   }

   /**
    * <p>Returns instance</p>
    * @return class instance
    */
   public static DeliveryService getInstance() {
      return INSTANCE;
   }

   /**
    * <p>Loads serialized info</p>
    */
   public void loadAll() {
      menuItems = FoodData.loadProducts();
      orderList = OrderData.loadOrders();
   }

   /**
    * <p>Well formed method</p>
    * @return true or false
    */
   private boolean isWellFormed() {
      for (MenuItem menuItem : menuItems) {
         if (menuItem == null) {
            return false;
         }
      }
      for (Map.Entry<Order, Set<MenuItem>> order : orderList.entrySet()) {
         if (order == null) {
            return false;
         }
      }
      return true;
   }

   /**
    * <p>Imports the products from .csv file/p>
    * @post a list of products from .csv file
    * @return success or fail
    */
   @Override
   public boolean importProducts() {
      assert isWellFormed() : "[ERROR] importProducts - not well formed!";
      boolean returnValue = false;
      try {
         File inputFile = new File("products.csv");
         InputStream inputStream = new FileInputStream(inputFile);
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
         menuItems = bufferedReader.lines().skip(1).map(BaseProduct::new).collect(Collectors.toSet());
         bufferedReader.close();
         returnValue = FoodData.addProducts((HashSet<MenuItem>) menuItems);
      } catch (IOException e) {
         e.printStackTrace();
      }
      assert isWellFormed() : "[ERROR] importProducts - not well formed!";
      return returnValue;
   }

   /**
    * <p>Adds a new base product into menu list</p>
    * @param menuItem item to be added
    * @return success or fail
    */
   @Override
   public boolean addProduct(MenuItem menuItem) {
      assert menuItem != null : "[ERROR] addProduct- Menu item is null!";
      assert isWellFormed() : "[ERROR] addProduct - not well formed!";
      boolean returnValue = menuItems.add(menuItem) && FoodData.addProduct(menuItem);
      assert menuItems.contains(menuItem) : "[ERROR] addProduct - Menu item not present";
      assert isWellFormed() : "[ERROR] addProduct - not well formed!";
      return returnValue;
   }

   /**
    * <p>Modifies a products from menu list</p>
    * @param menuItem item to be modified
    * @return success or fail
    */
   @Override
   public boolean modifyProduct(MenuItem menuItem) {
      assert menuItem != null : "[ERROR] modifyProduct - Menu item is null!";
      assert isWellFormed() : "[ERROR] modifyProduct - not well formed!";
      boolean returnValue = menuItems.remove(menuItem) && menuItems.add(menuItem) && FoodData.modifyProduct(menuItem);
      assert menuItems.contains(menuItem) : "[ERROR] modifyProduct - Menu item not present";
      assert isWellFormed() : "[ERROR] modifyProduct - not well formed!";
      return returnValue;
   }

   /**
    * <p>Deletes a product from menu list</p>
    * @param menuItem item to be deleted
    * @return success or fail
    */
   @Override
   public boolean deleteProduct(MenuItem menuItem) {
      assert menuItem != null : "[ERROR] deleteProduct - Menu item is null!";
      assert isWellFormed() : "[ERROR] deleteProduct - not well formed!";
      boolean returnValue = menuItems.remove(menuItem) && FoodData.removeProduct(menuItem);
      assert isWellFormed() : "[ERROR] deleteProduct - not well formed!";
      assert !menuItems.contains(menuItem) : "[ERROR] deleteProduct - Menu item not present";
      return returnValue;
   }

   /**
    * <p>Adds a composite product to menu list</p>
    * @param menuItem item to be created
    * @return success or fail
    */
   @Override
   public boolean createProduct(MenuItem menuItem) {
      assert menuItem != null : "[ERROR] createProduct - Menu item is null!";
      assert isWellFormed() : "[ERROR] createProduct - not well formed!";
      boolean returnValue = menuItems.add(menuItem) && FoodData.addProduct(menuItem);
      assert isWellFormed() : "[ERROR] createProduct - not well formed!";
      assert menuItems.contains(menuItem) : "[ERROR] createProduct - Menu item not present";
      return returnValue;
   }

   /**
    * <p>Creates a time report</p>
    * @param startHour start hour
    * @param endHour end hour
    * @param reportField area where the report will be written
    */
   @Override
   public void generateTimeReport(int startHour, int endHour, JTextArea reportField) {
      assert 0 <= startHour && startHour < endHour && endHour < 24 : "[ERROR] generateTimeReport - Invalid order interval";
      reportField.setText(" ORDERS PLACED BETWEEN " + formatNumber(startHour) + ":00 AND " + formatNumber(endHour) + ":00 ***\n");
      Map<Order, Set<MenuItem>> orderList = DeliveryService.getInstance().getOrderList();
      orderList
         .keySet()
         .stream()
         .filter(order -> startHour <= order.getOrderDate().getHours() && order.getOrderDate().getHours() <= endHour)
         .forEach(order -> reportField.append(order + "\n"));
   }

   /**
    * <p>Creates a report containing products and their order times</p>
    * @post a stock report
    * @param orderedAmount minimum ordered time
    * @param reportField area where the report will be written
    */
   @Override
   public void generateStockReport(int orderedAmount, JTextArea reportField) {
      assert orderedAmount > 0 : "[ERROR] Invalid amount";
      reportField.setText("PRODUCTS ORDERED MORE THAN " + orderedAmount + " TIME(S) ***\n");
      ArrayList<MenuItem> productArray = new ArrayList<>();
      Map<Order, Set<MenuItem>> orderList = DeliveryService.getInstance().getOrderList();
      orderList
         .keySet()
         .stream()
         .map(orderList::get)
         .forEach(productArray::addAll);
      Map<MenuItem, Long> productFrequency = productArray
         .stream()
         .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
      ArrayList<MenuItem> orderedProducts = new ArrayList<>();
      productFrequency
         .keySet()
         .stream()
         .filter(menuItem -> productFrequency.get(menuItem) >= orderedAmount)
         .forEach(menuItem -> { reportField.append(menuItem.getTitle() + " was ordered " + productFrequency.get(menuItem) + " time(s)\n"); orderedProducts.add(menuItem);});
      reportField.append("\n ORDERS CONTAINING THOSE PRODUCTS \n");
      orderList.keySet().stream().filter(order -> orderList.get(order).stream().anyMatch(orderedProducts::contains)).forEach(order -> reportField.append(order.toString() + "\n"));
   }

   /**
    * <p>Creates a report containing loyal customers</p>
    * @param orderedAmount minimum ordered amount
    * @param orderValue minimum ordered value
    * @param reportField area where the report will be written
    */
   @Override
   public void generateLoyaltyReport(int orderedAmount, int orderValue, JTextArea reportField) {
      assert orderedAmount > 0 && orderValue > 0 : "[ERROR]: generateLoyaltyReport - Invalid amounts!";
      reportField.setText(" CLIENTS THAT HAVE ORDERED MORE THAN " + orderedAmount + " TIME(S) \n");
      ArrayList<User> clientsArray = new ArrayList<>();
      Map<Order, Set<MenuItem>> orderList = DeliveryService.getInstance().getOrderList();
      orderList
         .keySet()
         .stream()
         .filter(order -> orderList.get(order)
            .stream()
            .mapToInt(MenuItem::computePrice)
            .sum() >= orderValue)
         .forEach(order -> clientsArray.add(UserData.findUser(order.getClientId())));
      Map<User, Long> clientFrequency = clientsArray
         .stream()
         .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
      Set<User> filteredClients = new HashSet<>();
      clientFrequency
         .keySet()
         .stream()
         .filter(client -> clientFrequency.get(client) >= orderedAmount)
         .forEach(client -> { reportField.append(client.toString() + "\n"); filteredClients.add(client); });
      reportField.append("\n ORDERS MADE BY THOSE CLIENTS \n");
      orderList.keySet().stream().filter(order -> filteredClients.contains(UserData.findUser(order.getClientId()))).forEach(order -> reportField.append(order + "\n"));
   }

   /**
    * <p>Creates a report containing ordered products in a given day</p>
    * @param selectedIndex index of selected day
    * @param selectedDay selected day
    * @param reportField area where the report will be written
    */
   @Override
   @SuppressWarnings("all")
   public void generateDateReport(int selectedIndex, String selectedDay, JTextArea reportField) {
      assert selectedIndex < 7 : "[ERROR] Invalid date!";
      reportField.setText(" PRODUCTS ORDERED " + selectedDay.toUpperCase() + " \n");
      ArrayList<MenuItem> productArray = new ArrayList<>();
      Map<Order, Set<MenuItem>> orderList = DeliveryService.getInstance().getOrderList();
      orderList
         .keySet()
         .stream()
         .filter(order -> order.getOrderDate().getDay() == (selectedIndex + 1) % 7)
         .map(order -> orderList.get(order))
         .forEach(productArray::addAll);
      Map<MenuItem, Long> productFrequency = productArray
         .stream()
         .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
      productFrequency
         .keySet()
         .stream()
         .forEach(menuItem -> reportField.append(menuItem.getTitle() + " was ordered " + productFrequency.get(menuItem) + " time(s)\n"));
      reportField.append("\n ORDERS CONTAINING THOSE PRODUCTS\n");
      orderList.keySet().stream().filter(order -> orderList.get(order).stream().anyMatch(productFrequency::containsKey)).forEach(order -> reportField.append(order.toString() + "\n"));
   }

   /**
    * <p>Adds the newOrder to order list</p>
    * @param newOrder new orders
    * @param orderedProducts order's products
    * @return success or fail
    */
   @Override
   public boolean placeOrder(Order newOrder, Set<MenuItem> orderedProducts) {
      assert newOrder != null && !orderedProducts.isEmpty(): "[ERROR] placeOrder - Order empty!";
      boolean returnValue = OrderData.addOrder(newOrder, orderedProducts) && orderList.put(newOrder, orderedProducts) == null;
      if (returnValue) {
         setChanged();
         notifyObservers(newOrder);
         printOrder(newOrder, orderedProducts);
         assert orderList.containsKey(newOrder) : "[ERROR] placeOrder - Order not present ";
      }
      return returnValue;
   }

   /**
    * <p>Prints a bill for an order</p>
    * @param newOrder given order
    * @param orderedProducts ordered products
    */
   private void printOrder(Order newOrder, Set<MenuItem> orderedProducts) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)", new Locale("en", "EN"));
      AtomicInteger totalPrice = new AtomicInteger(0);
      StringBuilder productList = new StringBuilder();
      orderedProducts.forEach(product -> {
         productList.append(product.getTitle());
         productList.append(", ");
         totalPrice.addAndGet(product.computePrice());
      });
      StringBuilder orderBill = new StringBuilder("-- Order no. ");
      orderBill.append(formatNumber(newOrder.getOrderId()));
      orderBill.append(" --\nClient: ");
      orderBill.append(UserData.findUser(newOrder.getClientId()));
      orderBill.append("\nProducts: ");
      orderBill.append(productList.substring(0, productList.length() - 2));
      orderBill.append("\nTotal price: ");
      orderBill.append(totalPrice.get());
      orderBill.append("€\nDate: ");
      orderBill.append(dateFormat.format(newOrder.getOrderDate()));
      orderBill.append("\n--- THANK YOU! ---\n\n");
      try {
         FileWriter fileWriter = new FileWriter("bill.txt", true);
         fileWriter.write(orderBill.toString());
         fileWriter.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * <p>Searches a product in product list</p>
    * @param tableModel table with values
    * @param inputValues search criterias
    */
   @Override
   public void searchProduct(DefaultTableModel tableModel, List<String> inputValues) {
      menuItems
         .stream()
         .map(MenuItem::getValues)
         .filter(productValues -> equalFields(productValues, inputValues))
         .forEach(tableModel::addRow);
   }

   /**
    * <p>Checks if given lists are the same</p>
    * @param productValues product values
    * @param inputValues input values
    * @return true or false
    */
   private boolean equalFields(Object[] productValues, List<String> inputValues) {
      return IntStream
         .range(0, 7)
         .map(i -> inputValues.get(i).isBlank() || equalTypes(productValues[i], inputValues.get(i)) ? 1 : 0)
         .sum() == 7;
   }

   /**
    * <p>Checks if objects are the same</p>
    * @param productValue product value
    * @param inputValue input value
    * @return true or false
    */
   private boolean equalTypes(Object productValue, String inputValue) {
      return productValue instanceof String && ((String) productValue).toLowerCase().contains(inputValue) ||
         productValue instanceof Float && ((float) productValue + "").contains(Float.parseFloat(inputValue) + "") ||
         productValue instanceof Integer && ((int) productValue + "").contains(Integer.parseInt(inputValue) + "");
   }

   /**
    * <p>Formats a number</p>
    * @param number number
    * @return formatted number
    */
   private String formatNumber(int number) {
      return (number < 10) ? ("0" + number) : ("" + number);
   }

   /**
    * <p>Returns the menu list</p>
    * @return menu list
    */
   public Set<MenuItem> getMenuItems() {
      return menuItems;
   }

   /**
    * <p>Returns the orders list</p>
    * @return order list
    */
   public Map<Order, Set<MenuItem>> getOrderList() {
      return orderList;
   }
}
