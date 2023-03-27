import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// CS3 Spreadsheet DateCell class.  Fill in the details.

public class DateCell extends Cell {
	private static final String pattern = "MM/dd/yyyy"; // ?Not sure if it's necessary to make this static, I just don't
														// want it to make a variable for every instance of the class
	private static final DateFormat dateFormat = new SimpleDateFormat(pattern);

	private Date date;

	// Here I am using Date so it won't keep the formatting as the user wanted, but
	// I do like that it will make everything consistent so idk
	public DateCell(String date) {
		try {
			this.date = dateFormat.parse(date);
		} catch (Exception e) {
		}
	}

	public static boolean isValidDate(String date) {
		try {
			dateFormat.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String abbreviatedCellText() {
		String output = fullCellText();
		output = String.format("%-10s", output).substring(0, 10); // ChatGPT moment
		return output;
	}

	@Override
	public String fullCellText() {
		return dateFormat.format(date);
	}
}