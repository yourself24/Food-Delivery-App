package businesslogic.listeners;

import businesslogic.Controller;
import model.User;
import presentation.MainFrame;
import presentation.UIFactory;
import presentation.panels.CredentialsPanel;
import service.UserData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for register button</p>
 */
public class RegisterListener implements ActionListener {
   private final MainFrame mainFrame;
   private final CredentialsPanel credentialsPanel;
   private final Controller appController;

   public RegisterListener(MainFrame mainFrame, CredentialsPanel credentialsPanel, Controller appController) {
      this.mainFrame = mainFrame;
      this.credentialsPanel = credentialsPanel;
      this.appController = appController;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      User newUser = credentialsPanel.getUser();
      if (newUser.getUsername().trim().isBlank() || newUser.getPassword().trim().isBlank()) {
         UIFactory.showAlert("All fields must be filled");
         return;
      }

      boolean foundUser = UserData.registerUser(newUser);
      if (!foundUser) {
         UIFactory.showAlert("Client already registered!");
      } else {
         UIFactory.showNextPanel(mainFrame, appController, newUser, "registered");
      }
   }
}
