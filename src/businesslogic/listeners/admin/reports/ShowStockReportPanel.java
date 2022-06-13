package businesslogic.listeners.admin.reports;

import businesslogic.Controller;
import businesslogic.DeliveryService;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for showing the stock report panel</p>
 */
public class ShowStockReportPanel implements ActionListener {
   private final Controller appController;
   private final AdminPanel adminPanel;

   public ShowStockReportPanel(Controller appController, AdminPanel adminPanel) {
      this.appController = appController;
      this.adminPanel = adminPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      adminPanel.getFinalReport().setText("");
      adminPanel.showStockReportPanel();

      appController.addStockReportButtonListener(adminPanel);
   }

    /**
     * <p>Listener for generate stock report button</p>
     */
    public static class StockReportListener implements ActionListener {
       private final AdminPanel adminPanel;

       public StockReportListener(AdminPanel adminPanel) {
          this.adminPanel = adminPanel;
       }

       @Override
       public void actionPerformed(ActionEvent e) {
          int orderedAmount = (int) ((JSpinner) adminPanel.getReportInputs().get(0)).getValue();
          JTextArea reportField = adminPanel.getFinalReport();

          DeliveryService.getInstance().generateStockReport(orderedAmount, reportField);
       }
    }
}
