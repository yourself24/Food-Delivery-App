package presentation.panels;

import model.User;
import presentation.UIFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * <p>Panel used for registering or logging in an user</p>
 */
public class CredentialsPanel extends JPanel {
   private final String title;
   private final boolean showType;
   private final JLabel titleLabel = UIFactory.createLabel("");
   private final JLabel nameLabel = UIFactory.createLabel("Username: ");
   private final JTextField nameInput = UIFactory.createInput();
   private final JLabel passwordLabel = UIFactory.createLabel("Password:  ");
   private final JPasswordField passwordInput = UIFactory.createPassword();
   private final JLabel typeLabel = UIFactory.createLabel("Type:           ");
   private final JComboBox<String> typeInput = UIFactory.createComboBox(new String[]{"client", "employee", "admin"});
   private final JButton actionButton = UIFactory.createButton("");

   /**
    * <p>Default constructor</p>
    * @param title panel title
    */
   public CredentialsPanel(String title, boolean showType) {
      this.title = title;
      this.showType = showType;
      this.setUpComponents();
      this.addComponents();
   }

   /**
    * <p>Sets up components</p>
    */
   private void setUpComponents() {
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.setBackground(Color.PINK);
      UIFactory.initializeTitle(titleLabel);
      actionButton.setText(title.toUpperCase());
   }

   /**
    * <p>Adds components to panel</p>
    */
   private void addComponents() {
      this.add(titleLabel);

      JPanel namePanel = new JPanel();
      namePanel.setBorder(new EmptyBorder(20, 0, 0, 0));
      namePanel.add(nameLabel);
      namePanel.setBackground(Color.PINK);
      namePanel.add(nameInput);
      this.add(namePanel);

      JPanel passPanel = new JPanel();
      passPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
      passPanel.add(passwordLabel);
      passPanel.setBackground(Color.PINK);
      passPanel.add(passwordInput);
      this.add(passPanel);

      if (this.showType) {
         JPanel typePanel = new JPanel();
         typePanel.setBorder(new EmptyBorder(0, 0, 20, 0));
         typePanel.add(typeLabel);
         typePanel.setBackground(Color.PINK);
         typePanel.add(typeInput);
         this.add(typePanel);
      } else {
         this.add(Box.createRigidArea(new Dimension(650, 80)));
      }

      this.add(actionButton);
   }

   /**
    * <p>Gets username from textfield</p>
    * @return user's name
    */
   public String getUsername() { return nameInput.getText(); }

   /**
    * <p>Gets password from textfield</p>
    * @return user's password
    */
   public String getPassword() { return new String(passwordInput.getPassword()); }

   /**
    * <p>Gets type of account(administrator,employee,client)</p>
    * @return user's type
    */
   public String getType() { return Objects.requireNonNull(typeInput.getSelectedItem()).toString();}


   public void addButtonListener(ActionListener crtListener) { actionButton.addActionListener(crtListener);}

   public User getUser() {
      User newUser = new User();
      newUser.setUsername(getUsername());
      newUser.setPassword(getPassword());
      newUser.setType(getType());
      return newUser;
   }
   /**
    clears all fields
    */
   public void clearFields() {
      nameInput.setText("");
      passwordInput.setText("");
      typeInput.setSelectedIndex(0);
   }
}
