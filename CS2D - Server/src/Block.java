import java.awt.*;
import java.io.Serializable;

public class Block implements Serializable{
	
	private int x, y, blockID;
	private int blockSize;
	Rectangle blockhitbox;

	public Block(int x, int y, int blockSize, int blockID) {
		super();
		this.blockSize = blockSize;
		this.x = x;
		this.y = y;
		this.blockID = blockID;
		this.blockhitbox = new Rectangle(x * blockSize,y * blockSize,blockSize,blockSize);
		
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getBlockID() {
		return blockID;
	}

	@Override
	public String toString() {
		return "Block [x=" + x + ", y=" + y + ", blockID=" + blockID + "]";
	}
	
	public Rectangle getHitbox() {
		return blockhitbox;
	}
	
	
	
	
	
	

}
