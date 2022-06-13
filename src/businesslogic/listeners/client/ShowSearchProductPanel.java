package businesslogic.listeners.client;

import businesslogic.Controller;
import presentation.panels.ClientPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowSearchProductPanel implements ActionListener {
   private final Controller appController;
   private final ClientPanel clientPanel;

   public ShowSearchProductPanel(Controller appController, ClientPanel clientPanel) {
      this.appController = appController;
      this.clientPanel = clientPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      clientPanel.populateTable();

      for (int i = 1; i <= 7; i++) {
         appController.addProductFormListener(clientPanel, i);
      }

      clientPanel.clearInputs(0);
      clientPanel.showSearchProductPanel();
   }
}
