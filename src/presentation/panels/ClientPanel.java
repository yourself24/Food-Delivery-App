package presentation.panels;

import businesslogic.DeliveryService;
import model.User;
import presentation.UIFactory;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Client panel</p>
 */
public class ClientPanel extends UserPanel {
   private final User client;

   public ClientPanel(User client) {
      super();
      UIFactory.setUpClientButtons(menuButton);
      this.client = client;
   }
   /**
    viewing available  products
    */
   public void showViewProductsPanel() {
      containerPanel.removeAll();

      if (DeliveryService.getInstance().getMenuItems().isEmpty()) {
         containerPanel.add(UIFactory.createLabel("No products in the menu list."));
         containerPanel.add(Box.createRigidArea(new Dimension(650, 300)));
      } else {
         containerPanel.add(UIFactory.createLabel("Viewing all the products"));
         tableScroll.setPreferredSize(new Dimension(730, 355));
         super.populateTable();
         containerPanel.add(tableScroll);
      }

      this.setShowingPanel(containerPanel);
   }
   /**
    search for a desired product
    */
   public void showSearchProductPanel() {
      containerPanel.removeAll();
      containerPanel.add(Box.createRigidArea(new Dimension(650, 10)));

      JPanel titlePanel = new JPanel();
      titlePanel.add(UIFactory.createLabel("Search a product | "));
      titlePanel.add(productLabels.get(1));
      titlePanel.add(productInputs.get(1));
      containerPanel.add(titlePanel);

      JPanel fieldsPanel = new JPanel(new GridLayout(3, 4, 20, 0));
      fieldsPanel.setMaximumSize(new Dimension(550, 200));
      for (int i = 2; i <= 7; i++) {
         productLabels.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
         fieldsPanel.add(productLabels.get(i));
         fieldsPanel.add(productInputs.get(i));
      }
      containerPanel.add(fieldsPanel);

      tableScroll.setPreferredSize(new Dimension(730, 250));
      containerPanel.add(tableScroll);
      this.setShowingPanel(containerPanel);
   }
   /**
    Panel for order placement
    */
   public void showPlaceOrderPanel() {
      containerPanel.removeAll();
      containerPanel.add(Box.createRigidArea(new Dimension(650, 10)));

      containerPanel.add(UIFactory.createLabel("Create an order"));
      containerPanel.add(Box.createRigidArea(new Dimension(650, 10)));

      JPanel fieldsPanel = new JPanel();
      fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
      super.addProductFieldsLabel(fieldsPanel);
      containerPanel.add(fieldsPanel);
      containerPanel.add(menuButton.get(0));
      containerPanel.add(Box.createRigidArea(new Dimension(650, 10)));

      this.setShowingPanel(containerPanel);
   }

   public User getClient() { return client; }
}
