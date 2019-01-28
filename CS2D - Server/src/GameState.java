import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
public class GameState implements Serializable {

	private ArrayList<Player> players;
	private ArrayList<Bullet> bullets;
	private Map map;
	
	
	
	

	
	public GameState(Map map) {
		super();
		this.players = new ArrayList<Player>();
		this.bullets = new ArrayList<Bullet>();
		this.map = map;
		
		
	}
	public void update(HashMap<SocketAddress, boolean[]> keys) {
		
		for(int i = 0; i < this.players.size(); i++) {
			Player p = getPlayers().get(i);
			Player p1;
			boolean[] currKeys = keys.get(p.getAddress());
			int pY = getPlayers().get(i).getY();
			int pX = getPlayers().get(i).getX();
			int pMoveSpd = 2;
			if(currKeys[0] == true) {
				p1 = new Player(p);
				p1.setY(pY - pMoveSpd);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setY(pY - pMoveSpd);
				}
				
			
			}
			if(currKeys[1] == true) {
				p1 = new Player(p);
				p1.setX(pX - pMoveSpd);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setX(pX - pMoveSpd);
				}
				
			}
			if(currKeys[2] == true) {
				p1 = new Player(p);
				p1.setY(pY + pMoveSpd);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setY(pY + pMoveSpd);
				}
				
			}
			if(currKeys[3] == true) {
				p1 = new Player(p);
				p1.setX(pX + pMoveSpd);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setX(pX + pMoveSpd);
				}
			}
			p.setVelY(p.getY() - pY);
			p.setVelX(p.getX() - pX);
			
			int bulletSpeed = 6;
			
			if(currKeys[4] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), p.getVelX(), -bulletSpeed, 5, p));
			}
			else if(currKeys[5] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), p.getVelX(), bulletSpeed, 5, p));
			}
			else if(currKeys[6] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), -bulletSpeed, p.getVelY(), 5, p));
			}
			else if(currKeys[7] == true) {
				bullets.add(new Bullet(p.getX(), p.getY(), bulletSpeed, p.getVelY(), 5, p));
			}
			
			p.setPlayerHitbox();
		}
	
		
			
			
			
		for(int i = 0; i < this.bullets.size(); i++) {
			System.out.println("calc bullets");
			Bullet b = this.bullets.get(i);
			b.setX(b.getX() + b.getVelX());
			b.setY(b.getY() + b.getVelY());
			if(map.bulletCollides(b)) {
				this.bullets.remove(b);
			}
			for(int pInd = 0; pInd < players.size(); pInd++) {
				Player p = players.get(pInd);
				if(p.getPlayerHitbox().intersects(b.getBulletHitbox()) && b.getBulletID().getAddress().equals(p.getAddress())== false) {
					System.out.println("bullet hit");
					this.bullets.remove(b);
					this.playerHit(p);
				}
			}
		}
		
		for( int i = 0; i < this.players.size(); i++) {
			Player p = players.get(i);
			if(p.getHealth() <= 0) {
				p.setX(200);
				p.setY(200);
				p.setHealth(10);
			}
		}
			
			
			
			
			
			
			
	}	
	
	public void playerHit(Player p) {
		p.setHealth(p.getHealth() - 1);
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
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public Map getMap() {
		return map;
	}
	
	public void setEqualTo(GameState gs) {
		this.players = gs.players;
		this.bullets = gs.bullets;
		this.map = gs.map;
		System.out.println(this.players.size());
	}
	
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	public static byte[] serialize(GameState gamestate) {
		ByteArrayOutputStream baOut = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(baOut);
		try {
			dataOut.writeInt(gamestate.players.size());
			for(int i = 0; i < gamestate.players.size(); i++) {
				Player p = gamestate.players.get(i);
				dataOut.writeInt(p.getX());
				dataOut.writeInt(p.getY());
				dataOut.writeInt(p.getTeam());
			}
			dataOut.writeInt(gamestate.bullets.size());
			for(int i = 0; i < gamestate.bullets.size(); i++) {
				Bullet b = gamestate.bullets.get(i);
				dataOut.writeInt(b.getX());
				dataOut.writeInt(b.getY());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return baOut.toByteArray();
	}
	
	public static GameState deserialize(byte[] data) {
		ByteArrayInputStream baIn = new ByteArrayInputStream(data);
		DataInputStream dataIn = new DataInputStream(baIn);
		GameState newState = new GameState(Main.getGameState().map);
		try {
			int playerNum = dataIn.readInt();
			System.out.println(playerNum);
			for(int i = 0; i < playerNum; i++) {
				int x = dataIn.readInt();
				int y = dataIn.readInt();
				int team = dataIn.readInt();
				newState.addPlayer(new Player(x,y,30,team,null));
				System.out.println(x+ " " + y+ " " + team );
			}
			int bulletsNum = dataIn.readInt();
			for(int i = 0; i < bulletsNum; i++) {
				int x = dataIn.readInt();
				int y = dataIn.readInt();
				newState.addBullet(new Bullet(x,y, 0, 0, 4, null));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return newState;
	}
	
	
	
	
	
		
		
	
	
}


