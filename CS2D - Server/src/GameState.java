import java.io.Serializable;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
public class GameState implements Serializable {

	private InputHandle input;
	private ArrayList<Player> players;
	private ArrayList<Bullet> bullets;
	private Map map;
	
	
	
	

	
	public GameState(InputHandle input, Map map) {
		super();
		this.input = input;
		this.players = new ArrayList<Player>();
		this.bullets = new ArrayList<Bullet>();
		this.map = map;
		
		
	}
	public void update(HashMap<SocketAddress, boolean[]> keys) {
		
		for(int i = 0; i < this.players.size(); i++) {
			Player p = getPlayers().get(i);
			Player p1;
			boolean[] currKeys = keys.get(p.getAddress());
			int pY = getPlayers().get(0).getY();
			int pX = getPlayers().get(0).getX();
			if(currKeys[0] == true) {
				p1 = new Player(p);
				p1.setY(pY - 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setY(pY - 2);
				}
				
			
			}
			if(currKeys[1] == true) {
				p1 = new Player(p);
				p1.setX(pX - 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setX(pX - 2);
				}
				
			}
			if(currKeys[2] == true) {
				p1 = new Player(p);
				p1.setY(pY + 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setY(pY + 2);
				}
				
			}
			if(currKeys[3] == true) {
				p1 = new Player(p);
				p1.setX(pX + 4);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setX(pX + 2);
				}
			}
			
			if(currKeys[4] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), 0, -12, 5, 0));
			}
			else if(currKeys[5] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), 0, 12, 5, 0));
			}
			else if(currKeys[6] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), -12, 0, 5, 0));
			}
			else if(currKeys[7] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), 12, 0, 5, 0));
			}		
		}
	
		
			
			
			
		for(int i = 0; i < this.bullets.size(); i++) {
			Bullet b = this.bullets.get(i);
			b.setX(b.getX() + b.getVelX());
			b.setY(b.getY() + b.getVelY());
			if(map.bulletCollides(b)) {
				this.bullets.remove(b);
			}
		}
			
			
			
			
			
			
			
	}	
		
	//LOTS OF WORK NEEDED. TEAMS, CONSISTENT SIZE, SPAWNPOINT
	public void createPlayer(SocketAddress add) {
		this.players.add(new Player(200,200, 30, 0, add));
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	public InputHandle getInput() {
		return input;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setEqualTo(GameState gs) {
		this.players = gs.players;
		this.bullets = gs.bullets;
		this.map = gs.map;
	}
	
	
	
	
	
		
		
	
	
}


