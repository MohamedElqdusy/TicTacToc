
public class Utils {
	private static boolean isVaildChar(String str) {
		return str.length() == 1;
	}

	private static boolean isVaildSize(String str) {
		return str.length() == 1 && Integer.valueOf(str) >= 3 && Integer.valueOf(str) <= 10;
	}

	public static void checkConfigValues(GameConfig gameConfig) throws Exception {
		if (!isVaildSize(gameConfig.getBoardSizeProp())) {
			throw new Exception("Invalid boardSize config");
		}
		if (!isVaildChar(gameConfig.getHumanPlayerOneProp())) {
			throw new Exception("Invalid humanPlayerOne config");
		}
		if (!isVaildChar(gameConfig.getHumanPlayerTwoProp())) {
			throw new Exception("Invalid humanPlayerTwo config");
		}
		if (!isVaildChar(gameConfig.getComputerPlayerProp())) {
			throw new Exception("Invalid computerPlayer config");
		}
	}

	public static boolean isValidCoordinate(String coordinate, int boardSize, boolean marked[][]) {
		String parts[] = coordinate.split(",");
		int i, j;
		try {
			i = Integer.valueOf(parts[0]);
			j = Integer.valueOf(parts[1]);
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		if (parts.length > 2 || i > boardSize - 1 || j > boardSize - 1) {
			System.out.println(" Invalid coordinat format");
			return false;
		}
		if (marked[i][j]) {
			System.out.println("Occupied cell!");
			return false;
		}
		return true;
	}
}
