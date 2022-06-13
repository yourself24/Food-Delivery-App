package businesslogic.listeners;

import businesslogic.Controller;
import service.UserData;
import model.User;
import presentation.MainFrame;
import presentation.UIFactory;
import presentation.panels.CredentialsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Listener for log in button</p>
 */
public class LoginListener implements ActionListener {
   private final MainFrame mainFrame;
   private final CredentialsPanel credentialsPanel;
   private final Controller appController;

   public LoginListener(MainFrame mainFrame, CredentialsPanel credentialsPanel, Controller appController) {
      this.mainFrame = mainFrame;
      this.credentialsPanel = credentialsPanel;
      this.appController = appController;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String username = credentialsPanel.getUsername();
      String password = credentialsPanel.getPassword();
      if (username.trim().isBlank() || password.trim().isBlank()) {
         UIFactory.showAlert("All fields must be filled!");
         return;
      }

      User foundUser = UserData.loginUser(username);
      if (foundUser == null) {
         UIFactory.showAlert("User not found!");
      } else if (foundUser.getPassword().compareTo(password) != 0) {
         UIFactory.showAlert("Credentials do not match!");
      } else {
         UIFactory.showNextPanel(mainFrame, appController, foundUser, "logged in");
      }
   }
}
