/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group 4
 * 1 - 5026231092 - Muhammad Fawwaz Al-Amien 
 * 2 - 5026231161 - Muhammad Daniel Alfarisi
 * 3 - 5026231210 - Bimo Rajendra Widyadhana
 */

package connectfour;

import javax.swing.*;

public class C4Main {
    public static void main(String[] args) {
        // Launch the home page
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage();
            homePage.show();
        });
    }
}