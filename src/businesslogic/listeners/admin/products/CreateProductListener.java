package businesslogic.listeners.admin.products;

import businesslogic.DeliveryService;
import model.CompositeProduct;
import model.MenuItem;
import presentation.UIFactory;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * <p>Listener for create product button</p>
 */
public class CreateProductListener implements ActionListener {
   private final AdminPanel adminPanel;

   public CreateProductListener(AdminPanel adminPanel) {
      this.adminPanel = adminPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      ArrayList<String> foundValues = adminPanel.getFoundProducts();
      HashSet<MenuItem> foundProducts = adminPanel.getProducts();

      CompositeProduct newProduct = new CompositeProduct(foundValues.get(5), foundProducts);

      if (DeliveryService.getInstance().createProduct(newProduct)) {
         UIFactory.showAlert("Product created successfully!");
         for (int i = 0; i < 6; i++) {
            adminPanel.clearInput(i);
         }
         adminPanel.setShowingPanel(new JPanel());
      } else {
         UIFactory.showAlert("An error occurred!");
      }
   }
}
