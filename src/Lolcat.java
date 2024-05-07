import java.util.Scanner;

/**
 * A Java implementation of lolcat that outputs stdin in a colorful text.
 */
public class Lolcat {
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			int i = 0;

			while (scanner.hasNext()) {
				String next = scanner.nextLine();

				// '%' character causes parsing exception
				next.replace("%", "");

				String[] arr = next.split("");
		
				for (String c: arr) {
					int c256 = (i % 127) + 64;
					System.out.printf("\u001B[38;5;%dm" + c + "\u001B[0;0m", c256);
					i++;
				}

				System.out.println();
			}
		} 
	}
}
