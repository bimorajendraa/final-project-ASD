package GraphicalTicTacToe;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public enum Seed {   // to save as "Seed.java"
    CROSS("X", "GraphicalTicTacToe/image/Cat1.jpg"),   // displayName, imageFilename
    NOUGHT("O", "GraphicalTicTacToe/image/anjing.jpg"),
    NO_SEED(" ", null);

    // Private variables
    private String displayName;
    private Image img = null;

    // Constructor (must be private)
    private Seed(String name, String imageFilename) {

        this.displayName = name;

        if (imageFilename != null) {
            URL imgURL = getClass().getClassLoader().getResource(imageFilename);
            ImageIcon icon = null;
            if (imgURL != null) {
                icon = new ImageIcon(imgURL);
                //System.out.println(icon);  // debugging
            } else {
                System.err.println("Couldn't find file " + imageFilename);
            }
            img = icon.getImage();
        }
    }

    // Public getters
    public String getDisplayName() {
        return displayName;
    }
    public Image getImage() {
        return img;
    }
}