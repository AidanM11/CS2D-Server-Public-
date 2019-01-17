import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel{

	private GameState gamestate;
	private Map map;
	private int blockSize;
	private Image[] tileset_skins;
	
	
	
	public Panel(GameState gamestate, Map map) {
		super();
		this.gamestate = gamestate;
		this.map = map;
		this.blockSize = 30;
		
		
		tileset_skins = new Image[100];
		
		for(int i = 0; i<tileset_skins.length;i++) {
			tileset_skins[i] = new ImageIcon("res/tileset_skins.png").getImage();
			tileset_skins[i] = createImage(new FilteredImageSource(tileset_skins[i].getSource(), new CropImageFilter(0,25 * i, 25,25)));
			
		}
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		
		drawMap(g, map);
		
		for(int i = 0; i < gamestate.getPlayers().size(); i++) {
			drawPlayer(g, gamestate.getPlayers().get(i));
		}
		for(int i = 0; i < gamestate.getBullets().size(); i++) {
			drawBullet(g, gamestate.getBullets().get(i));
		}
		
		
		
	}
	public void drawMap(Graphics g, Map map) {
		Block block;
		int centerWidth = (this.getWidth()/2) - (map.getMapWidth() * blockSize / 2);
		int centerHeight = (this.getHeight()/2) - (map.getMapHeight() * blockSize / 2);
		for(int y = 0; y < map.getBlock().length; y++) {
			for (int x = 0; x < map.getBlock()[0].length; x++) {
				block = map.getBlock()[y][x];
				
				//g.fillRect(centerWidth + (x * blockSize),centerHeight + (y * blockSize), blockSize, blockSize);
				g.drawImage(tileset_skins[block.getBlockID()] 	,centerWidth + (x * blockSize),centerHeight + (y * blockSize), blockSize, blockSize, null);
				//g.setColor(Color.RED);
				//g.drawRect(centerWidth + (block.getHitbox().x), centerHeight + (block.getHitbox().y), block.getHitbox().width, block.getHitbox().height);
		}
	}
}
	
	
	public void drawPlayer(Graphics g, Player player) {
		int centerWidth = (this.getWidth()/2) - (map.getMapWidth() * blockSize / 2);
		int centerHeight = (this.getHeight()/2) - (map.getMapHeight() * blockSize / 2);
		g.setColor(Color.ORANGE);
		g.fillRect(player.getX() + centerWidth,player.getY() + centerHeight,player.getSize(),player.getSize());
		player.setPlayerHitbox();
		//g.setColor(Color.GREEN);
		//g.drawRect(player.getPlayerHitbox().x, player.getPlayerHitbox().y, player.getPlayerHitbox().width, player.getPlayerHitbox().height);
		
	}
	
	public void drawBullet(Graphics g, Bullet bullet) {
		int centerWidth = (this.getWidth()/2) - (map.getMapWidth() * blockSize / 2);
		int centerHeight = (this.getHeight()/2) - (map.getMapHeight() * blockSize / 2);
		
		g.setColor(Color.BLACK);
		g.fillRect(bullet.getX() + centerWidth, bullet.getY() + centerHeight, bullet.getBulletSize(), bullet.getBulletSize());
		
		
	}
}
