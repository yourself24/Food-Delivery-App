package businesslogic.listeners.admin.products;

import businesslogic.Controller;
import businesslogic.DeliveryService;
import presentation.UIFactory;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for showing the import products panel</p>
 */
public class ShowImportProductsPanel implements ActionListener {
   private final Controller appController;
   private final AdminPanel adminPanel;

   public ShowImportProductsPanel(Controller appController, AdminPanel adminPanel) {
      this.appController = appController;
      this.adminPanel = adminPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      appController.addImportProductsButtonListener(adminPanel);

      JButton generateReportButton = adminPanel.getMenuButtonsArray().get(0);
      generateReportButton.setText("IMPORT");
      generateReportButton.setEnabled(true);

      adminPanel.showImportProductsPanel();
   }

    /**
     * <p>Listener for importing products button</p>
     */
    public static class ImportProductsListener implements ActionListener {
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
}
