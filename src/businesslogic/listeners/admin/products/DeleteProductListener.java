package businesslogic.listeners.admin.products;

import model.BaseProduct;
import businesslogic.DeliveryService;
import presentation.UIFactory;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for delete product button</p>
 */
public class DeleteProductListener implements ActionListener {
   private final AdminPanel adminPanel;

   public DeleteProductListener(AdminPanel adminPanel) {
      this.adminPanel = adminPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      int userOption = UIFactory.showConfirm("Are you sure you want to remove this product?");
      if (userOption == 0) {
         BaseProduct toBeRemoved = new BaseProduct(adminPanel.getInputs());
         if (DeliveryService.getInstance().deleteProduct(toBeRemoved)) {
            UIFactory.showAlert("Product removed successfully!");
            adminPanel.clearInputs(0);
            adminPanel.setShowingPanel(new JPanel());
         } else {
            UIFactory.showAlert("An error occurred!");
         }
      }
   }
}
