
// helper class to set fields in testing
public class GameConfigTestingHelper extends GameConfig {
	public GameConfigTestingHelper(String boardSizeProp, String humanPlayerOneProp, String humanPlayerTwoProp,
			String computerPlayerProp) {
		this.boardSizeProp = boardSizeProp;
		this.humanPlayerOneProp = humanPlayerOneProp;
		this.humanPlayerTwoProp = humanPlayerTwoProp;
		this.computerPlayerProp = computerPlayerProp;
	}
}
