package businesslogic.listeners.client;

import businesslogic.Controller;
import presentation.panels.ClientPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPlaceOrderPanel implements ActionListener {
   private final Controller appController;
   private final ClientPanel clientPanel;

   public ShowPlaceOrderPanel(Controller appController, ClientPanel clientPanel) {
      this.appController = appController;
      this.clientPanel = clientPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      clientPanel.clearInputs(0);
      clientPanel.showPlaceOrderPanel();

      JButton createProductButton = clientPanel.getMenuButtonsArray().get(0);
      createProductButton.setText("PLACE");
      createProductButton.setEnabled(false);

      for (int i = 0; i < 5; i++) {
         clientPanel.clearInput(i);
         appController.addProductNameListener(clientPanel, i);
      }

      clientPanel.clearInput(5);

      appController.addPlaceOrderButtonListener(clientPanel);
   }
}
