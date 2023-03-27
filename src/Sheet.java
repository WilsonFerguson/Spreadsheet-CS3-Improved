import java.util.List;
import java.util.Stack;

// CS3 Spreadsheet Sheet class. Update this file with your own code.

public class Sheet {
	private static int ROWS = 20;
	private static int COLS = 12;

	private Cell[][] sheet;
	private Stack<Sheet> history = new Stack<>();

	public Sheet() {
		sheet = new Cell[ROWS][COLS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				sheet[i][j] = new Cell();
			}
		}
	}

	public Sheet clone() {
		Sheet clone = new Sheet();
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				clone.sheet[i][j] = sheet[i][j];
			}
		}
		return clone;
	}

	private void setCell(Location location, Cell cell) {
		sheet[location.getRow()][location.getCol()] = cell;
	}

	private Cell getCellFromString(String value) {
		// First check if it's a cell reference
		if (Helper.isValidCell(value))
			return getCell(value); // Return a reference to the cell

		if (value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"')
			return new TextCell(value.substring(1, value.length() - 1));
		else if (value.charAt(0) == '(' && value.charAt(value.length() - 1) == ')')
			return new FormulaCell(value, this);
		else if (DateCell.isValidDate(value))
			return new DateCell(value);
		else if (Helper.isNumber(value))
			return new RealCell(value);

		return null;
	}

	private String processGetCell(String command) {
		if (!Helper.isValidCell(command))
			return null;

		return getCell(command).fullCellText();
	}

	private String processSetCell(String command) {
		if (!command.contains("="))
			return null;

		String[] parts = command.split("=", 2);
		String cellString = parts[0].trim();
		String value = parts[1].trim();

		if (!Helper.isValidCell(cellString))
			return null;

		Location location = new SheetLocation(cellString);
		Cell cell = getCellFromString(value);

		if (cell == null)
			return "Invalid cell value";

		history.push(clone());
		setCell(location, cell);
		return toString();
	}

	private String processClear(String command) {
		if (command.toLowerCase().equals("clear")) {
			history.push(clone());
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLS; j++) {
					sheet[i][j] = new Cell();
				}
			}
			return toString();
		}

		if (!command.toLowerCase().startsWith("clear "))
			return null;

		String cellString = command.substring("clear ".length()).trim();
		List<SheetLocation> cells = Helper.parseRange(cellString);
		if (cells == null)
			return null;

		for (SheetLocation cell : cells) {
			setCell(cell, new Cell());
		}

		return toString();
	}

	private String processUndo(String command) {
		if (command.toLowerCase().equals("undo")) {
			if (history.isEmpty())
				return "Cannot undo";

			sheet = history.pop().sheet;
			return toString();
		}

		return null;
	}

	public String processCommand(String command) {
		if (command.equals(""))
			return "";
		String output;

		// A1
		if ((output = processGetCell(command)) != null)
			return output;

		// A1 = 1
		if ((output = processSetCell(command)) != null)
			return output;

		// clear or clear A1
		if ((output = processClear(command)) != null)
			return output;

		// undo or redo
		if ((output = processUndo(command)) != null)
			return output;

		return toString();
	}

	public int getRows() {
		return ROWS;
	}

	public int getCols() {
		return COLS;
	}

	public Cell getCell(Location loc) {
		return sheet[loc.getRow()][loc.getCol()];
	}

	public Cell getCell(String cell) {
		return getCell(new SheetLocation(cell));
	}

	public Cell getCell(int row, int col) {
		return getCell(new SheetLocation(row, col));
	}

	public double getValue(String cell) {
		cell = cell.toUpperCase();
		if (Helper.isNumber(cell))
			return Double.parseDouble(cell);

		Cell c = getCell(cell);
		if (c instanceof RealCell) {
			return ((RealCell) c).getDoubleValue();
		}

		return Double.NaN;
	}

	@Override
	public String toString() {
		String output = "   |";
		for (int i = 0; i < COLS; i++) {
			output += (char) ('A' + i) + "         |";
		}
		output += "\n";

		for (int i = 0; i < ROWS; i++) {
			output += (i + 1);
			if (i < 9)
				output += "  |";
			else
				output += " |";
			for (int j = 0; j < COLS; j++) {
				output += getCell(i, j).abbreviatedCellText() + "|";
			}
			output += "\n";
		}

		return output;

	}
}
