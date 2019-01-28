import javax.swing.JOptionPane;

public class Main {
	
  private static Renderer render;
  private static GameState gamestate;
  private static InputHandle input;
  private static Map map;
  private static ConnectionHandler conn;
  private static InputHandler inHandle;
  private static Frame frame;
  private static String ip;
  private static String portString;

  
	
	public static void main(String[] args) {
		//Create input handle and give it to gamestate constuctor as arg
		frame = new Frame();
		frame.init();
		portString = JOptionPane.showInputDialog(frame.getContentPane(), "Enter Port Number:");
		ip = JOptionPane.showInputDialog(frame.getContentPane(), "Enter IP Number:");
		map = new Map();
		map.loadMap();
		gamestate = new GameState(map);
		conn = new ConnectionHandler();
		inHandle = new InputHandler(conn, gamestate);
		conn.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inHandle.start();
	}
	
	public static void setGameState(GameState argGameState) {
		gamestate.setEqualTo(argGameState);
	}
	public static GameState getGameState() {
		return gamestate;
	}
	
	public static String getIp() {
		return ip;
	}
	public static String getPort() {
		return portString;
	}
}


//Notes for aidan
//Added bullet class
//Made inputhandle class take arrow keys
//added bullet arraylist and other getters/setters
//added drawBullet function
