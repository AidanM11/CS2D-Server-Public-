import java.awt.*;
import java.net.InetAddress;
import java.net.SocketAddress;

public class Player {

	private int x, y, size, playerID;
	private int team;
	private SocketAddress address;
	private Rectangle playerhitbox;
	
	public Player(int x, int y, int size, int team, SocketAddress address) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.team = team;
		this.address = address;
		playerhitbox = new Rectangle(getX(),getY(),size,size);
		
	}
	public Player (Player player) {
		this.x = player.getX();
		this.y = player.getY();
		this.size = player.getSize();
		this.playerID = player.getPlayerID();
		this.team = 0;
		this.playerhitbox = new Rectangle(getX(),getY(),size,size);
	}
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public int getSize() {
		return size;
	}
	
	public void setX(int xPos) {
		x = xPos;
	}

	public void setY(int yPos) {
		y = yPos;
	}

	public int getPlayerID() {
		return playerID;
	}
	
	public Rectangle getPlayerHitbox() {
		return playerhitbox;
	}
	
	public void setPlayerHitbox() {
		playerhitbox.setBounds(getX(), getY(), size, size);
		
	}
	public int getTeam() {
		return team;
	}
	public SocketAddress getAddress() {
		return address;
	}
	public Rectangle getPlayerhitbox() {
		return playerhitbox;
	}
	
	
	
	
}
