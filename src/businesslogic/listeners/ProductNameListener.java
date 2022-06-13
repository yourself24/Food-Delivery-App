package businesslogic.listeners;

import businesslogic.DeliveryService;
import model.CompositeProduct;
import model.MenuItem;
import presentation.UIFactory;
import presentation.panels.UserPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Key listener for product name</p>
 */
public class ProductNameListener implements KeyListener {
   private final UserPanel userPanel;
   private final JPanel containerPanel;
   private final int currentField;
   private boolean foundProduct;

   public ProductNameListener(UserPanel userPanel, int currentField) {
      this.userPanel = userPanel;
      this.containerPanel = userPanel.getContainerPanel();
      this.currentField = currentField;
   }

   @Override
   public void keyTyped(KeyEvent e) {

   }

   @Override
   public void keyPressed(KeyEvent e) {

   }

   @Override
   public void keyReleased(KeyEvent e) {
      String inputValue = ((JTextField) e.getSource()).getText();

      searchForProduct(inputValue);
      checkIfProductExists(inputValue);
      setButtonEnableState();

      containerPanel.repaint();
      containerPanel.revalidate();
   }

   private void searchForProduct(String inputValue) {
      foundProduct = false;

      for (MenuItem menuItem : DeliveryService.getInstance().getMenuItems()) {
         if (menuItem.getTitle().toLowerCase().contains(inputValue.toLowerCase())) {
            if (currentField == -1) {
               userPanel.setInputs(menuItem.getValues());
            } else {
               userPanel.setInput(menuItem.getTitle(), currentField);
            }
            checkIfComposite(menuItem);
            foundProduct = true;
            break;
         }
      }
   }

   private void checkIfComposite(MenuItem menuItem) {
      if (menuItem instanceof CompositeProduct) {
         StringBuilder compositeAlert = new StringBuilder("<html>Note!<br/>The product " + menuItem.getTitle() + " is composed of: <br/>");
         for (MenuItem menuProduct : ((CompositeProduct) menuItem).getProductList()) {
            compositeAlert.append(menuProduct.getTitle());
            compositeAlert.append("<br/>");
         }
         compositeAlert.append("</html>");
         UIFactory.showAlert(compositeAlert.toString());
      }
   }

   private void checkIfProductExists(String inputValue) {
      if (!foundProduct || inputValue.trim().isBlank()) {
         if (currentField == -1) {
            userPanel.clearInputs(1);
         } else {
            userPanel.clearInput(currentField);
         }
      }
   }

   private void setButtonEnableState() {
      JButton actionButton = userPanel.getMenuButtonsArray().get(0);

      if (currentField == -1) {
         actionButton.setEnabled(foundProduct);
      } else {
         List<String> productList = DeliveryService.getInstance().getMenuItems()
                 .stream()
                 .map(menuItem -> menuItem.getTitle().toLowerCase())
                 .collect(Collectors.toList());

         int foundProducts = (int) userPanel.getFoundProducts()
                 .stream()
                 .filter(value -> !value.isBlank())
                 .map(String::toLowerCase)
                 .filter(productList::contains).count();

         actionButton.setEnabled(foundProducts != 0);
      }
   }
}
