import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int line;
		int column;

		System.out.println("Enter the number of line");
		line = sc.nextInt();
		System.out.println("Enter the number of column");
		column = sc.nextInt();

		MinesweeperGame game = new MinesweeperGame(line, column);
		game.run();
	}
}