import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import com.sun.tools.javac.util.ArrayUtils;

public class Map implements Serializable{

	private int mapHeight;
	private int mapWidth;
	private ArrayList<Point> spawnPoints;
	
	private int blockSize;
	
	private Block[][] block;
	
	
	
	public Map() {
		this.mapHeight = 25;
		this.mapWidth = 25;
		this.blockSize = 30;
		this.spawnPoints = new ArrayList<Point>();
		spawnPoints.add(null);
		spawnPoints.add(null);
		block = new Block[mapWidth][mapHeight];
	}
	
	public void createDefaultMap() {
		
		for(int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x] = new Block(x, y, 30, 0);
			}
		}
	}
	
	public void loadMap() {
		Save save = new Save(this, blockSize);
		block = save.loadSave(new File("save/save1.txt"));
		this.updateSpawnpoints();
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public int getMapWidth() {
		return mapWidth;
	}


	public Block[][] getBlock() {
		return block;
	}
	

	public ArrayList<Point> getSpawnPoints() {
		return spawnPoints;
	}

	public void setSpawnPoints(ArrayList<Point> spawnPoints) {
		this.spawnPoints = spawnPoints;
	}

	public boolean collides(Player p) {
		int[] collisionBlocks = {1,2,3};
		for(int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				if(block[y][x].getHitbox().intersects(p.getPlayerHitbox())) {
					for(int i = 0; i < collisionBlocks.length; i++) {
						if(block[y][x].getBlockID() == collisionBlocks[i]) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean bulletCollides(Bullet b) {
		
		for(int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				if(block[y][x].getHitbox().intersects(b.getBulletHitbox()) && block[y][x].getBlockID() != 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void updateSpawnpoints() {
		boolean team1HasSpawn = false;
		boolean team2HasSpawn = false;
		for(int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				if(block[y][x].getBlockID() == 6) {
					this.spawnPoints.set(0, new Point(x * blockSize,y * blockSize));
					team1HasSpawn = true;
				}
				if(block[y][x].getBlockID() == 7) {
					this.spawnPoints.set(1, new Point(x * blockSize,y * blockSize));
					team2HasSpawn = true;
				}
			}
		}
		if((team2HasSpawn && team1HasSpawn) == false) {
			System.out.println("NO SPAWNPOINT ASSIGNED");
		}
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	

	
	
	
	
}
