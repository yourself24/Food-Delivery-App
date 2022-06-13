package businesslogic.listeners.admin.reports;

import businesslogic.Controller;
import businesslogic.DeliveryService;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for showing the time report panel</p>
 */
public class ShowTimeReportPanel implements ActionListener {
   private final Controller appController;
   private final AdminPanel adminPanel;

   public ShowTimeReportPanel(Controller appController, AdminPanel adminPanel) {
      this.appController = appController;
      this.adminPanel = adminPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      adminPanel.getFinalReport().setText("");
      adminPanel.showTimeReportPanel();

      appController.addTimeReportButtonListener(adminPanel);
   }

    /**
     * <p>Listener for generate time report button</p>
     */
    public static class TimeReportListener implements ActionListener {
       private final AdminPanel adminPanel;

       public TimeReportListener(AdminPanel adminPanel) { this.adminPanel = adminPanel; }

       @Override
       public void actionPerformed(ActionEvent e) {
          int startHour = (int)((JSpinner)adminPanel.getReportInputs().get(0)).getValue();
          int endHour = (int)((JSpinner)adminPanel.getReportInputs().get(1)).getValue();
          JTextArea reportField = adminPanel.getFinalReport();

          DeliveryService.getInstance().generateTimeReport(startHour, endHour, reportField);
       }
    }
}
