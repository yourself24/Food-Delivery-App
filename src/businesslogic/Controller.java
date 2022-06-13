package businesslogic;

import businesslogic.listeners.LoginListener;
import businesslogic.listeners.ProductNameListener;
import businesslogic.listeners.RegisterListener;
import businesslogic.listeners.admin.products.*;
import businesslogic.listeners.admin.reports.*;
import businesslogic.listeners.client.*;
import presentation.MainFrame;
import presentation.UIFactory;
import presentation.panels.*;

/**
 * <p>Class used for adding  event listeners for buttons</p>
 */
public class Controller {
   private final MainFrame mainFrame;

   public Controller(MainFrame mainFrame) {
      this.mainFrame = mainFrame;
      this.addEventListeners();
   }

   private void addEventListeners() {
      if (mainFrame.getMainPanelComponents()[0] instanceof CredentialsPanel) {
         CredentialsPanel registerPanel = (CredentialsPanel) mainFrame.getMainPanelComponents()[0];
         CredentialsPanel loginPanel = (CredentialsPanel) mainFrame.getMainPanelComponents()[1];

         registerPanel.addButtonListener(new RegisterListener(mainFrame, registerPanel, this));
         loginPanel.addButtonListener(new LoginListener(mainFrame, loginPanel, this));
      }
   }

   public void addClientListeners(ClientPanel clientPanel) {
      clientPanel.addMenuButtonListener(new ShowViewProductsPanel(clientPanel), 1);
      clientPanel.addMenuButtonListener(new ShowSearchProductPanel(this, clientPanel), 2);
      clientPanel.addMenuButtonListener(new ShowPlaceOrderPanel(this, clientPanel), 3);
   }

   public void addProductFormListener(ClientPanel clientPanel, int fieldNb) {
      clientPanel.addProductFormListeners(new ProductFormFilter(clientPanel), fieldNb);
   }

   public void addPlaceOrderButtonListener(ClientPanel clientPanel) {
      clientPanel.addMenuButtonListener(new PlaceOrderListener(clientPanel), 0);
   }

   public void addProductNameListener(UserPanel userPanel, int fieldNb) {
      userPanel.addProductFormListeners(new ProductNameListener(userPanel, fieldNb), fieldNb);
   }

   public void addAdminListeners(AdminPanel adminPanel) {
      adminPanel.addMenuButtonListener(new ShowImportProductsPanel(this, adminPanel), 1);
      adminPanel.addMenuButtonListener(new ShowManageProductsPanel(this, adminPanel), 2);
      adminPanel.addMenuButtonListener(new ShowGenerateReportsPanel(this, adminPanel), 3);
   }

   public void addImportProductsButtonListener(AdminPanel adminPanel) {
      adminPanel.addMenuButtonListener(new ShowImportProductsPanel.ImportProductsListener(adminPanel), 0);
   }

   public void addManageProductsListener(AdminPanel adminPanel) {
      if (adminPanel.getMenuButtonsArray().size() == 8) {
         adminPanel.getMenuButtonsArray().remove(7);
         adminPanel.getMenuButtonsArray().remove(6);
         adminPanel.getMenuButtonsArray().remove(5);
         adminPanel.getMenuButtonsArray().remove(4);
      }

      adminPanel.getMenuButtonsArray().add(UIFactory.createButton("Add"));
      adminPanel.getMenuButtonsArray().add(UIFactory.createButton("Delete"));
      adminPanel.getMenuButtonsArray().add(UIFactory.createButton("Modify"));
      adminPanel.getMenuButtonsArray().add(UIFactory.createButton("Create"));

      adminPanel.addMenuButtonListener(new ShowAddProductPanel(this, adminPanel), 4);
      adminPanel.addMenuButtonListener(new ShowDeleteProductPanel(this, adminPanel), 5);
      adminPanel.addMenuButtonListener(new ShowModifyProductPanel(this, adminPanel), 6);
      adminPanel.addMenuButtonListener(new ShowCreateProductPanel(this, adminPanel), 7);
   }

   public void addAddProductButtonListener(AdminPanel adminPanel) {
      adminPanel.addMenuButtonListener(new ShowAddProductPanel.AddProductListener(adminPanel), 0);
   }

   public void addDeleteProductButtonListener(AdminPanel adminPanel) {
      adminPanel.addMenuButtonListener(new ShowDeleteProductPanel.DeleteProductListener(adminPanel), 0);
   }

   public void addModifyProductButtonListener(AdminPanel adminPanel) {
      adminPanel.addMenuButtonListener(new ShowModifyProductPanel.ModifyProductListener(adminPanel), 0);
   }

   public void addCreateProductButtonListener(AdminPanel adminPanel) {
      adminPanel.addMenuButtonListener(new ShowCreateProductPanel.CreateProductListener(adminPanel), 0);
   }

   public void addGenerateReportsListener(AdminPanel adminPanel) {
      if (adminPanel.getMenuButtonsArray().size() == 8) {
         adminPanel.getMenuButtonsArray().remove(7);
         adminPanel.getMenuButtonsArray().remove(6);
         adminPanel.getMenuButtonsArray().remove(5);
         adminPanel.getMenuButtonsArray().remove(4);
      }

      adminPanel.getMenuButtonsArray().add(UIFactory.createButton("Time"));
      adminPanel.getMenuButtonsArray().add(UIFactory.createButton("Stock"));
      adminPanel.getMenuButtonsArray().add(UIFactory.createButton("Loyalty"));
      adminPanel.getMenuButtonsArray().add(UIFactory.createButton("Date"));

      adminPanel.addMenuButtonListener(new ShowTimeReportPanel(this, adminPanel), 4);
      adminPanel.addMenuButtonListener(new ShowStockReportPanel(this, adminPanel), 5);
      adminPanel.addMenuButtonListener(new ShowLoyaltyReportPanel(this, adminPanel), 6);
      adminPanel.addMenuButtonListener(new ShowDateReportPanel(this, adminPanel), 7);
   }

   public void addTimeReportButtonListener(AdminPanel adminPanel) {
      adminPanel.addMenuButtonListener(new ShowTimeReportPanel.TimeReportListener(adminPanel), 0);
   }

   public void addStockReportButtonListener(AdminPanel adminPanel) {
      adminPanel.addMenuButtonListener(new ShowStockReportPanel.StockReportListener(adminPanel), 0);
   }

   public void addLoyaltyReportButtonListener(AdminPanel adminPanel) {
      adminPanel.addMenuButtonListener(new ShowLoyaltyReportPanel.LoyaltyReportListener(adminPanel), 0);
   }

   public void addDateReportButtonListener(AdminPanel adminPanel) {
      adminPanel.addMenuButtonListener(new ShowDateReportPanel.DateReportListener(adminPanel), 0);
   }
}
