package businesslogic.listeners.admin.products;

import businesslogic.Controller;
import businesslogic.DeliveryService;
import model.BaseProduct;
import presentation.UIFactory;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for showing the add product panel</p>
 */
public class ShowAddProductPanel implements ActionListener {
   private final Controller appController;
   private final AdminPanel adminPanel;

   public ShowAddProductPanel(Controller appController, AdminPanel adminPanel) {
      this.appController = appController;
      this.adminPanel = adminPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      adminPanel.clearInputs(1);
      adminPanel.showSearchProductPanel("Add");

      JButton addProductButton = adminPanel.getMenuButtonsArray().get(0);
      addProductButton.setText("ADD");
      addProductButton.setEnabled(true);

      appController.addAddProductButtonListener(adminPanel);
   }

   /**
    * <p>Listener for add product button</p>
    */
   public static class AddProductListener implements ActionListener {
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
}
