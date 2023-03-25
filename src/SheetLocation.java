// CS3 Spreadsheet SheetLocation class.  Fill in the details.

public class SheetLocation extends Location {

	public SheetLocation(int rowIn, int colIn) {
		super(rowIn, colIn);
	}

	public SheetLocation(String str) {
		this(Integer.parseInt(str.substring(1)) - 1, Character.toUpperCase(str.charAt(0)) - 'A');
		// TODO refactor this it's so bad
	}

	// TODO make these functions manipulate this object?
	// If so make sure to change FormulaCell to work with it
	public SheetLocation right() {
		return new SheetLocation(getRow(), getCol() + 1);
	}

	public SheetLocation left() {
		return new SheetLocation(getRow(), getCol() - 1);
	}

	public SheetLocation up() {
		return new SheetLocation(getRow() - 1, getCol());
	}

	public SheetLocation down() {
		return new SheetLocation(getRow() + 1, getCol());
	}

	@Override
	public String toString() {
		return String.format("%c%d", getCol() + 'A', getRow() + 1);
	}

}
