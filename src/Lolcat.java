import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * A Java implementation of lolcat that outputs stdin in a colorful text.
 */
public class Lolcat {
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			int i = 0;

			if (!has256Color()) {
				throw new RuntimeException("Your console doesn't support 8-bit color.");
			}

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

	private static boolean has256Color() {
		String command = "tput colors";
		try {
			Process proc = Runtime.getRuntime().exec(command);

			proc.waitFor();

			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream())); 

			boolean has256 = br.readLine().equals("256"); 

			br.close();

			return has256;

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return false;
	}
}
