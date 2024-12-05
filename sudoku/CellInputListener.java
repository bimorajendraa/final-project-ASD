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