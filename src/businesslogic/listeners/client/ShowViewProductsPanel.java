package businesslogic.listeners.client;

import presentation.panels.ClientPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowViewProductsPanel implements ActionListener {
   private final ClientPanel clientPanel;

   public ShowViewProductsPanel(ClientPanel clientPanel) {
      this.clientPanel = clientPanel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      clientPanel.populateTable();
      clientPanel.showViewProductsPanel();
   }
}
