package businesslogic.listeners.admin.reports;

import businesslogic.Controller;
import businesslogic.DeliveryService;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for showing rich report panel</p>
 */
public class ShowLoyaltyReportPanel implements ActionListener {
    private final Controller appController;
    private final AdminPanel adminPanel;

    public ShowLoyaltyReportPanel(Controller appController, AdminPanel adminPanel) {
        this.appController = appController;
        this.adminPanel = adminPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adminPanel.getFinalReport().setText("");
        adminPanel.showLoyaltyReportPanel();

        appController.addLoyaltyReportButtonListener(adminPanel);
    }

    /**
     * <p>Listener for generate rich report button</p>
     */
    public static class LoyaltyReportListener implements ActionListener {
        private final AdminPanel adminPanel;

        public LoyaltyReportListener(AdminPanel adminPanel) { this.adminPanel = adminPanel; }

        @Override
        public void actionPerformed(ActionEvent e) {
            int orderedAmount = (int) ((JSpinner) adminPanel.getReportInputs().get(0)).getValue();
            int orderValue = (int) ((JSpinner) adminPanel.getReportInputs().get(1)).getValue();
            JTextArea reportField = adminPanel.getFinalReport();

            DeliveryService.getInstance().generateLoyaltyReport(orderedAmount, orderValue, reportField);
        }
    }
}
