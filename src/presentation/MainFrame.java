package presentation;

import presentation.panels.CredentialsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * <p>Main application frame</p>
 */
public class MainFrame extends JFrame {
   public static final String APP_TITLE = "Big Boletus™ Delivery Service";

   private static final int APP_WIDTH = 800;
   private static final int APP_HEIGHT = 600;
   private final JPanel contentPanel = new JPanel();
   private final JPanel mainPanel = new JPanel();
   private final CredentialsPanel registerPanel = new CredentialsPanel("Register", true);
   private final CredentialsPanel loginPanel = new CredentialsPanel("Login", false);

   private static final MainFrame INSTANCE = new MainFrame();
   /**
    * <p>Default constructor</p>
    */
   private MainFrame() {
      this.setUpFrame();
      this.setUpContentPanel();
      this.addMainPanelComponents();
   }

   public static MainFrame getInstance() {
      return INSTANCE;
   }

   /**
    * <p>Set the frame</p>
    */
   private void setUpFrame() {
      this.setLocation(300, 150);

      this.setMinimumSize(new Dimension(APP_WIDTH, APP_HEIGHT));
      this.setResizable(false);
      this.setBackground(Color.cyan);
      this.setTitle(APP_TITLE);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   /**
    * <p>Set frame panel</p>
    */
   private void setUpContentPanel() {
      contentPanel.setLayout(new BorderLayout());
      contentPanel.setBackground(Color.MAGENTA);
      this.setContentPane(contentPanel);
   }


   /**
    * <p>Add components to main panel</p>
    */
   private void addMainPanelComponents() {
      this.setUpMainPanel();
      this.showRegisterLoginPanel();
   }

   /**
    * <p>Set up main panel</p>
    */
   private void setUpMainPanel() {
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
      contentPanel.add(mainPanel, BorderLayout.CENTER);
   }

   /**
    * <p>Adds title</p>
    */
   private void addAppTitle() {
      JLabel appTitle = new JLabel(APP_TITLE.toUpperCase());
      appTitle.setFont(new Font("Impact", Font.BOLD, 44));
      appTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
      mainPanel.add(appTitle);
   }

   public void showRegisterLoginPanel() {
      JPanel midPanel = new JPanel(new GridLayout(1, 2, 5, 0));
      midPanel.setBorder(new EmptyBorder(75, 0, 100, 0));

      registerPanel.clearFields();
      loginPanel.clearFields();

      midPanel.add(registerPanel);
      midPanel.add(loginPanel);
      this.setMainPanelComponents(midPanel);
   }

   public Component[] getMainPanelComponents() { return ((JPanel)mainPanel.getComponents()[1]).getComponents(); }

   public void setMainPanelComponents(JPanel newPanel) {
      mainPanel.removeAll();
      this.addAppTitle();
      mainPanel.add(newPanel);
      mainPanel.revalidate();
      mainPanel.repaint();
   }
}