// CS3 Spreadsheet TextCell class.

public class TextCell extends Cell {

	private String text;

	public TextCell(String text) {
		this.text = text;
	}

	/** Does not include enclosing quotes */
	@Override
	public String abbreviatedCellText() {
		String output = String.format("%-10s", text).substring(0, 10); // ChatGPT moment
		return output;
	}

	/** Includes enclosing quotes */
	@Override
	public String fullCellText() {
		return "\"" + text + "\"";
	}
}
