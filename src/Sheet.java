// CS3 Spreadsheet Sheet class. Update this file with your own code.

public class Sheet {
	// TODO in checkpoint 1: Declare class constants for number of rows and columns

	// TODO in checkpoint 2: declare a field as a 2-dimensional array of Cell
	
	public Sheet() {
		// TODO Implement this in checkpoint 2, to initialize the field to the proper
		// size 2-dimensional array of Cell, and set all elements to new Cell instances.
	}

	// TODO: Add private methods for commands.  They should return strings.
	

	public String processCommand(String command) {
		// TODO Implement this in checkpoints 1, 2, and 3, creating and calling private methods as needed.
		// For checkpoint 1, if command is an empty string (length zero), then return an empty string result.
		// For checkpoint 2, handle these commands, see project specification for details, note you
		// also need to create and implement the SpreadsheetLocation and TextCell classes and use them here:
		//     cell inspection, example: A1
		//     assignment of string values, example: A1 = "Hello"
		//     clearing the entire sheet, example: clear
		//     clearing a single cell, example: clear A1
		// For checkpoint 3, handle these additional variations of the assignment command, see project specification for
		// details, note you also need to create/implement/use the DateCell, RealCell, and FormulaCell classes
		//     assignment of date value, example: A1 = 1/1/2014
		//     assignment of real values, example A1 = 5.2
		//     assignment of real formulas, examples A1 = ( A2 * 4 + A3 ) or A1 = ( sum A1-d4 )
		// Note this method should be complete in checkpoint 3.
		// For checkpoints 4 and the final project, all changes should be in the FormulaCell class, except
		// for any fixes to bugs in previous checkpoints and possibly optional features.

		return "";
	}

	public int getRows() {
		// TODO Implement this in checkpoint 1
		return 0;
	}

	public int getCols() {
		// TODO Implement this in checkpoint 1
		return 0;
	}

	public Cell getCell(Location loc) {
		// TODO Implement this in checkpoint 2
		return null;
	}

	// TODO: override the toString method to return a friendly representation of the sheet
	
}
