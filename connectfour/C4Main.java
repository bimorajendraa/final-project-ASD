package connectfour;

import javax.swing.*;
import java.awt.*;

public class C4Main {
    public static void main(String[] args) {
        // Launch the home page
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage();
            homePage.show();
        });
    }
}