package businesslogic.listeners.admin.products;

import model.BaseProduct;
import businesslogic.DeliveryService;
import presentation.UIFactory;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for modifying a product</p>
 */
public class ModifyProductListener implements ActionListener {
   private final AdminPanel adminPanel;

   public ModifyProductListener(AdminPanel adminPanel) {
      this.adminPanel = adminPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      BaseProduct baseProduct = new BaseProduct(adminPanel.getInputs());
      if (DeliveryService.getInstance().modifyProduct(baseProduct)) {
         UIFactory.showAlert("Product modified successfully!");
         adminPanel.clearInputs(0);
         adminPanel.setShowingPanel(new JPanel());
      } else {
         UIFactory.showAlert("An error occurred!");
      }
   }
}
