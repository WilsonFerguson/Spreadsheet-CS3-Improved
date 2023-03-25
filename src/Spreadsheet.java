// CS3 Spreadsheet Spreadsheet class.  Fill in the details.

import java.util.Scanner;

public class Spreadsheet {
	public static Scanner console = new Scanner(System.in);

	public static void main(String[] args) {
		Sheet sheet = new Sheet(); // Keep this as the first statement in main

		System.out.println(sheet);
		while (true) {
			System.out.print("Enter a command: ");
			String command = console.nextLine();
			if (command.toLowerCase().equals("quit")) {
				break;
			}
			System.out.println(sheet.processCommand(command));
		}
	}
}
