package businesslogic.listeners.client;

import businesslogic.DeliveryService;
import model.MenuItem;
import model.Order;
import presentation.UIFactory;
import presentation.panels.ClientPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;

public class PlaceOrderListener implements ActionListener {
   private final ClientPanel clientPanel;

   public PlaceOrderListener(ClientPanel clientPanel) {
      this.clientPanel = clientPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      HashSet<MenuItem> orderedProducts = clientPanel.getProducts();

      Order newOrder = new Order(clientPanel.getClient().getId(), new Date());
      if (DeliveryService.getInstance().placeOrder(newOrder, orderedProducts)) {
         UIFactory.showAlert("Order placed successfully!");
         for (int i = 1; i < 6; i++) {
            clientPanel.clearInput(i);
         }
         clientPanel.setShowingPanel(new JPanel());
      } else {
         UIFactory.showAlert("An error occurred!");
      }
   }
}
