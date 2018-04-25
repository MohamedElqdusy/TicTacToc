public class Game {
	GameBoard gameBoard;

	public Game() throws Exception {
		gameBoard = new GameBoard();
	}

	public void play() {
		System.out.println("Tic Tac Toc  is Running..");
		gameBoard.printBoard();

		while (!gameBoard.isFull() && !gameBoard.isWinner()) {
			gameSequence();
		}
		gameEnd();
	}

	public void gameSequence() {
		System.out.println("Current Player Mark is " + gameBoard.getCurrentPlayer());
		gameBoard.takeTurn();
		gameBoard.printBoard();
		gameBoard.changePlayer();
	}

	public void gameEnd() {
		if (gameBoard.isWinner()) {
			System.out.println("The winner is " + gameBoard.getPrevPlayer());
		} else {
			System.out.println("The board is Full");
		}
	}

	public static void main(String[] args) throws Exception {
		Game game = new Game();
		game.play();
	}

}
