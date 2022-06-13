package presentation.panels;

import presentation.UIFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * <p>Employee panel</p>
 */
@SuppressWarnings("all")
public class EmployeePanel extends JPanel implements Observer {
   private final static EmployeePanel INSTANCE = new EmployeePanel();
   private final JTextArea ordersText = UIFactory.createTextArea();

   private EmployeePanel() {
      this.add(UIFactory.createLabel("Prepare the following orders: "));
      ordersText.setText("ORDERS AWAITING\n");
      JScrollPane ordersScroll = new JScrollPane(ordersText);
      ordersScroll.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(Color.BLACK)));
      ordersScroll.setPreferredSize(new Dimension(700, 380));
      this.add(ordersScroll);
   }

   public static EmployeePanel getInstance() { return INSTANCE; }

   @Override
   public void update(Observable o, Object arg) {
      ordersText.append(arg.toString() + "\n");
   }
}
