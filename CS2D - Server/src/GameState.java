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
	private ArrayList<WeaponTemplate> weaponTemplates;
	private static int startingMoney = 6000;
	private static int killMoney = 300;
	private static int roundWinMoney = 600;
	
	
	
	

	
	public GameState(Map map) {
		super();
		this.players = new ArrayList<Player>();
		this.bullets = new ArrayList<Bullet>();
		this.map = map;
		this.weaponTemplates = new ArrayList<WeaponTemplate>();
		//int firerate, int maxAmmo, int reloadTime, int damage, int bulletSize, int bulletSpeed, String name, int cost
		this.weaponTemplates.add(new WeaponTemplate(7, 30, 210, 2, 4, 8, "AK-47", 400));
		this.weaponTemplates.add(new WeaponTemplate(60, 5, 450, 20, 8, 12, "AWP", 400));
		this.weaponTemplates.add(new WeaponTemplate(2, 100, 550, 1, 2, 6, "Negev", 400));
		this.weaponTemplates.add(new WeaponTemplate(35, 6, 210, 10, 12, 2, "Shotgun", 400));
		this.weaponTemplates.add(new WeaponTemplate(2, 60, 210, 2, 2, 2, "SMG", 400));
		
		
	}
	public void update(HashMap<SocketAddress, boolean[]> keys) {
		
		for(int i = 0; i < this.players.size(); i++) {
			Player p = getPlayers().get(i);
			if(p.isDead()) {
				continue;
			}
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
					p.setRotation(0);
				}
				
			
			}
			if(currKeys[1] == true) {
				p1 = new Player(p);
				p1.setX(pX - pMoveSpd);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setX(pX - pMoveSpd);
					p.setRotation(2);
				}
				
			}
			if(currKeys[2] == true) {
				p1 = new Player(p);
				p1.setY(pY + pMoveSpd);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setY(pY + pMoveSpd);
					p.setRotation(1);
				}
				
			}
			if(currKeys[3] == true) {
				p1 = new Player(p);
				p1.setX(pX + pMoveSpd);
				p1.setPlayerHitbox();
				if(map.collides(p1) ==  false) {
					p.setX(pX + pMoveSpd);
					p.setRotation(3);
				}
			}
			p.setVelY(p.getY() - pY);
			p.setVelX(p.getX() - pX);
			
			if(p.getBuyWeapCooldown() <= 0) {
				for(int k = 0; k < 6; k++) {
					if(currKeys[k + 8] == true) {
						ArrayList<WeaponTemplate>weaps = this.weaponTemplates;
						if(weaps.size()>k) {
							WeaponTemplate w = weaps.get(k);
							if(w.getCost() <= p.getMoney()) {
								p.setMoney(p.getMoney() - w.getCost());
								p.setWeapon(w.createWeapon());
								p.setBuyWeapCooldown(100);
								break;
							}
							
						}
					}
				}
			}
			else {
				p.setBuyWeapCooldown(p.getBuyWeapCooldown() - 1);
			}
			
			if(p.getWeapon() != null) {
				if(p.getWeapon().isReloading() == false && p.getWeapon().isFireable() == true) {
					int damage = p.getWeapon().getDamage();
					int bSize = p.getWeapon().getBulletSize();
					int bulletSpeed = p.getWeapon().getBulletSpeed();
					
					if(currKeys[4] == true) {
						bullets.add(new Bullet(p.getX(), p.getY(), p.getVelX(), -bulletSpeed, bSize, damage, p));
						p.getWeapon().updateBulletFired();
						p.setRotation(0);
					}
					else if(currKeys[5] == true) {
						bullets.add(new Bullet(p.getX(), p.getY(), p.getVelX(), bulletSpeed, bSize, damage, p));
						p.getWeapon().updateBulletFired();
						p.setRotation(1);
					}
					else if(currKeys[6] == true) {
						bullets.add(new Bullet(p.getX(), p.getY(), -bulletSpeed, p.getVelY(), bSize, damage, p));
						p.getWeapon().updateBulletFired();
						p.setRotation(2);
					}
					else if(currKeys[7] == true) {
						bullets.add(new Bullet(p.getX(), p.getY(), bulletSpeed, p.getVelY(), bSize, damage,  p));
						p.getWeapon().updateBulletFired();
						p.setRotation(3);
					}
				}
				if(currKeys[4] == true) {
					p.setRotation(0);
				}
				else if(currKeys[5] == true) {
					p.setRotation(1);
				}
				else if(currKeys[6] == true) {
					p.setRotation(2);
				}
				else if(currKeys[7] == true) {
					p.setRotation(3);
				}
			}
			if(p.getWeapon() != null) {
				p.getWeapon().update();
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
				if(p.isDead()) {
					continue;
				}
				if(p.getPlayerHitbox().intersects(b.getBulletHitbox()) && b.getBulletID().getTeam() != p.getTeam()) {
					System.out.println("bullet hit");
					this.playerHit(p, b);
					if(p.getHealth() <= 0) {
						p.setDead(true);
						b.getBulletID().setMoney(b.getBulletID().getMoney() + killMoney);
					}
					this.bullets.remove(b);
				}
			}
		}
		
//		for( int i = 0; i < this.players.size(); i++) {
//			Player p = players.get(i);
//			if(p.getHealth() <= 0) {
//				p.setDead(true);
//			}
//		}
		if(teamsAlive() != 2) {
			for( int i = 0; i < this.players.size(); i++) {
				Player p = players.get(i);
				if(p.getTeam() == teamsAlive()) {
					p.setMoney(p.getMoney() + roundWinMoney);
				}
			}
			respawnAllPlayers();
		}
			
			
			
			
			
			
			
	}	
	
	public void playerHit(Player p, Bullet b) {
		p.setHealth(p.getHealth() - b.getDamage());
	}
	public void respawnAllPlayers() {
		for( int i = 0; i < this.players.size(); i++) {
			Player p = players.get(i);
			p.setX((int) this.getMap().getSpawnPoints().get(p.getTeam()).getX());
			p.setY((int) this.getMap().getSpawnPoints().get(p.getTeam()).getY());
			p.setHealth(15);
			p.setDead(false);
		}
	}
	
	/**
	 * 
	 * @return 2 if both teams alive, otherwise number of team still alive
	 */
	public int teamsAlive() {
		if(this.players.size() <= 1) {
			return 2;
		}
		int t0Count = 0;
		int t1Count = 0;
		for( int i = 0; i < this.players.size(); i++) {
			Player p = players.get(i);
			if(p.isDead() == false) {
				if(p.getTeam() == 0) {
					t0Count++;
				}
				else {
					t1Count++;
				}
			}
		}
		if(t0Count > 0 && t1Count > 0) {
			return 2;
		}
		if(t0Count > 0) {
			return 0;
		}
		else {
			return 1;
		}
	} 
		
	//LOTS OF WORK NEEDED. TEAMS, CONSISTENT SIZE, SPAWNPOINT
	public void createPlayer(SocketAddress add) {
		int team;
		int spawnX;
		int spawnY;
		if(this.getSizeOfTeam(0) > this.getSizeOfTeam(1)) {
			team = 1;
			spawnX = (int) this.getMap().getSpawnPoints().get(1).getX();
			spawnY = (int) this.getMap().getSpawnPoints().get(1).getY();
		}
		else {
			team = 0;
			spawnX = (int) this.getMap().getSpawnPoints().get(0).getX();
			spawnY = (int) this.getMap().getSpawnPoints().get(0).getY();
			
		}
		Player p = new Player(spawnX,spawnY, 30, team, startingMoney, add);
		
		this.players.add(p);
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
	public Player getPlayerByAddress(SocketAddress addr) {
		for(int i = 0; i < this.players.size(); i++) {
			if(this.players.get(i).getAddress().equals(addr)) {
				return this.players.get(i);
			}
		}
		System.out.println("Could not find player with given address");
		return null;
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
	
	public int getSizeOfTeam(int team) {
		int num = 0;
		for(int i = 0; i < this.players.size(); i++) {
			Player p = this.players.get(i);
			if(p.getTeam() == team) {
				num++;
			}
		}
		return num;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	public static byte[] serialize(GameState gamestate, SocketAddress recAddr) {
		ByteArrayOutputStream baOut = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(baOut);
		try {
			dataOut.writeInt(0);
			dataOut.writeInt(gamestate.players.size());
			for(int i = 0; i < gamestate.players.size(); i++) {
				Player p = gamestate.players.get(i);
				dataOut.writeInt(p.getX());
				dataOut.writeInt(p.getY());
				dataOut.writeInt(p.getTeam());
				dataOut.writeInt(p.getRotation());
				dataOut.writeInt(p.getMoney());
				//Tells client whether this player is the one it controls
				if(p.getAddress().equals(recAddr)) {
					dataOut.writeInt(1);
				}
				else {
					dataOut.writeInt(0);
				}
				if(p.getWeapon() == null) {
					dataOut.writeInt(0);
				}
				else {
					if(p.getWeapon().isReloading()) {
						dataOut.writeInt(1);
					}
					else {
						dataOut.writeInt(0);
					}
				}
				if(p.isDead()) {
					dataOut.writeInt(1);
				}
				else {
					dataOut.writeInt(0);
				}
			}
			dataOut.writeInt(gamestate.bullets.size());
			for(int i = 0; i < gamestate.bullets.size(); i++) {
				Bullet b = gamestate.bullets.get(i);
				dataOut.writeInt(b.getX());
				dataOut.writeInt(b.getY());
				dataOut.writeInt(b.getBulletSize());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return baOut.toByteArray();
	}
	
	
	
	public static byte[] serializeMap(GameState gamestate) {
		ByteArrayOutputStream baOut = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(baOut);
		try {
			dataOut.writeInt(1);
			dataOut.writeInt(gamestate.getMap().getMapHeight());
			dataOut.writeInt(gamestate.getMap().getMapWidth());
			for(int y = 0; y < gamestate.getMap().getMapHeight(); y++) {
				for(int x = 0; x < gamestate.getMap().getMapWidth(); x++) {
					dataOut.writeInt(gamestate.getMap().getBlock()[y][x].getBlockID());
				}
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
				newState.addPlayer(new Player(x,y,30,team,0,null));
				System.out.println(x+ " " + y+ " " + team );
			}
			int bulletsNum = dataIn.readInt();
			for(int i = 0; i < bulletsNum; i++) {
				int x = dataIn.readInt();
				int y = dataIn.readInt();
				int size = dataIn.readInt();
				newState.addBullet(new Bullet(x,y, 0, 0, size, 0, null));
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return newState;
	}
	
	public static Map deserializeMap(byte[] data) {
		ByteArrayInputStream baIn = new ByteArrayInputStream(data);
		DataInputStream dataIn = new DataInputStream(baIn);
		Map newMap = new Map();
		try {
			int height = dataIn.readInt();
			int width = dataIn.readInt();
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					newMap.getBlock()[y][x] = new Block(x, y, newMap.getBlockSize(), dataIn.readInt());
				}
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return newMap;
	}
	
	
	
	
	
		
		
	
	
}


