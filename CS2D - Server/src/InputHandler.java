import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class InputHandler extends Thread {
	private ConnectionHandler connHandle;
	private GameState gamestate;

	public InputHandler(ConnectionHandler connHandle, GameState gamestate) {
		super();
		this.connHandle = connHandle;
		this.gamestate = gamestate;
	}
	
	public void run() {
		
		while(true) {
			HashMap<SocketAddress, boolean[]> keys = connHandle.getAllKeys();
			ArrayList<SocketAddress> players = new ArrayList<SocketAddress>();
			players.addAll(keys.keySet());
			ArrayList<Player> currPl = gamestate.getPlayers();
			for(int i = 0; i < players.size(); i++) {
				if(containsAddress(players.get(i), currPl) == -1) {
					gamestate.createPlayer(players.get(i));
				}
			}
			gamestate.update(keys);
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		}
	}
	
	public int containsAddress(SocketAddress add, ArrayList<Player> players) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getAddress().equals(add)) {
				return i;
			}
		}
		return -1;
	}
}
