package presentation.panels;

import businesslogic.DeliveryService;
import model.BaseProduct;
import model.MenuItem;
import presentation.UIFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * <p>USER panel for general use</p>
 */
public class UserPanel extends JPanel {
   protected final ArrayList<JButton> menuButton = new ArrayList<>();
   protected final ArrayList<JLabel> productLabels = new ArrayList<>();
   protected final ArrayList<JTextField> productInputs = new ArrayList<>();
   protected final ArrayList<JTextField> productFound = new ArrayList<>();
   protected final JPanel mainPanel = new JPanel();
   protected final JPanel containerPanel = new JPanel();
   protected final JTable productsTable = new JTable();
   protected JScrollPane tableScroll;
   protected DefaultTableModel tableModel;

   public UserPanel() {
      this.setUpTable();
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

      UIFactory.setUpLabels(productLabels);
      UIFactory.setUpInputs(productInputs);
      UIFactory.setUpInputs(productFound);
      if (this instanceof AdminPanel) {
         UIFactory.setUpAdminButtons(menuButton);
      } else {
         UIFactory.setUpClientButtons(menuButton);
      }

      JPanel buttonsPanel = UIFactory.createButtonsPanel(new JButton[]{menuButton.get(1), menuButton.get(2), menuButton.get(3)});
      this.add(buttonsPanel);

      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
      this.add(mainPanel);

      this.revalidate();
      this.repaint();
   }

   private void setUpTable() {
      ArrayList<String> tableHeader = new ArrayList<>();
      Field[] fields = BaseProduct.class.getDeclaredFields();
      for (int i = 1; i < fields.length; i++) {
         tableHeader.add(fields[i].getName().toUpperCase());
      }
      tableModel = new DefaultTableModel(tableHeader.toArray(), 0);

      productsTable.setModel(tableModel);
      productsTable.getTableHeader().setFont(new Font("JetBrains Mono", Font.PLAIN, 16));
      productsTable.getTableHeader().setReorderingAllowed(false);
      productsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
      productsTable.setEnabled(false);

      tableScroll = new JScrollPane(productsTable);
   }

   public void setShowingPanel(JPanel newPanel) {
      mainPanel.removeAll();

      mainPanel.add(newPanel);

      mainPanel.revalidate();
      mainPanel.repaint();
   }

   public void populateTable() {
      tableModel.setRowCount(0);
      DeliveryService.getInstance().getMenuItems().forEach(menuItem -> tableModel.addRow(menuItem.getValues()));
   }

   protected void addProductFieldsLabel(JPanel fieldsPanel) {
      for (int i = 0; i < 5; i++) {
         JPanel rowEntry = new JPanel();
         JLabel productNb = UIFactory.createLabel("Product " + (i + 1));
         rowEntry.add(productNb);
         productInputs.get(i).setEnabled(true);
         rowEntry.add(productInputs.get(i));
         productFound.get(i).setEnabled(false);
         rowEntry.add(productFound.get(i));
         fieldsPanel.add(rowEntry);
      }
   }

   public String getInputs() {
      StringBuilder values = new StringBuilder();
      for (int i = 1; i <= 6; i++) {
         values.append(productInputs.get(i).getText()).append(",");
      }
      values.append(productInputs.get(7).getText());
      return values.toString();
   }

   public void setInput(String value, int fieldNb) {
      productFound.get(fieldNb).setText(value);
   }

   public void setInputs(Object[] values) {
      for (int i = 0; i < values.length; i++) {
         productInputs.get(i + 1).setText(values[i] == null ? "NOT FOUND" : values[i].toString());
      }
   }

   public void clearInput(int fieldNb) {
      productFound.get(fieldNb).setText("");
   }

   public void clearInputs(int skipName) {
      productInputs.stream().skip(skipName).forEach(crtInput -> crtInput.setText(""));
   }

   public ArrayList<String> getFoundProducts() {
      ArrayList<String> foundNames = new ArrayList<>();
      productFound.forEach(crtField -> foundNames.add(crtField.getText()));
      return foundNames;
   }

   public void addProductFormListeners(KeyListener keyListener, int fieldNb) {
      int fieldNumber = (fieldNb == -1) ? 0 : fieldNb;
      for (KeyListener listenerIterator : productInputs.get(fieldNumber).getKeyListeners()) {
         productInputs.get(fieldNumber).removeKeyListener(listenerIterator);
      }
      productInputs.get(fieldNumber).addKeyListener(keyListener);
   }

   public void addMenuButtonListener(ActionListener crtListener, int buttonNb) {
      for (ActionListener listenerIterator : menuButton.get(buttonNb).getActionListeners()) {
         menuButton.get(buttonNb).removeActionListener(listenerIterator);
      }
      menuButton.get(buttonNb).addActionListener(crtListener);
   }

   public HashSet<MenuItem> getProducts() {
      HashSet<MenuItem> productSet = new HashSet<>();
      for (int i = 0; i < 5; i++) {
         if (productFound.get(i).getText().isBlank()) continue;
         for (MenuItem menuItem : DeliveryService.getInstance().getMenuItems()) {
            if (menuItem.getTitle().compareTo(productFound.get(i).getText()) == 0) {
               productSet.add(menuItem);
               break;
            }
         }
      }
      return productSet;
   }

   public JPanel getMenuButtonsPanel() {
      return UIFactory.createButtonsPanel(new JButton[]{menuButton.get(4), menuButton.get(5), menuButton.get(6), menuButton.get(7)});
   }

   public DefaultTableModel getTableModel() {
      return tableModel;
   }

   public void setTableModel(DefaultTableModel tableModel) {
      this.tableModel = tableModel;
   }

   public JTable getTable() {
      return productsTable;
   }

   public ArrayList<JTextField> getProductInputs() {
      return productInputs;
   }

   public JPanel getContainerPanel() {
      return containerPanel;
   }

   public ArrayList<JButton> getMenuButtonsArray() {
      return menuButton;
   }
}
