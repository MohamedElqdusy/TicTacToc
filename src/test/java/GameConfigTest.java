import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GameConfigTest {

	private GameConfigTestingHelper gameConfigTesting1;
	private GameConfigTestingHelper gameConfigTesting2;

	@Before
	public void initializeGameConfig() {
		gameConfigTesting1 = new GameConfigTestingHelper("4", "a", "b", "c");
		gameConfigTesting2 = new GameConfigTestingHelper("025", "xasd", "yu", "");
	}

	@Test
	public void getBoardSizePropTest() {
		assertEquals(gameConfigTesting1.getBoardSizeProp(), "4");
		assertEquals(gameConfigTesting2.getBoardSizeProp(), "025");
	}

	@Test
	public void getHumanPlayerOnePropTest() {
		assertEquals(gameConfigTesting1.getHumanPlayerOneProp(), "a");
		assertEquals(gameConfigTesting2.getHumanPlayerOneProp(), "xasd");

	}

	@Test
	public void getHumanPlayerTwoPropTest() {
		assertEquals(gameConfigTesting1.getHumanPlayerTwoProp(), "b");
		assertEquals(gameConfigTesting2.getHumanPlayerTwoProp(), "yu");
	}

	@Test
	public void getComputerPlayerPropTest() {
		assertEquals(gameConfigTesting1.getComputerPlayerProp(), "c");
		assertEquals(gameConfigTesting2.getComputerPlayerProp(), "");

	}

}
