import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class GameBoard {
	private int boardSize;
	protected char humanPlayerOne;
	private char humanPlayerTwo;
	private char computerPlayer;
	protected GameConfig gameConfig;

	private char board[][];
	private boolean marked[][];

	private char currentPlayer;
	private char prevPlayer;

	private int marksNum;

	public GameBoard() throws Exception {
		this(new GameConfig());
	}

	public GameBoard(GameConfig gameConfig) throws Exception {
		this.gameConfig = gameConfig;
		// check configuration values
		Utils.checkConfigValues(gameConfig);

		// setting values from configuration
		boardSize = Integer.valueOf(gameConfig.getBoardSizeProp());
		humanPlayerOne = gameConfig.getHumanPlayerOneProp().charAt(0);
		humanPlayerTwo = gameConfig.getHumanPlayerTwoProp().charAt(0);
		computerPlayer = gameConfig.getComputerPlayerProp().charAt(0);
		board = new char[boardSize][boardSize];
		marked = new boolean[boardSize][boardSize];
		currentPlayer = getrandomPlayer();
		prevPlayer = currentPlayer;
		initializeBoard();
	}

	protected void initializeBoard() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public void changePlayer() {
		if (currentPlayer == humanPlayerOne) {
			currentPlayer = humanPlayerTwo;
		} else if (currentPlayer == humanPlayerTwo) {
			currentPlayer = computerPlayer;
		} else {
			currentPlayer = humanPlayerOne;
		}
	}

	public void markCell(String coordinate) {
		String parts[] = coordinate.split(",");
		int i = Integer.valueOf(parts[0]);
		int j = Integer.valueOf(parts[1]);
		board[i][j] = currentPlayer;
		marked[i][j] = true;
		marksNum++;
	}

	public void printBoard() {
		System.out.println(printHorizontalBoarder());
		for (int i = 0; i < boardSize; i++) {
			System.out.print("|");
			for (int j = 0; j < boardSize; j++) {
				System.out.print(new StringBuffer().append(" ").append(board[i][j]).append("  |"));
			}
			System.out.println();
			System.out.println(printHorizontalBoarder());

		}
	}

	public void takeTurn() {
		// Computer Turn
		if (currentPlayer == computerPlayer) {
			String coordinate = getValidComputerCoordinate();
			markCell(coordinate);
		} else {
			// Humans turn
			insertCoordinate();
		}
	}

	public boolean isWinner() {
		return checkRows() || checkColms() || checkDiagonals();
	}

	protected boolean checkRows() {
		for (int i = 0; i < boardSize; i++) {
			if (isWinningRow(i)) {
				return true;
			}
		}
		return false;
	}

	protected boolean isWinningRow(int r) {
		int count = 0;
		for (int i = 0; i < boardSize; i++) {
			count += (prevPlayer == board[r][i] ? 1 : 0);
		}
		return count == boardSize;
	}

	protected boolean checkColms() {
		for (int i = 0; i < boardSize; i++) {
			if (isWinningCol(i)) {
				return true;
			}
		}
		return false;
	}

	protected boolean isWinningCol(int c) {
		int count = 0;
		for (int i = 0; i < boardSize; i++) {
			count += (prevPlayer == board[i][c] ? 1 : 0);
		}
		return count == boardSize;
	}

	protected boolean checkDiagonals() {
		if (isWinningFirstDiag() || isWinningSecondDiag()) {
			return true;
		}
		return false;
	}

	protected boolean isWinningFirstDiag() {
		int count = 0;
		for (int i = 0; i < boardSize; i++) {
			count += (prevPlayer == board[i][i] ? 1 : 0);
		}
		return count == boardSize;
	}

	protected boolean isWinningSecondDiag() {
		int count = 0;
		int j = boardSize - 1;
		for (int i = 0; i < boardSize; i++) {
			count += (prevPlayer == board[i][j] ? 1 : 0);
			j--;
		}
		return count == boardSize;
	}

	private String getComputerCoordinate() {
		Random rand = new Random();
		int i = rand.nextInt(boardSize);
		int j = rand.nextInt(boardSize);
		return i + "," + j;

	}

	public String getValidComputerCoordinate() {
		String coordinate = getComputerCoordinate();
		while (!Utils.isValidCoordinate(coordinate, boardSize, marked)) {
			coordinate = getComputerCoordinate();
		}
		return coordinate;
	}

	public void insertCoordinate() {
		System.out.println("Insert your cell coordinates");
		Scanner scanner = new Scanner(System.in);
		String coordinate = scanner.nextLine();
		while (!Utils.isValidCoordinate(coordinate, boardSize, marked)) {
			System.out.println("Insert your cell coordinates");
			// re-enter your coordinate
			coordinate = scanner.nextLine();
		}
		markCell(coordinate);
	}

	public char getrandomPlayer() {
		Random random = new Random();
		int i = random.nextInt(3);

		if (i == 0) {
			return humanPlayerOne;
		} else if (i == 1) {
			return humanPlayerTwo;
		} else {
			return computerPlayer;
		}
	}

	public boolean isFull() {
		return marksNum == (boardSize * boardSize);
	}

	public String printHorizontalBoarder() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < boardSize; i++) {
			sb.append("#####");
		}
		sb.append("#");
		return sb.toString();
	}

	public int getBoardSize() {
		return boardSize;
	}

	public char getHumanPlayerOne() {
		return humanPlayerOne;
	}

	public char getHumanPlayerTwo() {
		return humanPlayerTwo;
	}

	public char getComputerPlayer() {
		return computerPlayer;
	}

	public char getCurrentPlayer() {
		return currentPlayer;
	}

	public char getPrevPlayer() {
		return prevPlayer;
	}

	public boolean[][] getMarked() {
		return marked;
	}
}
