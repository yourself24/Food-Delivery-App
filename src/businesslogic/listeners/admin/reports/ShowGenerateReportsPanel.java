package businesslogic.listeners.admin.reports;

import businesslogic.Controller;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for showing generate reports panel</p>
 */
public class ShowGenerateReportsPanel implements ActionListener {
   private final Controller appController;
   private final AdminPanel adminPanel;

   public ShowGenerateReportsPanel(Controller appController, AdminPanel adminPanel) {
      this.appController = appController;
      this.adminPanel = adminPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      appController.addGenerateReportsListener(adminPanel);

      JPanel container = new JPanel();
      container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
      container.add(adminPanel.getMenuButtonsPanel());

      JButton generateReportButton = adminPanel.getMenuButtonsArray().get(0);
      generateReportButton.setEnabled(true);
      generateReportButton.setText("GENERATE");

      adminPanel.setShowingPanel(container);
   }
}
