
public class Weapon {
	private int firerate, currFireTime, maxAmmo, currAmmo, reloadTime, currReloadTime, bulletSize, bulletSpeed, damage;
	private String name;
	private boolean fireable;
	private boolean reloading;
	/**
	 * @param firerate
	 * @param maxAmmo
	 * @param reloadTime
	 * @param damage
	 * @param name
	 */
	public Weapon(int firerate, int maxAmmo, int reloadTime, int damage, int bulletSize, int bulletSpeed, String name) {
		super();
		this.firerate = firerate;
		this.maxAmmo = maxAmmo;
		this.reloadTime = reloadTime;
		this.damage = damage;
		this.bulletSize = bulletSize;
		this.bulletSpeed = bulletSpeed;
		this.name = name;
		this.currAmmo = maxAmmo;
		this.currFireTime = 0;
		this.currReloadTime = 0;
		this.reloading = false;
		this.fireable = true;
	}
	
	
	
	public void update() {
		if(fireable == false && currFireTime <= 0) {
			fireable = true;
		}
		else if(fireable == false && currFireTime > 0) {
			currFireTime--;
		}
		if(reloading == false && currAmmo <= 0) {
			reloading = true;
			currReloadTime = reloadTime;
		}
		else if(reloading == true && currReloadTime <= 0) {
			reloading = false;
			currAmmo = maxAmmo;
		}
		else if(reloading == true && currReloadTime > 0) {
			currReloadTime--;
		}
	}
	
	public void updateBulletFired() {
		currAmmo--;
		this.fireable = false;
		this.currFireTime = firerate;
	}


	public int getFirerate() {
		return firerate;
	}


	public int getMaxAmmo() {
		return maxAmmo;
	}


	public int getCurrAmmo() {
		return currAmmo;
	}


	public int getReloadTime() {
		return reloadTime;
	}


	public int getCurrReloadTime() {
		return currReloadTime;
	}


	public int getDamage() {
		return damage;
	}


	public String getName() {
		return name;
	}


	public boolean isReloading() {
		return reloading;
	}



	public int getCurrFireTime() {
		return currFireTime;
	}



	public boolean isFireable() {
		return fireable;
	}



	public int getBulletSize() {
		return bulletSize;
	}



	public int getBulletSpeed() {
		return bulletSpeed;
	}
	
	
	
	
	
	
	
}
