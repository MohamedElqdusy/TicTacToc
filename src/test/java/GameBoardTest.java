import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	GameConfigTestingHelper gameconfig1;
	GameBoardTesting gameboard1;

	char board1[][];

	@Before
	public void initializeGameConfig() throws Exception {
		System.setOut(new PrintStream(outContent));
		gameconfig1 = new GameConfigTestingHelper("3", "a", "b", "c");
		gameboard1 = new GameBoardTesting(gameconfig1);
	}

	@Test
	public void PrintHorizontalBoarderTest() {
		assertEquals(gameboard1.printHorizontalBoarder(), "################");
	}

	@Test
	public void printBoardTest() {
		gameboard1.printBoard();
		String boarder = "################\n" + 
						 "|    |    |    |\n" + 
						 "################\n" + 
						 "|    |    |    |\n" + 
						 "################\n" + 
						 "|    |    |    |\n" + 
						 "################\n";
		assertEquals(boarder, outContent.toString());

	}

	@Test
	public void getComputerCoordinateTest() {
		String coordinate = gameboard1.getValidComputerCoordinate();
		assertTrue(isValidRandomCoordiante(coordinate, gameboard1.getBoardSize()));
	}

	private static boolean isValidRandomCoordiante(String str, int n) {
		String parts[] = str.split(",");
		int i = Integer.valueOf(parts[0]);
		int j = Integer.valueOf(parts[1]);
		if ((i >= 0 && i < n) || (j >= 0 && j < n)) {
			return true;
		}
		return false;
	}

	@Test
	public void isFullTest() {
		assertFalse(gameboard1.isFull());

		gameboard1.markCell("0,0");
		gameboard1.markCell("0,1");
		gameboard1.markCell("0,2");
		gameboard1.markCell("1,0");
		gameboard1.markCell("1,1");
		gameboard1.markCell("1,2");

		assertFalse(gameboard1.isFull());

		gameboard1.markCell("2,0");
		gameboard1.markCell("1,2");
		gameboard1.markCell("2,2");

		assertTrue(gameboard1.isFull());
	}

	@Test
	public void isWinningFirstDiagTest() {
		assertFalse(gameboard1.isWinningFirstDiag());
		gameboard1.markCell("0,0");
		assertFalse(gameboard1.isWinningFirstDiag());
		gameboard1.markCell("1,1");
		assertFalse(gameboard1.isWinningFirstDiag());
		gameboard1.markCell("2,2");
		assertTrue(gameboard1.isWinningFirstDiag());
	}

	@Test
	public void isWinningSecondDiagTest() {
		assertFalse(gameboard1.isWinningSecondDiag());
		gameboard1.markCell("0,2");
		assertFalse(gameboard1.isWinningSecondDiag());
		gameboard1.markCell("1,1");
		assertFalse(gameboard1.isWinningSecondDiag());
		gameboard1.markCell("2,0");
		assertTrue(gameboard1.isWinningSecondDiag());
	}

	@Test
	public void checkDiagonalsTest() {
		assertFalse(gameboard1.isWinningSecondDiag());
		gameboard1.markCell("0,2");
		assertFalse(gameboard1.isWinningSecondDiag());
		gameboard1.markCell("1,1");
		assertFalse(gameboard1.isWinningSecondDiag());
		gameboard1.markCell("2,0");
		assertTrue(gameboard1.isWinningSecondDiag());

		gameboard1.initializeBoard();

		assertFalse(gameboard1.isWinningFirstDiag());
		gameboard1.markCell("0,0");
		assertFalse(gameboard1.isWinningFirstDiag());
		gameboard1.markCell("1,1");
		assertFalse(gameboard1.isWinningFirstDiag());
		gameboard1.markCell("2,2");
		assertTrue(gameboard1.isWinningFirstDiag());

	}

	@Test
	public void checkRowsTest() {
		assertFalse(gameboard1.checkRows());
		gameboard1.markCell("0,0");
		assertFalse(gameboard1.checkRows());
		gameboard1.markCell("0,1");
		assertFalse(gameboard1.checkRows());
		gameboard1.markCell("0,2");
		assertTrue(gameboard1.checkRows());

	}

	@Test
	public void isWinningRowTest() {
		assertFalse(gameboard1.isWinningRow(0));
		gameboard1.markCell("0,0");
		assertFalse(gameboard1.isWinningRow(0));
		gameboard1.markCell("0,1");
		assertFalse(gameboard1.isWinningRow(0));
		gameboard1.markCell("0,2");
		assertTrue(gameboard1.isWinningRow(0));

		gameboard1.initializeBoard();

		assertFalse(gameboard1.isWinningRow(1));
		gameboard1.markCell("1,0");
		assertFalse(gameboard1.isWinningRow(1));
		gameboard1.markCell("1,1");
		assertFalse(gameboard1.isWinningRow(1));
		gameboard1.markCell("1,2");
		assertTrue(gameboard1.isWinningRow(1));

		gameboard1.initializeBoard();

		assertFalse(gameboard1.isWinningRow(2));
		gameboard1.markCell("2,0");
		assertFalse(gameboard1.isWinningRow(2));
		gameboard1.markCell("2,1");
		assertFalse(gameboard1.isWinningRow(2));
		gameboard1.markCell("2,2");
		assertTrue(gameboard1.isWinningRow(2));

	}

	@Test
	public void isWinningColTest() {
		assertFalse(gameboard1.isWinningCol(0));
		gameboard1.markCell("0,0");
		assertFalse(gameboard1.isWinningCol(0));
		gameboard1.markCell("1,0");
		assertFalse(gameboard1.isWinningCol(0));
		gameboard1.markCell("2,0");
		assertTrue(gameboard1.isWinningCol(0));

		gameboard1.initializeBoard();

		assertFalse(gameboard1.isWinningCol(1));
		gameboard1.markCell("0,1");
		assertFalse(gameboard1.isWinningCol(1));
		gameboard1.markCell("1,1");
		assertFalse(gameboard1.isWinningCol(1));
		gameboard1.markCell("2,1");
		assertTrue(gameboard1.isWinningCol(1));

		gameboard1.initializeBoard();

		assertFalse(gameboard1.isWinningCol(2));
		gameboard1.markCell("0,2");
		assertFalse(gameboard1.isWinningCol(2));
		gameboard1.markCell("1,2");
		assertFalse(gameboard1.isWinningCol(2));
		gameboard1.markCell("2,2");
		assertTrue(gameboard1.isWinningCol(2));

	}

	@Test
	public void checkColmsTest() {
		assertFalse(gameboard1.checkColms());
		gameboard1.markCell("0,0");
		assertFalse(gameboard1.checkColms());
		gameboard1.markCell("1,0");
		assertFalse(gameboard1.checkColms());
		gameboard1.markCell("2,0");
		assertTrue(gameboard1.checkColms());
	}

	@Test
	public void isWinnerTest() {
		// test diagonal
		assertFalse(gameboard1.isWinner());
		gameboard1.markCell("0,0");
		assertFalse(gameboard1.isWinner());
		gameboard1.markCell("1,1");
		assertFalse(gameboard1.isWinner());
		gameboard1.markCell("2,2");
		assertTrue(gameboard1.isWinner());

		gameboard1.initializeBoard();

		// test col
		assertFalse(gameboard1.isWinningCol(1));
		gameboard1.markCell("0,1");
		assertFalse(gameboard1.isWinningCol(1));
		gameboard1.markCell("1,1");
		assertFalse(gameboard1.isWinningCol(1));
		gameboard1.markCell("2,1");
		assertTrue(gameboard1.isWinningCol(1));

		gameboard1.initializeBoard();

		// test row
		assertFalse(gameboard1.isWinningRow(1));
		gameboard1.markCell("1,0");
		assertFalse(gameboard1.isWinningRow(1));
		gameboard1.markCell("1,1");
		assertFalse(gameboard1.isWinningRow(1));
		gameboard1.markCell("1,2");
		assertTrue(gameboard1.isWinningRow(1));
	}

	@Test
	public void getBoardSizeTest() {
		assertEquals(gameboard1.getBoardSize(), 3);
	}

	@Test
	public void getHumanPlayerOneTest() {
		assertEquals(gameboard1.getHumanPlayerOne(), 'a');
	}

	@Test
	public void getHumanPlayerTwoTest() {
		assertEquals(gameboard1.getHumanPlayerTwo(), 'b');
	}

	@Test
	public void getComputerPlayerTest() {
		assertEquals(gameboard1.getComputerPlayer(), 'c');
	}

	@Test
	public void getCurrentPlayerTest() {
		assertEquals(gameboard1.getCurrentPlayer(), 'a');
	}

	@Test
	public void getPrevPlayerTest() {
		assertEquals(gameboard1.getPrevPlayer(), 'a');
	}

	class GameBoardTesting extends GameBoard {

		public GameBoardTesting(GameConfig gameConfig) throws Exception {
			// to prevent buggy reference copy behaviour we should set it using
			// Super(param).
			super(gameConfig);
		}
	}
}
