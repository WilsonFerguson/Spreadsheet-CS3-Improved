import java.util.*;

public class RangeFunction {

    public static double evaluate(Sheet sheet, String methodName, List<SheetLocation> locations) {
        double[] cells = new double[locations.size()];
        for (int i = 0; i < locations.size(); i++) {
            cells[i] = sheet.getValue(locations.get(i).toString());
        }

        // Call the function with the name methodName
        methodName = methodName.toLowerCase();
        switch (methodName) {
            case "sum":
                return sum(cells);
            case "avg":
                return avg(cells);
            case "max":
                return max(cells);
            case "min":
                return min(cells);
            default:
                return Double.NaN;
        }
    }

    public static double sum(double[] values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum;
    }

    public static double avg(double[] values) {
        return sum(values) / values.length;
    }

    public static double max(double[] values) {
        double max = values[0];
        for (double value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static double min(double[] values) {
        double min = values[0];
        for (double value : values) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

}
