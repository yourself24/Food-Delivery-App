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
 * <p>Listener for showing the delete product panel</p>
 */
public class ShowDeleteProductPanel implements ActionListener {
   private final Controller appController;
   private final AdminPanel adminPanel;

   public ShowDeleteProductPanel(Controller appController, AdminPanel adminPanel) {
      this.appController = appController;
      this.adminPanel = adminPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      adminPanel.clearInputs(0);
      adminPanel.showSearchProductPanel("Delete");

      JButton addProductButton = adminPanel.getMenuButtonsArray().get(0);
      addProductButton.setText("DELETE");
      addProductButton.setEnabled(false);

      appController.addProductNameListener(adminPanel, -1);
      appController.addDeleteProductButtonListener(adminPanel);
   }

   /**
    * <p>Listener for delete product button</p>
    */
   public static class DeleteProductListener implements ActionListener {
      private final AdminPanel adminPanel;

      public DeleteProductListener(AdminPanel adminPanel) {
         this.adminPanel = adminPanel;
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         int userOption = UIFactory.showConfirm("Confirm product removal!");
         if (userOption == 0) {
            BaseProduct toBeRemoved = new BaseProduct(adminPanel.getInputs());
            if (DeliveryService.getInstance().deleteProduct(toBeRemoved)) {
               UIFactory.showAlert("Product!");
               adminPanel.clearInputs(0);
               adminPanel.setShowingPanel(new JPanel());
            } else {
               UIFactory.showAlert("Error occurred!");
            }
         }
      }
   }
}
