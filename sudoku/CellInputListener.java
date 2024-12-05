package sudoku;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// [TODO 2] Define a Listener Inner Class for all the editable Cells
class CellInputListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get a reference of the JTextField that triggers this action event
        Cell sourceCell = (Cell)e.getSource();

        // Retrieve the int entered
        int numberIn = Integer.parseInt(sourceCell.getText());
        // For debugging
        System.out.println("You entered " + numberIn);

        /*
         * [TODO 5] (later - after TODO 3 and 4)
         * Check the numberIn against sourceCell.number.
         * Update the cell status sourceCell.status,
         * and re-paint the cell via sourceCell.paint().
         */
        //if (numberIn == sourceCell.number) {
        //   sourceCell.status = CellStatus.CORRECT_GUESS;
        //} else {
        //   ......
        //}
        //sourceCell.paint();   // re-paint this cell based on its status

        /*
         * [TODO 6] (later)
         * Check if the player has solved the puzzle after this move,
         *   by calling isSolved(). Put up a congratulation JOptionPane, if so.
         */
    }
}