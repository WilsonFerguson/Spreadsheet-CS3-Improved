// CS3 Spreadsheet RealCell class.  Fill in the details.

public class RealCell extends Cell {
	private String fullText; // Stores full text of this cell
	
	public RealCell(String str) {
		this.fullText = str;
	}

	public double getDoubleValue() {
		return 0.0; // TODO: change this to return this cell's value
	}
	
	@Override
	public String abbreviatedCellText() {
		return "0.0"; // TODO: change this to call getDoubleValue and format the result
	}

	@Override
	public String fullCellText() {
		return this.fullText;
	}
}
