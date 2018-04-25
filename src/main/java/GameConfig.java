import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;

public class GameConfig {
	protected String boardSizeProp;
	protected String humanPlayerOneProp;
	protected String humanPlayerTwoProp;
	protected String computerPlayerProp;

	public GameConfig() {
		readConfigFile();
	}

	public String getBoardSizeProp() {
		return boardSizeProp;
	}

	public String getHumanPlayerOneProp() {
		return humanPlayerOneProp;
	}

	public String getHumanPlayerTwoProp() {
		return humanPlayerTwoProp;
	}

	public String getComputerPlayerProp() {
		return computerPlayerProp;
	}

	public void readConfigFile() {
		File configFile = new File("config.properties");
		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);
			boardSizeProp = props.getProperty("boardSize");
			humanPlayerOneProp = props.getProperty("humanPlayerOne");
			humanPlayerTwoProp = props.getProperty("humanPlayerTwo");
			computerPlayerProp = props.getProperty("computerPlayer");
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
}