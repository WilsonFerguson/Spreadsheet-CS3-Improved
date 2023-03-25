import java.util.LinkedList;
import java.util.List;

// CS3 Spreadsheet FormulaCell class.  Fill in the details.

public class FormulaCell extends RealCell {
	private Sheet sheet; // needed to find values of cells in the formula
	private boolean valid = true;

	public FormulaCell(String str, Sheet sheetIn) {
		super(str);
		this.sheet = sheetIn;
	}

	private double handleMethod(String[] args) {
		String methodName = args[0].trim().toUpperCase();

		List<SheetLocation> cells = new LinkedList<>();
		cells = Helper.parseRange(args[1]);
		if (cells == null)
			return Double.NaN;

		// If any cells in the range are invalid, the formula is invalid
		for (SheetLocation cell : cells) {
			if (Double.isNaN(sheet.getValue(cell.toString()))) {
				valid = false;
				return Double.NaN;
			}
		}

		return RangeFunction.evaluate(sheet, methodName, cells);
	}

	private double handleOperations(String[] args) {
		double value = 0; // ig you have to set it to 0, got an error otherwise

		while (args.length >= 3) {
			double left = sheet.getValue(args[0]);
			double right = sheet.getValue(args[2]);
			if (left == Double.NaN || right == Double.NaN) {
				valid = false;
				return Double.NaN;
			}

			String operator = args[1];

			switch (operator) {
				case "+":
					value = left + right;
					break;
				case "-":
					value = left - right;
					break;
				case "*":
					value = left * right;
					break;
				case "/":
					value = left / right;
					break;
				default:
					// Invalid expression in this case
					valid = false;
					return Double.NaN;
			}

			String[] newArgs = new String[args.length - 2];
			newArgs[0] = String.valueOf(value);
			for (int i = 3; i < args.length; i++) {
				newArgs[i - 2] = args[i];
			}

			args = newArgs;
		}

		return value;
	}

	@Override
	public double getDoubleValue() {
		String formula = fullCellText().substring(2, fullCellText().length() - 2); // Remove the parentheses

		String[] args = formula.split(" ");

		if (args.length == 1)
			return sheet.getValue(args[0]);

		double value = handleMethod(args);
		if (!Double.isNaN(value))
			return value;

		return handleOperations(args);
	}

	@Override
	public String abbreviatedCellText() {
		double value = getDoubleValue();

		String output = String.valueOf(value);
		if (!valid)
			output = "#ERROR";

		output = String.format("%-10s", output).substring(0, 10); // ChatGPT moment
		return output;
	}
}
