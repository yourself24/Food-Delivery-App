package presentation.panels;

import businesslogic.DeliveryService;
import presentation.UIFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;


public class AdminPanel extends UserPanel {
   private final ArrayList<Object> reportInputs = new ArrayList<>();
   private final JTextArea finalReport = UIFactory.createTextArea();
   private JScrollPane reportScroll;

   public AdminPanel() {
      super();
      UIFactory.setUpSpinners(reportInputs);
      this.setUpReportPanel();
   }


   /**
    * <p>Panel for product importing</p>
    */
   public void showImportProductsPanel() {
      containerPanel.removeAll();
      JPanel titlePanel = new JPanel();
      titlePanel.add(UIFactory.createLabel("Import products | "));
      titlePanel.add(menuButton.get(0));
      containerPanel.add(titlePanel);

      if (DeliveryService.getInstance().getMenuItems().isEmpty()) {
        containerPanel.add(UIFactory.createLabel("Currently there are no products in the menu list."));
        containerPanel.add(Box.createRigidArea(new Dimension(650, 330)));
      } else {
         containerPanel.add(UIFactory.createLabel("Viewing all the products"));

         super.populateTable();

         containerPanel.add(tableScroll);
      }

      this.setShowingPanel(containerPanel);
   }
   /**
    * <p>Panel for reports</p>
    */
   private void setUpReportPanel() {
      reportScroll = new JScrollPane(finalReport);
      reportScroll.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(Color.BLACK)));
      reportScroll.setPreferredSize(new Dimension(630, 500));
   }

   /**
    * <p>Panel for product searching</p>
    */
   public void showSearchProductPanel(String operationType) {
      containerPanel.removeAll();
      containerPanel.add(Box.createRigidArea(new Dimension(650, 10)));

      containerPanel.add(UIFactory.createLabel(operationType + " a product"));
      containerPanel.add(Box.createRigidArea(new Dimension(650, 10)));

      int showName = (operationType.compareTo("Add") != 0 ? 1 : 0);
      JPanel fieldsPanel = new JPanel(new GridLayout(7 + showName, 2, -100, 5));
      fieldsPanel.setMaximumSize(new Dimension(550, 250));

      boolean fieldEnabled = (operationType.compareTo("Delete") != 0);
      for (int i = 1 - showName; i < 8; i++) {
         productLabels.get(i).setHorizontalAlignment(SwingConstants.LEFT);
         fieldsPanel.add(productLabels.get(i));
         productInputs.get(i).setEnabled(i == 0 || fieldEnabled && !(i == 1 && showName > 0));
         fieldsPanel.add(productInputs.get(i));
      }

      containerPanel.add(fieldsPanel);
      containerPanel.add(Box.createRigidArea(new Dimension(650, 10)));
      containerPanel.add(menuButton.get(0));
      this.setShowingPanel(containerPanel);
   }
   /**
    * <p>Panel for product creation</p>
    */
   public void showCreateProductPanel() {
      containerPanel.removeAll();
      containerPanel.add(Box.createRigidArea(new Dimension(650, 10)));

      containerPanel.add(UIFactory.createLabel("Create a product"));
      containerPanel.add(Box.createRigidArea(new Dimension(650, 10)));

      JPanel fieldsPanel = new JPanel();
      fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

      JPanel newProductName = new JPanel();
      newProductName.add(UIFactory.createLabel("New product name"));
      newProductName.add(productFound.get(5));
      fieldsPanel.add(newProductName);

      super.addProductFieldsLabel(fieldsPanel);

      containerPanel.add(fieldsPanel);
      containerPanel.add(menuButton.get(0));
      containerPanel.add(Box.createRigidArea(new Dimension(650, 10)));

      this.setShowingPanel(containerPanel);
   }
   /**
    * <p>Panel for time reports</p>
    */
   public void showTimeReportPanel() {
      containerPanel.removeAll();

      JPanel titlePanel = new JPanel();
      titlePanel.add(UIFactory.createLabel("Time interval  -- "));

      titlePanel.add(menuButton.get(0));
      containerPanel.add(titlePanel);

      JPanel hourPanel = new JPanel();
      hourPanel.add(UIFactory.createLabel("Start hour: "));
      hourPanel.add((JSpinner) reportInputs.get(0));
      hourPanel.add(UIFactory.createLabel("End hour: "));
      hourPanel.add((JSpinner) reportInputs.get(1));
      containerPanel.add(hourPanel);

      containerPanel.add(reportScroll);

      this.setShowingPanel(containerPanel);
   }
   /**
    * <p>Panel for stock reports</p>
    */
   public void showStockReportPanel() {
      containerPanel.removeAll();

      JPanel titlePanel = new JPanel();
      titlePanel.add(UIFactory.createLabel("Most ordered products : "));
      titlePanel.add(menuButton.get(0));
      containerPanel.add(titlePanel);

      JPanel numberPanel = new JPanel();
      numberPanel.add(UIFactory.createLabel("Number: "));
      numberPanel.add((JSpinner) reportInputs.get(0));
      containerPanel.add(numberPanel);

      containerPanel.add(reportScroll);

      this.setShowingPanel(containerPanel);
   }
   /**
    * <p>Panel for Loyalty report</p>
    */
   public void showLoyaltyReportPanel() {
      containerPanel.removeAll();

      JPanel titlePanel = new JPanel();
      titlePanel.add(UIFactory.createLabel("Recurring customers : "));
      titlePanel.add(menuButton.get(0));
      containerPanel.add(titlePanel);

      JPanel loyalPanel = new JPanel();
      loyalPanel.add(UIFactory.createLabel("Minimum  no. of orders: "));
      loyalPanel.add((JSpinner) reportInputs.get(0));
      loyalPanel.add(UIFactory.createLabel("Minimum spent: "));
      loyalPanel.add((JSpinner) reportInputs.get(1));
      containerPanel.add(loyalPanel);

      containerPanel.add(reportScroll);

      this.setShowingPanel(containerPanel);
   }


   @SuppressWarnings("unchecked")
   public void showDateReportPanel() {
      containerPanel.removeAll();

      JPanel titlePanel = new JPanel();
      titlePanel.add(UIFactory.createLabel("Date interval -- "));

      titlePanel.add(menuButton.get(0));
      containerPanel.add(titlePanel);

      JPanel dayPanel = new JPanel();
      dayPanel.add(UIFactory.createLabel("Day : "));
      dayPanel.add((JComboBox<String>) reportInputs.get(2));
      containerPanel.add(dayPanel);

      containerPanel.add(reportScroll);

      this.setShowingPanel(containerPanel);
   }

   public JTextArea getFinalReport() {
      return finalReport;
   }

   public ArrayList<Object> getReportInputs() { return reportInputs; }
}
