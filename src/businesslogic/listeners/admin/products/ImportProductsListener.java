package businesslogic.listeners.admin.products;

import businesslogic.DeliveryService;
import presentation.UIFactory;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for importing products button</p>
 */
public class ImportProductsListener implements ActionListener {
   private final AdminPanel adminPanel;

   public ImportProductsListener(AdminPanel adminPanel) {
      this.adminPanel = adminPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (UIFactory.showConfirm("You are going to import the product list. Are you sure?") == 0) {
         if (DeliveryService.getInstance().importProducts()) {
            UIFactory.showAlert("Successfully imported the products!");
            adminPanel.setShowingPanel(new JPanel());
         } else {
            UIFactory.showAlert("An error occurred!");
         }
      }
   }
}
