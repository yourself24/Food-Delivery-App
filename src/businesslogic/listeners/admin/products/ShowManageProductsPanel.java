package businesslogic.listeners.admin.products;

import businesslogic.Controller;
import presentation.panels.AdminPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for showing the manage products panel</p>
 */
public class ShowManageProductsPanel implements ActionListener {
   private final Controller appController;
   private final AdminPanel adminPanel;

   public ShowManageProductsPanel(Controller appController, AdminPanel adminPanel) {
      this.appController = appController;
      this.adminPanel = adminPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      appController.addManageProductsListener(adminPanel);

      JPanel container = new JPanel();
      container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
      container.add(adminPanel.getMenuButtonsPanel());

      adminPanel.setShowingPanel(container);
   }
}
