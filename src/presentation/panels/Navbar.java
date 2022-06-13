package presentation.panels;

import model.User;
import presentation.UIFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * <p>Navbar panel that holds user information</p>
 */
public class Navbar extends JPanel {
   private final User user;
   private final JButton logoutBtn = UIFactory.createButton("");

   public Navbar(User user) {
      this.user = user;
      this.setUpPanel();
   }

   private void setUpPanel() {
      this.setLayout(new FlowLayout(FlowLayout.LEFT));
      this.setMaximumSize(new Dimension(750, 85));
      this.setBorder(new EmptyBorder(-15, 0, -10, 0));
      this.setBackground(Color.GRAY);

      this.addComponents();

      this.revalidate();
      this.repaint();
   }

   private void addComponents() {
      this.add(logoutBtn);

      JLabel userInfo = UIFactory.createLabel("You are logged in as " + user.getUsername() + " (" + user.getType().toLowerCase() + ")");
      this.add(userInfo);
   }

   public void addLogoutButtonListener(ActionListener crtListener) { logoutBtn.addActionListener(crtListener);}
}
