import java.awt.*;
import java.io.Serializable;

public class Bullet implements Serializable{

	private int x,y,velX, velY, bulletSize;
	private Player bulletID;
	private Rectangle bullethitbox;
	
	public Bullet(int x, int y,int velX, int velY, int bulletSize, Player bulletID) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		bullethitbox = new Rectangle(x,y,bulletSize,bulletSize);
		this.bulletSize = bulletSize;
		this.bulletID = bulletID;
	}
	
	public void setX(int xPos) {
		this.x = xPos;
		this.setBulletHitbox();
	}
	public void setY(int yPos) {
		this.y = yPos;
		this.setBulletHitbox();
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getBulletSize() {
		return bulletSize;
	}

	public int getVelX() {
		return velX;
	}

	public int getVelY() {
		return velY;
	}

	public Player getBulletID() {
		return bulletID;
	}
	public void setBulletHitbox() {
		bullethitbox.setBounds(this.x,this.y,this.bulletSize,this.bulletSize);
	}
	public Rectangle getBulletHitbox() {
		return bullethitbox;
	}
	
	
	
}
