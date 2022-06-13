import businesslogic.Controller;
import businesslogic.DeliveryService;
import presentation.MainFrame;

/**
 * <p>Class that executes the application</p>
 */
public class MainClass {
    /**
     * <p>Runs the application</p>
     * @param args void
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.getInstance();
        Controller mainController = new Controller(mainFrame);
        DeliveryService.getInstance().loadAll();
        mainFrame.setVisible(true);
    }
}


