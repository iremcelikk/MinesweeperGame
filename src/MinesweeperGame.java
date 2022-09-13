import java.util.Arrays;
import java.util.Scanner;

public class MinesweeperGame {
	int line;
	int column;
	String[][] mineField;
	String[][] defaultField;
	Scanner sc = new Scanner(System.in);
	int numberOfMine;

	public MinesweeperGame(int line, int column) {
		this.line = line;
		this.column = column;
		defaultField = new String[this.line][this.column];
		numberOfMine = (int) ((this.line * this.column) / 4.0);
	}

///////////////////// COPY ARRAY
	public static String[][] copy(String[][] defaultField, String[][] mineField) {

		mineField = new String[defaultField.length][];
		for (int i = 0; i < defaultField.length; i++) {
			mineField[i] = defaultField[i].clone();
		}
		return mineField;
	}

//////////////////////////////PRINT FIELD
	void printField(String[][] field) {
		System.out.println();
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

////////////////////////////// CREATE DEFAULT FIELD
	String[][] createDefaultField(String[][] field) {

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j] = "-";
			}
			System.out.println();
		}
		return field;
	}

	////////////////////////////// CREATE MINE FIELD
	String[][] createMineField(String[][] field) {

		int i = 0;
		int lineMine;
		int columnMine;

		while (i < numberOfMine) {
			lineMine = (int) (Math.random() * this.line);
			columnMine = (int) (Math.random() * this.column);

			if (!(field[lineMine][columnMine].equals("*"))) {
				field[lineMine][columnMine] = "*";
				i++;
			}
		}
		return field;
	}

	///////////////// START GAME
	void startGame() {
		int selected_line;
		int selected_column;
		boolean isDone = false;
		int repeatingNumber = 0;

		while (!isDone) {
			System.out.println("=================================");
			System.out.println("Enter the line(starts with 0):");
			selected_line = sc.nextInt();
			System.out.println("Enter the column(starts with 0):");
			selected_column = sc.nextInt();
			int counter = 0;

			if (selected_line < 0 || selected_line >= this.line || selected_column < 0
					|| selected_column >= this.column) {
				System.out.println("Invalid numbers.Try Again!!!");
				printField(defaultField);
			} else {
				if (mineField[selected_line][selected_column] == "*") {
					System.out.println("\nGame Over");
					printField(mineField);
					isDone = true;
				} else if ((defaultField[selected_line][selected_column] != "-")) {
					System.out.println("Coordinates Selected Before. Try Again!!!");
					printField(defaultField);
				} else {
					repeatingNumber++;
					for (int i = (selected_line - 1 < 0 ? 0
							: selected_line - 1); i <= (selected_line + 1 > defaultField.length - 1
									? defaultField.length - 1
									: selected_line + 1); i++) {

						for (int j = (selected_column - 1 < 0 ? 0
								: selected_column - 1); j <= (selected_column + 1 > defaultField[0].length - 1
										? defaultField[0].length - 1
										: selected_column + 1); j++) {

							if (!(i == selected_line && j == selected_column) && mineField[i][j] == "*") {
								counter++;
							}
						}
					}
					defaultField[selected_line][selected_column] = String.valueOf(counter);
					mineField[selected_line][selected_column] = String.valueOf(counter);
					printField(defaultField);

					isDone = didWin(repeatingNumber);
					if (isDone) {
						System.out.println("\nYou won :)");
						printField(mineField);
					}
				}
			}
		}
	}

	////////////////////// didWin
	boolean didWin(int repeatingNumber) {
		if (this.numberOfMine + repeatingNumber == this.line * this.column)
			return true;
		return false;
	}

	////////////////////////////// RUN
	void run() {

		this.defaultField = createDefaultField(this.defaultField);
		printField(this.defaultField);

		this.mineField = copy(this.defaultField, this.mineField);
		this.mineField = createMineField(mineField);

		startGame();
	}

}