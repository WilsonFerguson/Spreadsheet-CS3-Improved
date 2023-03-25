import java.util.LinkedList;
import java.util.List;

public class Helper {

    private static Sheet sheet = new Sheet();

    public static boolean isValidCell(String cell) {
        if (cell.length() < 2)
            return false;
        cell = cell.toUpperCase();

        char col = cell.charAt(0);
        if (col < 'A' || col > 'A' + sheet.getCols() - 1)
            return false;

        String row = cell.substring(1);
        if (!isNumber(row))
            return false;

        int rowInt = Integer.parseInt(row);
        if (rowInt < 1 || rowInt > sheet.getRows())
            return false;

        return true;
    }

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<SheetLocation> parseRange(String range) {
        if (!range.contains("-") || range.length() < "A1-A1".length()) {
            if (Helper.isValidCell(range)) {
                List<SheetLocation> cells = new LinkedList<>();
                cells.add(new SheetLocation(range));
                return cells;
            }
            return null;
        }

        String[] parts = range.split("-");
        SheetLocation endLocation = new SheetLocation(parts[1]);

        List<SheetLocation> cells = new LinkedList<>();
        SheetLocation current = new SheetLocation(parts[0]);
        while (current.getCol() <= endLocation.getCol()) {
            SheetLocation down = new SheetLocation(current.getRow(), current.getCol());

            while (down.getRow() <= endLocation.getRow()) {
                cells.add(down);
                down = down.down();
            }

            current = current.right();
        }

        return cells;
    }
}
