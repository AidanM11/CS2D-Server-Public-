import java.awt.*;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.SocketAddress;

public class Player implements Serializable{

	private int x, velX, velY, y, size, playerID;
	private int team;
	private int health;
	private Weapon weapon;
	private SocketAddress address;
	private Rectangle playerhitbox;
	
	public Player(int x, int y, int size, int team, SocketAddress address) {
		super();
		this.x = x;
		this.y = y;
		this.velX = 0;
		this.velY = 0;
		this.size = size;
		this.team = team;
		this.address = address;
		this.health = 15;
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
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public int getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	
	
	
	
	
}
