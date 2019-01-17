
public class Main {
	
  private static Renderer render;
  private static GameState gamestate;
  private static InputHandle input;
  private static Map map;
  private static ConnectionHandler conn;
  private static InputHandler inHandle;

  
	
	public static void main(String[] args) {
		//Create input handle and give it to gamestate constuctor as arg
		map = new Map();
		map.loadMap();
		gamestate = new GameState(input, map);
		conn = new ConnectionHandler();
		inHandle = new InputHandler(conn, gamestate);
		conn.start();
		inHandle.start();
	}
	
	public static void setGameState(GameState argGameState) {
		gamestate.setEqualTo(argGameState);
	}
	public static GameState getGameState() {
		return gamestate;
	}
	
}


//Notes for aidan
//Added bullet class
//Made inputhandle class take arrow keys
//added bullet arraylist and other getters/setters
//added drawBullet function
