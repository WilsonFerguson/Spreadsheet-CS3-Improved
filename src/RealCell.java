// CS3 Spreadsheet RealCell class.  Fill in the details.

public class RealCell extends Cell {
	private String fullText; // Stores full text of this cell

	public RealCell(String str) {
		this.fullText = str;
	}

	public double getDoubleValue() {
		return Double.parseDouble(fullText);
	}

	@Override
	public String abbreviatedCellText() {
		String output = String.format("%-10s", String.valueOf(getDoubleValue())).substring(0, 10); // ChatGPT moment
		return output;
	}

	@Override
	public String fullCellText() {
		return fullText;
	}
}
