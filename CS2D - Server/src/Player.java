import java.awt.*;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.SocketAddress;

public class Player implements Serializable{

	private int x, velX, velY, y, size, playerID, rotation;
	private int team;
	private int health;
	private Weapon weapon;
	private SocketAddress address;
	private Rectangle playerhitbox;
	private int money;
	private int buyWeapCooldown;
	private boolean dead;
	
	public Player(int x, int y, int size, int team, int money, SocketAddress address) {
		super();
		this.x = x;
		this.y = y;
		this.velX = 0;
		this.velY = 0;
		this.size = size;
		this.team = team;
		this.address = address;
		this.health = 15;
		this.rotation = 0;
		this.money = money;
		playerhitbox = new Rectangle(getX(),getY(),size,size);
		this.buyWeapCooldown = 0;
		this.dead=false;
		
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
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getBuyWeapCooldown() {
		return buyWeapCooldown;
	}
	public void setBuyWeapCooldown(int buyWeapCooldown) {
		this.buyWeapCooldown = buyWeapCooldown;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	
	
	
	
	
	
	
	
}
