/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group 4
 * 1 - 5026231092 - Muhammad Fawwaz Al-Amien 
 * 2 - 5026231161 - Muhammad Daniel Alfarisi
 * 3 - 5026231210 - Bimo Rajendra Widyadhana
 */

package sudoku;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO 2 Define a Listener Inner Class for all the editable Cells
class CellInputListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get a reference of the JTextField that triggers this action event
        Cell sourceCell = (Cell)e.getSource();

        // Retrieve the int entered
        int numberIn = Integer.parseInt(sourceCell.getText());
        // For debugging
        System.out.println("You entered " + numberIn);


    }
}