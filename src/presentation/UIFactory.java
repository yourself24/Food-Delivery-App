package presentation;

import businesslogic.Controller;
import model.User;
import presentation.panels.AdminPanel;
import presentation.panels.ClientPanel;
import presentation.panels.EmployeePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.Component.CENTER_ALIGNMENT;

/**
 * <p>Basic class for UI construction(hence the name UIFactrory ;D )</p>
 */
public class UIFactory {
   public static final Font APP_FONT = new Font("JetBrains Mono", Font.PLAIN, 20);

   /**
    * <p>Creates a label</p>
    * @param message label message
    * @return label
    */
   public static JLabel createLabel(String message) {
      JLabel newLabel = new JLabel(message);
      newLabel.setAlignmentX(CENTER_ALIGNMENT);
      newLabel.setHorizontalAlignment(SwingConstants.CENTER);
      newLabel.setFont(UIFactory.APP_FONT);
      return newLabel;
   }

   /**
    * <p>Creates input field</p>
    * @return input field
    */
   public static JTextField createInput() {
      JTextField newInput = new JTextField(12);
      newInput.setAlignmentX(CENTER_ALIGNMENT);
      newInput.setFont(UIFactory.APP_FONT);
      return newInput;
   }

   public static JTextArea createTextArea() {
      JTextArea newInput = new JTextArea();
      newInput.setEditable(false);
      newInput.setFont(UIFactory.APP_FONT);
      return newInput;
   }

   /**
    * <p>Creates an input field</p>
    * @return input field
    */
   public static JSpinner createSpinner() {
      JSpinner newInput = new JSpinner(new SpinnerNumberModel(12, 0, 999, 1));
      newInput.setAlignmentX(CENTER_ALIGNMENT);
      newInput.setFont(UIFactory.APP_FONT);
      ((DefaultFormatter)((JSpinner.DefaultEditor)newInput.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
      return newInput;
   }

   /**
    * <p>Creates an input field for password</p>
    * @return password field
    */
   public static JPasswordField createPassword() {
      JPasswordField newPassword = new JPasswordField(12);
      newPassword.setAlignmentX(CENTER_ALIGNMENT);
      newPassword.setFont(UIFactory.APP_FONT);
      return newPassword;
   }

   /**
    * <p>Creates a button</p>
    * @return button
    */
   public static JButton createButton(String message) {
      JButton newButton = new JButton(message.toUpperCase());
      newButton.setFont(new Font("Impact", Font.PLAIN, 20));
      newButton.setAlignmentX(CENTER_ALIGNMENT);
      newButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
      return newButton;
   }

   /**
    * <p>Creates a combobox of options</p>
    * @return  combobox
    */
   public static JComboBox<String> createComboBox(String[] values) {
      JComboBox<String> newComboBox = new JComboBox<>();
      newComboBox.setPreferredSize(new Dimension(220, 30));
      for (String value : values) {
         newComboBox.addItem(value);
      }
      newComboBox.setFont(UIFactory.APP_FONT);
      return newComboBox;
   }

   /**
    * <p>Set up the panel title</p>
    * @param panelTitle label
    */
   public static void initializeTitle(JLabel panelTitle) {
      panelTitle.setAlignmentX(CENTER_ALIGNMENT);
      panelTitle.setFont(new Font("Impact", Font.BOLD, 25));
      panelTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
   }

   /**
    * <p>Display an alert </p>
    * @param message alert message
    */
   public static void showAlert(String message) {
      JLabel alertMessage = new JLabel(message);
      alertMessage.setFont(UIFactory.APP_FONT);
      JOptionPane.showMessageDialog(MainFrame.getInstance(), alertMessage);
   }

   /**
    * <p>Displays confirmation frame </p>
    * @param message confirmation message
    * @return  option
    */
   public static int showConfirm(String message) {
      JLabel confirmMessage = new JLabel(message);
      confirmMessage.setFont(UIFactory.APP_FONT);
      return JOptionPane.showConfirmDialog(MainFrame.getInstance(), confirmMessage, "Are you sure?", JOptionPane.YES_NO_OPTION);
   }

   public static JPanel createUserInfoPanel(Controller appController, User foundUser) {


      JPanel midPanel = new JPanel();
      midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

      return midPanel;
   }

   public static JPanel createButtonsPanel(JButton[] buttons) {
      JPanel buttonsPanel = new JPanel();
      buttonsPanel.setLayout(new GridLayout(1, buttons.length));
      buttonsPanel.setMaximumSize(new Dimension(750, 35));
      buttonsPanel.setBackground(Color.MAGENTA);

      for (JButton button : buttons) {
         buttonsPanel.add(button);
      }

      return buttonsPanel;
   }

   public static void setUpLabels(ArrayList<JLabel> labelArray) {
      labelArray.add(UIFactory.createLabel("Product"));
      labelArray.add(UIFactory.createLabel("Title"));
      labelArray.add(UIFactory.createLabel("Rating"));
      labelArray.add(UIFactory.createLabel("Calories"));
      labelArray.add(UIFactory.createLabel("Protein"));
      labelArray.add(UIFactory.createLabel("Fat"));
      labelArray.add(UIFactory.createLabel("Sodium"));
      labelArray.add(UIFactory.createLabel("Price"));
   }

   public static void setUpInputs(ArrayList<JTextField> inputArray) {
      for (int i = 0; i < 8; i++) {
         inputArray.add(UIFactory.createInput());
      }
   }

   public static void setUpSpinners(ArrayList<Object> spinnerz) {
      spinnerz.add(UIFactory.createSpinner());
      spinnerz.add(UIFactory.createSpinner());
      spinnerz.add(UIFactory.createComboBox(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}));
   }

   public static void setUpAdminButtons(ArrayList<JButton> buttonz) {
      buttonz.add(UIFactory.createButton(""));
      buttonz.add(UIFactory.createButton("Import products"));
      buttonz.add(UIFactory.createButton("Manage products"));
      buttonz.add(UIFactory.createButton("Generate reports"));
   }

   public static void setUpClientButtons(ArrayList<JButton> buttonz) {
      buttonz.add(UIFactory.createButton(""));
      buttonz.add(UIFactory.createButton("View products"));
      buttonz.add(UIFactory.createButton("Search product"));
      buttonz.add(UIFactory.createButton("Place order"));
   }

   public static void showNextPanel(MainFrame mainFrame, Controller appController, User crtUser, String operation) {
      String userType = crtUser.getType();
      UIFactory.showAlert("Successfully " + operation + " as " + userType + ".");

      JPanel midPanel = UIFactory.createUserInfoPanel(appController, crtUser);

      if (userType.compareTo("client") == 0) {
         ClientPanel clientPanel = new ClientPanel(crtUser);
         appController.addClientListeners(clientPanel);
         midPanel.add(clientPanel);
      } else if (userType.compareTo("employee") == 0) {
         EmployeePanel employeePanel = EmployeePanel.getInstance();
         midPanel.add(employeePanel);
      } else {
         AdminPanel adminPanel = new AdminPanel();
         appController.addAdminListeners(adminPanel);
         midPanel.add(adminPanel);
      }

      mainFrame.setMainPanelComponents(midPanel);
   }
}
