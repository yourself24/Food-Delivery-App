package businesslogic.listeners.client;

import businesslogic.DeliveryService;
import presentation.panels.ClientPanel;

import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFormFilter implements KeyListener {
   private final ClientPanel clientPanel;

   public ProductFormFilter(ClientPanel clientPanel) {
      this.clientPanel = clientPanel;
   }

   @Override
   public void keyTyped(KeyEvent e) {

   }

   @Override
   public void keyPressed(KeyEvent e) {

   }

   @Override
   @SuppressWarnings("all")
   public void keyReleased(KeyEvent e) {
      DefaultTableModel tableModel = clientPanel.getTableModel();
      tableModel.setRowCount(0);
      List<String> inputValues = clientPanel.getProductInputs().stream().skip(1).map(field -> field.getText().toLowerCase()).collect(Collectors.toList());
      DeliveryService.getInstance().searchProduct(tableModel, inputValues);

      clientPanel.setTableModel(tableModel);

   }
}
