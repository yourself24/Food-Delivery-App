package businesslogic.listeners.admin.products;

import model.BaseProduct;
import businesslogic.DeliveryService;
import presentation.UIFactory;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for add product button</p>
 */
public class AddProductListener implements ActionListener {
   private final AdminPanel adminPanel;

   public AddProductListener(AdminPanel adminPanel) { this.adminPanel = adminPanel; }

   @Override
   public void actionPerformed(ActionEvent e) {
      try {
         BaseProduct newProduct = new BaseProduct(adminPanel.getInputs());
         if (!DeliveryService.getInstance().addProduct(newProduct)) {
            UIFactory.showAlert("Product already exists!");
         } else {
            UIFactory.showAlert("Successfully added a new product!");
            adminPanel.clearInputs(0);
            adminPanel.setShowingPanel(new JPanel());
         }
      } catch (Exception exception) {
         UIFactory.showAlert("Invalid input values!");
      }
   }
}
