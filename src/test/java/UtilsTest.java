import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UtilsTest {

	@Test
	public void isValidCoordinateTest() {
		int size = 3;
		boolean marked[][] = new boolean[size][size];

		assertTrue(Utils.isValidCoordinate("0,0", size, marked));
		assertTrue(Utils.isValidCoordinate("1,2", size, marked));

		// out of boundary coordinate testing off by one
		assertFalse(Utils.isValidCoordinate("3,3", size, marked));

		// out of boundary coordinate
		assertFalse(Utils.isValidCoordinate("4,4", size, marked));

		// occupied coordinate, first time usage
		marked[2][1] = true;
		assertFalse(Utils.isValidCoordinate("2,1", size, marked));

		// occupied coordinate that was valid before
		marked[0][0] = true;
		assertFalse(Utils.isValidCoordinate("0,0", size, marked));

		/////// testing new size \\\\\\\
		size = 7;
		marked = new boolean[size][size];

		assertTrue(Utils.isValidCoordinate("0,0", size, marked));
		assertTrue(Utils.isValidCoordinate("5,6", size, marked));

		// out of boundary coordinate testing off by one
		assertFalse(Utils.isValidCoordinate("7,7", size, marked));

		// out of boundary coordinate
		assertFalse(Utils.isValidCoordinate("180,255", size, marked));

		// occupied coordinate, first time usage
		marked[4][4] = true;
		assertFalse(Utils.isValidCoordinate("4,4", size, marked));

		// occupied coordinate that was valid before
		marked[5][6] = true;
		assertFalse(Utils.isValidCoordinate("5,6", size, marked));

	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	// Invalid boardSize config
	@Test
	public void checkConfigValuesTestCase1() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Invalid boardSize config");
		Utils.checkConfigValues(new GameConfigTestingHelper("11", "a", "b", "c"));
	}

	// Invalid humanPlayerOne config with 2 chars
	@Test
	public void checkConfigValuesTestCase2() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Invalid humanPlayerOne config");
		Utils.checkConfigValues(new GameConfigTestingHelper("5", "aa", "b", "c"));
	}

	// Invalid humanPlayerTwo config with 3 chars
	@Test
	public void checkConfigValuesTestCase3() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Invalid humanPlayerTwo config");
		Utils.checkConfigValues(new GameConfigTestingHelper("5", "a", "bbb", "c"));
	}

	// Invalid computerPlayer config with empty string
	@Test
	public void checkConfigValuesTestCase4() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Invalid computerPlayer config");
		Utils.checkConfigValues(new GameConfigTestingHelper("5", "a", "b", ""));
	}
}
